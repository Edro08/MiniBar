package com.purosurf.minibar.Modelo;

public class Producto {
    int IdProducto;
    String ProductoNombre;
    int IdCategoria;
    float PrecioUnitario;
    int IdEstado;
    String ImagenURL;

    public Producto(int idProducto, String productoNombre, int idCategoria, float precioUnitario, int idEstado, String imagenURL) {
        IdProducto = idProducto;
        ProductoNombre = productoNombre;
        IdCategoria = idCategoria;
        PrecioUnitario = precioUnitario;
        IdEstado = idEstado;
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

    public void setIdEstado(int idEstado) {
        IdEstado = idEstado;
    }

    public String getImagenURL() {
        return ImagenURL;
    }

    public void setImagenURL(String imagenURL) {
        ImagenURL = imagenURL;
    }
}
