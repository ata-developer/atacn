/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ATA1
 */

@Entity
@Table(name = "orden_vehiculo")
public class OrdenVehiculo implements Serializable {    
    
    @Id
    @SequenceGenerator(
            name = "orden_vehiculo_seq",
            sequenceName = "orden_vehiculo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orden_vehiculo_seq")
    @Column(name = "id_orden_vehiculo")
    private Long idOrdenVehiculo;
    
    @Column(name = "descripcion",unique=true)
    private String descripcion;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @ManyToOne
    @JoinColumn(name="id_orden", referencedColumnName="id_orden")
    private Orden orden;
    
    @ManyToOne
    @JoinColumn(name="id_vehiculo", referencedColumnName="id_vehiculo")
    private Vehiculo vehiculo;
    
    @Column(name = "fecha_registro_vehiculo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistroVehiculo;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idOrdenVehiculo);
        hash = 53 * hash + Objects.hashCode(this.descripcion);
        hash = 53 * hash + Objects.hashCode(this.genericoEntidad);
        hash = 53 * hash + Objects.hashCode(this.orden);
        hash = 53 * hash + Objects.hashCode(this.vehiculo);
        hash = 53 * hash + Objects.hashCode(this.fechaRegistroVehiculo);
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
        final OrdenVehiculo other = (OrdenVehiculo) obj;
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.idOrdenVehiculo, other.idOrdenVehiculo)) {
            return false;
        }
        if (!Objects.equals(this.genericoEntidad, other.genericoEntidad)) {
            return false;
        }
        if (!Objects.equals(this.orden, other.orden)) {
            return false;
        }
        if (!Objects.equals(this.vehiculo, other.vehiculo)) {
            return false;
        }
        if (!Objects.equals(this.fechaRegistroVehiculo, other.fechaRegistroVehiculo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrdenVehiculo{" + "idOrdenVehiculo=" + idOrdenVehiculo + ", descripcion=" + descripcion + ", genericoEntidad=" + genericoEntidad + ", orden=" + orden + ", vehiculo=" + vehiculo + ", fechaRegistroVehiculo=" + fechaRegistroVehiculo + '}';
    }

    
    
    public OrdenVehiculo () {
        genericoEntidad = new GenericoEntidad();
    }
    
    public Long getIdOrdenVehiculo() {
        return idOrdenVehiculo;
    }

    public void setIdOrdenVehiculo(Long idOrdenVehiculo) {
        this.idOrdenVehiculo = idOrdenVehiculo;
    }


    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
     * @return the orden
     */
    public Orden getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
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
     * @return the fechaRegistroVehiculo
     */
    public Date getFechaRegistroVehiculo() {
        return fechaRegistroVehiculo;
    }

    /**
     * @param fechaRegistroVehiculo the fechaRegistroVehiculo to set
     */
    public void setFechaRegistroVehiculo(Date fechaRegistroVehiculo) {
        this.fechaRegistroVehiculo = fechaRegistroVehiculo;
    }
}
