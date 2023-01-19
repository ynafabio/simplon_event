/*
 * Created on Jan 10, 2005
 *
 * Project : fideliplus
 * Package : fr.ivision.fideliplus.utils
 */
package event.simplon.fwk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class ContextUtils implements ServletContextListener {

	private static Properties confProps;
	private static ServletContext context;
	private static String realContextPath;

	private static String mailSmtpHost;
	private static String mailSenderDefault;
	private static String nameSenderDefault;
	private static String mailPassword;
	private static String mailSmtpPort;
	private static String listCivilite;
	private static String subjectMail;
	private static String messageMail;
	
	private static String defaultPathHttp;
	private static String defaultPathDirectory;
	private static String uploadPath;
	private static String uploadPathHttp;
	private static String imgDefault;
	private static String profilAdmin;
	private static String templateDirPath;
	private static String templateDirPathHttp;
	private static String templateDir;
	private static String planDirectory;
	
	private static String jourSemaine;
    private static String heureJour;
    private static String minuteJour;
    private static String heureFinAM;
    private static String heureDebutM;
    
	private static String typeCaisse;

	
	
	
	
	public static String getTypeCaisse() {
		return typeCaisse;
	}

	public static void setTypeCaisse(String typeCaisse) {
		ContextUtils.typeCaisse = typeCaisse;
	}

	public static Properties getConfProps() {
		return confProps;
	}

	public static ServletContext getContext() {
		return context;
	}

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		realContextPath = context.getRealPath("");
		confProps = System.getProperties();
		try {
			confProps.load(new FileInputStream(new File(realContextPath
					+ "/WEB-INF/classes/config.properties")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		defaultPathHttp = confProps.getProperty("default.path.http");
		defaultPathDirectory = confProps.getProperty("default.path.directory");
		imgDefault = confProps.getProperty("default.upload.img");
		profilAdmin = confProps.getProperty("profil.admin");
		typeCaisse = confProps.getProperty("type.caisse");
		
	}


	public static String getMailSmtpHost() {
		return mailSmtpHost;
	}

	public static void setMailSmtpHost(String mailSmtpHost) {
		ContextUtils.mailSmtpHost = mailSmtpHost;
	}

	public static String getMailSenderDefault() {
		return mailSenderDefault;
	}

	public static void setMailSenderDefault(String mailSenderDefault) {
		ContextUtils.mailSenderDefault = mailSenderDefault;
	}

	public static String getNameSenderDefault() {
		return nameSenderDefault;
	}

	public static void setNameSenderDefault(String nameSenderDefault) {
		ContextUtils.nameSenderDefault = nameSenderDefault;
	}

	public static String getMailPassword() {
		return mailPassword;
	}

	public static void setMailPassword(String mailPassword) {
		ContextUtils.mailPassword = mailPassword;
	}

	public static String getMailSmtpPort() {
		return mailSmtpPort;
	}

	public static void setMailSmtpPort(String mailSmtpPort) {
		ContextUtils.mailSmtpPort = mailSmtpPort;
	}

	public static String getListCivilite() {
		return listCivilite;
	}

	public static void setListCivilite(String listCivilite) {
		ContextUtils.listCivilite = listCivilite;
	}

	public static String getSubjectMail() {
		return subjectMail;
	}

	public static void setSubjectMail(String subjectMail) {
		ContextUtils.subjectMail = subjectMail;
	}

	public static String getMessageMail() {
		return messageMail;
	}

	public static void setMessageMail(String messageMail) {
		ContextUtils.messageMail = messageMail;
	}

	public static String getPlanDirectory() {
		return planDirectory;
	}

	public static void setPlanDirectory(String planDirectory) {
		ContextUtils.planDirectory = planDirectory;
	}

	public static String getDefaultPathDirectory() {
		return defaultPathDirectory;
	}

	public static void setDefaultPathDirectory(String defaultPathDirectory) {
		ContextUtils.defaultPathDirectory = defaultPathDirectory;
	}

	public static String getProfilAdmin() {
		return profilAdmin;
	}

	public static void setProfilAdmin(String profilAdmin) {
		ContextUtils.profilAdmin = profilAdmin;
	}

	
	public static String getRealContextPath() {
		return realContextPath;
	}

	public static void setRealContextPath(String realContextPath) {
		ContextUtils.realContextPath = realContextPath;
	}

	public static String getDefaultPathHttp() {
		return defaultPathHttp;
	}

	public static void setDefaultPathHttp(String defaultPathHttp) {
		ContextUtils.defaultPathHttp = defaultPathHttp;
	}

	public static String getUploadPath() {
		return uploadPath;
	}

	public static void setUploadPath(String uploadPath) {
		ContextUtils.uploadPath = uploadPath;
	}

	public static String getUploadPathHttp() {
		return uploadPathHttp;
	}

	public static void setUploadPathHttp(String uploadPathHttp) {
		ContextUtils.uploadPathHttp = uploadPathHttp;
	}

	public static String getImgDefault() {
		return imgDefault;
	}

	public static void setImgDefault(String imgDefault) {
		ContextUtils.imgDefault = imgDefault;
	}

	public static String getTemplateDirPath() {
		return templateDirPath;
	}

	public static void setTemplateDirPath(String templateDirPath) {
		ContextUtils.templateDirPath = templateDirPath;
	}

	public static String getTemplateDirPathHttp() {
		return templateDirPathHttp;
	}

	public static void setTemplateDirPathHttp(String templateDirPathHttp) {
		ContextUtils.templateDirPathHttp = templateDirPathHttp;
	}

	public static String getTemplateDir() {
		return templateDir;
	}

	public static void setTemplateDir(String templateDir) {
		ContextUtils.templateDir = templateDir;
	}

	

	public static void setConfProps(Properties confProps) {
		ContextUtils.confProps = confProps;
	}

	public static void setContext(ServletContext context) {
		ContextUtils.context = context;
	}

	public static String getJourSemaine() {
		return jourSemaine;
	}

	public static void setJourSemaine(String jourSemaine) {
		ContextUtils.jourSemaine = jourSemaine;
	}

	public static String getHeureJour() {
		return heureJour;
	}

	public static void setHeureJour(String heureJour) {
		ContextUtils.heureJour = heureJour;
	}

	public static String getMinuteJour() {
		return minuteJour;
	}

	public static void setMinuteJour(String minuteJour) {
		ContextUtils.minuteJour = minuteJour;
	}

	public static String getHeureFinAM() {
		return heureFinAM;
	}

	public static void setHeureFinAM(String heureFinAM) {
		ContextUtils.heureFinAM = heureFinAM;
	}

	public static String getHeureDebutM() {
		return heureDebutM;
	}

	public static void setHeureDebutM(String heureDebutM) {
		ContextUtils.heureDebutM = heureDebutM;
	}

	
	
}
