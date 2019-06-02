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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "vehiculo_categoria_trabajo")
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
    @Column(name = "id_vehiculo_categoria_trabajo")
    private Long idVehiculoCategoriaTrabajo;

    public Long getIdVehiculoCategoriaTrabajo() {
        return idVehiculoCategoriaTrabajo;
    }
    
    @ManyToOne
    @PrimaryKeyJoinColumn(name="id_vehiculo", referencedColumnName="id_vehiculo")
    private Vehiculo vehiculo;
    
    @ManyToOne
    @PrimaryKeyJoinColumn(name="id_categoria_forro_tapiceria", referencedColumnName="id_categoria")
    private Categoria categoriaForroTapiceria;
    
    @ManyToOne
    @PrimaryKeyJoinColumn(name="id_categoria_piso", referencedColumnName="id_categoria")
    private Categoria categoriaPiso;
    

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
     * @return the categoriaForroTapiceria
     */
    public Categoria getCategoriaForroTapiceria() {
        return categoriaForroTapiceria;
    }

    /**
     * @param categoriaForroTapiceria the categoriaForroTapiceria to set
     */
    public void setCategoriaForroTapiceria(Categoria categoriaForroTapiceria) {
        this.categoriaForroTapiceria = categoriaForroTapiceria;
    }

    /**
     * @return the categoriaPiso
     */
    public Categoria getCategoriaPiso() {
        return categoriaPiso;
    }

    /**
     * @param categoriaPiso the categoriaPiso to set
     */
    public void setCategoriaPiso(Categoria categoriaPiso) {
        this.categoriaPiso = categoriaPiso;
    }
    
}
