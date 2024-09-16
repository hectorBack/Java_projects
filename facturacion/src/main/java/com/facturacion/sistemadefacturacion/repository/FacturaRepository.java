package com.facturacion.sistemadefacturacion.repository;

import com.facturacion.sistemadefacturacion.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
