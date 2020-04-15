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
@Table(name = "periodo_horario")
public class PeriodoHorario implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PeriodoHorarioId periodoHorarioId;

    @MapsId("idPeriodo")
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne
    private Periodo periodo;

    @MapsId("idHorario")
    @JoinColumn(name = "id_horario", referencedColumnName = "id_horario")
    @ManyToOne
    private Horario horario;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.periodoHorarioId);
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
        final PeriodoHorario other = (PeriodoHorario) obj;
        if (!Objects.equals(this.periodoHorarioId, other.periodoHorarioId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PeriodoHorarioPrecio{" + "periodoHorarioPrecioId=" + (periodoHorarioId == null ? null : periodoHorarioId.toString()) + "'}'";
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
     * @return the periodoHorarioId
     */
    public PeriodoHorarioId getPeriodoHorarioPrecioId() {
        return periodoHorarioId;
    }

    /**
     * @param periodoHorarioPrecioId the periodoHorarioId to set
     */
    public void setPeriodoHorarioPrecioId(PeriodoHorarioId periodoHorarioPrecioId) {
        this.periodoHorarioId = periodoHorarioPrecioId;
    }

}
