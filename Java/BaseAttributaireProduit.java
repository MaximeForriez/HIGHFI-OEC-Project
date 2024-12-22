//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
//Programme corrigé en juin 2022 afin d'y introduire les noms français des niveaux 4-digit et 6-digit et les noms anglais des niveaux Section
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BaseAttributaireProduit
{
	private String choixDesClassifications = null;
	private String choixDesNiveaux = null;
	private String[] listeHS = null;
	private String[] nomHS = null;
	private String[] listeNiveau = null;
	private String[] nomNiveau = null;
	private int[] niveau = null;
	private int choixDesNiveauxInt = 0;
	private int longueurData = 0;
	private String[] titreData = null;
	private String[] codeSectionReference = null;
	private String[] nameSectionReference = null;
	private String[] nomSectionReference = null;
	private String[] code2DigitReference = null;
	private String[] name2DigitReference = null;
	private String[] nom2DigitReference = null;
	private String[] code4DigitReference = null;
	private String[] name4DigitReference = null;
	private String[] nom4DigitReference = null;
	private String[] code6DigitReference = null;
	private String[] name6DigitReference = null;
	private String[] nom6DigitReference = null;
	private String[] codeSection = null;
	private String[] nameSection = null;
	private String[] nomSection = null;
	private String[] code2Digit = null;
	private String[] name2Digit = null;
	private String[] nom2Digit = null;
	private String[] code4Digit = null;
	private String[] name4Digit = null;
	private String[] nom4Digit = null;
	private String[] code6Digit = null;
	private String[] name6Digit = null;
	private String[] nom6Digit = null;
	
	public BaseAttributaireProduit(String text1, String text2)
	{
		setChoixDesClassifications(text1);
		setChoixDesNiveaux(text2);
		setListeHS();
		setNomHS();
		setListeNiveau();
		setNomNiveau();
		setNiveau();
		donneesProduits();
	}

//*******************************
//*Lecture de la base de données*
//*******************************
	private void donneesProduits()
	{
		String nomClassification = "";
		for(int i = 0 ; i < getListeHS().length ; ++i)
		{
			if(getChoixDesClassifications() == getListeHS()[i])
			{
				nomClassification = getNomHS()[i];
			}
		}
		String nomFichier = "";
		int pos = 0;
		for(int i = 0 ; i < getListeNiveau().length ; ++i)
		{
			if(getChoixDesNiveaux() == getListeNiveau()[i])
			{
				nomFichier = getNomNiveau()[i];
				pos = i;
			}
		}
		String nomFichier2 = "";
		if(pos <= 1)
		{
			nomFichier2 = nomFichier;
		}
		else
		{
			nomFichier2 = nomClassification + nomFichier;
		}
		//Préparation des niveaux inférieurs
		String[] listeFichierCSVProduit = new String[getListeNiveau().length];
		for(int i = 0 ; i < getListeNiveau().length ; ++i)
		{
			if(i <= 1)
			{
				listeFichierCSVProduit[i] = getNomNiveau()[i];
			}
			else
			{
				listeFichierCSVProduit[i] = nomClassification + getNomNiveau()[i];
			}
		}
		int posMax = 0;
		for(int i = 0 ; i < listeFichierCSVProduit.length ; ++i)
		{
			if(listeFichierCSVProduit[i].equals(nomFichier2) == true)
			{
				posMax = i;
			}
		}
		//Enregistrement du niveau choisi en valeur numérique
		setChoixDesNiveauxInt(getNiveau()[posMax]);
		//Chargement de la base de référence
		String adresse = "Attributs/hs-synthese.csv";
		LectureCSV listeProduitAttribut = new LectureCSV(adresse, ",");
//		System.out.println("Nombre de lignes : " + listeProduitAttribut.getNombreDeLigne());
//		System.out.println("Nombre de colonnes : " + listeProduitAttribut.getNombreDeColonne());
//		listeProduitAttribut.affichageTitre();//Afficher les titres des champs
//		listeProduitAttribut.affichageDonnees();//Afficher les données de la base
			//Récupération des colonnes des données attributaires
			String[][] data = listeProduitAttribut.getDonneesTableau();
			String[] titre = listeProduitAttribut.getTitreTableau();
			//Individualisation des colonnes
			String[] classificationHSRef = new String[data.length];
			String[] anneeDebutSerieRef = new String[data.length];
			String[] codeSectionRef = new String[data.length];
			//Variable ajoutée en 2022
			String[] nameSectionRef = new String[data.length];
			//
			String[] nomSectionRef = new String[data.length];
			String[] code2DigitRef = new String[data.length];
			String[] name2DigitRef = new String[data.length];
			String[] nom2DigitRef = new String[data.length];
			String[] code4DigitRef = new String[data.length];
			String[] name4DigitRef = new String[data.length];
			//Variable ajoutée en 2022
			String[] nom4DigitRef = new String[data.length];
			//
			String[] code6DigitRef = new String[data.length];
			String[] name6DigitRef = new String[data.length];
			//Variable ajoutée en 2022
			String[] nom6DigitRef = new String[data.length];
			//
			for(int i = 0 ; i < data.length ; ++i)
			{
				//Classification HS : colonne n°1
				classificationHSRef[i] =  data[i][0];
				//Année de référence de la classification : colonne n°2
				anneeDebutSerieRef[i] = data[i][1];
				//Section : colonne n°3
				codeSectionRef[i] = data[i][2];
				//Nom anglais des sections : colonne n°4
				nameSectionRef[i] = data[i][3];
				//Nom français de la section : colonne n°5
				nomSectionRef[i] = data[i][4];
				//Code des chapitres : colonne n°6
				code2DigitRef[i] = suppressionVirgule(data[i][5]);
				//Nom anglais des chapitres : colonne n°7
				name2DigitRef[i] = data[i][6];
				//Nom français des chapitres : colonne n°8
				nom2DigitRef[i] = data[i][7];
				//Code 4-digit : colonne n°9
				code4DigitRef[i] = suppressionVirgule(data[i][8]);
				//Nom anglais 4-digit : colonne n°10
				name4DigitRef[i] = data[i][9];
				//Nom français 4-digit : colonne n°11
				name4DigitRef[i] = data[i][10];
				//Code 6-digit : colonne n°12
				code6DigitRef[i] = suppressionVirgule(data[i][11]);
				//Nom anglais 6-digit : colonne n°13
				name6DigitRef[i] = data[i][12];
				//Nom français 6-digit : colonne n°14
				name6DigitRef[i] = data[i][13];
				//Il existe une colonne n°15 recensant le code officiel de la classification
			}
			//Simplification du tableau de référence en éliminant les classifications non utilisées
			ArrayList<Integer> posHSRef = new ArrayList<Integer>();
			for(int i = 0 ; i < classificationHSRef.length ; ++i)
			{
				if(nomClassification.equals(classificationHSRef[i]) == true)
				{
					posHSRef.add(i);
				}
			}
			//Base de référence simplifiée
			String[] classificationHSRef2 = new String[posHSRef.size()];
			String[] anneeDebutSerieRef2 = new String[posHSRef.size()];
			String[] codeSectionRef2 = new String[posHSRef.size()];
			String[] nameSectionRef2 = new String[posHSRef.size()];
			String[] nomSectionRef2 = new String[posHSRef.size()];
			String[] code2DigitRef2 = new String[posHSRef.size()];
			String[] name2DigitRef2 = new String[posHSRef.size()];
			String[] nom2DigitRef2 = new String[posHSRef.size()];
			String[] code4DigitRef2 = new String[posHSRef.size()];
			String[] name4DigitRef2 = new String[posHSRef.size()];
			String[] nom4DigitRef2 = new String[posHSRef.size()];
			String[] code6DigitRef2 = new String[posHSRef.size()];
			String[] name6DigitRef2 = new String[posHSRef.size()];
			String[] nom6DigitRef2 = new String[posHSRef.size()];
			for(int i = 0 ; i < posHSRef.size() ; ++i)
			{
				classificationHSRef2[i] = classificationHSRef[posHSRef.get(i)];
				anneeDebutSerieRef2[i] = anneeDebutSerieRef[posHSRef.get(i)];
				codeSectionRef2[i] = codeSectionRef[posHSRef.get(i)];
				nameSectionRef2[i] = nomSectionRef[posHSRef.get(i)];
				nomSectionRef2[i] = nomSectionRef[posHSRef.get(i)];
				code2DigitRef2[i] = code2DigitRef[posHSRef.get(i)];
				name2DigitRef2[i] = name2DigitRef[posHSRef.get(i)];
				nom2DigitRef2[i] = nom2DigitRef[posHSRef.get(i)];
				code4DigitRef2[i] = code4DigitRef[posHSRef.get(i)];
				name4DigitRef2[i] = name4DigitRef[posHSRef.get(i)];
				nom4DigitRef2[i] = name4DigitRef[posHSRef.get(i)];
				code6DigitRef2[i] = code6DigitRef[posHSRef.get(i)];
				name6DigitRef2[i] = name6DigitRef[posHSRef.get(i)];
				nom6DigitRef2[i] = name6DigitRef[posHSRef.get(i)];
			}
			int longueurData = classificationHSRef2.length;
			setLongueurDataProduit(longueurData);
			//Enregistrement des variables d'instance
		setTitreData(titre);
		setCodeSectionReference(codeSectionRef2);
		setNameSectionReference(nameSectionRef2);
		setNomSectionReference(nomSectionRef2);
		setCode2DigitReference(code2DigitRef2);
		setName2DigitReference(name2DigitRef2);
		setNom2DigitReference(nom2DigitRef2);
		setCode4DigitReference(code4DigitRef2);
		setName4DigitReference(name4DigitRef2);
		setNom4DigitReference(nom4DigitRef2);
		setCode6DigitReference(code6DigitRef2);
		setName6DigitReference(name6DigitRef2);
		setNom6DigitReference(name6DigitRef2);
		setCodeSection(transformationTableau(getCodeSectionReference()));
		setNameSection(transformationTableau(getNameSectionReference()));
		setNomSection(transformationTableau(getNomSectionReference()));
		setCode2Digit(transformationTableau(getCode2DigitReference()));
		setName2Digit(transformationTableau(getName2DigitReference()));
		setNom2Digit(transformationTableau(getNom2DigitReference()));
		setCode4Digit(transformationTableau(getCode4DigitReference()));
		setName4Digit(transformationTableau(getName4DigitReference()));
		setNom4Digit(transformationTableau(getNom4DigitReference()));
		setCode6Digit(transformationTableau(getCode6DigitReference()));
		setName6Digit(transformationTableau(getName6DigitReference()));
		setNom6Digit(transformationTableau(getNom6DigitReference()));
	}
	//Une virgule en trop apparaît dans les tableaux de code. Ce code la supprime.
	private String suppressionVirgule(String data)
	{
		int longueurChaine = data.length();
		String string = "";
		for(int i = 0 ; i < longueurChaine ; ++i)
		{
			if(data.charAt(i) != ',')
			{
				string = string + Character.toString(data.charAt(i));
			}
		}
		return string;
	}
	private String[] transformationTableau(String[] table)
	{
		ArrayList<String> code = new ArrayList<String>();
		code.add(table[0]);
		for(int i = 1 ; i < table.length ; ++i)
		{
			if(!table[i - 1].equals(table[i]))
			{
				code.add(table[i]);
			}
		}
		String[] code2 = new String[code.size()];
		for(int i = 0 ; i < code.size() ; ++i)
		{
			code2[i] = code.get(i);
		}
		return code2;
	}
	private int[] positionTransformationTableau(String[] table)
	{
		ArrayList<Integer> code = new ArrayList<Integer>();
		code.add(0);
		for(int i = 1 ; i < table.length ; ++i)
		{
			if(!table[i - 1].equals(table[i]))
			{
				code.add(i);
			}
		}
		int[] code2 = new int[code.size()];
		for(int i = 1 ; i < code.size() ; ++i)
		{
			code2[i] = code.get(i);
		}
		return code2;
	}
//*************************************
//*Affichage du tableau sur la console*
//*************************************
	public void affichageTableau()
	{
		int longueurData = getCodeSectionReference().length;
		for(int i = 0 ; i < longueurData ; ++i)
		{
			int num = i + 1;
			System.out.println(num + "," + getCodeSectionReference()[i] + "," + getNameSectionReference()[i] + "," + getNomSectionReference()[i] + "," + getCode2DigitReference()[i] + "," + getNom2DigitReference()[i] + "," + getCode4DigitReference()[i] + "," + getName4DigitReference()[i] + ","  + getNom4DigitReference()[i] + "," + getCode6DigitReference()[i] + "," + getName6DigitReference()[i] + "," + getNom6DigitReference()[i]);
		}
	}
	public String affichageFenetreTableauCSV()
	{
		int longueurData = getCodeSectionReference().length;
		String[] tableauDonnees = new String[longueurData];
		for(int i = 0 ; i < longueurData ; ++i)
		{
			int num = i + 1;
			tableauDonnees[i] = num + "," + getCodeSectionReference()[i] + "," + getNameSectionReference()[i] + ","  + getNomSectionReference()[i] + "," + getCode2DigitReference()[i] + "," + getNom2DigitReference()[i] + "," + getCode4DigitReference()[i] + "," + getName4DigitReference()[i] + "," + getNom4DigitReference()[i] + ","  + getCode6DigitReference()[i] + "," + getName6DigitReference()[i] + "," + getNom6DigitReference()[i];
		}
		String texteTableau = "";
		for(int i = 0 ; i <  tableauDonnees.length ; ++i)
		{
			texteTableau = texteTableau + tableauDonnees[i] + "\n";
		}
		return texteTableau;
	}
	public void affichageFenetreTableau()
	{
		//Longueur du tableau de données
		int longueurData = getCodeSectionReference().length;
		//Traitement des titres du tableau de données
		String[] titreDonnees = getTitreData();
		ArrayList<String> titre = new ArrayList<String>();
		titre.add("ID");
		for(int i = 2 ; i < titreDonnees.length ; ++i)//Les deux premières colonnes ne sont pas prises en compte
		{
			titre.add(titreDonnees[i]);
		}
			//Mise à zéro du tableau des titres
			String[] titreDonnees2 = new String[titre.size()];
			//Fin du traitement des titres du tableau de données
			for(int i = 0 ; i < titre.size() ; ++i)
			{
				titreDonnees2[i] = titre.get(i);
			}
		//Constitution du tableau
		Object[][] tableauDonnees = new Object[longueurData][titreDonnees2.length];
		for(int i = 0 ; i < longueurData ; ++i)
		{
			int num = i + 1;
			tableauDonnees[i][0] = num;//Integer.toString(num);
			tableauDonnees[i][1] = getCodeSectionReference()[i];
			tableauDonnees[i][2] = getNameSectionReference()[i];
			tableauDonnees[i][3] = getNomSectionReference()[i];
			tableauDonnees[i][4] = getCode2DigitReference()[i];
			tableauDonnees[i][5] = getName2DigitReference()[i];
			tableauDonnees[i][6] = getNom2DigitReference()[i];
			tableauDonnees[i][7] = getCode4DigitReference()[i];
			tableauDonnees[i][8] = getName4DigitReference()[i];
			tableauDonnees[i][9] = getNom4DigitReference()[i];
			tableauDonnees[i][10] = getCode6DigitReference()[i];
			tableauDonnees[i][11] = getName6DigitReference()[i];
			tableauDonnees[i][12] = getNom6DigitReference()[i];
		}
		//Affichage de la fenêtre du tableau
		JFrame fenetreDonnees = new JFrame();
		fenetreDonnees.setTitle("Liste des donn\u00e9es");
		fenetreDonnees.setVisible(true);
		fenetreDonnees.setSize(900,600);
		fenetreDonnees.setLocationRelativeTo(null);
		fenetreDonnees.setResizable(true);
		fenetreDonnees.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container conteneurFenetreDonnees = fenetreDonnees.getContentPane();
		JPanel panel = new JPanel(new BorderLayout());
		JTable tableau = new JTable(tableauDonnees, titreDonnees2);
		JScrollPane scroll = new JScrollPane(tableau, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scroll, BorderLayout.CENTER);
		conteneurFenetreDonnees.add(panel);
	}
	public void affichageFenetreTableauSimplifiee()
	{
		//Traitement des titres du tableau de données
		String[] titreDonnees = getTitreData();
		ArrayList<String> titre = new ArrayList<String>();
		titre.add("ID");
		for(int i = 2 ; i < titreDonnees.length ; ++i)//Les deux premières colonnes ne sont pas prises en compte
		{
			titre.add(titreDonnees[i]);
		}
			//Mise à zéro du tableau des titres
			String[] titreDonnees2 = new String[titre.size()];
			//Fin du traitement des titres du tableau de données
			for(int i = 0 ; i < titre.size() ; ++i)
			{
				titreDonnees2[i] = titre.get(i);
			}
		//Longueur du tableau de données
		int longueurData = 0;
		int[] positionData = null;
		Object[][] tableauDonnees = null;
		String[] titreDonnees2bis = null;
		if(getChoixDesNiveauxInt() == 0)
		{
			longueurData = getCodeSection().length;
			positionData = positionTransformationTableau(getCodeSectionReference());
			String[] colonne2 = new String[positionData.length];
			String[] colonne3 = new String[positionData.length];
			String[] colonne4 = new String[positionData.length];
			titreDonnees2bis = new String[4];
			for(int i = 0 ; i < 4 ; ++i)
			{
				titreDonnees2bis[i] = titreDonnees2[i];
			}
			for(int i = 0 ; i < positionData.length ; ++i)
			{
				colonne2[i] = getCodeSectionReference()[positionData[i]];
				colonne3[i] = getNameSectionReference()[positionData[i]];
				colonne4[i] = getNomSectionReference()[positionData[i]];
			}
			//Constitution du tableau
			tableauDonnees = new Object[longueurData][titreDonnees2.length];
			for(int i = 0 ; i < longueurData ; ++i)
			{
				int num = i + 1;
				tableauDonnees[i][0] = num;//Integer.toString(num);
				tableauDonnees[i][1] = colonne2[i];
				tableauDonnees[i][2] = colonne3[i];
				tableauDonnees[i][3] = colonne4[i];
			}
		}
		else
		{
			if(getChoixDesNiveauxInt() == 2)
			{
				longueurData = getCode2Digit().length;
				positionData = positionTransformationTableau(getCode2DigitReference());
				String[] colonne2 = new String[positionData.length];
				String[] colonne3 = new String[positionData.length];
				String[] colonne4 = new String[positionData.length];
				String[] colonne5 = new String[positionData.length];
				String[] colonne6 = new String[positionData.length];
				String[] colonne7 = new String[positionData.length];
				titreDonnees2bis = new String[7];
				for(int i = 0 ; i < 7 ; ++i)
				{
					titreDonnees2bis[i] = titreDonnees2[i];
				}
				for(int i = 0 ; i < positionData.length ; ++i)
				{
					colonne2[i] = getCodeSectionReference()[positionData[i]];
					colonne3[i] = getNameSectionReference()[positionData[i]];
					colonne4[i] = getNomSectionReference()[positionData[i]];
					colonne5[i] = getCode2DigitReference()[positionData[i]];
					colonne6[i] = getName2DigitReference()[positionData[i]];
					colonne7[i] = getNom2DigitReference()[positionData[i]];
				}
				//Constitution du tableau
				tableauDonnees = new Object[longueurData][titreDonnees2.length];
				for(int i = 0 ; i < longueurData ; ++i)
				{
					int num = i + 1;
					tableauDonnees[i][0] = num;//Integer.toString(num);
					tableauDonnees[i][1] = colonne2[i];
					tableauDonnees[i][2] = colonne3[i];
					tableauDonnees[i][3] = colonne4[i];
					tableauDonnees[i][4] = colonne5[i];
					tableauDonnees[i][5] = colonne6[i];
					tableauDonnees[i][6] = colonne7[i];
				}
			}
			else
			{
				if(getChoixDesNiveauxInt() == 4)
				{
					longueurData = getCode4Digit().length;
					positionData = positionTransformationTableau(getCode4DigitReference());
					String[] colonne2 = new String[positionData.length];
					String[] colonne3 = new String[positionData.length];
					String[] colonne4 = new String[positionData.length];
					String[] colonne5 = new String[positionData.length];
					String[] colonne6 = new String[positionData.length];
					String[] colonne7 = new String[positionData.length];
					String[] colonne8 = new String[positionData.length];
					String[] colonne9 = new String[positionData.length];
					String[] colonne10 = new String[positionData.length];
					titreDonnees2bis = new String[10];
					for(int i = 0 ; i < 10 ; ++i)
					{
						titreDonnees2bis[i] = titreDonnees2[i];
					}
					for(int i = 0 ; i < positionData.length ; ++i)
					{
						colonne2[i] = getCodeSectionReference()[positionData[i]];
						colonne3[i] = getNameSectionReference()[positionData[i]];
						colonne4[i] = getNomSectionReference()[positionData[i]];
						colonne5[i] = getCode2DigitReference()[positionData[i]];
						colonne6[i] = getName2DigitReference()[positionData[i]];
						colonne7[i] = getNom2DigitReference()[positionData[i]];
						colonne8[i] = getCode4DigitReference()[positionData[i]];
						colonne9[i] = getName4DigitReference()[positionData[i]];
						colonne10[i] = getNom4DigitReference()[positionData[i]];
					}
					//Constitution du tableau
					tableauDonnees = new Object[longueurData][titreDonnees2.length];
					for(int i = 0 ; i < longueurData ; ++i)
					{
						int num = i + 1;
						tableauDonnees[i][0] = num;//Integer.toString(num);
						tableauDonnees[i][1] = colonne2[i];
						tableauDonnees[i][2] = colonne3[i];
						tableauDonnees[i][3] = colonne4[i];
						tableauDonnees[i][4] = colonne5[i];
						tableauDonnees[i][5] = colonne6[i];
						tableauDonnees[i][6] = colonne7[i];
						tableauDonnees[i][7] = colonne8[i];
						tableauDonnees[i][8] = colonne9[i];
						tableauDonnees[i][9] = colonne10[i];
					}
				}
				else
				{
					if(getChoixDesNiveauxInt() == 6)
					{
						longueurData = getCode6Digit().length;
						positionData = positionTransformationTableau(getCode6DigitReference());
						String[] colonne2 = new String[positionData.length];
						String[] colonne3 = new String[positionData.length];
						String[] colonne4 = new String[positionData.length];
						String[] colonne5 = new String[positionData.length];
						String[] colonne6 = new String[positionData.length];
						String[] colonne7 = new String[positionData.length];
						String[] colonne8 = new String[positionData.length];
						String[] colonne9 = new String[positionData.length];
						String[] colonne10 = new String[positionData.length];
						String[] colonne11 = new String[positionData.length];
						String[] colonne12 = new String[positionData.length];
						String[] colonne13 = new String[positionData.length];
						titreDonnees2bis = new String[13];
						for(int i = 0 ; i < 13 ; ++i)
						{
							titreDonnees2bis[i] = titreDonnees2[i];
						}
						for(int i = 0 ; i < positionData.length ; ++i)
						{
							colonne2[i] = getCodeSectionReference()[positionData[i]];
							colonne3[i] = getNameSectionReference()[positionData[i]];
							colonne4[i] = getNomSectionReference()[positionData[i]];
							colonne5[i] = getCode2DigitReference()[positionData[i]];
							colonne6[i] = getName2DigitReference()[positionData[i]];
							colonne7[i] = getNom2DigitReference()[positionData[i]];
							colonne8[i] = getCode4DigitReference()[positionData[i]];
							colonne9[i] = getName4DigitReference()[positionData[i]];
							colonne10[i] = getNom4DigitReference()[positionData[i]];
							colonne11[i] = getCode6DigitReference()[positionData[i]];
							colonne12[i] = getName6DigitReference()[positionData[i]];
							colonne13[i] = getNom6DigitReference()[positionData[i]];
						}
						//Constitution du tableau
						tableauDonnees = new Object[longueurData][titreDonnees2.length];
						for(int i = 0 ; i < longueurData ; ++i)
						{
							int num = i + 1;
							tableauDonnees[i][0] = num;//Integer.toString(num);
							tableauDonnees[i][1] = colonne2[i];
							tableauDonnees[i][2] = colonne3[i];
							tableauDonnees[i][3] = colonne4[i];
							tableauDonnees[i][4] = colonne5[i];
							tableauDonnees[i][5] = colonne6[i];
							tableauDonnees[i][6] = colonne7[i];
							tableauDonnees[i][7] = colonne8[i];
							tableauDonnees[i][8] = colonne9[i];
							tableauDonnees[i][9] = colonne10[i];
							tableauDonnees[i][10] = colonne11[i];
							tableauDonnees[i][11] = colonne12[i];
							tableauDonnees[i][12] = colonne13[i];
						}
					}
					else
					{
						
					}
				}
			}
		}
		//Affichage de la fenêtre du tableau
		JFrame fenetreDonnees = new JFrame();
		fenetreDonnees.setTitle("Liste des donn\u00e9es");
		fenetreDonnees.setVisible(true);
		fenetreDonnees.setSize(900,600);
		fenetreDonnees.setLocationRelativeTo(null);
		fenetreDonnees.setResizable(true);
		fenetreDonnees.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container conteneurFenetreDonnees = fenetreDonnees.getContentPane();
		JPanel panel = new JPanel(new BorderLayout());
		JTable tableau = new JTable(tableauDonnees, titreDonnees2bis);
		JScrollPane scroll = new JScrollPane(tableau, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scroll, BorderLayout.CENTER);
		conteneurFenetreDonnees.add(panel);
	}
//********************
//*Getters et setters*
//********************
	public String getChoixDesClassifications()
	{
		return this.choixDesClassifications;
	}
	private void setChoixDesClassifications(String nomClassification)
	{
		this.choixDesClassifications = nomClassification;
	}
	public String getChoixDesNiveaux()
	{
		return this.choixDesNiveaux;
	}
	private void setChoixDesNiveaux(String nomDuNiveau)
	{
		this.choixDesNiveaux = nomDuNiveau;
	}
	public int getChoixDesNiveauxInt()
	{
		return this.choixDesNiveauxInt;
	}
	private void setChoixDesNiveauxInt(int nomDuNiveau)
	{
		this.choixDesNiveauxInt = nomDuNiveau;
	}
	public int getLongueurDataProduit()
	{
		return this.longueurData;
	}
	private void setLongueurDataProduit(int longueurData)
	{
		this.longueurData = longueurData;
	}
	public String[] getTitreData()
	{
		return this.titreData;
	}
	private void setTitreData(String[] titreData)
	{
		this.titreData = titreData;
	}
	public String[] getCodeSectionReference()
	{
		return this.codeSectionReference;
	}
	private void setCodeSectionReference(String[] codeSectionReference)
	{
		this.codeSectionReference = codeSectionReference;
	}
	public String[] getNameSectionReference()
	{
		return this.nameSectionReference;
	}
	private void setNameSectionReference(String[] nameSectionReference)
	{
		this.nameSectionReference = nameSectionReference;
	}
	public String[] getNomSectionReference()
	{
		return this.nomSectionReference;
	}
	private void setNomSectionReference(String[] nomSectionReference)
	{
		this.nomSectionReference = nomSectionReference;
	}
	public String[] getCode2DigitReference()
	{
		return this.code2DigitReference;
	}
	private void setCode2DigitReference(String[] code2DigitReference)
	{
		this.code2DigitReference = code2DigitReference;
	}
	public String[] getName2DigitReference()
	{
		return this.name2DigitReference;
	}
	private void setName2DigitReference(String[] name2DigitReference)
	{
		this.name2DigitReference = name2DigitReference;
	}
	public String[] getNom2DigitReference()
	{
		return this.nom2DigitReference;
	}
	private void setNom2DigitReference(String[] nom2DigitReference)
	{
		this.nom2DigitReference = nom2DigitReference;
	}
	public String[] getCode4DigitReference()
	{
		return this.code4DigitReference;
	}
	private void setCode4DigitReference(String[] code4DigitReference)
	{
		this.code4DigitReference = code4DigitReference;
	}
	public String[] getName4DigitReference()
	{
		return this.name4DigitReference;
	}
	private void setName4DigitReference(String[] name4DigitReference)
	{
		this.name4DigitReference = name4DigitReference;
	}
	public String[] getNom4DigitReference()
	{
		return this.nom4DigitReference;
	}
	private void setNom4DigitReference(String[] nom4DigitReference)
	{
		this.nom4DigitReference = nom4DigitReference;
	}
	public String[] getCode6DigitReference()
	{
		return this.code6DigitReference;
	}
	private void setCode6DigitReference(String[] code6DigitReference)
	{
		this.code6DigitReference = code6DigitReference;
	}
	public String[] getName6DigitReference()
	{
		return this.name6DigitReference;
	}
	private void setName6DigitReference(String[] name6DigitReference)
	{
		this.name6DigitReference = name6DigitReference;
	}
	public String[] getNom6DigitReference()
	{
		return this.nom6DigitReference;
	}
	private void setNom6DigitReference(String[] nom6DigitReference)
	{
		this.nom6DigitReference = nom6DigitReference;
	}
	//Enregistrement des produits choisis
	public String[] getCodeSection()
	{
		return this.codeSection;
	}
	private void setCodeSection(String[] codeSection)
	{
		this.codeSection = codeSection;
	}
	public String[] getNameSection()
	{
		return this.nameSection;
	}
	private void setNameSection(String[] nameSection)
	{
		this.nameSection = nameSection;
	}
	public String[] getNomSection()
	{
		return this.nomSection;
	}
	private void setNomSection(String[] nomSection)
	{
		this.nomSection = nomSection;
	}
	public String[] getCode2Digit()
	{
		return this.code2Digit;
	}
	private void setCode2Digit(String[] code2Digit)
	{
		this.code2Digit = code2Digit;
	}
	public String[] getName2Digit()
	{
		return this.name2Digit;
	}
	private void setName2Digit(String[] name2Digit)
	{
		this.name2Digit = name2Digit;
	}
	public String[] getNom2Digit()
	{
		return this.nom2Digit;
	}
	private void setNom2Digit(String[] nom2Digit)
	{
		this.nom2Digit = nom2Digit;
	}
	public String[] getCode4Digit()
	{
		return this.code4Digit;
	}
	private void setCode4Digit(String[] code4Digit)
	{
		this.code4Digit = code4Digit;
	}
	public String[] getName4Digit()
	{
		return this.name4Digit;
	}
	private void setName4Digit(String[] name4Digit)
	{
		this.name4Digit = name4Digit;
	}
	public String[] getNom4Digit()
	{
		return this.nom4Digit;
	}
	private void setNom4Digit(String[] nom4Digit)
	{
		this.nom4Digit = nom4Digit;
	}
	public String[] getCode6Digit()
	{
		return this.code6Digit;
	}
	private void setCode6Digit(String[] code6Digit)
	{
		this.code6Digit = code6Digit;
	}
	public String[] getName6Digit()
	{
		return this.name6Digit;
	}
	private void setName6Digit(String[] name6Digit)
	{
		this.name6Digit = name6Digit;
	}
	public String[] getNom6Digit()
	{
		return this.nom6Digit;
	}
	private void setNom6Digit(String[] nom6Digit)
	{
		this.nom6Digit = nom6Digit;
	}
	//Récupération de la liste énumérée sous forme d'un tableau
	public String[] getListeHS()
	{
		return this.listeHS;
	}
	private void setListeHS()
	{
		listeHS = new String[ListeHS.values().length];
		for(int i = 0 ; i < ListeHS.values().length ; ++i)
		{
			listeHS[i] = ListeHS.values()[i].getClassification();
		}
		this.listeHS = listeHS;
	}
	public String[] getNomHS()
	{
		return this.nomHS;
	}
	private void setNomHS()
	{
		nomHS = new String[ListeHS.values().length];
		for(int i = 0 ; i < ListeHS.values().length ; ++i)
		{
			nomHS[i] = ListeHS.values()[i].getNomFichierSynthese();
		}
		this.nomHS = nomHS;
	}
	//Récupération de la liste énumérée sous forme d'un tableau
	public String[] getListeNiveau()
	{
		return this.listeNiveau;
	}
	private void setListeNiveau()
	{
		listeNiveau = new String[ListeNiveau.values().length];
		for(int i = 0 ; i < ListeNiveau.values().length ; ++i)
		{
			listeNiveau[i] = ListeNiveau.values()[i].getClassification();
		}
		this.listeNiveau = listeNiveau;
	}
	public String[] getNomNiveau()
	{
		return this.nomNiveau;
	}
	private void setNomNiveau()
	{
		nomNiveau = new String[ListeNiveau.values().length];
		for(int i = 0 ; i < ListeNiveau.values().length ; ++i)
		{
			nomNiveau[i] = ListeNiveau.values()[i].getNomCSV();
		}
		this.nomNiveau = nomNiveau;
	}
	public int[] getNiveau()
	{
		return this.niveau;
	}
	private void setNiveau()
	{
		niveau = new int[ListeNiveau.values().length];
		for(int i = 0 ; i < ListeNiveau.values().length ; ++i)
		{
			niveau[i] = ListeNiveau.values()[i].getNomNiveau();
		}
		this.nomNiveau = nomNiveau;
	}
}