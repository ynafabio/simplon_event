/*
 * Created on Feb 21, 2005
 *
 * Project : fideliplusFO
 * Package : fr.ivision.fideliplus.utils
 */
package event.simplon.fwk;

import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.apache.log4j.Logger;



/**
 * Classe complementaire du J2SDK sur la manipulation de chaines de caract�res
 * Permet nottament de supprimer les accents d'une chaine de caract�res
 */
public abstract class StringOperation {

	private final static Logger logger = Logger
			.getLogger(StringOperation.class);

	/** Index du 1er caractere accentu� * */
	private static final int MIN = 192;

	/** Index du dernier caractere accentu� * */
	private static final int MAX = 255;

	/** Vecteur de correspondance entre accent / sans accent * */
	private static final Vector map = initMap();


	public static List<BeanListSelect> getListSelect(String liste) {
		List<BeanListSelect> list = new LinkedList<BeanListSelect>();
		String[] _listStr = liste.split(":");
		for (int i = 0; i < _listStr.length; i += 2) {
			BeanListSelect bs = new BeanListSelect();
			bs.setId(_listStr[i]);
			bs.setLib(_listStr[i + 1]);
			list.add(bs);
		}
		return list;
	}

	public static List<BeanListSelect> getListSelect(int debut, int fin) {
		List<BeanListSelect> list = new LinkedList<BeanListSelect>();
		if (debut > fin) {
			for (int i = debut; i > fin; i--) {
				BeanListSelect bs = new BeanListSelect();
				bs.setId(new Integer(i).toString());
				bs.setLib(new Integer(i).toString());
				list.add(bs);
			}
		} else {
			for (int i = debut; i < fin; i++) {
				BeanListSelect bs = new BeanListSelect();
				bs.setId(new Integer(i).toString());
				bs.setLib(new Integer(i).toString());
				list.add(bs);
			}
		}
		return list;
	}
	
//	public static List<BeanListSelect> getListSelect(int debut, int fin, int niveau) {
//		List<BeanListSelect> list = new LinkedList<BeanListSelect>();
//		if (debut > fin) {
//			for (int i = debut; i > fin; niveau--) {
//				BeanListSelect bs = new BeanListSelect();
//				bs.setId(new Integer(i).toString());
//				bs.setLib(new Integer(i).toString());
//				list.add(bs);
//			}
//		} else {
//			for (int i = debut; i < fin; niveau++) {
//				BeanListSelect bs = new BeanListSelect();
//				bs.setId(new Integer(i).toString());
//				bs.setLib(new Integer(i).toString());
//				list.add(bs);
//			}
//		}
//		return list;
//	}

	public static String setValuesForm(String[] strMulti) {

		String valueForm = "";
		for (int i = 0; i < strMulti.length; i++) {
			valueForm += strMulti[i] + "_:_";

		}
		return valueForm;
	}

