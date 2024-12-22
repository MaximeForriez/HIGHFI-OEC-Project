# Test d’une version Java du traitement des données CSV de la base O.E.C.

Ce projet est le bilan de ce qui a été produit en juin-juillet 2020. Il n'a jamais été achevé.

## Objectifs

Lors de la reprise du projet « Îles », je me suis aperçu que la version SQL de la base de données n’était pas « portative[^1]  ». En effet, chaque fois que j’essayais d’extraire la totalité de la base sous ce format à partir de MySQL, j’obtenais une base tronquée, donc inutilisable en dehors de la machine virtuelle qui analyse les données à partir d’un code PHP.

Je suis par conséquent reparti des données découpées par Mathematica en format CSV qui, elles, peuvent facilement être transportées via une clé USB, un disque externe, etc. Elles peuvent être également être installées sur un serveur. Le problème est que le format CSV n’est pas pris en charge, du moins à ma connaissance, par le PHP. Suite à de nombreux échanges avec les ingénieurs, le Java semble être le langage le mieux adapté pour interpréter les données sous leur version CSV.

De mon côté, cela présente beaucoup d’avantages.

1. Je m’initie à un des langages les plus utilisés dans le monde.

2. Contrairement à ce que j’ai longtemps pensé, Java est le code source de Mathematica. Son apprentissage est de fait rapide, car il présente beaucoup de similitudes avec le logiciel que j’utilise depuis plus de dix ans.

3. Il est totalement portatif : il peut passer d’un « format non connecté » à un « format connecté », *etc*.

4. Il me permet de progresser dans ma connaissance des codes informatiques en apprenant un langage orienté objet ; Java interdisant le langage procédural que j’utilise depuis mes 10 ans. La démarche orienté objet me permettra à terme de mieux communiquer avec les ingénieurs qui l’utilisent de manière courante. Je commence à me débrouiller, mais la démarche de recherche s’adapte mal au langage U.M.L.

## Premiers tests

Pour l’instant, j’oriente ma démarche afin de créer un exécutif, plutôt qu’une conception en ligne. C’est plus simple pour apprendre le langage et le tester.

Ci-dessous, je présente les captures écrans des fenêtres que j’ai créées. Cette présentation se concentre sur le résultat, plutôt que sur les objets. Étant donné que je suis toujours en phase d’apprentissage, le code produit et le résultat visuel se concentrent sur l’essentiel, mais cela me demande parfois plusieurs heures de code avant d’arriver à obtenir le résultat présenté.

Le point de départ est la conception d’un lecteur CSV adapté aux données CSV que je produis *via* Mathematica. Ce lecteur est un objet qui est utilisé dans presque toutes les étapes présentées ici.

## Visualisation des fenêtres créées

Le traitement des données est séquencé.

1. Une fenêtre principale avec notamment des fonctions d’enregistrement des extractions, des traitements graphiques, etc. doit être conçue. Pour l’instant, elle n’existe pas, la conception étant toujours en cours et dépendant des résultats des tests. Je demande de fait d’imaginer qu’elle existe, et qu’elle ouvre la fenêtre d’extraction de l’étape 2.

2. Choix du type d’extraction. Avec Mathematica, j’ai préparé les données à différents types d’extraction afin d’optimiser les temps de calcul. Contrairement à la base SQL, ici, je peux directement travailler sur ce résultat.

Les études pourront être explicitées dans la future « aide ».

!["Écran 1"](IMG/Ecrans-01.png)

Par défaut, rien n’est sélectionné. Une sécurité a été mise en place afin d’empêcher la validation, si l’utilisateur ne sélectionne rien.

!["Écran 2"](IMG/Ecrans-02.png)

Pour l’instant, seul « Étude territoriale » est opérationnel. Il s’agit d’étudier les flux sans tenir compte des partenaires. Pour passer à l’étape 3, on ne peut cliquer que sur ce bouton radio.

3. Choix du niveau de précision de la base de données.

!["Écran 3"](IMG/Ecrans-03.png)

L’utilisateur peut choisir sa classification et son niveau d’analyse. À ce jour, seules les classifications sont susceptibles d’être modifiées par l’ajout d’une nouvelle mise à jour. Je réfléchis à une solution pour faciliter cette dynamique dans l’ensemble du code.

4. Choix des territoires et des périodes. C’est cette fenêtre qui m’a demandé le plus de travail. Elle n’a pas l’air comme ça, mais elle traite déjà plusieurs bases de données (les bases « titres »). Un certain nombre d’éléments pour faciliter la visualisation des étapes ont été mis en œuvre comme l’affichage sous le format d’un tableur des bases « titres », éléments qui pourront être récupérés plus tard dans le traitement même des données.

La fenêtre affiche le choix du niveau de précision de la base retenue par l’utilisateur (ici HS96 et 4-digit). À ce stade, ce n’est encore qu’une information. Elle permettra à terme d’orienter le programme dans les dossiers de la base de données.

!["Écran 4"](IMG/Ecrans-04.png)

Plusieurs sécurités ont été mises en place. La première est que l’utilisateur doit valider des territoires pour pouvoir continuer.

!["Écran 5"](IMG/Ecrans-05.png)

La seconde est d’empêcher la validation d’une période pour laquelle la date de départ serait supérieure à la date d’arrivée.

!["Écran 6"](IMG/Ecrans-06.png)

Le bouton « Choix des territoires » ouvre une « fenêtre temporaire » permettant de choisir ce que l’on veut. Pour l’instant, son design est très basique. Je travaille à son amélioration.

!["Écran 7"](IMG/Ecrans-07.png)

