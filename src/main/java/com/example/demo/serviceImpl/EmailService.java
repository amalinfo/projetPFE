package com.example.demo.serviceImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender mailSender;
    public  void sendMail( String to,String subject, String content){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("savewaterwed321@gmail.com");
                message.setSubject(subject);
                message.setText(content);
                mailSender.send(message);
    }
    public  void sendMailwithtml( String to,String subject, String pwd) throws MessagingException {
      MimeMessage message= mailSender.createMimeMessage();
      MimeMessageHelper helper= new MimeMessageHelper(message);
          helper.setFrom("savewaterwed321@gmail.com");
        helper.setSubject(subject);
        helper.setText(pwd,true);
        helper.setTo(to);
        mailSender.send(message);
    }


}
