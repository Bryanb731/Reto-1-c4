package reto_1.reto.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reto_1.reto.modelo.Usuario;

/**
 * 
 * @author Janus
 */
@Repository
public class UsuarioOperacionesRepositorio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> getAll() {
        return (List<Usuario>) usuarioRepositorio.findAll();
    }

    public Optional<Usuario> getUsuario(int id) {
        return usuarioRepositorio.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public Optional<Usuario> buscarCorreo(String email) {
        return usuarioRepositorio.findByEmail(email);
    }

    public Optional<Usuario> buscarContraseña(String password) {
        return usuarioRepositorio.findByPassword(password);
    }

}
