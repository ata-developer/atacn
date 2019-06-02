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
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "trabajo_categoria_precio")
@IdClass(VehiculoCategoriaTrabajoId.class)
public class VehiculoCategoriaTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id_vehiculo")
    private Long idVehiculo;
    
    @Id
    @Column(name = "id_categoria_forro_tapiceria")
    private Long idCategoriaForroTapiceria;
    
    @Id
    @Column(name = "id_categoria_piso")
    private Long idCategoriaPiso;
    
    
    @ManyToOne
    @PrimaryKeyJoinColumn(name="id_vehiculo", referencedColumnName="id_vehiculo")
    private Vehiculo vehiculo;
    
    @ManyToOne
    @PrimaryKeyJoinColumn(name="id_categoria_forro_tapiceria", referencedColumnName="id_categoria")
    private Categoria categoriaForroTapiceria;
    
    @ManyToOne
    @PrimaryKeyJoinColumn(name="id_categoria_piso", referencedColumnName="id_categoria")
    private Categoria categoriaPiso;
    
    

    /**
     * @return the idCategoriaForroTapiceria
     */
    public Long getIdCategoriaForroTapiceria() {
        return idCategoriaForroTapiceria;
    }

    /**
     * @param idCategoriaForroTapiceria the idCategoriaForroTapiceria to set
     */
    public void setIdCategoriaForroTapiceria(Long idCategoriaForroTapiceria) {
        this.idCategoriaForroTapiceria = idCategoriaForroTapiceria;
    }

    /**
     * @return the idCategoriaPiso
     */
    public Long getIdCategoriaPiso() {
        return idCategoriaPiso;
    }

    /**
     * @param idCategoriaPiso the idCategoriaPiso to set
     */
    public void setIdCategoriaPiso(Long idCategoriaPiso) {
        this.idCategoriaPiso = idCategoriaPiso;
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

    /**
     * @return the idVehiculo
     */
    public Long getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * @param idVehiculo the idVehiculo to set
     */
    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    
    
}
