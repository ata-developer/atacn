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
public class PeriodoEstablecimientoId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_periodo")
    private Long idPeriodo;
    
    @Column(name = "id_establecimiento")
    private Long idEstablecimiento;

    @Override
    public String toString() {
        return "TrabajoCategoriaPrecioId{" + "idTrabajo=" + idPeriodo + ", idCategoria=" + idEstablecimiento + '}';
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
     * @return the idEstablecimiento
     */
    public Long getIdHorario() {
        return idEstablecimiento;
    }

    /**
     * @param idEstablecimiento the idEstablecimiento to set
     */
    public void setIdHorario(Long idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }
   
}
