/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ATA1
 */
@Embeddable
public class VehiculoImagenId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_vehiculo")
    private Long idVehiculo;
    
    @Column(name = "id_imagen")
    private Long idImagen;

    @Override
    public String toString() {
        return "VehiculoImagenId{" + "idVehiculo=" + getIdVehiculo() + ", idImagen=" + getIdImagen() + '}';
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

    /**
     * @return the idImagen
     */
    public Long getIdImagen() {
        return idImagen;
    }

    /**
     * @param idImagen the idImagen to set
     */
    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }
    
    

   
   
}
