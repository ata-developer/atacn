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
@Table(name = "grupo_precio")
public class GrupoPrecio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "grupo_precio_seq",
            sequenceName = "grupo_precio_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grupo_precio_seq")
    @Column(name = "id_grupo_precio")
    private Long idGrupoPrecio;
    
    @Column(name = "nombre", unique = true)
    private String nombre;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    public GrupoPrecio () {
        genericoEntidad = new GenericoEntidad();
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombre != null ? nombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idGrupoPrecio fields are not set
        if (!(object instanceof GrupoPrecio)) {
            return false;
        }
        GrupoPrecio other = (GrupoPrecio) object;
        return !((this.nombre == null && other.nombre != null) || (this.nombre != null && !this.nombre.equals(other.nombre)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.GrupoPrecio[ id=" + nombre + " ]";
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the idGrupoPrecio
     */
    public Long getIdGrupoPrecio() {
        return idGrupoPrecio;
    }

    /**
     * @param idGrupoPrecio the idGrupoPrecio to set
     */
    public void setIdGrupoPrecio(Long idGrupoPrecio) {
        this.idGrupoPrecio = idGrupoPrecio;
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
