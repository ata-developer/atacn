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
public class Forro implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "forrotapiceria_seq",
            sequenceName = "forrotapiceria_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "forrotapiceria_seq")
    @Column(name = "id_forrotapiceria")
    private Long idForroTapiceria;
    
    @OneToMany
    private List<Fila> filas;

    public Long getIdForroTapiceria() {
        return idForroTapiceria;
    }

    public void setIdForroTapiceria(Long idForroTapiceria) {
        this.idForroTapiceria = idForroTapiceria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idForroTapiceria != null ? idForroTapiceria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idForroTapiceria fields are not set
        if (!(object instanceof Forro)) {
            return false;
        }
        Forro other = (Forro) object;
        if ((this.idForroTapiceria == null && other.idForroTapiceria != null) || (this.idForroTapiceria != null && !this.idForroTapiceria.equals(other.idForroTapiceria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.ForroTapiceria[ id=" + idForroTapiceria + " ]";
    }

    /**
     * @return the filas
     */
    public List<Fila> getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(List<Fila> filas) {
        this.filas = filas;
    }
    
}
