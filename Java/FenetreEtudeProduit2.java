//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreEtudeProduit2 extends JFrame
{
	private String choixDesClassifications = "";
	private String choixDesNiveaux = "";
	private BoiteACocherProduits3 listeDesProduits = null;
	private BoiteACocherDateDebut listeDesDates1 = null;
	private BoiteACocherDateFin listeDesDates2 = null;
	private Container contentPane = null;
	private JPanel panel3 = null;
	private JButton boutonRetour = null;
	private JButton boutonSuite = null;
	private JButton boutonAnnuler = null;
	private JButton boutonProduit = null;

	private String[] enregistrementProduit = null;
	private String labelProduit = null;
	private int anneeDebut = 0;
	private int anneeFin = 0;

	public FenetreEtudeProduit2(String text, String text2)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		init();
	}
	public FenetreEtudeProduit2(String text, String text2, String[] text3)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setEnregistrementProduit(text3);
		init();
	}
	public FenetreEtudeProduit2(String text, String text2, int anneeDebutSelection, int anneeFinSelection)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setAnneeDebut(anneeDebutSelection);
		setAnneeFin(anneeFinSelection);
		init();
	}
	public FenetreEtudeProduit2(String text, String text2, String[] text3, int anneeDebutSelection, int anneeFinSelection)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setEnregistrementProduit(text3);
		setAnneeDebut(anneeDebutSelection);
		setAnneeFin(anneeFinSelection);
		init();
	}
//****************************************
//*Initialisation des variables de classe*
//****************************************
	private void init()
	{
		//Initialisation de la zone de texte
		setLabelProduit();
		//Gestion de la fenêtre
		this.setTitle("\u00c9tude des types de produits");
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
		JPanel panel = new JPanel(new GridLayout(7, 2));
		//Gestion de l'objet : Choix des classifications
		panel.add(new Label("Choix de la classification : "));
		panel.add(new Label(getChoixDesClassifications()));
		//Gestion de l'objet : Choix des nivaux
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
		//Gestion des objets : Choix des dates
		panel.add(new Label("Choix de la p\u00e9riode :"));
		Panel panel2 = new Panel(new GridLayout(1, 4));
		panel2.add(new Label("De : "));
		if(getAnneeDebut() != 0)
		{
			listeDesDates1 = new BoiteACocherDateDebut(getChoixDesClassifications(), getAnneeDebut());
		}
		else
		{
			listeDesDates1 = new BoiteACocherDateDebut(getChoixDesClassifications());
		}
		//panel2.add(listeDesDates1.getPanel());
		if(getEnregistrementProduit() == null)
		{
			panel2.add(listeDesDates1.getPanelGris());
		}
		else
		{
			panel2.add(listeDesDates1.getPanel());
		}
		panel2.add(new Label(" \u00e0 "));
		if(getAnneeFin() != 0)
		{
			listeDesDates2 = new BoiteACocherDateFin(getChoixDesClassifications(), getAnneeFin());
		}
		else
		{
			listeDesDates2 = new BoiteACocherDateFin(getChoixDesClassifications());
		}
		//panel2.add(listeDesDates2.getPanel());
		if(getEnregistrementProduit() == null)
		{
			panel2.add(listeDesDates2.getPanelGris());
		}
		else
		{
			panel2.add(listeDesDates2.getPanel());
		}
		panel.add(panel2);
		return panel;
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
				FenetreEtudeProduit fenetre = new FenetreEtudeProduit(getChoixDesClassifications(), getChoixDesNiveaux());
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
				FenetreEtudeProduit3 fenetre = new FenetreEtudeProduit3(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementProduit(), CompareAnneeDebut.getAnneeDebut(), CompareAnneeFin.getAnneeFin());
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
				listeDesProduits = new BoiteACocherProduits3(getChoixDesClassifications(), getChoixDesNiveaux(), getAnneeDebut(), getAnneeFin());
				listeDesProduits.setVisible(true);
				dispose();
			}
		}
	}
}