package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces.IAdicionarActualizarUsuarioPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.Interfaces.IAdicionarActualizarUsuario_View;

public class AdicionarActualizarUsuarioPresentador implements IAdicionarActualizarUsuarioPresentador {

    IAdicionarActualizarUsuario_View iAdicionarActualizarUsuario_view;

    public AdicionarActualizarUsuarioPresentador(IAdicionarActualizarUsuario_View iAdicionarActualizarUsuario_view)
    {
        this.iAdicionarActualizarUsuario_view = iAdicionarActualizarUsuario_view;
    }

    @Override
    public boolean VerificarCorreoElectronico(Context context, String correo) {
        boolean estado = false;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM PERSONA " +
                "WHERE CORREOELECTRONICO = '" + correo + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        if(datos.moveToFirst() == true)
        {
            estado = true;
        }
        return estado;
    }

    @Override
    public Cursor DatosPersona(Context context, int idUsuario) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT PERSONA.IDPERSONA,NOMBRE,APELLIDO,CORREOELECTRONICO," +
                "PREGUNTASEGURIDAD,REPUESTASEGURIDAD FROM PERSONA " +
                "INNER JOIN USUARIO ON PERSONA.IDPERSONA = USUARIO.IDPERSONA " +
                "WHERE IDUSUARIO = '" + idUsuario + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        return datos;
    }
}
