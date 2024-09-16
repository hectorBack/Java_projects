package com.facturacion.sistemadefacturacion.repository;

import com.facturacion.sistemadefacturacion.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
