package com.facturacion.sistemadefacturacion.service;

import com.facturacion.sistemadefacturacion.entity.DetalleFactura;
import com.facturacion.sistemadefacturacion.entity.Factura;
import com.facturacion.sistemadefacturacion.entity.Producto;
import com.facturacion.sistemadefacturacion.repository.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    // Método para crear un nuevo DetalleFactura
    public DetalleFactura crearDetalle(Factura factura, Producto producto, Integer cantidad) {
        Double precioUnitario = producto.getPrecio(); // Suponiendo que el producto tiene un precio
        DetalleFactura detalle = new DetalleFactura(factura, producto, cantidad, precioUnitario);
        return detalleFacturaRepository.save(detalle); // Guardar el detalle en la base de datos
    }

    // Método para actualizar un DetalleFactura
    public DetalleFactura actualizarDetalle(Long id, Integer cantidad) {
        DetalleFactura detalle = detalleFacturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detalle no encontrado"));

        detalle.setCantidad(cantidad); // Actualizar la cantidad
        return detalleFacturaRepository.save(detalle); // Guardar los cambios
    }

    // Método para eliminar un DetalleFactura
    public void eliminarDetalle(Long id) {
        detalleFacturaRepository.deleteById(id);
    }

    // Método para obtener un detalle por su ID
    public DetalleFactura obtenerDetallePorId(Long id) {
        return detalleFacturaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Detalle no encontrado"));
    }

    // Método para obtener todos los detalles de una factura
    public List<DetalleFactura> obtenerDetallesPorFactura(Factura factura) {
        return detalleFacturaRepository.findByFactura(factura);
    }
}
