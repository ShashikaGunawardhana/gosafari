package com.example.safaribooking;

import android.content.Context;
import android.os.AsyncTask;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailAPI extends AsyncTask<Void,Void,Void> {

    private Context context;
    private Session session;
    private String email,park,type;
   // private int fulltkt,halftkt;
    //private double fulltot,halftot,tot;


    public JavaMailAPI(Context context, String email, String park, String type) {
        this.context = context;
        this.email = email;
       // this.name = name;
        this.park = park;
        this.type = type;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

       session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
           protected PasswordAuthentication getPasswordAuthenticaton(){
               return new PasswordAuthentication(Utils.EMAIL,Utils.PASSWORD);
           }
       });

        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(new InternetAddress(Utils.EMAIL));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject(park);
            mimeMessage.setSubject(type);
            Transport.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
