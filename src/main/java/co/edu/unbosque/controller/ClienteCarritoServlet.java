package co.edu.unbosque.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import co.edu.unbosque.model.Carrito;
import co.edu.unbosque.model.Cliente;
import co.edu.unbosque.model.ClienteDTO;
import co.edu.unbosque.model.Producto;
import co.edu.unbosque.model.persistance.CarritoDAO;
import co.edu.unbosque.model.persistance.ClienteDAO;
import co.edu.unbosque.model.persistance.DataMapper;
import co.edu.unbosque.model.persistance.ProductoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/clienteCarrito")
public class ClienteCarritoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ClienteDAO clienteDAO = new ClienteDAO();
    private CarritoDAO carritoDAO = new CarritoDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "login":
                loginForm(request, response);
                break;
            case "registro":
                registroForm(request, response);
                break;
            case "verCarrito":
                verCarrito(request, response);
                break;
            case "catalogo":
                mostrarCatalogo(request, response);
                break;
            case "logout":
                logout(request, response);
                break;
            default:
                loginForm(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "registrar":
                registrarCliente(request, response);
                break;
            case "iniciarSesion":
                iniciarSesion(request, response);
                break;
            case "agregarCarrito":
                agregarCarrito(request, response);
                break;
            default:
                loginForm(request, response);
        }
    }

    private void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    private void registroForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registro.jsp");
        dispatcher.forward(request, response);
    }

    private void registrarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = UUID.randomUUID().toString();
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String contraseña = request.getParameter("contraseña");

        Cliente cliente = new Cliente(id, nombre, email, contraseña, "Regular", "cliente");
        clienteDAO.guardarCliente(cliente);
        response.sendRedirect("clienteCarrito?action=login");
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String contraseña = request.getParameter("contraseña");

        Cliente cliente = clienteDAO.obtenerClientePorEmail(email);

        if (cliente != null && cliente.getContraseña().equals(contraseña)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", DataMapper.toDTO(cliente));

            response.sendRedirect("clienteCarrito?action=catalogo");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void mostrarCatalogo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = productoDAO.obtenerTodosProductos();
        request.setAttribute("productos", productos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
        dispatcher.forward(request, response);
    }

    private void verCarrito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String clienteId = ((ClienteDTO) request.getSession().getAttribute("usuarioLogueado")).getId();
        Carrito carrito = carritoDAO.obtenerCarritoPorClienteId(clienteId);
        request.setAttribute("carrito", DataMapper.toDTO(carrito));
        RequestDispatcher dispatcher = request.getRequestDispatcher("carrito.jsp");
        dispatcher.forward(request, response);
    }

    private void agregarCarrito(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String clienteId = ((ClienteDTO) request.getSession().getAttribute("usuarioLogueado")).getId();
        String idProducto = request.getParameter("idProducto");
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Producto producto = productoDAO.obtenerProductoPorId(idProducto);
        Carrito carrito = carritoDAO.obtenerCarritoPorClienteId(clienteId);

        if (carrito == null) {
            carrito = new Carrito(clienteId);
        }

        carrito.agregarProducto(producto, cantidad);
        carritoDAO.guardarCarrito(carrito);

        response.sendRedirect("clienteCarrito?action=verCarrito");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("clienteCarrito?action=login");
    }
}
