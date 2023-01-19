package event.simplon.utils;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

	
public class Calendrier {
	static String [] TabMois = {"Janvier","Février","Mars","Avril","Mai","Juin", "Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
	public static String [] TabJour = {"Dimanche","Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi"};
 	public static String [] tabJourAbrege = {"Dim","Lun","Mar","Mer","Jeu","Ven","Sam"};
	
	public static List<BeanCalendrier> listCalendier() {
		
		LinkedList<BeanCalendrier> list = new LinkedList<BeanCalendrier>();
		
		int longueurMois = 0;
		Calendar cal = Calendar.getInstance();
		longueurMois = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		for (int i = 0; i < longueurMois; i++) {
			BeanCalendrier bs = new BeanCalendrier();
			cal.set(Calendar.DAY_OF_MONTH,i+1);
			//System.out.println(cal.getTime());
			//System.out.println(cal.get(Calendar.DAY_OF_WEEK)-1);
			bs.setJourEnChiffre(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
			bs.setJourDeLaSemaine(String.valueOf(cal.get(Calendar.DAY_OF_WEEK)));
			bs.setJourEnLettre(tabJourAbrege[cal.get(Calendar.DAY_OF_WEEK)-1]);
			list.add(bs);
		}
		return list;
	}
	
	public static List<BeanCalendrier> listJourSemaineCalendier(boolean b) {
		
		String [] tabJour = TabJour;
		if(b)
			tabJour = tabJourAbrege;
		
		LinkedList<BeanCalendrier> list = new LinkedList<BeanCalendrier>();
		
		int longueurSemaine = 0;
		Calendar cal = Calendar.getInstance();
		longueurSemaine = cal.getActualMaximum(Calendar.DAY_OF_WEEK);
	
		for (int i = 0; i < longueurSemaine; i++) {
			BeanCalendrier bs = new BeanCalendrier();
			cal.set(Calendar.DAY_OF_WEEK,i+1);
			bs.setJourDeLaSemaine(String.valueOf(cal.get(Calendar.DAY_OF_WEEK)));
			bs.setJourEnLettre(tabJour[cal.get(Calendar.DAY_OF_WEEK)-1]);
			list.add(bs);
			
			
		}
		return list;
	}
	
	
}
