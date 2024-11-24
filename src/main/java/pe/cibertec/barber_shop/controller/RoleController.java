package pe.cibertec.barber_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.barber_shop.model.Role;
import pe.cibertec.barber_shop.service.RoleService;

import java.util.Map;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private RoleService roleDao;

    @GetMapping
    public ResponseEntity<Map<String, Object>> listar() {
        return roleDao.listarRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> listarPorID (@PathVariable Long id) {
        return roleDao.listarRolesPorId(id);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> agregar(@Validated @RequestBody Role role){
        return roleDao.agregarRoles(role);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody Role role,@PathVariable Long id){
        return roleDao.editarRoles(role,id);
    }

    @DeleteMapping
            ("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Long id){
        return roleDao.eliminarRoles(id);
    }
}
