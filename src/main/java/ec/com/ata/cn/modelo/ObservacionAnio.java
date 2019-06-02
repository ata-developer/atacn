/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "observacion_anio")
public class ObservacionAnio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "observacion_anio_seq",
            sequenceName = "observacion_anio_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "observacion_anio_seq")
    @Column(name = "observacion_anio_seq")
    private Long idObservacionAnio;

    public Long getIdObservacionAnio() {
        return idObservacionAnio;
    }
    
    @Column(name = "observacion")
    private String observacion;

    public void setIdObservacionAnio(Long idObservacionAnio) {
        this.idObservacionAnio = idObservacionAnio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObservacionAnio != null ? idObservacionAnio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idObservacionAnio fields are not set
        if (!(object instanceof ObservacionAnio)) {
            return false;
        }
        ObservacionAnio other = (ObservacionAnio) object;
        if ((this.idObservacionAnio == null && other.idObservacionAnio != null) || (this.idObservacionAnio != null && !this.idObservacionAnio.equals(other.idObservacionAnio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.ObservacionAnio[ id=" + idObservacionAnio + " ]";
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
}
