# Description détaillée des objets créés pour le programme d'extraction des données O.E.C.

03-04-07/09/2020

## Objectif

Ce programme est un sous-programme d'un logiciel de traitement qui se voudra beaucoup plus large. Il tente de régler le problème de l'extraction des données import-export issue de la base de l'O.E.C. corrigée par M. Forriez. L'objectif est de pouvoir lire et enregistrer (en format *.csv pour l'instant) les données extraites de la base.

## Description des objets

### `IlesAppMain`

La classe est le point d'entrée du programme

- Variables d'instance : aucune

- Méthodes :

	- `public static void main(String[] args)`

Elle ouvre l'objet `FenetreChoixEtude`.

### `FenetreFermeture`

Pour l'instant, elle ferme le programme, mais, à terme, elle devra être reconfigurée pour juste fermer la fenêtre d'extraction. C'est un objet qui implémente l'interface `ActionListener`.

- Variable d'instance : aucune

- Méthodes :

	- `public void actionPerformed(ActionEvent e)`

### `FenetreChoixEtude`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :

	- `Container contentPane`

	- `JRadioButton boutonRadio1`

	- `JRadioButton boutonRadio2`

	- `JRadioButton boutonRadio3`

	- `ButtonGroup buttonGroup`

	- `JButton boutonOK`

	- `JButton boutonAnnuler`

- Méthodes :

	- Constructeur : `public FenetreChoixEtude()`

Il lance la méthode `init()`.

	- `private void init()`

		- Elle initialise les variables de la classe.

		- Elle appelle les méthodes `FondBoutonRadio()` et `FondBoutonsDAction()` qui gèrent la fenêtre.
		
		- Elle gère les événements des boutons radio, du bouton `OK` et du bouton `Annuler` par l'intermédiaire des classes suivantes : `ClicOK()` (interne) et `FenetreFermeture()` (externe).

			- `private JPanel fondBoutonRadio()`
		
		- Elle crée les boutons radio à afficher.

			- `private JPanel fondBoutonsDAction()`
		
		- Elle crée les boutons `OK` et `Annuler`.

- Classes internes :
	
	- `ClicOK` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `OK`. Une sécurité vérifie si l'utilisateur a sélectionné l'un des boutons radio. Si oui, le code vérifie le choix et oriente l'utilisateur soit vers l'objet `FenetreEtudeTerritoriale`, soit vers l'objet `FenetreEtudeFlux` ou soit vers l'objet `FenetreEtudeProduit`.
	
		- Variables d'instance : aucune

		- Méthodes :

			- `public void actionPerformed(ActionEvent e)`

### `FenetreEtudeTerritoriale`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :

	- `BoiteACocherClassification listeDesClassifications`

	- `BoiteACocherNiveau listeDesNiveaux`

	- `Container contentPane`

	- `JButton boutonRetour`

	- `JButton boutonSuite`

	- `JButton boutonAnnuler`

	- `JPanel panel3`

	- `String[] listeHS`

	- `String[] listeNiveau`

	- `int choixDesClassifications`

	- `int choixDesNiveaux`

- Méthodes :

	- Constructeurs :

		- `public FenetreEtudeTerritoriale()`
		
			- Ce constructeur est appelé par `FenetreChoixEtude()`.

			- Le constructeur initialise les variables de classe en lançant les méthodes suivantes :

				- `setListeHS()` : transformation de l'énumération `ListeHS` en tableau

				- `setListeNiveau()` : transformation de l'énumération `ListeNiveau` en tableau

				- `setChoixDesClassifications(0)` : initialisation du choix de la classification par défaut

				- `setChoixDesNiveaux(0)` : initialisation du choix du niveau par défaut

		- `public FenetreEtudeTerritoriale(String classification, String niveau)`

			- Ce constructeur est appelé par `FenetreEtudeTerritoriale2()`. Il récupère les données sélectionnées par l'utilisateur de la fenêtre suivante pour afficher sa sélection.
			
			- Le constructeur initialise les variables de classe en lançant les méthodes suivantes :

				- `setListeHS()` : transformation de l'énumération `ListeHS` en tableau
					
				- `setListeNiveau()` : transformation de l'énumération `ListeNiveau` en tableau
					
				- `setChoixDesClassifications(classification)` : initialisation du choix de la classification opéré par l'utilisateur
					
				- `setChoixDesNiveaux(niveau)` : initialisation du choix du niveau opéré par l'utilisateur

	- `private void init()`
		
		- Elle crée la fenêtre. La zone principale de la fenêtre est gérée par la méthode `zoneMouvante()`.
		
		- Elle gère les événements des boutons Retour, Suite et Annuler par l'intermédiaire des classes suivantes : `ClicRetour()` (interne), `ClicSuivant()` (interne) et `FenetreFermeture()` (externe).

	- `private JPanel zoneMouvante()`
		
		- Elle appelle les objets : `BoiteACocherClassification()` et `BoiteACocherNiveau()`. Ces deux objets gèrent l'affichage des listes sur l'écran.
		
		- Si un choix a déjà été opéré par l'utilisateur, elle appelle les objets : `BoiteACocherClassification(getChoixDesClassifications())` et `BoiteACocherNiveau(getChoixDesNiveaux())`

	- *getters* et *setters* :
	
		- `public int getChoixDesClassifications()`

		- `private void setChoixDesClassifications(int choixDesClassifications)`

		- `public int getChoixDesNiveaux()`

		- `private void setChoixDesNiveaux(int choixDesNiveaux)`

		- `public String[] getListeHS()`

		- `private void setListeHS()`

		- `public String[] getListeNiveau()`

		- `private void setListeNiveau()`

- Classes internes :

	- `ClicRetour` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton Retour. Elle renvoie à l'objet `FenetreChoixEtude()`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
		
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Suite`. Elle envoie les données choisies à l'objet `FenetreEtudeTerritoriale2(getChoixDesClassifications(), getChoixDesNiveaux())`
		
		- Variables d'instance : aucune
		
		- Méthodes :

			- `public void actionPerformed(ActionEvent e)`

### `FenetreEtudeTerritoriale2`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `String choixDesClassifications`

	- `String choixDesNiveaux`

	- `BoiteACocherTerritoireV2 listeDesTerritoires`

	- `BoiteACocherDateDebut listeDesDates1`

	- `BoiteACocherDateFin listeDesDates2`

	- `JButton boutonRetour`

	- `JButton boutonSuite`

	- `JButton boutonAnnuler`

	- `JButton boutonTerritoire`

	- `Container contentPane`

	- `JPanel panel3`

	- `String[] enregistrementTerritoire`

	- `String labelTerritoire`

	- `int anneeDebut`

	- `int anneeFin`

- Méthodes :

	- Constructeurs :
		
		- `public FenetreEtudeTerritoriale2(String text, String text2)`
			
			- Elle correspond au premier choix de l'utilisateur. Elle récupère les données de `FenetreEtudeTerritoriale`.
					
			- Elle initialise les variables de classe par les méthodes :
					
				- `setChoixDesClassifications(text)`
						
				- `setChoixDesNiveaux(text2)`
						
				- `init()`
						
		- `public FenetreEtudeTerritoriale2(String text, String text2, String[] text3)`
				
			- Elle correspond au premier choix de l'utilisateur et récupère les données de `FenetreBoiteACocherTerritoireV2`.

			- Elle initialise les variables de classe par les méthodes :

				- `setChoixDesClassifications(text)`
						
				- `setChoixDesNiveaux(text2)`
						
				- `setEnregistrementTerritoire(text3)`
						
				- `init()`
						
		- `public FenetreEtudeTerritoriale2(String text, String text2, int anneeDebutSelection, int anneeFinSelection)`
				
			- Elle correspond au premier choix de l'utilisateur et récupère les données de `FenetreBoiteACocherTerritoireV2` dans le cas où aucun territoire n'a été sélectionné.
					
			- Elle initialise les variables de classe par les méthodes :
					
				- `setChoixDesClassifications(text)`
						
				- `setChoixDesNiveaux(text2)`
						
				- `setAnneeDebut(anneeDebutSelection)`
					
				- `setAnneeFin(anneeFinSelection)`
						
				- `init()`
						
		- `public FenetreEtudeTerritoriale2(String text, String text2, String text3[], int anneeDebutSelection, int anneeFinSelection)`
				
			- Elle correspond aux choix de l'utilisateur en provenance de FenetreEtudeTerritoriale3, et récupère les données de `FenetreBoiteACocherTerritoireV2`.
					
			- Elle initialise les variables de classe par les méthodes :

				- `setChoixDesClassifications(text)`
						
				- `setChoixDesNiveaux(text2)`
						
				- `setEnregistrementTerritoire(text3)`
					
				- `setAnneeDebut(anneeDebutSelection)`
					
				- `setAnneeFin(anneeFinSelection)`
					
				- `init()`
					
	- `private void init()`
		
		- Elle initialise la fenêtre.
				
		- Elle initialise l'affichage de la zone de texte par la méthode `setLabelTerritoire()`.
				
		- Elle gère le contenu de la fenêtre par la méthode `zoneMouvante()`.
				
		- Elle gère les événements des boutons Retour, Suite et Annuler par l'intermédiaire des classes suivantes : `ClicRetour()` (interne), `ClicSuivant()` (interne) et `FenetreFermeture()` (externe).
				
	- `private JPanel zoneMouvante()`
		
		- Elle gère le contenu de la fenêtre *via* les méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getLabelTerritoire()`, `getAnneeDebut()` et `getAnneeFin()`.
				
		- Elle appelle les classes externes `BoiteACocherDateDebut()` et `BoiteACocherDateFin()`.
				
		- Elle gère l'événement du bouton Choisir les territoires par l'intermédiaire de la classe interne : `ClicTerritoire()`.
				
	- *getters* et *setters*
		
		- `public String getChoixDesClassifications()`
				
		- `private void setChoixDesClassifications(String nomClassification)`
				
		- `public String getChoixDesNiveaux()`
				
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
				
		- `public String[] getEnregistrementTerritoire()`
				
		- `private void setEnregistrementTerritoire(String[] enregistrementTerritoire)`
				
		- `public String getLabelTerritoire()`
				
		- `private void setLabelTerritoire()`
				
			- Elle a besoin de `getEnregistrementTerritoire()` et de `divisible(i)`.
					
		- `private boolean divisible(int test)`
				
			- Elle teste la divisibilité par 9 pour changer de ligne tous les neuf éléments dans le `JTextArea`.
					
		- `public int getAnneeDebut()`

		- `private void setAnneeDebut(int anneeDebut)`

		- `public int getAnneeFin()`

		- `private void setAnneeFin(int anneeFin)`

- Classes internes :
	
	- `ClicRetour` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Retour`. Elle renvoie à l'objet `FenetreEtudeTerritoire(getChoixDesClassifications(), getChoixDesNiveaux())`.

		- Variables d'instance : aucune

		- Méthodes :

			- `public void actionPerformed(ActionEvent e)`
					
	- `ClicSuivant` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Suite`. Elle envoie les données choisies à l'objet `FenetreEtudeTerritoriale3(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), CompareAnneeDebut.getAnneeDebut(),CompareAnneeFin.getAnneeFin())`

		- Variables d'instance : aucune

		- Méthodes :

			- `public void actionPerformed(ActionEvent e)`
				
	- `ClicTerritoire` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Choisir des territoires`. Elle envoie les données choisies à l'objet `BoiteACocherTerritoireV2(getChoixDesClassifications(), getChoixDesNiveaux())`

		- Variables d'instance : aucune

		- Méthodes :

			- `public void actionPerformed(ActionEvent e)`

### `BoiteACocherTerritoireV2`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :

	- `BaseAttributaireTerritoire` `baseAttributaireTerritoire`

	- `String choixDesClassifications`

	- `String choixDesNiveaux`

	- `String choixDesTerritoires`

	- `int longueurData`

	- `String[] titreData`

	- `String[] codeISO`

	- `String[] englishName`

	- `String[] nomFrancais`

	- `String[] nomContinent`

	- `String[] borneAnneeDebut`

	- `String[] borneAnneeFin`

	- `JCheckBox option`

	- `JCheckBox[] casesACocher`

	- `String[] liste`

	- `String[] enregistrementTerritoire`

	- `JButton boutonOK`

	- `String[] listeHS`

	- `int anneeDebut`

	- `int anneeFin`

- Méthodes :

	- Constructeurs :
	
		- `public BoiteACocherTerritoireV2(String text, String text2)`

			- Elle correspond au premier choix de l'utilisateur si les dates n'ont pas été sélectionnées.

			- Elle initialise les variables de classe par les méthodes :

				- `setChoixDesClassifications(text)`

				- `setChoixDesNiveaux(text2)`

				- `setListeHS()`

				- `donneesTerritoires()`

				- `init()`

		- `public BoiteACocherTerritoireV2(String text, String text2, int anneeDebut, int anneeFin)`

			- Elle correspond au premier choix de l'utilisateur si les dates ont été sélectionnées.

			- Elle initialise les variables de classe par les méthodes :

				- `setChoixDesClassifications(text)`

				- `setChoixDesNiveaux(text2)`

				- `setAnneeDebut(anneeDebut)`

				- `setAnneeFin(anneeFin)`

				- `setListeHS()`

				- `donneesTerritoires()`

				- `init()`

	- `private void init()`
	
		- Elle gère l'événement de fermeture de la fenêtre par la classe interne `Fermeture()`.
		
		- Elle intègre `getCasesACocher()`.
		
		- Elle gère les événements de la case à cocher Tous par la classe interne `ActionClassification()` et des autres cases à cocher par la classe interne `ActionClassification2()`.
		
		- Elle gère le bouton `OK` par la classe interne `ClicBoutonOK()`.
		
	- `private JPanel getCasesACocher()`
	
		- Elle crée la liste des cases à cocher à partir de la base de données des territoires BaseAttributaireTerritoire() récupérée par l'intermédiaire des méthodes suivantes : `getLongueurDataTerritoire()`, `getNomContinent()`, `getNomFrancais()`, `getCodeISO()`, `getBorneAnneeDebut()` et `getBorneAnneeFin()`.
		
	- `private void donneesTerritoires()`

		- Elle initialise la base de données en créant un objet `BaseAttributaireTerritoire()` qui initialise les méthodes suivantes :

			- `setBaseAttributaireTerritoire(baseAttributaireTerritoire)`
			
			- `setLongueurDataTerritoire(baseAttributaireTerritoire.getLongueurDataTerritoire())`
			
			- `setTitreData(baseAttributaireTerritoire.getTitreData())`
			
			- `setCodeISO(baseAttributaireTerritoire.getCodeISO())`
			
			- `setName(baseAttributaireTerritoire.getEnglishName())`
			
			- `setNom(baseAttributaireTerritoire.getNomFrancais())`
			
			- `setNomContinent(baseAttributaireTerritoire.getNomContinent())`
			
			- `setBorneAnneeDebut(baseAttributaireTerritoire.getBorneAnneeDebut())`
			
			- `setBorneAnneFin(baseAttributaireTerritoire.getBorneAnneeFin())`

	-	*getters* et *setters*
			
			- `public BaseAttributaireTerritoire getBaseAttributaireTerritoire()`
			
			- `private void setBaseAttributaireTerritoire(BaseAttributaireTerritoire base)`
			
			- `public int getLongueurDataTerritoire()`
			
			- `private void setLongueurDataTerritoire(int longueurData)`
			
			- `public String[] getTitreData()`
			
			- `private void setTitreData(String[] titreData)`
			
			- `public String[] getCodeISO()`
			
			- `private void setCodeISO(String[] codeISO)`
			
			- `public String[] getEnglishName()`
			
			- `private void setEnglishName(String[] englishName)`
			
			- `public String[] getNomFrancais()`
			
			- `private void setNomFrancais(String[] nomFrancais)`
			
			- `public String[] getNomContinent()`
			
			- `private void setNomContinent(String[] nomContinent)`
			
			- `public String[] getBorneAnneeDebut()`
			
			- `private void setBorneAnneeDebut(String[] borneAnneeDebut)`
			
			- `public String[] getBorneAnneeFin()`
			
			- `private void setBorneAnneeFin(String[] borneAnneeFin)`
			
			- `public String getChoixDesClassifications()`
			
			- `private void setChoixDesClassifications(String nomClassification)`
			
			- `public String getChoixDesNiveaux()`
			
			- `private void setChoixDesNiveaux(String nomDuNiveau)`
			
			- `public int getAnneeDebut()`
			
			- `private void setAnneeDebut(int anneeDebut)`
			
			- `public int getAnneeFin()`
			
			- `private void setAnneeFin(int anneeFin)`
			
			- `public String[] getListeHS()`
			
			- `private void setListeHS()`
			
			- `public String[] getEnregistrementTerritoire()`
			
			- `private void setEnregistrementTerritoire(String[] enregistrementTerritoire)`

	- `private void toutCocher()`
		
		- Elle gère l'événement de la case à cocher Tous.
		
		- Elle permet de cocher toutes les autres cases à cocher et de les griser.

	- `private void cocherOption()`
		
		- Elle coche la case à cocher Tous si toutes les cases sont cochées.
		
	- `private void enregistrementCasesCochees()`
		
			- Elle enregistre les données sélectionnées (`codeISO`) sous la forme d'un tableau de type `String` par l'appel à la méthode `setEnregistrementTerritoire(enregistrementTerritoire`).

- Classes internes :
	
	- `ActionClassification()` implémente l'interface `ItemListener`. La classe gère le clic sur la case à cocher Tous.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche `toutCocher()`.
	
	- `ActionClassification2()` implémente l'interface `ItemListener`. La classe gère les clics des cases à cocher, à l'exception de la case Tous.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche sous condition `toutCocher()` si toutes les cases sont cochées, ou `cocherOption()` si quelques cases sont cochées.
				
				- Elle déclenche `enregistrementCasesCochees()`.
	
	- `ClicBoutonOK` implémente l'interface `ActionListener`. La classe gère le retour des données vers la `FenetreEtudeTerritoriale2(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getAnneeDebut(), getAnneeFin())`.
	
	- `Fermeture` implémente `WindowListener`. La classe gère le clic sur la croix de la fenêtre `BoiteACocherTerritoire`. La classe gère le retour des données vers la `FenetreEtudeTerritoriale2(getChoixDesClassifications(), getChoixDesNiveaux(), getAnneeDebut(), getAnneeFin())`. Elle annule l'éventuelle sélection des cases à cocher.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void windowClosing(WindowEvent e)`

### `FenetreEtudeTerritoriale3`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `BoiteACocherProduits listeDesProduits`
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `String[] territoire`
	
	- `int anneeDebut`
	
	- `int anneeFin`
	
	- `String[] enregistrementProduit`
	
	- `String labelProduit`
	
	- `Container contentPane`
	
	- `JPanel panel3`
	
	- `JButton boutonRetour`
	
	- `JButton boutonSuite`
	
	- `JButton boutonAnnuler`
	
	- `JButton boutonProduit`

- Méthodes :
	
	- Constructeurs :
		
		- `public FenetreEtudeTerritoriale3(String text1, String text2, String[] text3, int anneeDebut, int anneeFin)`
			
			- Elle récupère les données de FenetreEtudeTerritoriale2.
			
			- Elle initialise les variables de classes par les méthodes suivantes :
				
				- `setChoixDesClassifications(text1)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setTerritoire(text3)`
				
				- `setAnneeDebut(anneeDebut)`
				
				- `setAnneeFin(anneeFin)`
				
				- `init()`
		
		- `public FenetreEtudeTerritoriale3(String text1, String text2, String[] text3, int anneeDebut, int anneeFin, String[] text4)`
			
			- Elle récupère les données de `BoiteACocherProduits` ou de `FenetreEtudeTerritoriale4`.
			
			- Elle initialise les variables de classes par les méthodes suivantes :
				
				- `setChoixDesClassifications(text1)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setTerritoire(text3)`
				
				- `setAnneeDebut(anneeDebut)`
				
				- `setAnneeFin(anneeFin)`
				
				- `setEnregistrementProduit(text4)`
				
				- `init()`
		
		- `private void init()`
			
			- Elle initialise la zone de texte avec `setLabelProduit()`.
			
			- Elle gère l'affiche des données par la méthode `zoneMouvante()`.
			
			- Elle gère les événements des boutons `Retour`, `Suite` et `Annuler` respectivement par les classes `ClicRetour` (interne), `clicSuivant` (interne) et `FenetreFermeture` (externe).
		
		- `private JPanel zoneMouvante()`
			
			- Elle affiche les composants de la fenêtre par les méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()` et `getLabelProduit()`.
		
		- `private void affichageDesDonnees()`
			
			- Elle affiche les données récupérées sur la console en utilisant les méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getTerritoire()`, `getAnneeDebut()` et `getAnneeFin()`.
		
		- *getters* et *setters*
			
			- `public String getChoixDesClassifications()`
			
			- `private void setChoixDesClassifications(String nomClassification)`
			
			- `public String getChoixDesNiveaux()`
			
			- `private void setChoixDesNiveaux(String nomDuNiveau)`
			
			- `public String[] getTerritoire()`
			
			- `private void setTerritoire(String[] territoire)`
			
			- `public int getAnneeDebut()`
			
			- `private void setAnneeDebut(int anneeDebut)`
			
			- `public int getAnneeFin()`
			
			- `private void setAnneeFin(int anneeFin)`
			
			- `public String[] getEnregistrementProduit()`
			
			- `private void setEnregistrementProduit(String[] enregistrementProduit)`
			
			- `public String getLabelProduit()`
			
			- `private void setLabelProduit()`
				
				- Elle a besoin des methodes `getEnregistrementProduit()` et `divisible(i)`
			
			- `private boolean divisible(int test)`
				
				- Elle teste la divisibilité par 9 pour changer de ligne tous les neuf éléments dans le `JTextArea`.

- Classes internes :
	
	- `ClicRetour` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Retour`. Elle renvoie à l'objet `FenetreEtudeTerritoire2(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getAnneeDebut(), getAnneeFin())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Suite`. Elle envoie les données choisies à l'objet `FenetreEtudeTerritoriale4(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getAnneeDebut(), getAnneeFin(), getEnregistrementProduit())`
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicProduit` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Choisir des territoires`. Elle envoie les données choisies à l'objet `BoiteACocherProduits(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire, getAnneeDebut(), getAnneeFin())`
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`

### `BoiteACocherProduits`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `BaseAttributaireProduit baseAttributaireProduit`
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `String[] territoire`
	
	- `int anneeDebut`
	
	- `int anneeFin`
	
	- `String[] listeHS`
	
	- `String[] nomHS`
	
	- `String[] listeNiveau`
	
	- `String[] nomNiveau`
	
	- `int[] niveau`
	
	- `int choixDesNiveauxInt`
	
	- `String[] codeSection`
	
	- `String[] code2Digit`
	
	- `String[] code4Digit`
	
	- `int[] coupureTab`
	
	- `int[] coupureTab2`
	
	- `String[] code6Digit`
	
	- `int LongueurData`
	
	- `String[] titreData`
	
	- `String[] codeSectionReference`
	
	- `String[] nameSectionReference`
	
	- `String[] nomSectionReference`
	
	- `String[] code2DigitRefrence`
	
	- `String[] name2DigitReference`
	
	- `String[] nom2DigitReference`
	
	- `String[] code4DigitReference`
	
	- `String[] name4DigitReference`
	
	- `String[] nom4DigitReference`
	
	- `String[] code6DigitReference`
	
	- `String[] name6DigitReference`
	
	- `String[] nom6DigitReference`
	
	- `JCheckBox option`
	
	- `JCheckBox[] casesACocher`
	
	- `String[] liste`
	
	- `JButton boutonOK`
	
	- `String[] enregistrementProduit`

- Méthodes :
	
	- Constructeur : `public BoiteACocherProduits(String text, String text2, String[] text3, int anneeDebut, int anneeFin)`
		
		- Elle initialise les variables d'instance par les méthodes suivantes :
			
			- `setChoixDesClassifications(text)`
			
			- `setChoixDesNiveaux(text2)`
			
			- `setTerritoire(text3)`
			
			- `setAnneeDebut(anneeDebut)`
			
			- `setAnneeFin(anneeFin)`
			
			- `setListeHS()`
			
			- `setNomHS()`
			
			- `setListeNiveau()`
			
			- `setNomNiveau()`
			
			- `setNiveau()`
			
			- `donneesProduits()`
			
			- `init()`
		
		- Elle gère la fenêtre. Par la classe interne `Fermeture`, elle précise l'action lors de la fermeture de la fenêtre par la croix.
		
		- Elle gère les événements associés à la case à cocher Tous par la classe interne `ActionClassification()` et aux autres cases par la classe interne `ActionClassification2()`.
	
	- `private void init()`
		
		- Elle utilise la méthode `getChoixDesNiveaux()` afin d'obtenir la bonne liste à cocher à sélectionner parmi : `getCasesACocherSection()`, `getCasesACocher2Digit()`, `getCasesACocher4Digit()` et `getCasesACocher6Digit()`.
		
		- Elle gère l'événement du bouton `OK` par l'intermédiaire de la classe interne `ClicBoutonOK`.
	
	- Les cases à cocher affichées dépendent du choix de la classification et du niveau.
		
		- `private JPanel getCasesACocherSection()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()` et `getCodeSectionReference()` pour lister les cases à cocher à afficher.
		
		- `private JPanel getCasesACocher2Digit()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()`, `getCodeSectionReference()`, `getNom2DigitReference()` et `getCode2DigitReference()` pour lister les cases à cocher à afficher.
		
		- `private JPanel getCasesACocher4Digit()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()`, `getCodeSectionReference()`, `getNom2DigitReference()`, `getCode2DigitReference()`, `getNom4DigitReference()` et `getCode4DigitReference()` pour lister les cases à cocher à afficher.
		
		- `private JPanel getCasesACocher6Digit()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()`, `getCodeSectionReference()`, `getNom2DigitReference()`, `getCode2DigitReference()`, `getNom4DigitReference()`, `getCode4DigitReference()`, `getNom6DigitReference()` et `getCode6DigitReference()` pour lister les cases à cocher à afficher.
	
	- `private void donneesProduits()`
		
		- Elle crée un objet `BaseAttributaireProduit(getChoixDesClassifications(), getChoixDesNiveaux())`. Elle charge le tableau complet de la base de données correspondant à la classification retenue qu'il faut filtrer pour obtenir l'affichage souhaité en fonction du niveau retenu.
		
		- En utilisant les choix de l'utilisateur, les méthodes suivantes ont pour objectif de charger la base de données dans les variables de classe :
			
			- `setBaseAttributaireProduit(baseAttributaireProduit.getChoixDesNiveauxInt())`
			
			- `setLongueurDataProduit(baseAttributaireProduit.getLongueurDataProduit())`
			
			- `setTitreData(baseAttributaireProduit.getTitreData())`
			
			- `setCodeSectionReference(baseAttributaireProduit.getCodeSectionReference())`
			
			- `setNameSectionReference(baseAttributaireProduit.getNameSectionReference())`
			
			- `setNomSectionReference(baseAttributaireProduit.getNomSectionReference())`
			
			- `setCode2DigitReference(baseAttributaireProduit.getCode2DigitReference())`
			
			- `setName2DigitReference(baseAttributaireProduit.getName2DigiReference())`
			
			- `setNom2DigitReference(baseAttributaireProduit.getNom2DigiReference())`
			
			- `setCode4DigitReference(baseAttributaireProduit.getCode4DigitReference())`
			
			- `setName4DigitReference(baseAttributaireProduit.getName4DigitReference())`
			
			- `setNom4DigitReference(baseAttributaireProduit.getNom4DigitReference())`
			
			- `setCode6DigitReference(baseAttributaireProduit.getCode6DigitReference())`
			
			- `setName6DigitReference(baseAttributaireProduit.getName6DigitReference())`
			
			- `setNom6DigitReference(baseAttributaireProduit.getNom6DigitReference())`
			
			- `setCodeSection(baseAttributaireProduit.getCodeSection())`
			
			- `setCode2Digit(baseAttributaireProduit.getCode2Digit())`
			
			- `setCode4Digit(baseAttributaireProduit.getCode4Digit())`
			
			- `setCode6Digit(baseAttributaireProduit.getCode6Digit())`
	
	- *getters* et *setters*
		
		- `public BaseAttributaireProduit getBaseAttributaireProduit()`
		
		- `private void setBaseAttributaireProduit(BaseAttributaireProduit base)`
		
		- `public int getLongueurDataProduit()`
		
		- `private void setLongueurDataProduit(int longueurData)`
		
		- `public String[] getTitreData()`
		
		- `private void setTitreData(String[] titreData)`
		
		- `public String[] getCodeSectionReference()`
		
		- `private void setCodeSectionReference(String[] codeSectionReference)`
		
		- `public String[] getNameSectionReference()`
		
		- `private void setNameSectionReference(String[] nameSectionReference)`
		
		- `public String[] getNomSectionReference()`
		
		- `private void setNomSectionReference(String[] nomSectionReference)`
		
		- `public String[] getCode2DigitReference()`
		
		- `private void setCode2DigitRefrence(String[] code2DigitReference)`
		
		- `public String[] getName2DigitReference()`
		
		- `private void setName2DigitReference(String[] name2DigitReference)`
		
		- `public String[] getNom2DigitReference()`
		
		- `private void setNom2DigitReference(String[] nom2DigitReference)`
		
		- `public String[] getCode4DigitRefrence()`
		
		- `private void setCode4DigitReference(String[] code4DigitReference)`
		
		- `public String[] getName4DigitReference()`
		
		- `private void setName4DigitReference(String[] name4DigitReference)`
		
		- `public String[] getNom4DigitReference()`
		
		- `private void setNom4DigitReference(String[] nom4DigitReference)`
		
		- `public String[] getCode6DigitReference()`
		
		- `private void setCode6DigitReference(String[] code6DigitReference)`
		
		- `public String[] getName6DigitReference()`
		
		- `private void setName6DigitReference(String[] name6DigitReference)`
		
		- `public String[] getNom6DigitReference()`
		
		- `private void setNom6DigitReference(String[] nom6DigitReference)`
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public int getChoixDesNiveauxInt()`
		
		- `private void setChoixDesNiveauxInt(int nomDuNiveau)`
		
		- `public String[] getTerritoire()`
		
		- `private void setTerritoire(String[] territoire)`
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`
		
		- `public String[] getCodeSection()`
		
		- `private void setCodeSection(String codeSection)`
		
		- `public String[] getCode2Digit()`
		
		- `private void setCode2Digit(String[] code2Digit)`
		
		- `public String[] getCode4Digit()`
		
		- `private void setCode4Digit(String[] code4Digit)`
		
		- `public String[] getCode6Digit()`
		
		- `private void setCode6Digit(String[] code6Digit)`
		
		- `public String[] getEnregistrementProduit()`
		
		- `private void setEnregistrementProduit(String[] enregistrementProduit)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`
		
		- `public String[] getNomNiveau()`
		
		- `private void setNomNiveau()`
		
		- `public int[] getNiveau()`
		
		- `private void setNiveau()`
		
		- `private void toutCocher()`
		
		- `private void cocherOption()`
		
		- `private void enregistrementCasesCocheesSection()`
		
		- `private void enregistrementCasesCochees2Digit()`
		
		- `private void enregistrementCasesCochees4Digit()`
		
		- `private void enregistrementCasesCochees6Digit()`

- Classes internes :
	
	- `ActionClassification()` implémente l'interface `ItemListener`. La classe gère le clic sur la case à cocher Tous.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche `toutCocher()`.
	
	- `ActionClassification2()` implémente l'interface ItemListener. La classe gère les clics des cases à cocher, à l'exception de la case Tous.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche sous condition `toutCocher()` si toutes les cases sont cochées, ou `cocherOption()` si quelques cases sont cochées.
				
				- En fonction de `getChoixDesNiveauxInt()`, elle déclenche `enregistrementCasesCocheesSection()`, `enregistrementCasesCochees2Digit()`, `enregistrementCasesCochees4Digit()` et `enregistrementCasesCochees6Digit()`.
	
	- `ClicBoutonOK` implémente l'interface `ActionListener`. La classe gère le retour des données vers la `FenetreEtudeTerritoriale3(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getAnneeDebut(), getAnneeFin(), getEnregistrementProduit())`.
	
	- `Fermeture` implémente `WindowListener`. La classe gère le clic sur la croix de la fenêtre `BoiteACocherTerritoire`. La classe gère le retour des données vers la `FenetreEtudeTerritoriale3(getChoixDesClassifications(), getChoixDesNiveaux(), getAnneeDebut(), getAnneeFin())`. Elle annule l'éventuelle sélection des cases à cocher.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void windowClosing(WindowEvent e)`

### `FenetreEtudeTerritoriale4`
	
C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `int choixDesNiveauxInt`
	
	- `String[] territoire`
	
	- `int anneeDebut`
	
	- `int anneeFin`
	
	- `int[] annee`
	
	- `String[] codeProduit`
	
	- `String[] listeHS`
	
	- `String[] nomHS`
	
	- `String[] nomDossierHS`
	
	- `String[] listeNiveau`
	
	- `String[] nomNiveau`
	
	- `int[] niveau`
	
	- `String[] sousDossierPartie1`
	
	- `String[] sousDossierPartie2`
	
	- `String dossier`
	
	- `String sousDossierP1`
	
	- `String sousDossierP2`
	
	- `String sousDossierP3`
	
	- `String sousSousDossier = "Bd-Territoire\\"`
	
	- `String sousSousSousDossier = "Import-Export-Annee-Territoire-Detail-2\\"`
	
	- `BaseAttributaireTerritoire baseAttributaireTerritoire`
	
	- `int baseAttributaireTerritoireLongueurData`
	
	- `String[] baseAttributaireTerritoireTitreData`
	
	- `String[] baseAttributaireTerritoireCodeISO`
	
	- `String[] baseAttributaireTerritoireEnglishName`
	
	- `String[] baseAttributaireTerritoireNomFrancais`
	
	- `String[] baseAttributaireTerritoireNomContinent`
	
	- `String[] baseAttributaireTerritoireBorneAnneeDebut`
	
	- `String[] baseAttributaireTerritoireBorneAnneeFin`
	
	- `String[] englishNameTerritoire`
	
	- `String[] nomFrancaisTerritoire`
	
	- `String[] nomContinent`
	
	- `String[] borneAnneeDebutTerritoire`
	
	- `String[] borneAnneeFinTerritoire`
	
	- `BaseAttributaireProduit baseAttributaireProduit`
	
	- `String[] baseAttributaireProduitCodeProduit`
	
	- `String[] baseAttributaireProduitNomProduit`
	
	- `String[] baseAttributaireProduitNameProduct`
	
	- `String labelTerritoire`
	
	- `String labelProduit`
	
	- `Container contentPane`
	
	- `JPanel panel3`
	
	- `JButton boutonRetour`
	
	- `JButton boutonSuite`
	
	- `JButton boutonAnnuler`
	
	- `JButton boutonAffichageDonnees`
	
	- `String[] nomFichierALire`
	
	- `String titreExtraction`
	
	- `String donneesExtraction`
	
	- `String[] titreExtractionTableau`
	
	- `String[][] donneesExtractionTableau`

- Méthodes :
	
	- Constructeur : `FenetreEtudeTerritoriale4(String text1, String text2, String[] text3, int anneeDebut, int anneeFin, String[] text4)`
		
		- Elle permet d'initialiser les variables d'instance permettant d'initialiser les autres variables d'instance par les méthodes :
			
			- `setChoixDesClassifications(text1)`
			
			- `setChoixDesNiveaux(text2)`
			
			- `setTerritoire(text3)`
			
			- `setAnneeDebut(anneeDebut)`
			
			- `setAnneeFin(anneeFin)`
			
			- `setProduit(text4)`
			
			- `init()`
		
		- Elle contrôle les étapes de la lecture des fichiers de la base de données retenue par l'utilisateur par les méthodes :
			
			- `chargementFichierALire()`
			
			- `lectureBaseDeDonnees()`
		
		- Elle récupère les informations choisies par l'utilisateur grâce aux méthodes :
			
			- `setLabelTerritoire()`
			
			- `setLabelProduit()`
		
		- Elle gère l'affichage de la fenêtre avec la méthode `zoneMouvante()`.
		
		- Elle gère les événements des boutons `Retour`, `Enregistrement` et `Annuler` par l'intermédiaire des classes suivantes respectives : `ClicRetour` (interne), `ClicSuivant` (interne) et `ClicAnnuler` (externe).
	
	- `private JPanel zoneMouvante()`
		
		- Elle gère l'affichage des données complètes de l'extraction en utilisant les méthodes : `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getLabelTerritoire()` et `getLabelProduit()`.
		
		- Elle gère l'événement du bouton `Afficher les données` par l'appel à la classe interne `BoutonAffichageDonnees()`.
	
	- `private void tableauExtrait()`
		
		- Elle affiche les données sous la forme d'un `JTable`.
		
		- Elle utilise les méthodes : `getDonneesExtraction()` et `getTitreExtraction()`.
	
	- `public void getLabelTerritoire()`
		
		- Elle gère l'affiche du `JTextArea` affichant les choix de l'utilisateur concernant les territoires.
	
	- `private void setLabelTerritoire()`
		
		- Elle crée le tableau complet des données territoriales choisies par l'utilisateur en utilisant les méthodes : `getTerritoire()`, `getBaseAttributaireTerritoireCodeISO()` et `getBaseAttributaireTerritoireNomFrancais()`.
	
	- `public getLabelProduit()`
		
		- Elle gère l'affiche du JTextArea affichant les choix de l'utilisateur concernant les produits.
	
	- `private void setLabelProduit()`
		
		- Elle crée le tableau complet des données concernant les produits choisies par l'utilisateur en utilisant les méthodes : `getProduit()`, `getBaseAttributaireProduitCodeProduit()` et `getBaseAttributaireNomProduit()`.
	
	- `private void init()`
		
		- Elle initialise les variables d'instance par les méthodes : `donneesTerritoires()`, `setListeHS()`, `setNomHS()`, `setNomDossierHS()`, `setListeNiveau()`, `setSousDossierPartie1()`, `setSousDossierPartie2()`, `setDossier(dossier)`, `setSousDossier1(sousDossier1)`, `setSousDossier2(sousDossier2)`, `setSousDossier3(sousDossier3)`, `setChoixDesNiveauxInt(choixDesNiveauxInt)`, `setAnnee()`, `setEnglishNameTerritoire(englishNameTerritoire)`, `setNomFrancaisTerritoire(nomFrancaisTerritoire)`, `setNomContinent(nomContinent)`, `setAnneeDebutTerritoire(anneeDebutTerritoire)` et `setAnneeFin(anneeFinTerritoire)`. Pour ce, l'ordre des initialisations est très important, puisque les méthodes utilisent les variables enregistrées via `getListeHS()`, `getNomHS()`, `getNomDossier()`, `getListeNiveau()`, `getNiveau()`, `getSousDossierPartie1()`, `getSousDossierPartie2()`, `getAnneeDebut()`, `getAnneeFin()`, `getTerritoire()`, `getBaseAttributaireTerritoireCodeISO()`, `getBaseAttributaireTerritoireEnglishName()`, `getBaseAttributaireTerritoireNomFrancais()`, `getBaseAttributaireTerritoireNomContinent()`, `getBaseAttributaireTerritoireBorneAnneeDebut()`, `getBaseAttributaireTerritoireAnneeFin()`.
	
	- `private void donneesTerritoires()`
		
		- En appelant l'objet `BaseAttributaireTerritoire`, elle charge la base de données des territoires *via* les méthodes suivantes :
			
			- `setBaseAttributaireTerritoire(…)`
			
			- `setBaseAttributaireTerritoireLongueurData(…)`
			
			- `setBaseAttributaireTerritoireTitreData(…)`
			
			- `setBaseAttributaireTerritoireCodeISO(…)`
			
			- `setBaseAttributaireTerritoireEnglishName(…)`
			
			- `setBaseAttributaireTerritoireNomFrancais(…)`
			
			- `setBaseAttributaireTerritoireNomContinent(…)`
			
			- `setBaseAttributaireTerritoireBorneAnneeDebut(…)`
			
			- `setBaseAttributaireTerritoireBorneAnneeFin(…)`
	
	- `private void donneesProduits()`
		
		- En appelant l'objet `BaseAttributaireProduit`, elle charge la base de données des territoires *via* la méthode `setBaseAttributaireProduit(…)`.
		
		- En utilisant `getChoixDesNiveauxInt()`, les méthodes `setBaseAttributaireProduitCodeProduit(…)` et `setBaseAttributaireProduitNomProduit(…)` chargent les données choisies par l'utilisateur.
	
	- `private void chargementFichierALire()`
		
		- Elle initialise la liste des fichiers à lire en fonction des choix de l'utilisateur par l'intermédiaire des méthodes : `getDossier()`, `getSousDossierP1()`, `getSousDossier2()`, `getSousDossier3()`, `getAnnee()`, `getTerritoire()`, `getAnneeDebutTerritoire()`, `getAnneeFinTerritoire()` et les variables de classe : `sousSousDossier` et `sousSousDossier`, la méthode `setNomFichierALire(nomFichierALire)`.
	
	- `private void lectureBaseDeDonnees()`
		
		- Grâce à la méthode `getNomFichierALire()`, on peut initialiser un objet `LectureCSV`. On utilise ses méthodes `getTitreTableau()`, `getDonneesTableau()` et `getNombreDeColonne()` pour initialiser la lecture.
		
		- Le code insère la colonne `Nom du territoire` avec les noms complets des territoires et ajoute dans la colonne `Nom des produits` les noms complets.
		
		- Elle permet d'initialiser les méthodes `setTitrreExtraction(inittitre)`, `setTitreExtractionTableau(titre2)`, `setDonneesExtraction(initSortie)` et `setDonneesExtractionTableau(tabExtraction, numColonne + 1)` en utilisant `getBaseAttributaireTerritoireCodeISO()`, `getBaseAttributaireTerritoireNomFrancais()`, `getProduit()`, `getBaseAttributaireProduitCodeProduit()` et `getBaseAttributaireProduitNomProduit()`.
	
	- `private void affichageDonneesRecuperees()`
		
		- Elle permet de lire les choix de l'utilisateur sur la console grâce aux méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getChoixDesNiveauxInt()`, `getTerritoire()`, `getAnneeDebut()`, `getAnneeFin()`, `getAnnee()` et `getProduit()`. (point de contrôle pour le programmeur)
	
	- `private void affichageDonneesAttributaires()`
		
		- Elle permet d'afficher les données attributaires sélectionnées par l'utilisateur *via* `getTerritoire()`, `getNomFrancaisTerritoire()`, `getAnneeDebutTerritoire()` et `getAnneeFinTerritoire()`. (point de contrôle pour le programmeur)
	
	- `private void affichageFichiersALire()`
		
		- Elle permet d'afficher sur la console la liste des fichiers à lire pour l'extraction des données choisies par l'utilisateur. (point de contrôle pour le programmeur)
	
	- *getters* et *setters* :
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public String[] getTerritoire()`
		
		- `private void setTerritoire(String[] territoire)`
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`
		
		- `public int[] getAnnee()`
		
		- `private void setAnnee(int[] annee)`
		
		- `public String[] getProduit()`
		
		- `private void setProduit(String[] produit)`
		
		- `public int getChoixDesNiveauxInt()`
		
		- `private void setChoixDesNiveauxInt(int nomDuNiveau)`
		
		- `public String getDossier()`
		
		- `private void setDossier(String dossier)`
		
		- `public String getSousDossierP1()`
		
		- `private void setSousDossierP1(String sousDossierP1)`
		
		- `public String getSousDossierP2()`
		
		- `private void setSousDossierP2(String sousDossierP2)`
		
		- `public String getSousDossierP3()`
		
		- `private void setSousDossierP3(String sousDossierP3)`
		
		- `public BaseAttributaireTerritoire getBaseAttributaireTerritoire()`
		
		- `private void setBaseAttributaireTerritoire(BaseAttributaireTerritoire base)`
		
		- `public int getBaseAttributaireTerritoireLongueurData()`
		
		- `private void setBaseAttributaireTerritoireLongueurData(int longueurData)`
		
		- `public String[] getBaseAttributaireTerritoireTitreData()`
		
		- `private void setBaseAttributaireTerritoireTitreData(String[] titreData)`
		
		- `public String[] getBaseAttributaireTerritoireCodeISO()`
		
		- `private void setBaseAttributaireTerritoireCodeISO(String[] codeISO)`
		
		- `public String[] getBaseAttributaireTerritoireEnglishName()`
		
		- `private void setBaseAttributaireTerritoireEnglishName(String[] englishName)`
		
		- `public String[] getBaseAttributaireTerritoireNomFrancais()`
		
		- `private void setBaseAttributaireTerritoireNomFrancais(String[] nomFrancais)`
		
		- `public String[] getBaseAttributaireTerritoireNomContinent()`
		
		- `private void setBaseAttributaireTerritoireNomContinent(String[] nomContinent)`
		
		- `public String[] getBaseAttributaireTerritoireBorneAnneeDebut()`
		
		- `private void setBaseAttributaireTerritoireBorneAnneeDebut(String[] borneAnneeDebut)`
		
		- `public String[] getBaseAttributaireTerritoireBorneAnneeFin()`
		
		- `private void setBaseAttributaireTerritoireBorneAnneeFin(String[] borneAnneeFin)`
		
		- `public BaseAttributaireProduit getBaseAttributaireProduit()`
		
		- `private void setBaseAttributaireProduit(BaseAttributaireProduit baseProduit)`
		
		- `public String[] getBaseAttributaireProduitCodeProduit()`
		
		- `private void setBaseAttributaireProduitCodeProduit(String[] code)`
		
		- `public String[] getBaseAttributaireProduitNomProduit()`
		
		- `private void setBaseAttributaireProduitNomProduit(String[] nomProduit)`
		
		- `public String[] getBaseAttributaireProductNameProduct()`
		
		- `private void setBaseAttributaireProduitNameProduct(String[] nameProduct)`
		
		- `public String[] getEnglishNameTerritoire()`
		
		- `private void setEnglishNameTerritoire(String[] englishNameTerritoire)`
		
		- `public String[] getNomFrancaisTerritoire()`
		
		- `private void setNomFrancaisTerritoire(String[] nomFrancaisTerritoire)`
		
		- `public String[] getNomContinent()`
		
		- `private void setNomContinent(String[] nomContinent)`
		
		- `public String[] getAnneeDebutTerritoire()`
		
		- `private void setAnneeDebutTerritoire(String[] anneeDebutTerritoire)`
		
		- `public String[] getAnneeFinTerritoire()`
		
		- `private void setAnneeFinTerritoire(String[] anneeFinTerritoire)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getNomHS()`
		
		- `private void setNomHS()`
		
		- `public String[] getNomDossierHS()`
		
		- `private void setNomDossierHS()`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`
		
		- `public int[] getNiveau()`
		
		- `private void setNiveau()`
		
		- `public String[] getSousDossierPartie1()`
		
		- `private void setSousDossierPartie1()`
		
		- `public String[] getSousDossierPartie2()`
		
		- `private void setSousDossierPartie2()`
		
		- `public String[] getSousDossierPartie3()`
		
		- `private void setSousDossierPartie3()`
		
		- `public String[] getNomFichierALire()`
		
		- `private void setNomFichierALire(String[] table)`
		
		- `public String getTitreExtraction()`
		
		- `private void setTitreExtraction(String table)`
		
		- `public String getDonneesExtraction()`
		
		- `private void setDonneesExtraction(String table)`
		
		- `public String[] getTitreExtractionTableau()`
		
		- `private void setTitreExtractionTableau(String[] table)`
		
		- `public String[] getDonneesExtractionTableau()`
		
		- `private void setDonneesExtractionTableau(String[] tabExtraction, int numColonne)`

- Classes internes :
	
	- `ClicRetour` implémente `ActionListener`. La classe gère le retour vers `FenetreEtudeTerritoriale3(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getAnneeDebut(), getAnneeFin(), getProduit())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implémente `ActionListener`. La classe enregistre les données choisies par l'utilisateur sous la forme d'un fichier `*.csv` en appelant la méthode `ecrire()` qui a besoin de `getChoixDesClassifications()` et `getChoixDesNiveaux()`. Elle renvoie vers l'objet `FenetreChoixEtude()`.
	
	- `private void ecrire()`
		
		- Elle enregistre l'extraction sous la forme d'un nom unique en utilisant `getDossier()` et un objet `DateMaintenant` qui capture la date au moment du clic sur le bouton de sauvegarde grâce à sa méthode `getDateMaintenant()` qui renvoie une chaîne de caractères.
		
		- Le nom choisi, elle a besoin des méthodes `getTitreExtractionTableau()` et `getDonneesExtractionTableau()` pour enregistrer l'extraction voulue par l'utilisateur.
	
	- `private void messageDeFermeture()`
		
		- Elle ouvre une nouvelle fenêtre en fonction du choix de l'utilisateur : s'il confirme l'annulation, l'objet `FenetreChoixEtude()` est appelé ; s'il ne souhaite plus annuler, on reste sur l'objet `FenetreEtudeTerritoire4`.
	
	- `ClicAnnuler` implémente `ActionListener`. La classe renvoie à la méthode `messageDeFermeture()`.
		
		- `public void actionPerformed(ActionEvent e)`
	
	- `BoutonAffichageDonnees` implémente `ActionListener`. Elle permet d'afficher le `JTable` en appelant la méthode `tableauExtrait()`.
		
		- `public void actionPerformed(ActionEvent e)`
	
	- `Fermeture` implémente `WindowListener`. La classe gère le clic sur la croix de la fenêtre `FenetreEtudeTerritoriale4`. La classe ouvre une boîte de dialogue de confirmation ou annulation *via* la méthode `messageDeFermeture()`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void windowClosing(WindowEvent e)`

### `FenetreEtudeFlux`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `BoiteACocherClassification listeDesClassifications`
	
	- `BoiteACocherNiveau listeDesNiveaux`
	
	- `Container contentPane`
	
	- `JButton boutonRetour`
	
	- `JButton boutonSuite`
	
	- `JButton boutonAnnuler`
	
	- `JPanel panel3`
	
	- `String[] listeHS`
	
	- `String[] listeNiveau`
	
	- `int choixDesClassifications`
	
	- `int choixDesNiveaux`

- Méthodes :
	
	- Constructeurs :
		
		- `public FenetreEtudeFlux()`
			
			- Ce constructeur est appelé par `FenetreChoixEtude()`.
			
			- Le constructeur initialise les variables de classe en lançant les méthodes suivantes :
				
				- `setListeHS()` : transformation de l'énumération ListeHS en tableau
				
				- `setListeNiveau()` : transformation de l'énumération ListeNiveau en tableau
				
				- `setChoixDesClassifications(0)` : initialisation du choix de la classification par défaut
				
				- `setChoixDesNiveaux(0)` : initialisation du choix du niveau par défaut
		
		- `public FenetreEtudeFlux(String classification, String niveau)`
			
			- Ce constructeur est appelé par `FenetreEtudeFlux2()`. Il récupère les données sélectionnées par l'utilisateur de la fenêtre suivante pour afficher sa sélection.
			
			- Le constructeur initialise les variables de classe en lançant les méthodes suivantes :
				
				- `setListeHS()` : transformation de l'énumération ListeHS en tableau
				
				- `setListeNiveau()` : transformation de l'énumération ListeNiveau en tableau
				
				- `setChoixDesClassifications(classification)` : initialisation du choix de la classification opéré par l'utilisateur
				
				- `setChoixDesNiveaux(niveau)` : initialisation du choix du niveau opéré par l'utilisateur
	
	- `private void init()`
		
		- Elle crée la fenêtre. La zone principale de la fenêtre est gérée par la méthode `zoneMouvante()`.
		
		- Elle gère les événements des boutons `Retour`, `Suite` et `Annuler` par l'intermédiaire des classes suivantes : `ClicRetour()` (interne), `ClicSuivant()` (interne) et `FenetreFermeture()` (externe).
	
	- `private JPanel zoneMouvante()`
		
		- Elle appelle les objets : `BoiteACocherClassification()` et `BoiteACocherNiveau()`. Ces deux objets gèrent l'affichage des listes sur l'écran.
		
		- Si un choix a déjà été opéré par l'utilisateur, elle appelle les objets : `BoiteACocherClassification(getChoixDesClassifications())` et `BoiteACocherNiveau(getChoixDesNiveaux())`
	
	- *getters* et *setters* :
		
		- `public int getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(int choixDesClassifications)`
		
		- `public int getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(int choixDesNiveaux)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`

- Classes internes :
	
	- `ClicRetour` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton Retour. Elle renvoie à l'objet `FenetreChoixEtude()`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Suite`. Elle envoie les données choisies à l'objet `FenetreEtudeFlux2(getChoixDesClassifications(), getChoixDesNiveaux())`
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`

### `FenetreEtudeFlux2`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `BoiteACocherTerritoireFlux1 listeDesTerritoires`
	
	- `BoiteACocherTerritoireFlux1 listeDesTerritoires2`
	
	- `BoiteACocherDateDebut listeDesDates1`
	
	- `BoiteACocherDateFin listeDesDates2`
	
	- `JButton boutonRetour`
	
	- `JButton boutonSuite`
	
	- `JButton boutonAnnuler`
	
	- `JButton boutonTerritoire`
	
	- `JButton boutonTerritoire2`
	
	- `Container contentPane`
	
	- `JPanel panel3`
	
	- `String[] enregistrementTerritoire`
	
	- `String labelTerritoire`
	
	- `String[] enregistrementTerritoire2`
	
	- `String labelTerritoire2`
	
	- `int anneeDebut`
	
	- `int anneeFin`

- Méthodes :
	
	- Constructeurs :
		
		- `public FenetreEtudeFlux2(String text, String text2)`
			
			- Elle correspond au premier choix de l'utilisateur. Elle récupère les données de `FenetreEtudeFlux`.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `init()`
		
		- `public FenetreEtudeFlux2(String text, String text2, String[] text3, String[] text4)`
			
			- Elle correspond au premier choix de l'utilisateur et récupère les données de `FenetreBoiteACocherTerritoireFlux1` ou de `FenetreBoiteACocherTerritoireFlux2`.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setEnregistrementTerritoire(text3)`
				
				- `setEnregistrementTerritoire(text4)`
				
				- `init()`
		
		- `public FenetreEtudeFlux2(String text, String text2, int anneeDebutSelection, int anneeFinSelection)`
			
			- Elle correspond au premier choix de l'utilisateur et récupère les données de `FenetreBoiteACocherTerritoireFlux1` ou de `FenetreBoiteACocherTerritoireFlux2` dans le cas où aucun territoire n'a été sélectionné.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setAnneeDebut(anneeDebutSelection)`
				
				- `setAnneeFin(anneeFinSelection)`
				
				- `init()`
		
		- `public FenetreEtudeFlux2(String text, String text2, String text3[], String[] text4, int anneeDebutSelection, int anneeFinSelection)`
			
			- Elle correspond aux choix de l'utilisateur en provenance de `FenetreEtudeFlux3`, et récupère les données de `FenetreBoiteACocherTerritoireFlux1` ou de `FenetreBoiteACocherTerritoireFlux2`.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setEnregistrementTerritoire(text3)`
				
				- `setEnregistrementTerritoire(text4)`
				
				- `setAnneeDebut(anneeDebutSelection)`
				
				- `setAnneeFin(anneeFinSelection)`
				
				- `init()`
	
	- `private void init()`
		
		- Elle initialise la fenêtre.
		
		- Elle initialise l'affichage des zones de texte par les méthodes respectives `setLabelTerritoire()` et `setLabelTerritoire2()`.
		
		- Elle gère le contenu de la fenêtre par la méthode `zoneMouvante()`.
		
		- Elle gère les événements des boutons `Retour`, `Suite` et `Annuler` par l'intermédiaire des classes suivantes : `ClicRetour()` (interne), `ClicSuivant()` (interne) et `FenetreFermeture()` (externe).
		`private JPanel zoneMouvante()`
		
		- Elle gère le contenu de la fenêtre *via* les méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getLabelTerritoire()`, `getLabelTerritoire2()`, `getAnneeDebut()` et `getAnneeFin()`.
		
		- Elle appelle les classes externes `BoiteACocherDateDebut()` et `BoiteACocherDateFin()`.
		
		- Elle gère l'événement des boutons Choisir les territoires par l'intermédiaire des classes internes respectives : `ClicTerritoire()` et `ClicTerritoire2()`.
	
	- *getters* et *setters*
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public String[] getEnregistrementTerritoire()`
		
		- `private void setEnregistrementTerritoire(String[] enregistrementTerritoire)`
		
		- `public String[] getEnregistrementTerritoire2()`
		
		- `private void setEnregistrementTerritoire2(String[] enregistrementTerritoire)`
		
		- `public String getLabelTerritoire()`
		
		- `private void setLabelTerritoire()`
			
			- Elle a besoin de `getEnregistrementTerritoire()` et de `divisible(i)`.
		
		- `public String getLabelTerritoire2()`
		
		- `private void setLabelTerritoire2()`
			
			- Elle a besoin de getEnregistrementTerritoire2()` et de `divisible(i)`.
		
		- `private boolean divisible(int test)
			
			- Elle teste la divisibilité par 9 pour changer de ligne tous les neuf éléments dans le `JTextArea`.
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`

- Classes internes :
	
	- `ClicRetour` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Retour`. Elle renvoie à l'objet `FenetreEtudeFlux(getChoixDesClassifications(), getChoixDesNiveaux())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Suite`. Sous condition de sélections des territoires dans chacune des listes, elle envoie les données choisies à l'objet `FenetreEtudeFlux3(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), CompareAnneeDebut.getAnneeDebut(),CompareAnneeFin.getAnneeFin())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicTerritoire` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Choisir des territoires`. Elle envoie les données choisies à l'objet `BoiteACocherTerritoireFlux1(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), getAnneeDebut(), getAnneeFin())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicTerritoire2` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Choisir des territoires`. Elle envoie les données choisies à l'objet `BoiteACocherTerritoireFlux2(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), getAnneeDebut(), getAnneeFin())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`

### `BoiteACocherTerritoireFlux1`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `BaseAttributaireTerritoire baseAttributaireTerritoirev
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `String choixDesTerritoires`
	
	- `int longueurData`
	
	- `String[] titreData`
	
	- `String[] codeISO`
	
	- `String[] englishName`
	
	- `String[] nomFrancais`
	
	- `String[] nomContinent`
	
	- `String[] borneAnneeDebut`
	
	- `String[] borneAnneeFin`
	
	- `JCheckBox option`
	
	- `JCheckBox[] casesACocher`
	
	- `String[] liste`
	
	- `String[] enregistrementTerritoire`
	
	- `String[] enregistrementTerritoire2`
	
	- `JButton boutonOK`
	
	- `String[] listeHS`
	
	- `int anneeDebut`
	
	- `int anneeFin`

- Méthodes :
	
	- Constructeurs :
		
		- `public BoiteACocherTerritoireFlux1(String text, String text2)`
			
			- Elle correspond au premier choix de l'utilisateur si les dates n'ont pas été sélectionnées.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setListeHS()`
				
				- `donneesTerritoires()`
				
				- `init()`
		
		- `public BoiteACocherTerritoireFlux1(String text, String text2, int anneeDebut, int anneeFin)`
			
			- Elle correspond au premier choix de l'utilisateur si les dates ont été sélectionnées.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setAnneeDebut(anneeDebut)`
				
				- `setAnneeFin(anneeFin)`
				
				- `setListeHS()`
				
				- `donneesTerritoires()`
				
				- `init()`
		
		- `public BoiteACocherTerritoireFlux1(String text, String text2, String[] text3, String[] text4, int anneeDebut, int anneeFin)`
			
			- Elle correspond au choix complet de l'utilisateur.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setEnregistrementTerritoire(text3)`
				
				- `setEnregistrementTerritoire(text4)`
				
				- `setAnneeDebut(anneeDebut)`
				
				- `setAnneeFin(anneeFin)`
				
				- `setListeHS()`
				
				- `donneesTerritoires()`
				
				- `init()`
	
	- `private void init()`
		
		- Elle gère l'événement de fermeture de la fenêtre par la classe interne `Fermeture()`.
		
		- Elle intègre `getCasesACocher()`.
		
		- Elle gère les événements de la case à cocher Tous par la classe interne `ActionClassification()` et des autres cases à cocher par la classe interne `ActionClassification2()`.
		
		- Elle gère le bouton `OK` par la classe interne `ClicBoutonOK()`.
	
	- `private JPanel getCasesACocher()`
		
		- Elle crée la liste des cases à cocher à partir de la base de données des territoires `BaseAttributaireTerritoire()` récupérée par l'intermédiaire des méthodes suivantes : `getLongueurDataTerritoire()`, `getNomContinent()`, `getNomFrancais()`, `getCodeISO()`, `getBorneAnneeDebut()` et `getBorneAnneeFin()`.
	
	- `private void donneesTerritoires()`
		
		- Elle initialise la base de données en créant un objet `BaseAttributaireTerritoire()` qui initialise les méthodes suivantes :
			
			- `setBaseAttributaireTerritoire(baseAttributaireTerritoire)`
			
			- `setLongueurDataTerritoire(baseAttributaireTerritoire.getLongueurDataTerritoire())`
			
			- `setTitreData(baseAttributaireTerritoire.getTitreData())`
			
			- `setCodeISO(baseAttributaireTerritoire.getCodeISO())`
			
			- `setName(baseAttributaireTerritoire.getEnglishName())`
			
			- `setNom(baseAttributaireTerritoire.getNomFrancais())`
			
			- `setNomContinent(baseAttributaireTerritoire.getNomContinent())`
			
			- `setBorneAnneeDebut(baseAttributaireTerritoire.getBorneAnneeDebut())`
			
			- `setBorneAnneFin(baseAttributaireTerritoire.getBorneAnneeFin())`
	
	- *getters* et *setters*
		
		- `public BaseAttributaireTerritoire getBaseAttributaireTerritoire()`
		
		- `private void setBaseAttributaireTerritoire(BaseAttributaireTerritoire base)`
		
		- `public int getLongueurDataTerritoire()`
		
		- `private void setLongueurDataTerritoire(int longueurData)`
		
		- `public String[] getTitreData()`
		
		- `private void setTitreData(String[] titreData)`
		
		- `public String[] getCodeISO()`
		
		- `private void setCodeISO(String[] codeISO)`
		
		- `public String[] getEnglishName()`
		
		- `private void setEnglishName(String[] englishName)`
		
		- `public String[] getNomFrancais()`
		
		- `private void setNomFrancais(String[] nomFrancais)`
		
		- `public String[] getNomContinent()`
		
		- `private void setNomContinent(String[] nomContinent)`
		
		- `public String[] getBorneAnneeDebut()`
		
		- `private void setBorneAnneeDebut(String[] borneAnneeDebut)`
		
		- `public String[] getBorneAnneeFin()`
		
		- `private void setBorneAnneeFin(String[] borneAnneeFin)`
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getEnregistrementTerritoire()`
		
		- `private void setEnregistrementTerritoire(String[] enregistrementTerritoire)`
		
		- `public String[] getEnregistrementTerritoire2()`
		
		- `private void setEnregistrementTerritoire2(String[] enregistrementTerritoire)`
	
	- `private void toutCocher()`
		
		- Elle gère l'événement de la case à cocher Tous.
		
		- Elle permet de cocher toutes les autres cases à cocher et de les griser.
	
	- `private void cocherOption()`
		
		- Elle coche la case à cocher `Tous` si toutes les cases sont cochées.
	
	- `private void enregistrementCasesCochees()`
		
		- Elle enregistre les données sélectionnées (`codeISO`) sous la forme d'un tableau de type `String` par l'appel à la méthode `setEnregistrementTerritoire(enregistrementTerritoire)`.

- Classes internes :
	
	- `ActionClassification()` implémente l'interface `ItemListener`. La classe gère le clic sur la case à cocher `Tous`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche `toutCocher()`.
	
	- `ActionClassification2()` implémente l'interface `ItemListener`. La classe gère les clics des cases à cocher, à l'exception de la case `Tous`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche sous condition `toutCocher()` si toutes les cases sont cochées, ou `cocherOption()` si quelques cases sont cochées.
				
				- Elle déclenche `enregistrementCasesCochees()`.
	
	- `ClicBoutonOK` implémente l'interface `ActionListener`. La classe gère le retour des données vers la `FenetreEtudeFlux2(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), getAnneeDebut(), getAnneeFin())`.
	
	- `Fermeture` implémente `WindowListener`. La classe gère le clic sur la croix de la fenêtre `BoiteACocherTerritoire`. La classe gère le retour des données vers la `FenetreEtudeFlux2(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), getAnneeDebut(), getAnneeFin())`. Elle annule l'éventuelle sélection des cases à cocher.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void windowClosing(WindowEvent e)`

