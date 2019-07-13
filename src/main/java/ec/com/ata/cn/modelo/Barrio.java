/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table
public class Barrio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "barrio_seq",
            sequenceName = "barrio_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "barrio_seq")
    @Column(name = "id_barrio")
    private Long idBarrio;

    @Column(name = "barrio", unique = true)
    private String barrio;
        
    @ManyToOne
    @JoinColumn(name="id_ciudad", referencedColumnName="id_ciudad")
    private Ciudad ciudad;
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    public Long getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(Long idBarrio) {
        this.idBarrio = idBarrio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBarrio != null ? idBarrio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idBarrio fields are not set
        if (!(object instanceof Barrio)) {
            return false;
        }
        Barrio other = (Barrio) object;
        return !((this.idBarrio == null && other.idBarrio != null) || (this.idBarrio != null && !this.idBarrio.equals(other.idBarrio)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Categoria[ id=" + idBarrio + " ]";
    }

    /**
     * @return the barrio
     */
    public String getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    /**
     * @return the ciudad
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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
