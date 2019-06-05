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
public class TrabajoCategoriaPrecioId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_trabajo")
    private Long idTrabajo;
    
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Override
    public String toString() {
        return "TrabajoCategoriaPrecioId{" + "idTrabajo=" + idTrabajo + ", idCategoria=" + idCategoria + '}';
    }
    
    

    /**
     * @return the idTrabajo
     */
    public Long getIdTrabajo() {
        return idTrabajo;
    }

    /**
     * @param idTrabajo the idTrabajo to set
     */
    public void setIdTrabajo(Long idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    /**
     * @return the idCategoria
     */
    public Long getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
   
}