### `BoiteACocherTerritoireFlux2`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `BaseAttributaireTerritoire baseAttributaireTerritoire`
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `String choixDesTerritoires`
	
	- `int longueurData`
	
	- `String[] titreData`
	
	- `String[] codeISO`
	
	- `String[] englishName`
	
	- `String[] nomFrancais`
	
	- `String[] nomContinent`
	
	- `String[] borneAnneeDebut`
	
	- `String[] borneAnneeFin`
	
	- `JCheckBox option`
	
	- `JCheckBox[] casesACocher`
	
	- `String[] liste`
	
	- `String[] enregistrementTerritoire`
	
	- `String[] enregistrementTerritoire2`
	
	- `JButton boutonOK`
	
	- `String[] listeHS`
	
	- `int anneeDebut`
	
	- `int anneeFin`

- Méthodes :
	
	- Constructeurs :
		
		- `public BoiteACocherTerritoireFlux2(String text, String text2)`
			
			- Elle correspond au premier choix de l'utilisateur si les dates n'ont pas été sélectionnées.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setListeHS()`
				
				- `donneesTerritoires()`
				
				- `init()`
		
		- `public BoiteACocherTerritoireFlux2(String text, String text2, int anneeDebut, int anneeFin)`
			
			- Elle correspond au premier choix de l'utilisateur si les dates ont été sélectionnées.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setAnneeDebut(anneeDebut)`
				
				- `setAnneeFin(anneeFin)`
				
				- `setListeHS()`
				
				- `donneesTerritoires()`
				
				- `init()`
		
		- `public BoiteACocherTerritoireFlux2(String text, String text2, String[] text3, String[] text4, int anneeDebut, int anneeFin)`
			
			- Elle correspond au choix complet de l'utilisateur.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setEnregistrementTerritoire(text3)`
				
				- `setEnregistrementTerritoire(text4)
				
				- `setAnneeDebut(anneeDebut)`
				
				- `setAnneeFin(anneeFin)`
				
				- `setListeHS()`
				
				- `donneesTerritoires()`
				
				- `init()`
	
	- `private void init()`
		
		- Elle gère l'événement de fermeture de la fenêtre par la classe interne `Fermeture()`.
		
		- Elle intègre `getCasesACocher()`.
		
		- Elle gère les événements de la case à cocher `Tous` par la classe interne `ActionClassification()` et des autres cases à cocher par la classe interne `ActionClassification2()`.
		
		- Elle gère le bouton `OK` par la classe interne `ClicBoutonOK()`.
		`private JPanel getCasesACocher()`
		
		- Elle crée la liste des cases à cocher à partir de la base de données des territoires `BaseAttributaireTerritoire()` récupérée par l'intermédiaire des méthodes suivantes : `getLongueurDataTerritoire()`, `getNomContinent()`, `getNomFrancais()`, `getCodeISO()`, `getBorneAnneeDebut()` et `getBorneAnneeFin()`.
	
	- `private void donneesTerritoires()`
		
		- Elle initialise la base de données en créant un objet `BaseAttributaireTerritoire()` qui initialise les méthodes suivantes :
			
			- `setBaseAttributaireTerritoire(baseAttributaireTerritoire)`
			
			- `setLongueurDataTerritoire(baseAttributaireTerritoire.getLongueurDataTerritoire())`
			
			- `setTitreData(baseAttributaireTerritoire.getTitreData())`
			
			- `setCodeISO(baseAttributaireTerritoire.getCodeISO())`
			
			- `setName(baseAttributaireTerritoire.getEnglishName())`
			
			- `setNom(baseAttributaireTerritoire.getNomFrancais())`
			
			- `setNomContinent(baseAttributaireTerritoire.getNomContinent())`
			
			- `setBorneAnneeDebut(baseAttributaireTerritoire.getBorneAnneeDebut())`
			
			- `setBorneAnneFin(baseAttributaireTerritoire.getBorneAnneeFin())`
	
	- *getters* et *setters*
		
		- `public BaseAttributaireTerritoire getBaseAttributaireTerritoire()`
		
		- `private void setBaseAttributaireTerritoire(BaseAttributaireTerritoire base)`
		
		- `public int getLongueurDataTerritoire()`
		
		- `private void setLongueurDataTerritoire(int longueurData)`
		
		- `public String[] getTitreData()`
		
		- `private void setTitreData(String[] titreData)`
		
		- `public String[] getCodeISO()`
		
		- `private void setCodeISO(String[] codeISO)`
		
		- `public String[] getEnglishName()`
		
		- `private void setEnglishName(String[] englishName)`
		
		- `public String[] getNomFrancais()`
		
		- `private void setNomFrancais(String[] nomFrancais)`
		
		- `public String[] getNomContinent()`
		
		- `private void setNomContinent(String[] nomContinent)`
		
		- `public String[] getBorneAnneeDebut()`
		
		- `private void setBorneAnneeDebut(String[] borneAnneeDebut)`
		
		- `public String[] getBorneAnneeFin()`
		
		- `private void setBorneAnneeFin(String[] borneAnneeFin)`
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getEnregistrementTerritoire()`
		
		- `private void setEnregistrementTerritoire(String[] enregistrementTerritoire)`
		
		- `public String[] getEnregistrementTerritoire2()`
		
		- `private void setEnregistrementTerritoire2(String[] enregistrementTerritoire)`
	
	- `private void toutCocher()`
		
		- Elle gère l'événement de la case à cocher `Tous`.
		
		- Elle permet de cocher toutes les autres cases à cocher et de les griser.
	
	- `private void cocherOption()`
		
		- Elle coche la case à cocher `Tous` si toutes les cases sont cochées.
	
	- `private void enregistrementCasesCochees()`
		
		- Elle enregistre les données sélectionnées (`codeISO`) sous la forme d'un tableau de type `String` par l'appel à la méthode `setEnregistrementTerritoire2(enregistrementTerritoire)`.

