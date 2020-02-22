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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

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
    
    @Column(name = "parte")
    private String parte;

    @Column(name = "distintivo")
    private String distintivo;
    
    @Transient
    private Long idPartePadre;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_parte_padre")
    private Parte padre;

    @Embedded
    private GenericoEntidad genericoEntidad;

    @ManyToOne
    private Material material;

    public Parte() {
        genericoEntidad = new GenericoEntidad();
    }
    
    public void setIdParte(Long idParte) {
        this.idParte = idParte;
    }
    
    public Long getIdParte() {
        return idParte;
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

    /**
     * @return the idPartePadre
     */
    public Long getIdPartePadre() {
        return idPartePadre;
    }

    /**
     * @param idPartePadre the idPartePadre to set
     */
    public void setIdPartePadre(Long idPartePadre) {
        this.idPartePadre = idPartePadre;
    }

    /**
     * @return the padre
     */
    public Parte getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Parte padre) {
        this.padre = padre;
    }
    
    /**
     * @return the distintivo
     */
    public String getDistintivo() {
        return distintivo;
    }

    /**
     * @param distintivo the distintivo to set
     */
    public void setDistintivo(String distintivo) {
        this.distintivo = distintivo;
    }

}
