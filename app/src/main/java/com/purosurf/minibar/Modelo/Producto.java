package com.purosurf.minibar.Modelo;

public class Producto {
    private int IdProducto;
    private String ProductoNombre;
    private int IdCategoria;
    private float PrecioUnitario;
    //private int IdEstado; // variable correcta
    private String Estado;//variable improvisada para prueba
    private String ImagenURL;

    public Producto(int idProducto, String productoNombre, int idCategoria, float precioUnitario, String estado, String imagenURL) {
        IdProducto = idProducto;
        ProductoNombre = productoNombre;
        IdCategoria = idCategoria;
        PrecioUnitario = precioUnitario;
        Estado = estado;
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

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getImagenURL() {
        return ImagenURL;
    }

    public void setImagenURL(String imagenURL) {
        ImagenURL = imagenURL;
    }
}
