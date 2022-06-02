package com.purosurf.minibar.Presentador.InicioSesion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.purosurf.minibar.DB.MinibarBD;
import com.purosurf.minibar.Presentador.InicioSesion.Interfaces.IReestablecerContrasenaPresentador;
import com.purosurf.minibar.Vista.InicioSesion.Interfaces.IReestablecerContrasena_View;

public class ReestablecerContrasenaPresentador implements IReestablecerContrasenaPresentador{

    IReestablecerContrasena_View iReestablecerContrasena_view;

    public ReestablecerContrasenaPresentador(IReestablecerContrasena_View iReestablecerContrasena_view)
    {
        this.iReestablecerContrasena_view = iReestablecerContrasena_view;
    }

    @Override
    public void ActualizarContrasena(Context context, int idUsuario, String nuevaContrasena) {
        try{
            MinibarBD conexion = new MinibarBD(context, "Minibar_Sistema", null, 1);
            SQLiteDatabase base = conexion.getWritableDatabase();
            String consultaSql;
            consultaSql = "UPDATE USUARIO SET " +
                    "CONTRASEÑA = '" + nuevaContrasena + "'" +
                    "WHERE IDUSUARIO = '" + idUsuario + "'";
            base.execSQL(consultaSql);
        }
        catch (Exception e)
        {

        }
    }
}
