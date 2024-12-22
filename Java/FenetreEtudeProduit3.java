//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
//Programme corrigé en juin 2022 afin d'y introduire les noms français des niveaux 4-digit et 6-digit et les noms anglais des niveaux Section
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreEtudeProduit3 extends JFrame
{
	private String choixDesClassifications = null;
	private String choixDesNiveaux = null;
	private int choixDesNiveauxInt = 0;
	private int anneeDebut = 0;
	private int anneeFin = 0;
	private int[] annee = null;
	private String[] codeProduit = null;
	private String[] listeHS = null;
	private String[] nomHS = null;
	private String[] nomDossierHS = null;
	private String[] listeNiveau = null;
	private String[] nomNiveau = null;
	private int[] niveau = null;
	private String[] sousDossierPartie1 = null;
	private String[] sousDossierPartie2 = null;
	private String dossier = null;
	private String sousDossierP1 = null;
	private String sousDossierP2 = null;
	private String sousDossierP3 = null;
	private String sousSousDossier = "Bd-Produit\\";
	private BaseAttributaireProduit baseAttributaireProduit = null;
	private String[] baseAttributaireProduitCodeProduit = null;
	private String[] baseAttributaireProduitNomProduit = null;
	private String[] baseAttributaireProduitNameProduct = null;
	private String labelProduit = "";	
	private Container contentPane = null;
	private JPanel panel3 = null;
	private JButton boutonRetour = null;
	private JButton boutonSuite = null;
	private JButton boutonAnnuler = null;
	private JButton boutonAffichageDonnees  = null;

	private String[] nomFichierALire = null;
	private String titreExtraction = "";
	private String donneesExtraction = "";
	private String[] titreExtractionTableau = null;
	private String[][] donneesExtractionTableau = null;
	
	public FenetreEtudeProduit3(String text1, String text2, String[] text3, int anneeDebut, int anneeFin)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text1);
		setChoixDesNiveaux(text2);
		setProduit(text3);
		setAnneeDebut(anneeDebut);
		setAnneeFin(anneeFin);
		//Initialisation des données
		init();
		//Affichage sur la console des données
//		affichageDonneesRecuperees();
//		affichageDonneesAttributaires();
		//Chargement des fichiers
		chargementFichierALire();
		//Affichage sur la console des fichiers à lire
//		affichageFichiersALire();
		//Extraction de la requête
		lectureBaseDeDonnees();
		//Affichage de la requête sur console
//		System.out.println(getTitreExtraction());
//		System.out.println(getDonneesExtraction());
		setLabelProduit();
		/*
		//****************************************************************
		//Affichage des données du tableau
		for(int i = 0 ; i < getTitreExtractionTableau().length ; ++i)
		{
			System.out.println(getTitreExtractionTableau()[i]);
		}
		for(int i = 0 ; i < getDonneesExtractionTableau().length ; ++i)
		{
			int taille = getDonneesExtractionTableau()[i].length;
			for(int j = 0 ; j < taille ; ++j)
:			{
				System.out.println(getDonneesExtractionTableau()[i][j]);
			}
		}
		//****************************************************************
		*/
		//Initialisation de la fenêtre
		this.setTitle("\u00c9tude des types de produits : Extraction");
		this.setSize(900,600);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new Fermeture());
		//Couche de contenu du Frame
		contentPane = this.getContentPane();
		//Corps central
		contentPane.add("Center", zoneMouvante());//Insertion du JPanel principal
		//Boutons de fin
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		boutonRetour = new JButton("Retour");
		boutonSuite = new JButton("Enregistrement");
		boutonAnnuler = new JButton("Fermer");
		panel3.add(boutonRetour);
		panel3.add(boutonSuite);
		panel3.add(boutonAnnuler);
		contentPane.add("South", panel3);
		//Evénements
		boutonRetour.addActionListener(new ClicRetour());//Action du bouton "Retour"
		boutonSuite.addActionListener(new ClicSuivant());//Action du bouton "Enregistrement"
		boutonAnnuler.addActionListener(new ClicAnnuler());//Action du bouton "Fermer"
	}
