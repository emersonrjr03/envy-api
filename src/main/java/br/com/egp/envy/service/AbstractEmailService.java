package br.com.egp.envy.service;

import br.com.egp.envy.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.email.sender}")
    private String sender;

    @Override
    public void sendNewPasswordEmail(User user, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(User user, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(user.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitacao de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
}
