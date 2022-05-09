package com.purosurf.minibar.Modelo;

public class Habitacion {

    private int IdHabitaccion;
    private String NombreHabitacion;

    public Habitacion(int idHabitaccion, String nombreHabitacion, int idEstado) {
        IdHabitaccion = idHabitaccion;
        NombreHabitacion = nombreHabitacion;
        IdEstado = idEstado;
    }

    private int IdEstado;

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
