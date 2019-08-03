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
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table
public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "imagen_seq",
            sequenceName = "imagen_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "imagen_seq")
    @Column(name = "id_imagen")
    private Long idImagen;

    @Lob
    @Column(name = "datos_imagen")
    private byte[] datosImagen;

    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "tiene_padre")
    private Boolean tienePadre;

    @Embedded
    private GenericoEntidad genericoEntidad;

    public Imagen() {
        genericoEntidad = new GenericoEntidad();
    }

    public Long getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Long idImagen) {
        this.idImagen = idImagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagen != null ? idImagen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idImagen fields are not set
        if (!(object instanceof Imagen)) {
            return false;
        }
        Imagen other = (Imagen) object;
        if ((this.idImagen == null && other.idImagen != null) || (this.idImagen != null && !this.idImagen.equals(other.idImagen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Imagen[ id=" + idImagen + " ]";
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
     * @return the datosImagen
     */
    public byte[] getDatosImagen() {
        return datosImagen;
    }

    /**
     * @param datosImagen the datosImagen to set
     */
    public void setDatosImagen(byte[] datosImagen) {
        this.datosImagen = datosImagen;
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
     * @return the tienePadre
     */
    public Boolean getTienePadre() {
        return tienePadre;
    }

    /**
     * @param tienePadre the tienePadre to set
     */
    public void setTienePadre(Boolean tienePadre) {
        this.tienePadre = tienePadre;
    }
}
