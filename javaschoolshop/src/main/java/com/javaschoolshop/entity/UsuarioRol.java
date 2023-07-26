package com.javaschoolshop.entity;

import javax.persistence.*;

@Entity
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioRolId;
    @ManyToOne(fetch = FetchType.EAGER) // Everytime I search for user, role is fetched without additonal queries
    private Usuario usuario;
    @ManyToOne
    private Rol rol;
    public Long getUsuarioRolId() {
        return usuarioRolId;
    }
    public void setUsuarioRolId(Long usuarioRolId) {
        this.usuarioRolId = usuarioRolId;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
