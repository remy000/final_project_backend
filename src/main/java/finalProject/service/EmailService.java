package finalProject.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    @Autowired

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendingEmails(String rec,String subject,String text){
        try {
            MimeMessage message=mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom("nyrealestate001@gmail.com");
            helper.setTo(rec);
            helper.setSubject(subject);
            helper.setText(text);
            mailSender.send(message);
        }
        catch (Exception ex){
         ex.printStackTrace();
        }

    }
}