//***********************
//*Gestion de la fenêtre*
//***********************
	private JPanel zoneMouvante()
	{
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(4, 2));
		//Gestion de l'objet : Choix des classifications
		panel1.add(new Label("Choix de la classification : "));
		panel1.add(new Label(getChoixDesClassifications()));
		//Gestion de l'objet : Choix des niveaux
		panel1.add(new Label("Choix du niveau d'analyse : "));
		panel1.add(new Label(getChoixDesNiveaux()));
		//Gestion de l'objet : Choix des produits
		panel1.add(new Label("Choix des produits : "));
		JTextArea zoneDeTexteChoixProduits = new JTextArea(getLabelProduit());
		zoneDeTexteChoixProduits.setEditable(false);//Interdire la modification du texte de la zone
		zoneDeTexteChoixProduits.setBackground(new Color(240,240,240));//Couleur du fond
		zoneDeTexteChoixProduits.setForeground(Color.GRAY.brighter());//Couleur du texte
		panel1.add(new JScrollPane(zoneDeTexteChoixProduits));
		//Gestion du bouton : Afficher les résultats
		JPanel boutonAffichageDonneesPanel = new JPanel();
		boutonAffichageDonneesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		boutonAffichageDonnees = new JButton("Afficher les donn\u00e9es extraites");
		boutonAffichageDonneesPanel.setLayout(new BoxLayout(boutonAffichageDonneesPanel, BoxLayout.LINE_AXIS));
		boutonAffichageDonneesPanel.add(boutonAffichageDonnees);
		boutonAffichageDonneesPanel.add(Box.createRigidArea(new Dimension(50,100)));
		panel1.add(boutonAffichageDonneesPanel);
		boutonAffichageDonnees.addActionListener(new BoutonAffichageDonnees());
		return panel1;
	}
