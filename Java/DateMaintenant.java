//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;
import java.awt.*;

public class DateMaintenant
{
	private String dateString = "";
	private double dateNumber = 0;
	
	/*public static void main(String[] args)
	{
		DateMaintenant dateMaintenant = new DateMaintenant();
		System.out.println(dateMaintenant.getDateMaintenant());
		System.out.println(dateMaintenant.getDateMaintenantNombre());
	}*/
	
	public DateMaintenant()
	{
		Calendar calendaire = Calendar.getInstance();
		int jour = calendaire.get(Calendar.DAY_OF_MONTH);
		int mois = (calendaire.get(Calendar.MONTH) + 1);
		int annee = calendaire.get(Calendar.YEAR);
		int heure = calendaire.get(Calendar.HOUR_OF_DAY);
		int minute = calendaire.get(Calendar.MINUTE);
		int seconde = calendaire.get(Calendar.SECOND);
		int[] dateCode = {jour, mois, annee, heure, minute, seconde};
		String dateNumber2 = "";
		for(int i = 0 ; i < dateCode.length ; ++i)
		{
			String chaineFin = "";
			if(i < dateCode.length - 1)
			{
				chaineFin = "-";
			}
			else
			{
				chaineFin = "";
			}
			if(dateCode[i] < 10)
			{
				dateString = dateString + "0" +  Integer.toString(dateCode[i]) + chaineFin;
				dateNumber2 = dateNumber2 + "0" +  Integer.toString(dateCode[i]);
			}
			else
			{
				dateString = dateString +  Integer.toString(dateCode[i]) + chaineFin;
				dateNumber2 = dateNumber2 +  Integer.toString(dateCode[i]);
			}
		}
		dateNumber = Double.parseDouble(dateNumber2);
	}
	public String getDateMaintenant()
	{
		return dateString;
	}
	public double getDateMaintenantNombre()
	{
		return dateNumber;
	}
}