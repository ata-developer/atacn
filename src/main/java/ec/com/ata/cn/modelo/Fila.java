/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table
public class Fila implements Serializable {

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

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "fila_seq",
            sequenceName = "fila_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fila_seq")
    @Column(name = "id_fila")
    private Long idFila;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @OneToMany
    private List<Asiento> asientos;
    

    public Long getIdFila() {
        return idFila;
    }

    public void setIdFila(Long idFila) {
        this.idFila = idFila;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFila != null ? idFila.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idFila fields are not set
        if (!(object instanceof Fila)) {
            return false;
        }
        Fila other = (Fila) object;
        if ((this.idFila == null && other.idFila != null) || (this.idFila != null && !this.idFila.equals(other.idFila))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Fila[ id=" + idFila + " ]";
    }

    /**
     * @return the asientos
     */
    public List<Asiento> getAsientos() {
        return asientos;
    }

    /**
     * @param asientos the asientos to set
     */
    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
    }
    
}
