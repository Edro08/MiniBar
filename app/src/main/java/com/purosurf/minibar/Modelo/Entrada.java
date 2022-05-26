package com.purosurf.minibar.Modelo;

public class Entrada {

    private int IdEntrada,
                IdUsuario,
                IdProducto;
    private String Descripcion,
                    Fecha;
    private int Cantidad;
    private float   Precio,
                    Total;

    public Entrada(int idEntrada, int idUsuario, int idProducto, String descripcion, String fecha, int cantidad, float precio, float total) {
        IdEntrada = idEntrada;
        IdUsuario = idUsuario;
        IdProducto = idProducto;
        Descripcion = descripcion;
        Fecha = fecha;
        Cantidad = cantidad;
        Precio = precio;
        Total = total;
    }

    public int getIdEntrada() {
        return IdEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        IdEntrada = idEntrada;
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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
