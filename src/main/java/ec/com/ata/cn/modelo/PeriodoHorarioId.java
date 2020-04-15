/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ATA1
 */
@Embeddable
public class PeriodoHorarioId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_periodo")
    private Long idPeriodo;
    
    @Column(name = "id_horario")
    private Long idHorario;

    @Override
    public String toString() {
        return "TrabajoCategoriaPrecioId{" + "idTrabajo=" + idPeriodo + ", idCategoria=" + idHorario + '}';
    }
    
    

    /**
     * @return the idPeriodo
     */
    public Long getIdPeriodo() {
        return idPeriodo;
    }

    /**
     * @param idPeriodo the idPeriodo to set
     */
    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    /**
     * @return the idHorario
     */
    public Long getIdHorario() {
        return idHorario;
    }

    /**
     * @param idHorario the idHorario to set
     */
    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }
   
}