Cette fenêtre n’a l’air de rien, mais elle m’a demandé énormément de travail. La case à cocher « Tous », par exemple, était particulièrement casse-pieds à coder. L’utilisateur peut simplement l’activer, mais il faut également prévoir sa désactivation s’il décide d’enlever l’un des territoires, et son activation s’il clique un à un sur tous les territoires disponibles. Il a également fallu installer une sécurité. Si l’utilisateur ferme la fenêtre sans rien sélectionner, il retourne sur la fenêtre mère, et, dans ce cas, l’étape suivante est toujours bloquée.

Dans cette fenêtre, j’ai choisi d’exploiter à fond les bases « titres » que j’avais créée sur Mathematica. L’utilisateur dispose d’une information quasi-complète : Nom du territoire, son code ISO, et surtout la période couverte au sein de la base de données. En effet, quelques territoires ont des données débutant plus tardivement par rapport à la date de début de la base. Il y a aussi des données qui finissent à avant la date de fin de la base. Cela permet à l’utilisateur d’anticiper les cas d’extraction nulle, et de stopper éventuellement sa démarche.

Pour présenter cet élément, le plus simple de cliquer sur « Tous » et sur le « OK » visible en utilisant les ascenseurs (qui m’ont à eux seuls demandé plusieurs journées de code).

!["Écran 8"](IMG/Ecrans-08.png)

Si on clique sur « OK », après avoir sélectionné les territoires désirés, on retourne à la fenêtre précédente, et la liste retenue s’affiche dans la zone de texte[^2], et le texte est bloqué.

!["Écran 9"](IMG/Ecrans-09.png)

Il ne reste plus qu’à choisir la période retenue. Les dates affichées dépendent du choix de la classification. Les bornes qui apparaissent lui sont de fait spécifiques. Actuellement, si l’utilisateur corrige les territoires qu’il a sélectionnés, le choix de la période est annulé, et il doit la re-sélectionner.

5. L’ensemble des données est pour l’instant récupéré dans un objet spécifique, afin de continuer la démarche d’extraction. La fenêtre suivante qui sera développée en août 2020 est celle concernant les produits échangés. À cette étape, ils dépendent de la classification et de son niveau de précision. À la différence des données précédentes, ici, la nomenclature est hiérarchique. Il faudra proposer une solution ad hoc. Le choix des produits est l’ultime étape avant la lecture de la base de données à proprement dit.

!["Écran 10"](IMG/Ecrans-10.png)

Il faut cliquer sur « Choisir les produits » pour ouvrir la fenêtre des produits à l’instar de la fenêtre des territoires. La fenêtre obtenue dépend de la classification choisie et du niveau d’analyse. Pour l’exemple, la liste obtenue avec les sections de la classification HS92 s’affiche.

!["Écran 11"](IMG/Ecrans-11.png)

Tout comme territoire, une sécurité a été mise pour obliger l’utilisateur à sélectionner des produits.

!["Écran 12"](IMG/Ecrans-12.png)

La sélection opérée, la liste s’affiche dans la zone de texte. En cliquant sur suite, il s’ouvrira la fenêtre qui exécutera la requête dans la base de données CSV.

6. La fenêtre d’extraction s’ouvre.
 
!["Écran 13"](IMG/Ecrans-13.png)

La fenêtre résume les choix de l’utilisateur. Je n’ai pas trouvé de quelle manière insérer un « tableur » au sein de la fenêtre de résultat regroupant toutes les données. De fait, j’ai installé un bouton permettant d’afficher le « tableur » afin de visualiser les données.

!["Écran 14"](IMG/Ecrans-14.png)

L’avantage de ce choix est que la fenêtre du « tableur » est indépendante de sa fenêtre source (qui reste affichée). Aucun retour n’étant attendu, on peut de fait en faire ce que l’on veut.

Il ne reste qu’à enregistrer le tableur en CSV si le résultat est bien celui escompté. Il est également possible de copier-coller le tableur entièrement sélectionné dans un fichier Excel ou Word par exemple. Idéalement, il faudrait pouvoir enregistrer les extractions directement dans le format Microsoft. Un petit message indique que l’enregistrement a été effectué, et annonce le retour sur la fenêtre permettant de choisir les études disponibles. 

!["Écran 15"](IMG/Ecrans-15.png)

Par contre, si le résultat n’est pas satisfaisant, en cliquant sur « Fermer », l’utilisateur revient à la fenêtre permettant de choisir le type d’étude. Il en est informé par un message du même type.

!["Écran 16"](IMG/Ecrans-16.png)

L’ensemble des fenêtres contient un bouton « Retour » et un bouton « Suite » qui permettent à l’utilisateur de corriger ses choix. Pour l’instant, le bouton « Fermer » ferme le programme, mais, à terme, il fermera simplement la fenêtre pour revenir sur la fenêtre principale.

## Projet à finir

Il ne reste qu’à développer les extractions des flux complets et des produits sans tenir compte de leur origine ou de leur destination.

Il faudra également envisager une extraction de la base territoriale totalement agrégée (en cours de développement au niveau de la synthèse).

La base de données des import-export est ainsi beaucoup facilement accessible. Il faudra faire la même chose avec les données de la Banque mondiale, ce qui mettra fin à la phase EXTRACTION.

Ultérieurement, mais là, sans un ingénieur qui a l’habitude de ce genre de chose, ça sera dur, il faudrait développer une partie « graphique » permettant à l’utilisateur de croiser les données qu’il a extraites des bases en faisant des graphiques, et éventuellement des cartes.

[^1] : Ce terme n’est absolument pas technique. Les informaticiens ne doivent pas le prendre au pied de la lettre.

[^2] : Il faudra que je prévoie une sécurité. L’utilisateur ne pourra pas remplir directement la zone de texte.
