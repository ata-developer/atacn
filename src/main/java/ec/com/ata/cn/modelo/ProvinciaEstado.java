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
@Table(name = "provincia_estado")
public class ProvinciaEstado implements Serializable {

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
            name = "provincia_estado_seq",
            sequenceName = "provincia_estado_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "provincia_estado_seq")
    @Column(name = "id_provincia_estado")
    private Long idProvinciaEstado;

    @Column(name = "provincia_estado")
    private String provinciaEstado;
        
    @ManyToOne
    @JoinColumn(name="id_pais", referencedColumnName="id_pais")
    private Pais pais;
    
    public ProvinciaEstado () {
        genericoEntidad = new GenericoEntidad();
    }
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    public Long getIdProvinciaEstado() {
        return idProvinciaEstado;
    }

    public void setIdProvinciaEstado(Long idProvinciaEstado) {
        this.idProvinciaEstado = idProvinciaEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvinciaEstado != null ? idProvinciaEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idProvinciaEstado fields are not set
        if (!(object instanceof ProvinciaEstado)) {
            return false;
        }
        ProvinciaEstado other = (ProvinciaEstado) object;
        return !((this.idProvinciaEstado == null && other.idProvinciaEstado != null) || (this.idProvinciaEstado != null && !this.idProvinciaEstado.equals(other.idProvinciaEstado)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.ProvinciaEstado[ id=" + idProvinciaEstado + " ]";
    }

    /**
     * @return the provinciaEstado
     */
    public String getProvinciaEstado() {
        return provinciaEstado;
    }

    /**
     * @param provinciaEstado the provinciaEstado to set
     */
    public void setProvinciaEstado(String provinciaEstado) {
        this.provinciaEstado = provinciaEstado;
    }

    /**
     * @return the pais
     */
    public Pais getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

}
