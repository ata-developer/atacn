/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author ATA1
 */
@Entity
public class VehiculoCategoriaTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }
    
    @ManyToOne
    private Vehiculo vehiculo;
    
    @ManyToOne
    private Categoria categoriaForrosTapiceria;
    
    @ManyToOne
    private Categoria pisoIntegroProteccion;
    

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
        if (!(object instanceof VehiculoCategoriaTrabajo)) {
            return false;
        }
        VehiculoCategoriaTrabajo other = (VehiculoCategoriaTrabajo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.VehiculoCategoriaTrabajo[ id=" + id + " ]";
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
     * @return the categoriaForrosTapiceria
     */
    public Categoria getCategoriaForrosTapiceria() {
        return categoriaForrosTapiceria;
    }

    /**
     * @param categoriaForrosTapiceria the categoriaForrosTapiceria to set
     */
    public void setCategoriaForrosTapiceria(Categoria categoriaForrosTapiceria) {
        this.categoriaForrosTapiceria = categoriaForrosTapiceria;
    }

    /**
     * @return the pisoIntegroProteccion
     */
    public Categoria getPisoIntegroProteccion() {
        return pisoIntegroProteccion;
    }

    /**
     * @param pisoIntegroProteccion the pisoIntegroProteccion to set
     */
    public void setPisoIntegroProteccion(Categoria pisoIntegroProteccion) {
        this.pisoIntegroProteccion = pisoIntegroProteccion;
    }
    
}
