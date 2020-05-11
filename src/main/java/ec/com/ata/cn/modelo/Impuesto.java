/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table (name = "impuesto")
public class Impuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "impuesto_seq",
            sequenceName = "impuesto_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "impuesto_seq")
    @Column(name = "id_impuesto")
    private Long idImpuesto;

    @Column(name = "impuesto")
    private String impuesto;
    
    @Column(name = "factor")
    private BigDecimal factor;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    
    public Impuesto () {
        genericoEntidad = new GenericoEntidad();
    }

    public Long getIdImpuesto() {
        return idImpuesto;
    }

    public void setIdImpuesto(Long idImpuesto) {
        this.idImpuesto = idImpuesto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImpuesto != null ? idImpuesto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idImpuesto fields are not set
        if (!(object instanceof Impuesto)) {
            return false;
        }
        Impuesto other = (Impuesto) object;
        return !((this.idImpuesto == null && other.idImpuesto != null) || (this.idImpuesto != null && !this.idImpuesto.equals(other.idImpuesto)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Impuesto[ id=" + idImpuesto + " ]";
    }

    /**
     * @return the impuesto
     */
    public String getImpuesto() {
        return impuesto;
    }

    /**
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
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
     * @return the factor
     */
    public BigDecimal getFactor() {
        return factor;
    }

    /**
     * @param factor the factor to set
     */
    public void setFactor(BigDecimal factor) {
        this.factor = factor;
    }


}
