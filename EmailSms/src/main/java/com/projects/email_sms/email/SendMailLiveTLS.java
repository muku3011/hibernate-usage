package com.projects.email_sms.email;

import com.projects.email_sms.EmailSmsData;
import com.projects.email_sms.EmailSmsUtilClass;
import com.projects.email_sms.MailType;

import javax.mail.*;

public class SendMailLiveTLS {

    public static void main(String[] args) {

        Session session = Session.getInstance(EmailSmsUtilClass.getProperties(MailType.LIVE), new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailSmsUtilClass.getEmailSmsCredential("mail.username"), EmailSmsUtilClass.getEmailSmsCredential("mail.password"));
            }
        });

        EmailSmsData emailSmsData = new EmailSmsData();
        emailSmsData.setMailFrom(EmailSmsUtilClass.getEmailSmsCredential("mail.from"));
        emailSmsData.setMailTo(EmailSmsUtilClass.getEmailSmsCredential("mail.to"));
        emailSmsData.setMailSubject(EmailSmsUtilClass.getEmailSmsCredential("mail.subject"));
        emailSmsData.setMailBody(EmailSmsUtilClass.getEmailSmsCredential("mail.body"));
        try {
            Message message = EmailSmsUtilClass.getPreparedMessage(emailSmsData, session);
            Transport.send(message);
            System.out.println("Done !!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