//*************************************
//*Gestion de la fenêtre des résultats*
//*************************************
	private void tableauExtrait()
	{
		//Affichage de la fenêtre du tableau
		JFrame fenetreDonnees = new JFrame();
		fenetreDonnees.setTitle("\u00c9tude des types de produits : Extraction");
		fenetreDonnees.setVisible(true);
		fenetreDonnees.setSize(900,600);
		fenetreDonnees.setLocationRelativeTo(null);
		fenetreDonnees.setResizable(true);
		fenetreDonnees.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container conteneurFenetreDonnees = fenetreDonnees.getContentPane();
		JPanel panel = new JPanel(new BorderLayout());
		JTable tableau = new JTable(getDonneesExtractionTableau(), getTitreExtractionTableau());
		JScrollPane scroll = new JScrollPane(tableau, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(scroll, BorderLayout.CENTER);
		conteneurFenetreDonnees.add(panel);
	}
	public String getLabelProduit()
	{
		return this.labelProduit;
	}
	private void setLabelProduit()
	{
		String[] codeProduit = new String[getProduit().length];
		String[] nomProduit = new String[getProduit().length];
		for(int i = 0 ; i < getProduit().length ; ++i)
		{
			codeProduit[i] = getProduit()[i];
		}		
		ArrayList<Integer> positionNomProduit = new ArrayList<Integer>();
		for(int i = 0 ; i < codeProduit.length ; ++i)
		{
			for(int j = 0 ; j < getBaseAttributaireProduitCodeProduit().length ; ++j)
			{
				if(codeProduit[i].equals(getBaseAttributaireProduitCodeProduit()[j]))
				{
					positionNomProduit.add(j);
				}
			}
		}
		for(int i = 0 ; i < positionNomProduit.size() ; ++i)
		{
			nomProduit[i] = getBaseAttributaireProduitNomProduit()[positionNomProduit.get(i)];
		}
		String labelProduit2 = "";
		for(int i = 0 ; i < codeProduit.length ; ++i)
		{
			if(i < codeProduit.length - 1)
			{
				labelProduit2 = labelProduit2 + nomProduit[i] + " (" + codeProduit[i] + ")\n";
			}
			else
			{
				labelProduit2 = labelProduit2 + nomProduit[i] + " (" + codeProduit[i] + ")";
			}
		}
		this.labelProduit = labelProduit2;
	}
//*******************************************
//*Initialisation des getters et des setters*
//*******************************************
	private void init()
	{
		//Chargement des bases énumérées
		setListeHS();
		setNomHS();
		setNomDossierHS();
		setListeNiveau();
		setNiveau();
		setSousDossierPartie1();
		setSousDossierPartie2();
		//Création des noms de dossier à ouvrir
		for(int i = 0 ; i < getListeHS().length ; ++i)
		{
			if(getListeHS()[i] == getChoixDesClassifications())
			{
				sousDossierP2 = getNomHS()[i];
				dossier = getNomDossierHS()[i];
			}
		}
		for(int i = 0 ; i < getListeNiveau().length ; ++i)
		{
			if(getListeNiveau()[i] == getChoixDesNiveaux())
			{
				choixDesNiveauxInt = getNiveau()[i];
				sousDossierP1 = getSousDossierPartie1()[i];
				sousDossierP3 = getSousDossierPartie2()[i];
			}
		}
		setDossier(dossier);
		setSousDossierP1(sousDossierP1);
		setSousDossierP2(sousDossierP2);
		setSousDossierP3(sousDossierP3);
		setChoixDesNiveauxInt(choixDesNiveauxInt);
		//Création d'un tableau de dates
		ArrayList<Integer> annee2 = new ArrayList<Integer>();
		for(int i = getAnneeDebut() ; i <= getAnneeFin() ; ++i)
		{
			annee2.add(i);
		}
		annee = new int[annee2.size()];
		for(int i = 0 ; i < annee2.size() ; ++i)
		{
			annee[i] = annee2.get(i);
		}
		setAnnee(annee);
		//Chargement des attributs des produits
		donneesProduits();
	}
	//Chargement des données attributaires
	private void donneesProduits()
	{
		baseAttributaireProduit = new BaseAttributaireProduit(getChoixDesClassifications(), getChoixDesNiveaux());
		setBaseAttributaireProduit(baseAttributaireProduit);
		if(getChoixDesNiveauxInt() == 0)
		{
			setBaseAttributaireProduitCodeProduit(baseAttributaireProduit.getCodeSection());
			setBaseAttributaireProduitNomProduit(baseAttributaireProduit.getNomSection());
			setBaseAttributaireProduitNameProduct(baseAttributaireProduit.getNameSection());
		}
		else
		{
			if(getChoixDesNiveauxInt() == 2)
			{
				setBaseAttributaireProduitCodeProduit(baseAttributaireProduit.getCode2Digit());
				setBaseAttributaireProduitNomProduit(baseAttributaireProduit.getNom2Digit());
				setBaseAttributaireProduitNameProduct(baseAttributaireProduit.getName2Digit());
			}
			else
			{
				if(getChoixDesNiveauxInt() == 4)
				{
					setBaseAttributaireProduitCodeProduit(baseAttributaireProduit.getCode4Digit());
					setBaseAttributaireProduitNomProduit(baseAttributaireProduit.getName4Digit());
					setBaseAttributaireProduitNameProduct(baseAttributaireProduit.getName4Digit());
				}
				else
				{
					if(getChoixDesNiveauxInt() == 6)
					{
						setBaseAttributaireProduitCodeProduit(baseAttributaireProduit.getCode6Digit());
						setBaseAttributaireProduitNomProduit(baseAttributaireProduit.getName6Digit());
						setBaseAttributaireProduitNameProduct(baseAttributaireProduit.getName6Digit());
					}
					else
					{
					
					}
				}
			}
		}
	}
	//Création des fichiers à lire
	private void chargementFichierALire()
	{
		String adresse = getDossier() + getSousDossierP1() + getSousDossierP2() + getSousDossierP3() + sousSousDossier;
		ArrayList<String> nomFichier = new ArrayList<String>();
		for(int i = 0 ; i < getAnnee().length ; ++i)
		{
			String adresse2 = adresse + getAnnee()[i] + ".csv";
			nomFichier.add(adresse2);
		}
		nomFichierALire = new String[nomFichier.size()];
		for(int i = 0 ; i < nomFichier.size() ; ++i)
		{
			nomFichierALire[i] = nomFichier.get(i);
		}
		setNomFichierALire(nomFichierALire);
	}
	//Extraction des données
	private void lectureBaseDeDonnees()
	{
		for(int k = 0 ; k < getNomFichierALire().length ; ++k)
		{
			LectureCSV listeData = new LectureCSV(getNomFichierALire()[k], ",");		
//			System.out.println("Nombre de lignes : " + listeData.getNombreDeLigne());
//			System.out.println("Nombre de colonnes : " + listeData.getNombreDeColonne());
//			listeData.affichageTitre();//Afficher les titres des champs
//			listeData.affichageDonnees();//Afficher les données de la base
			//Récupération des données
			String[] datatitre = listeData.getTitreTableau();
			String[][] data = listeData.getDonneesTableau();
			int numColonne = listeData.getNombreDeColonne();
			//Initialisation des titres de l'extraction
			if(k == 0)
			{
				String inittitre = "";
				for(int i = 0 ; i < datatitre.length ; ++i)
				{
					if(i != datatitre.length - 1)
					{
						inittitre = inittitre + datatitre[i] + ",";
					}
					else
					{
						inittitre = inittitre + datatitre[i];
					}
				}
				setTitreExtraction(inittitre);
				setTitreExtractionTableau(datatitre);
			}
			//Récupération de la colonne du code des produits
			String[] codeProduit = new String[data.length];
			for(int i = 0 ; i < data.length ; ++i)
			{
				codeProduit[i] = data[i][0];
			}
			//Sélection des produits étudiés
			ArrayList<Integer> position = new ArrayList<Integer>();
			for(int i = 0 ; i < codeProduit.length ; ++i)
			{
				for(int j = 0 ; j < getProduit().length ; ++j)
				{
					if(codeProduit[i].equals(getProduit()[j]))
					{
						position.add(i);
					}
				}
			}
			String[][] dataSortie = new String[position.size()][numColonne];
			for(int i = 0 ; i < position.size() ; ++i)
			{
				for(int j = 0 ; j < numColonne ; ++j)
				{
					dataSortie[i][j] = data[position.get(i)][j];
				}
			}
			//Insertion des noms des produits
			ArrayList<Integer> positionNomProduit = new ArrayList<Integer>();
			for(int i = 0 ; i < dataSortie.length ; ++i)
			{
				for(int j = 0 ; j < getBaseAttributaireProduitCodeProduit().length ; ++j)
				{
					if(dataSortie[i][0].equals(getBaseAttributaireProduitCodeProduit()[j]))
					{
						positionNomProduit.add(j);
					}
				}
			}
			for(int i = 0 ; i < positionNomProduit.size() ; ++i)
			{
				dataSortie[i][1] = getBaseAttributaireProduitNomProduit()[positionNomProduit.get(i)];
			}
			//Ecriture du fichier d'extraction
			String initSortie = "";
			for(int i = 0 ; i < dataSortie.length ; ++i)
			{
				String init = "";
				for(int j = 0 ; j < numColonne ; ++j)
				{
					if(j != numColonne - 1)
					{
						init = init + dataSortie[i][j] + ",";
					}
					else
					{
						init = init + dataSortie[i][j] + "\n";
					}
				}
				initSortie = init;
			}
			setDonneesExtraction(initSortie);
			//Ecriture du tableau d'extraction
			String[][] tabExtraction = new String[dataSortie.length][numColonne];
			for(int i = 0 ; i < dataSortie.length ; ++i)
			{
				for(int j = 0 ; j < numColonne ; ++j)
				{
					tabExtraction[i][j] = dataSortie[i][j];
				}
			}
			//Récupération des données antérieures
			setDonneesExtractionTableau(tabExtraction, numColonne);
		}
	}
//**************************************
//*Affichage sur la console des données*
//**************************************
	private void affichageDonneesRecuperees()
	{
		System.out.println("Classification choisie : " + getChoixDesClassifications());
		System.out.println("Niveau choisi : " + getChoixDesNiveaux() + " ("  + getChoixDesNiveauxInt() + ")");
		System.out.print("Entre " + getAnneeDebut() + " et " + getAnneeFin() + " : ");
		for(int i = 0 ; i < getAnnee().length ; ++i)
		{
			if(i < getAnnee().length - 1)
			{
				System.out.print(getAnnee()[i] + ",");
			}
			else
			{
				System.out.print(getAnnee()[i] + "\n");
			}
		}
		System.out.print("Liste des produits retenus : ");
		for(int i = 0 ; i < getProduit().length ; ++i)
		{
			if(i < getProduit().length - 1)
			{
				System.out.print(getProduit()[i] + ",");
			}
			else
			{
				System.out.print(getProduit()[i] + "\n");
			}
		}
	}
	private void affichageDonneesAttributaires()
	{
		System.out.println("Attributs des produits : ");
		for(int i = 0 ; i < getBaseAttributaireProduitCodeProduit().length ; ++i)
		{
			System.out.println(getBaseAttributaireProduitCodeProduit()[i] + "," + getBaseAttributaireProduitNomProduit()[i]);
		}
	}
	private void affichageFichiersALire()
	{
		System.out.println("Adresse des fichiers lus :");
		for(int i = 0 ; i < getNomFichierALire().length ; ++i)
		{
			System.out.println(getNomFichierALire()[i]);
		}
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
	public int getAnneeDebut()
	{
		return this.anneeDebut;
	}
	private void setAnneeDebut(int anneeDebut)
	{
		this.anneeDebut = anneeDebut;
	}
	public int getAnneeFin()
	{
		return this.anneeFin;
	}
	private void setAnneeFin(int anneeFin)
	{
		this.anneeFin = anneeFin;
	}
	public int[] getAnnee()
	{
		return this.annee;
	}
	private void setAnnee(int[] annee)
	{
		this.annee = annee;
	}	
	public String[] getProduit()
	{
		return this.codeProduit;
	}
	private void setProduit(String[] produit)
	{
		this.codeProduit = produit;
	}
	public int getChoixDesNiveauxInt()
	{
		return this.choixDesNiveauxInt;
	}
	private void setChoixDesNiveauxInt(int nomDuNiveau)
	{
		this.choixDesNiveauxInt = nomDuNiveau;
	}
	public String getDossier()
	{
		return this.dossier;
	}
	private void setDossier(String dossier)
	{
		this.dossier = dossier;
	}
	public String getSousDossierP1()
	{
		return this.sousDossierP1;
	}
	private void setSousDossierP1(String sousDossierP1)
	{
		this.sousDossierP1 = sousDossierP1;
	}
	public String getSousDossierP2()
	{
		return this.sousDossierP2;
	}
	private void setSousDossierP2(String sousDossierP2)
	{
		this.sousDossierP2 = sousDossierP2;
	}
	public String getSousDossierP3()
	{
		return this.sousDossierP3;
	}
	private void setSousDossierP3(String sousDossierP3)
	{
		this.sousDossierP3 = sousDossierP3;
	}
	//Base de données des produits
	public BaseAttributaireProduit getBaseAttributaireProduit()
	{
		return this.baseAttributaireProduit;
	}
	private void setBaseAttributaireProduit(BaseAttributaireProduit baseProduit)
	{
		this.baseAttributaireProduit = baseProduit;
	}
	public String[] getBaseAttributaireProduitCodeProduit()
	{
		return this.baseAttributaireProduitCodeProduit;
	}
	private void setBaseAttributaireProduitCodeProduit(String[] code)
	{
		this.baseAttributaireProduitCodeProduit = code;
	}
	public String[] getBaseAttributaireProduitNomProduit()
	{
		return this.baseAttributaireProduitNomProduit;
	}
	private void setBaseAttributaireProduitNomProduit(String[] nomProduit)
	{
		this.baseAttributaireProduitNomProduit = nomProduit;
	}
	public String[] getBaseAttributaireProduitNameProduct()
	{
		return this.baseAttributaireProduitNameProduct;
	}
	private void setBaseAttributaireProduitNameProduct(String[] nomProduit)
	{
		this.baseAttributaireProduitNameProduct = nomProduit;
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
	public String[] getNomDossierHS()
	{
		return this.nomDossierHS;
	}
	private void setNomDossierHS()
	{
		nomDossierHS = new String[ListeHS.values().length];
		for(int i = 0 ; i < ListeHS.values().length ; ++i)
		{
			nomDossierHS[i] = ListeHS.values()[i].getDossier();
		}
		this.nomDossierHS = nomDossierHS;
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
	public String[] getSousDossierPartie1()
	{
		return this.sousDossierPartie1;
	}
	private void setSousDossierPartie1()
	{
		sousDossierPartie1 = new String[ListeNiveau.values().length];
		for(int i = 0 ; i < ListeNiveau.values().length ; ++i)
		{
			sousDossierPartie1[i] = ListeNiveau.values()[i].getSousDossierP1();
		}
		this.sousDossierPartie1 = sousDossierPartie1;
	}
	public String[] getSousDossierPartie2()
	{
		return this.sousDossierPartie2;
	}
	private void setSousDossierPartie2()
	{
		sousDossierPartie2 = new String[ListeNiveau.values().length];
		for(int i = 0 ; i < ListeNiveau.values().length ; ++i)
		{
			sousDossierPartie2[i] = ListeNiveau.values()[i].getSousDossierP2();
		}
		this.sousDossierPartie2 = sousDossierPartie2;
	}
	//Fichiers à lire
	public String[] getNomFichierALire()
	{
		return this.nomFichierALire;
	}
	private void setNomFichierALire(String[] table)
	{
		this.nomFichierALire = table;
	}
	//Extraction
	public String getTitreExtraction()
	{
		return this.titreExtraction;
	}
	private void setTitreExtraction(String table)
	{
		this.titreExtraction = table;
	}
	public String getDonneesExtraction()
	{
		return this.donneesExtraction;
	}
	private void setDonneesExtraction(String table)
	{
		this.donneesExtraction = this.donneesExtraction + table;
	}
	public String[] getTitreExtractionTableau()
	{
		return this.titreExtractionTableau;
	}
	private void setTitreExtractionTableau(String[] table)
	{
		this.titreExtractionTableau = table;
	}
	public String[][] getDonneesExtractionTableau()
	{
		return this.donneesExtractionTableau;
	}
	private void setDonneesExtractionTableau(String[][] tabExtraction, int numColonne)
	{
		if(getDonneesExtractionTableau() != null)
		{
			String[][] tabExtraction2 = new String[getDonneesExtractionTableau().length + tabExtraction.length][numColonne];
			for(int i = 0 ; i < getDonneesExtractionTableau().length ; ++i)
			{
				int taille = numColonne;
				for(int j = 0 ; j < taille ; ++j)
				{
					tabExtraction2[i][j] = getDonneesExtractionTableau()[i][j];
				}
			}
			for(int i = getDonneesExtractionTableau().length ; i < getDonneesExtractionTableau().length + tabExtraction.length ; ++i)
			{
				int taille = numColonne;
				for(int j = 0 ; j < taille ; ++j)
				{
					tabExtraction2[i][j] = tabExtraction[i - getDonneesExtractionTableau().length][j];
				}
			}
			this.donneesExtractionTableau = tabExtraction2;
		}	
		else
		{
			this.donneesExtractionTableau = tabExtraction;
		}
	}
//************************
//*Gestion des événements*
//************************
	class ClicRetour implements ActionListener
	{
		//Méthode obligatoire à implanter avec l'interface ActionEvent
		public void actionPerformed(ActionEvent e)
		{
			if (boutonRetour.isEnabled()) 
			{
				FenetreEtudeProduit2 renvoiDonnees = new FenetreEtudeProduit2(getChoixDesClassifications(), getChoixDesNiveaux(), getProduit(), getAnneeDebut(), getAnneeFin());
				renvoiDonnees.setVisible(true);
				dispose();
			}
		}
	}
	//Enregistrement des données
	private void ecrire(String fileContent)
	{
		DateMaintenant dateMaintement = new DateMaintenant();
		String adresse = getDossier() + fileContent + "-" + dateMaintement.getDateMaintenant() + ".csv";
		try
		{
			FileWriter writer = new FileWriter(adresse);//Mettre l'adresse
			//Ajout de la nouvelle donnée
			String titre = "";
			for(int i = 0 ; i < getTitreExtractionTableau().length ; i++)
			{
				if(i != getTitreExtractionTableau().length - 1)
				{
					titre = titre + "\"" + getTitreExtractionTableau()[i] + "\"" + ",";
				}
				else
				{
					titre = titre + "\"" + getTitreExtractionTableau()[i] + "\"" + "\n";
				}
			}
			String donnees = "";
			for(int i = 0 ; i < getDonneesExtractionTableau().length ; ++i)
			{
				int numColonne = getDonneesExtractionTableau()[i].length;
				for(int j = 0 ; j < numColonne ; ++j)
				{
					if(j != numColonne - 1)
					{
						donnees = donnees + "\"" + getDonneesExtractionTableau()[i][j] + "\"" + ",";
					}
					else
					{
						donnees = donnees + "\"" + getDonneesExtractionTableau()[i][j] + "\"" + "\n";
					}
				}
			}
			String tableauAEnregistrer = titre + donnees;
			//Enregistrement de la nouvelle donnée
			writer.write(tableauAEnregistrer);
			System.out.println("Enregistrement effectu\u00e9");
			writer.close();
			JOptionPane informationMessage = new JOptionPane();
			informationMessage.showMessageDialog(null, "Votre fichier CSV a \u00e9t\u00e9 enregistr\u00e9 dans le dossier " + getDossier() + "\nVous pouvez faire une autre extraction.", "Extraction enregistr\u00e9e", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(IOException e) {e.printStackTrace();}
		catch(ArrayIndexOutOfBoundsException e) {e.printStackTrace();}
	}
	class ClicSuivant implements ActionListener
	{
		//Méthode obligatoire à implanter avec l'interface ActionEvent
		public void actionPerformed(ActionEvent e)
		{
			String nomDuFichier = "Extraction-Produit-" + getChoixDesClassifications() + "-" + getChoixDesNiveaux();
			ecrire(nomDuFichier);
			FenetreChoixEtude renvoiDonnees = new FenetreChoixEtude();
			renvoiDonnees.setVisible(true);
			dispose();
		}
	}
	private void messageDeFermeture()
	{
		JOptionPane boiteDeFermeture = new JOptionPane();
		int boiteDeFermetureValeur = boiteDeFermeture.showConfirmDialog(null, "Vous n'avez pas enregistr\u00e9 votre extraction.\nVous lancez une nouvelle extraction.", "Retour", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(boiteDeFermetureValeur != JOptionPane.CANCEL_OPTION)
		{
			FenetreChoixEtude renvoiDonnees = new FenetreChoixEtude();
			renvoiDonnees.setVisible(true);
			dispose();
		}
		else
		{
			//Empêcher la fermeture de la fenêtre parente et la laisser visible !
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setVisible(true);
		}
	}
	class ClicAnnuler implements ActionListener
	{
		//Méthode obligatoire à implanter avec l'interface ActionEvent
		public void actionPerformed(ActionEvent e)
		{
			messageDeFermeture();
		}
	}
	class BoutonAffichageDonnees implements ActionListener
	{
		//Méthode obligatoire à implanter avec l'interface ActionEvent
		public void actionPerformed(ActionEvent e)
		{
			if (boutonAffichageDonnees.isEnabled()) 
			{
				tableauExtrait();
//				dispose();
			}
		}
	}
	class Fermeture implements WindowListener
	{
		public void windowOpened(WindowEvent e)
		{}
		public void windowClosing(WindowEvent e)
		{
			messageDeFermeture();
		}
		public void windowClosed(WindowEvent e)
		{}
		public void windowActivated(WindowEvent e)
		{}
		public void windowDeactivated(WindowEvent e)
		{}    
		public void windowDeiconified(WindowEvent e)
		{}
		public void windowIconified(WindowEvent e)
		{}
	}
}