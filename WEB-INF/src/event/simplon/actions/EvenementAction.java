package event.simplon.actions;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

import com.itextpdf.text.DocumentException;

import event.simplon.bean.Evenement;
import event.simplon.fwk.ContextUtils;
import event.simplon.fwk.FormValidator;
import event.simplon.manager.EvenementManager;

@WebServlet(name="EvenementAction", urlPatterns = {"/admin/event/*"})
public class EvenementAction extends HttpServlet{

	private static Logger logger = Logger.getLogger(EvenementAction.class);
	private static final long serialVersionUID = 1L;
	private static final String DIR_IMG = "img/";
	static Map<String, String> champValidText = new HashMap<String, String>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdf_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	
	String _JSP = null;
	String _REPERTOIRE_JSP = "/WEB-INF/jsp/";
	String _REP_ADMIN = "admin";
	String _REP_PUBLIC = "public/";
	String _REP_DOC = "doc_reporting/";
	boolean admin = false;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminPublic(request);
	
		String id = request.getParameter("id");
		String idParticipant = request.getParameter("idpart");
		String actPage = request.getParameter("action");
		
		List<Evenement> list = null;
		if(!GenericValidator.isBlankOrNull(actPage) && actPage.equals("edit")) {
			
			if(!GenericValidator.isBlankOrNull(id) && GenericValidator.isInt(id)) {
				list = EvenementManager.list(id, idParticipant, null, null);
				if(!GenericValidator.isBlankOrNull(idParticipant) && GenericValidator.isInt(idParticipant)) 
					request.setAttribute("bean", EvenementManager.getPartEvent(Integer.parseInt(idParticipant)));
				else
					request.setAttribute("bean", EvenementManager.get(Integer.parseInt(id)));
			}
			request.setAttribute("list", list);
			jsp("/editEvent", request, response);
		}else if(!GenericValidator.isBlankOrNull(actPage) && actPage.equals("list")) {
			if(request.getParameter("search")!=null) {
				try {
					Evenement critere = critereRechercheEvent(request, response);
					request.setAttribute("bean", critere);
					list = EvenementManager.
							list(critere.getIdEvenement()!=null?critere.getIdEvenement().toString():null,
							null,
							critere.getDate1()!=null?sdf.format(critere.getDate1()):null,  
							critere.getDate2()!=null?sdf.format(critere.getDate2()):null);
					request.setAttribute("list", list);
					
					new PdfAction().createListParticipantEvent(list,
							critere.getDate1()!=null?sdf.format(critere.getDate1()):null,  
							critere.getDate2()!=null?sdf.format(critere.getDate2()):null,
							ContextUtils.getDefaultPathDirectory()+ _REP_DOC + "Etat_Liste_Participants.pdf");
					request.setAttribute("urlEtatListeParticipant", getUrlHttp(request) + _REP_DOC + "Etat_Liste_Participants.pdf");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			list = EvenementManager.listEvent();
			request.setAttribute("listEvent", list);
			jsp("/listEvent", request, response);
		}else if(!GenericValidator.isBlankOrNull(actPage) && actPage.equals("del")) {
			if(!GenericValidator.isBlankOrNull(id) && GenericValidator.isInt(id)) 
				EvenementManager.delete(Integer.parseInt(id));
			
			list = EvenementManager.list(null, null, null, null);
			request.setAttribute("list", list);
			jsp("/editEvent", request, response);
		}
		
		
	}

	private String getUrlHttp(HttpServletRequest request){
		StringBuffer surl = request.getRequestURL();
		int pos = surl.lastIndexOf(request.getServletPath());
	    String redirectURL = surl.substring(0, pos + 1);
	    return redirectURL;
	}

	private void adminPublic(HttpServletRequest request) {
		_JSP = "";
		String strURI = request.getRequestURI();
		String[] splitStr = strURI.split("/");
		if (splitStr.length >= 3) {
			if (splitStr[2].equals("admin")) {
				_JSP = _REPERTOIRE_JSP + _REP_ADMIN;
				admin = true;
			} else {
				_JSP = _REPERTOIRE_JSP + _REP_PUBLIC;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		adminPublic(request);
		
		if (admin) { // valid form seulement si admin
		//	intiValid();
			Evenement bean = new Evenement();
			if (FormValidator.validForm(request, bean, champValidText)) {
				bean = (Evenement) FormValidator.getBean(request, bean);

				int id=0;	
				if(request.getParameter("search_event")!=null) {
					
					String url = getUrlHttpSearchEvent(request);
					
					redirect(admin, "/event?action=list&search=" + url, request, response);
				}else {
					if (bean.getIdEvenement() == null) {
						id = EvenementManager.insert(bean);
						if(id>0) {
							bean.setIdEvenement(id);
							EvenementManager.insertParticipant(bean);
						}
					}else {
						id = EvenementManager.update(bean);
						if(id>0)
							EvenementManager.updateParticipant(bean);
					}
					
					redirect(admin, "/event?action=edit&id=" + id, request, response);
				}
			} else {
				
				jsp("/editEvent", request, response);
			}
		} else
			redirect(admin, "/event", request, response);

	}
	
	private String getUrlHttpSearchEvent(HttpServletRequest request){
		String url = "ok";
		
		if(!GenericValidator.isBlankOrNull(request.getParameter("date1")))
			url = url + "&d1="+request.getParameter("date1");
		if(!GenericValidator.isBlankOrNull(request.getParameter("date2")))
			url = url + "&d2="+request.getParameter("date2");		
		if(!GenericValidator.isBlankOrNull(request.getParameter("idEvenement")))
			url = url + "&id="+request.getParameter("idEvenement");

	    return url;
	}

	private static Evenement critereRechercheEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		Evenement bean = new Evenement();
		
		if(!GenericValidator.isBlankOrNull(request.getParameter("id")))
			bean.setIdEvenement(Integer.parseInt(request.getParameter("id")));
		else
			bean.setIdEvenement(null);
		if(!GenericValidator.isBlankOrNull(request.getParameter("d1")))
			bean.setDate1(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("d1")));
		else
			bean.setDate1(null);
		if(!GenericValidator.isBlankOrNull(request.getParameter("d2")))
			bean.setDate2(new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("d2")));
		else
			bean.setDate2(null);
		
		
		return bean;	
	}
	
//	private void intiValid() {
//		champValidText.put("titreArticle", "TEXT_Sujet");
//		champValidText.put("contenuArticle", "TEXT_Contenu");
//		champValidText.put("dateArticle", "DATE_Date");
//	}

	private void jsp(String jsp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(_JSP + jsp + ".jsp").forward(request, response);
	}

	private void redirect(boolean admin, String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (admin)
			response.sendRedirect(getServletContext().getContextPath() + "/admin" + url);
		else
			response.sendRedirect(getServletContext().getContextPath() + url);

	}
}
