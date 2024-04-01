# GraphQl


http://localhost:8090 como trabajo con GraphQl en este puerto puedo ver links disponibles
http://localhost:8090/h2-console

## Anotaciones en GraphQl

* QueryMapping --> Consultas

* Mutattion --> Operaciones que van a alterar

# READ
type Query{
# Declaro un Query que se va a llamar igual que el método en el controller, retorna una lista de prodcto
listarProductos: [Producto]
}

# Declaro el objeto producto
type Producto{
id: String,
precio: Float,
cantidad: Int
}

http://localhost:8090/graphiql --> path por default




