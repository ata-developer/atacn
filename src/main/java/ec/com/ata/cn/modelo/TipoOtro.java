/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author ATA1
 */
@Entity
public class TipoOtro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "tipo_otro_seq",
            sequenceName = "tipo_otro_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tipo_otro_seq")
    @Column(name = "id_tipo_otro")
    private Long idTipoOtro;
    
    @Column(name = "tipo_otro")
    private String tipoOtro;

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.TipoOtro[ id=" + idTipoOtro + " ]";
    }

    /**
     * @return the tipoOtro
     */
    public String getTipoOtro() {
        return tipoOtro;
    }

    /**
     * @param tipoOtro the tipoOtro to set
     */
    public void setTipoOtro(String tipoOtro) {
        this.tipoOtro = tipoOtro;
    }

    
}
