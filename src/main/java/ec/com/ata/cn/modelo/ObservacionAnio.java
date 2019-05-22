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

/**
 *
 * @author ATA1
 */
@Entity
public class ObservacionAnio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "observacionanio_seq",
            sequenceName = "observacionanio_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "observacionanio_seq")
    @Column(name = "id_observacionanio")
    private Long id;

    public Long getId() {
        return id;
    }
    
    @Column(name = "observacion")
    private String observacion;

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObservacionAnio)) {
            return false;
        }
        ObservacionAnio other = (ObservacionAnio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.ObservacionAnio[ id=" + id + " ]";
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
