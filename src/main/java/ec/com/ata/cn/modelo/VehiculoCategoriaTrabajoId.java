/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author ATA1
 */
@Embeddable
public class VehiculoCategoriaTrabajoId implements Serializable {

    private Long idVehiculo;
    
    private Long idCategoriaForroTapiceria;
    
    private Long idCategoriaPiso;
    
}
