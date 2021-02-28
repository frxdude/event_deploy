package com.golomt.event.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.golomt.event.DTO.UserDTO;
import com.golomt.event.Model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.golomt.event.Helper.QRCodeGenerator;
import com.golomt.event.Model.User;
//import com.golomt.properties.Mail;
import com.google.zxing.WriterException;

@Service
public class UserService {
	private JavaMailSender javaMailSender;
//	@Autowired
//	private Mail mailProperties;
	private static final String mailFrom = "event@golomtbank.com";
	QRCodeGenerator QRCG = new QRCodeGenerator();
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDateTime now = LocalDateTime.now();

	@Autowired
	public UserService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public void sendMail(UserDTO user, String imgurQr, Event event)
			throws MailException, MessagingException, WriterException, IOException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(mailFrom);
//		helper.setFrom(mailProperties.getMail());
		helper.setTo(user.getEmail());
		helper.setSubject("\""+event.getEventName() + "\" АРГА ХЭМЖЭЭНИЙ БҮРТГЭЛ БАТАЛГААЖУУЛАЛТ");
		helper.setText("<!DOCTYPE html>" 
				+ "<html lang=\"en\">" 
				+ "<head>" 
					+ "<meta charset=\"utf-8\">" 
				+ "</head>"
				+ "<body>" 
					+ "<div>\"" + event.getEventName() +"\" арга хэмжээний бүртгэл амжилттай хийгдлээ.</div>"
					+ "<div>Бид доорх мэдээлийг хүлээн авсныг баталгаажуулж байна.</div>"
					+ "<div>Арга хэмжээ: \"" + event.getEventName() + "\"</div>"
					+ "<div>Овог: " + user.getLastName() +"</div>"
					+ "<div>Нэр: " + user.getFirstName() +"</div>"
					+ "<div>Байгууллага: " + user.getCompanyName() + "</div>"
					+ "<div>Албан тушаал: " + user.getPosition() + "</div>"
					+ "<div>Утасны дугаар: " + user.getTelNumber() + "</div>" 
					+ "<div>Цахим хаяг: " + user.getEmail() + "</div>"
					+ "<div>Баталгаажуулах дугаар: " + user.getAccessNumber() + "</div>"
					+ "<div>Энэхүү QR кодыг арга хэмжээний өдөр нэвтрэхэд ашиглах тул, та хадгалж авна уу.</div>" 
					+ "<div><img src=\""+ imgurQr + "\"/>" + "</div>"
					+ "<div>Хаана: "	+ event.getEventWhere() + "</div>" 
					+ "<div>Хэзээ: " + event.getEventStartTime() + "</div>"
				+ "<div>Энэхүү имэйл нь Голомт банкнаас автоматаар илгээгдсэн учир хариу бичих шаардлагагүй.</div>"
				+ "</body>" + "</html>", true);
		javaMailSender.send(message);

//        mailSender.send(message);
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setFrom("tester4golomt@gmail.com");
//		mail.setTo(user.getEmail());
//		mail.setSubject("Testing email for registration");
//		mail.setText("test123");
//		javaMailSender.send(mail);
	}

	public void sendInfo(String email, File file)
			throws MailException, MessagingException, WriterException, IOException {

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(mailFrom);
		helper.setTo(email);
		helper.addAttachment("CSV", file);
		helper.setSubject("Тайлан");
		helper.setText("<!DOCTYPE html>" 
				+ "<html lang=\"en\">" 
				+ "<head>" 
					+ "<meta charset=\"utf-8\">" 
				+ "</head>"
				+ "<body>"
				+ "<div>testing </div>"
				+ "</body>" + "</html>", true);
		javaMailSender.send(message);
	}
	
	public void sendMailtoAll(User[] users) throws MailException {
		SimpleMailMessage mail = new SimpleMailMessage();
		String bcc = "";
		for (int i = 0; i < users.length; i++) {
			bcc += users[i].getEmail() + " ";
		}
		mail.setFrom(mailFrom);
		mail.setTo(mailFrom);
		mail.setBcc(bcc);
		mail.setSubject("Testing email for registration");
		mail.setText("<!DOCTYPE html>" + "<html lang=\"en\">" + "<head>" + "<meta charset=\"utf-8\">" + "</head>"
				+ "<body>" + "<div>Баярлалаа,</div>" + "Голомт банк " + dtf.format(now) + "</div></body>" + "</html>");
		javaMailSender.send(mail);
	}

}