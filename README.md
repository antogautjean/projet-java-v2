
# Projet L3 MIAGE

Cette application permet de visualiser les données de production d'une usine fictive.

Lancer l'application
--

Vous trouverez un fichier `gestionnaire-de-production.jar` déjà prêt à l'emploi dans le dossier `/exemple`, il vous suffit de double cliquer dessus pour l'executer

Structure du projet
--

L'application est constituée de 3 onglets:
* Le premier:    
    * permet d'avoir une visualisation de l'état du stock et de modifier la quantité à acheter pour chaque produit.
    * présente également l'état de l'ensemble des chaines de production et permet de modifier leur niveau d'activation.
    * en bas de page on retrouve deux indicateurs qui permettent de mettre en avant le nombres de commandes satisfaites et le la valeur totale du stock.
* Le deuxieme:
    * permet une visualisation de l'emplois du temps des employés de l'usine
* Le troisième:
    * permet de fournir au programme les fichiers de données csv

```
Structure du code (src)
│ AppMain    
└─── controller
|     │  Configuration
|     │  Chaine de production
|     |  Personnel
|     |  Stock
|     |  Stock virtuel
|     └─────────────── 
└─── model
|     │  Employes
|     │  Produit
|     |  Chaine de production
|     |  Unitées
|     └─────────────── 
└─── view
      │─── Composants
      |     │  employes.csv
      |     |  prix.csv
      |     └─────────────── 
      │─── Onglets
      |     │  Vue par défaut
      |     |  Vue de usine
      |     |  Vue de empoyés
      |     |  Vue de paramètres
      |     └─────────────── 
      │  Vue Globale 
      └─────────────── 
```
