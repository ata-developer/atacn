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
public class TipoTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "tipotrabajo_seq",
            sequenceName = "tipotrabajo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tipotrabajo_seq")
    @Column(name = "id_tipotrabajo")
    private Long idTipoTrabajo;
    
    @Column(name = "tipo")
    private String tipo;

    public Long getIdTipoTrabajo() {
        return idTipoTrabajo;
    }

    public void setIdTipoTrabajo(Long idTipoTrabajo) {
        this.idTipoTrabajo = idTipoTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoTrabajo != null ? idTipoTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idTipoTrabajo fields are not set
        if (!(object instanceof TipoTrabajo)) {
            return false;
        }
        TipoTrabajo other = (TipoTrabajo) object;
        if ((this.idTipoTrabajo == null && other.idTipoTrabajo != null) || (this.idTipoTrabajo != null && !this.idTipoTrabajo.equals(other.idTipoTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.TipoTrabajo[ id=" + idTipoTrabajo + " ]";
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
