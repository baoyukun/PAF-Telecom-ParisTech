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

`Programmation`, `Informatique`, `Traitement du langage naturel`

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

`Articles téléchargés en PDF`  -----*analyser*---->>  `Données en json`  
-----*construire / mettre en jour (e.g., jena)*---->>  `Graph de connaissance en RDF`
<<----*requête reçue par serveur*-----  `Serveur`  -----*envoyer réponse sous json*---->>  `Visualisation D3`

Les données d'un article sont organisées sous json comme ceci:

```json
{
  "identity": "http://givingsense.eu/sembib/data/tpt/paf2017/f_xxxx",
  "title": "Nom_du_article",
  "doi": "doiName(standard identity)",
  "date": {"year": 2017, "month": 6},
  "monTitle": "monographyName",
  "citedCount": "4",
  "lang": "en",
  "author": [
    {"name": {"firstName": "firstName", "middleName": "middleName", "lastName": "lastName"},
     "email": "emailAddr",
     "affiliation": {
       "department": ["dpart1", "dpart2"],
       "laboratory": ["lab1", "lab2"],
       "institution": ["institut1", "institut2"],
       "address": "address",
       "settlement": ["settle1", "settle2"],
       "country": ["country1", "country2"]
     }
    },
    {},
    {}
  ],
  "keywords": ["keyword1", "keyword2", "keyword3"],
  "abstract": "abstract",
  "bibliography": [{
    "title": "title",
    "author": {"name": {"firstName": "firstName", "middleName": "middleName", "lastName": "lastName"}},
    "monoTitle": "monographyName",
    "date": {"year": 2017, "month": 6}
    },
    {},
    {}
  ],
  "scopus":{
    "paperScopusId": "paperScopusId",
    "publicationName": "publicationName",
    "aggregationType": "aggregationType",
    "author": [{
      "fullName": "fullName",
      "affiliation_id": "affiliation_id",
      "scopus_id": "scopus_id"
    },
    {},
    {}
    ]
  }
}
```

Ainsi, le fichier json complet ressemble à ceci:

```json
{
  "projet": "PAF2017-TelecomParistech",
  "articles": [
    {},{},{},{},{}
  ]
}
```

Par `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Mercredi 21 juin 2017

#### Partie Visualisation

J'ai continué le développement des outils de visualisation de fichiers JSON. J'ai écrit des scripts PHP permettant la production de PAA, d'autres produisant des fichiers intermédiaires avant le passage par une sorte de "filtre" PHP qui modifie les données pour ne garder que celles pertinentes. Le détail de ce travail est disponible [ici](https://github.com/baoyukun/PAF-Telecom-ParisTech/edit/master/visualisation).

Par `Nino Filiu`

#### Partie Analyse

J'ai étudié le RDF, SPARSQL et Ontology à l'aide de tutoriel et documentation de Jena.

Par `ZHU Fangda`

De mon part, j'ai essayé plein d'outils pour récupérer, nettoyer et mettre en forme des données. Voici plus d'informations sur les outils et ma remarque:

- [SoPaper, So Easy](https://github.com/ppwwyyxx/SoPaper) qui permet à partir du nom d'un article de chercher et télécharger cet article sur Internet. Pour l'instant, on n'a pas besoin de l'utiliser car on se contente tout au début de construire un graph de connaissance basé sur les [1819 articles](http://givingsense.eu/sembib/data/srcPdf/) fournis par professeur et on les a déjà téléchargés en écrivant un [code Python de quelques lignes](crawler/downloader.py).

- [Grobid](http://grobid.readthedocs.io/en/latest/) qui permet d'extraire des données d'un article de format `PDF`. Les données obtenues sont organisées sous forme `XML` bien structurée et un programme pourrais très facilement "voir" toutes les informations liées avec cet article. Grâce aux algorithmes de *Machine Learning*, le résultat est satisfaisant mais malheureusement les erreurs ne sont pas négligeables.

- [PDFMiner](https://github.com/euske/pdfminer), [PyPDF](http://pythonhosted.org/PyPDF2/), [Pdf2htmlEX
](https://github.com/coolwanglu/pdf2htmlEX), [Apache PDFBox](https://pdfbox.apache.org/) et autres logiciels qui permettent d'extraire toute information en plein texte ou bien qui transmettent fichier PDF en fichier texte. Cela est très important parce qu'on doit enfin exploiter des mots clés d'un article à partir du plein texte en utilisant `NLTK (Natural Language Processing Toolkit)`.

- [Scholar.py](https://github.com/ckreibich/scholar.py), [Arxiv-references
](https://github.com/nishimuuu/Arxiv-references), [Scopus-API
](https://github.com/scopus-api/scopus) et crawlers écrits par moi, qui essaient de chercher et de vérifier toutes les informations d'un article. A priori, `Google Scholar` est un bon endroit pour faire ça alors que malheureusement non seulement manque-il des APIs publics, mais aussi il déteste des crawlers récursifs.

Par `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Jeudi 22 juin 2017

