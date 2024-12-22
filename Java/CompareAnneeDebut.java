//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CompareAnneeDebut
{
	private static int anneeDebut = 0;
	
	public CompareAnneeDebut(int annee)
	{
		this.anneeDebut = annee;
	}
	public static int getAnneeDebut()
	{
		return anneeDebut;
	}
}