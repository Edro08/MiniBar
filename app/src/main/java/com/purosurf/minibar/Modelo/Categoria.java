package com.purosurf.minibar.Modelo;

public class Categoria {

    private int IdCategoria;
    private String NombreCategoria;
    private int IdEstado;

    public Categoria(int idCategoria, String nombreCategoria, int idEstado) {
        IdCategoria = idCategoria;
        NombreCategoria = nombreCategoria;
        IdEstado = idEstado;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        IdCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return NombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        NombreCategoria = nombreCategoria;
    }

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int idEstado) {
        IdEstado = idEstado;
    }
}
