//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreEtudeFlux3 extends JFrame
{
	private BoiteACocherProduits2 listeDesProduits = null;
	private String choixDesClassifications = null;
	private String choixDesNiveaux = null;
	private String[] territoire = null;
	private String[] territoire2 = null;
	private int anneeDebut = 0;
	private int anneeFin = 0;
	private String[] enregistrementProduit = null;
	private String labelProduit = null;
	private Container contentPane = null;
	private JPanel panel3 = null;
	private JButton boutonRetour = null;
	private JButton boutonSuite = null;
	private JButton boutonAnnuler = null;
	private JButton boutonProduit = null;
	
	public FenetreEtudeFlux3(String text1, String text2, String[] text3, String[] text4, int anneeDebut, int anneeFin)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text1);
		setChoixDesNiveaux(text2);
		setTerritoire(text3);
		setTerritoire2(text4);
		setAnneeDebut(anneeDebut);
		setAnneeFin(anneeFin);
		init();
	}
	public FenetreEtudeFlux3(String text1, String text2, String[] text3, String[] text4, int anneeDebut, int anneeFin, String[] text5)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text1);
		setChoixDesNiveaux(text2);
		setTerritoire(text3);
		setTerritoire2(text4);
		setAnneeDebut(anneeDebut);
		setAnneeFin(anneeFin);
		setEnregistrementProduit(text5);
		init();
	}
//****************************************
//*Initialisation des variables de classe*
//****************************************
	private void init()
	{
		//Initialisation de la zone de texte
		setLabelProduit();
		//Affichage sur la console des choix de l'utilisateur
//		affichageDesDonnees();
		//Gestion de la fenêtre
		this.setTitle("\u00c9tude des flux entre territoires");
		this.setSize(550,350);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Couche de contenu du Frame
		contentPane = this.getContentPane();
		//Corps central
		contentPane.add("Center", zoneMouvante());
		//Boutons de fin
		panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		boutonRetour = new JButton("Retour");
		boutonSuite = new JButton("Extraction");
		boutonAnnuler = new JButton("Fermer");
		panel3.add(boutonRetour);
		panel3.add(boutonSuite);
		panel3.add(boutonAnnuler);
		contentPane.add("South", panel3);
		//Evénements
		boutonRetour.addActionListener(new ClicRetour());
		boutonSuite.addActionListener(new ClicSuivant());
		boutonAnnuler.addActionListener(new FenetreFermeture());//Action du bouton "Quitter"
	}
//************************************
//*Gestion de l'affichage des widgets*
//************************************
	private JPanel zoneMouvante()
	{
		JPanel panel = new JPanel(new GridLayout(5, 2));
		//Gestion de l'objet : Choix des classifications
		panel.add(new Label("Choix de la classification : "));
		panel.add(new Label(getChoixDesClassifications()));
		//Gestion de l'objet : Choix des niveaux
		panel.add(new Label("Choix du niveau d'analyse : "));
		panel.add(new Label(getChoixDesNiveaux()));
		//Gestion de l'objet : Choix des produits
		panel.add(new Label("Choix des produits : "));
		JPanel boutonProduitPanel = new JPanel();
		boutonProduit = new JButton("Choisir les produits");
		boutonProduitPanel.setLayout(new BoxLayout(boutonProduitPanel, BoxLayout.LINE_AXIS));
		boutonProduitPanel.add(boutonProduit);
		boutonProduitPanel.add(Box.createRigidArea(new Dimension(50,100)));
		panel.add(boutonProduitPanel);
		boutonProduit.addActionListener(new ClicProduit());
		//Gestion de la zone de texte affichant les sélections
		panel.add(new Label("S\u00e9lection : "));
		JTextArea zoneDeTexteChoixProduits = new JTextArea(getLabelProduit());
		zoneDeTexteChoixProduits.setEditable(false);//Interdire la modification du texte de la zone
		zoneDeTexteChoixProduits.setBackground(new Color(240,240,240));//Couleur du fond
		zoneDeTexteChoixProduits.setForeground(Color.GRAY.brighter());//Couleur du texte
		panel.add(new JScrollPane(zoneDeTexteChoixProduits));
		return panel;
	}
