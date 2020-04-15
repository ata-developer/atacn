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
@Table(name = "periodo_establecimiento_fecha")
public class PeriodoEstablecimientoFecha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "pef_seq",
            sequenceName = "pef_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pef_seq")
    @Column(name = "id_pef")
    private Long idPEF;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @ManyToOne
    @JoinColumn(name="id_periodo", referencedColumnName="id_periodo")
    private Periodo periodo;
    
    @ManyToOne
    @JoinColumn(name="id_establecimiento", referencedColumnName="id_establecimiento")
    private Establecimiento establecimiento;
    
    @Embedded
    private GenericoEntidad genericoEntidad;   
    
    public PeriodoEstablecimientoFecha () {
        
    }

    public Long getIdPEF() {
        return idPEF;
    }

    public void setIdPEF(Long idPEF) {
        this.idPEF = idPEF;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPEF != null ? idPEF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPEF fields are not set
        if (!(object instanceof PeriodoEstablecimientoFecha)) {
            return false;
        }
        PeriodoEstablecimientoFecha other = (PeriodoEstablecimientoFecha) object;
        return !((this.idPEF == null && other.idPEF != null) || (this.idPEF != null && !this.idPEF.equals(other.idPEF)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.PEF[ id=" + idPEF + " ]";
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

    /**
     * @return the periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
}
