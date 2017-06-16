# PAF-Telecom-ParisTech
**Analyse de publications scientifiques – Visualisation des résultats**

## Encadrants

[Jean-Claude Moissinac](https://moissinac.wp.imt.fr/) (bureau: E505)

[Cyril Concolato](https://concolato.wp.imt.fr/) (bureau: E504)

## Membres

[BAO Yukun](https://github.com/baoyukun)

[FILIU Nino](https://github.com/ninofiliu)

[ZHU Fangda](https://github.com/zhufangda)

DIALLO Cherif (adresse git pas encore communiqué)

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

![demo result](/resource/image0.jpg "demo result")

## Développement du projet

### vendredi 16 juin 2017

Nous déssinons l'architecture de notre projet comme le suivant:

![architecture](/resource/architecture.png "architecture")

Nous devons extraire telles informations à partir des rapports scientifiques:

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

Nous trouvons que les recherches dans le domaine "_Citation Network_" nous intéressent le plus et qu'il y a aussi des ressources à ce sujet disponibles que l'on pourra consulter par la suite. Ci-dessous les principaux programmes et avancées qui ont été faites dans le domaine:

- [Action Science Explorer](http://www.cs.umd.edu/hcil/ase/)

ASE  est un outil permettant une compréhension rapide de publications scientifiques. Il est basé sur SocialAction et JabRef. Il permet un management des références, analyse de réseau, et production de représentations visuelle.

On peut choisir de lier les publications selon plusieurs facteurs: date de publication, nombre de citations, etc. On a un moyen d’accéder facilement au “contexte de publication” (phrases dans lesquelles les papiers sont cités).

- [Google Scholar](https://scholar.google.fr/)

GS offre une recherche avancée avec plusieurs options (recherche par mot, par titre, par expression exacte, par thème, par publication, par date, etc) et offre des résultats en fonction de la pertinence. Des informations complémentaires sur les articles sont données: quels articles l'ont cité, comment il a été cité, quels sont des articles liés. Il n’y a pas de visualisation graphique des liens entre les articles ni de quantification développée et à large échelle de ceux-ci.

- [SocialAction](http://www.cs.umd.edu/hcil/socialaction/)

SA est un logiciel qui permet une analyse de réseau et une représentation visuelle de cette analyse. C’est sur ce logiciel que tourne ASE.

- [VOSviewer](http://www.vosviewer.com/)

Programme pour construire et visualiser des données bibliométriques. Au lieu de construire le fichier de références à la main, ce programme permet d'analyser syntaxiquement automatique tout le fichier.