- Classes internes :
	
	- `ActionClassification()` implémente l'interface `ItemListener`. La classe gère le clic sur la case à cocher `Tous`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche `toutCocher()`.
	
	- `ActionClassification2()` implémente l'interface `ItemListener`. La classe gère les clics des cases à cocher, à l'exception de la case `Tous`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche sous condition `toutCocher()` si toutes les cases sont cochées, ou `cocherOption()` si quelques cases sont cochées.
				
				- Elle déclenche `enregistrementCasesCochees()`.
	
	- `ClicBoutonOK` implémente l'interface `ActionListener`. La classe gère le retour des données vers la `FenetreEtude Flux2(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), getAnneeDebut(), getAnneeFin())`.
	
	- `Fermeture` implémente `WindowListener`. La classe gère le clic sur la croix de la fenêtre `BoiteACocherTerritoire`. La classe gère le retour des données vers la `FenetreEtudeFlux2(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), getAnneeDebut(), getAnneeFin())`. Elle annule l'éventuelle sélection des cases à cocher.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void windowClosing(WindowEvent e)`

### `FenetreEtudeFlux3`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `BoiteACocherProduits2 listeDesProduits`
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `String[] territoire`
	
	- `String[] territoire2`
	
	- `int anneeDebut`
	
	- `int anneeFin`
	
	- `String[] enregistrementProduit`
	
	- `String labelProduit`
	
	- `Container contentPane`
	
	- `JPanel panel3`
	
	- `JButton boutonRetour`
	
	- `JButton boutonSuite`
	
	- `JButton boutonAnnuler`
	
	- `JButton boutonProduit`

