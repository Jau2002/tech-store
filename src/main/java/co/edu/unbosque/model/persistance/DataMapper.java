package co.edu.unbosque.model.persistance;

import java.util.HashMap;
import java.util.Map;

import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.CarritoDTO;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.ProductoDTO;

public class DataMapper {

	
	public static ClienteDTO toDTO(Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getEmail(), cliente.getContraseña(),
				cliente.getNivelMembresia(), cliente.getRol());
	}
	
	public static Cliente toEntity(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNombre(), clienteDTO.getEmail(),
				clienteDTO.getContraseña(), clienteDTO.getNivelMembresia(), clienteDTO.getRol());
	}
	
	public static ProductoDTO toDTO(Producto producto) {
		return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getCategoria(), producto.getPrecio(),
				producto.getStock());
	}

	public static Producto toEntity(ProductoDTO productoDTO) {
		return new Producto(productoDTO.getId(), productoDTO.getNombre(), productoDTO.getCategoria(),
				productoDTO.getPrecio(), productoDTO.getStock());
	}

	public static CarritoDTO toDTO(Carrito carrito) {
		CarritoDTO carritoDTO = new CarritoDTO(carrito.getClienteId());
		Map<ProductoDTO, Integer> productosDTO = new HashMap<>();
		for (Map.Entry<Producto, Integer> entry : carrito.getProductos().entrySet()) {
			productosDTO.put(toDTO(entry.getKey()), entry.getValue());
		}
		carritoDTO.setProductosDTO(productosDTO);
		return carritoDTO;
	}

	public static Carrito toEntity(CarritoDTO carritoDTO) {
		Carrito carrito = new Carrito(carritoDTO.getClienteId());
		Map<Producto, Integer> productos = new HashMap<>();
		for (Map.Entry<ProductoDTO, Integer> entry : carritoDTO.getProductosDTO().entrySet()) {
			productos.put(toEntity(entry.getKey()), entry.getValue());
		}
		carrito.setProductos(productos);
		return carrito;
	}
}
