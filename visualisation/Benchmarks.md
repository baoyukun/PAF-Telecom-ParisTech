# Benchmarks

*Ce fichier regroupe les diverses temps d'éxécution des scripts de ce dossier. Elles n'ont pas vocation à être des mesures exactes mais donnent des ordres de grandeurs pour la conception du site.*

**min-FDG.php**

|**fichier traité**|**cut**|**temps d'execution**|
|------------------|-------|---------------------|
|miserables        |2      |<1s                  |
|coauteurs         |50     |82s                  |
|coauteurs         |30     |82s                  |
|coauteurs         |20     |79s                  |
|coauteurs         |10     |90s                  |
|coauteurs         |7      |78s                  |
|coauteurs         |4      |71s                  |
|coauteurs         |2      |74s                  |

**chercheurs.php**

|**fichier traité**|**temps d'execution**|
|------------------|---------------------|
|chercheurstpt     |<1s                  |

**coauteurs.php**

|**fichier traité**|**temps d'execution**|
|------------------|---------------------|
|publis            |18s                  |

**display.php**

|**fichier de données**|**affichage**|**temps d'execution**|
|----------------------|-------------|---------------------|
|coauteurs-min         |FDG,FDGZ     |<1s                  |
|chercheurs            |CT           |<1s                  |
|coauteurs             |FDG,FDGZ     |50s environ          |

# Conclusion sur les benchmarks

A part pour des fichiers de test légers, la création de fichiers de liens coupés (ici, passer de *coauteurs.json* à *coauteurs-min.json* via *min-FDG.php*) est très lente. Elle peut être faite en amont mais il n'est pour l'instant en aucun cas envisageable d'implémenter un curseur qui pilote la variable *cut* en temps réel. Un choix s'impose:
- Décider de la valeur de *cut* au préalable et générer le fichier *coauteurs-min.json* en amont, ce qui est la solution la plus simple
- Générer en amont plusieurs fichiers *coauteurs-min-XX* relatifs à différentes valeurs de *cut*, ce qui nécessite de revoir légèrement l'organisation des fichiers mais qui semble plus pertinent
