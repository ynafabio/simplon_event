package event.simplon.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet(name="IndexAction", urlPatterns = {"/index", "/public/index"})
public class IndexAction extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static Map<String, String> champValidText = new HashMap<String, String>();
	
	public static final String ATT_MAUVAIS_LOGIN = "bad_login";
	
	String _REPERTOIRE_JSP = "/WEB-INF/jsp/template/";
	String _REPERTOIRE_JSP_PUBLIC = "/WEB-INF/jsp/public/";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String p = request.getParameter("p");
		String strURI = request.getRequestURI();
		String[] ss = strURI.split("/");
		
		if (p == null) { // cas par default
			if (ss[2] != null && !ss[2].equals("index")) 
				jsp(_REPERTOIRE_JSP_PUBLIC, ss[2] , request, response);			
		} else {
			jsp(_REPERTOIRE_JSP, "index_" + p, request, response);
		}
	}

	

//	private void intiValid() {
//		champValidText.put("sujetQ", "Sujet");
//		champValidText.put("nomPrenomsQ", "Nom et prénoms");
//		champValidText.put("texteQ", "Texte");
//		champValidText.put("mailQ", "E-Mail");
//	}

	private void jsp(String repertoire, String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(repertoire + jsp + ".jsp").forward(request, response);
	}

	@SuppressWarnings("unused")
	private void redirect(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(getServletContext().getContextPath() + url);
	}
}
