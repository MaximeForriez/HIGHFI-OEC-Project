//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreChoixEtude extends JFrame
{
	private Container contentPane = null;
	private JRadioButton boutonRadio1 = null;
	private JRadioButton boutonRadio2 = null;
	private JRadioButton boutonRadio3 = null;
	private ButtonGroup buttonGroup = null;
	private JButton boutonOK = null;
	private JButton boutonAnnuler = null;
	
	public FenetreChoixEtude()
	{
		this.setTitle("Choix du type d\'\u00e9tude");
		this.setSize(350,200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		init();
	}
//******************************************
//*Initiatilisation des variables de classe*
//******************************************
	private void init()
	{
		boutonRadio1 = new JRadioButton("\u00c9tude territoriale", false);
		boutonRadio2 = new JRadioButton("\u00c9tude des flux", false);
		boutonRadio3 = new JRadioButton("\u00c9tude des produits", false);
		buttonGroup = new ButtonGroup();
		boutonOK = new JButton("OK");
		boutonAnnuler = new JButton("Annuler");
		
		//Couche de contenu du JFrame
		contentPane = this.getContentPane();

		//Définir le groupe de boutons radio
		buttonGroup.add(boutonRadio1);
		buttonGroup.add(boutonRadio2);
		buttonGroup.add(boutonRadio3);

		//Positionner les éléments dans la fenêtre
		contentPane.add("East", new JPanel());
		contentPane.add("Center", fondBoutonRadio());
		contentPane.add("South", fondBoutonsDAction());

		//Gestion des événements
			//Gestion du bouton OK : il ne déclenche quelque chose que si l'un bouton radio est poussé.
			boutonOK.addActionListener(new ClicOK());
			//Gestion du bouton de fermeture
			boutonAnnuler.addActionListener(new FenetreFermeture());//Action du bouton "Quitter"
	}
//************************************
//*Gestion de l'affichage des widgets*
//************************************
	//Définir les objets du fond avec les boutons radio
	private JPanel fondBoutonRadio()
	{
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 1, 20, 20));
		p1.add(boutonRadio1);
		p1.add(boutonRadio2);
		p1.add(boutonRadio3);
		return p1;
	}
	
	//Définir les boutons d'action
	private JPanel fondBoutonsDAction()
	{
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		boutonOK.setPreferredSize(new Dimension(80,20));
		boutonAnnuler.setPreferredSize(new Dimension(80,20));
		p3.add(boutonOK);
		p3.add(boutonAnnuler);
		return p3;
	}
//************************
//*Gestion des événements*
//************************
	class ClicOK implements ActionListener
	{
		//Méthode obligatoire à implanter avec l'interface ActionEvent
		public void actionPerformed(ActionEvent e)
		{
			if(boutonRadio1.isSelected() == false && boutonRadio2.isSelected() == false && boutonRadio3.isSelected() == false)
			{
				JOptionPane erreurMessage = new JOptionPane();
				erreurMessage.showMessageDialog(null, "Erreur ! Aucun choix n\'a \u00e9t\u00e9 fait !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(boutonRadio1.isSelected() == true)
				{
					FenetreEtudeTerritoriale fenetre = new FenetreEtudeTerritoriale();
					fenetre.setVisible(true);
					dispose();//Fermeture de la fenêtre qui en appelle une autre
				}
				else
				{
					if(boutonRadio2.isSelected() == true)
					{
//						FenetreFlux fenetre = new FenetreFlux();
						FenetreEtudeFlux fenetre = new FenetreEtudeFlux();
						fenetre.setVisible(true);
						dispose();//Fermeture de la fenêtre qui en appelle une autre
					}
					else
					{
						if(boutonRadio3.isSelected() == true)
						{
							//Début - Message temporaire à supprimer
							/*
							JOptionPane informationMessage = new JOptionPane();
							int informationMessageValeur = informationMessage.showConfirmDialog(null, "Attention ! Seule la classification HS92 a \u00e9t\u00e9 mise \u00e0 jour.", "Message d'information", JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
							if(informationMessageValeur == JOptionPane.OK_OPTION)
							{
								FenetreEtudeProduit fenetre = new FenetreEtudeProduit();
								fenetre.setVisible(true);
								dispose();//Fermeture de la fenêtre qui en appelle une autre
							}
							else
							{
								setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
								setVisible(true);
							}
							*/
							//Fin - Message temporaire à supprimer
							
							//Début : Code normal :
							FenetreEtudeProduit fenetre = new FenetreEtudeProduit();
							fenetre.setVisible(true);
							dispose();//Fermeture de la fenêtre qui en appelle une autre
							//Fin - Code normal
						}
						else
						{
						
						}
					}
				}
			}
		}
	}
}