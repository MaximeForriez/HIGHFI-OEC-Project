//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreEtudeFlux2 extends JFrame
{
	private String choixDesClassifications = "";
	private String choixDesNiveaux = "";
	private BoiteACocherTerritoireFlux1 listeDesTerritoires = null;
	private BoiteACocherTerritoireFlux2 listeDesTerritoires2 = null;
	private BoiteACocherDateDebut listeDesDates1 = null;
	private BoiteACocherDateFin listeDesDates2 = null;
	private Container contentPane = null;
	private JPanel panel3 = null;
	private JButton boutonRetour = null;
	private JButton boutonSuite = null;
	private JButton boutonAnnuler = null;
	private JButton boutonTerritoire = null;
	private JButton boutonTerritoire2 = null;

	private String[] enregistrementTerritoire = null;
	private String labelTerritoire = null;
	private String[] enregistrementTerritoire2 = null;
	private String labelTerritoire2 = null;
	private int anneeDebut = 0;
	private int anneeFin = 0;

	public FenetreEtudeFlux2(String text, String text2)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		init();
	}
	public FenetreEtudeFlux2(String text, String text2, String[] text3, String[] text4)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setEnregistrementTerritoire(text3);
		setEnregistrementTerritoire2(text4);
		init();
	}
	public FenetreEtudeFlux2(String text, String text2, int anneeDebutSelection, int anneeFinSelection)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setAnneeDebut(anneeDebutSelection);
		setAnneeFin(anneeFinSelection);
		init();
	}
	public FenetreEtudeFlux2(String text, String text2, String[] text3, String[] text4, int anneeDebutSelection, int anneeFinSelection)
	{
		//Récupération des choix de l'utilisateur
		setChoixDesClassifications(text);
		setChoixDesNiveaux(text2);
		setEnregistrementTerritoire(text3);
		setEnregistrementTerritoire2(text4);
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
		setLabelTerritoire2();
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
		boutonSuite = new JButton("Suite");
		boutonAnnuler = new JButton("Fermer");
		panel3.add(boutonRetour);
		panel3.add(boutonSuite);
		panel3.add(boutonAnnuler);
		contentPane.add("South", panel3);
		//Evénements
//		boutonRetour.addActionListener(new BoutonRetourChoixEtude());
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
		//Gestion de l'objet : Choix des territoires
		panel.add(new Label("Choix des territoires (Origine) : "));
		JPanel boutonTerritoirePanel = new JPanel();
		boutonTerritoire = new JButton("Choisir les territoires");
		boutonTerritoirePanel.setLayout(new BoxLayout(boutonTerritoirePanel, BoxLayout.LINE_AXIS));
		boutonTerritoirePanel.add(boutonTerritoire);
		boutonTerritoirePanel.add(Box.createRigidArea(new Dimension(50,100)));
		panel.add(boutonTerritoirePanel);
		boutonTerritoire.addActionListener(new ClicTerritoire());
		//Gestion de la zone de texte affichant les sélections
		panel.add(new Label("S\u00e9lection des territoires (Origine) : "));
		JTextArea zoneDeTexteChoixTerritoires = new JTextArea(getLabelTerritoire());
		zoneDeTexteChoixTerritoires.setEditable(false);//Interdire la modification du texte de la zone
		zoneDeTexteChoixTerritoires.setBackground(new Color(240,240,240));//Couleur du fond
		zoneDeTexteChoixTerritoires.setForeground(Color.GRAY.brighter());//Couleur du texte
		panel.add(new JScrollPane(zoneDeTexteChoixTerritoires));
		//Gestion de l'objet : Choix des territoires
		panel.add(new Label("Choix des territoires (Destination) : "));
		JPanel boutonTerritoirePanel2 = new JPanel();
		boutonTerritoire2 = new JButton("Choisir les territoires");
		boutonTerritoirePanel2.setLayout(new BoxLayout(boutonTerritoirePanel2, BoxLayout.LINE_AXIS));
		boutonTerritoirePanel2.add(boutonTerritoire2);
		boutonTerritoirePanel2.add(Box.createRigidArea(new Dimension(50,100)));
		panel.add(boutonTerritoirePanel2);
		boutonTerritoire2.addActionListener(new ClicTerritoire2());
		//Gestion de la zone de texte affichant les sélections
		panel.add(new Label("S\u00e9lection des territoires (Destination) : "));
		JTextArea zoneDeTexteChoixTerritoires2 = new JTextArea(getLabelTerritoire2());
		zoneDeTexteChoixTerritoires2.setEditable(false);//Interdire la modification du texte de la zone
		zoneDeTexteChoixTerritoires2.setBackground(new Color(240,240,240));//Couleur du fond
		zoneDeTexteChoixTerritoires2.setForeground(Color.GRAY.brighter());//Couleur du texte
		panel.add(new JScrollPane(zoneDeTexteChoixTerritoires2));
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
		if(getEnregistrementTerritoire() == null || getEnregistrementTerritoire2() == null)
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
		if(getEnregistrementTerritoire() == null || getEnregistrementTerritoire2() == null)
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
	public String[] getEnregistrementTerritoire2()
	{
		return this.enregistrementTerritoire2;
	}
	private void setEnregistrementTerritoire2(String[] enregistrementTerritoire)
	{
		this.enregistrementTerritoire2 = enregistrementTerritoire;
	}
	public String getLabelTerritoire()
	{
		return this.labelTerritoire;
	}
	private void setLabelTerritoire()
	{
		String labelTerritoire2b = "";
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
					labelTerritoire2b = labelTerritoire2b + getEnregistrementTerritoire()[i] + ", " + finChaine;
				}
				else
				{
					labelTerritoire2b = labelTerritoire2b + getEnregistrementTerritoire()[i] + finChaine;
				}
			}
		}
		else
		{
			labelTerritoire2b = "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !";
		}
		this.labelTerritoire = labelTerritoire2b;
	}
	public String getLabelTerritoire2()
	{
		return this.labelTerritoire2;
	}
	private void setLabelTerritoire2()
	{
		String labelTerritoire2b = "";
		if(getEnregistrementTerritoire2() != null)
		{
			for(int i = 0 ; i < getEnregistrementTerritoire2().length ; ++i)
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
					
				if(i < getEnregistrementTerritoire2().length - 1)
				{
					labelTerritoire2b = labelTerritoire2b + getEnregistrementTerritoire2()[i] + ", " + finChaine;
				}
				else
				{
					labelTerritoire2b = labelTerritoire2b + getEnregistrementTerritoire2()[i] + finChaine;
				}
			}
		}
		else
		{
			labelTerritoire2b = "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !";
		}
		this.labelTerritoire2 = labelTerritoire2b;
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
				FenetreEtudeFlux fenetre = new FenetreEtudeFlux(getChoixDesClassifications(), getChoixDesNiveaux());
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
			if(getLabelTerritoire() == "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !" && getLabelTerritoire2() == "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !" )
			{
				JOptionPane erreurMessage = new JOptionPane();
				erreurMessage.showMessageDialog(null, "Erreur ! Vous n'avez choisi aucun territoire !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(getLabelTerritoire() == "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !" && getLabelTerritoire2() != "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !" )
				{
					JOptionPane erreurMessage = new JOptionPane();
					erreurMessage.showMessageDialog(null, "Erreur ! Vous n'avez choisi aucun territoire originaire !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(getLabelTerritoire() != "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !" && getLabelTerritoire2() == "Pas de donn\u00e9es s\u00e9lectionn\u00e9es !" )
					{
						JOptionPane erreurMessage = new JOptionPane();
						erreurMessage.showMessageDialog(null, "Erreur ! Vous n'avez choisi aucun territoire destinataire !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						FenetreEtudeFlux3 fenetre = new FenetreEtudeFlux3(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), CompareAnneeDebut.getAnneeDebut(), CompareAnneeFin.getAnneeFin());
						fenetre.setVisible(true);
						dispose();
					}
				}
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
				listeDesTerritoires = new BoiteACocherTerritoireFlux1(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), getAnneeDebut(), getAnneeFin());
				listeDesTerritoires.setVisible(true);
				dispose();
			}
		}
	}
	class ClicTerritoire2 implements ActionListener
	{
		//Méthode obligatoire à implanter avec l'interface ActionEvent
		public void actionPerformed(ActionEvent e)
		{
			if (boutonTerritoire2.isEnabled()) 
			{
				listeDesTerritoires2 = new BoiteACocherTerritoireFlux2(getChoixDesClassifications(), getChoixDesNiveaux(), getEnregistrementTerritoire(), getEnregistrementTerritoire2(), getAnneeDebut(), getAnneeFin());
				listeDesTerritoires2.setVisible(true);
				dispose();
			}
		}
	}
}