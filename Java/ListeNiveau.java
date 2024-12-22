//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
public enum ListeNiveau
{
	NIVEAU_0("Section","Donnees-HS-Agreges.csv",0,"year_origin_destination_","_S\\"),NIVEAU_1("Chapitre (2-digit)","Code-2-digit.csv",2,"year_origin_destination_","_2\\"),NIVEAU_2("Titre (4-digit)","-4-digit.csv",4,"year_origin_destination_","_4\\"),NIVEAU_3("Sous-titre (6-digit)","-6-digit.csv",6,"year_origin_destination_","_6\\");

	private String classification;
	private String nomCSV;
	private int niveau;
	private String sousdossierp1;
	private String sousdossierp2;
	
	ListeNiveau(String classification, String nomCSV, int niveau, String sousdossierp1, String sousdossierp2)
	{
		this.classification = classification;
		this.nomCSV = nomCSV;
		this.niveau = niveau;
		this.sousdossierp1 = sousdossierp1;
		this.sousdossierp2 = sousdossierp2;
	}
	
	public String getClassification()
	{
		return  this.classification;
	}
	public String getNomCSV()
	{
		return  this.nomCSV;
	}
	public int getNomNiveau()
	{
		return  this.niveau;
	}
	public String getSousDossierP1()
	{
		return  this.sousdossierp1;
	}
	public String getSousDossierP2()
	{
		return  this.sousdossierp2;
	}
}