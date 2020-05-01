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
@Table(name = "periodo_establecimiento")
public class PeriodoEstablecimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PeriodoEstablecimientoId periodoEstablecimientoId;

    @MapsId("idPeriodo")
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne
    private Periodo periodo;

    @MapsId("idEstablecimiento")
    @JoinColumn(name = "id_establecimiento", referencedColumnName = "id_establecimiento")
    @ManyToOne
    private Establecimiento establecimiento;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.periodoEstablecimientoId);
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
        final PeriodoEstablecimiento other = (PeriodoEstablecimiento) obj;
        if (!Objects.equals(this.periodoEstablecimientoId, other.periodoEstablecimientoId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PeriodoEstablecimientoPrecio{" + "periodoEstablecimientoPrecioId=" + (periodoEstablecimientoId == null ? null : periodoEstablecimientoId.toString()) + "'}'";
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

    /**
     * @return the periodoEstablecimientoId
     */
    public PeriodoEstablecimientoId getPeriodoEstablecimientoPrecioId() {
        return periodoEstablecimientoId;
    }

    /**
     * @param periodoEstablecimientoPrecioId the periodoEstablecimientoId to set
     */
    public void setPeriodoEstablecimientoPrecioId(PeriodoEstablecimientoId periodoEstablecimientoPrecioId) {
        this.periodoEstablecimientoId = periodoEstablecimientoPrecioId;
    }
}
