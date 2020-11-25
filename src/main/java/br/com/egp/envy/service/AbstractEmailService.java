package br.com.egp.envy.service;

import br.com.egp.envy.entity.UserEntity;
import br.com.egp.envy.mapper.UserMapper;
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
    public void sendNewPasswordEmail(UserEntity userEntity, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(userEntity, newPass);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(UserEntity userEntity, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(userEntity.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitacao de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(htmlFromTemplateNewPassword(userEntity, newPass));
        return sm;
    }

    protected String htmlFromTemplateNewPassword(UserEntity userEntity, String newPass) {
        Context context = new Context();
        context.setVariable("user", UserMapper.unmarshall(userEntity));
        context.setVariable("newPassword", newPass);
        return templateEngine.process(PATH_TEMPLATE_PASSWORD_CHANGE, context);
    }

    protected MimeMessage prepareMimeMessage(UserEntity userEntity, String newPass) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(userEntity.getEmail());
        helper.setFrom(sender);
        helper.setSubject("New password request");
        helper.setSentDate(new Date(System.currentTimeMillis()));

        mimeMessage.setContent(htmlFromTemplateNewPassword(userEntity, newPass), "text/html");

        return mimeMessage;
    }

    public void sendNewPasswordHtmlEmail(UserEntity userEntity, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(userEntity, newPass);
        MimeMessage mm = null;
        try {
            mm = prepareMimeMessage(userEntity, newPass);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            sendNewPasswordEmail(userEntity, newPass);
        }
    }
}
