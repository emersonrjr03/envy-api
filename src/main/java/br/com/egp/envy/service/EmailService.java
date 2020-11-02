package br.com.egp.envy.service;

import br.com.egp.envy.entity.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    static String PATH_TEMPLATE_PASSWORD_CHANGE = "email/passwordChange";
    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(User user, String newPass);

    void sendNewPasswordHtmlEmail(User user, String newPass);

    void sendHtmlEmail(MimeMessage msg);
}
