package event.simplon.actions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import event.simplon.bean.Login;
import event.simplon.fwk.ContextUtils;
import event.simplon.fwk.FormValidator;

@WebServlet(urlPatterns = { "/login", "/admin/login" })
public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static Map<String, String> champValidText = new HashMap<String, String>();

	public static final String _SESSION_ADMIN = "sessionAdmin";
	public static final String _SESSION_EMPLOYE = "sessionEmploye";
	public static final String ATT_MAUVAIS_LOGIN = "bad_login";
	public static final String _DERNIERE_CONNEXION = "derniereConnexion";
	public static final String _MAIL_IDENTIFICATION = "mailDerniereConnexion";
	public static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 365; // 1 an
	public static final String FORMAT_DATE = "dd/MM/yyyy-HH:mm:ss";

	String _REPERTOIRE_JSP = "/WEB-INF/jsp/template/";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String p = request.getParameter("p");
		HttpSession session = request.getSession();
		if (p == null) { // cas par default
			if (session.getAttribute(_SESSION_ADMIN) != null) {
				jsp("tableaubord", request, response);
			} else {
				traiteDerniereConnexion(request, _DERNIERE_CONNEXION);
				traiteDerniereConnexion(request, _MAIL_IDENTIFICATION);
				jsp("login", request, response);
			}
		} else if (p.equals("deconnexion")){
			session.removeAttribute(_SESSION_ADMIN);
			session.invalidate();
			redirect("/login", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		intiValid();
		Login bean = new Login();
		if (FormValidator.validForm(request, bean, champValidText)) {
			bean = (Login) FormValidator.getBean(request, bean);
			HttpSession session = request.getSession();
			String[] splitProfil = null;
			splitProfil = ContextUtils.getProfilAdmin().split(":");
			
			if (splitProfil[0].equals(bean.getIdentifiant()) & splitProfil[1].equals(bean.getPassWd())) {
				// identification de l'admin
				bean.setProfil(splitProfil[2]);
				session.setAttribute(_SESSION_ADMIN, bean);
				creationCookie(request, response, bean.getIdentifiant());
				redirect("/admin/login", request, response);			
			} else {
				request.setAttribute(ATT_MAUVAIS_LOGIN, "Vous avez saisi de mauvais identifiants");
				jsp("login", request, response);
			}
		} else {
			request.setAttribute(ATT_MAUVAIS_LOGIN, "Vous avez saisi de mauvais identifiants");
			request.setAttribute("login", "login");
			jsp("login", request, response);
		}
	}

	private void traiteDerniereConnexion(HttpServletRequest request, String cookie) {
		/* Tentative de récupération du cookie depuis la requête */
		String derniereConnexion = getCookieValue(request, cookie);
		/* Si le cookie existe, alors calcul de la durée */
		if (derniereConnexion != null) {
			/* Ajout de l'intervalle en tant qu'attribut de la requête */
			request.setAttribute(cookie, derniereConnexion);
		}
	}
	
	

	private void creationCookie(HttpServletRequest request, HttpServletResponse response, String mail) {
		/* Création du cookie, et ajout à la réponse HTTP */
		Date dtCourante = new Date();
		/* Récupération de la date présente dans le cookie */
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		String derniereConnexion = sdf.format(dtCourante);
		setCookie(response, _DERNIERE_CONNEXION, derniereConnexion, COOKIE_MAX_AGE);
		setCookie(response, _MAIL_IDENTIFICATION, mail, COOKIE_MAX_AGE);
	}
	

	/**
	 * Méthode utilitaire gérant la récupération de la valeur d'un cookie donné
	 * depuis la requête HTTP.
	 */
	private static String getCookieValue(HttpServletRequest request, String nom) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie != null && nom.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/*
	 * Méthode utilitaire gérant la création d'un cookie et son ajout à la
	 * réponse HTTP.
	 */
	private static void setCookie(HttpServletResponse response, String nom, String valeur, int maxAge) {
		Cookie cookie = new Cookie(nom, valeur);
		cookie.setMaxAge(maxAge);
		 response.addCookie(cookie);
	}

	private void intiValid() {
		champValidText.put("mail", "E-Mail");
		champValidText.put("passWd", "Mot de passe");

	}

	private void jsp(String jsp, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(_REPERTOIRE_JSP + jsp + ".jsp").forward(request, response);
	}

	private void redirect(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(getServletContext().getContextPath() + url);
	}

}
