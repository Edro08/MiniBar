package com.purosurf.minibar.Presentador.InicioSesion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Persona;
import com.purosurf.minibar.Presentador.InicioSesion.Interfaces.IRecuperarCuentaPresentador;
import com.purosurf.minibar.Vista.InicioSesion.Interfaces.IRecuperarCuenta_View;

import java.util.ArrayList;
import java.util.List;
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
    public String PreguntaPersona(Context context, int idUsuario) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT PREGUNTASEGURIDAD FROM PERSONA " +
                "INNER JOIN USUARIO ON PERSONA.IDPERSONA = USUARIO.IDPERSONA " +
                "WHERE IDUSUARIO = '" + idUsuario + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        datos.moveToFirst();
        return datos.getString(0);
    }

    @Override
    public boolean VerificarCorreo(Context context, String correo, int idUsuario) {
        boolean estado = false;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT CORREOELECTRONICO FROM PERSONA " +
                "INNER JOIN USUARIO ON PERSONA.IDPERSONA = USUARIO.IDPERSONA " +
                "WHERE IDUSUARIO = '" + idUsuario + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        datos.moveToFirst();
        String correoactual = datos.getString(0).toLowerCase();
        if(correoactual.equals(correo.toLowerCase())){ estado = true;}

        return estado;
    }

    @Override
    public boolean VerificarRespuesta(Context context, String respuesta, int idUsuario) {
        boolean estado = false;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT REPUESTASEGURIDAD FROM PERSONA " +
                "INNER JOIN USUARIO ON PERSONA.IDPERSONA = USUARIO.IDPERSONA " +
                "WHERE IDUSUARIO = '" + idUsuario + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        datos.moveToFirst();
        if(datos.getString(0).equals(respuesta)){ estado = true;}

        return estado;
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
            mimeMessage.setSubject("Recuperacion de cuenta App Mini Bar");
            mimeMessage.setText("Hola Usuario, " +
                    "\n\nEsperamos de parte de Mini Bar del Hotel Puro Surf que tengas un buen dia" +
                    "\n\nEnviamos el código de seguridad respectivo para el acceso a tu cuenta" +
                    "\n\nCódigo de verificación: " + codigo);

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

        }

        return estado;
    }
}
