/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ATA1
 */
@Entity
public class FrenoDeMano implements Serializable {
  
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "anio")
    private Long anio;

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (anio != null ? anio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the anio fields are not set
        if (!(object instanceof FrenoDeMano)) {
            return false;
        }
        FrenoDeMano other = (FrenoDeMano) object;
        if ((this.anio == null && other.anio != null) || (this.anio != null && !this.anio.equals(other.anio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.AnioVehiculo[ id=" + anio + " ]";
    }
    
}
