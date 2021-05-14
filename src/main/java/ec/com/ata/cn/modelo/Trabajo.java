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
public class Trabajo implements Serializable {
        
    @Id
    @SequenceGenerator(
            name = "trabajo_seq",
            sequenceName = "trabajo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trabajo_seq")
    @Column(name = "id_trabajo")
    private Long idTrabajo;
    
    @Column(name = "descripcion",unique=true)
    private String descripcion;
    
    @Column(name = "detalle")
    private String detalle;
    
    @Column(name = "orden")
    private Long orden;
    
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @ManyToOne
    @JoinColumn(name="id_grupo_precio", referencedColumnName="id_grupo_precio")
    private GrupoPrecio grupoPrecio;
    
    public Trabajo () {
        genericoEntidad = new GenericoEntidad();
    }
    
    public Long getIdTrabajo() {
        return idTrabajo;
    }

    public void setIdTrabajo(Long idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrabajo != null ? idTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idTrabajo fields are not set
        if (!(object instanceof Trabajo)) {
            return false;
        }
        Trabajo other = (Trabajo) object;
        return !((this.idTrabajo == null && other.idTrabajo != null) || (this.idTrabajo != null && !this.idTrabajo.equals(other.idTrabajo)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Trabajo[ id=" + idTrabajo + " ]";
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
     * @return the GrupoPrecio
     */
    public GrupoPrecio getGrupoPrecio() {
        return grupoPrecio;
    }

    /**
     * @param grupoPrecio the GrupoPrecio to set
     */
    public void setGrupoPrecio(GrupoPrecio grupoPrecio) {
        this.grupoPrecio = grupoPrecio;
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
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    /**
     * @return the orden
     */
    public Long getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Long orden) {
        this.orden = orden;
    }
}
