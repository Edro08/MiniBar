package com.purosurf.minibar.Presentador.InicioSesion;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Modelo.Usuario;
import com.purosurf.minibar.Presentador.InicioSesion.Interfaces.IIniciarSesionPresentador;
import com.purosurf.minibar.Vista.InicioSesion.Interfaces.IIniciarSesion_View;

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
        consultaSql = "SELECT IDUSUARIO,USUARIO,CONTRASEÑA,IDESTADO, NOMBRE FROM USUARIO INNER JOIN " +
                "PERSONA ON USUARIO.IDPERSONA = PERSONA.IDPERSONA " +
                "WHERE USUARIO = '"+ usuario.getUsuario() +"'";
        Cursor datos = base.rawQuery(consultaSql, null);
        boolean isIniciarSesionSuccess = datos.moveToFirst();

        if(isIniciarSesionSuccess) {
            if(usuario.getContrasena().equals(datos.getString(2)))
            {
                if(datos.getInt(3) == 1)
                {
                    iniciarSesion_view.OnIniciarSesionResult("Inicio de Sesion Exitoso");
                    iniciarSesion_view.IdUser(datos.getInt(0));
                    iniciarSesion_view.NombreUsuario(datos.getString(4));
                    estado = true;
                }
                else
                {
                    iniciarSesion_view.OnIniciarSesionResult("Usuario Deshabilitado");
                }
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

    @Override
    public boolean typeUser(String Usuario) {
        boolean respuesta = false;
        if (Usuario.equals("Admin"))
        {
            respuesta = true;
        }
        return respuesta;
    }
}
