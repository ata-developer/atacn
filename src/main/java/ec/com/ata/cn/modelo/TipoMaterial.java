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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "tipo_material")
public class TipoMaterial implements Serializable {

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
            name = "tipo_material_seq",
            sequenceName = "tipo_material_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tipo_material_seq")
    @Column(name = "id_tipo_material")
    private Long idTipoMaterial;
    
    @Column(name = "tipo", unique = true)
    private String tipo;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    public TipoMaterial () {
        genericoEntidad = new GenericoEntidad();
    }

    public Long getIdTipoMaterial() {
        return idTipoMaterial;
    }

    public void setIdTipoMaterial(Long idTipoMaterial) {
        this.idTipoMaterial = idTipoMaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoMaterial != null ? idTipoMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idTipoMaterial fields are not set
        if (!(object instanceof TipoMaterial)) {
            return false;
        }
        TipoMaterial other = (TipoMaterial) object;
        if ((this.idTipoMaterial == null && other.idTipoMaterial != null) || (this.idTipoMaterial != null && !this.idTipoMaterial.equals(other.idTipoMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.TipoMaterial[ id=" + idTipoMaterial + " ]";
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
