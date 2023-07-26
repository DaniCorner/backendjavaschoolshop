package com.javaschoolshop.service;

import com.javaschoolshop.entity.Usuario;
import com.javaschoolshop.entity.UsuarioRol;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface UsuarioService {
    public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception;
    public Usuario obtenerUsuario(String username);
    public void eliminarUsuario(Long usuarioId);
    Usuario guardarUsuario(Usuario usuarioExistente);
    String saveProfileImage(MultipartFile file);
}
