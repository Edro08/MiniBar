package com.purosurf.minibar.Modelo;

public class Inventario {
    private int IdInventario,
                IdProducto,
                CantidadMinima,
                CantidadMaxima,
                Existencias;
    private String NombreProd;

    public Inventario(int idInventario, int idProducto, int cantidadMinima, int cantidadMaxima, int existencias, String nombreProd) {
        IdInventario = idInventario;
        IdProducto = idProducto;
        CantidadMinima = cantidadMinima;
        CantidadMaxima = cantidadMaxima;
        Existencias = existencias;
        NombreProd = nombreProd;
    }

    public int getIdInventario() {
        return IdInventario;
    }

    public void setIdInventario(int idInventario) {
        IdInventario = idInventario;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public int getCantidadMinima() {
        return CantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        CantidadMinima = cantidadMinima;
    }

    public int getCantidadMaxima() {
        return CantidadMaxima;
    }

    public void setCantidadMaxima(int cantidadMaxima) {
        CantidadMaxima = cantidadMaxima;
    }

    public int getExistencias() {
        return Existencias;
    }

    public void setExistencias(int existencias) {
        Existencias = existencias;
    }

    public String getNombreProd() {
        return NombreProd;
    }

    public void setNombreProd(String nombreProd) {
        NombreProd = nombreProd;
    }
}
