package br.com.egp.envy.service;

import br.com.egp.envy.entity.UserEntity;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    static String PATH_TEMPLATE_PASSWORD_CHANGE = "email/passwordChange";
    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(UserEntity userEntity, String newPass);

    void sendNewPasswordHtmlEmail(UserEntity userEntity, String newPass);

    void sendHtmlEmail(MimeMessage msg);
}
