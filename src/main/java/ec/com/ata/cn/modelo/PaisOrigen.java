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
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "pais_origen")
public class PaisOrigen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id    
    @SequenceGenerator(
            name = "pais_origen_seq",
            sequenceName = "pais_origen_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pais_origen_seq")
    @Column(name = "id_pais_origen")
    private Long idPaisOrigen;
    
    @Column(name = "pais")
    private String pais;

    public Long getIdPaisOrigen() {
        return idPaisOrigen;
    }

    public void setIdPaisOrigen(Long idPaisOrigen) {
        this.idPaisOrigen = idPaisOrigen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPaisOrigen != null ? idPaisOrigen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPaisOrigen fields are not set
        if (!(object instanceof PaisOrigen)) {
            return false;
        }
        PaisOrigen other = (PaisOrigen) object;
        if ((this.idPaisOrigen == null && other.idPaisOrigen != null) || (this.idPaisOrigen != null && !this.idPaisOrigen.equals(other.idPaisOrigen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.PaisOrigen[ id=" + idPaisOrigen + " ]";
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }
    
}
