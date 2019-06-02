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
public class VehiculoCategoriaTrabajoId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Column(name = "id_vehiculo")
    private Long idVehiculo;
    
    @Column(name = "id_categoria_forro_tapiceria")
    private Long idCategoriaForroTapiceria;
    
    @Column(name = "id_categoria_piso")
    private Long idCategoriaPiso;

    /**
     * @return the idVehiculo
     */
    public Long getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * @param idVehiculo the idVehiculo to set
     */
    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    /**
     * @return the idCategoriaForroTapiceria
     */
    public Long getIdCategoriaForroTapiceria() {
        return idCategoriaForroTapiceria;
    }

    /**
     * @param idCategoriaForroTapiceria the idCategoriaForroTapiceria to set
     */
    public void setIdCategoriaForroTapiceria(Long idCategoriaForroTapiceria) {
        this.idCategoriaForroTapiceria = idCategoriaForroTapiceria;
    }

    /**
     * @return the idCategoriaPiso
     */
    public Long getIdCategoriaPiso() {
        return idCategoriaPiso;
    }
    
}
