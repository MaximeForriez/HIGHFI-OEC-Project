//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
//Programme corrigé en juin 2022 afin d'y introduire les noms français des niveaux 4-digit et 6-digit et les noms anglais des niveaux Section
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoiteACocherProduits extends JFrame
{
	private BaseAttributaireProduit baseAttributaireProduit = null;
	private String choixDesClassifications = null;
	private String choixDesNiveaux = null;
	private String[] territoire = null;
	private int anneeDebut = 0;
	private int anneeFin = 0;
	private String[] listeHS = null;
	private String[] nomHS = null;
	private String[] listeNiveau = null;
	private String[] nomNiveau = null;
	private int[] niveau = null;
	private int choixDesNiveauxInt = 0;
	private String[] codeSection = null;
	private String[] code2Digit = null;
	private String[] code4Digit = null;
	private int[] coupureTab = null;
	private int[] coupureTab2 = null;
	private String[] code6Digit = null;
	private int longueurData = 0;
	private String[] titreData = null;
	private String[] codeSectionReference = null;
	//Ajout de la variable en juin 2022
	private String[] nameSectionReference = null;
	//
	private String[] nomSectionReference = null;
	private String[] code2DigitReference = null;
	private String[] name2DigitReference = null;
	private String[] nom2DigitReference = null;
	private String[] code4DigitReference = null;
	private String[] name4DigitReference = null;
	//Ajout de la variable en juin 2022
	private String[] nom4DigitReference = null;
	//
	private String[] code6DigitReference = null;
	private String[] name6DigitReference = null;
	//Ajout de la variable en juin 2022
	private String[] nom6DigitReference = null;
	//
	private JCheckBox option = null;
	private JCheckBox[] casesACocher = null;
	private String[] liste = null;
	private JButton boutonOK = null;
	private String[] enregistrementProduit = null;
//***********************
//*Gestion de la fenêtre*
//***********************
	public BoiteACocherProduits(String text, String text2, String[] text3, int anneeDebut, int anneeFin)
	{
		//Récupération de la valeur de la classification
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setTerritoire(text3);
		setAnneeDebut(anneeDebut);
		setAnneeFin(anneeFin);
		setListeHS();
		setNomHS();
		setListeNiveau();
		setNomNiveau();
		setNiveau();
		//Initialisation des données attributaires des produits
		donneesProduits();
		//Gestion de la fenêtre
		this.setTitle("\u00c9tude territoriale : Choix des produits");
		this.setSize(800,700);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new Fermeture());
		//Initialisation des données
		init();
		option.addItemListener(new ActionClassification());
		for(int i = 0 ; i < casesACocher.length ; ++i)
		{
			casesACocher[i].addItemListener(new ActionClassification2());
		}
		//Affichage des données attributaires produits sur la console et en JTable
//		affichageTableau();
//		System.out.println(affichageFenetreTableauCSV());
//		affichageFenetreTableau();
//		affichageFenetreTableauSimplifiee();
	}
