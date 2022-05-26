package com.purosurf.minibar.Modelo;

public class Producto {
    private int IdProducto;
    private String ProductoNombre;
    private int IdCategoria;
    private float PrecioUnitario;
    private int IdEstado; // variable correcta
    private String ImagenURL;

    public Producto(int idProducto, String productoNombre, int idCategoria, float precioUnitario, int idestado, String imagenURL) {
        IdProducto = idProducto;
        ProductoNombre = productoNombre;
        IdCategoria = idCategoria;
        PrecioUnitario = precioUnitario;
        IdEstado = idestado;
        ImagenURL = imagenURL;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int idProducto) {
        IdProducto = idProducto;
    }

    public String getProductoNombre() {
        return ProductoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        ProductoNombre = productoNombre;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        IdCategoria = idCategoria;
    }

    public float getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        PrecioUnitario = precioUnitario;
    }

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int idestado) {
        IdEstado = idestado;
    }

    public String getImagenURL() {
        return ImagenURL;
    }

    public void setImagenURL(String imagenURL) {
        ImagenURL = imagenURL;
    }
}
