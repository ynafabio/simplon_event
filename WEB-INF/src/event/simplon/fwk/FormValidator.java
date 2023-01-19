package event.simplon.fwk;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.validator.GenericValidator;


public class FormValidator {

	public final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public static final String _TEXT = "TEXT_";
	public static final String _ENTIER = "ENTIER_";
	public static final String _DOUBLE = "DOUBLE_";
	public static final String _ENTIER_NON_NULL = "NN_ENTIER_";
	public static final String _DOUBLE_NON_NULL = "NN_DOUBLE_";
	public static final String _DATE = "DATE_";
	public static final String _DATE_NN = "NN_DATE_";
	public static final String _MAIL = "MAIL_";
	public static final String ATT_ERREURS = "erreurs";
	public static final String ATT_MESSAGE_ERREURS = "messagerreurs";
	public static final String ATT_RESULTAT = "resultat";
	public static final int TAILLE_TAMPON = 10240; // 10 ko
	static String resultat;

	public static boolean validForm(HttpServletRequest request, Object bean, Map<String, String> champValidText) {
		Map<String, String> erreurs = new HashMap<String, String>();
		boolean b = true;
		erreurs = traiteForm(request, bean, champValidText);
		/* Initialisation du r�sultat global de la validation. */
		if (erreurs.isEmpty()) {
			resultat = "Succ�s de l'inscription.";
		} else {
			b = false;
			resultat = "�chec de l'inscription.";
		}
		request.setAttribute(ATT_ERREURS, erreurs);
		request.setAttribute(ATT_RESULTAT, resultat);
		return b;
	}

