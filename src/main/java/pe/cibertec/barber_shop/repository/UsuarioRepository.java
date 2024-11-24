package pe.cibertec.barber_shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.cibertec.barber_shop.model.User;


@Repository
public interface UsuarioRepository extends JpaRepository<User, Long>{

    Optional<User> findOneByEmail(String email);
    Optional<User> findByResetPasswordToken(String token);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
