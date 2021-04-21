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
import javax.persistence.Table;
import javax.persistence.Transient;

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
    
    @MapsId("idTrabajoCategoriaPrecio")
    @JoinColumn(name="id_trabajo_categoria_precio", referencedColumnName="id_trabajo_categoria_precio")
    @ManyToOne
    private TrabajoCategoriaPrecio trabajoCategoriaPrecio;
    
    @Transient
    private boolean seleccionar;
   
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

   /**
     * @return the trabajoCategoriaPrecio
     */
    public TrabajoCategoriaPrecio getTrabajoCategoriaPrecio() {
        return trabajoCategoriaPrecio;
    }

    /**
     * @param trabajoCategoriaPrecio the trabajoCategoriaPrecio to set
     */
    public void setTrabajoCategoriaPrecio(TrabajoCategoriaPrecio trabajoCategoriaPrecio) {
        this.trabajoCategoriaPrecio = trabajoCategoriaPrecio;
    }
    
    
    /**
     * @return the seleccionar
     */
    public boolean isSeleccionar() {
        return seleccionar;
    }

    /**
     * @param seleccionar the seleccionar to set
     */
    public void setSeleccionar(boolean seleccionar) {
        this.seleccionar = seleccionar;
    }

    
}
