package com.facturacion.sistemadefacturacion.repository;

import com.facturacion.sistemadefacturacion.entity.DetalleFactura;
import com.facturacion.sistemadefacturacion.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {
    List<DetalleFactura> findByFactura(Factura factura);

}
