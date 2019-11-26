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
public class GrupoPrecioParteCategoriaVehiculoId implements Serializable {

    private static final long serialVersionUID = 1L;   
    
    @Column(name = "id_grupo_precio")
    private Long idGrupoPrecio;
    
    @Column(name = "id_categoria")
    private Long idCategoria;
    
    @Column(name = "id_parte")
    private Long idParte;
    
    @Column(name = "id_vehiculo")
    private Long idVehiculo;

    /**
     * @return the idGrupoPrecio
     */
    public Long getIdGrupoPrecio() {
        return idGrupoPrecio;
    }

    /**
     * @param idGrupoPrecio the idGrupoPrecio to set
     */
    public void setIdGrupoPrecio(Long idGrupoPrecio) {
        this.idGrupoPrecio = idGrupoPrecio;
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

    /**
     * @return the idParte
     */
    public Long getIdParte() {
        return idParte;
    }

    /**
     * @param idParte the idParte to set
     */
    public void setIdParte(Long idParte) {
        this.idParte = idParte;
    }

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
    
    

    
   
}
