package pe.cibertec.barber_shop.service;

import org.springframework.http.ResponseEntity;
import pe.cibertec.barber_shop.model.Role;

import java.util.Map;

public interface RoleService {
    ResponseEntity<Map<String, Object>> listarRoles();

    ResponseEntity<Map<String, Object>> listarRolesPorId(Long id);

    ResponseEntity<Map<String, Object>> agregarRoles(Role role);

    ResponseEntity<Map<String, Object>> editarRoles(Role role, Long id);

    ResponseEntity<Map<String, Object>> eliminarRoles(Long id);
}

