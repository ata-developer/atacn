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
@Table(name = "rol_url")
public class RolUrl implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RolUrlId rolUrlId;

    @MapsId("idUrl")
    @JoinColumn(name = "id_url", referencedColumnName = "id_url")
    @ManyToOne
    private Url url;

    @MapsId("idRol")
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    @ManyToOne
    private Rol rol;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.getRolUrlId());
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
        final RolUrl other = (RolUrl) obj;
        if (!Objects.equals(this.rolUrlId, other.rolUrlId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UsuarioRolPrecio{" + "urlRolPrecioId=" + (getRolUrlId() == null ? null : getRolUrlId().toString()) + "'}'";
    }
    
   /**
     * @return the rolUrlId
     */
    public RolUrlId getRolUrlId() {
        return rolUrlId;
    }

    /**
     * @param rolUrlId the rolUrlId to set
     */
    public void setRolUrlId(RolUrlId rolUrlId) {
        this.rolUrlId = rolUrlId;
    }

    /**
     * @return the url
     */
    public Url getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(Url url) {
        this.url = url;
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


}
