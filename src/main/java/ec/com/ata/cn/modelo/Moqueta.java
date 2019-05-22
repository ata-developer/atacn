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
public class Moqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "moqueta_seq",
            sequenceName = "moqueta_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "moqueta_seq")
    @Column(name = "moqueta_seq")
    private Long idMoqueta;

    public Long getIdMoqueta() {
        return idMoqueta;
    }

    public void setIdMoqueta(Long idMoqueta) {
        this.idMoqueta = idMoqueta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMoqueta != null ? idMoqueta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idMoqueta fields are not set
        if (!(object instanceof Moqueta)) {
            return false;
        }
        Moqueta other = (Moqueta) object;
        if ((this.idMoqueta == null && other.idMoqueta != null) || (this.idMoqueta != null && !this.idMoqueta.equals(other.idMoqueta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Moqueta[ id=" + idMoqueta + " ]";
    }
    
}
