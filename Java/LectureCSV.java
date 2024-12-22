//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.util.*;
import java.io.*;
import java.nio.*;

public class LectureCSV
{
	private String nomDuFichier = "";
	private String separateurTexte = "";
	private int nombreDeColonne = 0;
	private int nombreDeLigne = 0;
	private String[] titreTableau = null;//Version brute : titres
	private String[][] donneesTableau = null;//Version data
	private String titreTexte = "";//Version Enregistrement des données : Ligne des titres
	private String[] donneesTexte = null;//Version Enregistrement des données
	private String fileContent = "";//Il faut initialiser la variable sinon null apparaît en tête de la ligne créée.
//***************
//*Constructeurs*
//***************
	//Lecture d'un fichier avec précision du séparateur de texte
	public LectureCSV(String nomDuFichier, String separateurTexte)
	{
		//Initialisation des variables
		setNomDuFichier(nomDuFichier);//Récupération de la variable de classe
		setSeparateurTexte(separateurTexte);//Récupération de la variable de classe
		//Affichage des variables pour vérification
//		System.out.println(getNomDuFichier());
//		System.out.println(getSeparateurTexte());
		//Lecture du fichier;
		lecture();
	}
	//Lecture d'un fichier sans précision du séparateur de texte. Par défaut ce sera une virgule.
	public LectureCSV(String nomDuFichier)
	{
		//Initialisation des variables
		setNomDuFichier(nomDuFichier);//Récupération de la variable de classe
		setSeparateurTexte(",");//Récupération de la variable de classe
		//Affichage des variables pour vérification
//		System.out.println(getNomDuFichier());
//		System.out.println(getSeparateurTexte());
		//Lecture du fichier;
		lecture();
	}
	//Lecture d'un fichier sans précision du séparateur de texte. Par défaut ce sera une virgule.
	public LectureCSV()
	{
		System.out.println("Erreur ! Veuillez nommer votre fichier !");
	}
//************************
//*Lecture du fichier CSV*
//************************
	private void lecture()
	{
		File lecture = new File(getNomDuFichier());//Création d'un nouveau fichier
//		System.out.println(lecture);//Affichage du nom de l'adresse et du fichier
		//Tester les données pour en faire un tableau
		try 
		{
			//Lecture des données du fichier CSV
			Scanner inputStream = new Scanner(lecture);
			//Caractéristiques pour charger le tableau : nombre de lignes et nombre de colonnes
			int numLigne = 0;
			ArrayList<Integer> numColonneTab = new ArrayList<Integer>();
			while (inputStream.hasNext())
			{
				String data = inputStream.nextLine();//Obtenir l'ensemble des lignes / Modification de next() en nextLine afin de prendre en compte les espaces
				//Insertion d'un nouvel objet afin de lire les guillemets dans un fichier CSV
				Separateur separateur = new Separateur(data, getSeparateurTexte());
				String[] value = separateur.getDonneesTableau();
				//Ancien code
//				String[] value = data.split(getSeparateurTexte());//Séparer les colonnes
				numLigne++;
				numColonneTab.add(value.length);
			}
			//Paramètres du tableau
			int numColonne = numColonneTab.get(0);
			for(int i = 0 ; i < numColonneTab.size() ; ++i)
			{
				if(numColonne == numColonneTab.get(i))
				{
					numColonne = numColonneTab.get(i);
				}
				else
				{
					System.out.println("Votre tableau est mal con\u00e7u le nombre de colonne n'est pas identique sur chacune des lignes.");
					numColonne = 0;
					break;
				}
			}
			//Initialisation de la taille du tableau
			setNombreDeLigne(numLigne);
			setNombreDeColonne(numColonne);
			//Fermeture du fichier
			inputStream.close();
			//Lecture des données du fichier CSV
			Scanner inputStream2 = new Scanner(lecture);
			//Boucle pour charger le tableau
			String tableauChaine[][] = new String[getNombreDeLigne()][getNombreDeColonne()];
			int numLigneInit = 0;//Initialisation du compteur
			int numColonneInit = 0;//Initialisation du compteur
			while (inputStream2.hasNext())
			{
				String data = inputStream2.nextLine();//Obtenir l'ensemble des lignes / Modification de next() en nextLine afin de prendre en compte les espaces
				//Insertion d'un nouvel objet afin de lire les guillemets dans un fichier CSV
				Separateur separateur = new Separateur(data, getSeparateurTexte());
				String[] values = separateur.getDonneesTableau();
				//Ancien code
//				String[] values = data.split(getSeparateurTexte());//Séparer les colonnes
				for(int i = numColonneInit ; i < getNombreDeColonne() ; ++i)
				{
					tableauChaine[numLigneInit][i] = values[i];
				}
				numLigneInit++;
			}
			//Initialisation des tableaux de transfert : titreTableau, donneesTableau, titreTexte, donneesTexte	
			setTitreTableau(tableauChaine);
			setDonneesTableau(tableauChaine);
			setTitreTexte(tableauChaine);
			setDonneesTexte(tableauChaine);
			//Fermeture du fichier
			inputStream2.close();
		}
		catch(FileNotFoundException e) {System.out.println("Le fichier n'existe pas.");}
	}
//***********************
//*Fonctions d'affichage*
//***********************
	//Si on utilise directement les getters de tableaux, c'est la référence du tableau qui s'affiche.
	//Affichage de la base de données
	public void affichage()
	{
		//Affichage du titre
		System.out.println(getTitreTexte());
		//Affichage des lignes de données
		for(int i = 0; i < getNombreDeLigne() - 1 ; i++)
		{
			System.out.println(getDonneesTexte()[i]);
		}
	}	
	//Affichage des titres de la base de données
	public void affichageTitre()
	{
		for(int i = 0 ; i < getTitreTableau().length ; ++i)
		{	
			System.out.printf("Attribut %d : %s %n", i + 1, getTitreTableau()[i]);
		}
	}
	//Affichage de la base de données sans les titres
	public void affichageDonnees()
	{
		for(int i = 0; i < getNombreDeLigne() - 1 ; i++)
		{
			System.out.printf("Ligne %d : %s %n", i + 1, getDonneesTexte()[i]);
		}
	}
	//Affichage d'une cellule de la base de données sans les titres
	public void affichageCellule(int ligne, int colonne)
	{
		if(ligne >= 1 && ligne <= getNombreDeLigne() - 1 && colonne >= 1 && colonne <= getNombreDeColonne())
		{
			System.out.printf("Cellule (%d , %d) : %s %n", ligne, colonne, getDonneesTableau()[ligne - 1][colonne - 1]);
		}
		else
		{
			System.out.println("La cellule n'existe pas.");
		}
	}
	//Affichage d'une colonne
	public void affichageColonne(int colonne)
	{
		String[] tab = new String[getNombreDeLigne() - 1];
		for(int i = 0 ; i < getNombreDeLigne() - 1 ; ++i)//La ligne de titre ne doit pas être prise en compte.
		{
			tab[i] = getDonneesTableau()[i][colonne - 1];
//			System.out.println(tab[i]);
		}
		String init = "Colonne " + colonne + " : ";
		for(int i = 0 ; i < getNombreDeLigne() - 1 ; ++i)
		{
			if(i != getNombreDeLigne() - 2)
			{
				init = init + tab[i] + getSeparateurTexte();
			}
			else
			{
				init = init + tab[i];
			}
		}
		System.out.println(init);
	}
	//Affichage d'une ligne
	public void affichageLigne(int ligne)
	{
		String[] tab = new String[getNombreDeColonne()];
		for(int i = 0 ; i < getNombreDeColonne() ; ++i)
		{
			tab[i] = getDonneesTableau()[ligne - 1][i];
//			System.out.println(tab[i]);
		}
		String init = "Ligne " + ligne + " : ";
		for(int i = 0 ; i < getNombreDeColonne() ; ++i)
		{
			if(i != getNombreDeColonne() - 1)
			{
				init = init + tab[i] + getSeparateurTexte();
			}
			else
			{
				init = init + tab[i];
			}
		}
		System.out.println(init);
	}
//***********************
//*Fonctions d'écriture*
//***********************
	//Enregistrement d'une nouvelle valeur
	private void ecrire(String fileContent)
	{
		this.fileContent = fileContent;
		try
		{
			FileWriter writer = new FileWriter(getNomDuFichier());//Mettre l'adresse
			//Ajout de la nouvelle donnée
			ArrayList<String> donnees = new ArrayList<String>();
			for(int i = 0 ; i < getNombreDeLigne() - 1 ; i++)
			{
				donnees.add(donneesTexte[i]);
			}
			donnees.add(fileContent);
			//Enregistrement de la nouvelle valeur
			String init = "";
			for(int i = 0; i < getTitreTableau().length ; i++)
			{
				if(i != getTitreTableau().length - 1)
				{
					init = init + getTitreTableau()[i] + getSeparateurTexte();
				}
				else
				{
					init = init + getTitreTableau()[i] + "\n";
				}
			}
			for(int i = 0; i < donnees.size() ; i++)
			{
				if(i != donnees.size() - 1)
				{
					init = init + donnees.get(i) + "\n";
				}
				else
				{
					init = init + donnees.get(i);
				}
			}
			//Enregistrement de la nouvelle donnée
			writer.write(init);
			System.out.println("Enregistrement effectu\u00e9");
			writer.close();
			//Modification du tableau interne
			lecture();
		}
		catch(IOException e) {e.printStackTrace();}
		catch(ArrayIndexOutOfBoundsException e) {e.printStackTrace();}
	}
	
