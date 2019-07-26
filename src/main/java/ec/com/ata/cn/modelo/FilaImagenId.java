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
public class FilaImagenId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_fila")
    private Long idFila;
    
    @Column(name = "id_imagen")
    private Long idImagen;

    @Override
    public String toString() {
        return "FilaImagenId{" + "idFila=" + idFila + ", idImagen=" + idImagen + '}';
    }
    
    

    /**
     * @return the idFila
     */
    public Long getIdFila() {
        return idFila;
    }

    /**
     * @param idFila the idFila to set
     */
    public void setIdFila(Long idFila) {
        this.idFila = idFila;
    }

    /**
     * @return the idImagen
     */
    public Long getIdImagen() {
        return idImagen;
    }

    /**
     * @param idImagen the idImagen to set
     */
    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }
   
}
