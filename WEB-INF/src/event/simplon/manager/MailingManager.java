package event.simplon.manager;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import event.simplon.fwk.ContextUtils;


public class MailingManager {

	private final static Logger logger = Logger.getLogger(MailingManager.class);
	private final static String MAILER_VERSION = "Java";

	public static boolean envoyerMailAuthentifier(String mailTo, String sujet, 
			String messageAenvoyer,	String nameSender) {
		boolean b = false;
		Message message = new MimeMessage(getSession());
		try {
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailTo));
			message.addFrom(new InternetAddress[]{new InternetAddress(
					ContextUtils.getMailSenderDefault(), nameSender)});
			logger.debug("SENDER " + ContextUtils.getMailSenderDefault());
			logger.debug("NAME SENDER " + ContextUtils.getNameSenderDefault());
			logger.debug("MAILTO " + mailTo);
			message.setSubject(sujet);
			message.setContent(messageAenvoyer, "text/html");
			
			Transport.send(message);
			b = true;
			logger.debug("MAIL BIEN ENVOYE");
		} catch (MessagingException mex) {
			// logger.error(e);
			logger.error("send failed, exception: " + mex);
			return b;
		} catch (Exception e) {
			logger.error(e);
			return b;
		}

		return b;
	}

	
	public static boolean sendMailAuthentifier(String mailTo,
			String sujet, StringBuilder messageAenvoyer, String nameSenderDefault) {
		boolean b = false;
		Message message = new MimeMessage(getSession());
		try {
			//message.addRecipient(RecipientType.TO, new InternetAddress(mailTo,nameTo));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailTo)); 
			message.addFrom(new InternetAddress[]{new InternetAddress(
					ContextUtils.getMailSenderDefault(), nameSenderDefault)});
			logger.debug("SENDER " + ContextUtils.getMailSenderDefault());
			logger.debug("NAME SENDER " + nameSenderDefault);
			logger.debug("MAILTO " + mailTo);
			message.setSubject(sujet);
			//message.setContent(messageAenvoyer, "text/html");

			message.setDataHandler(new DataHandler(new ByteArrayDataSource(messageAenvoyer.toString(), "text/html")));
			Transport.send(message);
			b = true;
			logger.debug("MAIL BIEN ENVOYE");
		} catch (MessagingException mex) {
			// logger.error(e);
			logger.error("send failed, exception: " + mex);
			return b;
		} catch (Exception e) {
			logger.error(e);
			return b;
		}

		return b;
	}
	
	public static boolean sendMailWithFileAttach(String mailTo,
			String sujet, String messageAenvoyer, String sourceMsg, String nameSenderDefault) {
		boolean b = false;
		Message message = new MimeMessage(getSession());
		try {
			//message.addRecipient(RecipientType.TO, new InternetAddress(mailTo,nameTo));
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(mailTo)); 
			message.addFrom(new InternetAddress[]{new InternetAddress(
					ContextUtils.getMailSenderDefault(), nameSenderDefault)});
			logger.debug("SENDER " + ContextUtils.getMailSenderDefault());
			logger.debug("NAME SENDER " + nameSenderDefault);
			logger.debug("MAILTO " + mailTo);
			message.setSubject(sujet);
			//message.setContent(messageAenvoyer, "text/html");
			
			 // Créer la partie contenu du premier message
		      MimeBodyPart mbp1 = new MimeBodyPart();
		      mbp1.setText(messageAenvoyer);
		 
		      // Créer la partie contenu du second message
		      // réservé au fichier joint
		      MimeBodyPart mbp2 = new MimeBodyPart();
		 
		      // Attacher le fichier à l'email
		      if(sourceMsg!=null) {
		      FileDataSource fichier_joint = new FileDataSource(sourceMsg);
		      	mbp2.setDataHandler(new DataHandler(fichier_joint));
		      	mbp2.setFileName(fichier_joint.getName());
		      }
		      // Créer un conteneur multipart pour les deux contenus
		      Multipart mp = new MimeMultipart();
		      mp.addBodyPart(mbp1);
		      
		      if(sourceMsg!=null) {
		    	  mp.addBodyPart(mbp2);
		      }
		 
		      // Ajouter le Multipart au message
		      message.setContent(mp);
			
			Transport.send(message);
			b = true;
			logger.debug("MAIL BIEN ENVOYE");
		} catch (MessagingException mex) {
			// logger.error(e);
			logger.error("send failed, exception: " + mex);
			return b;
		} catch (Exception e) {
			logger.error(e);
			return b;
		}

		return b;
	}
	
	private static Session getSession() {
		Authenticator authenticator = new Authenticator();

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator
				.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties
				.setProperty("mail.smtp.host", ContextUtils.getMailSmtpHost());
		properties
				.setProperty("mail.smtp.port", ContextUtils.getMailSmtpPort());

		return Session.getInstance(properties, authenticator);
	}

	private static class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator() {
			String username = ContextUtils.getMailSenderDefault();//
			// "web@koffi.net";
			String password = ContextUtils.getMailPassword();// "ckoffi";
			// String username = "contact@emc2.ci";
			// String password = "passWd15";
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
	
	public static String templateNotificationProforma() throws Exception {
		
		String mesmail = "";		
		mesmail = "Bonjour, "; 
		mesmail += "\r\n\r\n";	
			
		mesmail += "Notification d'edition d'une proforma par la cellule commerciale. \r\n\r\n";
		mesmail += "Cordialement.\r\n\r\n";
	
		return mesmail;
	}
	
	public static String templateValidationProforma() throws Exception {
		
		String mesmail = "";		
		mesmail = "Bonjour, "; 
		mesmail += "\r\n\r\n";	
			
		mesmail += "Notification de validation de la proforma par la DBFMG. \r\n\r\n";
		mesmail += "Cordialement.\r\n\r\n";
	
		return mesmail;
	}
	
	public static boolean contact(String mailTo, String nameTo,
			String sujet, String messageAenvoyer,
			String nomContact){
		boolean b = false;
		Message message = new MimeMessage(getSession());
		try {
			message.addRecipient(RecipientType.TO, new InternetAddress(mailTo,
					nameTo));
			message.addFrom(new InternetAddress[]{new InternetAddress(
					ContextUtils.getMailSenderDefault(), ContextUtils
							.getNameSenderDefault())});
			logger.debug("SENDER " + ContextUtils.getMailSenderDefault());
			logger.debug("NAME SENDER " + ContextUtils.getNameSenderDefault());
			logger.debug("MAILTO " + mailTo);
			message.setSubject(sujet);
			message.setContent(messageAenvoyer, "text/html");
			
			Transport.send(message);
			b = true;
			logger.debug("MAIL BIEN ENVOYE");
		} catch (MessagingException mex) {
			// logger.error(e);
			logger.error("send failed, exception: " + mex);
			return b;
		} catch (Exception e) {
			logger.error(e);
			return b;
		}
	  return b;
	}

}
