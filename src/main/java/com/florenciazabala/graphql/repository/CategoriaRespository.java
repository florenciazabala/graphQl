package com.florenciazabala.graphql.repository;

import com.florenciazabala.graphql.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRespository extends JpaRepository<Categoria, Long> {
}
