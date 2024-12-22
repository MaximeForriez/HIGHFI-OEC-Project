//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CompareAnnee extends JFrame
{
	private int anneeDebut = CompareAnneeDebut.getAnneeDebut();
	private int anneeFin = CompareAnneeFin.getAnneeFin();
	
	public CompareAnnee()
	{
		if(anneeDebut > anneeFin)
		{
			JOptionPane erreurMessage = new JOptionPane();
			erreurMessage.showMessageDialog(null, "Erreur ! La date de fin doit \u00eatre sup\u00e9rieure ou \u00e9gale \u00e0 la date de d\u00e9but !", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			
		}
	}
}