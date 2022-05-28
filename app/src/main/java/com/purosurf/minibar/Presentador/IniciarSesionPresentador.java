package com.purosurf.minibar.Presentador;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Usuario;
import com.purosurf.minibar.Presentador.Interfaces.IIniciarSesionPresentador;
import com.purosurf.minibar.Vista.Interfaces.InicioSesion.IIniciarSesion_View;

public class IniciarSesionPresentador implements IIniciarSesionPresentador {

    IIniciarSesion_View iniciarSesion_view;

    public IniciarSesionPresentador (IIniciarSesion_View iniciarSesion_view)
    {
        this.iniciarSesion_view = iniciarSesion_view;
    }

    @Override
    public boolean OnIniciarSesion(String Usuario, String Contrasena, Context context) {
        boolean estado = false;
        Usuario usuario = new Usuario(Usuario,Contrasena);
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM USUARIO WHERE USUARIO = '"+ usuario.getUsuario() +"'";
        Cursor datos = base.rawQuery(consultaSql, null);
        boolean isIniciarSesionSuccess = datos.moveToFirst();

        if(isIniciarSesionSuccess) {
            if(usuario.getContrasena().equals(datos.getString(2)))
            {
                iniciarSesion_view.OnIniciarSesionResult("Inicio de Sesion Exitoso");
                estado = true;
            }
            else
            {
                iniciarSesion_view.OnIniciarSesionResult("Datos incorrectos");
            }
        }
        else
        {
            iniciarSesion_view.OnIniciarSesionResult("Usuario no Existe");
        }
        datos.close();
        return estado;
    }
}
