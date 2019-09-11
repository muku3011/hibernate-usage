package com.projects.email_sms.email;

import com.projects.email_sms.EmailSmsData;
import com.projects.email_sms.EmailSmsUtilClass;
import com.projects.email_sms.MailType;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

public class SendMailGmailSSL {
    public static void main(String[] args) {

        Session session = Session.getDefaultInstance(EmailSmsUtilClass.getProperties(MailType.GMAIL_SSL),
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EmailSmsUtilClass.getEmailSmsCredential("mail.username"), EmailSmsUtilClass.getEmailSmsCredential("mail.password"));
                    }
                });

        try {
            EmailSmsData emailSmsData = new EmailSmsData();
            emailSmsData.setMailFrom(EmailSmsUtilClass.getEmailSmsCredential("mail.from"));
            emailSmsData.setMailTo(EmailSmsUtilClass.getEmailSmsCredential("mail.to"));
            emailSmsData.setMailSubject(EmailSmsUtilClass.getEmailSmsCredential("mail.subject"));
            emailSmsData.setMailBody(EmailSmsUtilClass.getEmailSmsCredential("mail.body"));

            Message message = EmailSmsUtilClass.getPreparedMessage(emailSmsData, session);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}