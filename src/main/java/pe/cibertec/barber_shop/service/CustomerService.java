package pe.cibertec.barber_shop.service;

import org.springframework.http.ResponseEntity;
import pe.cibertec.barber_shop.model.Customer;

import java.util.Map;

public interface CustomerService {
    ResponseEntity<Map<String, Object>> listarClientes();
    ResponseEntity<Map<String, Object>> listarClientesPorId(Long id);
    ResponseEntity<Map<String, Object>> agregarCliente(Customer customer);
    ResponseEntity<Map<String, Object>> editarCliente(Customer customer, Long id);
    ResponseEntity<Map<String, Object>> eliminarCliente(Long id);
}