- Méthodes :
	
	- Constructeurs :
		
		- `public FenetreEtudeFlux3(String text1, String text2, String[] text3, String[] text4, int anneeDebut, int anneeFin)`
			
			- Elle récupère les données de `FenetreEtudeFlux2`.
			
			- Elle initialise les variables de classes par les méthodes suivantes :
				
				- `setChoixDesClassifications(text1)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setTerritoire(text3)`
				
				- `setTerritoire(text4)`
				
				- `setAnneeDebut(anneeDebut)`
				
				- `setAnneeFin(anneeFin)`
				
				- `init()`
		
		- `public FenetreEtudeFlux3(String text1, String text2, String[] text3, String[] text4, int anneeDebut, int anneeFin, String[] text5)`
			
			- Elle récupère les données de `BoiteACocherProduits2` ou de `FenetreEtudeFlux4`.
			
			- Elle initialise les variables de classes par les méthodes suivantes :
				
				- `setChoixDesClassifications(text1)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setTerritoire(text3)`
				
				- `setTerritoire(text4)`
				
				- `setAnneeDebut(anneeDebut)`
				
				- `setAnneeFin(anneeFin)`
				
				- `setEnregistrementProduit(text5)`
				
				- `init()`
		
		- `private void init()`
			
			- Elle initialise la zone de texte avec `setLabelProduit()`.
			
			- Elle gère l'affiche des données par la méthode `zoneMouvante()`.
			
			- Elle gère les événements des boutons `Retour`, `Suite` et `Annuler` respectivement par les classes `ClicRetour` (interne), `clicSuivant` (interne) et `FenetreFermeture` (externe).
		
		- `private JPanel zoneMouvante()`
			
			- Elle affiche les composants de la fenêtre par les méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()` et `getLabelProduit()`.
		
		- `private void affichageDesDonnees()`
			
			- Elle affiche les données récupérées sur la console en utilisant les méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getTerritoire()`, `getTerritoire2()`, `getAnneeDebut()` et `getAnneeFin()`.
		
		- *getters* et *setters*
			
			- `public String getChoixDesClassifications()`
			
			- `private void setChoixDesClassifications(String nomClassification)`
			
			- `public String getChoixDesNiveaux()`
			
			- `private void setChoixDesNiveaux(String nomDuNiveau)`
			
			- `public String[] getTerritoire()`
			
			- `private void setTerritoire(String[] territoire)`
			
			- `public String[] getTerritoire2()`
			
			- `private void setTerritoire2(String[] territoire)`
			
			- `public int getAnneeDebut()`
			
			- `private void setAnneeDebut(int anneeDebut)`
			
			- `public int getAnneeFin()`
			
			- `private void setAnneeFin(int anneeFin)`
			
			- `public String[] getEnregistrementProduit()`
			
			- `private void setEnregistrementProduit(String[] enregistrementProduit)`
			
			- `public String getLabelProduit()`
			
			- `private void setLabelProduit()`
				
				- Elle a besoin des methods `getEnregistrementProduit()` et `divisible(i)`
			
			- `private boolean divisible(int test)`
				
				- Elle teste la divisibilité par 9 pour changer de ligne tous les neuf éléments dans le `JTextArea`.

- Classes internes :
	
	- `ClicRetour` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Retour`. Elle renvoie à l'objet `FenetreEtudeFlux2(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getTerritoire2(), getAnneeDebut(), getAnneeFin())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Suite`. Elle envoie les données choisies à l'objet `FenetreEtudeFlux4(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getTerritoire2(), getAnneeDebut(), getAnneeFin(), getEnregistrementProduit())`
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicProduit` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Choisir des territoires`. Elle envoie les données choisies à l'objet `BoiteACocherProduits2(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire, getTerritoire2(), getAnneeDebut(), getAnneeFin())`
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`

### `BoiteACocherProduits2`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `BaseAttributaireProduit baseAttributaireProduit`
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `String[] territoire`
	
	- `String[] territoire2`
	
	- `int anneeDebut`
	
	- `int anneeFin`
	
	- `String[] listeHS`
	
	- `String[] nomHS`
	
	- `String[] listeNiveau`
	
	- `String[] nomNiveau`
	
	- `int[] niveau`
	
	- `int choixDesNiveauxInt`
	
	- `String[] codeSection`
	
	- `String[] code2Digit`
	
	- `String[] code4Digit`
	
	- `int[] coupureTab`
	
	- `int[] coupureTab2`
	
	- `String[] code6Digit`
	
	- `int LongueurData`
	
	- `String[] titreData`
	
	- `String[] codeSectionReference`
	
	- `String[] nameSectionReference`
	
	- `String[] nomSectionReference`
	
	- `String[] code2DigitRefrence`
	
	- `String[] name2DigitReference`
	
	- `String[] nom2DigitReference`
	
	- `String[] code4DigitReference`
	
	- `String[] name4DigitReference`
	
	- `String[] nom4DigitReference`
	
	- `String[] code6DigitReference`
	
	- `String[] name6DigitReference`
	
	- `String[] nom6DigitReference`
	
	- `JCheckBox option`
	
	- `JCheckBox[] casesACocher`
	
	- `String[] liste`
	
	- `JButton boutonOK`
	
	- `String[] enregistrementProduit`

- Méthodes :
	
	- Constructeur : `public BoiteACocherProduits2(String text, String text2, String[] text3, String[] text4, int anneeDebut, int anneeFin)`
		
		- Elle initialise les variables d'instance par les méthodes suivantes :
			
			- `setChoixDesClassifications(text)`
			
			- `setChoixDesNiveaux(text2)`
			
			- `setTerritoire(text3)`
			
			- `setTerritoire(text4)`
			
			- `setAnneeDebut(anneeDebut)`
			
			- `setAnneeFin(anneeFin)`
			
			- `setListeHS()`
			
			- `setNomHS()`
			
			- `setListeNiveau()`
			
			- `setNomNiveau()`
			
			- `setNiveau()`
			
			- `donneesProduits()`
			
			- `init()`
		
		- Elle gère la fenêtre. Par la classe interne `Fermeture`, elle précise l'action lors de la fermeture de la fenêtre par la croix.
		
		- Elle gère les événements associés à la case à cocher `Tous` par la classe interne `ActionClassification()` et aux autres cases par la classe interne `ActionClassification2()`.
	
	- `private void init()`
		
		- Elle utilise la méthode `getChoixDesNiveaux()` afin d'obtenir la bonne liste à cocher à sélectionner parmi : `getCasesACocherSection()`, `getCasesACocher2Digit()`, `getCasesACocher4Digit()` et `getCasesACocher6Digit()`.
		
		- Elle gère l'événement du bouton `OK` par l'intermédiaire de la classe interne `ClicBoutonOK`.
	
	- Les cases à cocher affichées dépendent du choix de la classification et du niveau.
		
		- `private JPanel getCasesACocherSection()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()` et `getCodeSectionReference()` pour lister les cases à cocher à afficher.
		
		- `private JPanel getCasesACocher2Digit()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()`, `getCodeSectionReference()`, `getNom2DigitReference()` et `getCode2DigitReference()` pour lister les cases à cocher à afficher.
		
		- `private JPanel getCasesACocher4Digit()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()`, `getCodeSectionReference()`, `getNom2DigitReference()`, `getCode2DigitReference()`, `getNom4DigitReference()` et `getCode4DigitReference()` pour lister les cases à cocher à afficher.
		
		- `private JPanel getCasesACocher6Digit()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()`, `getCodeSectionReference()`, `getNom2DigitReference()`, `getCode2DigitReference()`, `getNom4DigitReference()`, `getCode4DigitReference()`, `getNom6DigitReference()` et `getCode6DigitReference()` pour lister les cases à cocher à afficher.
	
	- `private void donneesProduits()`
		
		- Elle crée un objet `BaseAttributaireProduit(getChoixDesClassifications(), getChoixDesNiveaux())`. Elle charge le tableau complet de la base de données correspondant à la classification retenue qu'il faut filtrer pour obtenir l'affichage souhaité en fonction du niveau retenu.
		
		- En utilisant les choix de l'utilisateur, les méthodes suivantes ont pour objectif de charger la base de données dans les variables de classe :
			
			- `setBaseAttributaireProduit(baseAttributaireProduit.getChoixDesNiveauxInt())`
			
			- `setLongueurDataProduit(baseAttributaireProduit.getLongueurDataProduit())`
			
			- `setTitreData(baseAttributaireProduit.getTitreData())`
			
			- `setCodeSectionReference(baseAttributaireProduit.getCodeSectionReference())`
			
			- `setNameSectionReference(baseAttributaireProduit.getNameSectionReference())`
			
			- `setNomSectionReference(baseAttributaireProduit.getNomSectionReference())`
			
			- `setCode2DigitReference(baseAttributaireProduit.getCode2DigitReference())`
			
			- `setName2DigitReference(baseAttributaireProduit.getName2DigiReference())`
			
			- `setNom2DigitReference(baseAttributaireProduit.getNom2DigiReference())`
			
			- `setCode4DigitReference(baseAttributaireProduit.getCode4DigitReference())`
			
			- `setName4DigitReference(baseAttributaireProduit.getName4DigitReference())`
			
			- `setNom4DigitReference(baseAttributaireProduit.getNom4DigitReference())`
			
			- `setCode6DigitReference(baseAttributaireProduit.getCode6DigitReference())`
			
			- `setName6DigitReference(baseAttributaireProduit.getName6DigitReference())`
			
			- `setNom6DigitReference(baseAttributaireProduit.getNom6DigitReference())`
			
			- `setCodeSection(baseAttributaireProduit.getCodeSection())`
			
			- `setCode2Digit(baseAttributaireProduit.getCode2Digit())`
			
			- `setCode4Digit(baseAttributaireProduit.getCode4Digit())`
			
			- `setCode6Digit(baseAttributaireProduit.getCode6Digit())`
	
	- *getters* et *setters*
		
		- `public BaseAttributaireProduit getBaseAttributaireProduit()`
		
		- `private void setBaseAttributaireProduit(BaseAttributaireProduit base)`
		
		- `public int getLongueurDataProduit()`
		
		- `private void setLongueurDataProduit(int longueurData)`
		
		- `public String[] getTitreData()`
		
		- `private void setTitreData(String[] titreData)`
		
		- `public String[] getCodeSectionReference()`
		
		- `private void setCodeSectionReference(String[] codeSectionReference)`
		
		- `public String[] getNameSectionReference()`
		
		- `private void setNameSectionReference(String[] nameSectionReference)`
		
		- `public String[] getNomSectionReference()`
		
		- `private void setNomSectionReference(String[] nomSectionReference)`
		
		- `public String[] getCode2DigitReference()`
		
		- `private void setCode2DigitRefrence(String[] code2DigitReference)`
		
		- `public String[] getName2DigitReference()`
		
		- `private void setName2DigitReference(String[] name2DigitReference)`
		
		- `public String[] getNom2DigitReference()`
		
		- `private void setNom2DigitReference(String[] nom2DigitReference)`
		
		- `public String[] getCode4DigitRefrence()`
		
		- `private void setCode4DigitReference(String[] code4DigitReference)`
		
		- `public String[] getName4DigitReference()`
		
		- `private void setName4DigitReference(String[] name4DigitReference)`
		
		- `public String[] getNom4DigitReference()`
		
		- `private void setNom4DigitReference(String[] nom4DigitReference)`
		
		- `public String[] getCode6DigitReference()`
		
		- `private void setCode6DigitReference(String[] code6DigitReference)`
		
		- `public String[] getName6DigitReference()`
		
		- `private void setName6DigitReference(String[] name6DigitReference)`
		
		- `public String[] getNom6DigitReference()`
		
		- `private void setNom6DigitReference(String[] nom6DigitReference)`
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public int getChoixDesNiveauxInt()`
		
		- `private void setChoixDesNiveauxInt(int nomDuNiveau)`
		
		- `public String[] getTerritoire()`
		
		- `private void setTerritoire(String[] territoire)`
		
		- `public String[] getTerritoire2()`
		
		- `private void setTerritoire2(String[] territoire)`
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`
		
		- `public String[] getCodeSection()`
		
		- `private void setCodeSection(String codeSection)`
		
		- `public String[] getCode2Digit()`
		
		- `private void setCode2Digit(String[] code2Digit)`
		
		- `public String[] getCode4Digit()`
		
		- `private void setCode4Digit(String[] code4Digit)`
		
		- `public String[] getCode6Digit()`
		
		- `private void setCode6Digit(String[] code6Digit)`
		
		- `public String[] getEnregistrementProduit()`
		
		- `private void setEnregistrementProduit(String[] enregistrementProduit)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`
		
		- `public String[] getNomNiveau()`
		
		- `private void setNomNiveau()`
		
		- `public int[] getNiveau()`
		
		- `private void setNiveau()`
		
		- `private void toutCocher()`
		
		- `private void cocherOption()`
		
		- `private void enregistrementCasesCocheesSection()`
		
		- `private void enregistrementCasesCochees2Digit()`
		
		- `private void enregistrementCasesCochees4Digit()`
		
		- `private void enregistrementCasesCochees6Digit()`

