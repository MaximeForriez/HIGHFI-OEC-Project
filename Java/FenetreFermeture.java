//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreFermeture implements ActionListener
{
	//Méthode obligatoire à implanter avec l'interface ActionListener
	public void actionPerformed(ActionEvent e)
	{
		System.exit(0);//Action de fermer la fenêtre et le programme
	}
}