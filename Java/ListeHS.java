//Programme de Maxime Forriez pour HIGHFI datant de l'été 2020
//Programme corrigé en juin 2022 pour y introduire les deux nouvelles classifications
public enum ListeHS
{
	HS92("Classification HS92",1995,2021,"hs92","HS6-REV-1992 - 1995-2014\\"),HS96("Classification HS96",1998,2021,"hs96","HS6-REV-1996 - 1998-2014\\"),HS02("Classification HS02",2003,2021,"hs02","HS6-REV-2002 - 2003-2014\\"),HS07("Classification HS07",2008,2021,"hs07","HS6-REV-2007 - 2008-2014\\"),HS12("Classification HS12",2013,2021,"hs12","HS6-REV-2012\\"),HS17("Classification HS17",2018,2021,"hs17","HS6-REV-2017\\");
	//,HS22("Classification HS22",2023,20,"hs22","HS6-REV-2022\\");
		
	private String classification;
	private String synthese;
	private int anneeDebut;
	private int anneeFin;
	private String dossier;
	
	ListeHS(String classification, int anneeDebut, int anneeFin, String synthese, String dossier)
	{
		this.classification = classification;
		this.anneeDebut = anneeDebut;
		this.anneeFin = anneeFin;
		this.synthese = synthese;
		this.dossier = dossier;
	}
	
	public String getClassification()
	{
		return  this.classification;
	}
	public String getNomFichierSynthese()
	{
		return this.synthese;
	}
	public int getAnneeDebut()
	{
		return this.anneeDebut;
	}
	public int getAnneeFin()
	{
		return this.anneeFin;
	}
	public String getDossier()
	{
		return this.dossier;
	}
}