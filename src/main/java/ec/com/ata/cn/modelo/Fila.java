/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author ATA1
 */
@Entity
public class Fila implements Serializable {

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
    
    @OneToMany
    private List<Asiento> listaAsiento;
    

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
     * @return the listaAsiento
     */
    public List<Asiento> getListaAsiento() {
        return listaAsiento;
    }

    /**
     * @param listaAsiento the listaAsiento to set
     */
    public void setListaAsiento(List<Asiento> listaAsiento) {
        this.listaAsiento = listaAsiento;
    }
    
}
