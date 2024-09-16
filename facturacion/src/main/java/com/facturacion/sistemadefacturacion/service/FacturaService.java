package com.facturacion.sistemadefacturacion.service;

import com.facturacion.sistemadefacturacion.entity.Factura;
import com.facturacion.sistemadefacturacion.entity.DetalleFactura;
import com.facturacion.sistemadefacturacion.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    // Obtener todas las facturas
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    // Obtener factura por ID
    public Optional<Factura> getFacturaById(Long id) {
        return facturaRepository.findById(id);
    }

    // Guardar factura
    public Factura saveFactura(Factura factura) {
        factura.setFecha(LocalDateTime.now());  // Asignar la fecha actual
        double total = factura.getDetalles().stream().mapToDouble(DetalleFactura::getSubtotal).sum();
        factura.setTotal(total);
        return facturaRepository.save(factura);
    }

    // Actualizar factura
    public Factura updateFactura(Long id, Factura factura) {
        Optional<Factura> optionalFactura = facturaRepository.findById(id);
        if (optionalFactura.isPresent()) {
            Factura existingFactura = optionalFactura.get();
            existingFactura.setCliente(factura.getCliente());
            existingFactura.setDetalles(factura.getDetalles());
            double total = factura.getDetalles().stream().mapToDouble(DetalleFactura::getSubtotal).sum();
            existingFactura.setTotal(total);
            return facturaRepository.save(existingFactura);
        }
        return null;
    }

    // Eliminar factura
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    // Método para obtener una factura por su ID
    public Factura obtenerFacturaPorId(Long id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con el ID: " + id));
    }
}
