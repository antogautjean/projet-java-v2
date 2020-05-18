#Projet L3 MIAGE 

Etat d'avancement
--
Ce programme à pour objectif de permet à un utilisateur possédant des données de production d'une usine de les visualiser sous forme de tableau.
Il pourra ainsi 

Fonctionnalitée manquantes
--
* a
* b
* c

Difficultées
-- 
###Gestion de projet
* Répartition du travail
* Organisation

###Technique
* Apprentisage et compréhension de Java Swing
* Gestion des conflis GitHub 

#Dossier technique

Conception MVC
--

```
SUGGESTION APRES BUILDED
│   projet-java-v2.jar
│   settings.properties
└───data
      │  chaines.csv
      │  employes.csv
      |  prix.csv
      └─────────────── 
```

###Architechture


L'application est constituée de 3 onglets:
* Le premier:    
    * permet d'avoir une visualisation de l'état du stock et de modifier la quantité à acheter pour chaque produit.
    * présente également l'état de l'ensemble des chaines de production et permet de modifier leur niveau d'activation.
    * en bas de page on retrouve deux indicateurs qui permettent de mettre en avant le nombres de commandes satisfaites et le la valeur totale du stock.
* Le deuxieme:
    * permet une visualisation de l'emplois du temps des employés de l'usine
* Le troisième:
    * permet de fournir au programme les fichiers de données csv

