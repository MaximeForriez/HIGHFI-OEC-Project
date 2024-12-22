//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreEtudeTerritoriale extends JFrame
{
	private BoiteACocherClassification listeDesClassifications = null;
	private BoiteACocherNiveau listeDesNiveaux = null;
	private Container contentPane = null;
	private JButton boutonRetour = null;
	private JButton boutonSuite = null;
	private JButton boutonAnnuler = null;
	private JPanel panel3 = null;

	private String[] listeHS = null;
	private String[] listeNiveau = null;
	private int choixDesClassifications = 0;
	private int choixDesNiveaux = 0;
	
	public FenetreEtudeTerritoriale()
	{
		setListeHS();
		setListeNiveau();
		setChoixDesClassifications(0);
		setChoixDesNiveaux(0);
		init();
	}
	public FenetreEtudeTerritoriale(String classification, String niveau)
	{
		setListeHS();
		setListeNiveau();
		for(int i = 0 ; i < getListeHS().length ; ++i)
		{
			if(classification.equals(getListeHS()[i]))
			{
				setChoixDesClassifications(i);
			}
		}
		for(int i = 0 ; i < getListeNiveau().length ; ++i)
		{
			if(niveau.equals(getListeNiveau()[i]))
			{
				setChoixDesNiveaux(i);
			}
		}
		init();
	}
//****************************************
//*Initialisation des variables de classe*
//****************************************
	private void init()
	{
		this.setTitle("\u00c9tude territoriale");
		this.setSize(450,200);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Couche de contenu du Frame
		contentPane = this.getContentPane();
		//Corps central
		contentPane.add(zoneMouvante());
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
		JPanel panel = new JPanel(new GridLayout(2, 2));
		//Gestion de l'objet : Choix des classifications
		panel.add(new Label("Choix de la classification : "));
		if(getChoixDesClassifications() == 0)
		{
			listeDesClassifications = new BoiteACocherClassification();
		}
		else
		{
			listeDesClassifications = new BoiteACocherClassification(getChoixDesClassifications());
		}
		panel.add(listeDesClassifications.getPanel());
		//Gestion de l'objet : Niveau d'analyse
		panel.add(new Label("Choix du niveau d'analyse : "));
		if(this.getChoixDesNiveaux() == 0)
		{
			listeDesNiveaux = new BoiteACocherNiveau();
		}
		else
		{
			listeDesNiveaux = new BoiteACocherNiveau(getChoixDesNiveaux());
		}
		panel.add(listeDesNiveaux.getPanel());
		return panel;
	}
//********************
//*Getters et setters*
//********************
	public int getChoixDesClassifications()
	{
		return this.choixDesClassifications;
	}
	private void setChoixDesClassifications(int choixDesClassifications)
	{
		this.choixDesClassifications = choixDesClassifications;
	}
	public int getChoixDesNiveaux()
	{
		return this.choixDesNiveaux;
	}
	private void setChoixDesNiveaux(int choixDesNiveaux)
	{
		this.choixDesNiveaux = choixDesNiveaux;
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
				FenetreChoixEtude fenetre = new FenetreChoixEtude();
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
			FenetreEtudeTerritoriale2 fenetre = new FenetreEtudeTerritoriale2(listeDesClassifications.getChoixDesClassifications(), listeDesNiveaux.getChoixDesNiveaux());
			fenetre.setVisible(true);
			dispose();
		}
	}
}