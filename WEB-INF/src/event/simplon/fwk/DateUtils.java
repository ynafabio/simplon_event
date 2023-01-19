package event.simplon.fwk;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Administrateur <br>
 * 
 * D�finition : <br>
 */
public class DateUtils {

	/**
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getDate(int year, int month, int day) {
		return (new GregorianCalendar(year, (month - 1), day)).getTime();
	}

	/**
	 * @param dateField
	 * @return
	 */
	public static Date getDate(String dateField) {
		if (null != dateField) {
			String[] splittedDate = dateField.split("/");
			if (splittedDate.length == 3)
				return getDate(splittedDate[2], splittedDate[1],
						splittedDate[0]);
		}
		return null;
	}

	/**
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date getDate(String year, String month, String day) {
		if (null != year && 0 < year.length() && null != month
				&& 0 < month.length() && null != day && 0 < day.length()) {
			try {
				return getDate(Integer.parseInt(year), Integer.parseInt(month),
						Integer.parseInt(day));
			} catch (NumberFormatException nfe) {
				return null;
			}
		}
		return null;
	}

	/**
	 * @param date
	 * @return
	 */
	public static int getDay(java.util.Date date) {
		if (null != date) {
			String[] splittedDate = getFormattedDate(date).split("/");
			return Integer.parseInt(splittedDate[0]);
		}
		return 0;
	}

	/**
	 * @param date
	 * @return
	 */
	public static String getFormattedDate(Date date) {
		if (null != date) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			return formatter.format(date);
		}
		return null;
	}

	/**
	 * @param date
	 * @return
	 */
	public static String getFormattedDate(java.sql.Date date) {
		return getFormattedDate(sqlDateToUtilDate(date));
	}

	/**
	 * @param date
	 * @return
	 */
	public static int getMonth(java.util.Date date) {
		if (null != date) {
			String[] splittedDate = getFormattedDate(date).split("/");
			return Integer.parseInt(splittedDate[1]);
		}
		return 0;
	}

	/**
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static java.sql.Date getSqlDate(int year, int month, int day) {
		return utilDateToSqlDate(getDate(year, month, day));
	}

	/**
	 * @param dateField
	 * @return
	 */
	public static java.sql.Date getSqlDate(String dateField) {
		return utilDateToSqlDate(getDate(dateField));
	}

	/**
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static java.sql.Date getSqlDate(String year, String month, String day) {
		return utilDateToSqlDate(getDate(year, month, day));
	}

	/**
	 * @return
	 */
	public static java.sql.Date getSqlDateOfTheDay() {
		return new java.sql.Date((new GregorianCalendar()).getTime().getTime());
	}

	/**
	 * @param date
	 * @return
	 */
	public static int getYear(java.util.Date date) {
		if (null != date) {
			String[] splittedDate = getFormattedDate(date).split("/");
			return Integer.parseInt(splittedDate[2]);
		}
		return 0;
	}

	/**
	 * @param date
	 * @return
	 */
	public static java.util.Date sqlDateToUtilDate(java.sql.Date date) {
		return date != null ? new Date(date.getTime()) : null;
	}

	/**
	 * @param timestamp
	 * @return
	 */
	public static java.util.Date timestampToUtilDate(
			java.sql.Timestamp timestamp) {
		java.util.Date date = new Date();
		date.setTime(timestamp.getTime());
		return date;
	}

	/**
	 * @param date
	 * @return
	 */
	public static java.sql.Date utilDateToSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}
	
	/**
	 * @param date
	 * @return
	 */
	public static Timestamp utilDateToTimestamp(java.util.Date date) {
		Timestamp t = new Timestamp(date.getTime());
		return t;
	}

	public static Timestamp getTimeStamp(String dateField) {
		Date dd = new Date();
		if (null != dateField) {
			String str_dd = dateField.substring(0, 10);
			String str_hm = dateField.substring(11, dateField.length());
			String[] splittedDate = str_dd.split("/");
			String[] splittedHeure = str_hm.split(":");
			if (splittedDate.length == 3) {
				dd = getDate(splittedDate[2], splittedDate[1], splittedDate[0]);
			}

			GregorianCalendar gg = new GregorianCalendar();
			gg.setTime(dd);
			if (splittedHeure.length == 3) {
				gg.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splittedDate[0]));
				gg.set(Calendar.MINUTE, Integer.parseInt(splittedDate[1]));
				gg.set(Calendar.SECOND, Integer.parseInt(splittedDate[2]));
				
			}
			String temp = gg.get(Calendar.HOUR_OF_DAY)+" : "+gg.get(Calendar.MINUTE)+" : "+gg.get(Calendar.SECOND);
			Timestamp tt = new Timestamp(gg.getTimeInMillis());
			return tt;
		}

		return null;
	}

	public static String getFormattedTimestamp(Timestamp ts) {
		
		GregorianCalendar gg = new GregorianCalendar();
		gg.setTimeInMillis(ts.getTime());
		String strTs = "";
		if (null != gg) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			strTs = formatter.format(gg.getTime());
			//strTs += gg.get(Calendar.)
		}
		return strTs;
	}
	
	
	public static String getJourPrecedent(String datePosition){
 		
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		
			String[] strsplit = datePosition.split("/");
			int jour = Integer.parseInt(strsplit[0]);
			int mois = Integer.parseInt(strsplit[1]);
			int annee = Integer.parseInt(strsplit[2]);
		
		
		gc.set(Calendar.DAY_OF_MONTH, jour-1);
		gc.set(Calendar.MONTH, mois-1);
		gc.set(Calendar.YEAR, annee);
	 	
		String jourPrec = sdf.format(gc.getTime());
		
		return jourPrec;
 	}
	
	
	public static boolean compareDate(String datePosition, Date dateSysteme){
 		
 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		GregorianCalendar gc = new GregorianCalendar();
		
			String[] strsplit = datePosition.split("/");
			int jour = Integer.parseInt(strsplit[0]);
			int mois = Integer.parseInt(strsplit[1]);
			int annee = Integer.parseInt(strsplit[2]);
		
		gc.set(Calendar.DAY_OF_MONTH, jour);
		gc.set(Calendar.MONTH, mois-1);
		gc.set(Calendar.YEAR, annee);
	 	
		if(gc.getTime().after(dateSysteme))
			return true;
		else
			return false;
 	}

	
	
}