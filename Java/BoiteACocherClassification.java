//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//public class BoiteACocherClassification extends Applet implements ItemListener
public class BoiteACocherClassification implements ItemListener
{
	private Choice listeDesClassifications = null;
	private String choixDesClassifications = "";
	private String[] listeHS = null;
	private int selection = 0;

	public BoiteACocherClassification()
	{
		init();
	}
	public BoiteACocherClassification(int classification)
	{
		setSelection(classification);
		init();
	}
//****************************************
//*Initialisation des variables de classe*
//****************************************
	private void init()
	{
		setListeHS();
		listeDesClassifications = new Choice();
		for(int i = 0 ; i < getListeHS().length ; ++i)
		{
			listeDesClassifications.addItem(getListeHS()[i]);
		}
		listeDesClassifications.select(getListeHS()[getSelection()]);
		setChoixDesClassifications(getListeHS()[getSelection()]);
		listeDesClassifications.addItemListener(this);
	}
//************************************
//*Gestion de l'affichage des widgets*
//************************************
	public JPanel getPanel()
	{
		JPanel panel = new JPanel();
		panel.add(listeDesClassifications);
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
	//Variable choisie
	public String getChoixDesClassifications()
	{
		return this.choixDesClassifications;
	}
	private void setChoixDesClassifications(String text)
	{
		this.choixDesClassifications = text;
	}
//************************
//*Gestion des événements*
//************************
	public void itemStateChanged(ItemEvent e)
	{
		setChoixDesClassifications(listeDesClassifications.getSelectedItem());
	}	
}