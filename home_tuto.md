# Afficher les catégories de films sur l'accueil 

### Objectifs: 
Modifier la page d'accueil de l'application afin d'y afficher les catégories de films récupérées à partir de l'API. 

### Contraintes: 
- Gérer les données des vues en utilisant le databinding 
- Afficher les catégories sous forme de grid 
- Utiliser le endpoint /genre/movie/list (cf. https://developers.themoviedb.org/3/genres/get-movie-list)

### Marche à suivre 

#### 1. Mise en place du databinding 

1.1 Activer le databinding dans le module (app)

```kotlin
...
apply plugin: 'kotlin-kapt'
...

android {
    ...

    dataBinding {
        enabled = true
    }

}

```

1.2 Utiliser le databiding pour gérer la vue de HomeFragment

Ouvrir le layout du fragment (home_fragment) puis ajouter la balise <layout> </layout> à la racine du document. 
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout>
    <androidx.constraintlayout.widget.ConstraintLayout
       ***
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

1.3 Utiliser le databinding dans le fragment HomeFragment 

> Android Studio générera automatiquement une classe dont les attributs sont les objets de vue déclarer dans le fichier; le nom de chaque élément est déterminer par leur id.

> Le nom de la classe est déterminer par le nom du fichier layout (utilisation du camelCase, les _ sont enlevés). Exemple fragment_home deviendra FragmentHomeBinding. 

Modifier onCreateView dans HomeFragment.kt afin d'utiliser la classe autogénérée par le databinding pour créer la vue de la home. 

```kotlin
...
private lateinit var binding: FragmentHomeBinding
...

override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
```
> Le databinding nous affranchi des tâches répétitives comme findViewById pour récupérer les éléments de vue 

Modifier la méthode onViewCreated...
```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.token.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = "${it.requestToken} - ${it.expiresAt}"
        })

        homeViewModel.error.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = "Erreur $it"
        })

        binding.buttonHome.setOnClickListener {
            val action = HomeFragmentDirections
                    .actionHomeFragmentToHomeSecondFragment("From HomeFragment")
            NavHostFragment.findNavController(this@HomeFragment)
                    .navigate(action)
        }
    }
``` 

1.4 Compiler le projet, s'il n'y a pas d'erreurs vous devriez avoir le même comportement qu'avant. 

#### 2. Ajouter l'action de l'API et les méthodes necessaires permettant de récuper la liste des catégories de films 

* Uri: /genre/movie/list
* Réponse
````json
{
  "genres": [
    {
      "id": 28,
      "name": "Action"
    }
  ]
}
````
> Remarquez que l'API retourne un JSON avec un attribut `genres` qui contient le tableau des catégories. 

2.1 Créer les objets du modèle de données permettant de modéliser la réponse de l'API 

- Dans le package response, créez une classe CategoryReponse 
- Dans le fichier de la classe, créez les deux classes permettant de déserialiser la réponse du serveur 

```kotlin 
internal data class CategoryResponse(
    @SerializedName("genres")
    val genres: List<Genre>
) {
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    )
}
```


2.2. Modifier l'interface du service `MovieService` pour y ajouter une action permettant de lister les catégoris

```kotlin
internal interface MovieService {
    ...

    @GET("/genre/movie/list")
    suspend fun getCategories(): Response<CategoryResponse>
}

``` 

2.3 Modifiez la classe `OnlineDataSource` pour y ajouter la méthode permettant d'exécuter l'action getCategories 

```kotlin
suspend fun getCategories(): Result<List<CategoryResponse.Genre>> {
        return try {
            val response = service.getCategories()
            if (response.isSuccessful) {
                Result.Succes(response.body()!!.genres)
            } else {
                Result.Error(
                    exception = Exception(),
                    message = response.message(),
                    code = response.code()
                )
            }
        } catch (e: Exception) {
            Result.Error(
                exception = e,
                message = e.message ?: "No message",
                code = -1
            )
        }
    }
   ```
2.4 Ajoutez la classe permettant d'exposer les catégories aux autres composants de l'application 
> Ici, il n'y a pas d'intérêt à créer deux classes, car on veut dans tous les cas retourner la liste des catégories; nul besoin de l'encapsuler dans un autre objet. 

Dans le package data, créez la classe Category 
```kotlin 
data class Category(
    val id: Int,
    val name: String
)
```

2.4 Modifiez le repository pour y ajouter la méthode getCategories 

```kotlin
suspend fun getCategories(): Result<List<Category>> {
        return when(val result = online.getCategories()) {
            is Result.Succes -> {
                // On utilise la fonction map pour convertir les catégories de la réponse serveur
                // en liste de categories d'objets de l'application
                val categories = result.data.map {
                    it.toCategory()
                }
                Result.Succes(categories)
            }
            is Result.Error -> result
        }
    }
 ```
 
 2.5 Modifiez le VM `HomeViewModel` pour y ajouter la méthode getCategories afin de récupérer la liste des catégories de film depuis le repository
 
 ```kotlin
 ...
 private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories
 ...
 
 fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getCategories()) {
                is Result.Succes -> {
                    _categories.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
 ```





