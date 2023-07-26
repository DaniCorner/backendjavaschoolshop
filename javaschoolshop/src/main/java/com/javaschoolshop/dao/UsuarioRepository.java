package com.javaschoolshop.dao;

import com.javaschoolshop.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    //SELECT * FROM usuarios u WHERE u.username = 'o';
    public Usuario findByUsername(String username);
}
