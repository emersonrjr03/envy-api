package br.com.egp.envy.service;

import br.com.egp.envy.entity.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(User user, String newPass);
}
