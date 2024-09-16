package com.facturacion.sistemadefacturacion.controller;

import com.facturacion.sistemadefacturacion.entity.DetalleFactura;
import com.facturacion.sistemadefacturacion.entity.Factura;
import com.facturacion.sistemadefacturacion.entity.Producto;
import com.facturacion.sistemadefacturacion.service.DetalleFacturaService;
import com.facturacion.sistemadefacturacion.service.FacturaService;
import com.facturacion.sistemadefacturacion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles-factura")
@CrossOrigin(origins = "*", methods  = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private ProductoService productoService;

    // Crear un nuevo detalle de factura
    @PostMapping("/crear")
    public ResponseEntity<DetalleFactura> crearDetalleFactura(
            @RequestParam Long facturaId,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad) {

        Factura factura = facturaService.obtenerFacturaPorId(facturaId);
        Producto producto = productoService.obtenerProductoPorId(productoId);

        if (producto.getStock() < cantidad) {
            return ResponseEntity.badRequest().body(null); // Verifica si hay suficiente stock
        }

        DetalleFactura nuevoDetalle = detalleFacturaService.crearDetalle(factura, producto, cantidad);
        return ResponseEntity.ok(nuevoDetalle);
    }

    // Obtener los detalles de una factura por factura ID
    @GetMapping("/factura/{facturaId}")
    public ResponseEntity<List<DetalleFactura>> obtenerDetallesPorFactura(@PathVariable Long facturaId) {
        Factura factura = facturaService.obtenerFacturaPorId(facturaId);
        List<DetalleFactura> detalles = detalleFacturaService.obtenerDetallesPorFactura(factura);
        return ResponseEntity.ok(detalles);
    }

    // Obtener un detalle de factura por su ID
    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> obtenerDetallePorId(@PathVariable Long id) {
        DetalleFactura detalle = detalleFacturaService.obtenerDetallePorId(id);
        return ResponseEntity.ok(detalle);
    }

    // Actualizar un detalle de factura
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<DetalleFactura> actualizarDetalleFactura(
            @PathVariable Long id,
            @RequestParam Integer cantidad) {

        DetalleFactura detalleActualizado = detalleFacturaService.actualizarDetalle(id, cantidad);
        return ResponseEntity.ok(detalleActualizado);
    }

    // Eliminar un detalle de factura
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarDetalleFactura(@PathVariable Long id) {
        detalleFacturaService.eliminarDetalle(id);
        return ResponseEntity.noContent().build(); // Retorna un 204 si se elimina correctamente
    }
}
