//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//public class BoiteACocherNiveau extends Applet implements ItemListener
public class BoiteACocherNiveau implements ItemListener
{
	private Choice listeDesNiveaux = null;
	private String choixDesNiveaux = "";
	private String[] listeNiveau = null;
	private int selection = 0;

	public BoiteACocherNiveau()
	{
		init();
	}
	public BoiteACocherNiveau(int niveau)
	{
		setSelection(niveau);
		init();
	}
//****************************************
//*Initialisation des variables de classe*
//****************************************
	private void init()
	{
		setListeNiveau();
		listeDesNiveaux = new Choice();
		for(int i = 0 ; i < getListeNiveau().length ; ++i)
		{
			listeDesNiveaux.addItem(getListeNiveau()[i]);
		}
		listeDesNiveaux.select(getListeNiveau()[getSelection()]);
		setChoixDesNiveaux(getListeNiveau()[getSelection()]);
		listeDesNiveaux.addItemListener(this);
	}
//************************************
//*Gestion de l'affichage des widgets*
//************************************
	public JPanel getPanel()
	{
		JPanel panel = new JPanel();
		panel.add(listeDesNiveaux);
		return panel;
	}
//********************
//*Getters et setters*
//********************
	public int getSelection()
	{
		return this.selection;
	}
	private void setSelection(int valeur)
	{
		this.selection = valeur;
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
	//Variable choisie
	public String getChoixDesNiveaux()
	{
		return this.choixDesNiveaux;
	}
	private void setChoixDesNiveaux(String text)
	{
		this.choixDesNiveaux = text;
	}
//************************
//*Gestion des événements*
//************************
	public void itemStateChanged(ItemEvent e)
	{
		setChoixDesNiveaux(listeDesNiveaux.getSelectedItem());
	}
}