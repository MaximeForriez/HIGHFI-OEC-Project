//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreEtudeTerritoriale2 extends JFrame
{
	private String choixDesClassifications = "";
	private String choixDesNiveaux = "";
	private BoiteACocherTerritoireV2 listeDesTerritoires = null;
	private BoiteACocherDateDebut listeDesDates1 = null;
	private BoiteACocherDateFin listeDesDates2 = null;
	private JButton boutonRetour = null;
	private JButton boutonSuite = null;
	private JButton boutonAnnuler = null;
	private JButton boutonTerritoire = null;
	private JPanel panel3 = null;
	private Container contentPane = null;

	private String[] enregistrementTerritoire = null;
	private String labelTerritoire = null;
	private int anneeDebut = 0;
	private int anneeFin = 0;

	public FenetreEtudeTerritoriale2(String text, String text2)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		init();
	}
	public FenetreEtudeTerritoriale2(String text, String text2, String[] text3)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setEnregistrementTerritoire(text3);
		init();
	}
	public FenetreEtudeTerritoriale2(String text, String text2, int anneeDebutSelection, int anneeFinSelection)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setAnneeDebut(anneeDebutSelection);
		setAnneeFin(anneeFinSelection);
		init();
	}
	public FenetreEtudeTerritoriale2(String text, String text2, String[] text3, int anneeDebutSelection, int anneeFinSelection)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setEnregistrementTerritoire(text3);
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
		setLabelTerritoire();
		//Gestion de la fenêtre
		this.setTitle("\u00c9tude territoriale");
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
		boutonSuite = new JButton("Suite");
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
		//Gestion de l'objet : Choix des nivaux
		panel.add(new Label("Choix du niveau d'analyse : "));
		panel.add(new Label(getChoixDesNiveaux()));
		//Gestion de l'objet : Choix des territoires
		panel.add(new Label("Choix des territoires : "));
		JPanel boutonTerritoirePanel = new JPanel();
		boutonTerritoire = new JButton("Choisir les territoires");
		boutonTerritoirePanel.setLayout(new BoxLayout(boutonTerritoirePanel, BoxLayout.LINE_AXIS));
		boutonTerritoirePanel.add(boutonTerritoire);
		boutonTerritoirePanel.add(Box.createRigidArea(new Dimension(50,100)));
		panel.add(boutonTerritoirePanel);
		boutonTerritoire.addActionListener(new ClicTerritoire());
		//Gestion de la zone de texte affichant les sélections
		panel.add(new Label("S\u00e9lection : "));
		JTextArea zoneDeTexteChoixTerritoires = new JTextArea(getLabelTerritoire());
		zoneDeTexteChoixTerritoires.setEditable(false);//Interdire la modification du texte de la zone
		zoneDeTexteChoixTerritoires.setBackground(new Color(240,240,240));//Couleur du fond
		zoneDeTexteChoixTerritoires.setForeground(Color.GRAY.brighter());//Couleur du texte
		panel.add(new JScrollPane(zoneDeTexteChoixTerritoires));
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
		if(getEnregistrementTerritoire() == null)
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
		if(getEnregistrementTerritoire() == null)
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
	public String[] getEnregistrementTerritoire()
	{
		return this.enregistrementTerritoire;
	}
	private void setEnregistrementTerritoire(String[] enregistrementTerritoire)
	{
		this.enregistrementTerritoire = enregistrementTerritoire;
	}
	public String getLabelTerritoire()
	{
		return this.labelTerritoire;
	}
	private void setLabelTerritoire()
	{
		String labelTerritoire2 = "";
		if(getEnregistrementTerritoire() != null)
		{
			for(int i = 0 ; i < getEnregistrementTerritoire().length ; ++i)
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
					
				if(i < getEnregistrementTerritoire().length - 1)
				{
					labelTerritoire2 = labelTerritoire2 + getEnregistrementTerritoire()[i] + ", " + finChaine;
				}
				else
				{
					labelTerritoire2 = labelTerritoire2 + getEnregistrementTerritoire()[i] + finChaine;
				}
			}
		}
		else
		{
			labelTerritoire2 = "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !";
		}
		this.labelTerritoire = labelTerritoire2;
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
				FenetreEtudeTerritoriale fenetre = new FenetreEtudeTerritoriale(getChoixDesClassifications(), getChoixDesNiveaux());
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
			if(getLabelTerritoire() == "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !")
			{
				JOptionPane erreurMessage = new JOptionPane();
				erreurMessage.showMessageDialog(null, "Erreur ! Vous n'avez choisi aucun territoire !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				FenetreEtudeTerritoriale3 fenetre = new FenetreEtudeTerritoriale3(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), CompareAnneeDebut.getAnneeDebut(), CompareAnneeFin.getAnneeFin());
				fenetre.setVisible(true);
				dispose();
			}
		}
	}
	class ClicTerritoire implements ActionListener
	{
		//Méthode obligatoire à implanter avec l'interface ActionEvent
		public void actionPerformed(ActionEvent e)
		{
			if (boutonTerritoire.isEnabled()) 
			{
				listeDesTerritoires = new BoiteACocherTerritoireV2(getChoixDesClassifications(), getChoixDesNiveaux());
				listeDesTerritoires.setVisible(true);
				dispose();
			}
		}
	}
}