package com.javaschoolshop.controller;

import com.javaschoolshop.dao.UsuarioRepository;
import com.javaschoolshop.entity.Rol;
import com.javaschoolshop.entity.Usuario;
import com.javaschoolshop.entity.UsuarioRol;
import com.javaschoolshop.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPerfil("default.png");
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        Rol rol = new Rol();
        rol.setRolId(2L);
        rol.setRolNombre("NORMAL");

        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        usuarioRoles.add(usuarioRol);
        return usuarioService.guardarUsuario(usuario,usuarioRoles);
    }
    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }
    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

    @PutMapping("/{username}")
    public Usuario actualizarUsuario(@PathVariable("username") String username, @RequestBody Usuario usuarioActualizado) throws Exception {
        Usuario usuarioExistente = usuarioService.obtenerUsuario(username);
        if (usuarioExistente == null) {
            throw new Exception("User doesn't exist");
        }
        // Actualiza los campos relevantes del usuario existente con los valores del usuario actualizado
        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setApellido(usuarioActualizado.getApellido());
        usuarioExistente.setEmail(usuarioActualizado.getEmail());
        usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
        usuarioExistente.setPassword(usuarioActualizado.getPassword());
        usuarioExistente.setPerfil(usuarioActualizado.getPerfil());
        // Guarda el usuario actualizado en la base de datos
        return usuarioService.guardarUsuario(usuarioExistente);
    }

    @PostMapping("/{id}/perfil")
    public ResponseEntity<Map<String, String>> uploadProfileImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        String perfilURL = usuarioService.saveProfileImage(file);

        // Update the Usuario entity with the profile image URL
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setPerfil(perfilURL);
            usuarioRepository.save(usuario);
            // Create a JSON object to hold the imageURL
            Map<String, String> responseData = new HashMap<>();
            responseData.put("imageURL", perfilURL);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
