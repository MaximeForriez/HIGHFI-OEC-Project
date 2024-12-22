//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IlesAppMain
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				FenetreChoixEtude fenetre = new FenetreChoixEtude();
				fenetre.setVisible(true);
			}
		});
	}
}