- Classes internes :
	
	- `ActionClassification()` implémente l'interface `ItemListener`. La classe gère le clic sur la case à cocher `Tous`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche `toutCocher()`.
	
	- `ActionClassification2()` implémente l'interface ItemListener. La classe gère les clics des cases à cocher, à l'exception de la case `Tous`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche sous condition `toutCocher()` si toutes les cases sont cochées, ou `cocherOption()` si quelques cases sont cochées.
				
				- En fonction de `getChoixDesNiveauxInt()`, elle déclenche `enregistrementCasesCocheesSection()`, `enregistrementCasesCochees2Digit()`, `enregistrementCasesCochees4Digit()` et `enregistrementCasesCochees6Digit()`.
	
	- `ClicBoutonOK` implémente l'interface `ActionListener`. La classe gère le retour des données vers la `FenetreEtudeFlux3(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getTerritoire2(), getAnneeDebut(), getAnneeFin(), getEnregistrementProduit())`.
	
	- `Fermeture` implémente `WindowListener`. La classe gère le clic sur la croix de la fenêtre `BoiteACocherTerritoire`. La classe gère le retour des données vers la `FenetreEtudeFlux3(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getTerritoire2(), getAnneeDebut(), getAnneeFin())`. Elle annule l'éventuelle sélection des cases à cocher.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void windowClosing(WindowEvent e)`

 ### `FenetreEtudeFlux4`

 C'est un objet qui hérite de `JFrame`.

 - Variables d'instance :
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `int choixDesNiveauxInt`
	
	- `String[] territoire`
	
	- `String[] territoire2`
	
	- `int anneeDebut`
	
	- `int anneeFin`
	
	- `int[] annee`
	
	- `String[] codeProduit`
	
	- `String[] listeHS`
	
	- `String[] nomHS`
	
	- `String[] nomDossierHS`
	
	- `String[] listeNiveau`
	
	- `String[] nomNiveau`
	
	- `int[] niveau`
	
	- `String[] sousDossierPartie1`
	
	- `String[] sousDossierPartie2`
	
	- `String dossier`
	
	- `String sousDossierP1`
	
	- `String sousDossierP2`
	
	- `String sousDossierP3`
	
	- `String sousSousDossier = "Bd-Flux\\"`
	
	- `BaseAttributaireTerritoire baseAttributaireTerritoire`
	
	- `int baseAttributaireTerritoireLongueurData`
	
	- `String[] baseAttributaireTerritoireTitreData`
	
	- `String[] baseAttributaireTerritoireCodeISO`
	
	- `String[] baseAttributaireTerritoireEnglishName`
	
	- `String[] baseAttributaireTerritoireNomFrancais`
	
	- `String[] baseAttributaireTerritoireNomContinent`
	
	- `String[] baseAttributaireTerritoireBorneAnneeDebut`
	
	- `String[] baseAttributaireTerritoireBorneAnneeFin`
	
	- `String[] englishNameTerritoire`
	
	- `String[] nomFrancaisTerritoire`
	
	- `String[] nomContinent`
	
	- `String[] borneAnneeDebutTerritoire`
	
	- `String[] borneAnneeFinTerritoire`
	
	- `String[] englishNameTerritoire2`
	
	- `String[] nomFrancaisTerritoire2`
	
	- `String[] nomContinent2`
	
	- `String[] borneAnneeDebutTerritoire2`
	
	- `String[] borneAnneeFinTerritoire2`
	
	- `BaseAttributaireProduit baseAttributaireProduit`
	
	- `String[] baseAttributaireProduitCodeProduit`
	
	- `String[] baseAttributaireProduitNomProduit`
	
	- `String[] baseAttributaireProduitNameProduct`
	
	- `String labelTerritoire`
	
	- `String labelTerritoire2`
	
	- `String labelProduit`
	
	- `Container contentPane`
	
	- `JPanel panel3`
	
	- `JButton boutonRetour`
	
	- `JButton boutonSuite`
	
	- `JButton boutonAnnuler`
	
	- `JButton boutonAffichageDonnees`
	
	- `String[] nomFichierALire`
	
	- `String titreExtraction`
	
	- `String donneesExtraction`
	
	- `String[] titreExtractionTableau`
	
	- `String[][] donneesExtractionTableau`

- Méthodes :
	
	- Constructeur : `FenetreEtudeFlux4(String text1, String text2, String[] text3, String[] text4, int anneeDebut, int anneeFin, String[] text5)`
		
		- Elle permet d'initialiser les variables d'instance permettant d'initialiser les autres variables d'instance par les méthodes :
			
			- `setChoixDesClassifications(text1)`
			
			- `setChoixDesNiveaux(text2)`
			
			- `setTerritoire(text3)`
			
			- `setTerritoire(text4)`
			
			- `setAnneeDebut(anneeDebut)`
			
			- `setAnneeFin(anneeFin)`
			
			- `setProduit(text5)`
			
			- `init()`
		
		- Elle contrôle les étapes de la lecture des fichiers de la base de données retenue par l'utilisateur par les méthodes :
			
			- `chargementFichierALire()`
			
			- `lectureBaseDeDonnees()`
		
		- Elle récupère les informations choisies par l'utilisateur grâce aux méthodes :
			
			- `setLabelTerritoire()`
			
			- `setLabelTerritoire2()`
			
			- `setLabelProduit()`
		
		- Elle gère l'affichage de la fenêtre avec la méthode `zoneMouvante()`.
		
		- Elle gère les événements des boutons `Retour`, `Enregistrement` et `Annuler` par l'intermédiaire des classes suivantes respectives : `ClicRetour` (interne), `ClicSuivant` (interne) et `ClicAnnuler` (externe).
	
	- `private JPanel zoneMouvante()`
		
		- Elle gère l'affichage des données complètes de l'extraction en utilisant les méthodes : `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getLabelTerritoire()`, `getLabelTerritoire2()` et `getLabelProduit()`.
		
		- Elle gère l'événement du bouton `Afficher les données` par l'appel à la classe interne `BoutonAffichageDonnees()`.
	
	- `private void tableauExtrait()`
		
		- Elle affiche les données sous la forme d'un `JTable`.
		
		- Elle utilise les méthodes : `getDonneesExtraction()` et `getTitreExtraction()`.
	
	- `public void getLabelTerritoire()`
		
		- Elle gère l'affiche du `JTextArea` affichant les choix de l'utilisateur concernant les territoires.
	
	- `private void setLabelTerritoire()`
		
		- Elle crée le tableau complet des données territoriales choisies par l'utilisateur en utilisant les méthodes : `getTerritoire()`, `getBaseAttributaireTerritoireCodeISO()` et `getBaseAttributaireTerritoireNomFrancais()`.
	
	- `public void getLabelTerritoire2()`
		
		- Elle gère l'affiche du `JTextArea` affichant les choix de l'utilisateur concernant les territoires.
	
	- `private void setLabelTerritoire2()`
		
		- Elle crée le tableau complet des données territoriales choisies par l'utilisateur en utilisant les méthodes : `getTerritoire2()`, `getBaseAttributaireTerritoireCodeISO()` et `getBaseAttributaireTerritoireNomFrancais()`.
	
	- `public getLabelProduit()`
		
		- Elle gère l'affiche du `JTextArea` affichant les choix de l'utilisateur concernant les produits.
	
	- `private void setLabelProduit()`
		
		- Elle crée le tableau complet des données concernant les produits choisies par l'utilisateur en utilisant les méthodes : `getProduit()`, `getBaseAttributaireProduitCodeProduit()` et `getBaseAttributaireNomProduit()`.
	
	- `private void init()`
		
		- Elle initialise les variables d'instance par les méthodes : `donneesTerritoires()`, `setListeHS()`, `setNomHS()`, `setNomDossierHS()`, `setListeNiveau()`, `setSousDossierPartie1()`, `setSousDossierPartie2()`, `setDossier(dossier)`, `setSousDossier1(sousDossier1)`, `setSousDossier2(sousDossier2)`, `setSousDossier3(sousDossier3)`, `setChoixDesNiveauxInt(choixDesNiveauxInt)`, `setAnnee()`, `setEnglishName(englishNameTerritoire)`, `setNomFrancais(nomFrancaisTerritoire)`, `setNomContinent(nomContinent)`, `setAnneeDebutTerritoire(anneeDebutTerritoire)`, `setAnneeFin(anneeFinTerritoire)`, `setEnglishNameTerritoire2(englishNameTerritoire2)`, `setNomFrancaisTerritoire2(nomFrancaisTerritoire2)`, `setNomContinent2(nomContinent2)`, `setAnneeDebutTerritoire2(anneeDebutTerritoire2)` et `setAnneeFin2(anneeFinTerritoire2)`. Pour ce, l'ordre des initialisations est très important, puisque les méthodes utilisent les variables enregistrées *via* `getListeHS()`, `getNomHS()`, `getNomDossier(), `getListeNiveau()`, `getNiveau()`, `getSousDossierPartie1()`, `getSousDossierPartie2()`, `getAnneeDebut()`, `getAnneeFin()`, `getTerritoire()`, `getBaseAttributaireTerritoireCodeISO()`, `getBaseAttributaireTerritoireEnglishName()`, `getBaseAttributaireTerritoireNomFrancais()`, `getBaseAttributaireTerritoireNomContinent()`, `getBaseAttributaireTerritoireBorneAnneeDebut()`, `getBaseAttributaireTerritoireAnneeFin()`.

	-	`private void donneesTerritoires()`
		
		- En appelant l'objet `BaseAttributaireTerritoire`, elle charge la base de données des territoires *via* les méthodes suivantes :
			
			- `setBaseAttributaireTerritoire(…)`
			
			- `setBaseAttributaireTerritoireLongueurData(…)`
			
			- `setBaseAttributaireTerritoireTitreData(…)`
			
			- `setBaseAttributaireTerritoireCodeISO(…)`
			
			- `setBaseAttributaireTerritoireEnglishName(…)`
			
			- `setBaseAttributaireTerritoireNomFrancais(…)`
			
			- `setBaseAttributaireTerritoireNomContinent(…)`
			
			- `setBaseAttributaireTerritoireBorneAnneeDebut(…)`
			
			- `setBaseAttributaireTerritoireBorneAnneeFin(…)`
	
	- `private void donneesProduits()`
		
		- En appelant l'objet BaseAttributaireProduit, elle charge la base de données des territoires *via* la méthode `setBaseAttributaireProduit(…)`.
		
		- En utilisant `getChoixDesNiveauxInt()`, les méthodes `setBaseAttributaireProduitCodeProduit(…)` et `setBaseAttributaireProduitNomProduit(…)` chargent les données choisies par l'utilisateur.
		`private void chargementFichierALire()`
		
		- Elle initialise la liste des fichiers à lire en fonction des choix de l'utilisateur par l'intermédiaire des méthodes : `getDossier()`, `getSousDossierP1()`, `getSousDossier2()`, `getSousDossier3()`, `getAnnee()`, `getTerritoire()`, `getTerritoire2()`, `getAnneeDebutTerritoire()`, `getAnneeFinTerritoire()` et la variable de classe : `sousSousDossier`, la méthode `setNomFichierALire(nomFichierALire)`.
	
	- `private void lectureBaseDeDonnees()`
		
		- Grâce à la méthode `getNomFichierALire()`, on peut initialiser un objet `LectureCSV`. On utilise ses méthodes `getTitreTableau()`, `getDonneesTableau()` et `getNombreDeColonne()` pour initialiser la lecture.
		
		- Le code insère la colonne `Nom du territoire` avec les noms complets des territoires et ajoute dans la colonne `Nom des produits` les noms complets.
		
		- Elle permet d'initialiser les méthodes `setTitrreExtraction(inittitre)`, `setTitreExtractionTableau(titre2)`, `setDonneesExtraction(initSortie)` et `setDonneesExtractionTableau(tabExtraction, numColonne + 1)` en utilisant `getBaseAttributaireTerritoireCodeISO()`, `getBaseAttributaireTerritoireNomFrancais()`, `getProduit()`, `getBaseAttributaireProduitCodeProduit()` et `getBaseAttributaireProduitNomProduit()`.
	
	- `private void affichageDonneesRecuperees()`
		
		- Elle permet de lire les choix de l'utilisateur sur la console grâce aux méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getChoixDesNiveauxInt()`, `getTerritoire()`, `getTerritoire2()`, `getAnneeDebut()`, `getAnneeFin()`, `getAnnee()` et `getProduit()`. (point de contrôle pour le programmeur)
	
	- `private void affichageDonneesAttributaires()`
		
		- Elle permet d'afficher les données attributaires sélectionnées par l'utilisateur *via* `getTerritoire()`, `getNomFrancaisTerritoire()`, `getAnneeDebutTerritoire()` et `getAnneeFinTerritoire()`. (point de contrôle pour le programmeur)
	
	- `private void affichageFichiersALire()`
		
		- Elle permet d'afficher sur la console la liste des fichiers à lire pour l'extraction des données choisies par l'utilisateur. (point de contrôle pour le programmeur)
	
	- *getters* et *setters* :
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public String[] getTerritoire()`
		
		- `private void setTerritoire(String[] territoire)`
		
		- `public String[] getTerritoire2()`
		
		- `private void setTerritoire2(String[] territoire)`
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`
		
		- `public int[] getAnnee()`
		
		- `private void setAnnee(int[] annee)`
		
		- `public String[] getProduit()`
		
		- `private void setProduit(String[] produit)`
		
		- `public int getChoixDesNiveauxInt()`
		
		- `private void setChoixDesNiveauxInt(int nomDuNiveau)`
		
		- `public String getDossier()`
		
		- `private void setDossier(String dossier)`
		
		- `public String getSousDossierP1()`
		
		- `private void setSousDossierP1(String sousDossierP1)`
		
		- `public String getSousDossierP2()`
		
		- `private void setSousDossierP2(String sousDossierP2)`
		
		- `public String getSousDossierP3()`
		
		- `private void setSousDossierP3(String sousDossierP3)`
		
		- `public BaseAttributaireTerritoire getBaseAttributaireTerritoire()`
		
		- `private void setBaseAttributaireTerritoire(BaseAttributaireTerritoire base)`
		
		- `public int getBaseAttributaireTerritoireLongueurData()`
		
		- `private void setBaseAttributaireTerritoireLongueurData(int longueurData)`
		
		- `public String[] getBaseAttributaireTerritoireTitreData()`
		
		- `private void setBaseAttributaireTerritoireTitreData(String[] titreData)`
		
		- `public String[] getBaseAttributaireTerritoireCodeISO()`
		
		- `private void setBaseAttributaireTerritoireCodeISO(String[] codeISO)`
		
		- `public String[] getBaseAttributaireTerritoireEnglishName()`
		
		- `private void setBaseAttributaireTerritoireEnglishName(String[] englishName)`
		
		- `public String[] getBaseAttributaireTerritoireNomFrancais()`
		
		- `private void setBaseAttributaireTerritoireNomFrancais(String[] nomFrancais)`
		
		- `public String[] getBaseAttributaireTerritoireNomContinent()`
		
		- `private void setBaseAttributaireTerritoireNomContinent(String[] nomContinent)`
		
		- `public String[] getBaseAttributaireTerritoireBorneAnneeDebut()`
		
		- `private void setBaseAttributaireTerritoireBorneAnneeDebut(String[] borneAnneeDebut)`
		
		- `public String[] getBaseAttributaireTerritoireBorneAnneeFin()`
		
		- `private void setBaseAttributaireTerritoireBorneAnneeFin(String[] borneAnneeFin)`
		
		- `public BaseAttributaireProduit getBaseAttributaireProduit()`
		
		- `private void setBaseAttributaireProduit(BaseAttributaireProduit baseProduit)`
		
		- `public String[] getBaseAttributaireProduitCodeProduit()`
		
		- `private void setBaseAttributaireProduitCodeProduit(String[] code)`
		
		- `public String[] getBaseAttributaireProduitNomProduit()`
		
		- `private void setBaseAttributaireProduitNomProduit(String[] nomProduit)`
		
		- `public String[] getBaseAttributaireProduitNameProduct()`
		
		- `private void setBaseAttributaireProduitNameProduct(String[] nameProduct)`
		
		- `public String[] getEnglishNameTerritoire()`
		
		- `private void setEnglishNameTerritoire(String[] englishNameTerritoire)`
		
		- `public String[] getNomFrancaisTerritoire()`
		
		- `private void setNomFrancaisTerritoire(String[] nomFrancaisTerritoire)`
		
		- `public String[] getNomContinent()`
		
		- `private void setNomContinent(String[] nomContinent)`
		
		- `public String[] getAnneeDebutTerritoire()`
		
		- `private void setAnneeDebutTerritoire(String[] anneeDebutTerritoire)`
		
		- `public String[] getAnneeFinTerritoire()`
		
		- `private void setAnneeFinTerritoire(String[] anneeFinTerritoire)`
		
		- `public String[] getEnglishNameTerritoire2()`
		
		- `private void setEnglishNameTerritoire2(String[] englishNameTerritoire)`
		
		- `public String[] getNomFrancaisTerritoire2()`
		
		- `private void setNomFrancaisTerritoire2(String[] nomFrancaisTerritoire)`
		
		- `public String[] getNomContinent2()`
		
		- `private void setNomContinent2(String[] nomContinent)`
		
		- `public String[] getAnneeDebutTerritoire2()`
		
		- `private void setAnneeDebutTerritoire2(String[] anneeDebutTerritoire)`
		
		- `public String[] getAnneeFinTerritoire2()`
		
		- `private void setAnneeFinTerritoire2(String[] anneeFinTerritoire)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getNomHS()`
		
		- `private void setNomHS()`
		
		- `public String[] getNomDossierHS()`
		
		- `private void setNomDossierHS()`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`
		
		- `public int[] getNiveau()`
		
		- `private void setNiveau()`
		
		- `public String[] getSousDossierPartie1()`
		
		- `private void setSousDossierPartie1()`
		
		- `public String[] getSousDossierPartie2()`
		
		- `private void setSousDossierPartie2()`
		
		- `public String[] getSousDossierPartie3()`
		
		- `private void setSousDossierPartie3()`
		
		- `public String[] getNomFichierALire()`
		
		- `private void setNomFichierALire(String[] table)`
		
		- `public String getTitreExtraction()`
		
		- `private void setTitreExtraction(String table)`
		
		- `public String getDonneesExtraction()`
		
		- `private void setDonneesExtraction(String table)`
		
		- `public String[] getTitreExtractionTableau()`
		
		- `private void setTitreExtractionTableau(String[] table)`
		
		- `public String[] getDonneesExtractionTableau()`
		
		- `private void setDonneesExtractionTableau(String[] tabExtraction, int numColonne)`

- Classes internes :
	
	- `ClicRetour` implémente `ActionListener`. La classe gère le retour vers `FenetreEtudeFlux3(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getTerritoire2(), getAnneeDebut(), getAnneeFin(), getProduit())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implemente `ActionListener`. La classe enregistre les données choisies par l'utilisateur sous la forme d'un fichier `*.csv` en appelant la méthode `ecrire()` qui a besoin de `getChoixDesClassifications()` et `getChoixDesNiveaux()`. Elle renvoie vers l'objet `FenetreChoixEtude()`.
	
	- `private void ecrire()`
		
		- Elle enregistre l'extraction sous la forme d'un nom unique en utilisant `getDossier()` et un objet `DateMaintenant` qui capture la date au moment du clic sur le bouton de sauvegarde grâce à sa méthode `getDateMaintenant()` qui renvoie une chaîne de caractères.
		
		- Le nom choisi, elle a besoin des méthodes `getTitreExtractionTableau()` et `getDonneesExtractionTableau()` pour enregistrer l'extraction voulue par l'utilisateur.
	
	- `private void messageDeFermeture()`
		
		- Elle ouvre une nouvelle fenêtre en fonction du choix de l'utilisateur : s'il confirme l'annulation, l'objet `FenetreChoixEtude()` est appelé ; s'il ne souhaite plus annuler, on reste sur l'objet `FenetreEtudeTerritoire4`.
	
	- `ClicAnnuler` implémente `ActionListener`. La classe renvoie à la méthode `messageDeFermeture()`.
		
		- `public void actionPerformed(ActionEvent e)`
	
	- `BoutonAffichageDonnees` implémente `ActionListener`. Elle permet d'afficher le `JTable` en appelant la méthode `tableauExtrait()`.
		
		- `public void actionPerformed(ActionEvent e)`
	
	- `Fermeture` implémente `WindowListener`. La classe gère le clic sur la croix de la fenêtre `FenetreEtudeFlux4`. La classe ouvre une boîte de dialogue de confirmation ou annulation *via* la méthode `messageDeFermeture()`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void windowClosing(WindowEvent e)`

### `FenetreEtudeProduit`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `BoiteACocherClassification listeDesClassifications`
	
	- `BoiteACocherNiveau listeDesNiveaux`
	
	- `Container contentPane`
	
	- `JButton boutonRetour`
	
	- `JButton boutonSuite`
	
	- `JButton boutonAnnuler`
	
	- `JPanel panel3`
	
	- `String[] listeHS`
	
	- `String[] listeNiveau`
	
	- `int choixDesClassifications`
	
	- `int choixDesNiveaux`

- Méthodes :
	
	- Constructeurs :
		
		- `public FenetreEtudeProduit()`
			
			- Ce constructeur est appelé par `FenetreChoixEtude()`.
			
			- Le constructeur initialise les variables de classe en lançant les méthodes suivantes :
				
				- `setListeHS()` : transformation de l'énumération `ListeHS` en tableau
				
				- `setListeNiveau()` : transformation de l'énumération `ListeNiveau` en tableau
				
				- `setChoixDesClassifications(0)` : initialisation du choix de la classification par défaut
				
				- `setChoixDesNiveaux(0)` : initialisation du choix du niveau par défaut
		
		- `public FenetreEtudeProduit(String classification, String niveau)`
			
			- Ce constructeur est appelé par `FenetreEtudeProduit2()`. Il récupère les données sélectionnées par l'utilisateur de la fenêtre suivante pour afficher sa sélection.
			
			- Le constructeur initialise les variables de classe en lançant les méthodes suivantes :
				
				- `setListeHS()` : transformation de l'énumération `ListeHS` en tableau
				
				- `setListeNiveau()` : transformation de l'énumération `ListeNiveau` en tableau
				
				- `setChoixDesClassifications(classification)` : initialisation du choix de la classification opéré par l'utilisateur
				
				- `setChoixDesNiveaux(niveau)` : initialisation du choix du niveau opéré par l'utilisateur
	
	- `private void init()`
		
		- Elle crée la fenêtre. La zone principale de la fenêtre est gérée par la méthode `zoneMouvante()`.
		
		- Elle gère les événements des boutons `Retour`, `Suite` et `Annuler` par l'intermédiaire des classes suivantes : `ClicRetour()` (interne), `ClicSuivant()` (interne) et `FenetreFermeture()` (externe).
	
	- `private JPanel zoneMouvante()`
		
		- Elle appelle les objets : `BoiteACocherClassification()` et `BoiteACocherNiveau()`. Ces deux objets gèrent l'affichage des listes sur l'écran.
		
		- Si un choix a déjà été opéré par l'utilisateur, elle appelle les objets : `BoiteACocherClassification(getChoixDesClassifications()) et BoiteACocherNiveau(getChoixDesNiveaux())`
	
	- *getters* et *setters* :
		
		- `public int getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(int choixDesClassifications)`
		
		- `public int getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(int choixDesNiveaux)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`

- Classes internes :
	
	- `ClicRetour` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Retour`. Elle renvoie à l'objet `FenetreChoixEtude()`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Suite`. Elle envoie les données choisies à l'objet `FenetreEtudeProduit2(getChoixDesClassifications(), getChoixDesNiveaux())`
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`

### `FenetreEtudeProduit2`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `BoiteACocherProduits3 listeDesProduits`
	
	- `BoiteACocherDateDebut listeDesDates1`
	
	- `BoiteACocherDateFin listeDesDates2`
	
	- `JButton boutonRetour`
	
	- `JButton boutonSuite`
	
	- `JButton boutonAnnuler`
	
	- `JButton boutonProduit`
	
	- `Container contentPane`
	
	- `JPanel panel3`
	
	- `String[] enregistrementProduit`
	
	- `String labelProduit`
	
	- `int anneeDebut`
	
	- `int anneeFin`

- Méthodes :
	
	- Constructeurs :
		
		- `public FenetreEtudeProduit2(String text, String text2)`
			
			- Elle correspond au premier choix de l'utilisateur. Elle récupère les données de `FenetreEtudeTerritoriale`.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `init()`
		
		- `public FenetreEtudeProduit2(String text, String text2, String[] text3)`
			
			- Elle correspond au premier choix de l'utilisateur et récupère les données de `FenetreBoiteACocherProduit3`.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setEnregistrementProduit(text3)`
				
				- `init()`
		
		- `public FenetreEtudeProduit2(String text, String text2, int anneeDebutSelection, int anneeFinSelection)`
			
			- Elle correspond au premier choix de l'utilisateur et récupère les données de `FenetreBoiteACocherProduit3` dans le cas où aucun territoire n'a été sélectionné.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setAnneeDebut(anneeDebutSelection)`
				
				- `setAnneeFin(anneeFinSelection)`
				
				- `init()`
		
		- `public FenetreEtudeProduit2(String text, String text2, String text3[], int anneeDebutSelection, int anneeFinSelection)`
			
			- Elle correspond aux choix de l'utilisateur en provenance de `FenetreEtudeProduit3`, et récupère les données de `FenetreBoiteACocherProduit3`.
			
			- Elle initialise les variables de classe par les méthodes :
				
				- `setChoixDesClassifications(text)`
				
				- `setChoixDesNiveaux(text2)`
				
				- `setEnregistrementProduit(text3)`
				
				- `setAnneeDebut(anneeDebutSelection)`
				
				- `setAnneeFin(anneeFinSelection)`
				
				- `init()`
	
	- `private void init()`
		
		- Elle initialise la fenêtre.
		
		- Elle initialise l'affichage de la zone de texte par la méthode `setLabelProduit()`.
		
		- Elle gère le contenu de la fenêtre par la méthode `zoneMouvante()`.
		
		- Elle gère les événements des boutons `Retour`, `Suite` et `Annuler` par l'intermédiaire des classes suivantes : `ClicRetour()` (interne), `ClicSuivant()` (interne) et `FenetreFermeture()` (externe).
	
	- `private JPanel zoneMouvante()`
		
		- Elle gère le contenu de la fenêtre *via* les méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getLabelProduit()`, `getAnneeDebut()` et `getAnneeFin()`.
		
		- Elle appelle les classes externes `BoiteACocherDateDebut()` et `BoiteACocherDateFin()`.
		
		- Elle gère l'événement du bouton `Choisir les territoires` par l'intermédiaire de la classe interne : `ClicProduit()`.
	
	- *getters* et *setters*
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public String[] getEnregistrementProduit()`
		
		- `private void setEnregistrementProduit(String[] enregistrementProduit)`
		
		- `public String getLabelProduit()`
		
		- `private void setLabelProduit()`
		
			- Elle a besoin de `getEnregistrementProduit()` et de `divisible(i)`.
		
		- `private boolean divisible(int test)`
			
			- Elle teste la divisibilité par 9 pour changer de ligne tous les neuf éléments dans le `JTextArea`.
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`

