# PixelTracer


PixelTracer est un éditeur vectoriel fonctionnant en **mode texte (console)**.  
Il permet de dessiner des formes géométriques  à l’aide de commandes textuelles avec des paramètres en fonction de la formes dessinés.

##  Description
PixelTracer est une application Java permettant de :

- Créer des zones de dessin (*Area*)
- Gérer des calques (*Layer*)
- Dessiner des formes vectorielles
- Afficher le rendu dans le terminal
- Manipuler dynamiquement les objets via des commandes

Le projet repose sur une architecture modulaire séparant :

- Le parsing des commandes
- La logique métier
- Le modèle de données
- Le rendu graphique

## Architecture du projet
pixeltracer
- ├── app → Application principale
- ├── command → Parsing & exécution des commandes
- ├── core → Outils utilitaires (ID, texte)
- ├── model → Modèle de données
- │     └── shapes → Implémentations des formes
- └── render → ce qui permet d'avoir l'apperçu dans la console


## Commande du projet 

###  Contrôle de l'application

- `clear` → Effacer l’écran  
- `exit` → Quitter le programme  
- `help` → Afficher l’aide  
- `plot` → Rafraîchir l’affichage  

---

###  Dessiner des formes

- `point` px py  
- `line` x1 y1 x2 y2  
- `square` x y length 
- `rectangl`e x y width height  
- `circle` x y r  
- `polygon` x1 y1 x2 y2 ...  
- `curve` x1 y1 x2 y2 x3 y3 x4 y4  

---

###  Gestion des éléments

- `list` {areas, layers, shapes}  
- `select` {area, layer, shape} {id}  
- `delete` shape {id}  
- `new` {area, layer}  

---

###  Paramètres

- `set char border ascii_code`  
- `set char background ascii_code`
