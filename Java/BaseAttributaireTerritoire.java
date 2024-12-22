//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BaseAttributaireTerritoire
{
	private String choixDesClassifications = "";
	private String[] listeHS = null;
	private String[] fichierSyntheseListeHS = null;
	private int longueurData = 0;
	private String[] titreData = null;
	private String[] codeISO = null;
	private String[] englishName = null;
	private String[] nomFrancais = null;
	private String[] nomContinent = null;
	private String[] borneAnneeDebut = null;
	private String[] borneAnneeFin = null;
	
	public BaseAttributaireTerritoire(String text1)
	{
		setChoixDesClassifications(text1);
		setListeHS();
		setFichierSyntheseListeHS();
		donneesTerritoires();
	}
//*******************************
//*Lecture de la base de données*
//*******************************
	private void donneesTerritoires()
	{
		//Adresse de la liste des fichiers disponible
		String adresse2 = "";
		for(int i = 0 ; i < getListeHS().length ; ++i)
		{
			if(getChoixDesClassifications() == getFichierSyntheseListeHS()[i])
			{
				adresse2 = "Attributs/Synthese-" + getFichierSyntheseListeHS()[i] + "-Territoire-par-annee.csv";
			}
		}
		//Adresse de la liste des territoires
		String adresse = "Attributs/country_names.csv";
		LectureCSV listeTerritoireData = new LectureCSV(adresse, ",");
//		System.out.println("Nombre de lignes : " + listeTerritoireData.getNombreDeLigne());
//		System.out.println("Nombre de colonnes : " + listeTerritoireData.getNombreDeColonne());
//		listeTerritoireData.affichageTitre();//Afficher les titres des champs
//		listeTerritoireData.affichageDonnees();//Afficher les données de la base
		//Récupération des colonnes des données attributaires
		String[][] data = listeTerritoireData.getDonneesTableau();
		String[] titre = listeTerritoireData.getTitreTableau();
		//Individualisation des colonnes
		String[] codeISO2 = new String[data.length];
		String[] englishName2 = new String[data.length];
		String[] nomFrancais2 = new String[data.length];
		String[] nomContinent2 = new String[data.length];
		for(int i = 0 ; i < data.length ; ++i)
		{
			//Code ISO : colonne n°1
			codeISO2[i] = data[i][1];
			//Nom en anglais : colonne n°2
			englishName2[i] = data[i][2];
			//Nom en français : colonne n°3
			nomFrancais2[i] = data[i][3];
			//Nom du continent : colonne n°4
			nomContinent2[i] = data[i][4];
		}		
		//Liste actualisée de la classification choisie
		LectureCSV listeTerritoireHS = new LectureCSV(adresse2, ",");
		String[][] data2 = listeTerritoireHS.getDonneesTableau();
		String[] titre2 = listeTerritoireHS.getTitreTableau();
		//Taille des données attributaires
		int longueurData = data2.length;
		setLongueurDataTerritoire(longueurData);
		//Comparaison avec la liste actualisée de la classification choisie
		ArrayList<Integer> position = new ArrayList<Integer>();
		ArrayList<Integer> position2 = new ArrayList<Integer>();
		for(int i = 0 ; i < data.length ; ++i)
		{
			for(int j = 0 ; j < getLongueurDataTerritoire() ; ++j)
			{
				if(codeISO2[i].equals(data2[j][3]) == true)
				{
					position.add(i);
					position2.add(j);
				}
			}
		}
		borneAnneeDebut = new String[position2.size()];
		borneAnneeFin = new String[position2.size()];
		for(int i = 0 ; i < position2.size() ; ++i)
		{
			borneAnneeDebut[i] = data2[position2.get(i)][0];
			borneAnneeFin[i] = data2[position2.get(i)][1];
		}
		
		//Individualisation des colonnes
		codeISO = new String[getLongueurDataTerritoire()];
		englishName = new String[getLongueurDataTerritoire()];
		nomFrancais = new String[getLongueurDataTerritoire()];
		nomContinent = new String[getLongueurDataTerritoire()];
		for(int i = 0 ; i < position.size() ; ++i)
		{
			//Code ISO : colonne n°1
			codeISO[i] = codeISO2[position.get(i)];
			//Nom en anglais : colonne n°2
			englishName[i] = englishName2[position.get(i)];
			//Nom en français : colonne n°3
			nomFrancais[i] = nomFrancais2[position.get(i)];
			//Nom du continent : colonne n°4
			nomContinent[i] = nomContinent2[position.get(i)];
		}
		//Enregistrement des variables d'instance
		setTitreData(titre);
		setCodeISO(codeISO);
		setEnglishName(englishName);
		setNomFrancais(nomFrancais);
		setNomContinent(nomContinent);
		setBorneAnneeDebut(borneAnneeDebut);
		setBorneAnneeFin(borneAnneeFin);
	}
//*************************************
//*Affichage du tableau sur la console*
//*************************************
	public void affichageTableau()
	{
		int longueurData = getCodeISO().length;
		for(int i = 0 ; i < longueurData ; ++i)
		{
			int num = i + 1;
			System.out.println(num + "," + getCodeISO()[i] + "," + getEnglishName()[i] + "," + getNomFrancais()[i] + "," + getNomContinent()[i]);
		}
	}
	public String affichageFenetreTableauCSV()
	{
		int longueurData = getCodeISO().length;
		String[] tableauDonnees = new String[longueurData];
		for(int i = 0 ; i < longueurData ; ++i)
		{
			int num = i + 1;
			tableauDonnees[i] = num + "," + getCodeISO()[i] + "," + getEnglishName()[i] + "," + getNomFrancais()[i] + "," + getNomContinent()[i];
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
		int longueurData = getCodeISO().length;
		//Traitement des titres du tableau de données
		String[] titreDonnees = getTitreData();
		ArrayList<String> titre = new ArrayList<String>();
		titre.add("ID");
		for(int i = 1 ; i < getTitreData().length ; ++i)
		{
			titre.add(getTitreData()[i]);
		}
			//Mise à zéro du tableau des titres
			String[] titreDonnees2 = new String[titre.size()];
			//Fin du traitement des titres du tableau de données
			for(int i = 0 ; i < titreDonnees.length ; ++i)
			{
				titreDonnees2[i] = titre.get(i);
			}
		//Constitution du tableau
		Object[][] tableauDonnees = new Object[longueurData][titreDonnees2.length];
		for(int i = 0 ; i < longueurData ; ++i)
		{
			int num = i + 1;
			tableauDonnees[i][0] = num;//Integer.toString(num);
			tableauDonnees[i][1] = getCodeISO()[i];
			tableauDonnees[i][2] = getEnglishName()[i];
			tableauDonnees[i][3] = getNomFrancais()[i];
			tableauDonnees[i][4] = getNomContinent()[i];
		}
		//Affichage de la fenêtre du tableau
		JFrame fenetreDonnees = new JFrame();
		fenetreDonnees.setTitle("Liste des donn\u00e9es");
		fenetreDonnees.setVisible(true);
		fenetreDonnees.setSize(700,500);
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
//********************
//*Getters et Setters*
//********************
	public String getChoixDesClassifications()
	{
		return this.choixDesClassifications;
	}
	private void setChoixDesClassifications(String nomClassification)
	{
		this.choixDesClassifications = nomClassification;
	}
	public int getLongueurDataTerritoire()
	{
		return this.longueurData;
	}
	private void setLongueurDataTerritoire(int longueurData)
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
	public String[] getCodeISO()
	{
		return this.codeISO;
	}
	private void setCodeISO(String[] codeISO)
	{
		this.codeISO = codeISO;
	}
	public String[] getEnglishName()
	{
		return this.englishName;
	}
	private void setEnglishName(String[] englishName)
	{
		this.englishName = englishName;
	}
	public String[] getNomFrancais()
	{
		return this.nomFrancais;
	}
	private void setNomFrancais(String[] nomFrancais)
	{
		this.nomFrancais = nomFrancais;
	}
	public String[] getNomContinent()
	{
		return this.nomContinent;
	}
	private void setNomContinent(String[] nomContinent)
	{
		this.nomContinent = nomContinent;
	}
	public String[] getBorneAnneeDebut()
	{
		return this.borneAnneeDebut;
	}
	private void setBorneAnneeDebut(String[] borneAnneeDebut)
	{
		this.borneAnneeDebut = borneAnneeDebut;
	}
	public String[] getBorneAnneeFin()
	{
		return this.borneAnneeFin;
	}
	private void setBorneAnneeFin(String[] borneAnneeFin)
	{
		this.borneAnneeFin = borneAnneeFin;
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
	public String[] getFichierSyntheseListeHS()
	{
		return this.fichierSyntheseListeHS;
	}
	private void setFichierSyntheseListeHS()
	{
		fichierSyntheseListeHS = new String[ListeHS.values().length];
		for(int i = 0 ; i < ListeHS.values().length ; ++i)
		{
			fichierSyntheseListeHS[i] = ListeHS.values()[i].getNomFichierSynthese();
		}
		this.fichierSyntheseListeHS = fichierSyntheseListeHS;
	}
}