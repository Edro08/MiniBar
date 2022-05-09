package com.purosurf.minibar.Modelo;

public class InventarioHabitacion {
    private int IdInventarioHabitación;
    private int IdHabitacion;
    private int IdProducto;
    private float Existencias;
    private int CantidadMinima;

    public InventarioHabitacion(int idInventarioHabitación, int idHabitacion, int idProducto, float existencias, int cantidadMinima) {
        IdInventarioHabitación = idInventarioHabitación;
        IdHabitacion = idHabitacion;
        IdProducto = idProducto;
        Existencias = existencias;
        CantidadMinima = cantidadMinima;
    }

    public int getIdInventarioHabitación() {
        return IdInventarioHabitación;
    }

    public void setIdInventarioHabitación(int idInventarioHabitación) {
        IdInventarioHabitación = idInventarioHabitación;
    }

    public int getIdHabitacion() {
        return IdHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        IdHabitacion = idHabitacion;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public float getExistencias() {
        return Existencias;
    }

    public void setExistencias(float existencias) {
        Existencias = existencias;
    }

    public int getCantidadMinima() {
        return CantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        CantidadMinima = cantidadMinima;
    }
}
