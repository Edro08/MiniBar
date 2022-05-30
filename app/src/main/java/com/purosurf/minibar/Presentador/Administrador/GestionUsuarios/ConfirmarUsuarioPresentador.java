package com.purosurf.minibar.Presentador.Administrador.GestionUsuarios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Presentador.Administrador.GestionUsuarios.Interfaces.IConfirmarUsuarioPresentador;
import com.purosurf.minibar.Vista.Administrador.GestionUsuarios.Interfaces.IConfirmarUsuario_View;

public class ConfirmarUsuarioPresentador implements IConfirmarUsuarioPresentador {

    IConfirmarUsuario_View iConfirmarUsuario_view;

    public ConfirmarUsuarioPresentador(IConfirmarUsuario_View iConfirmarUsuario_view)
    {
        this.iConfirmarUsuario_view = iConfirmarUsuario_view;
    }

    @Override
    public boolean VerificarUsuario(Context context, String usuario) {
        boolean estado = false;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT * FROM USUARIO " +
                "WHERE USUARIO = '" + usuario + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        if(datos.moveToFirst() == true)
        {
            estado = true;
        }
        return estado;
    }

    @Override
    public Cursor DatosUsuario(Context context, int idUsuario) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "SELECT IDUSUARIO, USUARIO, CONTRASEÑA, IDESTADO, IDPERSONA " +
                "FROM USUARIO WHERE IDUSUARIO = '" + idUsuario + "'";
        Cursor datos = base.rawQuery(consultaSql, null);
        return datos;
    }

    @Override
    public int AdicionarPersona(Context context, String nombre, String apellido, String correo, String pregunta, String respuesta) {
        Cursor idPersona;
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "INSERT INTO PERSONA(NOMBRE,APELLIDO,CORREOELECTRONICO,PREGUNTASEGURIDAD,REPUESTASEGURIDAD) " +
                "VALUES('"+ nombre +"', '"+ apellido +"','"+ correo +"'," +
                "'"+ pregunta +"','"+ respuesta +"')";
        base.execSQL(consultaSql);
        idPersona = base.rawQuery("SELECT last_insert_rowid() FROM PERSONA", null);
        idPersona.moveToFirst();
        return idPersona.getInt(0);
    }


    @Override
    public void ActualizarPersona(Context context,int idPersona, String nombre, String apellido, String correo, String pregunta, String respuesta) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "UPDATE PERSONA SET " +
                "NOMBRE = '" + nombre + "' ," +
                "APELLIDO = '" + apellido + "' ," +
                "CORREOELECTRONICO = '" + correo + "' ," +
                "PREGUNTASEGURIDAD = '" + pregunta + "' ," +
                "REPUESTASEGURIDAD = '" + respuesta + "'" +
                "WHERE IDPERSONA = '" + idPersona + "'";
        base.execSQL(consultaSql);
    }

    @Override
    public void AdicionarUsuario(Context context, int idPersona, String usuario, String clave) {
        //Conexión a la BD
        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "INSERT INTO USUARIO(USUARIO,CONTRASEÑA,IDESTADO,IDPERSONA) " +
                "VALUES('"+ usuario +"','"+ clave +"',1,'"+ idPersona +"')";
        base.execSQL(consultaSql);
    }

    @Override
    public void ActualizarUsuario(Context context, int idUsuario, String usuario, String clave) {
        //Conexión a la BD
        if(idUsuario == 1 && !usuario.equals("Admin"))
        {
            usuario = "Admin";
            iConfirmarUsuario_view.OnCambiarUsuarioAdmin("No es posible cambiar Usuario Admin!");
        }

        MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
        SQLiteDatabase base = conexion.getWritableDatabase();
        String consultaSql;
        consultaSql = "UPDATE USUARIO SET " +
                "USUARIO = '" + usuario + "' ," +
                "CONTRASEÑA = '" + clave + "'" +
                "WHERE IDUSUARIO = '" + idUsuario + "'";
        base.execSQL(consultaSql);
    }
}
