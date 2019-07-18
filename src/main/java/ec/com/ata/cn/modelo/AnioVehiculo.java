/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "anio_vehiculo")
public class AnioVehiculo implements Serializable {
  
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "anio")
    private Long anio;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    public AnioVehiculo () {
        genericoEntidad = new GenericoEntidad();
    }

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
        if (!(object instanceof AnioVehiculo)) {
            return false;
        }
        AnioVehiculo other = (AnioVehiculo) object;
        if ((this.anio == null && other.anio != null) || (this.anio != null && !this.anio.equals(other.anio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.AnioVehiculo[ id=" + anio + " ]";
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
    
}
