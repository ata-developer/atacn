/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "usuario_rol")
public class UsuarioRol implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UsuarioRolId usuarioRolId;

    @MapsId("idUsuario")
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuario;

    @MapsId("idRol")
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne
    private Rol rol;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.usuarioRolId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioRol other = (UsuarioRol) obj;
        if (!Objects.equals(this.usuarioRolId, other.usuarioRolId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioRolPrecio{" + "usuarioRolPrecioId=" + (usuarioRolId == null ? null : usuarioRolId.toString()) + "'}'";
    }
    
    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }

    /**
     * @return the usuarioRolId
     */
    public UsuarioRolId getUsuarioRolPrecioId() {
        return usuarioRolId;
    }

    /**
     * @param usuarioRolPrecioId the usuarioRolId to set
     */
    public void setUsuarioRolPrecioId(UsuarioRolId usuarioRolPrecioId) {
        this.usuarioRolId = usuarioRolPrecioId;
    }

}
