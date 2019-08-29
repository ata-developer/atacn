/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "tipo_fila")
public class TipoFila implements Serializable {   

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "tipo_fila_seq",
            sequenceName = "tipo_fila_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tipo_fila_seq")
    @Column(name = "id_tipo_fila")
    private Long idTipoFila;

    @Column(name = "tipo_fila")
    private String tipoFila;

    @Column(name = "numero_asiento")
    private Integer numeroAsiento;
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    public TipoFila () {
        genericoEntidad = new GenericoEntidad();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.idTipoFila);
        hash = 19 * hash + Objects.hashCode(this.tipoFila);
        hash = 19 * hash + Objects.hashCode(this.numeroAsiento);
        hash = 19 * hash + Objects.hashCode(this.genericoEntidad);
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
        final TipoFila other = (TipoFila) obj;
        if (!Objects.equals(this.tipoFila, other.tipoFila)) {
            return false;
        }
        if (!Objects.equals(this.idTipoFila, other.idTipoFila)) {
            return false;
        }
        if (!Objects.equals(this.numeroAsiento, other.numeroAsiento)) {
            return false;
        }
        if (!Objects.equals(this.genericoEntidad, other.genericoEntidad)) {
            return false;
        }
        return true;
    }

   

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.TipoAsiento[ id=" + getIdTipoFila() + " ]";
    }

    
    
     /**
     * @return the genericoEntidad
     */
    public GenericoEntidad getGenericoEntidad() {
        return genericoEntidad;
    }

    /**
     * @param genericoEntidad the genericoEntidad to set
     */
    public void setGenericoEntidad(GenericoEntidad genericoEntidad) {
        this.genericoEntidad = genericoEntidad;
    }

    /**
     * @return the idTipoFila
     */
    public Long getIdTipoFila() {
        return idTipoFila;
    }

    /**
     * @param idTipoFila the idTipoFila to set
     */
    public void setIdTipoFila(Long idTipoFila) {
        this.idTipoFila = idTipoFila;
    }

    /**
     * @return the tipoFila
     */
    public String getTipoFila() {
        return tipoFila;
    }

    /**
     * @param tipoFila the tipoFila to set
     */
    public void setTipoFila(String tipoFila) {
        this.tipoFila = tipoFila;
    }

    /**
     * @return the numeroAsiento
     */
    public Integer getNumeroAsiento() {
        return numeroAsiento;
    }

    /**
     * @param numeroAsiento the numeroAsiento to set
     */
    public void setNumeroAsiento(Integer numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

}
