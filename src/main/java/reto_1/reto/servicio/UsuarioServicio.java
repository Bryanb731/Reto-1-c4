package reto_1.reto.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reto_1.reto.modelo.Usuario;
import reto_1.reto.repositorio.UsuarioOperacionesRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioOperacionesRepositorio usuarioOperacionesRepositorio;

    public List<Usuario> getAll() {
        return usuarioOperacionesRepositorio.getAll();
    }

    public Optional<Usuario> getUsuario(int id) {
        return usuarioOperacionesRepositorio.getUsuario(id);
    }

    public Usuario salvar(Usuario usuario) {
        if(usuario.getId() == null) {
            usuarioOperacionesRepositorio.salvar(usuario);
        }else {
            Optional<Usuario> consultar = usuarioOperacionesRepositorio.getUsuario(usuario.getId());
            if(consultar.isEmpty()) {
                return usuarioOperacionesRepositorio.salvar(usuario);
            }else {
                return usuario;
            }
        }
        return usuario;
    }

    public boolean getCorreo(String email) {
        Optional<Usuario> correo = usuarioOperacionesRepositorio.buscarCorreo(email);
        if(!correo.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Optional<Usuario> consultas(String email, String password) {
        Usuario usuario = new Usuario(email, password, "NO DEFINIDO");
        Optional<Usuario> buscar = usuarioOperacionesRepositorio.buscarContraseña(password);
        Optional<Usuario> user = Optional.of(usuario);
        String correo = email;

        if(!buscar.isEmpty()) {
            if(!buscar.get().getEmail().equals(correo)) {
                return user;
            } else {
                return buscar;
            }
        } 
        return user;
    }

}
