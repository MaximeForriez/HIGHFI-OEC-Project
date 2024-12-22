//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.util.*;
import java.io.*;
import java.nio.*;

public class Separateur
{
	private String chaineDeTexte = "";
	private String separateurDeTexte = ",";//Valeur par défaut
	private char separateurDeTexte2;
	private int nombreDeColonne = 0;
	private String[] donneesTableau = null;//Variable de sortie
	
	public Separateur(String chaineDeTexte, String separateurDeTexte)
	{
		//Initialisation des variables de classe
		setChaineDeTexte(chaineDeTexte);
		setSeparateurDeTexte(separateurDeTexte);
		setSeparateurDeTexteCaractere(separateurDeTexteConv(getSeparateurDeTexte()));
		controleDesCaracteres();
	}
	//Convertir le séparateur de texte en caractère
	private char separateurDeTexteConv(String texte)
	{
		return texte.charAt(0);
	}
//****************************************
//*Priorité des guillemets sur la virgule*
//****************************************
	//Attention ! Ce code ne marche que s'il existe deux guillemets dans la zone de texte à séparer. Exemple ,"nnn", -> ça marche, mais ,"jkjk"jkjk"n", -> ça ne marche pas.
	//Contrôle des caractères
	private void controleDesCaracteres()
	{
		//Recherche prioritaire des guillemets
		String data = getChaineDeTexte();
		int longueurChaine = data.length();
		char[] caracteres = new char[longueurChaine];
		ArrayList<Integer> posGuillemet = new ArrayList<Integer>();
		posGuillemet.add(-1);
		for(int i = 0 ; i < longueurChaine ; ++i)
		{
			caracteres[i] = data.charAt(i);
			if(caracteres[i] == '\"')
			{
				posGuillemet.add(i);
			}
		}
		posGuillemet.add(longueurChaine);
		ArrayList<String> chaine2 = new ArrayList<String>();
		for(int i = 0 ; i < posGuillemet.size() - 1 ; i++)
		{
			String chaine = data.substring(posGuillemet.get(i) + 1, posGuillemet.get(i + 1));
			if(i != 0)
			{
				chaine2.add("\"");
			}
			chaine2.add(chaine);
		}
		//Recherche du séparateur de texte
		ArrayList<String> chaine3 = new ArrayList<String>();
		int count = 0;
		while(count < chaine2.size())
		{
			String test = chaine2.get(count);
			if(test != "\"")
			{
				int longueurChaine2 = test.length();
				char[] caracteres2 = new char[longueurChaine2];
				ArrayList<Integer> posVirgule = new ArrayList<Integer>();
				posVirgule.add(-1);
				for(int i = 0 ; i < longueurChaine2 ; ++i)
				{
					caracteres2[i] = test.charAt(i);
					if(caracteres2[i] == getSeparateurDeTexteCaractere())
					{
						posVirgule.add(i);
					}
				}
				posVirgule.add(longueurChaine2);
				for(int i = 0 ; i < posVirgule.size() - 1 ; i++)
				{
					String chaine = data.substring(posVirgule.get(i) + 1, posVirgule.get(i + 1));
					if(chaine != "")
					{
						chaine3.add(chaine);
					}
				}
				count++;
			}
			else
			{
				if(test != getSeparateurDeTexte())
				{
					count++;
					String chaine = chaine2.get(count);
					chaine3.add(chaine);
					count++;
				}
				else
				{
					count++;
				}
			}
		}
		//Nettoyage du résultat
		ArrayList<String> chaine4 = new ArrayList<String>();
		count = 0;
		while(count < chaine3.size())
		{
			String test = chaine3.get(count);
			int max = test.length();
			if(max > 0)
			{
				char test2 = test.charAt(0);
				if(test2 == getSeparateurDeTexteCaractere())
				{
					String test3 = test.substring(1, max);
					int max2 = test3.length();
					if(max2 > 0)
					{
						chaine4.add(test3);
					}
				}
				else
				{
					chaine4.add(test);
				}
			}
			count++;
		}
		setNombreDeColonne(chaine4.size());
		String[] tableauChaine = new String[chaine4.size()];
		for(int i = 0 ; i < chaine4.size() ; ++i)
		{
			tableauChaine[i] = chaine4.get(i);
		}
		setDonneesTableau(tableauChaine);
		//Affichage du découpage
//		System.out.println(chaine4);
//		System.out.print(chaine4.size() + ",");
	}
//********************
//*Getters et setters*
//********************
	//Variable nombreDeColonne
	public int getNombreDeColonne()
	{
		return nombreDeColonne;
	}
	private void setNombreDeColonne(int nombreDeColonne)
	{
		this.nombreDeColonne = nombreDeColonne;
	}
	//Traitement du tableau de données sans les titres en version data
	public String[] getDonneesTableau()
	{
		return this.donneesTableau;
	}
	private void setDonneesTableau(String[] chaine)
	{
		this.donneesTableau = chaine;
	}
	//Chaîne de caractères récupérée
	public String getChaineDeTexte()
	{
		return this.chaineDeTexte;
	}
	private void setChaineDeTexte(String chaine)
	{
		this.chaineDeTexte = chaine;
	}
	//Séparateur de texte récupéré
	public String getSeparateurDeTexte()
	{
		return this.separateurDeTexte;
	}
	private void setSeparateurDeTexte(String separateurDeTexte)
	{
		this.separateurDeTexte = separateurDeTexte;
	}
	public char getSeparateurDeTexteCaractere()
	{
		return this.separateurDeTexte2;
	}
	private void setSeparateurDeTexteCaractere(char separateurDeTexte)
	{
		this.separateurDeTexte2 = separateurDeTexte;
	}
}