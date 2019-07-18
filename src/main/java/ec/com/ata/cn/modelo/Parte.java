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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table
public class Parte implements Serializable {
    
    @Id
    @SequenceGenerator(
            name = "parte_seq",
            sequenceName = "parte_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "parte_seq")
    @Column(name = "id_parte")
    private Long idParte;
    
    
    public Parte () {
        genericoEntidad = new GenericoEntidad();
    }

    public Long getIdParte() {
        return idParte;
    }

    @Column(name = "parte")
    private String parte;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @ManyToOne
    private Material material;

    public void setIdParte(Long idParte) {
        this.idParte = idParte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParte != null ? idParte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idParte fields are not set
        if (!(object instanceof Parte)) {
            return false;
        }
        Parte other = (Parte) object;
        if ((this.idParte == null && other.idParte != null) || (this.idParte != null && !this.idParte.equals(other.idParte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Parte[ id=" + idParte + " ]";
    }

    /**
     * @return the parte
     */
    public String getParte() {
        return parte;
    }

    /**
     * @param parte the parte to set
     */
    public void setParte(String parte) {
        this.parte = parte;
    }

    /**
     * @return the material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * @param material the material to set
     */
    public void setMaterial(Material material) {
        this.material = material;
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