- Classes internes :
	
	- `ClicRetour` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Retour`. Elle renvoie à l'objet `FenetreEtudeProduit(getChoixDesClassifications(), getChoixDesNiveaux())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Suite`. Elle envoie les données choisies à l'objet `FenetreEtudeProduit3(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementProduit(), CompareAnneeDebut.getAnneeDebut(),CompareAnneeFin.getAnneeFin())`
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicProduit` implémente l'interface `ActionListener`. La classe gère le clic sur le bouton `Choisir des territoires`. Elle envoie les données choisies à l'objet `BoiteACocherProduit3(getChoixDesClassifications(), getChoixDesNiveaux(), getAnneeDebut(), getAnneeFin())`
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`

### `BoiteACocherProduit3`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `BaseAttributaireProduit baseAttributaireProduit`
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `int anneeDebut`
	
	- `int anneeFin`
	
	- `String[] listeHS`
	
	- `String[] nomHS`
	
	- `String[] listeNiveau`
	
	- `String[] nomNiveau`
	
	- `int[] niveau`
	
	- `int choixDesNiveauxInt`
	
	- `String[] codeSection`
	
	- `String[] code2Digit`
	
	- `String[] code4Digit`
	
	- `int[] coupureTab`
	
	- `int[] coupureTab2`
	
	- `String[] code6Digit`
	
	- `int LongueurData`
	
	- `String[] titreData`
	
	- `String[] codeSectionReference`
	
	- `String[] nameSectionReference`
	
	- `String[] nomSectionReference`
	
	- `String[] code2DigitRefrence`
	
	- `String[] name2DigitReference`
	
	- `String[] nom2DigitReference`
	
	- `String[] code4DigitReference`
	
	- `String[] name4DigitReference`
	
	- `String[] nom4DigitReference`
	
	- `String[] code6DigitReference`
	
	- `String[] name6DigitReference`
	
	- `String[] nom6DigitReference`
	
	- `JCheckBox option`
	
	- `JCheckBox[] casesACocher`
	
	- `String[] liste`
	
	- `JButton boutonOK`
	
	- `String[] enregistrementProduit`

- Méthodes :
	
	- Constructeur : `public BoiteACocherProduits3(String text, String text2, int anneeDebut, int anneeFin)`
		
		- Elle initialise les variables d'instance par les méthodes suivantes :
			
			- `setChoixDesClassifications(text)`
			
			- `setChoixDesNiveaux(text2)`
			
			- `setAnneeDebut(anneeDebut)`
			
			- `setAnneeFin(anneeFin)`
			
			- `setListeHS()`
			
			- `setNomHS()`
			
			- `setListeNiveau()`
			
			- `setNomNiveau()`
			
			- `setNiveau()`
			
			- `donneesProduits()`
			
			- `init()`
		
		- Elle gère la fenêtre. Par la classe interne `Fermeture`, elle précise l'action lors de la fermeture de la fenêtre par la croix.
		
		- Elle gère les événements associés à la case à cocher `Tous` par la classe interne `ActionClassification()` et aux autres cases par la classe interne `ActionClassification2()`.
	
	- `private void init()`
		
		- Elle utilise la méthode `getChoixDesNiveaux()` afin d'obtenir la bonne liste à cocher à sélectionner parmi : `getCasesACocherSection()`, `getCasesACocher2Digit()`, `getCasesACocher4Digit()` et `getCasesACocher6Digit()`.
		
		- Elle gère l'événement du bouton `OK` par l'intermédiaire de la classe interne `ClicBoutonOK`.
	
	- Les cases à cocher affichées dépendent du choix de la classification et du niveau.
		
		- `private JPanel getCasesACocherSection()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()` et `getCodeSectionReference()` pour lister les cases à cocher à afficher.
		
		- `private JPanel getCasesACocher2Digit()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()`, `getCodeSectionReference()`, `getNom2DigitReference()` et `getCode2DigitReference()` pour lister les cases à cocher à afficher.
		
		- `private JPanel getCasesACocher4Digit()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()`, `getCodeSectionReference()`, `getNom2DigitReference()`, `getCode2DigitReference()`, `getNom4DigitReference()` et `getCode4DigitReference()` pour lister les cases à cocher à afficher.
		
		- `private JPanel getCasesACocher6Digit()`
			
			- Elle utilise les méthodes `getLongueurDataProduit()`, `getNomSectionReference()`, `getCodeSectionReference()`, `getNom2DigitReference()`, `getCode2DigitReference()`, `getNom4DigitReference()`, `getCode4DigitReference()`, `getNom6DigitReference()` et `getCode6DigitReference()` pour lister les cases à cocher à afficher.
	
	- `private void donneesProduits()`
		
		- Elle crée un objet `BaseAttributaireProduit(getChoixDesClassifications(), getChoixDesNiveaux())`. Elle charge le tableau complet de la base de données correspondant à la classification retenue qu'il faut filtrer pour obtenir l'affichage souhaité en fonction du niveau retenu.
		
		- En utilisant les choix de l'utilisateur, les méthodes suivantes ont pour objectif de charger la base de données dans les variables de classe :
			
			- `setBaseAttributaireProduit(baseAttributaireProduit.getChoixDesNiveauxInt())`
			
			- `setLongueurDataProduit(baseAttributaireProduit.getLongueurDataProduit())`
			
			- `setTitreData(baseAttributaireProduit.getTitreData())`
			
			- `setCodeSectionReference(baseAttributaireProduit.getCodeSectionReference())`
			
			- `setNameSectionReference(baseAttributaireProduit.getNameSectionReference())`
			
			- `setNomSectionReference(baseAttributaireProduit.getNomSectionReference())`
			
			- `setCode2DigitReference(baseAttributaireProduit.getCode2DigitReference())`
			
			- `setName2DigitReference(baseAttributaireProduit.getName2DigiReference())`
			
			- `setNom2DigitReference(baseAttributaireProduit.getNom2DigiReference())`
			
			- `setCode4DigitReference(baseAttributaireProduit.getCode4DigitReference())`
			
			- `setName4DigitReference(baseAttributaireProduit.getName4DigitReference())`
			
			- `setNom4DigitReference(baseAttributaireProduit.getNom4DigitReference())`
			
			- `setCode6DigitReference(baseAttributaireProduit.getCode6DigitReference())`
			
			- `setName6DigitReference(baseAttributaireProduit.getName6DigitReference())`
			
			- `setNom6DigitReference(baseAttributaireProduit.getNom6DigitReference())`
			
			- `setCodeSection(baseAttributaireProduit.getCodeSection())`
			
			- `setCode2Digit(baseAttributaireProduit.getCode2Digit())`
			
			- `setCode4Digit(baseAttributaireProduit.getCode4Digit())`
			
			- `setCode6Digit(baseAttributaireProduit.getCode6Digit())`
	
	- *getters* et *setters*
		
		- `public BaseAttributaireProduit getBaseAttributaireProduit()`
		
		- `private void setBaseAttributaireProduit(BaseAttributaireProduit base)`
		
		- `public int getLongueurDataProduit()`
		
		- `private void setLongueurDataProduit(int longueurData)`
		
		- `public String[] getTitreData()`
		
		- `private void setTitreData(String[] titreData)`
		
		- `public String[] getCodeSectionReference()`
		
		- `private void setCodeSectionReference(String[] codeSectionReference)`
		
		- `public String[] getNameSectionReference()`
		
		- `private void setNameSectionReference(String[] nameSectionReference)`
		
		- `public String[] getNomSectionReference()`
		
		- `private void setNomSectionReference(String[] nomSectionReference)`
		
		- `public String[] getCode2DigitReference()`
		
		- `private void setCode2DigitRefrence(String[] code2DigitReference)`
		
		- `public String[] getName2DigitReference()`
		
		- `private void setName2DigitReference(String[] name2DigitReference)`
		
		- `public String[] getNom2DigitReference()`
		
		- `private void setNom2DigitReference(String[] nom2DigitReference)`
		
		- `public String[] getCode4DigitRefrence()`
		
		- `private void setCode4DigitReference(String[] code4DigitReference)`
		
		- `public String[] getName4DigitReference()`
		
		- `private void setName4DigitReference(String[] name4DigitReference)`
		
		- `public String[] getNom4DigitReference()`
		
		- `private void setNom4DigitReference(String[] nom4DigitReference)`
		
		- `public String[] getCode6DigitReference()`
		
		- `private void setCode6DigitReference(String[] code6DigitReference)`
		
		- `public String[] getName6DigitReference()`
		
		- `private void setName6DigitReference(String[] name6DigitReference)`
		
		- `public String[] getNom6DigitReference()`
		
		- `private void setNom6DigitReference(String[] nom6DigitReference)`
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public int getChoixDesNiveauxInt()`
		
		- `private void setChoixDesNiveauxInt(int nomDuNiveau)`
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`
		
		- `public String[] getCodeSection()`
		
		- `private void setCodeSection(String codeSection)`
		
		- `public String[] getCode2Digit()`
		
		- `private void setCode2Digit(String[] code2Digit)`
		
		- `public String[] getCode4Digit()`
		
		- `private void setCode4Digit(String[] code4Digit)`
		
		- `public String[] getCode6Digit()`
		
		- `private void setCode6Digit(String[] code6Digit)`
		
		- `public String[] getEnregistrementProduit()`
		
		- `private void setEnregistrementProduit(String[] enregistrementProduit)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getNomHS()`
		
		- `private void setNomHS()`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`
		
		- `public String[] getNomNiveau()`
		
		- `private void setNomNiveau()`
		
		- `public int[] getNiveau()`
		
		- `private void setNiveau()`
		
		- `private void toutCocher()`
		
		- `private void cocherOption()`
		
		- `private void enregistrementCasesCocheesSection()`
		
		- `private void enregistrementCasesCochees2Digit()`
		
		- `private void enregistrementCasesCochees4Digit()`
		
		- `private void enregistrementCasesCochees6Digit()`

- Classes internes :
	
	- `ActionClassification()` implémente l'interface `ItemListener`. La classe gère le clic sur la case à cocher `Tous`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche `toutCocher()`.
	
	- `ActionClassification2()` implémente l'interface `ItemListener`. La classe gère les clics des cases à cocher, à l'exception de la case `Tous`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
				
				- Elle déclenche sous condition `toutCocher()` si toutes les cases sont cochées, ou `cocherOption()` si quelques cases sont cochées.
				
				- En fonction de `getChoixDesNiveauxInt()`, elle déclenche `enregistrementCasesCocheesSection()`, `enregistrementCasesCochees2Digit()`, `enregistrementCasesCochees4Digit()` et `enregistrementCasesCochees6Digit()`.
	
	- `ClicBoutonOK` implémente l'interface `ActionListener`. La classe gère le retour des données vers la `FenetreEtudeProduit2(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementProduit(), CompareAnneeDebut.getAnneeDebut(), CompareAnneeFin.getAnneeFin())`.
	
	- `Fermeture` implémente `WindowListener`. La classe gère le clic sur la croix de la fenêtre `BoiteACocherTerritoire`. La classe gère le retour des données vers la `FenetreEtudeProduit2(getChoixDesClassifications(), getChoixDesNiveaux(), getAnneeDebut(), getAnneeFin())`. Elle annule l'éventuelle sélection des cases à cocher.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void windowClosing(WindowEvent e)`

### `FenetreEtudeProduit3`

C'est un objet qui hérite de `JFrame`.

- Variables d'instance :
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `int choixDesNiveauxInt`
	
	- `int anneeDebut`
	
	- `int anneeFin`
	
	- `int[] annee`
	
	- `String[] codeProduit`
	
	- `String[] listeHS`
	
	- `String[] nomHS`
	
	- `String[] nomDossierHS`
	
	- `String[] listeNiveau`
	
	- `String[] nomNiveau`
	
	- `int[] niveau`
	
	- `String[] sousDossierPartie1`
	
	- `String[] sousDossierPartie2`
	
	- `String dossier`
	
	- `String sousDossierP1`
	
	- `String sousDossierP2`
	
	- `String sousDossierP3`
	
	- `String sousSousDossier = "Bd-Produit\\"`
	
	- `BaseAttributaireProduit baseAttributaireProduit`
	
	- `String[] baseAttributaireProduitCodeProduit`
	
	- `String[] baseAttributaireProduitNomProduit`
	
	- `String[] baseAttributaireProduitNameProduct`
	
	- `String labelProduit`
	
	- `Container contentPane`
	
	- `JPanel panel3`
	
	- `JButton boutonRetour`
	
	- `JButton boutonSuite`
	
	- `JButton boutonAnnuler`
	
	- `JButton boutonAffichageDonnees`
	
	- `String[] nomFichierALire`
	
	- `String titreExtraction`
	
	- `String donneesExtraction`
	
	- `String[] titreExtractionTableau`
	
	- `String[][] donneesExtractionTableau`

