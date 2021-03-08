/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ATA1
 */
@Embeddable
public class RolUrlId implements Serializable {

   

    private static final long serialVersionUID = 1L;

    @Column(name = "id_url")
    private Long idUrl;
    
    @Column(name = "id_rol")
    private Long idRol;

    @Override
    public String toString() {
        return "UrlRolId{" + "correo=" + getIdUrl() + ", idRol=" + idRol + '}';
    }
    
     /**
     * @return the idUrl
     */
    public Long getIdUrl() {
        return idUrl;
    }

    /**
     * @param idUrl the idUrl to set
     */
    public void setIdUrl(Long idUrl) {
        this.idUrl = idUrl;
    }

    

    /**
     * @return the idRol
     */
    public Long getIdRol() {
        return idRol;
    }

    /**
     * @param idRol the idRol to set
     */
    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }
   
}
