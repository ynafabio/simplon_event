package event.simplon.fwk;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import event.simplon.actions.LoginAction;


@WebFilter("/*")
public class AdminUserFilter implements Filter {
	private static Logger logger = Logger.getLogger(AdminUserFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		/* Cast des objets request et response */
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse respo = (HttpServletResponse) response;
		
		/* Non-filtrage des ressources statiques */
		String chemin = req.getRequestURI().substring(req.getContextPath().length());
		if (chemin.startsWith("/img/") || chemin.startsWith("/plugins/") || chemin.startsWith("/dist/" )
			|| chemin.startsWith("/login") || chemin.startsWith("/public/")) {
			if (chemin.startsWith("/public/") || chemin.startsWith("/login"))
				req.setAttribute("sessionPublic", "public");
			
			chain.doFilter(request, response);
			//return;
		} else {
			/* Récupération de la session depuis la requête */
			HttpSession session = req.getSession();
			if (session.getAttribute(LoginAction._SESSION_ADMIN) == null) {
				/* Redirection vers la page publique */
				logger.debug("## CHEMIN - " + chemin);
				redirect("/login", req, respo);
			} else {
				/* Affichage de la page restreinte */
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}

	private void redirect(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getServletContext().getContextPath() + url);
	}
}
