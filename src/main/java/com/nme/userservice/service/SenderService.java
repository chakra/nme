package com.nme.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by chakrabhandari on 3/01/2017.
 */
@Service
public class SenderService {

    @Autowired
    JavaMailSender mailSender;

    //@PostConstruct
    public void send(String sendTo, String url) {
        MimeMessage mail = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(sendTo);
            //helper.setReplyTo("someone@localhost");
            helper.setFrom("emailsendertester24@gmail.com");
            helper.setSubject("Lorem ipsum");
            helper.setText("Lorem ipsum dolor sit amet [...] <a href=\""+url+"\">Click here</a>");
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {

        }

        mailSender.send(mail);

    }

}
