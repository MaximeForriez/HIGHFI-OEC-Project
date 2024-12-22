//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//public class BoiteACocherDateFin extends Applet implements ItemListener
public class BoiteACocherDateFin implements ItemListener
{
	private String choixDesClassifications = "";
	private int anneeDebut = 0;
	private String[] listeHS = null;
	private int[] listeHSAnneeDebut = null;
	private int anneeFin = 0;
	private int[] listeHSAnneeFin = null;
	private Choice listeDateFin = null;
	private int choixDeLaDateDeFin = 0;
	private int anneeFinSelection = 0;

	public BoiteACocherDateFin(String text)
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
		setAnneeFinSelection(getAnneeFin() - getAnneeDebut());
		init();
	}
	public BoiteACocherDateFin(String text, int anneeFin)
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
		int positionAnneeFin = 0;
		for(int i = getAnneeDebut() ; i <= getAnneeFin() ; ++i)
		{
			if(i == anneeFin)
			{
				positionAnneeFin = i - getAnneeDebut();
			}
		}
		setAnneeFinSelection(positionAnneeFin);
		init();
	}
//****************************************
//*Initialisation des variables de classe*
//****************************************
	private void init()
	{
		listeDateFin = new Choice();
		for(int i = getAnneeDebut() ; i <= getAnneeFin() ; ++i)
		{
			listeDateFin.addItem(Integer.toString(i));
		}
		listeDateFin.select(getAnneeFinSelection());
		listeDateFin.addItemListener(this);
		CompareAnneeFin compareAnneeFin = new CompareAnneeFin(getAnneeFin());
	}
//************************************
//*Gestion de l'affichage des widgets*
//************************************
	public JPanel getPanel()
	{
		JPanel panel = new JPanel();
		panel.add(listeDateFin);
		return panel;
	}
	public JPanel getPanelGris()
	{
		JPanel panel = new JPanel();
		listeDateFin.setEnabled(false);
		panel.add(listeDateFin);
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
	public int getAnneeFinSelection()
	{
		return this.anneeFinSelection;
	}
	private void setAnneeFinSelection(int selection)
	{
		this.anneeFinSelection = selection;
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
	public int getChoixDeLaDateDeFin()
	{
		return this.choixDeLaDateDeFin;
	}
	private void setChoixDeLaDateDeFin(int annee)
	{
		this.choixDeLaDateDeFin = annee;
	}
//************************
//*Gestion des événements*
//************************
	public void itemStateChanged(ItemEvent e)
	{
		setChoixDeLaDateDeFin(Integer.parseInt(listeDateFin.getSelectedItem()));
		CompareAnneeFin compareAnneeFin = new CompareAnneeFin(getChoixDeLaDateDeFin());
		CompareAnnee compareAnnee = new CompareAnnee();
	}
}