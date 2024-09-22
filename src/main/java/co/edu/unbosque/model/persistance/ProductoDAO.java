package co.edu.unbosque.model.persistance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Producto;

public class ProductoDAO {

    private final String archivoProductos = "productos.bin";
    private List<Producto> listaProductos;

    public ProductoDAO() {
        listaProductos = leerProductosDesdeArchivo();
        if (listaProductos == null) {
            listaProductos = new ArrayList<>();
        }
    }

    // Obtener todos los productos
    public List<Producto> obtenerTodosProductos() {
        return listaProductos;
    }

    // Guardar un nuevo producto
    public void guardarProducto(Producto producto) {
        listaProductos.add(producto);
        guardarProductosEnArchivo();
    }

    // Actualizar un producto existente
    public void actualizarProducto(Producto producto) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId().equals(producto.getId())) {
                listaProductos.set(i, producto);
                break;
            }
        }
        guardarProductosEnArchivo();
    }

    // Obtener un producto por su ID
    public Producto obtenerProductoPorId(String id) {
        for (Producto producto : listaProductos) {
            if (producto.getId().equals(id)) {
                return producto;
            }
        }
        return null;
    }

    // Leer los productos desde el archivo binario
    @SuppressWarnings("unchecked")
	private List<Producto> leerProductosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoProductos))) {
            return (List<Producto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Guardar los productos en el archivo binario
    private void guardarProductosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoProductos))) {
            oos.writeObject(listaProductos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

