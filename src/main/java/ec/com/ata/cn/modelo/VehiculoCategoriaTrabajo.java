/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "vehiculo_categoria_trabajo")
public class VehiculoCategoriaTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private VehiculoCategoriaTrabajoId vehiculoCategoriaTrabajoId;
    
    @MapsId("idVehiculo")
    @JoinColumn(name="id_vehiculo", referencedColumnName="id_vehiculo")
    @ManyToOne
    private Vehiculo vehiculo;
    
    @MapsId("idCategoriaForroTapiceria")
    @JoinColumn(name="id_categoria_forro_tapiceria", referencedColumnName="id_categoria" )
    @ManyToOne
    private Categoria categoriaForroTapiceria;
    
    @MapsId("idCategoriaPiso")
    @ManyToOne
    @PrimaryKeyJoinColumn(name="id_categoria_piso", referencedColumnName="id_categoria")
    private Categoria categoriaPiso;
   
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
     * @return the vehiculoCategoriaTrabajoId
     */
    public VehiculoCategoriaTrabajoId getVehiculoCategoriaTrabajoId() {
        return vehiculoCategoriaTrabajoId;
    }

    /**
     * @param vehiculoCategoriaTrabajoId the vehiculoCategoriaTrabajoId to set
     */
    public void setVehiculoCategoriaTrabajoId(VehiculoCategoriaTrabajoId vehiculoCategoriaTrabajoId) {
        this.vehiculoCategoriaTrabajoId = vehiculoCategoriaTrabajoId;
    }
    
    
}
