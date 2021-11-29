package reto_1.reto.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reto_1.reto.modelo.Usuario;
import reto_1.reto.servicio.UsuarioServicio;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UsuarioControlador {

    /**
     * 
     * instanciacion de clase UsuarioServicio
     */
    @Autowired
    private UsuarioServicio usuarioServicio;

    /**
     * 
     * @return regresa lista con todos los usuario de la base de datos
     */
    @GetMapping("/all")
    public List<Usuario> getAll() {
        return usuarioServicio.getAll();
    }

    /**
     * 
     * @param usuario recibe los nuevos valores de un objeto Usuario
     * @return regresa los valores de nuevo Usuario, y los guarda en la base
     */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody Usuario usuario) {
        return usuarioServicio.salvar(usuario);
    }

    /**
     * 
     * @param email recibe un email
     * @return regresa los valores de un usuario con el email si existe en la base de datos
     */
    @GetMapping("/{email}")
    public boolean getByCorreo(@PathVariable("email") String email) {
        return usuarioServicio.getCorreo(email);
    }

    /**
     * 
     * @param email recibe un email
     * @param password recibe una contraseña
     * @return regresa todos los valores de usuario, si el email y la contraseña
     *  existen en la base de datos y si coinciden
     */
    @GetMapping("/{email}/{password}")
    public Optional<Usuario> validar(@PathVariable("email") String email, @PathVariable("password") String password) {
        return usuarioServicio.consultas(email, password);
    }

}
