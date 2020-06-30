/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.Date;
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
@Table ( name = "orden_fecha")
public class OrdenFecha implements Serializable {
  
    @Id
    @SequenceGenerator(
            name = "orden_fecha_seq",
            sequenceName = "orden_fecha_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orden_fecha_seq")
    @Column(name = "id_orden_fecha")
    private Long idOrdenFecha;    
    
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    
    @Column(name = "observacion")
    private String observacion;
    
    @ManyToOne
    @JoinColumn(name="id_vehiculo_trabajo", referencedColumnName="id_vehiculo_trabajo")
    private VehiculoTrabajo vehiculoTrabajo;
    
    @ManyToOne
    @JoinColumn(name="id_orden_vehiculo", referencedColumnName="id_orden_vehiculo")
    private OrdenVehiculo ordenVehiculo;
    
    @ManyToOne
    @JoinColumn(name="id_equipo", referencedColumnName="id_equipo")
    private Equipo equipo;
    
    @ManyToOne
    @JoinColumn(name="id_parqueadero", referencedColumnName="id_parqueadero")
    private Parqueadero parqueadero;
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    /**
     * @return the idOrdenFecha
     */
    public Long getIdOrdenFecha() {
        return idOrdenFecha;
    }

    /**
     * @param idOrdenFecha the idOrdenFecha to set
     */
    public void setIdOrdenFecha(Long idOrdenFecha) {
        this.idOrdenFecha = idOrdenFecha;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the vehiculoTrabajo
     */
    public VehiculoTrabajo getVehiculoTrabajo() {
        return vehiculoTrabajo;
    }

    /**
     * @param vehiculoTrabajo the vehiculoTrabajo to set
     */
    public void setVehiculoTrabajo(VehiculoTrabajo vehiculoTrabajo) {
        this.vehiculoTrabajo = vehiculoTrabajo;
    }

    /**
     * @return the ordenVehiculo
     */
    public OrdenVehiculo getOrdenVehiculo() {
        return ordenVehiculo;
    }

    /**
     * @param ordenVehiculo the ordenVehiculo to set
     */
    public void setOrdenVehiculo(OrdenVehiculo ordenVehiculo) {
        this.ordenVehiculo = ordenVehiculo;
    }

    /**
     * @return the equipo
     */
    public Equipo getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
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
     * @return the parqueadero
     */
    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    /**
     * @param parqueadero the parqueadero to set
     */
    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }
        
}
