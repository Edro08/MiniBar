package com.purosurf.minibar.Presentador.InicioSesion;

import android.content.Context;
import android.util.Log;

import com.purosurf.minibar.Presentador.InicioSesion.Interfaces.IRecuperarCuentaPresentador;
import com.purosurf.minibar.Vista.InicioSesion.Interfaces.IRecuperarCuenta_View;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarCuentaPresentador implements IRecuperarCuentaPresentador {

    IRecuperarCuenta_View iRecuperarCuenta_view;

    public RecuperarCuentaPresentador(IRecuperarCuenta_View iRecuperarCuenta_view)
    {
        this.iRecuperarCuenta_view = iRecuperarCuenta_view;
    }


    @Override
    public int NumeroAleatorio() {
        return (int) Math.floor(Math.random()*(999999-100000));
    }

    @Override
    public String VerificarCorreo(Context context, String correo, int idUsuario) {
        return null;
    }

    @Override
    public String VerificarRespuesta(Context context, String respuesta, int idUsuario) {
        return null;
    }

    @Override
    public boolean EnviarCorreo(String correo, int codigo) {
        boolean estado = false;

            String stringSenderEmail = "edrogarcia0813@gmail.com";
            String stringReceiverEmail = correo;
            String stringPasswordSenderEmail = "gfmkcbflplnuzquh";

            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.port", "465");
            properties.setProperty("mail.smtp.auth", "true");
            properties.put("mail.smtp.ssl.enable", "true");

        javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
            }
        });

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(stringSenderEmail));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
            mimeMessage.setSubject("Subject: Recuperacion de cuenta App Mini Bar");
            mimeMessage.setText("Hello Programmer, \n\nProgrammer World has sent you this 2nd email." +
                    " \n\n Cheers!\nProgrammer World: " + codigo);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(mimeMessage);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            estado= true;

        } catch (Exception e) {
            Log.e("tag", e.toString());
        }

        return estado;
    }
}
