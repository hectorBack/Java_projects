package com.facturacion.sistemadefacturacion.service;

import com.facturacion.sistemadefacturacion.entity.Producto;
import com.facturacion.sistemadefacturacion.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // Obtener producto por ID
    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    // Guardar producto
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Actualizar producto
    public Producto updateProducto(Long id, Producto producto) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto existingProducto = optionalProducto.get();
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setDescripcion(producto.getDescripcion());
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setStock(producto.getStock());
            return productoRepository.save(existingProducto);
        }
        return null;
    }

    // Eliminar producto
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Método para obtener un producto por su ID
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con el ID: " + id));
    }
}
