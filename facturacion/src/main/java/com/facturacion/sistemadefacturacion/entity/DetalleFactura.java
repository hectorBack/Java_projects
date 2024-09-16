package com.facturacion.sistemadefacturacion.entity;

import jakarta.persistence.*;

@Entity
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Factura factura;

    @ManyToOne
    private Producto producto;

    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    // Constructor vacío
    public DetalleFactura() {
    }

    // Constructor con parámetros
    public DetalleFactura(Factura factura, Producto producto, Integer cantidad, Double precioUnitario) {
        this.factura = factura;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        calcularSubtotal(); // Se calcula automáticamente el subtotal
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal(); // Recalcular el subtotal cuando se cambie la cantidad
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
        calcularSubtotal(); // Recalcular el subtotal cuando se cambie el precio unitario
    }

    public Double getSubtotal() {
        return subtotal;
    }

    // Método para calcular el subtotal
    private void calcularSubtotal() {
        this.subtotal = this.cantidad * this.precioUnitario;
    }
}

