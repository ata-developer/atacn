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
public class Asiento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "asiento_seq",
            sequenceName = "asiento_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "asiento_seq")
    @Column(name = "id_asiento")
    private Long idAsiento;

    @Embedded
    private GenericoEntidad genericoEntidad;
    
    
    
    @OneToMany
    private List<Parte> partes;
    
    
    public Asiento () {
        genericoEntidad = new GenericoEntidad();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAsiento != null ? idAsiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asiento)) {
            return false;
        }
        Asiento other = (Asiento) object;
        if ((this.idAsiento == null && other.idAsiento != null) || (this.idAsiento != null && !this.idAsiento.equals(other.idAsiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Asiento[ id=" + idAsiento + " ]";
    }

    /**
     * @return the partes
     */
    public List<Parte> getPartes() {
        return partes;
    }

    /**
     * @param partes the partes to set
     */
    public void setPartes(List<Parte> partes) {
        this.partes = partes;
    }

    /**
     * @return the idAsiento
     */
    public Long getIdAsiento() {
        return idAsiento;
    }

    /**
     * @param idAsiento the idAsiento to set
     */
    public void setIdAsiento(Long idAsiento) {
        this.idAsiento = idAsiento;
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
    
}
