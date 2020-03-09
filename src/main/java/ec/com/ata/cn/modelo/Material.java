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
public class Material implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "material_seq",
            sequenceName = "material_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "material_seq")
    @Column(name = "id_material")
    private Long idMaterial;
    
    @Column(name = "codigo")
    private String codigo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @ManyToOne
    private TipoMaterial tipoMaterial;
    
    @ManyToOne
    private Color color;
    
    
    public Material () {
        genericoEntidad = new GenericoEntidad();
    }

    public Long getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Long idMaterial) {
        this.idMaterial = idMaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterial != null ? idMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idMaterial fields are not set
        if (!(object instanceof Material)) {
            return false;
        }
        Material other = (Material) object;
        if ((this.idMaterial == null && other.idMaterial != null) || (this.idMaterial != null && !this.idMaterial.equals(other.idMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Material[ id=" + idMaterial + " ]";
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tipoMaterial
     */
    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    /**
     * @param tipoMaterial the tipoMaterial to set
     */
    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
    
}
