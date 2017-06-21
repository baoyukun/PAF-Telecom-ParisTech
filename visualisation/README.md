# Visualisation

Ce dossier regroupe tous les scripts permettant un affichage des données.

La méthode utilisée ici pour visualiser les données est la suivante:
1. Pour chaque graph à afficher, il y a un script dédié pour traiter les fichiers JSON
2. Ces scripts créent un fichier JSON "prêt à afficher": le plugin d3.js n'a alors qu'à aller chercher le bon fichier
3. Cette action est effectuée par **display.php**: ce fichier prends en GET le type de graph ainsi que l'adresse du fichier de données, *display.php?dataURL=data/chercheurs.json&graphType=CT* affiche par exemple un collapsible tree (*graphType=CT*) basé sur les données de chercheurs.json (*dataURL=data/chercheurs.json*).

- **chercheurs.php** va lire **data/chercheurstpt.json** et crée **data/chercheurs.json** qui sera affichée par *display.php?dataURL=data/chercheurstptd3.json&graphType=CT* par un collapsible tree (CT)
- **coauteurs.php** va lire **data/publis.json** et crée **data/coauteurs.json** qui sera afiché par *display.php?dataURL=data/coauteurs.json&graphType=FDG* par un force-directed graph (FDG);
