package sw.server;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import sw.server.models.Sheep;

/**
 * Sends email using google mails SMTP service
 * 
 * @author Lars Kristian
 * 
 */
public class Email {

	/**
	 * Sends an email alert
	 * 
	 * @param username The username of the recipient
	 * @param email The email of the recipient
	 * @param sheep The model for the sheep that triggered the email alert
	 */
	public static void send(String username, String email, Sheep sheep) {

		final String email1 = "sheepwatch@gmail.com";
		final String password = "IT1901passord";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session1 = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email1, password);
			}
		});

		try {
			Message message = new MimeMessage(session1);
			message.setFrom(new InternetAddress(email1));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Sheep Watch System Alarm");
			message.setText("Hello " + username + ",\nYour sheep " + sheep.getName() + " with id " + sheep.getId() + " has set of an alarm."
					+ "\nGo to http://SheepWatch.com for information(Does not work, maybe we send some info in this mail;) )");

			Transport.send(message);
		} catch (MessagingException e) {
			Logger.debug(e);
			throw new RuntimeException(e);
		}
	}
}