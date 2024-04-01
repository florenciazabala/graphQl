package com.florenciazabala.graphql;

import com.florenciazabala.graphql.entities.Categoria;
import com.florenciazabala.graphql.entities.Producto;
import com.florenciazabala.graphql.repository.CategoriaRespository;
import com.florenciazabala.graphql.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class GraphqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApplication.class, args);
	}

	@Bean
	CommandLineRunner  commandLineRunner(CategoriaRespository categoriaRespository, ProductoRepository productoRepository){
		Random random = new Random();
		return args -> {
			List.of("Computadoras","Impresoras","Smartphones").forEach(cat -> {
				Categoria categoria = Categoria.builder().nombre(cat).build();
					categoriaRespository.save(categoria);
			});
			categoriaRespository.findAll().forEach( cat -> {
				for( int i = 0; i < 10; i++){
					Producto producto = Producto.builder()
							.id(UUID.randomUUID().toString())
							.nombre("Computadora " + i)
							.precio(100 + Math.random()*50000)
							.cantidad(random.nextInt(100))
							.categoria(cat)
							.build();
					productoRepository.save(producto);
				}
			});
		};
	}


}
