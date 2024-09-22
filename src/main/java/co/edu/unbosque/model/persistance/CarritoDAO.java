package co.edu.unbosque.model.persistance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import co.edu.unbosque.model.Carrito;

public class CarritoDAO {
    private final String archivoCarritos = "carritos.bin";
    private List<Carrito> listaCarritos;

    public CarritoDAO() {
        listaCarritos = leerCarritosDesdeArchivo();
        if (listaCarritos == null) {
            listaCarritos = new ArrayList<>();
        }
    }

    // Obtener el carrito de un cliente por su ID
    public Carrito obtenerCarritoPorClienteId(String clienteId) {
        for (Carrito carrito : listaCarritos) {
            if (carrito.getClienteId().equals(clienteId)) {
                return carrito;
            }
        }
        return null;
    }

    // Guardar un carrito
    public void guardarCarrito(Carrito carrito) {
        boolean existe = false;
        for (int i = 0; i < listaCarritos.size(); i++) {
            if (listaCarritos.get(i).getClienteId().equals(carrito.getClienteId())) {
                listaCarritos.set(i, carrito);
                existe = true;
                break;
            }
        }
        if (!existe) {
            listaCarritos.add(carrito);
        }
        guardarCarritosEnArchivo();
    }

    // Eliminar un carrito por clienteId
    public void eliminarCarrito(String clienteId) {
        listaCarritos.removeIf(carrito -> carrito.getClienteId().equals(clienteId));
        guardarCarritosEnArchivo();
    }

    // Leer los carritos desde el archivo binario
    @SuppressWarnings("unchecked")
	private List<Carrito> leerCarritosDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoCarritos))) {
            return (List<Carrito>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Guardar los carritos en el archivo binario
    private void guardarCarritosEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoCarritos))) {
            oos.writeObject(listaCarritos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


