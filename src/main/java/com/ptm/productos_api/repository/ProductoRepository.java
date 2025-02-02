package com.ptm.productos_api.repository;

import com.ptm.productos_api.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByPrecioLessThanEqual(Double precio);
}
