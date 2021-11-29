package reto_1.reto.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import reto_1.reto.modelo.Usuario;

/**
 * 
 * @author Janus
 */
public interface UsuarioRepositorio extends CrudRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM usuarios WHERE user_email = ?1", nativeQuery = true)
    Optional<Usuario> findByEmail(String email);

    @Query(value = "SELECT * FROM usuarios WHERE user_password = ?1", nativeQuery = true)
    Optional<Usuario> findByPassword(String password);

}