	public static String removeChar(String s, char c) {
		String r = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != c)
				r += s.charAt(i);
			else
				r += " ";
		}
		return r;
	}

	public static String removeSpace(String s) {
		String r = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != " ".toCharArray()[0])
				r += s.charAt(i);
		}
		return r;
	}

	/**
	 * Initialisation du tableau de correspondance entre les caract�res
	 * accentu�s et leur homologues non accentu�s
	 */
	private static Vector initMap() {
		Vector Result = new Vector();
		String car = null;

		car = new String("A");
		Result.add(car); /* '\u00C0' � alt-0192 */
		Result.add(car); /* '\u00C1' � alt-0193 */
		Result.add(car); /* '\u00C2' � alt-0194 */
		Result.add(car); /* '\u00C3' � alt-0195 */
		Result.add(car); /* '\u00C4' � alt-0196 */
		Result.add(car); /* '\u00C5' � alt-0197 */
		car = new String("AE");
		Result.add(car); /* '\u00C6' � alt-0198 */
		car = new String("C");
		Result.add(car); /* '\u00C7' � alt-0199 */
		car = new String("E");
		Result.add(car); /* '\u00C8' � alt-0200 */
		Result.add(car); /* '\u00C9' � alt-0201 */
		Result.add(car); /* '\u00CA' � alt-0202 */
		Result.add(car); /* '\u00CB' � alt-0203 */
		car = new String("I");
		Result.add(car); /* '\u00CC' � alt-0204 */
		Result.add(car); /* '\u00CD' � alt-0205 */
		Result.add(car); /* '\u00CE' � alt-0206 */
		Result.add(car); /* '\u00CF' � alt-0207 */
		car = new String("D");
		Result.add(car); /* '\u00D0' � alt-0208 */
		car = new String("N");
		Result.add(car); /* '\u00D1' � alt-0209 */
		car = new String("O");
		Result.add(car); /* '\u00D2' � alt-0210 */
		Result.add(car); /* '\u00D3' � alt-0211 */
		Result.add(car); /* '\u00D4' � alt-0212 */
		Result.add(car); /* '\u00D5' � alt-0213 */
		Result.add(car); /* '\u00D6' � alt-0214 */
		car = new String("*");
		Result.add(car); /* '\u00D7' � alt-0215 */
		car = new String("0");
		Result.add(car); /* '\u00D8' � alt-0216 */
		car = new String("U");
		Result.add(car); /* '\u00D9' � alt-0217 */
		Result.add(car); /* '\u00DA' � alt-0218 */
		Result.add(car); /* '\u00DB' � alt-0219 */
		Result.add(car); /* '\u00DC' � alt-0220 */
		car = new String("Y");
		Result.add(car); /* '\u00DD' � alt-0221 */
		car = new String("�");
		Result.add(car); /* '\u00DE' � alt-0222 */
		car = new String("B");
		Result.add(car); /* '\u00DF' � alt-0223 */
		car = new String("a");
		Result.add(car); /* '\u00E0' � alt-0224 */
		Result.add(car); /* '\u00E1' � alt-0225 */
		Result.add(car); /* '\u00E2' � alt-0226 */
		Result.add(car); /* '\u00E3' � alt-0227 */
		Result.add(car); /* '\u00E4' � alt-0228 */
		Result.add(car); /* '\u00E5' � alt-0229 */
		car = new String("ae");
		Result.add(car); /* '\u00E6' � alt-0230 */
		car = new String("c");
		Result.add(car); /* '\u00E7' � alt-0231 */
		car = new String("e");
		Result.add(car); /* '\u00E8' � alt-0232 */
		Result.add(car); /* '\u00E9' � alt-0233 */
		Result.add(car); /* '\u00EA' � alt-0234 */
		Result.add(car); /* '\u00EB' � alt-0235 */
		car = new String("i");
		Result.add(car); /* '\u00EC' � alt-0236 */
		Result.add(car); /* '\u00ED' � alt-0237 */
		Result.add(car); /* '\u00EE' � alt-0238 */
		Result.add(car); /* '\u00EF' � alt-0239 */
		car = new String("d");
		Result.add(car); /* '\u00F0' � alt-0240 */
		car = new String("n");
		Result.add(car); /* '\u00F1' � alt-0241 */
		car = new String("o");
		Result.add(car); /* '\u00F2' � alt-0242 */
		Result.add(car); /* '\u00F3' � alt-0243 */
		Result.add(car); /* '\u00F4' � alt-0244 */
		Result.add(car); /* '\u00F5' � alt-0245 */
		Result.add(car); /* '\u00F6' � alt-0246 */
		car = new String("/");
		Result.add(car); /* '\u00F7' � alt-0247 */
		car = new String("0");
		Result.add(car); /* '\u00F8' � alt-0248 */
		car = new String("u");
		Result.add(car); /* '\u00F9' � alt-0249 */
		Result.add(car); /* '\u00FA' � alt-0250 */
		Result.add(car); /* '\u00FB' � alt-0251 */
		Result.add(car); /* '\u00FC' � alt-0252 */
		car = new String("y");
		Result.add(car); /* '\u00FD' � alt-0253 */
		car = new String("�");
		Result.add(car); /* '\u00FE' � alt-0254 */
		car = new String("y");
		Result.add(car); /* '\u00FF' � alt-0255 */
		Result.add(car); /* '\u00FF' alt-0255 */

		return Result;
	}

	/**
	 * Transforme une chaine pouvant contenir des accents dans une version sans
	 * accent
	 * 
	 * @param chaine
	 *            Chaine a convertir sans accent
	 * @return Chaine dont les accents ont �t� supprim�
	 */
	public static String sansAccent(String chaine) {
		StringBuffer Result = new StringBuffer(chaine);

		for (int index = 0; index < Result.length(); index++) {
			int carVal = chaine.charAt(index);
			if (carVal >= MIN && carVal <= MAX) { // Remplacement
				String newVal = (String) map.get(carVal - MIN);
				Result.replace(index, index + 1, newVal);
			}
		}
		return Result.toString();
	}

	public static String getLetterRandom() {
		Random rd = new Random();
		logger.debug("LETTER : " + String.valueOf(rd.nextInt()).substring(0, 1));
		return String.valueOf(rd.nextInt()).substring(0, 1);
	}

	/**
	 * @param main
	 * @param listeTagValeur
	 * @return
	 */
	public static String substitution(String main, Hashtable listeTagValeur) {

		CharArrayReader rd = new CharArrayReader(main.toCharArray());
		BufferedReader br = new BufferedReader(rd);

		LinkedList listeTag = new LinkedList();
		LinkedList listeOffset = new LinkedList();
		int etat = 0;
		char c;
		int ci;
		StringBuffer chaineCourante = new StringBuffer();
		int positionCourante = -1;
		int markup = 0;
		StringBuffer sbResultat = new StringBuffer();
		try {
			while ((ci = br.read()) != -1) {
				c = (char) ci;
				positionCourante++;
				switch (etat) {
				case 0:
					if (c == '[') {
						etat = 1;
						markup = positionCourante;
					}
					break;
				case 1:
					if (c == '$') {
						etat = 2;
						chaineCourante = new StringBuffer();
					} else
						etat = 0;
					break;
				case 2:
					if (c == ']') {
						etat = 0;
						listeTag.add(chaineCourante.toString());
						listeOffset.add(new Integer(markup));
					} else
						chaineCourante.append(c);
					break;
				default:
					throw new Exception("Erreur inattendue");
				}
			}
			br.close();

			if (etat != 0)
				throw new Exception("chaine html invalide");

			int decalage = 0;
			sbResultat = new StringBuffer(main);

			for (int i = 0; i < listeTag.size(); i++) {
				String tag = (String) listeTag.get(i);
				String valeur = (String) listeTagValeur.get(tag);
				if (valeur != null) {
					int longueurTag = tag.length();
					int offset = ((Integer) listeOffset.get(i)).intValue();
					sbResultat.replace(offset + decalage, offset + longueurTag
							+ 3 + decalage, valeur);
					decalage += valeur.length() - longueurTag - 3;
				}
			}
		} catch (Exception e) {
			logger.debug(e);
		}

		return sbResultat.toString();
	}

	public static String utf8ToLatin1(String text)
			throws CharacterCodingException {
		CharsetEncoder iso8859Encoder = Charset.forName("iso-8859-1")
				.newEncoder();
		CharsetEncoder utf8Encoder = Charset.forName("UTF-8").newEncoder();
		CharsetEncoder w1252 = Charset.forName("windows-1252").newEncoder();
		String utf8S = new String(utf8Encoder.encode(
				CharBuffer.wrap(text.toCharArray())).array());
		String w1252S = new String(w1252.encode(
				CharBuffer.wrap(text.toCharArray())).array());
		// System.out.println("### UTF8 " + utf8S);
		String latin1Str = text;
		// if (iso8859Encoder.canEncode(utf8S)) {
		// latin1Str = new
		// String(iso8859Encoder.encode(CharBuffer.wrap(utf8S.toCharArray())).array());
		// } else if (iso8859Encoder.canEncode(w1252S))
		latin1Str = new String(iso8859Encoder.encode(
				CharBuffer.wrap(w1252S.toCharArray())).array());
		// latin1Str = new String(iso8859Encoder.encode(
		// CharBuffer.wrap(text.toCharArray())).array());
		// System.out.println("### LATIN1 " + latin1Str);
		return latin1Str;
	}

	public static String formatAccentHtml(String id) {
		String ret = id;
		ret = ret.replaceAll("%C3%B4", "�");
		ret = ret.replaceAll("%C3%A9", "�");
		ret = ret.replaceAll("%C3%89", "�");
		ret = ret.replaceAll("%C3%A8", "�");
		ret = ret.replaceAll("%C3%88", "�");
		ret = ret.replaceAll("%C3%A7", "�");
		ret = ret.replaceAll("%C3%87", "�");
		ret = ret.replaceAll("%C3%A0", "�");
		ret = ret.replaceAll("%C3%80", "�");
		ret = ret.replaceAll("%C3%B9", "�");
		ret = ret.replaceAll("%C3%99", "�");
		ret = ret.replaceAll("%C3%AA", "�");
		ret = ret.replaceAll("%C3%8A", "�");
		ret = ret.replaceAll("%C3%BB", "�");
		ret = ret.replaceAll("%C3%9B", "�");
		ret = ret.replaceAll("%C3%AB", "�");
		ret = ret.replaceAll("%C3%8B", "�");
		ret = ret.replaceAll("%C3%BC", "�");
		ret = ret.replaceAll("%C3%9C", "�");

		return ret;
	}
}
