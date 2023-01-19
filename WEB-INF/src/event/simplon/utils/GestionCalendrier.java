package event.simplon.utils;
/**
 * Classe Calendrier.java
 *
 *
 *
 * @author Mainfroid yohann
 * @version 1.00 07/03/05
 */

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

	public class GestionCalendrier {


	static Calendar ObjCalandar;		//Déclaration de l'objet de type calendar
	public static String [] TabMois = {"Janvier","Février","Mars","Avril","Mai","Juin", "Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
 	public static String [] TabJour = {"", "Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
 	public static int JourActuel; // jour actuel
 	public static int MoisActuel; // mois actuel
 	public static int AnneeActuel; //annee actuelle
 	public static int JourSem; // jour de la semaine
	public static int Jour; // jour sur lequel on se situe...
 	public static int Mois; // mois sur lequel on se situe apres avoir clique sur un des boutons
 	public static int Annee; // Annee sur lequel on se situe apres avoir clique sur un des boutons
 	public static int PremierJour; // premierJour du mois
 	public static int NbJourMois; // nbre de jour dans le mois actuel


 	
 	public static void main(String[] args) {
		
 		ObjCalandar = Calendar.getInstance ();// Création d'une instance de la classe Calendar
	 	JourActuel = Jour = ObjCalandar.get(Calendar.DAY_OF_MONTH);//Récupération de la date du jour
			JourSem = ObjCalandar.get(Calendar.DAY_OF_WEEK);// 1:dimanche 2:lundi 3:mardi 4:mercredi 5:jeudi 6:vendredi 7:samedi
			MoisActuel = Mois = ObjCalandar.get(Calendar.MONTH);// affectation de la valeur du mois en cours
			AnneeActuel = Annee = ObjCalandar.get(Calendar.YEAR);// affectation de la valeur de l'année en cours
			NbJourMois = ObjCalandar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(JourActuel);
		System.out.println(JourSem);
		System.out.println(MoisActuel);
		System.out.println(AnneeActuel);
		System.out.println(NbJourMois);
		System.out.println(TabJour[JourSem]);
		System.out.println(TabMois[MoisActuel]);
	
		System.out.println("Nous somme le :"+TabJour[JourSem]+" "+JourActuel+" "+TabMois[MoisActuel]+" "+AnneeActuel);
	
		PremierJour=PremierJourDuMois();
		System.out.println(TabJour[PremierJour]);
		MoisSuivant();
 	}
 	
 	
 	public GestionCalendrier()
         {
			ObjCalandar = Calendar.getInstance ();// Création d'une instance de la classe Calendar
		 	JourActuel = Jour = ObjCalandar.get(Calendar.DAY_OF_MONTH);//Récupération de la date du jour
 			JourSem = ObjCalandar.get(Calendar.DAY_OF_WEEK);// 1:dimanche 2:lundi 3:mardi 4:mercredi 5:jeudi 6:vendredi 7:samedi
 			MoisActuel = Mois = ObjCalandar.get(Calendar.MONTH);// affectation de la valeur du mois en cours
 			AnneeActuel = Annee = ObjCalandar.get(Calendar.YEAR);// affectation de la valeur de l'année en cours
 			NbJourMois = ObjCalandar.getActualMaximum(Calendar.DAY_OF_MONTH);
                        PremierJour=PremierJourDuMois();
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
	//-----------------------------------------------------------------------------------------------------
	/** Calcul la position du premier jour du mois dans la semaine*/

	public static int PremierJourDuMois()
		{
	 		// Récupération du premier jour du mois dans DAY_OF_MONTH
	 		ObjCalandar.set(Calendar.DAY_OF_MONTH,1);
	 		PremierJour = ObjCalandar.get(Calendar.DAY_OF_WEEK);
	 		// On réinitialise DAY_OF_MONTH au jour courant
	 		ObjCalandar.set(Calendar.DAY_OF_MONTH,JourActuel);
                
	 		return PremierJour;
		}

	//-----------------------------------------------------------------------------------------------------

	public static void MoisSuivant()
 	{
	 	ObjCalandar.set(Calendar.DAY_OF_MONTH,1);
	 	Jour = ObjCalandar.get(Calendar.DAY_OF_MONTH);
	 	ObjCalandar.set(Calendar.MONTH,Mois+1);
	 	Mois = ObjCalandar.get(Calendar.MONTH);
	 	NbJourMois= ObjCalandar.getActualMaximum(Calendar.DAY_OF_MONTH);//Récupération du nombre de jour dans le mois
	 	Annee = ObjCalandar.get(Calendar.YEAR);
	 	PremierJour=PremierJourDuMois();
 	}

 	public static void MoisPrecedent()
 	{
		 ObjCalandar.set(Calendar.DAY_OF_MONTH,1);
		 Jour = ObjCalandar.get(Calendar.DAY_OF_MONTH);
		 ObjCalandar.set(Calendar.MONTH,Mois-1);
		 Mois = ObjCalandar.get(Calendar.MONTH);
		 NbJourMois = ObjCalandar.getActualMaximum(Calendar.DAY_OF_MONTH);//Récupération du nombre de jour dans le mois
		 Annee = ObjCalandar.get(Calendar.YEAR);
		 PremierJour=PremierJourDuMois();
 	}

	 public void AnneeSuivant()
	 {
		 ObjCalandar.set(Calendar.DAY_OF_MONTH,1);
		 Jour = ObjCalandar.get(Calendar.DAY_OF_MONTH);
		 ObjCalandar.set(Calendar.YEAR,Annee+1);
		 Mois = ObjCalandar.get(Calendar.MONTH);
		 NbJourMois = ObjCalandar.getActualMaximum(Calendar.DAY_OF_MONTH);//Récupération du nombre de jour dans le mois
		 Annee = ObjCalandar.get(Calendar.YEAR);
                 PremierJour=PremierJourDuMois();

	 }

	 public static void AnneePrecedent()
	 {
		 ObjCalandar.set(Calendar.DAY_OF_MONTH,1);
		 Jour = ObjCalandar.get(Calendar.DAY_OF_MONTH);
		 ObjCalandar.set(Calendar.YEAR,Annee-1);
		 Mois = ObjCalandar.get(Calendar.MONTH);
		 NbJourMois = ObjCalandar.getActualMaximum(Calendar.DAY_OF_MONTH);//Récupération du nombre de jour dans le mois
		 Annee = ObjCalandar.get(Calendar.YEAR);
		 PremierJour=PremierJourDuMois();
	 }

    private static void jbInit() throws Exception {
    }

    
    public static List<BeanCalendrier> getListCalendier(String dateFormatddMMyyyy) {
		
    	
		LinkedList<BeanCalendrier> list = new LinkedList<BeanCalendrier>();
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf4 = new SimpleDateFormat("EEEE");
		SimpleDateFormat sdf5 = new SimpleDateFormat("MMM");
		SimpleDateFormat sdf6 = new SimpleDateFormat("ddMMyyyy");
	
		if(dateFormatddMMyyyy == null){
			dateFormatddMMyyyy = sdf.format(gc.getTime());
		}
		
			String[] strsplit = dateFormatddMMyyyy.split("/");
			int jour = Integer.parseInt(strsplit[0]);
			int mois = Integer.parseInt(strsplit[1]);
			int annee = Integer.parseInt(strsplit[2]);
			
			gc.set(Calendar.DAY_OF_MONTH, jour);
			gc.set(Calendar.MONTH, mois-1);
			gc.set(Calendar.YEAR, annee);	
		int longueurMois = gc.getActualMaximum(Calendar.DAY_OF_MONTH);

		for (int i = 1; i < longueurMois+1; i++) {
			BeanCalendrier bc = new BeanCalendrier();
			gc.set(Calendar.DAY_OF_MONTH, i);
			gc.set(Calendar.MONTH, mois-1);
			gc.set(Calendar.YEAR, annee);
			
			bc.setJourEnChiffre(sdf1.format(gc.getTime()));
			bc.setMoisEnChiffre(sdf2.format(gc.getTime()));
			bc.setAnneeEnChiffre(sdf3.format(gc.getTime()));
			bc.setJourEnLettre(sdf4.format(gc.getTime()));
			bc.setMoisEnLettre(sdf5.format(gc.getTime()));
			bc.setNbJourDuMois(longueurMois);
			bc.setJourMoisAnnee(sdf.format(gc.getTime()));
			bc.setJourDeLaSemaine(String.valueOf(gc.get(Calendar.DAY_OF_WEEK)));
			bc.setDdMMyyyy(sdf6.format(gc.getTime()));
			list.add(bc);
		}
		return list;
	}
    
    
    public static List<BeanCalendrier> listJourDeLaSemaine() {
		
		LinkedList<BeanCalendrier> list = new LinkedList<BeanCalendrier>();
		DateFormatSymbols dfsFR = new DateFormatSymbols(Locale.FRENCH);
		String[] joursSemaineFR = dfsFR.getWeekdays();
		
		for (int i = 1; i < joursSemaineFR.length; i++) {
			BeanCalendrier bc = new BeanCalendrier();

			//System.out.println(joursSemaineFR[i]);
			bc.setJourDeLaSemaine(joursSemaineFR[i]);
			bc.setJourSemaineChiffre(String.valueOf(i));
			list.add(bc);
		}
		return list;
	}
    
	public static List<BeanCalendrier> listDateDeSemaine(String dateddMMyyyy) {
			
			LinkedList<BeanCalendrier> list = new LinkedList<BeanCalendrier>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE dd MMMM");
			GregorianCalendar gc = new GregorianCalendar();
			
			
			if(dateddMMyyyy == null){
				dateddMMyyyy = sdf.format(gc.getTime());
			}
			
			String[] strsplit = dateddMMyyyy.split("/");
			int jour = Integer.parseInt(strsplit[0]);
			int mois = Integer.parseInt(strsplit[1]);
			int annee = Integer.parseInt(strsplit[2]);
			
			gc.set(Calendar.DAY_OF_MONTH, jour);
			gc.set(Calendar.MONTH, mois-1);
			gc.set(Calendar.YEAR, annee);	
			
			for (int i = 1; i < 8; i++) {
				BeanCalendrier bc = new BeanCalendrier();
	
				bc.setJourMoisAnnee(sdf.format(gc.getTime()));
				bc.setJourEnLetMoisAnneeEnLet(sdf1.format(gc.getTime()));

				gc.add(Calendar.DAY_OF_MONTH, 1);

				list.add(bc);
			}
			return list;
	}
    
	
	public static String getMoisSuivant(int jour, int mois, int annee){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE dd MMMM");
		GregorianCalendar gc = new GregorianCalendar();
		
		if(jour==0 || mois==0 || annee==0){
			String dateddMMyyyy = sdf.format(gc.getTime());
			String[] strsplit = dateddMMyyyy.split("/");
			jour = Integer.parseInt(strsplit[0]);
			mois = Integer.parseInt(strsplit[1]);
			annee = Integer.parseInt(strsplit[2]);
		}
		
		gc.set(Calendar.DAY_OF_MONTH, jour);
		gc.set(Calendar.MONTH, mois);
		gc.set(Calendar.YEAR, annee);
	 	
		String moisSuiv = sdf.format(gc.getTime());
		
		return moisSuiv;
 	}

 	public static String getMoisPrecedent(int jour, int mois, int annee){
 		
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE dd MMMM");
		GregorianCalendar gc = new GregorianCalendar();
		
		if(jour==0 || mois==0 || annee==0){
			String dateddMMyyyy = sdf.format(gc.getTime());
			String[] strsplit = dateddMMyyyy.split("/");
			jour = Integer.parseInt(strsplit[0]);
			mois = Integer.parseInt(strsplit[1]);
			annee = Integer.parseInt(strsplit[2]);
		}
		
		gc.set(Calendar.DAY_OF_MONTH, jour);
		gc.set(Calendar.MONTH, mois-2);
		gc.set(Calendar.YEAR, annee);
	 	
		String moisPrec = sdf.format(gc.getTime());
		
		return moisPrec;
 	}
 	
 	public static String getMoisSuivant(String dateddMMyyyy){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE dd MMMM");
		GregorianCalendar gc = new GregorianCalendar();
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
			String[] strsplit = dateddMMyyyy.split("/");
			int jour = Integer.parseInt(strsplit[0]);
			int mois = Integer.parseInt(strsplit[1]);
			int annee = Integer.parseInt(strsplit[2]);
		
		
		gc.set(Calendar.DAY_OF_MONTH, jour);
		gc.set(Calendar.MONTH, mois);
		gc.set(Calendar.YEAR, annee);
	 	
		String moisSuiv = sdf.format(gc.getTime());
		
		return moisSuiv;
 	}

 	public static Date getMoisSuivantEnDate(String dateddMMyyyy){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE dd MMMM");
		GregorianCalendar gc = new GregorianCalendar();
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
			String[] strsplit = dateddMMyyyy.split("/");
			int jour = Integer.parseInt(strsplit[0]);
			int mois = Integer.parseInt(strsplit[1]);
			int annee = Integer.parseInt(strsplit[2]);
		
		
		gc.set(Calendar.DAY_OF_MONTH, jour);
		gc.set(Calendar.MONTH, mois);
		gc.set(Calendar.YEAR, annee);
	 	
		Date moisSuiv = gc.getTime();
		
		return moisSuiv;
 	}
 	public static String getMoisPrecedent(String dateddMMyyyy){
 		
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE dd MMMM");
		GregorianCalendar gc = new GregorianCalendar();
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
			String[] strsplit = dateddMMyyyy.split("/");
			int jour = Integer.parseInt(strsplit[0]);
			int mois = Integer.parseInt(strsplit[1]);
			int annee = Integer.parseInt(strsplit[2]);
		
		gc.set(Calendar.DAY_OF_MONTH, jour);
		gc.set(Calendar.MONTH, mois-2);
		gc.set(Calendar.YEAR, annee);
	 	
		String moisPrec = sdf.format(gc.getTime());
		
		return moisPrec;
 	}
 	
 	public static Date getMoisPrecedentEnDate(String dateddMMyyyy){
 		
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE dd MMMM");
		GregorianCalendar gc = new GregorianCalendar();
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
			String[] strsplit = dateddMMyyyy.split("/");
			int jour = Integer.parseInt(strsplit[0]);
			int mois = Integer.parseInt(strsplit[1]);
			int annee = Integer.parseInt(strsplit[2]);
		
		gc.set(Calendar.DAY_OF_MONTH, jour);
		gc.set(Calendar.MONTH, mois-2);
		gc.set(Calendar.YEAR, annee);
	 	
		Date moisPrec = gc.getTime();
		
		return moisPrec;
 	}
 	
 	public static int getIntJour(String dateddMMyyyy){
 		
		String[] strsplit = dateddMMyyyy.split("/");
		int jour = Integer.parseInt(strsplit[0]);
	 	
		return jour;
 	}
 	
 	public static int getIntMois(String dateddMMyyyy){
 		
		String[] strsplit = dateddMMyyyy.split("/");
		int mois = Integer.parseInt(strsplit[1]);
	 	
		return mois;
 	}
 	
 	public static int getIntAnnee(String dateddMMyyyy){
		
		String[] strsplit = dateddMMyyyy.split("/");
		int annee = Integer.parseInt(strsplit[2]);
	 	
		return annee;
 	}
 	
 	public static String getJourPrec(String dateddMMyyyy) throws ParseException{
 		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
		
		String[] strsplit = dateddMMyyyy.split("/");
		int jour = Integer.parseInt(strsplit[0])-1;
		int mois = Integer.parseInt(strsplit[1]);
		int annee = Integer.parseInt(strsplit[2]);
		
		gc.set(Calendar.DAY_OF_MONTH, jour);
		gc.set(Calendar.MONTH, mois-1);
		gc.set(Calendar.YEAR, annee);
	 	
		String jourPrec = sdf.format(gc.getTime());
		
		return jourPrec;
 	}
 	
 	public static String getDebutMois(String dateddMMyyyy) throws ParseException{
 		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
		
		String[] strsplit = dateddMMyyyy.split("/");
		int mois = Integer.parseInt(strsplit[1]);
		int annee = Integer.parseInt(strsplit[2]);
		
		gc.set(Calendar.DAY_OF_MONTH, 1);
		gc.set(Calendar.MONTH, mois-1);
		gc.set(Calendar.YEAR, annee);
	 	
		String debutMois = sdf.format(gc.getTime());
		
		return debutMois;
 	}
 	
 	public static String getDebutMoisPrec(String dateddMMyyyy) throws ParseException{
 		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
		
		String[] strsplit = dateddMMyyyy.split("/");
		int mois = Integer.parseInt(strsplit[1]);
		int annee = Integer.parseInt(strsplit[2]);
		
		gc.set(Calendar.DAY_OF_MONTH, 1);
		gc.set(Calendar.MONTH, mois-2);
		gc.set(Calendar.YEAR, annee);
	 	
		String debutMois = sdf.format(gc.getTime());
		
		return debutMois;
 	}
 	
 	public static String getDebutAnnee(String dateddMMyyyy) throws ParseException{
 		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
		
		String[] strsplit = dateddMMyyyy.split("/");
		int annee = Integer.parseInt(strsplit[2]);
		
		gc.set(Calendar.DAY_OF_MONTH, 1);
		gc.set(Calendar.MONTH, 0);
		gc.set(Calendar.YEAR, annee);
	 	
		String debutAnnee = sdf.format(gc.getTime());
		
		return debutAnnee;
 	}
 	
 	public static String getFinMois(String dateddMMyyyy) throws ParseException{
 		
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
		
		String[] strsplit = dateddMMyyyy.split("/");
		int mois = Integer.parseInt(strsplit[1]);
		int annee = Integer.parseInt(strsplit[2]);
		
		
		gc.set(Calendar.MONTH, mois-1);
		gc.set(Calendar.YEAR, annee);
		
		int longueurMois = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		gc.set(Calendar.DAY_OF_MONTH, longueurMois);
		gc.set(Calendar.MONTH, mois-1);
		gc.set(Calendar.YEAR, annee);

		
		String finMois = sdf.format(gc.getTime());
		
		return finMois;
 	}
    
 	public static String getFinMoisPrec(String dateddMMyyyy) throws ParseException{
 		
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
		
		String[] strsplit = dateddMMyyyy.split("/");
		int mois = Integer.parseInt(strsplit[1]);
		int annee = Integer.parseInt(strsplit[2]);
		
		int longueurMois = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		gc.set(Calendar.DAY_OF_MONTH, longueurMois);
		gc.set(Calendar.MONTH, mois-2);
		gc.set(Calendar.YEAR, annee);

		
		String finMois = sdf.format(gc.getTime());
		
		return finMois;
 	}
 	
 	public static String getFinAnnee(String dateddMMyyyy) throws ParseException{
 		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		
		
		if(dateddMMyyyy == null){
			dateddMMyyyy = sdf.format(gc.getTime());
		}
		
		String[] strsplit = dateddMMyyyy.split("/");
		int annee = Integer.parseInt(strsplit[2]);
		
		gc.set(Calendar.DAY_OF_MONTH, 31);
		gc.set(Calendar.MONTH, 11);
		gc.set(Calendar.YEAR, annee);
	 	
		String finAnnee = sdf.format(gc.getTime());
		
		return finAnnee;
 	}
 	
 	 public static BeanCalendrier getCalendier(String dateFormatddMMyyyy) {
 		
 		GregorianCalendar gc = new GregorianCalendar();
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
 		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
 		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
 		SimpleDateFormat sdf4 = new SimpleDateFormat("EEEE");
 		SimpleDateFormat sdf5 = new SimpleDateFormat("MMMM");
 		//SimpleDateFormat sdf6 = new SimpleDateFormat("ddMMyyyy");
 	
 		if(dateFormatddMMyyyy == null){
 			dateFormatddMMyyyy = sdf.format(gc.getTime());
 		}
 		
 			String[] strsplit = dateFormatddMMyyyy.split("/");
 			int jour = Integer.parseInt(strsplit[0]);
 			int mois = Integer.parseInt(strsplit[1]);
 			int annee = Integer.parseInt(strsplit[2]);
 			
 			BeanCalendrier bc = new BeanCalendrier();
 			gc.set(Calendar.DAY_OF_MONTH, jour);
 			gc.set(Calendar.MONTH, mois-1);
 			gc.set(Calendar.YEAR, annee);	
 			
 			bc.setJourEnChiffre(sdf1.format(gc.getTime()));
 			bc.setMoisEnChiffre(sdf2.format(gc.getTime()));
 			bc.setAnneeEnChiffre(sdf3.format(gc.getTime()));
 			bc.setJourEnLettre(sdf4.format(gc.getTime()));
 			bc.setMoisEnLettre(sdf5.format(gc.getTime()));
 			bc.setJourMoisAnnee(sdf.format(gc.getTime()));
 		
 		return bc;
 	}
 	 
 	 public static List<BeanCalendrier> getListMoisAnnee(String dateFormatddMMyyyy) throws ParseException {
		
 	 	
		LinkedList<BeanCalendrier> list = new LinkedList<BeanCalendrier>();
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf4 = new SimpleDateFormat("EEEE");
		SimpleDateFormat sdf5 = new SimpleDateFormat("MMMMM");
		//SimpleDateFormat sdf6 = new SimpleDateFormat("ddMMyyyy");
	
		if(dateFormatddMMyyyy == null){
			dateFormatddMMyyyy = sdf.format(gc.getTime());
		}
		
			String[] strsplit = dateFormatddMMyyyy.split("/");
			int jour = Integer.parseInt(strsplit[0]);
			int mois = Integer.parseInt(strsplit[1]);
			int annee = Integer.parseInt(strsplit[2]);
			
			gc.set(Calendar.DAY_OF_MONTH, jour);
			gc.set(Calendar.MONTH, mois-1);
			gc.set(Calendar.YEAR, annee);	
		
		for (int i = 1; i < 13; i++) {
			BeanCalendrier bc = new BeanCalendrier();
			gc.set(Calendar.DAY_OF_MONTH, jour);
			gc.set(Calendar.MONTH, i-1);
			gc.set(Calendar.YEAR, annee);
			
			bc.setJourEnChiffre(sdf1.format(gc.getTime()));
			bc.setMoisEnChiffre(sdf2.format(gc.getTime()));
			bc.setAnneeEnChiffre(sdf3.format(gc.getTime()));
			bc.setJourEnLettre(sdf4.format(gc.getTime()));
			bc.setMoisEnLettre(sdf5.format(gc.getTime()));
			//bc.setNbJourDuMois(longueurMois);
			bc.setJourMoisAnnee(sdf.format(gc.getTime()));
		
			list.add(bc);
		}
		return list;
	}
 	 
 	public static int getPremierJourDuMois(String dateFormatddMMyyyy)
	{
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 		GregorianCalendar gc = new GregorianCalendar();
 		
 		if(dateFormatddMMyyyy == null){
			dateFormatddMMyyyy = sdf.format(gc.getTime());
		}
 		String[] strsplit = dateFormatddMMyyyy.split("/");
		//int jour = Integer.parseInt(strsplit[0]);
		int mois = Integer.parseInt(strsplit[1]);
		int annee = Integer.parseInt(strsplit[2]);
		
		// Récupération du premier jour du mois dans DAY_OF_MONTH
		gc.set(Calendar.DAY_OF_MONTH, 1);
		gc.set(Calendar.MONTH, mois-1);
		gc.set(Calendar.YEAR, annee);	
		
 		
 		PremierJour = gc.get(Calendar.DAY_OF_WEEK);
 		// On réinitialise DAY_OF_MONTH au jour courant
 		gc.set(Calendar.DAY_OF_MONTH, JourActuel);
            
 		return PremierJour;
	}
}
