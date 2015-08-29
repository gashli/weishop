package com.gashli.wshop.utils;


import com.gashli.wshop.entity.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.gashli.wshop.service.IConfigService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class MailSenderUtils {
    public static boolean sendMail(String email, String content, String title, HttpServletRequest request) {
        ServletContext context = request.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);

        IConfigService configService = (IConfigService) ctx.getBean("configService");
        Config config = (Config) configService.findById(Config.class, 1);
        final String userName = config.getSendEmail();
        final String passWord = config.getSendEmailPass();
        String smtp = config.getSendEmailSmtp();

        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", smtp);
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, passWord);
            }
        });
        Message msg = new MimeMessage(session);
        session.setDebug(false);
        try {
            msg.setFrom(new InternetAddress(userName));
            msg.setSubject(title);
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setContent(content, "text/html;charset=utf-8");
            Transport.send(msg);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }
}
