package com.projects.email_sms;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class EmailSmsUtilClass {

    private static Properties emailSmsProperties = new Properties();

    private static final String HOST_GMAIL = "74.125.130.109";
    private static final int PORT_GMAIL_SSL = 465;
    private static final int PORT_GMAIL_TLS = 587;
    private static final String HOST_LIVE = "65.55.176.126";
    private static final int PORT_LIVE = 587;

    static {
        FileReader emailFileReader;
        try {
            emailFileReader = new FileReader("src/main/resources/email_sms/mail_sms.properties");
            emailSmsProperties.load(emailFileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Message getPreparedMessage(EmailSmsData emailSmsData, Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailSmsData.getMailFrom()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailSmsData.getMailTo()));
        message.setSubject(emailSmsData.getMailSubject());
        message.setText(emailSmsData.getMailBody());
        return message;
    }

    public static String getEmailSmsCredential(String property) {
        System.out.println(emailSmsProperties.getProperty(property));
        return emailSmsProperties.getProperty(property);
    }


    public static Properties getProperties(MailType mailType) {
        Properties properties = new Properties();
        switch (mailType) {
            case GMAIL_SSL:
                properties.put("mail.smtp.socketFactory.port", PORT_GMAIL_SSL);
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.port", PORT_GMAIL_SSL);

                properties.put("mail.smtp.host", HOST_GMAIL);
                properties.put("mail.smtp.auth", "true");
                break;
            case GMAIL_TLS:
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.port", PORT_GMAIL_TLS);

                properties.put("mail.smtp.host", HOST_GMAIL);
                properties.put("mail.smtp.auth", "true");
                break;
            case LIVE:
                properties.put("mail.smtp.host", HOST_LIVE);
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.port", PORT_LIVE);
                properties.put("mail.smtp.auth", "true");
                break;
        }
        return properties;
    }
}