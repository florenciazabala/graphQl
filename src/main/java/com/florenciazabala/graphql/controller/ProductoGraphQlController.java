package com.florenciazabala.graphql.controller;

import com.florenciazabala.graphql.dto.ProductoRequest;
import com.florenciazabala.graphql.entities.Categoria;
import com.florenciazabala.graphql.entities.Producto;
import com.florenciazabala.graphql.repository.CategoriaRespository;
import com.florenciazabala.graphql.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

/*Use Controller insted of RestController beacuse isn't an API Rest*/
@Controller
public class ProductoGraphQlController {

  @Autowired
  private ProductoRepository productoRepository;

  @Autowired
  private CategoriaRespository categoriaRespository;

  @QueryMapping
  public List<Producto> listarProductos(){
    return productoRepository.findAll();
  }

  @QueryMapping
  public Producto listarProductoPorId(@Argument String id) throws Exception {
    return productoRepository.findById(id).orElseThrow(() ->
        new Exception("Producto no encontrado"));
  }

  @QueryMapping
  public List<Categoria> listarCategorias(){
    return categoriaRespository.findAll();
  }

  @QueryMapping
  public Categoria listarCategoriaPorId(@Argument Long id) throws Exception {
    return categoriaRespository.findById(id).orElseThrow(() ->
        new Exception("Categoria no encontrada"));
  }

  @MutationMapping
  public Producto guardarProducto(@Argument ProductoRequest productoRequest){

    Categoria categoria = categoriaRespository.findById(productoRequest.categoriaId()).orElse(null);
    Producto producto = new Producto();
    producto.setId(UUID.randomUUID().toString());
    producto.setNombre(productoRequest.nombre());
    producto.setPrecio(productoRequest.precio());
    producto.setCantidad(productoRequest.cantidad());
    producto.setCategoria(categoria);
    return productoRepository.save(producto);
  }

  @MutationMapping
  public Producto actualizarProducto(@Argument String id, @Argument ProductoRequest productoRequest) throws Exception {

    Categoria categoria = categoriaRespository.findById(productoRequest.categoriaId()).orElse(null);
    Producto producto = productoRepository.findById(id).orElseThrow(() -> new Exception("Producto no encontrado"));
    producto.setNombre(productoRequest.nombre());
    producto.setPrecio(productoRequest.precio());
    producto.setCantidad(productoRequest.cantidad());
    producto.setCategoria(categoria);
    return productoRepository.save(producto);
  }

  @MutationMapping
  public void eliminarProducto(@Argument String id){
    productoRepository.deleteById(id);
  }
}
