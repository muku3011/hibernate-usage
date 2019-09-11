package com.projects.email_sms;

public class EmailSmsData {

    //Email specific data
    private String mailTo;
    private String mailFrom;
    private String mailSubject;
    private String mailBody;

    //SMS specific data
    private String smsUserName;
    private String smsToNumber;
    private String smsMessage;
    private String smsFromNumber;

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public String getSmsToNumber() {
        return smsToNumber;
    }

    public void setSmsToNumber(String smsToNumber) {
        this.smsToNumber = smsToNumber;
    }

    public String getSmsMessage() {
        return smsMessage;
    }

    public void setSmsMessage(String smsMessage) {
        this.smsMessage = smsMessage;
    }

    public String getSmsFromNumber() {
        return smsFromNumber;
    }

    public void setSmsFromNumber(String smsFromNumber) {
        this.smsFromNumber = smsFromNumber;
    }


    public String getSmsUserName() {
        return smsUserName;
    }

    public void setSmsUserName(String smsUserName) {
        this.smsUserName = smsUserName;
    }
}
