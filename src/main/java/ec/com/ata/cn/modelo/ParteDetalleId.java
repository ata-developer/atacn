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
public class ParteDetalleId implements Serializable {

    /**
     * @return the idParte
     */
    public Long getIdParte() {
        return idParte;
    }

    /**
     * @param idParte the idParte to set
     */
    public void setIdParte(Long idParte) {
        this.idParte = idParte;
    }

    /**
     * @return the idDetalle
     */
    public Long getIdDetalle() {
        return idDetalle;
    }

    /**
     * @param idDetalle the idDetalle to set
     */
    public void setIdDetalle(Long idDetalle) {
        this.idDetalle = idDetalle;
    }

    private static final long serialVersionUID = 1L;

    @Column(name = "id_parte")
    private Long idParte;
    
    @Column(name = "id_detalle")
    private Long idDetalle;
    
    
   
}
