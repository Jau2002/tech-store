package co.edu.unbosque.model.persistance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import co.edu.unbosque.model.Cliente;

public class ClienteDAO {

    private final String archivoClientes = "clientes.bin";
    private List<Cliente> listaClientes;

    public ClienteDAO() {
        listaClientes = leerClientesDesdeArchivo();
        if (listaClientes == null) {
            listaClientes = new ArrayList<>();
        }
    }

    // Obtener todos los clientes
    public List<Cliente> obtenerTodosClientes() {
        return listaClientes;
    }

    // Guardar un nuevo cliente
    public void guardarCliente(Cliente cliente) {
        listaClientes.add(cliente);
        guardarClientesEnArchivo();
    }

    // Actualizar un cliente existente
    public void actualizarCliente(Cliente cliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getId().equals(cliente.getId())) {
                listaClientes.set(i, cliente);
                break;
            }
        }
        guardarClientesEnArchivo();
    }

    // Obtener un cliente por email
    public Cliente obtenerClientePorEmail(String email) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }

    // Leer los clientes desde el archivo binario
    @SuppressWarnings("unchecked")
	private List<Cliente> leerClientesDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivoClientes))) {
            return (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Guardar los clientes en el archivo binario
    private void guardarClientesEnArchivo() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivoClientes))) {
            oos.writeObject(listaClientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
