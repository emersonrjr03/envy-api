package br.com.egp.envy.service;

import br.com.egp.envy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.email.sender}")
    private String sender;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendNewPasswordEmail(User user, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(User user, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(user.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitacao de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(htmlFromTemplateNewPassword(user, newPass));
        return sm;
    }

    protected String htmlFromTemplateNewPassword(User user, String newPass) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("newPassword", newPass);
        return templateEngine.process(PATH_TEMPLATE_PASSWORD_CHANGE, context);
    }

    protected MimeMessage prepareMimeMessage(User user, String newPass) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(user.getEmail());
        helper.setFrom(sender);
        helper.setSubject("New password request");
        helper.setSentDate(new Date(System.currentTimeMillis()));

        mimeMessage.setContent(htmlFromTemplateNewPassword(user, newPass), "text/html");

        return mimeMessage;
    }

    public void sendNewPasswordHtmlEmail(User user, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(user, newPass);
        MimeMessage mm = null;
        try {
            mm = prepareMimeMessage(user, newPass);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            sendNewPasswordEmail(user, newPass);
        }
    }
}
