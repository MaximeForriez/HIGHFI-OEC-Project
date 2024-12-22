//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CompareAnneeFin
{
	private static int anneeFin = 0;
	
	public CompareAnneeFin(int annee)
	{
		this.anneeFin = annee;
	}
	public static int getAnneeFin()
	{
		return anneeFin;
	}
}