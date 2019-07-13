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
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "ciudad_seq",
            sequenceName = "ciudad_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ciudad_seq")
    @Column(name = "id_ciudad")
    private Long idCiudad;

    @Column(name = "ciudad")
    private String ciudad;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
        
    @ManyToOne
    @JoinColumn(name="id_provincia_estado", referencedColumnName="id_provincia_estado")
    private ProvinciaEstado provinciaEstado;

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiudad != null ? idCiudad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idCiudad fields are not set
        if (!(object instanceof Ciudad)) {
            return false;
        }
        Ciudad other = (Ciudad) object;
        return !((this.idCiudad == null && other.idCiudad != null) || (this.idCiudad != null && !this.idCiudad.equals(other.idCiudad)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Categoria[ id=" + idCiudad + " ]";
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the provinciaEstado
     */
    public ProvinciaEstado getProvinciaEstado() {
        return provinciaEstado;
    }

    /**
     * @param provinciaEstado the provinciaEstado to set
     */
    public void setProvinciaEstado(ProvinciaEstado provinciaEstado) {
        this.provinciaEstado = provinciaEstado;
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
