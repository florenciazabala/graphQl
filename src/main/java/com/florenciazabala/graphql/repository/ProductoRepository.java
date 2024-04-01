package com.florenciazabala.graphql.repository;

import com.florenciazabala.graphql.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, String> {
}