- Méthodes :
	
	- Constructeur : `FenetreEtudeProduit3(String text1, String text2, String[] text3, int anneeDebut, int anneeFin)`
		
		- Elle permet d'initialiser les variables d'instance permettant d'initialiser les autres variables d'instance par les méthodes :
			
			- `setChoixDesClassifications(text1)`
			
			- `setChoixDesNiveaux(text2)`
			
			- `setProduit(text3)`
			
			- `setAnneeDebut(anneeDebut)`
			
			- `setAnneeFin(anneeFin)`
			
			- `init()`
		
		- Elle contrôle les étapes de la lecture des fichiers de la base de données retenue par l'utilisateur par les méthodes :
			
			- `chargementFichierALire()`
			
			- `lectureBaseDeDonnees()`
		
		- Elle récupère les informations choisies par l'utilisateur grâce à la méthode : `setLabelProduit()`.
		
		- Elle gère l'affichage de la fenêtre avec la méthode `zoneMouvante()`.
		
		- Elle gère les événements des boutons `Retour`, `Enregistrement` et `Annuler` par l'intermédiaire des classes suivantes respectives : `ClicRetour` (interne), `ClicSuivant` (interne) et `ClicAnnuler` (externe).
	
	- `private JPanel zoneMouvante()`
		
		- Elle gère l'affichage des données complètes de l'extraction en utilisant les méthodes : `getChoixDesClassifications()`, `getChoixDesNiveaux()` et `getLabelProduit()`.
		
		- Elle gère l'événement du bouton `Afficher les données` par l'appel à la classe interne `BoutonAffichageDonnees()`.
	
	- `private void tableauExtrait()`
		
		- Elle affiche les données sous la forme d'un `JTable`.
		
		- Elle utilise les méthodes : `getDonneesExtraction()` et `getTitreExtraction()`.
	
	- `public getLabelProduit()`
		
		- Elle gère l'affiche du `JTextArea` affichant les choix de l'utilisateur concernant les produits.
	
	- `private void setLabelProduit()`
		
		- Elle crée le tableau complet des données concernant les produits choisies par l'utilisateur en utilisant les méthodes : `getProduit()`, `getBaseAttributaireProduitCodeProduit()` et `getBaseAttributaireNomProduit()`.
	
	- `private void init()`
		
		- Elle initialise les variables d'instance par les méthodes : `setListeHS()`, `setNomHS()`, `setNomDossierHS()`, `setListeNiveau()`, `setSousDossierPartie1()`, `setSousDossierPartie2()`, `setDossier(dossier)`, `setSousDossier1(sousDossier1)`, `setSousDossier2(sousDossier2)`, `setSousDossier3(sousDossier3)`, `setChoixDesNiveauxInt(choixDesNiveauxInt)` et `setAnnee()`. Pour ce, l'ordre des initialisations est très important, puisque les méthodes utilisent les variables enregistrées *via* `getListeHS()`, `getNomHS()`, `getNomDossier()`, `getListeNiveau()`, `getNiveau()`, `getSousDossierPartie1()`, `getSousDossierPartie2()`, `getAnneeDebut()` et `getAnneeFin()`.
	
	- `private void donneesProduits()`
		
		- En appelant l'objet `BaseAttributaireProduit`, elle charge la base de données des territoires *via* la méthode `setBaseAttributaireProduit(…)`.
		
		- En utilisant `getChoixDesNiveauxInt()`, les méthodes `setBaseAttributaireProduitCodeProduit(…)` et `setBaseAttributaireProduitNomProduit(…)` chargent les données choisies par l'utilisateur.
	
	- `private void chargementFichierALire()`
		
		- Elle initialise la liste des fichiers à lire en fonction des choix de l'utilisateur par l'intermédiaire des méthodes : `getDossier()`, `getSousDossierP1()`, `getSousDossier2()`, `getSousDossier3()`, `getAnnee()` et la variable de classe : `sousSousDossier`, la méthode `setNomFichierALire(nomFichierALire)`.
	
	- `private void lectureBaseDeDonnees()`
		
		- Grâce à la méthode `getNomFichierALire()`, on peut initialiser un objet `LectureCSV`. On utilise ses méthodes `getTitreTableau()`, `getDonneesTableau()` et `getNombreDeColonne()` pour initialiser la lecture.
		
		- Le code insère la colonne `Nom du territoire` avec les noms complets des territoires et ajoute dans la colonne `Nom des produits` les noms complets.
		
		- Elle permet d'initialiser les méthodes `setTitreExtraction(inittitre)`, `setTitreExtractionTableau(titre2)`, `setDonneesExtraction(initSortie)` et `setDonneesExtractionTableau(tabExtraction, numColonne + 1)` en utilisant `getProduit()`, `getBaseAttributaireProduitCodeProduit()` et `getBaseAttributaireProduitNomProduit()`.
	
	- `private void affichageDonneesRecuperees()`
		
		- Elle permet de lire les choix de l'utilisateur sur la console grâce aux méthodes `getChoixDesClassifications()`, `getChoixDesNiveaux()`, `getChoixDesNiveauxInt()`, `getAnneeDebut()`, `getAnneeFin()`, `getAnnee()` et `getProduit()`. (point de contrôle pour le programmeur)
	
	- `private void affichageDonneesAttributaires()`
		
		- Elle permet d'afficher les données attributaires sélectionnées par l'utilisateur *via* `getProduit()`, `getBaseAttributaireProduitCodeProduit()` et `getBaseAttributaireProduitNomProduit()`. (point de contrôle pour le programmeur)
	
	- `private void affichageFichiersALire()`
		
		- Elle permet d'afficher sur la console la liste des fichiers à lire pour l'extraction des données choisies par l'utilisateur. (point de contrôle pour le programmeur)
	
	- *getters* et *setters* :
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int anneeDebut)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int anneeFin)`
		
		- `public int[] getAnnee()`
		
		- `private void setAnnee(int[] annee)`
		
		- `public String[] getProduit()`
		
		- `private void setProduit(String[] produit)`
		
		- `public int getChoixDesNiveauxInt()`
		
		- `private void setChoixDesNiveauxInt(int nomDuNiveau)`
		
		- `public String getDossier()`
		
		- `private void setDossier(String dossier)`
		
		- `public String getSousDossierP1()`
		
		- `private void setSousDossierP1(String sousDossierP1)`
		
		- `public String getSousDossierP2()`
		
		- `private void setSousDossierP2(String sousDossierP2)`
		
		- `public String getSousDossierP3()`
		
		- `private void setSousDossierP3(String sousDossierP3)`
		
		- `public BaseAttributaireProduit getBaseAttributaireProduit()`
		
		- `private void setBaseAttributaireProduit(BaseAttributaireProduit baseProduit)`
		
		- `public String[] getBaseAttributaireProduitCodeProduit()`
		
		- `private void setBaseAttributaireProduitCodeProduit(String[] code)`
		
		- `public String[] getBaseAttributaireProduitNomProduit()`
		
		- `private void setBaseAttributaireProduitNomProduit (String[] nomProduit)`
		
		- `public String[] getBaseAttributaireProduitNameProduct()`
		
		- `private void setBaseAttributaireProduitNameProduct (String[] nameProduct)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getNomHS()`
		
		- `private void setNomHS()`
		
		- `public String[] getNomDossierHS()`
		
		- `private void setNomDossierHS()`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`
		
		- `public int[] getNiveau()`
		
		- `private void setNiveau()`
		
		- `public String[] getSousDossierPartie1()`
		
		- `private void setSousDossierPartie1()`
		
		- `public String[] getSousDossierPartie2()`
		
		- `private void setSousDossierPartie2()`
		
		- `public String[] getNomFichierALire()`
		
		- `private void setNomFichierALire(String[] table)`
		
		- `public String getTitreExtraction()`
		
		- `private void setTitreExtraction(String table)`
		
		- `public String getDonneesExtraction()`
		
		- `private void setDonneesExtraction(String table)`
		
		- `public String[] getTitreExtractionTableau()`
		
		- `private void setTitreExtractionTableau(String[] table)`
		
		- `public String[] getDonneesExtractionTableau()`
		
		- `private void setDonneesExtractionTableau(String[] tabExtraction, int numColonne)`

- Classes internes :
	
	- `ClicRetour` implémente `ActionListener`. La classe gère le retour vers `FenetreEtudeProduit2(getChoixDesClassifications(), getChoixDesNiveaux(), getProduit(), getAnneeDebut(), getAnneeFin())`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void actionPerformed(ActionEvent e)`
	
	- `ClicSuivant` implemente `ActionListener`. La classe enregistre les données choisies par l'utilisateur sous la forme d'un fichier `*.csv` en appelant la méthode `ecrire()` qui a besoin de `getChoixDesClassifications()` et `getChoixDesNiveaux()`. Elle renvoie vers l'objet `FenetreChoixEtude()`.
	
	- `private void ecrire()`
		
		- Elle enregistre l'extraction sous la forme d'un nom unique en utilisant `getDossier()` et un objet `DateMaintenant` qui capture la date au moment du clic sur le bouton de sauvegarde grâce à sa méthode `getDateMaintenant()` qui renvoie une chaîne de caractères.
		
		- Le nom choisi, elle a besoin des méthodes `getTitreExtractionTableau()` et `getDonneesExtractionTableau()` pour enregistrer l'extraction voulue par l'utilisateur.
	
	- `private void messageDeFermeture()`
		
		- Elle ouvre une nouvelle fenêtre en fonction du choix de l'utilisateur : s'il confirme l'annulation, l'objet `FenetreChoixEtude()` est appelé ; s'il ne souhaite plus annuler, on reste sur l'objet `FenetreEtudeProduit3`.
	
	- `ClicAnnuler` implémente `ActionListener`. La classe renvoie à la méthode `messageDeFermeture()`.
		
		- `public void actionPerformed(ActionEvent e)`
	
	- `BoutonAffichageDonnees` implémente `ActionListener`. Elle permet d'afficher le `JTable` en appelant la méthode `tableauExtrait()`.
		
		- `public void actionPerformed(ActionEvent e)`
	
	- `Fermeture` implémente `WindowListener`. La classe gère le clic sur la croix de la fenêtre `FenetreEtudeTerritoriale4`. La classe ouvre une boîte de dialogue de confirmation ou annulation *via* la méthode `messageDeFermeture()`.
		
		- Variables d'instance : aucune
		
		- Méthodes :
			
			- `public void windowClosing(WindowEvent e)`

### `BoiteACocherClassification`

Contrairement à ce que son nom indique, l'objet est une liste de choix unique. Il est utilisé par les classes suivantes : `FenetreEtudeTerritoriale()`, `FenetreEtudeFlux()` et `FenetreEtudeProduit()`. Il implémente l'interface `ItemListener`.

- Variables d'instance :
	
	- `Choice listeDesClassifications`
	
	- `String choixDesClassifications`
	
	- `String[] listeHS`
	
	- `int selection`

- Méthodes :
	
	- Constructeurs :
		
		- `public BoiteACocherClassification()`
			
			- Elle correspond au choix par défaut.
			
			- Elle appelle `init()`.
		
		- `public BoiteACocherClassification(int classification)`
			
			- Elle correspond à un choix de l'utilisateur.
			
			- Elle initialise son choix par `setSelection(classification)`.
			
			- Elle appelle `init()`.
	
	- `private void init()`
		
		- Elle initialise la variable de classe `listeHS` par `setListeHS()` qui transforme l'énumération `ListeHS` en tableau
		
		- Elle utilise les variables de classe enregistrée *via* `getListeHS()` et `getSelection()`.
		
		- Elle gère l'événement de la liste.
	
	- `public JPanel getPanel()`
		
		- Elle permet de récupérer le widget de la liste de choix dans l'objet d'appel afin de l'afficher.
	
	- *getters* et *setters*
		
		- `public in getSelection()`
		
		- `private void setSelection(int valeur)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String text)`
	
	- `public void itemStateChanged(ItemEvent e)`
		
		- Elle reinitialise le choix des classifications `setChoixDesClassifications(listeDesClassifications.getSelectionItem())`

### `BoiteACocherNiveau`

Contrairement à ce que son nom indique, l'objet est une liste de choix unique. Il est utilisé par les classes suivantes : `FenetreEtudeTerritoriale()`, `FenetreEtudeFlux()` et `FenetreEtudeProduit()`. Elle implémente l'interface `ItemListener`.

- Variables d'instance :
	
	- `Choice listeDesNiveaux`
	
	- `String choixDesNiveaux`
	
	- `String[] listeNiveau`
	
	- `int selection`

- Méthodes :
	
	- Constructeurs :
		
		- `public BoiteACocherNiveau()`
			
			- Elle correspond au choix par défaut.
			
			- Elle appelle `init()`.
		
		- `public BoiteACocherNiveau(int niveau)`
			
			- Elle correspond à un choix de l'utilisateur.
			
			- Elle initialise son choix par `setSelection(niveau)`.
			
			- Elle appelle `init()`.
	
	- `private void init()`
		
		- Elle initialise la variable de classe `listeNiveau` par `setListeNiveau()` qui transforme l'énumération `ListeNiveau` en tableau
		
		- Elle utilise les variables de classe enregistrée *via* `getListeNiveau()` et `getSelection()`.
		
		- Elle gère l'événement de la liste.
	
	- `public JPanel getPanel()`
		
		- Elle permet de récupérer le widget de la liste de choix dans l'objet d'appel afin de l'afficher.
	
	- *getters* et *setters*
		
		- `public in getSelection()`
		
		- `private void setSelection(int valeur)`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String text)`
	
	- `public void itemStateChanged(ItemEvent e)`
		
		- Elle reinitialise le choix des classifications `setChoixDesClassifications(listeDesNiveaux.getSelectionItem())`

### `BoiteACocherDateDebut`

Contrairement à ce que son nom indique, l'objet est une liste de choix unique. Il est utilisé par les classes suivantes : `FenetreEtudeTerritoriale2()`, `FenetreEtudeFlux2()` et `FenetreEtudeProduit2()`. Elle implémente l'interface `ItemListener`.

- Variables d'instance :
	
	- `String choixDesClassifications`
	
	- `int choixDeLaDateDeDebut`
	
	- `Choice listeDateDebut`
	
	- `int anneeDebut`
	
	- `String[] listeHS`
	
	- `int[] listeHSAnneeDebut`
	
	- `int  anneeFin`
	
	- `int[] listeHSAnneeFin`
	
	- `int anneeDebutSelection`

- Méthodes :
	
	- Constructeurs :
		
		- public BoiteACocherDateDebut(String text)`
			
			- Elle initialise les variables provenant de `FenetreEtudeTerritoriale()` par les méthodes suivantes :
				
				- `setChoixDesClassifications(text)`
				
				- `setListeHS()`
				
				- `setListeHSAnneeDebut()`
				
				- `setListeHSAnneeFin()`
				
				- `setAnneeDebut(getListeHSAnneeDebut()[i])`
				
				- `setAnneeFin(getListeHSAnneeFin()[i])`
				
				- `init()`
		
		- `public BoiteACocherDateDebut(String text, int anneeDebut)`
			
			- Elle initialise les variables provenant de `FenetreEtudeTerritoriale3()` par les méthodes suivantes :
				
				- `setChoixDesClassifications(text)`
				
				- `setListeHS()`
				
				- `setListeHSAnneeDebut()`
				
				- `setListeHSAnneeFin()`
				
				- `setAnneeDebut(getListeHSAnneeDebut()[i])`
				
				- `setAnneeFin(getListeHSAnneeFin()[i])`
				
				- `setAnneeDebutSelection(positionAnneeDebut)`
				
				- `init()`
	
	- `private void init()`
		
		- Elle initialise la liste de choix unique en récupérant les données correspondant à la classification choisie.
		
		- Elle utilise les méthodes `getAnneeDebut()`, `getAnneeFin()` et `getAnneeDebutSelection()`
		
		- Elle crée un objet `CompareAnneeDebut(getAnneeDebut())` vérifiant si le choix de la date est pertinente par rapport à la date de fin.
	
	- `public JPanel getPanel()`
		
		- Elle permet à `FenetreEtudeTerritoriale2` de récupérer la liste non grisée.
	
	- `public JPanel getPanelGris()`
		
		- Elle permet à `FenetreEtudeTerritoriale2` de récupérer la liste grisée.
	
	- *getters* et *setters*
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int annee)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int annee)`
		
		- `public int getAnneeDebutSelection()`
		
		- `private void setAnneeDebutSelection(int selection)`
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public int[] getListeHSAnneeDebut()`
		
		- `private void setListeHSAnneeDebut()`
		
		- `public  int[] getListeHSAnneeFin()`
		
		- `private void setListeHSAnneeFin()`
		
		- `public int getChoixDeLaDateDeDebut()`
		
		- `private void setChoixDeLaDateDeDebut(int annee)`
	
	- `public void itemStateChanged(ItemEvent e)`
		
		- Elle  initialise le choix de la date de début par la méthode `setChoixDeLaDateDeDebut(…)`.
		
		- Elle initialise l'objet `CompareAnneeDebut(getChoixDeLaDateDeDebut())`.
		
		- Elle compare les dates de début et de fin en initialisant l'objet `CompareAnnee()`.

### `BoiteACocherDateFin`

Contrairement à ce que son nom indique, l'objet est une liste de choix unique. Elle implémente l'interface `ItemListener`.

- Variables d'instance :
	
	- `String choixDesClassifications`
	
	- `int anneeDebut`
	
	- `String[] listeHS`
	
	- `int[] listeHSAnneeDebut`
	
	- `int anneeFin`
	
	- `int[] listeHSAnneeFin`
	
	- `Choice listeDateFin`
	
	- `int choixDeLaDateDeFin`
	
	- `int anneeFinSelection`

- Méthodes :
	
	- Constructeurs :
		
		- `public BoiteACocherDateFin(String text)`
			
			- Elle initialise les variables provenant de `FenetreEtudeTerritoriale()` par les méthodes suivantes :
				
				- `setChoixDesClassifications(text)`
				
				- `setListeHS()`
				
				- `setListeHSAnneeDebut()`
				
				- `setListeHSAnneeFin()`
				
				- `setAnneeDebut(getListeHSAnneeDebut()[i])`
				
				- `setAnneeFin(getListeHSAnneeFin()[i])`
				
				- `setAnneeFinSelection(getAnneeFin() – getAnneeDebut())`
				
				- `init()`
		
		- `public BoiteACocherDateFin(String text, int anneeFin)`
			
			- Elle initialise les variables provenant de `FenetreEtudeTerritoriale3()` par les méthodes suivantes :
				
				- `setChoixDesClassifications(text)`
				
				- `setListeHS()`
				
				- `setListeHSAnneeDebut()`
				
				- `setListeHSAnneeFin()`
				
				- `setAnneeDebut(getListeHSAnneeDebut()[i])`
				
				- `setAnneeFin(getListeHSAnneeFin()[i])`
				
				- `setAnneeDebutSelection(positionAnneeFin)`
				
				- `init()`
	
	- `private void init()`
		
		- Elle initialise la liste de choix unique en récupérant les données correspondant à la classification choisie.
		
		- Elle utilise les méthodes `getAnneeDebut()`, `getAnneeFin()` et `getAnneeDebutSelection()`
		
		- Elle crée un objet `CompareAnneeFin(getAnneeFin())` vérifiant si le choix de la date est pertinente par rapport à la date de début.
	
	- `public JPanel getPanel()`
		
		- Elle permet à `FenetreEtudeTerritoriale2` de récupérer la liste non grisée.
	
	- `public JPanel getPanelGris()`
		
		- Elle permet à `FenetreEtudeTerritoriale2` de récupérer la liste grisée.
	
	- *getters* et *setters*
		
		- `public int getAnneeDebut()`
		
		- `private void setAnneeDebut(int annee)`
		
		- `public int getAnneeFin()`
		
		- `private void setAnneeFin(int annee)`
		
		- `public int getAnneeDebutSelection()`
		
		- `private void setAnneeDebutSelection(int selection)`
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public int[] getListeHSAnneeDebut()`
		
		- `private void setListeHSAnneeDebut()`
		
		- `public  int[] getListeHSAnneeFin()`
		
		- `private void setListeHSAnneeFin()`
		
		- `public int getChoixDeLaDateDeFin()`
		
		- `private void setChoixDeLaDateDeFin(int annee)`
	
	- `public void itemStateChanged(ItemEvent e)`
		
		- Elle  initialise le choix de la date de fin par la méthode `setChoixDeLaDateDeFin(…)`.
		
		- Elle initialise l'objet `CompareAnneeFin(getChoixDeLaDateDeFin())`.
		
		- Elle compare les dates de début et de fin en initialisant l'objet `CompareAnnee()`.

### `CompareAnneeDebut`

La classe récupère l'année de début choisie et en fait une variable statistique qui sera récupérée par l'objet `CompareAnnee`.

- Variable d'instance : `static int anneeDebut`

- Méthodes :
	
	- Constructeur : `public CompareAnneeDebut(int annee)`
	
	- `public static int getAnneeDebut()`

### `CompareAnneeFin`

La classe récupère l'année de fin choisie et en fait une variable statistique qui sera récupérée par l'objet `CompareAnnee`.

- Variable d'instance : `static int anneeFin`

- Méthodes :
	
	- Constructeur : `public CompareAnneeFin(int annee)`
	
	- `public static int getAnneeFin()`

### `CompareAnnee`

La classe hérite de `JFrame`. Elle compare les deux variables statistiques et affiche si besoin est une fenêtre d'erreur.

- Variables d'instance :
	
	- `int anneeDebut = CompareAnneeDebut.getAnneeDebut()`
	
	- `int anneeFin = CompareAnneeFin.getAnneeFin()`

- Méthodes :
	
	- Constructeur : `public CompareAnnee()`
		
		- Elle affiche un message d'erreur si l'ordre des dates est mauvais `(anneeDebut <= anneeFin)`.

### `BaseAttributaireTerritoire`

La classe ouvre la base de données des attributs des territoires en fonction de la classification retenue. Il est utilisé par les classes suivantes : `FenetreEtudeTerritoriale2()`, `FenetreEtudeFlux2()` et `FenetreEtudeProduit2()`.

- Variables d'instance :
	
	- `String choix DesClassifications`
	
	- `String[] listeHS`
	
	- `String[] fichierSyntheseListeHS`
	
	- `int longueurData`
	
	- `String[] titreData`
	
	- `String[] codeISO`
	
	- `String[] englishName`
	
	- `String[] nomFrancais`
	
	- `String[] nomContinent`
	
	- `String[] borneAnneeDebut`
	
	- `String[] borneAnneeFin`

- Méthodes :
	
	- Constructeur : `public BaseAttributaireTerritoire(String text 1)`
		
		- Elle initialise les données par les méthodes : `setChoixDesClassifications(text1)`, `setListeHS()`, `setFichierSyntheseListeHS()` et `donneesTerritoires()`
	
	- `private void donneesTerritoires()`
		
		- Elle se sert de `getChoixDesClassifications()` et `getFichierSyntheseListeHS()` pour lire le fichier de synthèse sur les territoires disponibles.
		
		- Elle appelle l'objet `LectureCSV` et récupère les données *via* `getDonneesTableau()` et `getTitreTableau()`.
		
		- Elle initialise les variables de classe par les méthodes :
			
			- `setLongueurDataTerritoire(longueurData)`
			
			- `setTitreData(titre)`
			
			- `setCodeISO(codeISO)`
			
			- `setEnglishName(englishName)`
			
			- `setNomFrancais(nomFrancais)`
			
			- `setNomContinent(nomContinent)`
			
			- `setBorneAnneeDebut(borneAnneeDebut)`
			
			- `setBorneAnneeFin(borneAnneeFin)`
	
	- `public void affichageTableau()`
		
		- Elle affiche le tableau de données sur la console en utilisant `getCode()`, `getEnglishName()`, `getNomFrancais()` et `getNomContinent()`
	
	- `public String affichageFenetreTableauCSV()`
		
		- Elle affiche sur la console le tableau en format CSV en utilisant `getCodeISO()`, `getEnglishName()`, `getNomFrancais()` et `getNomContinent()`.
	
	- `public void affichageFenetreTableau()`
		
		- Elle affiche le tableau sous la forme d'un `JTable` en utilisant `getCodeISO()`, `getEnglishName()`, `getNomFrancais()`, `getNomContinent()` et `getTitreData()`.
	
	- *getters* et *setters* :
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications (String nomClassification)`
		
		- `public int getLongueurDataTerritoire()`
		
		- `private void setLongueurDataTerritoire(int longueurData)`
		
		- `public String[] getTitreData()`
		
		- `private void setTitreData(String[] titreData)`
		
		- `public String[] getCodeISO()`
		
		- `private void setCodeISO(String[] codeISO)`
		
		- `public String[] getEnglishName()`
		
		- `private void setEnglishName(String[] englishName)`
		
		- `public String[] getNomFrancais()`
		
		- `private void setNomFrancais(String[] nomFrancais)`
		
		- `public String[] getNomContinent()`
		
		- `private void setNomContinent(String[] nomContinent)`
		
		- `public String[] getBorneAnneeDebut()`
		
		- `private void setBorneAnneeDebut(String[] borneAnneeDebut)`
		
		- `public String[] getBorneAnneeFin()`
		
		- `private void setBorneAnneeFin(String[] borneAnneeFin)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getFichierSyntheseListeHS()`
		
		- `private void setFichierSyntheseListeHS()`

### `BaseAttributaireProduit`

La classe ouvre la base de données des attributs des territoires en fonction de la classification et du niveau retenus.

- Variables d'instance :
	
	- `String choixDesClassifications`
	
	- `String choixDesNiveaux`
	
	- `String[] listeHS`
	
	- `String[] nomHS`
	
	- `String[] listeNiveau`
	
	- `String[] nomNiveau`
	
	- `int[] niveau`
	
	- `int choixDesNiveauxInt`
	
	- `int longueurData`
	
	- `String[] titreData`
	
	- `String[] codeSectionReference`
	
	- `String[] nameSectionReference`
	
	- `String[] nomSectionReference`
	
	- `String[] code2DigitReference`
	
	- `String[] name2DigitReference`
	
	- `String[] nom2DigitReference`
	
	- `String[] code4DigitReference`
	
	- `String[] name4DigitReference`
	
	- `String[] nom4DigitReference`
	
	- `String[] code6DigitReference`
	
	- `String[] name6DigitReference`
	
	- `String[] nom6DigitReference`
	
	- `String[] codeSection`
	
	- `String[] nameSection`
	
	- `String[] nomSection`
	
	- `String[] code2Digit`
	
	- `String[] name2Digit`
	
	- `String[] nom2Digit`
	
	- `String[] code4Digit`
	
	- `String[] name4Digit`
	
	- `String[] nom4Digit`
	
	- `String[] code6Digit`
	
	- `String[] nom6Digit`
	
	- `String[] name6Digit`

- Méthodes :
	
	- Constructeur : `public BaseAttributaireProduit(String text1, String text2)`
		
		- Elle ouvre la base de données attributaires des produits à partir du choix de la classification et du niveau de l'utilisateur. Pour ce, elle charge les variables d'instance par les méthodes suivantes :
			
			- `setChoixDesClassifications(text1)`
			
			- `setChoixDesNiveaux(text2)`
			
			- `setListeHS()`
			
			- `setNomHS()`
			
			- `setListeNiveau()`
			
			- ``setNomNiveau()`
			
			- `setNiveau()`

