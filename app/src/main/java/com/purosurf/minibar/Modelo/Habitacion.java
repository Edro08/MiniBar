package com.purosurf.minibar.Modelo;

public class Habitacion {

    private int IdHabitaccion;
    private String NombreHabitacion;
    private int IdEstado;

    public Habitacion(int idHabitaccion, String nombreHabitacion, int idEstado) {
        IdHabitaccion = idHabitaccion;
        NombreHabitacion = nombreHabitacion;
        IdEstado = idEstado;
    }

    public int getIdHabitaccion() {
        return IdHabitaccion;
    }

    public void setIdHabitaccion(int idHabitaccion) {
        IdHabitaccion = idHabitaccion;
    }

    public String getNombreHabitacion() {
        return NombreHabitacion;
    }

    public void setNombreHabitacion(String nombreHabitacion) {
        NombreHabitacion = nombreHabitacion;
    }

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int idEstado) {
        IdEstado = idEstado;
    }

}
