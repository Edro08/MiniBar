package com.purosurf.minibar.Modelo;

public class Salida {

    private int IdSalida,
                IdUsuario,
                IdProducto;
    private String  Desccripcion,
                    Fecha;
    private int Cantidad;
    private float   Precio,
                    Total;

    public Salida(int idSalida, int idUsuario, int idProducto, String desccripcion, String fecha, int cantidad, float precio, float total) {
        IdSalida = idSalida;
        IdUsuario = idUsuario;
        IdProducto = idProducto;
        Desccripcion = desccripcion;
        Fecha = fecha;
        Cantidad = cantidad;
        Precio = precio;
        Total = total;
    }

    public int getIdSalida() {
        return IdSalida;
    }

    public void setIdSalida(int idSalida) {
        IdSalida = idSalida;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public String getDesccripcion() {
        return Desccripcion;
    }

    public void setDesccripcion(String desccripcion) {
        Desccripcion = desccripcion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }
}
