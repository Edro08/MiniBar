package com.purosurf.minibar.Modelo;

public class DetalleConsumo {
    private int IdDetalleConsumo;
    private int IdConsumo;
    //private int IdProducto; // variable correcta
    private String NombreProducto; //variable improvisada para prueba
    private int Cantidad;
    private float SubTotal;

    public DetalleConsumo(int idDetalleConsumo, int idConsumo, String nombreProducto, int cantidad, float subTotal) {
        IdDetalleConsumo = idDetalleConsumo;
        IdConsumo = idConsumo;
        NombreProducto = nombreProducto;
        Cantidad = cantidad;
        SubTotal = subTotal;
    }

    public int getIdDetalleConsumo() {
        return IdDetalleConsumo;
    }

    public void setIdDetalleConsumo(int idDetalleConsumo) {
        IdDetalleConsumo = idDetalleConsumo;
    }

    public int getIdConsumo() {
        return IdConsumo;
    }

    public void setIdConsumo(int idConsumo) {
        IdConsumo = idConsumo;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        NombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public float getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(float subTotal) {
        SubTotal = subTotal;
    }
}
