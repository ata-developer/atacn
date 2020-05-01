/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
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

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "horario_parqueadero")
public class HorarioParqueadero implements Serializable {    

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "horario_parqueadero_seq",
            sequenceName = "horario_parqueadero_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "horario_parqueadero_seq")
    @Column(name = "id_horario_parqueadero")
    private Long idHorarioParqueadero;
    
    @Column(name = "orden")
    private Long orden;
    
    @ManyToOne
    @JoinColumn(name="id_horario", referencedColumnName="id_horario")
    private Horario horario;
    
    @ManyToOne
    @JoinColumn(name="id_pef_horario", referencedColumnName="id_pef_horario")
    private PEFHorario pEFHorario;
    
    @ManyToOne
    @JoinColumn(name="id_parqueadero", referencedColumnName="id_parqueadero")
    private Parqueadero parqueadero;
    
    @ManyToOne
    @JoinColumn(name="id_vehiculo_trabajo", referencedColumnName="id_vehiculo_trabajo")
    private VehiculoTrabajo vehiculoTrabajo;
    
    @Embedded
    private GenericoEntidad genericoEntidad;   
    
    public HorarioParqueadero () {
        
    }

    public Long getIdPEF() {
        return idHorarioParqueadero;
    }

    public void setIdPEF(Long idHorarioParqueadero) {
        this.idHorarioParqueadero = idHorarioParqueadero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorarioParqueadero != null ? idHorarioParqueadero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idHorarioParqueadero fields are not set
        if (!(object instanceof HorarioParqueadero)) {
            return false;
        }
        HorarioParqueadero other = (HorarioParqueadero) object;
        return !((this.idHorarioParqueadero == null && other.idHorarioParqueadero != null) || (this.idHorarioParqueadero != null && !this.idHorarioParqueadero.equals(other.idHorarioParqueadero)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.PEF[ id=" + idHorarioParqueadero + " ]";
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
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
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
     * @return the orden
     */
    public Long getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Long orden) {
        this.orden = orden;
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
     * @return the pEFHorario
     */
    public PEFHorario getpEFHorario() {
        return pEFHorario;
    }

    /**
     * @param pEFHorario the pEFHorario to set
     */
    public void setpEFHorario(PEFHorario pEFHorario) {
        this.pEFHorario = pEFHorario;
    }

}
