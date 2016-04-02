package model;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class EmailResponse {
	
	private static final String from = "garomaildemo@gmail.com";
	private static final String pass = "ittalent";
	private static final String host = "smtp.gmail.com";
	public static EmailResponse instance;
	
	private EmailResponse(){}
	
	public static EmailResponse getInstance(){
		if(instance == null)
			instance = new EmailResponse();
		return instance;
	}
	
	public boolean SendEmail(String toAdress, String subject, String text){
		

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");

	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(from, pass);
	            }
	         });

	      try {

	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from, "Team TalentHub"));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(toAdress));

	         // Set Subject: header field
	         message.setSubject(subject);

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<H4>"+text+"</H4><br><br>----------------------<br>The Talent Hub team<br><img src=\"cid:image\">";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource(
	            "src/main/webapp/static/img/logo-black.png");

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");

	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // put everything together
	         message.setContent(multipart);
	         // Send message
	         Transport.send(message);

	         return true;

	      } catch (MessagingException e) {
	    	  System.out.println("Error sending mail ");
	    	  e.printStackTrace();
	    	  return false;
	      } catch (UnsupportedEncodingException e) {
			System.out.println("Custom e-mail name error");
			e.printStackTrace();
			return false;
		}
	}
}
