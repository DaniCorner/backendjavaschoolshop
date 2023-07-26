package com.javaschoolshop.service;

import com.javaschoolshop.entity.Usuario;
import com.javaschoolshop.entity.UsuarioRol;
import com.javaschoolshop.entity.Rol;
import com.javaschoolshop.dao.RolRepository;
import com.javaschoolshop.dao.UsuarioRepository;
import com.javaschoolshop.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Override
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        Usuario usuarioLocal = usuarioRepository.findByUsername(usuario.getUsername());
        if(usuarioLocal != null){
            System.out.println("User already exist");
            throw new Exception("User already exist in the database");
        }
        else{
            for(UsuarioRol usuarioRol:usuarioRoles){
                rolRepository.save(usuarioRol.getRol());
            }
            usuario.getUsuarioRoles().addAll(usuarioRoles);
            usuarioLocal = usuarioRepository.save(usuario);
        }
        return usuarioLocal;
    }
    @Override
    public Usuario obtenerUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }
    @Override
    public void eliminarUsuario(Long usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }
    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    private static final String UPLOAD_DIR = "C:\\Users\\Utilizateur\\OneDrive\\Escritorio\\Vidahumana\\FRONTENDBUENO\\03-frontend\\javaschoolshop\\src\\assets\\profiles";

    //TO SAVE THE PROFILE IMAGE
    public String saveProfileImage(MultipartFile file) {
        if (file.isEmpty()) {
            return "Error: Profile image is empty.";
        }
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename(); //GetOriginal is a MultipartF method
            Path filePath = Paths.get(UPLOAD_DIR, fileName); //Path object, represents location
            Files.copy(file.getInputStream(), filePath); //Copy the object in the path, to be stored in my app
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Failed to save profile image.";
        }
    }
}