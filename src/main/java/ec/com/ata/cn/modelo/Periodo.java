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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ATA1
 */
@Entity
@Table
public class Periodo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "periodo_seq",
            sequenceName = "periodo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "periodo_seq")
    @Column(name = "id_periodo")
    private Long idPeriodo;

    @Column(name = "periodo")
    private String periodo;

    @Temporal(TemporalType.DATE)
    private Date inicio;

    @Temporal(TemporalType.DATE)
    private Date fin;
    
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    public Periodo() {
        genericoEntidad = new GenericoEntidad();
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPeriodo fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        return !((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Periodo[ id=" + idPeriodo + " ]";
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
     * @return the periodo
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the fin
     */
    public Date getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(Date fin) {
        this.fin = fin;
    }

    /**
     * @return the inicio
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
}
