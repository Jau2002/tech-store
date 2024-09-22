package co.edu.unbosque.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Carrito implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private String clienteId;
    private Map<Producto, Integer> productos;

    // Constructor
    public Carrito(String clienteId) {
        this.clienteId = clienteId;
        this.productos = new HashMap<>();
    }

    // Getters y Setters
    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public Map<Producto, Integer> getProductos() {
        return productos;
    }

    public void setProductos(Map<Producto, Integer> productos) {
        this.productos = productos;
    }

    
    public void agregarProducto(Producto producto, int cantidad) {
        productos.put(producto, productos.getOrDefault(producto, 0) + cantidad);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public void vaciarCarrito() {
        productos.clear();
    }

    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : productos.entrySet()) {
            total += entry.getKey().getPrecio() * entry.getValue();
        }
        return total;
    }
}