//****************************************
//*Initialisation des variables de classe*
//****************************************
	private void init()
	{
		//Couche de contenu du Frame
		Container conteneur = this.getContentPane();
		JPanel panel = new JPanel(new BorderLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBounds(100, 100, 100, 100);
		if(getChoixDesNiveauxInt() == 0)
		{
			panel.add(getCasesACocherSection());
		}
		else
		{
			if(getChoixDesNiveauxInt() == 2)
			{
				panel.add(getCasesACocher2Digit());
			}
			else
			{
				if(getChoixDesNiveauxInt() == 4)
				{
					panel.add(getCasesACocher4Digit());
				}
				else
				{
					if(getChoixDesNiveauxInt() == 6)
					{
						panel.add(getCasesACocher6Digit());
					}
					else
					{
						panel.add(new Label("Pas de liste !"));
					}
				}
			}
		}
		//Bouton OK
		boutonOK = new JButton("OK");
		panel.add(boutonOK);
		boutonOK.addActionListener(new ClicBoutonOK());
		//Gestion des ascenseurs de la fenêtre
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		scroll.setPreferredSize(new Dimension(800,700));
        scroll.setViewportView(panel);
		conteneur.add(scroll, BorderLayout.CENTER);
	}
//*****************************
//*Cases à cocher des produits*
//*****************************
	private JPanel getCasesACocherSection()
	{
		String[] liste2 = new String[getLongueurDataProduit()];
		for(int i = 0 ; i < getLongueurDataProduit() ; ++i)
		{
			liste2[i] = getNomSectionReference()[i] + " (" + getCodeSectionReference()[i] + ")";
		}
		ArrayList<String> resultat = new ArrayList<String>();
		resultat.add(liste2[0]);
		for(int i = 1 ; i < liste2.length ; ++i)
		{
			if(!liste2[i - 1].equals(liste2[i]))
			{
				resultat.add(liste2[i]);
			}
		}
		liste = new String[resultat.size()];
		JPanel panel = new JPanel(new GridLayout(liste.length + 1, 1));
		option = new JCheckBox("Tous", false);
		panel.add(option);
		for(int i = 0 ; i < resultat.size() ; ++i)
		{
			liste[i] = resultat.get(i);
		}
		casesACocher = new JCheckBox[liste.length];
		for(int i = 0 ; i < liste.length ; ++i)
		{
			casesACocher[i] = new JCheckBox(liste[i], false);
			panel.add(casesACocher[i]);
		}
//		toutCocher();//Génère un bogue de type "flux"
		return panel;
	}
	private JPanel getCasesACocher2Digit()
	{
		String[] liste2 = new String[getLongueurDataProduit()];
		String[] liste3 = new String[getLongueurDataProduit()];
		for(int i = 0 ; i < getLongueurDataProduit() ; ++i)
		{
			liste2[i] = "Section : " + getNomSectionReference()[i];
			liste3[i] = getNom2DigitReference()[i] + " (" + getCode2DigitReference()[i] + ")";
		}
		ArrayList<String> resultat = new ArrayList<String>();
		resultat.add(liste2[0]);
		ArrayList<String> resultat2 = new ArrayList<String>();
		resultat2.add(liste3[0]);
		for(int i = 1 ; i < liste3.length ; ++i)
		{
			if(!liste3[i - 1].equals(liste3[i]))
			{
				resultat.add(liste2[i]);
				resultat2.add(liste3[i]);
			}
		}
		//Position de la case hiérarchique
		ArrayList<Integer> positionSection = new ArrayList<Integer>();
		positionSection.add(0);
		for(int i = 1 ; i < resultat.size() ; ++i)
		{
			if(!resultat.get(i - 1).equals(resultat.get(i)))
			{
				positionSection.add(i);
			}
		}
		//Gestion de l'affichage des cases à cocher
		String[] label1 = new String[resultat.size()];
		for(int i = 0 ; i < resultat.size() ; ++i)
		{
			label1[i] = resultat.get(i);
		}
		liste = new String[resultat2.size()];
		for(int i = 0 ; i < resultat2.size() ; ++i)
		{
			liste[i] = resultat2.get(i);
		}
		JPanel panel = new JPanel(new GridLayout(positionSection.size() + liste.length + 1, 1));
		option = new JCheckBox("Tous", false);
		panel.add(option);
		casesACocher = new JCheckBox[liste.length];
		for(int i = 0 ; i < liste.length ; ++i)
		{
			casesACocher[i] = new JCheckBox(liste[i], false);
		}
		for(int i = 0 ; i < liste.length ; ++i)
		{
			for(int j = 0 ; j < positionSection.size() ; ++j)
			{
				if(i == positionSection.get(j))
				{
					panel.add(new JLabel(label1[positionSection.get(j)]));
				}
			}
			panel.add(casesACocher[i]);
		}
//		toutCocher();//Génère un bogue de type "flux"
		return panel;
	}
	private JPanel getCasesACocher4Digit()
	{
		String[] liste2 = new String[getLongueurDataProduit()];
		String[] liste3 = new String[getLongueurDataProduit()];
		String[] liste4 = new String[getLongueurDataProduit()];
		for(int i = 0 ; i < getLongueurDataProduit() ; ++i)
		{
			liste2[i] = "Section : " + getNomSectionReference()[i];
			liste3[i] = "-Chapitre " + getCode2DigitReference()[i] + ": " + getNom2DigitReference()[i];
			liste4[i] = getNom4DigitReference()[i] + " (" + getCode4DigitReference()[i] + ")";
		}
		ArrayList<String> resultat = new ArrayList<String>();
		resultat.add(liste2[0]);
		ArrayList<String> resultat2 = new ArrayList<String>();
		resultat2.add(liste3[0]);
		ArrayList<String> resultat3 = new ArrayList<String>();
		resultat3.add(liste4[0]);
		for(int i = 1 ; i < liste4.length ; ++i)
		{
			if(!liste4[i - 1].equals(liste4[i]))
			{
				resultat.add(liste2[i]);
				resultat2.add(liste3[i]);
				resultat3.add(liste4[i]);
			}
		}
		//Position de la case hiérarchique
		ArrayList<Integer> positionSection = new ArrayList<Integer>();
		positionSection.add(0);
		for(int i = 1 ; i < resultat.size() ; ++i)
		{
			if(!resultat.get(i - 1).equals(resultat.get(i)))
			{
				positionSection.add(i);
			}
		}
		ArrayList<Integer> positionChapitre = new ArrayList<Integer>();
		positionChapitre.add(0);
		for(int i = 1 ; i < resultat2.size() ; ++i)
		{
			if(!resultat2.get(i - 1).equals(resultat2.get(i)))
			{
				positionChapitre.add(i);
			}
		}
		//Gestion de l'affichage des cases à cocher
		String[] label1 = new String[resultat.size()];
		for(int i = 0 ; i < resultat.size() ; ++i)
		{
			label1[i] = resultat.get(i);
		}
		String[] label2 = new String[resultat2.size()];
		for(int i = 0 ; i < resultat2.size() ; ++i)
		{
			label2[i] = resultat2.get(i);
		}
		liste = new String[resultat3.size()];
		for(int i = 0 ; i < resultat3.size() ; ++i)
		{
			liste[i] = resultat3.get(i);
		}
		JPanel panel = new JPanel(new GridLayout(positionSection.size() + positionChapitre.size() + liste.length + 1, 1));
		option = new JCheckBox("Tous", false);
		panel.add(option);
		casesACocher = new JCheckBox[liste.length];
		for(int i = 0 ; i < liste.length ; ++i)
		{
			casesACocher[i] = new JCheckBox(liste[i], false);
		}
		for(int i = 0 ; i < liste.length ; ++i)
		{
			for(int j = 0 ; j < positionSection.size() ; ++j)
			{
				if(i == positionSection.get(j))
				{
					panel.add(new JLabel(label1[positionSection.get(j)]));
				}
			}
			for(int j = 0 ; j < positionChapitre.size() ; ++j)
			{
				if(i == positionChapitre.get(j))
				{
					panel.add(new JLabel(label2[positionChapitre.get(j)]));
				}
			}
			panel.add(casesACocher[i]);
		}
//		toutCocher();//Génère un bogue de type "flux"
		return panel;
	}
	private JPanel getCasesACocher6Digit()
	{
		String[] liste2 = new String[getLongueurDataProduit()];
		String[] liste3 = new String[getLongueurDataProduit()];
		String[] liste4 = new String[getLongueurDataProduit()];
		String[] liste5 = new String[getLongueurDataProduit()];
		for(int i = 0 ; i < getLongueurDataProduit() ; ++i)
		{
			liste2[i] = "Section : " + getNomSectionReference()[i];
			liste3[i] = "-Chapitre " + getCode2DigitReference()[i] + ": " + getNom2DigitReference()[i];
			liste4[i] = "--Titre " + getCode4DigitReference()[i] + " : " + getNom4DigitReference()[i];
			liste5[i] = getNom6DigitReference()[i] + " (" + getCode6DigitReference()[i] + ")";
		}
		ArrayList<String> resultat = new ArrayList<String>();
		resultat.add(liste2[0]);
		ArrayList<String> resultat2 = new ArrayList<String>();
		resultat2.add(liste3[0]);
		ArrayList<String> resultat3 = new ArrayList<String>();
		resultat3.add(liste4[0]);
		ArrayList<String> resultat4 = new ArrayList<String>();
		resultat4.add(liste5[0]);
		for(int i = 1 ; i < liste5.length ; ++i)
		{
			if(!liste5[i - 1].equals(liste5[i]))
			{
				resultat.add(liste2[i]);
				resultat2.add(liste3[i]);
				resultat3.add(liste4[i]);
				resultat4.add(liste5[i]);
			}
		}
		//Position de la case hiérarchique
		ArrayList<Integer> positionSection = new ArrayList<Integer>();
		positionSection.add(0);
		for(int i = 1 ; i < resultat.size() ; ++i)
		{
			if(!resultat.get(i - 1).equals(resultat.get(i)))
			{
				positionSection.add(i);
			}
		}
		ArrayList<Integer> positionChapitre = new ArrayList<Integer>();
		positionChapitre.add(0);
		for(int i = 1 ; i < resultat2.size() ; ++i)
		{
			if(!resultat2.get(i - 1).equals(resultat2.get(i)))
			{
				positionChapitre.add(i);
			}
		}
		ArrayList<Integer> positionTitre = new ArrayList<Integer>();
		positionTitre.add(0);
		for(int i = 1 ; i < resultat3.size() ; ++i)
		{
			if(!resultat3.get(i - 1).equals(resultat3.get(i)))
			{
				positionTitre.add(i);
			}
		}
		//Gestion de l'affichage des cases à cocher
		String[] label1 = new String[resultat.size()];
		for(int i = 0 ; i < resultat.size() ; ++i)
		{
			label1[i] = resultat.get(i);
		}
		String[] label2 = new String[resultat2.size()];
		for(int i = 0 ; i < resultat2.size() ; ++i)
		{
			label2[i] = resultat2.get(i);
		}
		String[] label3 = new String[resultat3.size()];
		for(int i = 0 ; i < resultat3.size() ; ++i)
		{
			label3[i] = resultat3.get(i);
		}
		liste = new String[resultat4.size()];
		for(int i = 0 ; i < resultat4.size() ; ++i)
		{
			liste[i] = resultat4.get(i);
		}
		//Création d'un panel sur plusieurs colonnes
		int numLigne = positionSection.size() + positionChapitre.size() + positionTitre.size() + liste.length + 1;
		int pas = 6;
		int coupure = (int)((numLigne)/pas);
		coupureTab = new int[pas + 2];
		coupureTab2 = new int[pas + 2];
		for(int i = 0 ; i < coupureTab.length ; ++i)
		{
			coupureTab[i] = i*coupure;
			coupureTab2[i] = i*coupure - 1;
		}
		JPanel panel = new JPanel(new GridLayout(1, pas + 1));
//		JPanel panel = new JPanel(new GridLayout(numLigne, 1));
		option = new JCheckBox("Tous", false);
//		panel.add(option);
		casesACocher = new JCheckBox[liste.length];
		for(int i = 0 ; i < liste.length ; ++i)
		{
			casesACocher[i] = new JCheckBox(liste[i], false);
		}
		ArrayList<Object> souspanel = new ArrayList<Object>();
		ArrayList<String> type = new ArrayList<String>();
		souspanel.add(option);
		type.add("Case");
		for(int i = 0 ; i < liste.length ; ++i)
		{
			for(int j = 0 ; j < positionSection.size() ; ++j)
			{
				if(i == positionSection.get(j))
				{
					JLabel label = new JLabel(label1[positionSection.get(j)]);
					souspanel.add(label);
					type.add("Label");
				}
			}
			for(int j = 0 ; j < positionChapitre.size() ; ++j)
			{
				if(i == positionChapitre.get(j))
				{
					JLabel label = new JLabel(label2[positionChapitre.get(j)]);
					souspanel.add(label);
					type.add("Label");
				}
			}
			for(int j = 0 ; j < positionTitre.size() ; ++j)
			{
				if(i == positionTitre.get(j))
				{
					JLabel label = new JLabel(label3[positionTitre.get(j)]);
					souspanel.add(label);
					type.add("Label");
				}
			}
			souspanel.add(casesACocher[i]);
			type.add("Case");
		}
		//Insertion des lignes vides
		int delta = coupure - (numLigne - coupureTab[coupureTab.length - 2]);
		if(delta != 0)
		{
			for(int i = 1 ; i <= delta ; ++i)
			{
				souspanel.add(new JLabel("                 "));
				type.add("Label");
			}
		}
		//Insertion des panels sur plusieurs colonnes
		int count = 0;
		for(int k = 0 ; k < coupureTab.length - 1 ; ++k)
		{
			JPanel souspanel2 = new JPanel(new GridLayout(coupureTab[k + 1] - (coupureTab2[k] + 1), 1));
			for(int i = coupureTab2[k] + 1 ; i < coupureTab[k + 1] ; ++i)
			{
				if(type.get(i) == "Label")
				{
					JLabel label = (JLabel) souspanel.get(i);
					souspanel2.add(label);
				}
				else
				{
					if(type.get(i) == "Case")
					{
//						JCheckBox checkbox = (JCheckBox) souspanel.get(i);
						if(i != 0)
						{
//							casesACocher[count] = checkbox;
							souspanel2.add(casesACocher[count]);
//							casesACocher[count].addItemListener(new ActionClassification2());
							count++;
						}
						else
						{
//							option = checkbox;
							souspanel2.add(option);
//							option.addItemListener(new ActionClassification());
						}
//						souspanel2.add(checkbox);
					}
				}
			}
			panel.add(souspanel2);
		}
		/*
		for(int i = 0 ; i < liste.length ; ++i)
		{
			for(int j = 0 ; j < positionSection.size() ; ++j)
			{
				if(i == positionSection.get(j))
				{
					panel.add(new JLabel(label1[positionSection.get(j)]));
				}
			}
			for(int j = 0 ; j < positionChapitre.size() ; ++j)
			{
				if(i == positionChapitre.get(j))
				{
					panel.add(new JLabel(label2[positionChapitre.get(j)]));
				}
			}
			for(int j = 0 ; j < positionTitre.size() ; ++j)
			{
				if(i == positionTitre.get(j))
				{
					panel.add(new JLabel(label3[positionTitre.get(j)]));
				}
			}
			panel.add(casesACocher[i]);
		}
		*/
//		toutCocher();//Génère un bogue de type "flux"
		return panel;
	}
//******************************************
//*Initialisation des données attributaires*
//******************************************
	private void donneesProduits()
	{
		baseAttributaireProduit = new BaseAttributaireProduit(getChoixDesClassifications(), getChoixDesNiveaux());
		setBaseAttributaireProduit(baseAttributaireProduit);
		//Enregistrement des variables d'instance
		setChoixDesNiveauxInt(baseAttributaireProduit.getChoixDesNiveauxInt());
		setLongueurDataProduit(baseAttributaireProduit.getLongueurDataProduit());
		setTitreData(baseAttributaireProduit.getTitreData());
		setCodeSectionReference(baseAttributaireProduit.getCodeSectionReference());
		setNameSectionReference(baseAttributaireProduit.getNameSectionReference());
		setNomSectionReference(baseAttributaireProduit.getNomSectionReference());
		setCode2DigitReference(baseAttributaireProduit.getCode2DigitReference());
		setName2DigitReference(baseAttributaireProduit.getName2DigitReference());
		setNom2DigitReference(baseAttributaireProduit.getNom2DigitReference());
		setCode4DigitReference(baseAttributaireProduit.getCode4DigitReference());
		setName4DigitReference(baseAttributaireProduit.getName4DigitReference());
		setNom4DigitReference(baseAttributaireProduit.getNom4DigitReference());
		setCode6DigitReference(baseAttributaireProduit.getCode6DigitReference());
		setName6DigitReference(baseAttributaireProduit.getName6DigitReference());
		setNom6DigitReference(baseAttributaireProduit.getNom6DigitReference());
		//Initialisation des variables de sortie
		setCodeSection(baseAttributaireProduit.getCodeSection());
		setCode2Digit(baseAttributaireProduit.getCode2Digit());
		setCode4Digit(baseAttributaireProduit.getCode4Digit());
		setCode6Digit(baseAttributaireProduit.getCode6Digit());
	}
//*************************************
//*Affichage du tableau sur la console*
//*************************************
	public void affichageTableau()
	{
		getBaseAttributaireProduit().affichageTableau();
	}
	public String affichageFenetreTableauCSV()
	{
		return getBaseAttributaireProduit().affichageFenetreTableauCSV();
	}
	public void affichageFenetreTableau()
	{
		getBaseAttributaireProduit().affichageFenetreTableau();
	}
	public void affichageFenetreTableauSimplifiee()
	{
		getBaseAttributaireProduit().affichageFenetreTableauSimplifiee();
	}
	public void affichageSelection()
	{
		for(int i = 0 ; i < getEnregistrementProduit().length ; ++i)
		{
			System.out.println(getEnregistrementProduit()[i]);
		}
	}
//********************
//*Getters et setters*
//********************
	public BaseAttributaireProduit getBaseAttributaireProduit()
	{
		return this.baseAttributaireProduit;
	}
	private void setBaseAttributaireProduit(BaseAttributaireProduit base)
	{
		this.baseAttributaireProduit = base;
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
	//Récupération des données de l'utilisateur
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
	public String[] getTerritoire()
	{
		return this.territoire;
	}
	private void setTerritoire(String[] territoire)
	{
		this.territoire = territoire;
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
	//Enregistrement des produits choisis
	public String[] getCodeSection()
	{
		return this.codeSection;
	}
	private void setCodeSection(String[] codeSection)
	{
		this.codeSection = codeSection;
	}
	public String[] getCode2Digit()
	{
		return this.code2Digit;
	}
	private void setCode2Digit(String[] code2Digit)
	{
		this.code2Digit = code2Digit;
	}
	public String[] getCode4Digit()
	{
		return this.code4Digit;
	}
	private void setCode4Digit(String[] code4Digit)
	{
		this.code4Digit = code4Digit;
	}
	public String[] getCode6Digit()
	{
		return this.code6Digit;
	}
	private void setCode6Digit(String[] code6Digit)
	{
		this.code6Digit = code6Digit;
	}
	public String[] getEnregistrementProduit()
	{
		return this.enregistrementProduit;
	}
	private void setEnregistrementProduit(String[] enregistrementProduit)
	{
		this.enregistrementProduit = enregistrementProduit;
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
//************************
//*Gestion des événements*
//************************
	//Fonction pour tout cocher
	private void toutCocher()
	{
		if(option.isSelected() == true)
		{
			//La boucle est trop récursive, un débordement de pile se produit. Il est nécessaire de capturer l'erreur, afin que la boucle puisse s'exécuter.
			//Le problème ne se pose que pour les 6-Digit
			try
			{
				for(int i = 0 ; i < liste.length ; ++i)
				{
					if(casesACocher[i].isSelected() == false)
					{
						casesACocher[i].setSelected(true);
					}
					casesACocher[i].setEnabled(false);
				}
			}
			catch(StackOverflowError e) {}
		}
		else
		{
			//Le problème ne se pose pas au déclic, mais par sécurité la capture de l'erreur est posée.
			try
			{
				for(int i = 0 ; i < liste.length ; ++i)
				{
					if(casesACocher[i].isSelected() == true)
					{
						casesACocher[i].setSelected(false);
					}
					casesACocher[i].setEnabled(true);
				}
			}
			catch(StackOverflowError e) {}
		}
	}
	class ActionClassification implements ItemListener
	{
		//Méthode obligatoire à implanter avec l'interface ItemEvent
		public void itemStateChanged(ItemEvent e)
		{
			toutCocher();//Par défaut, on coche tous les territoires
		}
	}
	//Si on coche toutes les cases à cocher, la case option est automatiquement cochée.
	private void cocherOption()
	{
		int count = 0;
		for(int i = 0 ; i < liste.length ; ++i)
		{
			if(casesACocher[i].isSelected() == true)
			{
				count++;
			}
		}
		if(count == liste.length)
		{
			option.setSelected(true);
		}
		else
		{
			option.setSelected(false);
		}
	}
	//Cette fonction enregistre la position dans liste[] des cases cochées
	private void enregistrementCasesCocheesSection()
	{
		ArrayList<Integer> enregistrement = new ArrayList<Integer>();
		for(int i = 0 ; i < liste.length ; ++i)
		{
			if(casesACocher[i].isSelected() == true)
			{
				enregistrement.add(i);
			}
		}
		enregistrementProduit = new String[enregistrement.size()];
		for(int i = 0 ; i < enregistrement.size() ; ++i)
		{
			enregistrementProduit[i] = getCodeSection()[enregistrement.get(i)];
		}
		setEnregistrementProduit(enregistrementProduit);
	}
	private void enregistrementCasesCochees2Digit()
	{
		ArrayList<Integer> enregistrement = new ArrayList<Integer>();
		for(int i = 0 ; i < liste.length ; ++i)
		{
			if(casesACocher[i].isSelected() == true)
			{
				enregistrement.add(i);
			}
		}
		enregistrementProduit = new String[enregistrement.size()];
		for(int i = 0 ; i < enregistrement.size() ; ++i)
		{
			enregistrementProduit[i] = getCode2Digit()[enregistrement.get(i)];
		}
		setEnregistrementProduit(enregistrementProduit);
	}
	private void enregistrementCasesCochees4Digit()
	{
		ArrayList<Integer> enregistrement = new ArrayList<Integer>();
		for(int i = 0 ; i < liste.length ; ++i)
		{
			if(casesACocher[i].isSelected() == true)
			{
				enregistrement.add(i);
			}
		}
		enregistrementProduit = new String[enregistrement.size()];
		for(int i = 0 ; i < enregistrement.size() ; ++i)
		{
			enregistrementProduit[i] = getCode4Digit()[enregistrement.get(i)];
		}
		setEnregistrementProduit(enregistrementProduit);
	}
	private void enregistrementCasesCochees6Digit()
	{
		ArrayList<Integer> enregistrement = new ArrayList<Integer>();
		for(int i = 0 ; i < liste.length ; ++i)
		{
			if(casesACocher[i].isSelected() == true)
			{
				enregistrement.add(i);
			}
		}
		enregistrementProduit = new String[enregistrement.size()];
		for(int i = 0 ; i < enregistrement.size() ; ++i)
		{
			enregistrementProduit[i] = getCode6Digit()[enregistrement.get(i)];
		}
		setEnregistrementProduit(enregistrementProduit);
	}
	//Choix des territoires. Attention ! Lorsque la case à cocher option est cochée, cela bloque toutes les autres cases.
	class ActionClassification2 implements ItemListener
	{
		//Méthode obligatoire à implanter avec l'interface ItemEvent
		public void itemStateChanged(ItemEvent e)
		{
			if(getChoixDesNiveauxInt() == 0)
			{
				if(option.isSelected() == true)
				{
					toutCocher();
					enregistrementCasesCocheesSection();
				}
				else
				{
					cocherOption();
					enregistrementCasesCocheesSection();
				}
			}
			else
			{
				if(getChoixDesNiveauxInt() == 2)
				{
					if(option.isSelected() == true)
					{
						toutCocher();
						enregistrementCasesCochees2Digit();
					}
					else
					{
						cocherOption();
						enregistrementCasesCochees2Digit();
					}
				}
				else
				{
					if(getChoixDesNiveauxInt() == 4)
					{
						if(option.isSelected() == true)
						{
							toutCocher();
							enregistrementCasesCochees4Digit();
						}
						else
						{
							cocherOption();
							enregistrementCasesCochees4Digit();
						}
					}
					else
					{
						if(getChoixDesNiveauxInt() == 6)
						{
							if(option.isSelected() == true)
							{
								toutCocher();
								enregistrementCasesCochees6Digit();
							}
							else
							{
									cocherOption();
									enregistrementCasesCochees6Digit();
							}
						}
						else
						{
							
						}
					}
				}
			}
//			affichageSelection();
		}
	}
	class ClicBoutonOK implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (boutonOK.isEnabled()) 
			{
				FenetreEtudeTerritoriale3 renvoiDonnees = new FenetreEtudeTerritoriale3(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getAnneeDebut(), getAnneeFin(), getEnregistrementProduit());
				renvoiDonnees.setVisible(true);
				dispose();
			}
		}
	}
	class Fermeture implements WindowListener
	{
		public void windowOpened(WindowEvent e)
		{}
		public void windowClosing(WindowEvent e)
		{
			FenetreEtudeTerritoriale3 renvoiDonnees = new FenetreEtudeTerritoriale3(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getAnneeDebut(), getAnneeFin());
			renvoiDonnees.setVisible(true);
			dispose();
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