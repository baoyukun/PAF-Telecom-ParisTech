# PAF-Telecom-ParisTech

**Analyse de publications scientifiques – Visualisation des résultats**

## Encadrants

[Jean-Claude Moissinac](https://moissinac.wp.imt.fr/) (bureau: E505)

[Cyril Concolato](https://concolato.wp.imt.fr/) (bureau: E504)

## Membres

[BAO Yukun](https://github.com/baoyukun)

[DIALLO Cherif](https://github.com/Mcdiallo)

[FILIU Nino](https://github.com/ninofiliu)

[ZHU Fangda](https://github.com/zhufangda)

## Domaines

_Programmation_, _Informatique_, _Traitement du langage naturel_

## Introduction

L’organisation de la recherche au niveau international conduit à une intensification des efforts de publication. Un mouvement est en marche pour favoriser l’exploitation de ces publications tant par les autres chercheurs que par les industriels et les startups. Cela passe notamment par une analyse des publications scientifiques afin de produire des outils d’aide à leur exploitation : liens entre publications, classements thématiques…

Ce projet vise à exploiter un graphe de connaissances établi à partir d’un corpus de plusieurs centaines de publications de Telecom ParisTech. A partir de cette représentation sémantique des articles, ce projet vise à proposer des analyses et des visualisations. Des compléments au graphe de connaissances pourront être apportés avec des techniques de traitement de la langue naturelle (NLP).

On cherchera ensuite à produire des représentations visuelles, par exemple : groupements d’articles similaires, liens entre des groupes d’articles, visualisation des thèmes dominants d’un groupe d’article, graphes de similitudes entre chercheurs…

Ce projet nécessitera de programmer en Python (pour les traitements) et javascript (pour la visualisation) en s’appuyant sur des bibliothèques facilitant la plupart des traitements (notamment NLTK pour Python et D3 pour javascript).

Dans le cadre de votre projet, vous devrez

- Trouver des regroupements –d’articles, de chercheurs…- par analyse du graphe
- Produire des représentations d’articles ou de groupes d’articles, de chercheurs…

[![demo result](/resource/image0.jpg "demo result")](http://paf.telecom-paristech.fr/projets/analyse-publications-scientifiques-visualisation-resultats)

## Développement du projet

- **Semaine 1**
  - [Vendredi](#vendredi-16-juin-2017)
- **Semaine 2**
  - [Lundi](#lundi-19-juin-2017)
  - [Mardi](#mardi-20-juin-2017)
  - [Mercredi](#mercredi-21-juin-2017)
  - [Jeudi](#jeudi-22-juin-2017)
  - [Vendredi](#vendredi-23-juin-2017)
- **Semaine 3**
  - [Lundi](#lundi-26-juin-2017)
  - [Mardi](#mardi-27-juin-2017)
  - [Mercredi](#mercredi-28-juin-2017)
  - [Jeudi](#jeudi-29-juin-2017)

### Vendredi 16 juin 2017

L'architecture de notre construction sera la suivante:

![architecture](/resource/architecture.png "architecture")

![Organigramme de programmation](/resource/architecture1.png "Organigramme de programmation")

Les opérations de fetching et construction des données ne seront pas forcément réalisées en même temps que l'affichag de ces données, d'où la séparation research/client. Les données sont stockées en partie sous des fichiers .JSON ce qui simplifie leur accès pour la zone client: les données sont alors prêtes à être traitées par les modules D3.js qui affichera les graphiques au client.

Nous avons considéré les informations suivantes comme pertinentes à extraire des publications scientifiques:

- Article
  - Nom des auteurs
  - Date de publication
  - Citations d’autres articles
  - Citations par d’autres articles
  - Thème
  - Mots clefs pertinents
- Chercheur
  - Publications
  - Citations par des articles
  - Articles qu’il cite
  - Chercheurs qui ont travaillé avec lui
  - Thème
  - Evolution de toutes ces données en fonction du temps
- Institution
  - Liste des chercheurs
  - Citations entre les chercheurs
  - Citations par les autres institutions
  - Citations des autres institutions
  - Thème
  - Evolution de toutes ces données en fonction du temps

Nous trouvons que les recherches dans le domaine "_Citation Network_" nous intéressent le plus et qu'il y a beaucoup de ressources à ce sujet disponibles que l'on pourra consulter par la suite. Ci-dessous les principaux programmes qui ont été fait dans le domaine:

- [Action Science Explorer](http://www.cs.umd.edu/hcil/ase/)

ASE  est un outil permettant une compréhension rapide de publications scientifiques. Il est basé sur SocialAction et JabRef. Il permet un management des références, analyse de réseau, et production de représentations visuelle.

On peut choisir de lier les publications selon plusieurs facteurs: date de publication, nombre de citations, etc. On a un moyen d’accéder facilement au “contexte de publication” (phrases dans lesquelles les papiers sont cités).

- [Google Scholar](https://scholar.google.fr/)

GS offre une recherche avancée avec plusieurs options (recherche par mot, par titre, par expression exacte, par thème, par publication, par date, etc) et offre des résultats en fonction de la pertinence. Des informations complémentaires sur les articles sont données: quels articles l'ont cité, comment il a été cité, quels sont des articles liés. Il n’y a pas de visualisation graphique des liens entre les articles ni de quantification développée et à large échelle de ceux-ci.

- [SocialAction](http://www.cs.umd.edu/hcil/socialaction/)

SA est un logiciel qui permet une analyse de réseau et une représentation visuelle de cette analyse. C’est sur ce logiciel que tourne ASE.

- [VOSviewer](http://www.vosviewer.com/)

Programme pour construire et visualiser des données bibliométriques. Au lieu de construire le fichier de références à la main, ce programme permet d'analyser syntaxiquement automatique tout le fichier.

[*Retour au calendrier*](#développement-du-projet)

### Lundi 19 juin 2017

#### Partie Visualisation

Avec Cherif, ma partie du travail est centrée sur le développement d'outils de visualisations des données. J'ai aujourd'hui travaillé sur le dernier maillon de la chaîne d'information: quand les données sont stockées dans un fichier .JSON et qu'elles doivent être envoyées au client poour un affichage html/css/javascript.

Cet affichage base ses données sur des outils de visualisation de .JSON fournis par d3.js. Aujourd'hui, je me suis concentré sur l'exploration des fonctionnalités de ces outils et de leur flexibilité. J'ai tenté de développer des fonctions JavaScript qui interfèrent avec le graph en cours d'affichage mais cela ne me semble pas la meilleure idée. Je pense que, pour afficher un des données mises différemment en valeur on devra plutôt faire un traitement PHP des données en amont.

Par `Nino Filiu` et `Cherif Diallo`

#### Partie Analyse

Afin d'extraire les informations dans le pdf, j'ai tenté d'utiliser pdfminer. Avec cette méthode, on est capable d'obtenir le titre et les auteurs de pdf directement. Ce matin, J'ai aussi éssayé d'utiliser [gscholar](https://github.com/venthur/gscholar)  et [scholar](https://github.com/ckreibich/scholar.py), mais google scholar possède un mécanisme afin de défendre le crawler. Je vais ensuite utiliser l'API de *Scorpus*.

Nous avons aussi essayé d'établir la liste de citation pour chaque article en utilisant le crawler. Cette méthode ça va pour presque un moitié des articles sur le site alors qu'elle ne convient pas pour un autre moitié du à la grande variation des résultats donnés par le crawler. Concernant le graph de connaissance, on détermine d'utiliser jena qui est très couramment utilisé dans le monde SPQRQL et RDF. On est encore en train d'apprendre à utiliser des outils pour manipuler des données déjà aquises et construire un premier graph de connaissance.

Par `ZHU Fangda` et `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Mardi 20 juin 2017

#### Partie Visualisation

J'ai passé la première partie de la journée à déterminer avec Cherif quels données nous allions représenter et avec l'aides de quels graphs. Ce travail est disponible [ici](https://github.com/baoyukun/PAF-Telecom-ParisTech/blob/master/rapports/graphs-possibles.md). Cette détermination permet de savoir quels sont les données à avoir en amont: auteurs, co-auteurs, citations, etc.

J'ai écrit des scripts PHP pour permettre d'aller chercher le fichier JSON, de le modifier pour qu'il ait la forme des fichiers JSON qu'utilisent les plugins D3 que j'utilise, et d'afficher le graph correspondant. J'appelle par la suite cette catégorie de fichiers JSON les PAA (prêts-à-afficher).

Par `Nino Filiu`

#### Partie Analyse

Nous divisons le travail de cette partie en deux morceaux: Fangda prend en charge du graph de connaissance (éventuellement nécessite un apprentissage préliminaire) et Yukun continue à classifier et analyser des articles PDF en mettant toutes les informations dans un format uniforme de json.

Notre schéma de travail pourrait se représenter comme ceci:

```
`Articles téléchargés en PDF`  -----*analyser*---->>  `Données en json`  

-----*construire / mettre en jour (e.g., jena)*---->>  `Graph de connaissance en RDF`

<<----*requête reçue par serveur*-----  `Serveur`  -----*envoyer réponse sous json*---->>  `Visualisation D3`
```

Les données d'un article sont organisées sous json comme ceci:

```json
{
  "identity": "http://givingsense.eu/sembib/data/tpt/paf2017/Nom_du_article_0001",
  "title": "Nom_du_article",
  "ref": "refName",
  "date": {"year": 2017, "month": 6},
  "address": "publicationAddress",
  "belongTo": "booktitle_or_conference_Name",
  "category": "invite",
  "language": "en",
  "audience": "2",
  "project": "projectName",
  "department": "departmentName",
  "group": "groupName",
  "author": [
    {"firstName": "Yukun", "middleName": "", "lastName": "BAO", "affiliation": "telecom-paristech", "ID": "xxx001"},
    {"firstName": "Fangda", "middleName": "", "lastName": "ZHU", "affiliation": "telecom-paristech", "ID": "xxx002"},
    {"firstName": "Nino", "middleName": "", "lastName": "FILIU", "affiliation": "telecom-paristech", "ID": "xxx003"},
    {"firstName": "Cherif", "middleName": "", "lastName": "DIALLO", "affiliation": "telecom-paristech", "ID": "xxx004"}
  ],
  "keyword": ["keyword1", "keyword2", "keyword3"],
  "citationList": [
    {"identity": "", "title": "", "ref": "", "author": [], "date": {}},
    {"identity": "", "title": "", "ref": "", "author": [], "date": {}},
    {"identity": "", "title": "", "ref": "", "author": [], "date": {}}
  ],
  "citedList": [
    {"identity": "", "title": "", "ref": "", "author": [], "date": {}},
    {"identity": "", "title": "", "ref": "", "author": [], "date": {}},
    {"identity": "", "title": "", "ref": "", "author": [], "date": {}}
  ]
}
```

Par `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Mercredi 21 juin 2017

#### Partie Visualisation

J'ai continué le développement des outils de visualisation de fichiers JSON. J'ai écrit des scripts PHP permettant la production de PAA, d'autres produisant des fichiers intermédiaires avant le passage par une sorte de "filtre" PHP qui modifie les données pour ne garder que celles pertinentes. Le détail de ce travail est disponible [ici](https://github.com/baoyukun/PAF-Telecom-ParisTech/edit/master/visualisation)

Par `Nino Filiu`

#### Partie Analyse

J'ai étudié le RDF, SPARSQL et Ontology à l'aide de tutoriel et documentation de Jena.

Par `ZHU Fangda`

De mon part, j'ai essayé plein d'outils pour récupérer, nettoyer et mettre en forme des données. Voici plus d'informations sur les outils et mon remarque:

- [SoPaper, So Easy](https://github.com/ppwwyyxx/SoPaper) qui permet à partir le nom d'un article de chercher et télécharger cet article sur Internet. Pour l'instant, on n'a pas besoin de l'utiliser car on se contente tout au début de construire un graph de connaissance basé sur les [1819 articles](http://givingsense.eu/sembib/data/srcPdf/) fournis par professeur et on les a déjà téléchargés en écrivant un [code Python de quelques lignes](crawler/downloader.py).

- [Grobid](http://grobid.readthedocs.io/en/latest/) qui permet d'extraire des données d'un article de format `PDF`. Les données obtenues sont organisées sous forme `XML` bien structurée et un programme pourrais très facilement "voir" touts les informations liées avec cet article. Grâce aux algorithmes de *Machine Learning*, le résultat est satisfaisant mais malheureusement les erreurs ne sont pas négligeables.

- [PDFMiner](https://github.com/euske/pdfminer), [PyPDF](http://pythonhosted.org/PyPDF2/), [Pdf2htmlEX
](https://github.com/coolwanglu/pdf2htmlEX), [Apache PDFBox](https://pdfbox.apache.org/) et autres logiciels qui permettent d'extraire toute information en plein texte ou bien qui transmettent fichier PDF en fichier texte. Cela est très important parce qu'on doit enfin exploiter des mots clés d'un article à partir du plein texte en utilisant `NLTK (Natural Language Processing Toolkit)`.

- [Scholar.py](https://github.com/ckreibich/scholar.py), [Arxiv-references
](https://github.com/nishimuuu/Arxiv-references), [Scopus-API
](https://github.com/scopus-api/scopus) et crawlers écrits par moi, qui essaient de chercher et de vérifier toutes les informations d'un article. A priori, `Google Scholar` est un bon endroit pour faire ça alors que malheureusement non seulement manque-il des APIs publics, mais aussi il déteste des crawlers récursifs.

Par `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Jeudi 22 juin 2017

[*Retour au calendrier*](#développement-du-projet)

### Vendredi 23 juin 2017

[*Retour au calendrier*](#développement-du-projet)

### Lundi 26 juin 2017

[*Retour au calendrier*](#développement-du-projet)

### Mardi 27 juin 2017

[*Retour au calendrier*](#développement-du-projet)

### Mercredi 28 juin 2017

[*Retour au calendrier*](#développement-du-projet)

### Jeudi 29 juin 2017

[*Retour au calendrier*](#développement-du-projet)
