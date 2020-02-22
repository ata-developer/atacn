/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
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
@Table(name = "parte_detalle")
public class ParteDetalle implements Serializable {   

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ParteDetalleId parteDetalleId;

    @MapsId("idParte")
    @JoinColumn(name = "id_parte", referencedColumnName = "id_parte")
    @ManyToOne
    private Parte parte;

    @MapsId("idDetalle")
    @JoinColumn(name = "id_detalle", referencedColumnName = "id_detalle")
    @ManyToOne
    private Detalle detalle;
    
    
    
    /**
     * @return the parteDetalleId
     */
    public ParteDetalleId getParteDetalleId() {
        return parteDetalleId;
    }

    /**
     * @param parteDetalleId the parteDetalleId to set
     */
    public void setParteDetalleId(ParteDetalleId parteDetalleId) {
        this.parteDetalleId = parteDetalleId;
    }

    /**
     * @return the parte
     */
    public Parte getParte() {
        return parte;
    }

    /**
     * @param parte the parte to set
     */
    public void setParte(Parte parte) {
        this.parte = parte;
    }

    /**
     * @return the detalle
     */
    public Detalle getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }
    
}
