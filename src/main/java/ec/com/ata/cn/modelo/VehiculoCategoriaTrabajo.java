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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table
public class VehiculoCategoriaTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "vehiculo_categoria_trabajo_seq",
            sequenceName = "vehiculo_categoria_trabajo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehiculo_categoria_trabajo_seq")
    @Column(name = "idvehiculo_categoria_trabajo")
    private Long idVehiculoCategoriaTrabajo;

    public Long getIdVehiculoCategoriaTrabajo() {
        return idVehiculoCategoriaTrabajo;
    }
    
    @ManyToOne
    private Vehiculo vehiculo;
    
    @ManyToOne
    private Categoria categoriaTrabajoAsiento;
    
    @ManyToOne
    private Categoria trabajoPiso;
    

    public void setIdVehiculoCategoriaTrabajo(Long idVehiculoCategoriaTrabajo) {
        this.idVehiculoCategoriaTrabajo = idVehiculoCategoriaTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculoCategoriaTrabajo != null ? idVehiculoCategoriaTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idVehiculoCategoriaTrabajo fields are not set
        if (!(object instanceof VehiculoCategoriaTrabajo)) {
            return false;
        }
        VehiculoCategoriaTrabajo other = (VehiculoCategoriaTrabajo) object;
        if ((this.idVehiculoCategoriaTrabajo == null && other.idVehiculoCategoriaTrabajo != null) || (this.idVehiculoCategoriaTrabajo != null && !this.idVehiculoCategoriaTrabajo.equals(other.idVehiculoCategoriaTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.VehiculoCategoriaTrabajo[ id=" + idVehiculoCategoriaTrabajo + " ]";
    }

    /**
     * @return the vehiculo
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the categoriaTrabajoAsiento
     */
    public Categoria getCategoriaTrabajoAsiento() {
        return categoriaTrabajoAsiento;
    }

    /**
     * @param categoriaTrabajoAsiento the categoriaTrabajoAsiento to set
     */
    public void setCategoriaTrabajoAsiento(Categoria categoriaTrabajoAsiento) {
        this.categoriaTrabajoAsiento = categoriaTrabajoAsiento;
    }

    /**
     * @return the trabajoPiso
     */
    public Categoria getTrabajoPiso() {
        return trabajoPiso;
    }

    /**
     * @param trabajoPiso the trabajoPiso to set
     */
    public void setTrabajoPiso(Categoria trabajoPiso) {
        this.trabajoPiso = trabajoPiso;
    }
    
}
