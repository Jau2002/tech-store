package co.edu.unbosque.model;

import java.util.HashMap;
import java.util.Map;

public class CarritoDTO {
    private String clienteId;
    private Map<ProductoDTO, Integer> productosDTO;

    public CarritoDTO(String clienteId) {
        this.clienteId = clienteId;
        this.productosDTO = new HashMap<>();
    }
    
	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public Map<ProductoDTO, Integer> getProductosDTO() {
		return productosDTO;
	}

	public void setProductosDTO(Map<ProductoDTO, Integer> productosDTO) {
		this.productosDTO = productosDTO;
	}
    
}
