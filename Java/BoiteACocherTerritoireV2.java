//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BoiteACocherTerritoireV2 extends JFrame
{
	private BaseAttributaireTerritoire baseAttributaireTerritoire = null;
	private String choixDesClassifications = "";
	private String choixDesNiveaux = "";
	private String choixTerritoire = "";
	private int longueurData = 0;
	private String[] titreData = null;
	private String[] codeISO = null;
	private String[] englishName = null;
	private String[] nomFrancais = null;
	private String[] nomContinent = null;
	private String[] borneAnneeDebut = null;
	private String[] borneAnneeFin = null;

	private JCheckBox option = null;
	private JCheckBox[] casesACocher = null;
	private String[] liste = null;
	private String[] enregistrementTerritoire = null;
	
	private JButton boutonOK = null;
	
	private String[] listeHS = null;
	
	private int anneeDebut = 0;
	private int anneeFin = 0;

	public BoiteACocherTerritoireV2(String text, String text2)
	{
		//Récupération de la valeur de la classification
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setListeHS();
		//Initialisation des données attributaires territoriales
		donneesTerritoires();
		//Affichage des données attributaires territoriales
//		affichageTableau();
//		System.out.println(affichageFenetreTableauCSV());
//		affichageFenetreTableau();

		init();
	}
	public BoiteACocherTerritoireV2(String text, String text2, int anneeDebut, int anneeFin)
	{
		//Récupération de la valeur de la classification
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setAnneeDebut(anneeDebut);
		setAnneeFin(anneeFin);
		setListeHS();
		//Initialisation des données attributaires territoriales
		donneesTerritoires();
		//Affichage des données attributaires territoriales
//		affichageTableau();
//		System.out.println(affichageFenetreTableauCSV());
//		affichageFenetreTableau();

		init();
	}
//***********************
//*Gestion de la fenêtre*
//***********************
	private void init()
	{
		this.setTitle("\u00c9tude territoriale : Choix des territoires");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
//		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new Fermeture());
		
		Container conteneur = this.getContentPane();
		JPanel panel = new JPanel(new BorderLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBounds(100, 100, 100, 100);
		panel.add(getCasesACocher());
		
		option.addItemListener(new ActionClassification());
		for(int i = 0 ; i < liste.length ; ++i)
		{
			casesACocher[i].addItemListener(new ActionClassification2());
		}
		
		boutonOK = new JButton("OK");
		panel.add(boutonOK);
		boutonOK.addActionListener(new ClicBoutonOK());

		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setAlignmentX(LEFT_ALIGNMENT);
		scroll.setPreferredSize(new Dimension(200,200));
        scroll.setViewportView(panel);
		conteneur.add(scroll, BorderLayout.CENTER);
	}

//********************************
//*Cases à cocher des territoires*
//********************************
	private JPanel getCasesACocher()
	{
		liste = new String[getLongueurDataTerritoire()];
		//1 case à cocher "Tous" + 7 cases "Continent"
		JPanel panel = new JPanel(new GridLayout(liste.length + 1 + 7, 1));
		casesACocher = new JCheckBox[liste.length];
		option = new JCheckBox("Tous", false);
		panel.add(option);
		//Préparation de la liste des continents
		//Attention ! Pour que le tri fonctionne correctement, il faut pré-trier le fichier country_names.csv : (1) trier par ordre alphabétique les territoire : (2) trier par ordre alphabétique les continents.
		ArrayList<Integer> positionContinent = new ArrayList<Integer>();
		positionContinent.add(0);
		for(int i = 1 ; i < getNomContinent().length ; ++i)
		{
			if(!getNomContinent()[i - 1].equals(getNomContinent()[i]))
			{
				positionContinent.add(i);
			}
		}
		
		for(int i = 0 ; i < getLongueurDataTerritoire() ; ++i)
		{
			for(int j = 0 ; j < positionContinent.size() ; ++j)
			{
				if(i == positionContinent.get(j))
				{
					panel.add(new Label(getNomContinent()[positionContinent.get(j)]));
				}
			}
			liste[i] = getNomFrancais()[i] + " (" + getCodeISO()[i] + ") (" + getBorneAnneeDebut()[i] + "-" + getBorneAnneeFin()[i] + ")";
			casesACocher[i] = new JCheckBox(liste[i], false);
			panel.add(casesACocher[i]);
		}
//		toutCocher();//Génère un bogue de type "flux"
		return panel;
	}	
//******************************************
//*Initialisation des données attributaires*
//******************************************
	private void donneesTerritoires()
	{
		baseAttributaireTerritoire = new BaseAttributaireTerritoire(getChoixDesClassifications());
		setBaseAttributaireTerritoire(baseAttributaireTerritoire);
		setLongueurDataTerritoire(baseAttributaireTerritoire.getLongueurDataTerritoire());
		setTitreData(baseAttributaireTerritoire.getTitreData());
		setCodeISO(baseAttributaireTerritoire.getCodeISO());
		setName(baseAttributaireTerritoire.getEnglishName());
		setNom(baseAttributaireTerritoire.getNomFrancais());
		setNomContinent(baseAttributaireTerritoire.getNomContinent());
		setBorneAnneeDebut(baseAttributaireTerritoire.getBorneAnneeDebut());
		setBorneAnneeFin(baseAttributaireTerritoire.getBorneAnneeFin());
	}
//*************************************
//*Affichage du tableau sur la console*
//*************************************
	public void affichageTableau()
	{
		getBaseAttributaireTerritoire().affichageTableau();
	}
	public String affichageFenetreTableauCSV()
	{
		return getBaseAttributaireTerritoire().affichageFenetreTableauCSV();
	}
	public void affichageFenetreTableau()
	{
		getBaseAttributaireTerritoire().affichageFenetreTableau();
	}
	public void affichageSelection()
	{
		for(int i = 0 ; i < getEnregistrementTerritoire().length ; ++i)
		{
			System.out.println(getEnregistrementTerritoire()[i]);
		}
	}
//********************
//*Getters et setters*
//********************
	public BaseAttributaireTerritoire getBaseAttributaireTerritoire()
	{
		return this.baseAttributaireTerritoire;
	}
	private void setBaseAttributaireTerritoire(BaseAttributaireTerritoire base)
	{
		this.baseAttributaireTerritoire = base;
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
	private void setName(String[] englishName)
	{
		this.englishName = englishName;
	}
	public String[] getNomFrancais()
	{
		return this.nomFrancais;
	}
	private void setNom(String[] nomFrancais)
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
	//Choix de la fenêtre précédente
	public String getChoixDesClassifications()
	{
		return this.choixDesClassifications;
	}
	public void setChoixDesClassifications(String nomClassification)
	{
		this.choixDesClassifications = nomClassification;
	}
	public String getChoixDesNiveaux()
	{
		return this.choixDesNiveaux;
	}
	public void setChoixDesNiveaux(String nomDuNiveau)
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
	public String[] getEnregistrementTerritoire()
	{
		return this.enregistrementTerritoire;
	}
	private void setEnregistrementTerritoire(String[] enregistrementTerritoire)
	{
		this.enregistrementTerritoire = enregistrementTerritoire;
	}
	//Récupération de la liste énumérée sous forme d'un tableau
	public String[] getListeHS()
	{
		return this.listeHS;
	}
	public void setListeHS()
	{
		listeHS = new String[ListeHS.values().length];
		for(int i = 0 ; i < ListeHS.values().length ; ++i)
		{
			listeHS[i] = ListeHS.values()[i].getClassification();
		}
		this.listeHS = listeHS;
	}
//************************
//*Gestion des événements*
//************************
	//Fonction pour tout cocher
	private void toutCocher()
	{
		if(option.isSelected() == true)
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
		else
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
	public void enregistrementCasesCochees()
	{
		ArrayList<Integer> enregistrement = new ArrayList<Integer>();
		for(int i = 0 ; i < liste.length ; ++i)
		{
			if(casesACocher[i].isSelected() == true)
			{
				enregistrement.add(i);
			}
		}
		enregistrementTerritoire = new String[enregistrement.size()];
		for(int i = 0 ; i < enregistrement.size() ; ++i)
		{
			enregistrementTerritoire[i] = codeISO[enregistrement.get(i)];
		}
		setEnregistrementTerritoire(enregistrementTerritoire);
	}

	//Choix des territoires. Attention ! Lorsque la case à cocher option est cochée, cela bloque toutes les autres cases.
	class ActionClassification2 implements ItemListener
	{
		//Méthode obligatoire à implanter avec l'interface ItemEvent
		public void itemStateChanged(ItemEvent e)
		{
			if(option.isSelected() == true)
			{
				toutCocher();
				enregistrementCasesCochees();
//				affichageSelection();
			}
			else
			{
				cocherOption();
				enregistrementCasesCochees();
//				affichageSelection();
			}
		}
	}
	class ClicBoutonOK implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (boutonOK.isEnabled()) 
			{
				FenetreEtudeTerritoriale2 renvoiDonnees = new FenetreEtudeTerritoriale2(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getAnneeDebut(), getAnneeFin());
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
			FenetreEtudeTerritoriale2 renvoiDonnees = new FenetreEtudeTerritoriale2(getChoixDesClassifications(), getChoixDesNiveaux(), getAnneeDebut(), getAnneeFin());
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