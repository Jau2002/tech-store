package co.edu.unbosque.model;

import java.io.Serializable;

public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
    private String nombre;
    private String email;
    private String contrase�a;
    private String nivelMembresia;
    private String rol; // "cliente" o "admin"

    
    public Cliente(String id, String nombre, String email, String contrase�a, String nivelMembresia, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrase�a = contrase�a;
        this.nivelMembresia = nivelMembresia;
        this.rol = rol;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}


	public String getNivelMembresia() {
		return nivelMembresia;
	}

	public void setNivelMembresia(String nivelMembresia) {
		this.nivelMembresia = nivelMembresia;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}

