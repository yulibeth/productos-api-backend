package com.ptm.productos_api.service;

import com.ptm.productos_api.model.Producto;
import com.ptm.productos_api.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public Double calcularValorTotalInventario() {
        return productoRepository.findAll()
                .stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidadStock())
                .sum();
    }

    public List<List<Object>> obtenerCombinacionesDeProductos(Double valor) {
        List<Producto> productos = productoRepository.findAll();
        List<List<Object>> resultado = new ArrayList<>();

        // Obtener todas las combinaciones de 2 o 3 productos
        for (int i = 0; i < productos.size(); i++) {
            for (int j = i + 1; j < productos.size(); j++) {
                for (int k = j + 1; k <= productos.size(); k++) {
                    List<Producto> combinacion = new ArrayList<>(Arrays.asList(productos.get(i), productos.get(j)));
                    if (k < productos.size()) combinacion.add(productos.get(k));

                    double suma = combinacion.stream().mapToDouble(Producto::getPrecio).sum();
                    if (suma <= valor) {
                        List<Object> datos = new ArrayList<>();
                        datos.add(combinacion.stream().map(Producto::getNombre).collect(Collectors.toList()));
                        datos.add(suma);
                        resultado.add(datos);
                    }
                }
            }
        }

        return resultado.stream()
                .sorted((a, b) -> Double.compare((Double) b.get(1), (Double) a.get(1))) // Orden descendente
                .limit(5)
                .collect(Collectors.toList());
    }
}
