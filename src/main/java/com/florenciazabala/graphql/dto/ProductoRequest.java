package com.florenciazabala.graphql.dto;

public record ProductoRequest (
  String id,
  String nombre,
  double precio,
  int cantidad,
  Long categoriaId
  ){}
