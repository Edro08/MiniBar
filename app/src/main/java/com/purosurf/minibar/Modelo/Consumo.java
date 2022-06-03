package com.purosurf.minibar.Modelo;

import android.os.Parcelable;

public class Consumo{

    private int IdConsumo,
                IdUsuario,
                IdHabitación;
    private String Fecha;
    private float Total;


    public Consumo(int idConsumo, int idUsuario, int idHabitación, String fecha, float total) {
        IdConsumo = idConsumo;
        IdUsuario = idUsuario;
        IdHabitación = idHabitación;
        Fecha = fecha;
        Total = total;
    }

    public int getIdConsumo() {
        return IdConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        IdConsumo = idConsumo;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public int getIdHabitación() {
        return IdHabitación;
    }

    public void setIdHabitación(int idHabitación) {
        IdHabitación = idHabitación;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }
}
