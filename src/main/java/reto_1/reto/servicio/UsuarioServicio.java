package reto_1.reto.servicio;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reto_1.reto.modelo.Usuario;
import reto_1.reto.repositorio.UsuarioOperacionesRepositorio;

/**
 *
 * @author Janus
 */
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

    public Map<String, Object> validacion(String email, String password) {
        Map<String, Object> response = new LinkedHashMap<>();
        Integer id = 0;
        String correo = "";
        String contra = "";
        String nombre = "";

        for(Usuario user: usuarioOperacionesRepositorio.getAll()) {
            id = user.getId();
            correo = user.getEmail();
            contra = user.getPassword();
            nombre = user.getName();
        }

        response.put("id", id);
        response.put("email", correo);
        response.put("password", contra);
        response.put("name", nombre);

        System.out.println(contra + " " + password);
        System.out.println(correo + " " + email);


        // response.put("email", correo);
        // response.put("password", contra);
        // response.put("name", nombre);

        if(!email.equals(correo) || !password.equals(contra)) {
            response.put("id", "null");
            response.put("email", email);
            response.put("password", password);
            response.put("name", "NO DEFINIDO");
            return response;
        } else {
            System.out.println(response);
            return response;
        }
    }

}
