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
@Table(name = "fila_imagen")
public class FilaImagen implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private FilaImagenId filaImagenId;

    @MapsId("idFila")
    @JoinColumn(name = "id_fila", referencedColumnName = "id_fila")
    @ManyToOne
    private Fila fila;

    @MapsId("idImagen")
    @JoinColumn(name = "id_imagen", referencedColumnName = "id_imagen")
    @ManyToOne
    private Imagen imagen;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.filaImagenId);
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
        final FilaImagen other = (FilaImagen) obj;
        if (!Objects.equals(this.filaImagenId, other.filaImagenId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FilaImagen{" + "filaImagenPrecioId=" + (filaImagenId == null ? null : filaImagenId.toString()) + "'}'";
    }
    
    /**
     * @return the fila
     */
    public Fila getFila() {
        return fila;
    }

    /**
     * @param fila the fila to set
     */
    public void setFila(Fila fila) {
        this.fila = fila;
    }

    /**
     * @return the imagen
     */
    public Imagen getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the filaImagenId
     */
    public FilaImagenId getFilaImagenId() {
        return filaImagenId;
    }

    /**
     * @param filaImagenId the filaImagenId to set
     */
    public void setFilaImagenId(FilaImagenId filaImagenId) {
        this.filaImagenId = filaImagenId;
    }

}