	private static Map<String, String> traiteForm(HttpServletRequest request, Object bean, Map<String, String> champValidText) {
		Map<String, String> erreurs = new HashMap<String, String>();
		Field[] fields = bean.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String nomChamp = fields[i].getName();
			String valeurChamp = getValeurChamp(request, nomChamp);
			if (champValidText.containsKey(nomChamp)) {
				try {
					String typeValue = champValidText.get(nomChamp);
					if (typeValue.startsWith(_TEXT)) {
						FormValidator.validationTexte(valeurChamp, typeValue.substring(_TEXT.length()));
					} else if (typeValue.startsWith(_ENTIER)) {
						FormValidator.validationEntier(valeurChamp, typeValue.substring(_ENTIER.length()));
					} else if (typeValue.startsWith(_DOUBLE)) {
						FormValidator.validationDouble(valeurChamp, typeValue.substring(_DOUBLE.length()));
					} else if (typeValue.startsWith(_ENTIER_NON_NULL)) {
						FormValidator.validationEntierNonNull(valeurChamp, typeValue.substring(_ENTIER_NON_NULL.length()));
					} else if (typeValue.startsWith(_DOUBLE_NON_NULL)) {
						FormValidator.validationDoubleNonNull(valeurChamp, typeValue.substring(_DOUBLE_NON_NULL.length()));
					} else if (typeValue.startsWith(_MAIL)) {
						FormValidator.validationEmail(valeurChamp);
					} else if (typeValue.startsWith(_DATE)) {
						FormValidator.validationDate(valeurChamp, typeValue.substring(_DATE.length()));
					} else if (typeValue.startsWith(_DATE_NN)) {
						FormValidator.validationDateNonNull(valeurChamp, typeValue.substring(_DATE_NN.length()));
					}
				} catch (Exception e) {
					erreurs.put(nomChamp, e.getMessage());
					if (!erreurs.isEmpty()) {
						request.setAttribute(ATT_MESSAGE_ERREURS, e.getMessage());
						return erreurs;
					}
				}
			}
		}
		return erreurs;
	}

	private static void validationEntier(String champ, String labelChamp) throws Exception {
		if (champ == null) {
			throw new Exception("Le champ \"" + labelChamp + "\" ne peut �tre null.");
		} else if (!GenericValidator.isInt(champ)) {
			throw new Exception("Le " + labelChamp + " doit �tre un entier.");
		}
	}

	private static void validationEntierNonNull(String champ, String labelChamp) throws Exception {
		if (!GenericValidator.isBlankOrNull(champ) && !GenericValidator.isInt(champ)) {
			throw new Exception("Le champ " + labelChamp + " doit �tre un entier.");
		}
	}
	
	private static void validationDouble(String champ, String labelChamp) throws Exception {
		if (champ == null) {
			throw new Exception("Le champ \"" + labelChamp + "\" ne peut �tre null.");
		} else if (!GenericValidator.isDouble(champ)) {
			throw new Exception("Le " + labelChamp + " doit �tre un d�cimal.");
		}
	}

	private static void validationDoubleNonNull(String champ, String labelChamp) throws Exception {
		if (!GenericValidator.isBlankOrNull(champ) && !GenericValidator.isDouble(champ)) {
			throw new Exception("Le champ " + labelChamp + " doit �tre un d�cimal.");
		}
	}

	public static Object getBean(HttpServletRequest request, Object bean) {
		Field[] fields = bean.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			String nomChamp = fields[i].getName();
			String valeurChamp = getValeurChamp(request, nomChamp);
			if (valeurChamp != null) // mise � jour du bean
				setMethodBean(bean, nomChamp, valeurChamp);
		}
		// }
		return bean;
	}

	private static void setMethodBean(Object bean, String nomChamp, String valeurChamp) {
		Method[] methodsBean = bean.getClass().getDeclaredMethods();
		Integer _integer = new Integer(0);
		Double _double = new Double(0);
		Date _date = new Date();
		for (int i = 0; i < methodsBean.length; i++) {
			// routine pour identifier la m�thode GET
			Method methodGET = methodsBean[i];
			if (methodGET.getName().equals(getNomMethode(nomChamp, "get"))) {
				// routine pour identifier la m�thode SET
				Method[] methodsBeanSet = bean.getClass().getDeclaredMethods();
				for (int j = 0; j < methodsBeanSet.length; j++) {
					Method methodSET = methodsBeanSet[j];
					if (methodSET.getName().equals(getNomMethode(nomChamp, "set"))) {
						try {
							// entier
							if (methodGET.getReturnType().isInstance(_integer)) {
								methodSET.invoke(bean,
										Integer.valueOf(valeurChamp));
							}// double
							else if (methodGET.getReturnType().isInstance(_double)) {
								methodSET.invoke(bean,
										Double.valueOf(valeurChamp));
							}// date
							else if (methodGET.getReturnType()
									.isInstance(_date)) {
								methodSET.invoke(bean, sdf.parse(valeurChamp));
							}// autre
							else {
								methodSET.invoke(bean, valeurChamp);
							}
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}

	}

	private static String getNomMethode(String nomChamp, String debut) {
		if (nomChamp != null && nomChamp.length() > 0) {
			char[] char_table = nomChamp.toCharArray();
			char_table[0] = Character.toUpperCase(char_table[0]);
			nomChamp = new String(char_table);
			nomChamp = debut + nomChamp;
		}
		return nomChamp;
	}

	/*
	 * M�thode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	public static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur;
		}
	}

	public static void validationDate(String date, String labelChamp) throws Exception {
		if (date == null) {
			throw new Exception("Le champ \"" + labelChamp + "\" doit �tre une date.");
		} else {
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				df.setLenient(false);
				df.parse(date);
			} catch (ParseException e) {
				throw new Exception("Le champ \"" + labelChamp + "\" doit �tre une date valide.");
			}
		}
	}

	public static void validationDateNonNull(String date, String labelChamp) throws Exception {
		if (date != null) {
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				df.setLenient(false);
				df.parse(date);
			} catch (ParseException e) {
				throw new Exception("Le champ \"" + labelChamp + "\" doit �tre une date valide.");
			}
		}
	}

	public static void validationEmail(String email) throws Exception {
		if (email != null && email.trim().length() != 0) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	/**
	 * Valide les mots de passe saisis.
	 */
	public static void validationPassWd(String motDePasse, String confirmation) throws Exception {
		if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
			if (!motDePasse.equals(confirmation)) {
				throw new Exception("Les mots de passe entr�s sont diff�rents, merci de les saisir � nouveau.");
			} else if (motDePasse.trim().length() < 3) {
				throw new Exception("Les mots de passe doivent contenir au moins 3 caract�res.");
			}
		} else {
			throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	/**
	 * Valide le nom d'utilisateur saisi.
	 */
	public static void validationTexte(String champ, String labelChamp) throws Exception {
		if (champ == null) {
			throw new Exception("Le champ \"" + labelChamp + "\" ne peut �tre vide.");
			// } else if (champ != null && champ.trim().length() < 3) {
			// throw new Exception("Le " + labelChamp +
			// " doit contenir au moins 3 caract�res.");
		}
	}

	/*
	 * M�thode utilitaire qui a pour but d'�crire le fichier pass� en param�tre
	 * sur le disque, dans le r�pertoire donn� et avec le nom donn�.
	 */
	public static String ecrireFichier(Part part, String nomFichier, String chemin) throws IOException {
		/* Pr�pare les flux. */
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		String nouveauNomFichier = transformeNomFichier(nomFichier);
		try {
			/* Ouvre les flux. */
			entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + "/" + nouveauNomFichier)), TAILLE_TAMPON);

			/*
			 * Lit le fichier re�u et �crit son contenu dans un fichier sur le
			 * disque.
			 */
			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				if (sortie != null)
					sortie.close();
			} catch (IOException ignore) {
			}
			try {
				if (entree != null)
					entree.close();
			} catch (IOException ignore) {
			}
		}
		return nouveauNomFichier;
	}

	private static String transformeNomFichier(String nomFichier) {
		String nouveauNomFichier = StringOperation.sansAccent(nomFichier);
		nouveauNomFichier = StringOperation.removeSpace(nomFichier);
		Random ra = new Random();
		GregorianCalendar gc = new GregorianCalendar();
		String dd = String.valueOf(gc.get(Calendar.DAY_OF_MONTH) + "." + gc.get(Calendar.MONTH) + "." + gc.get(Calendar.YEAR));
		nouveauNomFichier = dd + "_" + ra.nextInt(10) + "_" + nouveauNomFichier;
		return nouveauNomFichier;
	}

	/*
	 * M�thode utilitaire qui a pour unique but d'analyser l'en-t�te
	 * "content-disposition", et de v�rifier si le param�tre "filename" y est
	 * pr�sent. Si oui, alors le champ trait� est de type File et la m�thode
	 * retourne son nom, sinon il s'agit d'un champ de formulaire classique et
	 * la m�thode retourne null.
	 */
	public static String getNomFichier(Part part) {
		/* Boucle sur chacun des param�tres de l'en-t�te "content-disposition". */
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			/* Recherche de l'�ventuelle pr�sence du param�tre "filename". */
			if (contentDisposition.trim().startsWith("filename")) {
				/*
				 * Si "filename" est pr�sent, alors renvoi de sa valeur,
				 * c'est-�-dire du nom de fichier sans guillemets.
				 */
				String nomFichier = contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
				nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1).substring(nomFichier.lastIndexOf('\\') + 1);
				return nomFichier;
			}
		}
		/* Et pour terminer, si rien n'a �t� trouv�... */
		return null;
	}
	

}
