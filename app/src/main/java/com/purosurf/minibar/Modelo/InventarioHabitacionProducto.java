package com.purosurf.minibar.Modelo;

public class InventarioHabitacionProducto {
    private int IdInventarioHabitación;
    private int IdHabitacion;
    private int IdProducto;
    private int Existencias;
    private int CantidadMinima;
    private String NombreHabitacion;
    private String ProductoNombre;
    private float PrecioUnitario;
    private int Cantidad;
    private float SubTotal;

    public InventarioHabitacionProducto(int idInventarioHabitación, int idHabitacion,String nombreHabitacion ,
                                int idProducto, String nombreProducto, float precio ,
                                int existencias, int cantidadMinima, int cantidad, float subTotal)
    {
        IdInventarioHabitación = idInventarioHabitación;
        IdHabitacion = idHabitacion;
        NombreHabitacion = nombreHabitacion;
        IdProducto = idProducto;
        ProductoNombre = nombreProducto;
        PrecioUnitario = precio;
        Existencias = existencias;
        CantidadMinima = cantidadMinima;
        Cantidad = cantidad;
        SubTotal = subTotal;
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

    public int getExistencias() {
        return Existencias;
    }

    public void setExistencias(int existencias) {
        Existencias = existencias;
    }

    public int getCantidadMinima() {
        return CantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        CantidadMinima = cantidadMinima;
    }

    public String getNombreHabitacion() {
        return NombreHabitacion;
    }

    public void setNombreHabitacion(String nombreHabitacion) {
        NombreHabitacion = nombreHabitacion;
    }

    public String getProductoNombre() {
        return ProductoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        ProductoNombre = productoNombre;
    }

    public float getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        PrecioUnitario = precioUnitario;
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
