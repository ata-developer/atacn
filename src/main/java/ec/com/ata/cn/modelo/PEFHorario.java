/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "pef_horario")
public class PEFHorario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "pef_horario_seq",
            sequenceName = "pef_horario_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pef_horario_seq")
    @Column(name = "id_pef_horario")
    private Long idPEFHorario;
    
    @Column(name = "orden")
    private Long orden;
    
    
    @ManyToOne
    @JoinColumn(name="id_horario", referencedColumnName="id_horario")
    private Horario horario;
    
    @ManyToOne
    @JoinColumn(name="id_pef", referencedColumnName="id_pef")
    private PeriodoEstablecimientoFecha periodoEstablecimientoFecha;
    
    @OneToMany(mappedBy = "pEFHorario", fetch = FetchType.EAGER)
    @OrderBy("orden ASC")
    private List<HorarioParqueadero> listaHorarioParqueadero;
    
    @Embedded
    private GenericoEntidad genericoEntidad;   
    
    public PEFHorario () {
        
    }

    public Long getIdPEF() {
        return idPEFHorario;
    }

    public void setIdPEF(Long idPEFHorario) {
        this.idPEFHorario = idPEFHorario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPEFHorario != null ? idPEFHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPEFHorario fields are not set
        if (!(object instanceof PEFHorario)) {
            return false;
        }
        PEFHorario other = (PEFHorario) object;
        return !((this.idPEFHorario == null && other.idPEFHorario != null) || (this.idPEFHorario != null && !this.idPEFHorario.equals(other.idPEFHorario)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.PEF[ id=" + idPEFHorario + " ]";
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
     * @return the periodoEstablecimientoFecha
     */
    public PeriodoEstablecimientoFecha getPeriodoEstablecimientoFecha() {
        return periodoEstablecimientoFecha;
    }

    /**
     * @param periodoEstablecimientoFecha the periodoEstablecimientoFecha to set
     */
    public void setPeriodoEstablecimientoFecha(PeriodoEstablecimientoFecha periodoEstablecimientoFecha) {
        this.periodoEstablecimientoFecha = periodoEstablecimientoFecha;
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
     * @return the listaHorarioParqueadero
     */
    public List<HorarioParqueadero> getListaHorarioParqueadero() {
        return listaHorarioParqueadero;
    }

    /**
     * @param listaHorarioParqueadero the listaHorarioParqueadero to set
     */
    public void setListaHorarioParqueadero(List<HorarioParqueadero> listaHorarioParqueadero) {
        this.listaHorarioParqueadero = listaHorarioParqueadero;
    }
}