//*****************************************************
//*Affichage des choix de l'utilisateur sur la console*
//*****************************************************
	private void affichageDesDonnees()
	{
		System.out.println(getChoixDesClassifications());
		System.out.println(getChoixDesNiveaux());
		for(int i = 0 ; i < getTerritoire().length ; ++i)
		{
			System.out.println(getTerritoire()[i]);
		}
		for(int i = 0 ; i < getTerritoire2().length ; ++i)
		{
			System.out.println(getTerritoire2()[i]);
		}
		System.out.println(getAnneeDebut());
		System.out.println(getAnneeFin());
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
	public String[] getTerritoire()
	{
		return this.territoire;
	}
	private void setTerritoire(String[] territoire)
	{
		this.territoire = territoire;
	}
	public String[] getTerritoire2()
	{
		return this.territoire2;
	}
	private void setTerritoire2(String[] territoire)
	{
		this.territoire2 = territoire;
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
	public String[] getEnregistrementProduit()
	{
		return this.enregistrementProduit;
	}
	private void setEnregistrementProduit(String[] enregistrementProduit)
	{
		this.enregistrementProduit = enregistrementProduit;
	}
	public String getLabelProduit()
	{
		return this.labelProduit;
	}
	private void setLabelProduit()
	{
		String labelProduit2 = "";
		if(getEnregistrementProduit() != null)
		{
			for(int i = 0 ; i < getEnregistrementProduit().length ; ++i)
			{
				String finChaine = "";
				if(divisible(i) == true)
				{
					finChaine = "\n";
				}
				else
				{
					finChaine = "";
				}
					
				if(i < getEnregistrementProduit().length - 1)
				{
					labelProduit2 = labelProduit2 + getEnregistrementProduit()[i] + ", " + finChaine;
				}
				else
				{
					labelProduit2 = labelProduit2 + getEnregistrementProduit()[i] + finChaine;
				}
			}
		}
		else
		{
			labelProduit2 = "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !";
		}
		this.labelProduit = labelProduit2;
	}
	private boolean divisible(int test)
	{
		boolean test2;
		double y = ((double) test + 1) / (double) 9;
		if (y == (int)y)
		{
			test2 = true;
		}
		else
		{
			test2 = false;
		}
		return test2;
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
				FenetreEtudeFlux2 fenetre = new FenetreEtudeFlux2(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getTerritoire2(), getAnneeDebut(), getAnneeFin());
				fenetre.setVisible(true);
				dispose();
			}
		}
	}
	class ClicSuivant implements ActionListener
	{
		//Méthode obligatoire à implanter avec l'interface ActionEvent
		public void actionPerformed(ActionEvent e)
		{
			if(getLabelProduit() == "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !")
			{
				JOptionPane erreurMessage = new JOptionPane();
				erreurMessage.showMessageDialog(null, "Erreur ! Vous n'avez choisi aucun produit !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				FenetreEtudeFlux4 fenetre = new FenetreEtudeFlux4(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getTerritoire2(), getAnneeDebut(), getAnneeFin(), getEnregistrementProduit());
				fenetre.setVisible(true);
				dispose();
			}
		}
	}
	class ClicProduit implements ActionListener
	{
		//Méthode obligatoire à implanter avec l'interface ActionEvent
		public void actionPerformed(ActionEvent e)
		{
			if (boutonProduit.isEnabled()) 
			{
				listeDesProduits = new BoiteACocherProduits2(getChoixDesClassifications(), getChoixDesNiveaux(), getTerritoire(), getTerritoire2(), getAnneeDebut(), getAnneeFin());
				listeDesProduits.setVisible(true);
				dispose();
			}
		}
	}
}