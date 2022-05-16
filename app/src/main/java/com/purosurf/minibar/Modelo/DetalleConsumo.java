package com.purosurf.minibar.Modelo;

public class DetalleConsumo {
    private int IdDetalleConsumo;
    private int IdConsumo;
    private int IdProducto;
    private int Cantidad;
    private float SubTotal;

    public DetalleConsumo(int idDetalleConsumo, int idConsumo, int idProducto, int cantidad, float subTotal) {
        IdDetalleConsumo = idDetalleConsumo;
        IdConsumo = idConsumo;
        IdProducto = idProducto;
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

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
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
