/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "vehiculo_imagen")
public class VehiculoImagen implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private VehiculoImagenId vehiculoImagenId;

    @MapsId("idVehiculo")
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    @ManyToOne
    private Vehiculo vehiculo;

    @MapsId("idImagen")
    @JoinColumn(name = "id_imagen", referencedColumnName = "id_imagen")
    @ManyToOne
    private Imagen imagen;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.vehiculoImagenId);
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
        final VehiculoImagen other = (VehiculoImagen) obj;
        return Objects.equals(this.vehiculoImagenId, other.vehiculoImagenId);
    }

    @Override
    public String toString() {
        return "VehiculoImagen{" + "vehiculoImagenPrecioId=" + (vehiculoImagenId == null ? null : vehiculoImagenId.toString()) + "'}'";
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
     * @return the imagen
     */
    public Imagen getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the vehiculoImagenId
     */
    public VehiculoImagenId getVehiculoImagenId() {
        return vehiculoImagenId;
    }

    /**
     * @param vehiculoImagenId the vehiculoImagenId to set
     */
    public void setVehiculoImagenId(VehiculoImagenId vehiculoImagenId) {
        this.vehiculoImagenId = vehiculoImagenId;
    }

}
