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
@Table(name = "lugar_vehiculo_trabajo")
public class LugarVehiculoTrabajo implements Serializable {   

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "lugar_vehiculo_trabajo_seq",
            sequenceName = "lugar_vehiculo_trabajo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "lugar_vehiculo_trabajo_seq")
    @Column(name = "id_lugar_vehiculo_trabajo")
    private Long idLugarVehiculoTrabajo;

    @Column(name = "lugar_vehiculo_trabajo")
    private String lugarVehiculoTrabajo;
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    public LugarVehiculoTrabajo () {
        genericoEntidad = new GenericoEntidad();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.idLugarVehiculoTrabajo);
        hash = 19 * hash + Objects.hashCode(this.lugarVehiculoTrabajo);
        hash = 19 * hash + Objects.hashCode(this.genericoEntidad);
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
        final LugarVehiculoTrabajo other = (LugarVehiculoTrabajo) obj;
        if (!Objects.equals(this.lugarVehiculoTrabajo, other.lugarVehiculoTrabajo)) {
            return false;
        }
        if (!Objects.equals(this.idLugarVehiculoTrabajo, other.idLugarVehiculoTrabajo)) {
            return false;
        }
        if (!Objects.equals(this.genericoEntidad, other.genericoEntidad)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.LugarVehiculoTrabajo[ id=" + getIdLugarVehiculoTrabajo() + " ]";
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
     * @return the idLugarVehiculoTrabajo
     */
    public Long getIdLugarVehiculoTrabajo() {
        return idLugarVehiculoTrabajo;
    }

    /**
     * @param idLugarVehiculoTrabajo the idLugarVehiculoTrabajo to set
     */
    public void setIdLugarVehiculoTrabajo(Long idLugarVehiculoTrabajo) {
        this.idLugarVehiculoTrabajo = idLugarVehiculoTrabajo;
    }

    /**
     * @return the lugarVehiculoTrabajo
     */
    public String getLugarVehiculoTrabajo() {
        return lugarVehiculoTrabajo;
    }

    /**
     * @param lugarVehiculoTrabajo the lugarVehiculoTrabajo to set
     */
    public void setLugarVehiculoTrabajo(String lugarVehiculoTrabajo) {
        this.lugarVehiculoTrabajo = lugarVehiculoTrabajo;
    }
}
