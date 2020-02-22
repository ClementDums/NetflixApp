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
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
       ***
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```



