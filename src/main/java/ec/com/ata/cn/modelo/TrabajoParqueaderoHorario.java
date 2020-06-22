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
@Table(name = "trabajo_parqueadero_horario")
public class TrabajoParqueaderoHorario implements Serializable {    
       
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "trabajo_parqueadero_horario_seq",
            sequenceName = "trabajo_parqueadero_horario_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trabajo_parqueadero_horario_seq")
    @Column(name = "id_trabajo_parqueadero_horario")
    private Long idTrabajoParqueaderoHorario;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicial;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
        
    @ManyToOne
    @JoinColumn(name="id_parqueadero", referencedColumnName="id_parqueadero")
    private Parqueadero parqueadero;
    
    @ManyToOne
    @JoinColumn(name="id_vehiculo_trabajo", referencedColumnName="id_vehiculo_trabajo")
    private VehiculoTrabajo vehiculoTrabajo;
    
    @ManyToOne
    @JoinColumn(name="id_orden_vehiculo", referencedColumnName="id_orden_vehiculo")
    private OrdenVehiculo ordenVehiculo;
    
    @Embedded
    private GenericoEntidad genericoEntidad;   
    
    public TrabajoParqueaderoHorario () {
        
    }

    public Long getIdPEF() {
        return idTrabajoParqueaderoHorario;
    }

    public void setIdPEF(Long idTrabajoParqueaderoHorario) {
        this.idTrabajoParqueaderoHorario = idTrabajoParqueaderoHorario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrabajoParqueaderoHorario != null ? idTrabajoParqueaderoHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idTrabajoParqueaderoHorario fields are not set
        if (!(object instanceof TrabajoParqueaderoHorario)) {
            return false;
        }
        TrabajoParqueaderoHorario other = (TrabajoParqueaderoHorario) object;
        return !((this.idTrabajoParqueaderoHorario == null && other.idTrabajoParqueaderoHorario != null) || (this.idTrabajoParqueaderoHorario != null && !this.idTrabajoParqueaderoHorario.equals(other.idTrabajoParqueaderoHorario)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.PEF[ id=" + idTrabajoParqueaderoHorario + " ]";
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
