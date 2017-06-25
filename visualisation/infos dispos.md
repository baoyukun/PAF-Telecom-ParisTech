# Informations disponibles

Toutes les informations que le serveur peut obtenir ou qui sont déjà sous forme de fichier JSON et qui seront plus tard necessaires à la construction des graphs.

|**information**                              |**statut**                                             |**resultat**     |
|---------------------------------------------|-------------------------------------------------------|---------------|
|Liste des chercheurs avec leur groupe        |[query](#liste-des-chercheurs-avec-leur-groupe)        |[Resultat]("/visualisation/json/liste-des-chercheurs-avec-leur-groupe.json")
|Liste des chercheurs avec leur département   |[query](#liste-des-chercheurs-avec-leur-département)   |[Resultat]("/visualisation/json/liste-des-chercheurs-avec-leur-département.json")
|Liste des chercheurs avec les mots-clefs     |[query](#liste-des-chercheurs-avec-les-mots-clefs)     |[Resultat]("/visualisation/json/liste-des-chercheurs-avec-les-mots-clefs")
|Liste des publications avec les auteurs      |[query](#liste-des-publications-avec-les-auteurs)      |[Resultat]("/visualisation/json/liste-des-publications-avec-les-auteurs")
|Liste des publications avec le département   |[query](#liste-des-publications-avec-le-département)   |[Resultat]("/visualisation/json/liste-des-publications-avec-le-département")
|Liste des publications avec le groupe        |[query](#liste-des-publications-avec-le-groupe)        |[Resultat]("/visualisation/json/liste-des-publications-avec-le-groupe")
|Liste des publications avec les mots-clefs   |[query](#liste-des-publications-avec-les-mots-clefs)   |[Resultat]("/visualisation/json/liste-des-publications-avec-les-mots-clefs")
|Liste des publications avec la date          |[query](#liste-des-publications-avec-la-date)          |[Resultat]("/visualisation/json/liste-des-publications-avec-la-date.json")
|Liste des publications avec les auteurs cités|[query](#liste-des-publications-avec-les-auteurs-cités)|[Resultat]("/visualisation/json/liste-des-publications-avec-les-auteurs-cités.json")


Sachant que l'on utilise Jena Fuseki comme un serveur, on peut utiliser "SPARQL 1.1 Graph Store HTTP Protocolles"  pour obtenir la resultat. Le code resemble à ci-dessous

```Angular
 var req = {
        method: 'GET',
        url: http://localhost:3030/paf/query',
        headers: { 'Content-type' : 'application/x-www-form-urlencoded',
            'Accept' : 'application/sparql-results+json' },
        params: {
            query :myquery,
            format: "json"
        }
    };
  ```
  Pour différent de demande, il suffit de changer le myquery chaqut fois. 

## Liste des chercheurs avec leur groupe
Query:
```sql
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX paf: <http://givingsense.eu/sembib/data/tpt/paf2017/model#>

SELECT ?familyName ?GivenName ?group
WHERE {
  ?author paf:group ?group.
  ?author foaf:family_name ?familyName.
  ?author foaf:givenname ?GivenName
}
```
Resultat:
```JSON
{
  "head": {
    "vars": [ "familyName" , "GivenName" , "group" ]
  } ,
  "results": {
    "bindings": [
      {
        "familyName": { "type": "literal" , "value": "Duc" } ,
        "GivenName": { "type": "literal" , "value": "Guillaume" } ,
        "group": { "type": "literal" , "value": "SEN" }
      } ,
      {
        "familyName": { "type": "literal" , "value": "Polti" } ,
        "GivenName": { "type": "literal" , "value": "Alexis" } ,
        "group": { "type": "literal" , "value": "SEN" }
      } ,
      {
        "familyName": { "type": "literal" , "value": "Chaudhuri" } ,
        "GivenName": { "type": "literal" , "value": "Sumanta" } ,
        "group": { "type": "literal" , "value": "SEN" }
      }
    ]
  }
}
      
```
## Liste des chercheurs avec leur département
Query
```sql
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX paf: <http://givingsense.eu/sembib/data/tpt/paf2017/model#>

SELECT ?familyName ?GivenName ?departement
WHERE {
  ?author paf:departement ?departement.
  ?author foaf:family_name ?familyName.
  ?author foaf:givenname ?GivenName
}
```


## Liste des chercheurs avec les mots-clefs
``` sql
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX paf: <http://givingsense.eu/sembib/data/tpt/paf2017/model#>

SELECT ?familyName ?GivenName ?keyword
WHERE {
  ?article paf:wirtten_by ?author.
  ?article paf:has_key_word ?keyword.
  ?author foaf:family_name ?familyName.
  ?author foaf:givenname ?GivenName
}
```
## Liste des publications avec les auteurs
``` sql
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX paf: <http://givingsense.eu/sembib/data/tpt/paf2017/model#>

SELECT ?title ?familyName ?givenname
WHERE {
  ?article paf:wirtten_by ?author.
  ?article paf:title ?title.
  ?author foaf:family_name ?familyName.
  ?author foaf:givenname ?givenname
}
```

## Liste des publications avec le département
``` sql
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX paf: <http://givingsense.eu/sembib/data/tpt/paf2017/model#>

SELECT ?title ?departement
WHERE {
  ?article paf:title ?title.
  ?article paf:departement ?departement
}
```
## Liste des publications avec le groupe
``` sql
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX paf: <http://givingsense.eu/sembib/data/tpt/paf2017/model#>

SELECT ?title ?group
WHERE {
  ?article paf:title ?title.
  ?article paf:group ?group
}
```
## Liste des publications avec les mots-clefs
``` sql
PREFIX foaf: <http://xmlns.com/foaf/0.1/>
PREFIX paf: <http://givingsense.eu/sembib/data/tpt/paf2017/model#>

SELECT ?title ?group
WHERE {
  ?article paf:title ?title.
  ?article paf:group ?group
}
```
## Liste des publications avec la date
## Liste des publications avec les auteurs cités
    