ce qui permet d'utiliser `donneesProduits()` pour charger la base correspondant aux choix de l'utilisateur.
	
	- `private void donneesProduits()`
		
		- Afin d'utiliser l'objet `LectureCSV`, il est nécessaire d'appeler les méthodes suivantes : `getListeHS()`, `getChoixDesClassifications()`, `getNomHS()`, `getListeNiveau()`, `getChoixDesNiveaux()` et `getNomNiveau()`. Ils permettent également d'instancier la variable de classe `choixDesNiveauxInt` *via* la méthode `setChoixDesNiveauxInt(getNiveau()[posMax])`.
		
		- L'objet `LectureCSV` appelle ses méthodes propres : `getDonneesTableau()` et `getTitreTableau()`.
		
		- La base de données lue, il est possible de charger les autres variables d'instance :
			
			- `setLongueurDataProduit(longueurData)`
			
			- `setTitreData(titre)`
			
			- `setCodeSectionReference(codeSectionRef2)`
			
			- `setNameSectionReference(nameSectionRef2)`
			
			- `setNomSectionReference(nomSectionRef2)`
			
			- `setCode2DigitReference(code2DigitRef2)`
			
			- `setName2DigitReference(name2DigitRef2)`
			
			- `setNom2DigitReference(nom2DigitRef2)`
			
			- `setCode4DigitReference(code4DigitRef2)`
			
			- `setName4DigitReference(name4DigitRef2)`
			
			- `setNom4DigitReference(nom4DigitRef2)`
			
			- `setCode6DigitReference(code6DigitRef2)`
			
			- `setName6DigitReference(name6DigitRef2)`
			
			- `setNom6DigitReference(nome6DigitRef2)`
			
			- `setCodeSection(transformationTableau(getCodeSectionReference()))`
			
			- `setNameSection(transformationTableau(getNameSectionReference()))`
			
			- `setNomSection(transformationTableau(getNomSectionReference()))`
			
			- `setCode2Digit(transformationTableau(getCode2DigitReference()))`
			
			- `setName2Digit(transformationTableau(getName2DigitReference()))`
			
			- `setNom2Digit(transformationTableau(getNom2DigitReference()))`
			
			- `setCode4Digit(transformationTableau(code4DigitReference()))`
			
			- `setName4Digit(transformationTableau(name4DigitReference()))`
			
			- `setNom4Digit(transformationTableau(nom4DigitReference()))`
			
			- `setNom4Digit(transformationTableau(nom4DigitReference()))`
			
			- `setCode6Digit(transformationTableau(code6DigitReference()))`
			
			- `setName6Digit(transformationTableau(name6DigitReference()))`
			
			- `setNom6Digit(transformationTableau(nom6DigitReference()))`
		
		- `private String suppressionVirgule(String data)`
			
			- Elle supprime les virgules non supprimées par `LectureCSV.`
		
		- `private String[] transformationTableau(String[] table)`
			
			- Elle supprime les lignes en double d'un tableau en fonction du choix du niveau par l'utilisateur.
		
		- `private int[] positionTransformationTableau(String[] table)`
			
			- Elle est utilisée par la méthode `affichageFenetreTableau()`. Elle permet de repérer les positions des lignes conservées dans le tableau.
	
	- Affichage du tableau sur la console et par un `JTable`
		
		- `public void affichageTableau()`
			
			- Elle a besoin des méthodes `getCodeSectionReference()`, `getNameSectionReference()`, `getNomSectionReference()`, `getCode2DigitReference()`, `getNom2DigitReference()`, `getCode4DigitReference()`, `getName4DigitReference()`, `getNom4DigitReference()`, `getCode6DigitReference()`, `getName6DigitReference()` et `getNom6DigitReference()`.
		
		- `public String affichageFenetreTableauCSV()`
			
			- Elle a besoin des méthodes `getCodeSectionReference()`, `getNameSectionReference()`, `getNomSectionReference()`, `getCode2DigitReference()`, `getNom2DigitReference()`, `getCode4DigitReference()`, `getName4DigitReference()`, `getNom4DigitReference()`, `getCode6DigitReference()`, getName6DigitReference() et `getNom6DigitReference()`.
		
		- `public void affichageFenetreTableau()`
			
			- Elle a besoin des méthodes `getCodeSectionReference()`, `getNameSectionReference()`, `getNomSectionReference()`, `getCode2DigitReference()`, `getNom2DigitReference()`, `getCode4DigitReference()`, `getName4DigitReference()`, `getNom4DigitReference()`, `getCode6DigitReference()`, `getName6DigitReference()` et `getNom6DigitReference()`.
		
		- `public void affichageFenetreTableauSimpliee()`
			
			- Elle a besoin des méthodes `getTitreData()`, `getChoixDesNiveauxInt()`, `positionTransformationTableau()`, `getCodeSection()`, `getCodeSectionReference()`, `getNameSectionReference()`, `getNomSectionReference()`, `getCode2Digit()`, `getCode2DigitReference()`, `getCode2DigitReference()`, `getName2DigitReference()`, `getNom2DigitReference()`, `getCode4Digit()`, `getCode4DigitReference()`, `getName4DigitReference()`, `getNom4DigitReference()`, `getCode6Digit()`, `getCode6DigitReference()`, `getName6DigitReference()` et `getNom6DigitReference()`.
	
	- *getters* et *setters* :
		
		- `public String getChoixDesClassifications()`
		
		- `private void setChoixDesClassifications(String nomClassification)`
		
		- `public String getChoixDesNiveaux()`
		
		- `private void setChoixDesNiveaux(String nomDuNiveau)`
		
		- `public int getChoixDesNiveauxInt()`
		
		- `private void setChoixDesNiveauxInt(int nomDuNiveau)`
		
		- `public int getLongueurDataProduit()`
		
		- `private void setLongueurDataProduit(int longueurData)`
		
		- `public String[] getTitreData()`
		
		- `private void setTitreData(String[] titreData)`
		
		- `public String[] getCodeSectionReference()`
		
		- `private void setCodeSectionReference(String[] codeSectionReference)`
		
		- `public String[] getNameSectionReference()`
		
		- `private void setNameSectionReference(String[] nameSectionReference)`
		
		- `public String[] getNomSectionReference()`
		
		- `private void setNomSectionReference(String[] nomSectionReference)`
		
		- `public String[] getCode2DigitReference()`
		
		- `private void setCode2DigitReference(String[] code2DigitReference)`
		
		- `public String[] getName2DigitReference()`
		
		- `private void setName2DigitReference(String[] name2DigitReference)`
		
		- `public String[] getNom2DigitReference()`
		
		- `private void setNom2DigitReference(String[] nom2DigitReference)`
		
		- `public String[] getCode4DigitReference()`
		
		- `private void setCode4DigitReference(String[] code4DigitReference)`
		
		- `public String[] getName4DigitReference()`
		
		- `private void setName4DigitReference(String[] name4DigitReference)`
		
		- `public String[] getNom4DigitReference()`
		
		- `private void setNom4DigitReference(String[] nom4DigitReference)`
		
		- `public String[] getCode6DigitReference()`
		
		- `private void setCode6DigitReference(String[] code6DigitReference)`
		
		- `public String[] getName6DigitReference()`
		
		- `private void setName6DigitReference(String[] name6DigitReference)`
		
		- `public String[] getNom6DigitReference()`
		
		- `private void setNom6DigitReference(String[] nom6DigitReference)`
		
		- `public String[] getCodeSection()`
		
		- `private void setCodeSection(String[] codeSection)`
		
		- `public String[] getNameSection()`
		
		- `private void setNameSection(String[] nameSection)`
		
		- `public String[] getNomSection()`
		
		- `private void setNomSection(String[] nomSection)`
		
		- `public String[] getCode2Digit()`
		
		- `private void setCode2Digit(String[] code2Digit)`
		
		- `public String[] getName2Digit()`
		
		- `private void setName2Digit(String[] name2Digit)`
		
		- `public String[] getNom2Digit()`
		
		- `private void setNom2Digit(String[] nom2Digit)`
		
		- `public String[] getCode4Digit()`
		
		- `private void setCode4Digit(String[] code4Digit)`
		
		- `public String[] getName4Digit()`
		
		- `private void setName4Digit(String[] name4Digit)`
		
		- `public String[] getNom4Digit()`
		
		- `private void setNom4Digit(String[] nom4Digit)`
		
		- `public String[] getCode6Digit()`
		
		- `private void setCode6Digit(String[] code6Digit)`
		
		- `public String[] getName6Digit()`
		
		- `private void setName6Digit(String[] name6Digit)`
		
		- `public String[] getNom6Digit()`
		
		- `private void setNom6Digit(String[] nom6Digit)`
		
		- `public String[] getListeHS()`
		
		- `private void setListeHS()`
		
		- `public String[] getNomHS()`
		
		- `private void setNomHS()`
		
		- `public String[] getListeNiveau()`
		
		- `private void setListeNiveau()`
		
		- `public String[] getNomNiveau()`
		
		- `private void setNomNiveau()`

### `ListeHS` (énumeration)

L'énumération permet de modifier à un seul et unique endroit du code les paramètres concernant les classifications qui nécessitent une mise à jour.

- Variables d'instance :
	
	- `String classification`
	
	- `String synthese`
	
	- `int anneeDebut`
	
	- `int anneeFin`
	
	- `String dossier`

- Méthodes :
	
	- Constructeur : `public ListeHS(String classification, int anneeDebut, int anneeFin, String synthese, String dossier)`
	
	- *getters* et *setters* :
		
		- `public String getClassification()`
			
			- Elle permet d'avoir accès au nom s'affichant sur l'écran.
		
		- `public String getNomFichierSynthese()`
			
			- Elle permet d'avoir accès au code de la classification.
		
		- `public int getAnneeDebut()`
			
			- Elle permet d'avoir accès à l'année de départ de la classification.
		
		- `public int getAnneeFin()`
			
			- Elle permet d'avoir accès à l'année de fin de la classification.
		
		- `public String getDossier()`
			
			- Elle permet de définir les dossiers de référence dans les données de la classification se trouvent.

### `ListeNiveau` (énumeration)

L'énumération permet de modifier à un seul et unique endroit du code les paramètres concernant les niveaux qui nécessitent une mise à jour.

- Variables d'instance :
	
	- `String classification`
	
	- `String nomCSV`
	
	- `int niveau`
	
	- `String sousdossierp1`
	
	- `String sousdossierp2`

- Méthodes :

	- Constructeur : `ListeNiveau(String classification, String nomCSV, int niveau, String sousdossierp1, String sousdossierp2)`
	
	- getters et setters :
		
		- `public String getClassification()`
			
			- Elle permet d'avoir accès au nom s'affichant sur l'écran.
		
		- `public String getNomCSV()`
			
			- Elle permet d'avoir accès au nom du fichier contenant la classification au niveau requis. Il était nécessaire de croiser cette information avec l'énumération `ListeHS`. (Ne sert plus)
		
		- `public int getNomNiveau()`
			
			- Elle permet d'accès accès au code du niveau.
		
		- `public String getSousDossierP1()`
			
			- Elle permet d'avoir accès à la première partie du nom du dossier contenant le niveau souhaité.
		
		- `public String getSousDossierP2()`
			
			- Elle permet d'avoir accès à la troisième partie du nom du dossier contenant le niveau souhaité.
		
		- **N.B.** La deuxième partie du nom du dossier est le nom de la classification, ce qui nécessite l'usage de l'énumération `ListeHS`.

### `LectureCSV`

La classe lit les fichiers CSV.

- Variables d'instance :

	- `String nomDuFichier`

	- `String separateurTexte`

	- `int nombreDeColonne`

	- `int nombreDeLigne`

	- `String[] titreTableau`

	- `String[][] donneesTableau`

	- `String titreTexte`

	- `String[] donneesTexte`

	- `String fileContent`

- Méthodes :

	- Constructeurs :

		- `LectureCSV(String nomDuFichier, String separateurTexte)`

			- Elle initialise les données souhaitées par les méthodes `setNomDuFichier(nomDuFichier)` et `setSeparateurTexte(separateurTexte)`.

			- Elle lit les données par la méthode `lecture()`.

		- `LectureCSV(String nomDuFichier)`

			- Elle initialise les données souhaitées par les méthodes `setNomDuFichier(nomDuFichier)` et `setSeparateurTexte(",")`.

			- Elle lit les données par la méthode `lecture()`.

		- `LectureCSV()`

			- Elle affiche un message d'erreur sur la console.

	- `private void lecture()`
		
		- Elle lit le fichier récupéré par `getNomDuFichier()`.
		
		- Elle nécessite l'usage d'un objet `Separateur(data, getSeparateurTexte())` qui établit une priorité sur les guillemets par rapport aux virgules.
		
		- Elle initialise le tableau récupéré par les méthodes :
		
			- `setNombreDeLigne(numLigne)`
			
			- `setNombreDeColonne(numColonne)`
			
			- `setTitreTableau(tableauChaine)`
			
			- `setDonneesTableau(tableauChaine)`
			
			- `setTitreTexte(tableauChaine)`
			
			- `setDonneesTexte(tableauChaine)`
			
	- Affichage des résultats dans l'objet d'appel
		
		- `public void affichage()`
			
			- Elle affiche un format texte combinant le titre et les données en utilisant `getTitreTexte()`, `getNombreDeLigne()` et `getDonneesTexte()`.
		
		- `public void affichageTexte()`
			
			- Elle affiche un format texte du titre en utilisant `getTitreTableau()`.
		
		- `public void affichageDonnees()`
			
			- Elle affiche un format texte des données en utilisant `getNombreDeLigne()`, `getNombreDeColonne()` et `getDonneesTexte()`.
		
		- `public void affichageCellule(int ligne, int colonne)`
			
			- Elle affiche le contenu de la cellule demandée en utilisant `getNombreDeLigne()`, `getNombreDeColonne()` et `getDonneesTableau()`. Les variables ligne et colonne vont de 1 à l'infini.
		
		- `public void affichageColonne(int colonne)`
			
			- Elle affiche le contenu de la colonne demandée.
		
		- `public void affichageLigne(int ligne)`
			
			- Elle affiche le contenu de la ligne demandée.
	
	- `private void saisie()`
		
		- Elle permet d'enregistrer une nouvelle ligne *via* la console en utilisant `getNomDuFichier()`, `getNombreDeLigne()`, `getTitreTableau()` et `getSeparateurTexte()`.
		
		- Le nouveau tableau constitué, il faut l'écrire *via* la méthode `ecrire()`.
	
	- `private void ecrire(String fileContent)`
		
		- Elle enregistre une nouvelle ligne saisie sur la console en utilisant `getNomDuFichier()`, `getNombreDeLigne()`, `getTitreTableau()` et `getSeparateurTexte()`.
		
		- Afin de préparer une nouvelle saisie, une nouvelle lecture du tableau avec la nouvelle ligne doit être effectuée par la méthode `lecture()`.
	
	- *getters* et *setters*
		
		- `public String getNomDuFichier()`
		
		- `private void setNomDuFichier(String adresse)`
		
		- `public int getNombreDeColonne()`
		
		- `private void setNombreDeColonne(int nombreDeColonne)`
		
		- `public int getNombreDeLigne()`
		
		- `private void setNombreDeLigne(int nombreDeLigne)`
		
		- `public String getSeparateurTexte()`
		
		- `private void setSeparateurTexte(String separateurTexte)`
		
		- `public String[] getTitreTableau()`
		
		- `private void setTitreTableau(String[][] tableauChaine)`
			
			- Elle a besoin de `getNombreDeColonne()`.
		
		- `public String[][] getDonneesTableau()`
		
		- `private void setDonneesTableau(String[][] tableauChaine)`
			
			- Elle a besoin de `getNombreDeLigne()` et `getNombreDeColonne()`.
		
		- `public String getTitreTexte()`
		
		- `private void setTitreTexte(String[][] tableauChaine)`
			
			- Elle a besoin de `getNombreDeColonne()` pour fonctionner.
		
		- `public String[] `getDonneesTexte()`
		
		- `private void setDonneesTexte(String[][] tableauChaine)`
			
			- Elle a besoin de `getNombreDeLigne()` et `getNombreDeColonne()`.

### `Separateur`

Lors de la lecture par l'objet `LectureCSV()`, chaque ligne du tableau contenu dans le fichier lu est envoyé dans l'objet `Separateur()` afin de traiter sa chaîne de caractères. En repérant dans un premier temps les guillemets, puis dans un second temps les virgules.

- Variables d'instance :

	- `String chaineDeTexte`

	- `String separateurDeTexte`

	- `char separateurDeTexte2`

	- `int nombreDeColonne`

	- `String[] donneesTableau`

- Méthodes :

	- Constructeur : `public Separateur(String chaineDeTexte, String separateurDeTexte)`

		- Elle initialise les variables d'instance en utilisant `setChaineDeTexte(chaineDeTexte)`, `setSeparateurDeTexte(separateurDeTexte)`, `setSeparateurDeTexteCaractere(separateurDeTexteConv(getSeparateurDeTexte()))`.
		
		- Elle lance la méthode `contoleDesCaracteres()`.
	
	- `private char separateurDeTexteConv(String texte)`
		
		- Elle convertit le séparateur de texte exprimé en chaîne de caractères en un caractère.
	
	- `private void controleDesCaracteres()`
		
		- Elle applique la transformation souhaitée en utilisant `getChaineDeTexte()`, `getSeparateurDeTexte()` et `getSeparateurDeTexteCaractere()`.
		
		- Elle enregistre le découpage *via* les méthodes `setNombreDeColonne(chaine4.size())` et `setDonneesTableau(tableauChaine)`
	
	- *getters* et *setters*
		
		- `public int getNombreDeColonne()`
		
		- `private void setNombreDeColonne(int nombreDeColonne)`
		
		- `public String[] getDonneesTableau()`
		
		- `private void setDonneesTableau(String[] chaine)`
		
		- `public String getChaineDeTexte()`
		
		- `private void setChaineDeTexte(String chaine)`
		
		- `public String getSeparateurDeTexte()`
		
		- `private void setSeparateurDeTexte(String separateurDeTexte)`
		
		- `public char getSeparateurDeTexte()`
		
		- `private void setSeparateurDeTexte(char separateurDeTexte)`

### `DateMaintenant`

	La classe enregistre la date au moment où l'objet est sollicité.

- Variables d'instance :

	- `String dateString`

	- `double dateNumber`

- Méthodes :

	- Constructeur : `public DateMaintenant()`

	- `public String getDateMaintenant()`

		- Elle permet de renvoyer la date enregistrée sous format d'une chaîne de caractère.

	- `public double getDateMaintenantNombre()`

		- Elle permet de renvoyer la date sous la forme d'un nombre combinant jour, mois, année, heure, minute et seconde.

## Perspectives

Pour l'instant, le programme ne fait qu'enregistrer une extraction. Il faudrait pouvoir offrir à l'utilisateur le moyen de l'exploiter en lui donnant la possibilité d'afficher des graphiques ou des cartes par exemple. Il faudrait faire un point pour réfléchir à ces compléments, qui sont réalisables par d'autres logiciels comme Excel ou un système d'information géographique.

Il faudra prévoir un sous-programme pour exploiter les autres bases de données issues de la Banque mondiale.

Il faudra penser le croisement les données import-export et les autres bases de données.

À terme, il faudra concevoir un système d'aide à la décision qui prendra la forme d'une analyse en correspondances multiples (A.C.M.).
