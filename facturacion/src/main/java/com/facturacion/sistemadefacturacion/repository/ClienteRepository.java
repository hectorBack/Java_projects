package com.facturacion.sistemadefacturacion.repository;

import com.facturacion.sistemadefacturacion.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
