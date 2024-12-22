//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//public class BoiteACocherDateDebut extends Applet implements ItemListener
public class BoiteACocherDateDebut implements ItemListener
{
	private String choixDesClassifications = "";
	private int choixDeLaDateDeDebut = 0;
	private Choice listeDateDebut = null;
	private int anneeDebut = 0;
	private String[] listeHS = null;
	private int[] listeHSAnneeDebut = null;
	private int anneeFin = 0;
	private int[] listeHSAnneeFin = null;
	private int anneeDebutSelection = 0;
	
	public BoiteACocherDateDebut(String text)
	{
		setChoixDesClassifications(text);
		setListeHS();
		setListeHSAnneeDebut();
		setListeHSAnneeFin();
		for(int i = 0 ; i < getListeHS().length ; ++i)
		{
			if(getChoixDesClassifications() == getListeHS()[i])
			{
				setAnneeDebut(getListeHSAnneeDebut()[i]);
				setAnneeFin(getListeHSAnneeFin()[i]);
			}
		}
		init();
	}
	public BoiteACocherDateDebut(String text, int anneeDebut)
	{
		setChoixDesClassifications(text);
		setListeHS();
		setListeHSAnneeDebut();
		setListeHSAnneeFin();
		for(int i = 0 ; i < getListeHS().length ; ++i)
		{
			if(getChoixDesClassifications() == getListeHS()[i])
			{
				setAnneeDebut(getListeHSAnneeDebut()[i]);
				setAnneeFin(getListeHSAnneeFin()[i]);
			}
		}
		int positionAnneeDebut = 0;
		for(int i = getAnneeDebut() ; i <= getAnneeFin() ; ++i)
		{
			if(i == anneeDebut)
			{
				positionAnneeDebut = i - getAnneeDebut();
			}
		}
		setAnneeDebutSelection(positionAnneeDebut);
		init();
	}
//****************************************
//*Initialisation des variables de classe*
//****************************************
	private void init()
	{
		listeDateDebut = new Choice();
		for(int i = getAnneeDebut() ; i <= getAnneeFin() ; ++i)
		{
			listeDateDebut.addItem(Integer.toString(i));
		}
		listeDateDebut.select(getAnneeDebutSelection());
		listeDateDebut.addItemListener(this);
		CompareAnneeDebut compareAnneeDebut = new CompareAnneeDebut(getAnneeDebut());
	}
//************************************
//*Gestion de l'affichage des widgets*
//************************************
	public JPanel getPanel()
	{
		JPanel panel = new JPanel();
		panel.add(listeDateDebut);
		return panel;
	}
	public JPanel getPanelGris()
	{
		JPanel panel = new JPanel();
		listeDateDebut.setEnabled(false);
		panel.add(listeDateDebut);
		return panel;
	}
//********************
//*Getters et setters*
//********************
	public int getAnneeDebut()
	{
		return this.anneeDebut;
	}
	private void setAnneeDebut(int annee)
	{
		this.anneeDebut = annee;
	}
	public int getAnneeFin()
	{
		return this.anneeFin;
	}
	private void setAnneeFin(int annee)
	{
		this.anneeFin = annee;
	}
	public int getAnneeDebutSelection()
	{
		return this.anneeDebutSelection;
	}
	private void setAnneeDebutSelection(int selection)
	{
		this.anneeDebutSelection = selection;
	}
	public String getChoixDesClassifications()
	{
		return this.choixDesClassifications;
	}
	private void setChoixDesClassifications(String nomClassification)
	{
		this.choixDesClassifications = nomClassification;
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
	public int[] getListeHSAnneeDebut()
	{
		return this.listeHSAnneeDebut;
	}
	private void setListeHSAnneeDebut()
	{
		listeHSAnneeDebut = new int[ListeHS.values().length];
		for(int i = 0 ; i < ListeHS.values().length ; ++i)
		{
			listeHSAnneeDebut[i] = ListeHS.values()[i].getAnneeDebut();
		}
		this.listeHSAnneeDebut = listeHSAnneeDebut;
	}
	public int[] getListeHSAnneeFin()
	{
		return this.listeHSAnneeFin;
	}
	private void setListeHSAnneeFin()
	{
		listeHSAnneeFin = new int[ListeHS.values().length];
		for(int i = 0 ; i < ListeHS.values().length ; ++i)
		{
			listeHSAnneeFin[i] = ListeHS.values()[i].getAnneeFin();
		}
		this.listeHSAnneeFin = listeHSAnneeFin;
	}
	public int getChoixDeLaDateDeDebut()
	{
		return this.choixDeLaDateDeDebut;
	}
	private void setChoixDeLaDateDeDebut(int annee)
	{
		this.choixDeLaDateDeDebut = annee;
	}
//************************
//*Gestion des événements*
//************************
	public void itemStateChanged(ItemEvent e)
	{
		setChoixDeLaDateDeDebut(Integer.parseInt(listeDateDebut.getSelectedItem()));
		CompareAnneeDebut compareAnneeDebut = new CompareAnneeDebut(getChoixDeLaDateDeDebut());
		CompareAnnee compareAnnee = new CompareAnnee();
	}
}