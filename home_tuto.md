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

Ouvrir le fichier HomeFragment.kt et modifier la méthode onCreateView afin d'utiliser la classe autogénérée par le databinding pour créer la vue de la home. 

```kotlin
override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
```