	//Saisir une nouvelle valeur
	public void saisie()
	{
		//Traitement de la saisie
		Scanner clavier = new Scanner(System.in);
		String nouvelleDonnee[] = new String[getNombreDeColonne()];
		for(int i = 0 ; i < getNombreDeColonne() ; i++)
		{
			System.out.printf("Colonne %d - %s : %n", i + 1, getTitreTableau()[i]);
			clavier = new Scanner(System.in);
			nouvelleDonnee[i] = clavier.nextLine();
			String test = nouvelleDonnee[i];
			int positionSeparateurTexte = test.indexOf(getSeparateurTexte());
			//Sécurité pour la saisie
			while(positionSeparateurTexte != -1)
			{
				System.out.println("Erreur ! Le s\u00e9parateur de texte ne peut pas \u00eatre saisi ! Veillez resaisir votre entr\u00e9e !");
				System.out.printf("Colonne %d - %s : %n", i + 1, getTitreTableau()[i]);
				clavier = new Scanner(System.in);
				nouvelleDonnee[i] = clavier.nextLine();
				String test2 = nouvelleDonnee[i];
				positionSeparateurTexte = test2.indexOf(getSeparateurTexte());	
			}
		}
		//Traitement des données saisies
		for(int i = 0 ; i < getNombreDeColonne() ; i++)
		{			
			if(i != getNombreDeColonne() - 1)
			{
				this.fileContent = this.fileContent + nouvelleDonnee[i] + getSeparateurTexte();
			}
			else
			{
				this.fileContent = this.fileContent + nouvelleDonnee[i];
			}
		}
		ecrire(fileContent);
	}
//********************
//*Getters et Setters*
//********************	
	//Variable nomDuFichier
	public String getNomDuFichier()
	{
		return this.nomDuFichier;
	}
	private void setNomDuFichier(String adresse)
	{
		this.nomDuFichier = adresse;
	}
	//Variable nombreDeColonne
	public int getNombreDeColonne()
	{
		return nombreDeColonne;
	}
	private void setNombreDeColonne(int nombreDeColonne)
	{
		this.nombreDeColonne = nombreDeColonne;
	}
	//Variable nombreDeLigne
	public int getNombreDeLigne()
	{
		return nombreDeLigne;
	}
	private void setNombreDeLigne(int nombreDeLigne)
	{
		this.nombreDeLigne = nombreDeLigne;
	}
	//Variable separateurTexte
	public String getSeparateurTexte()
	{
		return separateurTexte;
	}
	private void setSeparateurTexte(String separateurTexte)
	{
		this.separateurTexte = separateurTexte;
	}
	//Traitement de la première ligne : les titres des champs
	public String[] getTitreTableau()
	{
		return this.titreTableau;
	}
	private void setTitreTableau(String[][] tableauChaine)
	{
		int numColonne = 0;//Initialisation du compteur
		String titreTableau[] = new String[getNombreDeColonne()];
		for(int i = numColonne; i < getNombreDeColonne(); i++)
		{
			titreTableau[i] = tableauChaine[0][i];
		}
		this.titreTableau = titreTableau;
	}
	//Traitement du tableau de données sans les titres en version data
	public String[][] getDonneesTableau()
	{
		return this.donneesTableau;
	}
	private void setDonneesTableau(String[][] tableauChaine)
	{
		int numLigne = 1;//Réinitialisation du compteur - La ligne 1 correspond à la première ligne des données.
		int numColonne = 0;//Réinitialisation du compteur
		String[][] donneesTableau = new String[getNombreDeLigne() - 1][getNombreDeColonne()];
		for(int j = numLigne; j < getNombreDeLigne() ; j++)
		{
			for(int i = numColonne; i < getNombreDeColonne() ; i++)
			{
				donneesTableau[j - 1][i] = tableauChaine[j][i];
			}
		}
		this.donneesTableau = donneesTableau;
	}
	//Traitement de la première ligne : les titres des champs en texte pour l'enregistrement des données
	public String getTitreTexte()
	{
		return this.titreTexte;
	}
	private void setTitreTexte(String[][] tableauChaine)
	{
		int numColonne = 0;//Initialisation du compteur
		String titreTexte = "";
		for(int i = numColonne; i < getNombreDeColonne() ; i++)
		{
			if(i < getNombreDeColonne() - 1)
			{
				titreTexte = titreTexte + tableauChaine[0][i] + ",";
			}
			else
			{
				titreTexte = titreTexte + tableauChaine[0][i];
			}
		}		
		this.titreTexte = titreTexte;
	}
	//Traitement du tableau de données en texte pour l'enregistrement des données
	public String[] getDonneesTexte()
	{
		return this.donneesTexte;
	}
	private void setDonneesTexte(String[][] tableauChaine)
	{
		int numLigne = 1;//Réinitialisation du compteur - La ligne 1 correspond à la première ligne des données.
		int numColonne = 0;//Réinitialisation du compteur
		String[] donneesTexte = new String[getNombreDeLigne() - 1];
		String memoire;//Variable mémorisant les valeurs de chacune des colonnes par ligne
		for(int j = numLigne; j < getNombreDeLigne() ; j++)
		{
			for(int i = numColonne; i < getNombreDeColonne() ; i++)
			{
				//Initialisation de la variable memoire
				if(i == 0)
				{
					memoire = "";
				}
				else
				{
					memoire = donneesTexte[j - 1];
				}
				//Agrégation des lignes
				if(i < getNombreDeColonne() - 1)
				{
					donneesTexte[j - 1] = memoire + tableauChaine[j][i] + ",";
				}
				else
				{
					donneesTexte[j - 1] = memoire + tableauChaine[j][i];
				}
			}
		}
		this.donneesTexte = donneesTexte;
	}
}