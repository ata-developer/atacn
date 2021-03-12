/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "usuario_sistema")
public class UsuarioSistema implements Serializable {   

    
    
    @Id
    @Column(name = "correo", length = 60)
    private String correo;
    
    @Column(name = "contrasenia", length = 100)
    private String contrasenia;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @Embedded
    private GenericoEntidad genericoEntidad;

    public UsuarioSistema() {
        genericoEntidad = new GenericoEntidad();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.correo);
        hash = 79 * hash + Objects.hashCode(this.usuario);
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
        final UsuarioSistema other = (UsuarioSistema) obj;
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        return true;
    }
    
    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
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
     * @return the genericoEntidad
     */
    public GenericoEntidad getGenericoEntidad() {
        return genericoEntidad;
    }

    /**
     * @param genericoEntidad the genericoEntidad to set
     */
    public void setGenericoEntidad(GenericoEntidad genericoEntidad) {
        this.genericoEntidad = genericoEntidad;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
   

}