#### Partie Visualisation

J'ai modifié les scripts existants en fonction de la nouvelle convention de fichier JSON décrite plus haut par Yukun. J'ai en outre écrit de nouveaux scripts permettant une visualisation des mots-clefs. Cela m'a pris un peu de temps étant donné qu'il fallait construire une toute nouvelle clase d'objets adapté à ce script.

Je n'ai pas encore mis à jour la partie "visualisation" du git car j'attends d'avoir des scripts fonctionnels et complets.

Par `Nino Filiu`

#### Partie Analyse

J'ai décidé de profiter des outils pour arriver à la fin comme le suivant:

1. Utiliser *Grobid* pour extraire des informations de base du format [TEI(Text Encoding Initiative)](http://www.tei-c.org/index.xml) qui est une variante de standard `XML` connu. Remarquez que le *TEI* utilisé par Grobid a été encore adapté à PDF en utilisant le langage [ODD(One Document Does it all)](http://www.tei-c.org/Guidelines/Customization/odds.xml).

2. Ecrire moi-même un parser pour analyser des documents `TEI` et mettre les éléments d'information sous format `json`. Là encore il y a deux façons pour le faire:

  - Après avoir compris le standard *TEI*, écrire une classe de parser pour chaque élément que l'on voudrait extraire, puis manipuler ces classes. On pourrait être inspiré par différents schémas comme `xsd`, `relaxNG`, `W3C XML`, etc.

  - Considérer chaque fichier du format *TEI* comme un fichier contenant simplement une *String*, puis en utilisant `Expressions Régulières` pour extraire des éléments que l'on veut. Le programme serait beaucoup plus court, mais on devrait prendre beaucoup de temps pour synthétiser l'environnement de syntaxe autour duquel l'élément se localise. Cette règle doit être universelle pour tous les cas afin de ne pas produire des bêtises.

3. Enrichir le premier json en utilisant des APIs publics de *Scopus*, des crawlers sur *Google Scholar* ou *Arxiv* et en le comparant avec la liste de publications fournie par professeur. Corriger éventuellement des erreurs dans le json, par exemple des caractères non ASCII dans le titre des articles.

4. En utilisant le toolkit `NLTK`, extraire des mots clés à partir soit des abstraits déjà acquis dans le json soit du texte complet produit par des outils comme *PyPDF*, pour les textes qui manquent des mots clés.

Par `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Vendredi 23 juin 2017

#### Partie Analyse

Dans un premier temps, on commence par le *Grobid*. J'ai utilisé la [`version 0.4.1`](https://github.com/kermitt2/grobid/archive/grobid-parent-0.4.1.zip) qui est la dernière version stable sur `Windows10 64bit`.

- J'ai essayé le mode server local (localhost:8080) tout d'abord et j'ai écrit un petit programme qui sert à faire tourner les 1819 articles automatiquement. Malheureusement, après un succès d'environ 500 articles, le serveur tombe en panne ou peut-être refuse de continuer (même le programme dort une seconde après chaque requête, même pour les petits articles dont le nom ne comporte que des caractères ASCII). Vous trouveriez aussi ce petit programme [ici](/parser/parser_pdf2tei_grobid_local_service.py).

- Puis j'ai tourné vers le mode command line (batch mode en anglais) et j'ai de la chance d'avoir toutes les réponses de 1678 articles parmi les 1819 articles avec une super performance. Les commands sous fichier Windows *bat* se trouvent [ici](/parser/parser_pdf2tei_grobid_batch_service.bat).

- Je n'ai pas essayé le mode intégré en Java. A mon avis, le résultat du mode command line serait le plus satisfaisant. Mais c'est totalement faisable et vous touveriez le [Javadoc](http://grobid.github.io/grobid-core/index.html) du projet Grobid ainsi qu'un [exemple complet](https://github.com/kermitt2/grobid-example) qui utilise des Java APIs de Grobid.

Remarquez: Avec `maven`, building est juste une commande sous `grobid/`:

```shell
mvn -DskipTests=true clean install
```  

Par `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Lundi 26 juin 2017

#### Partie Visualisation

J'ai travaillé sur le poster et j'ai mis au clair certains aspects de l'architecture et de la chaîne d'information avec mes collègues.

Jusqu'à présent, j'avais travaillé avec l'architecture suivante:

DONNEES JSON -> TRAITEMENT PHP -> AFFICHAGE

ce qui permettait d'avoir un projet qui marche avec le moins d'étapes possibles afin de garantir la réalisation du projet en cas d'échec de l'architecture suivante, décrite plus haut:

DONNEES JSON -> STOCKAGE RDF -> EXTRACTION SPARQL -> DONNEES JSON SPECIFIQUES -> TRAITEMENT PHP -> AFFICHAGE

Cette architecture est plus pertinente car RDF est plus adapté au stockage de données et SPARQL est plus adaptée à l'extraction de données provenant de gros fichiers. Je suis en ce moment même en train de développer des scripts permettant la mise en place de cette architecture.

Par `Nino Filiu`

#### Partie Analyse

Aujourd'hui, je suis assez courageux pour écrire un parser pour `TEI`. J'ai tout d'abord essayé Java bindings pour XML de version `JAXB 2.0`(Java Architecture for XML Binding 2.0) qui associe Java classes avec la représentation XML. Malheureusement, ce n'est pas tout à fait la même chose qu'on veut. Mais ça m'aide à arriver jusqu'à la fin en écrivant moins de code. Vous trouvriez l'entrée de ce projet [ici](/parser/parser_tei2json_java/src/main/Main.java) qui produit le bon résultat.

Le défaut de ce parser est qu'il est très long à écrire pour ajouter de nouveaux éléments qu'on veux extraire. Du coup, je préfère d'écrire un deuxième parser en Python en utilisant `Expressions régulières`. Le programme est beaucoup moins long et le résultat est assez satisfaisant. Vous trouveriez ce programme [ici](/parser/parser_tei2json_python.py).

Finalement le fichier json produit de 1677 articles se trouve [ici](/parser/paperJson.json).

Par `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Mardi 27 juin 2017

#### Partie Visualisation

J'ai essayé de voir si un serveur PHP pouvait processer lui-même les requêtes SPARQL sur le fichier RDF. Je ne pense pas que ça soit impossible, mais je n'ait pas réussi à le faire. Ces langages me sont inconnus et les codes proposés sur internet ne marchent pas (peut-être sont-ils dépassés? Le web design me semblait très "début d'internet").

J'ai donc fini les scripts d'hier. Ces scripts sont disponibles dans la partie visualisation. Ils "lisent" l'output JSON de SPARQL et produisent un JSON "prêt à afficher" qui sera lu et affiché par le script *display.php*.

Par `Nino Filiu`

J'ai fini le site internet et j'ai vérifié la possibilité d'intégration des scripts PHPs développés par Nino.

Par `Cherif Diallo`

#### Partie Analyse

Aujourd'hui d'une part j'ai utilisé des APIs publics de Scopus afin d'extraire l'information de `citedList`(c'est-à-dire la liste de publications qui ont cité cet article), d'autre part les APIs m'aide à corriger certaines informations puisque Scopus est une base de données assez complète.

L'informations que Scopus APIs puissent fournir sont:

- `titre`
- `DOI`
- `date de publication`
- `nombre d'être cité`
- `identité de l'article chez Scopus`
- `auteurs(nom complet, identité de l'affiliation et identité chez Scopus)`

Autres informations comme `citedList` et `bibliographie` ne sont pas directement retournées, mais j'ai essayé d'utiliser un petit crawler qui prend le lien vers cet article retourné par APIs et qui récupère la page, l'analyse en utilisant *Expressions régulières* et donne toutes les informations qu'on veut. Malheureusement, seulement 403 parmi 1677 articles ont été trouvés chez Scopus. L'information des autres articles est basée sur ce que Grobid a fourni.

J'ai aussi vérifié et complété toutes les informations en consultant la liste de publications fournie par professeur.

Le programme de cette partie se trouve [ici](/crawler/scopusCrawler.py).

Par `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Mercredi 28 juin 2017

#### Partie Visualisation

#### Partie Analyse

Aujourd'hui j'essaie de compléter le champ *keywords* pour les articles qui manquent du mot clé ou bien qui ont très peu de mots clés. Le résultat à la fin est que chaque article a au moins 5 mots clés sous la condition que son abstract ait été extrait.

La plupart des codes open-source pour extraire des mots clés à partir du texte est une implémentation d'un algorithme bien connu qui s'appelle [**Automatic Keyword Extraction from Individual Documents**](https://www.researchgate.net/publication/227988510_Automatic_Keyword_Extraction_from_Individual_Documents) par Stuart Rose, Dave Engel, Nick Cramer et Wendy Cowley. L'implémentation la plus connue qui s'appelle `Rake` réalisée en Python n'a pas utilisé le toolkit *NLTK*. Dans notre cas, j'ai implémenté cet algorithme en utilisant NLTK grâce à l'inspiration de *Vishwas B Sharma*.

Vous trouveriez le [code](/NLTK/keywordExtraction.py) ainsi que le fichier [json](/parser/paperJson.json) définitif.

Par `BAO Yukun`

[*Retour au calendrier*](#développement-du-projet)

### Jeudi 29 juin 2017

**Triomphe !!!**

:confetti_ball::balloon::sparkling_heart::satisfied::heart_eyes::stuck_out_tongue_closed_eyes:

C'est le moment de présenter deux semaines de travail intense sur le sujet et de conclure l'année en beauté.

Voici notre Website: [Analyse de publications scientifiques – Visualisation des résultats](http://paf-telecom-paristech.comli.com)

Voici notre poster:

![poster](/resource/Poster.jpg "PAF: Analyse de publications scientifiques et Visualisation des résultats")

[*Retour au calendrier*](#développement-du-projet)
