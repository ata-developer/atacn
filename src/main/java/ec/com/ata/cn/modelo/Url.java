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
public class Url implements Serializable {    

   

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "url_seq",
            sequenceName = "url_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "url_seq")
    @Column(name = "id_url")
    private Long idUrl;

    @Column(name = "url")
    private String url;
   
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "cabecera")
    private String cabecera;
    
    @Column(name = "presentar")
    private Boolean presentar;
    
    @Column(name = "es_menu")
    private Boolean esMenu;
    
    
    public Url() {
        genericoEntidad = new GenericoEntidad();
    }

    
    @Transient
    private Long idUrlPadre;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_url_padre")
    private Url padre;

    @Embedded
    private GenericoEntidad genericoEntidad;

    public Long getIdUrl() {
        return idUrl;
    }

    public void setIdUrl(Long idUrl) {
        this.idUrl = idUrl;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUrl != null ? idUrl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idUrl fields are not set
        if (!(object instanceof Url)) {
            return false;
        }
        Url other = (Url) object;
        return !((this.idUrl == null && other.idUrl != null) || (this.idUrl != null && !this.idUrl.equals(other.idUrl)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Url[ id=" + idUrl + " ]";
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @return the idUrlPadre
     */
    public Long getIdUrlPadre() {
        return idUrlPadre;
    }

    /**
     * @param idUrlPadre the idUrlPadre to set
     */
    public void setIdUrlPadre(Long idUrlPadre) {
        this.idUrlPadre = idUrlPadre;
    }

    /**
     * @return the padre
     */
    public Url getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Url padre) {
        this.padre = padre;
    }
    
    /**
     * @return the cabecera
     */
    public String getCabecera() {
        return cabecera;
    }

    /**
     * @param cabecera the cabecera to set
     */
    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }
    
    /**
     * @return the presentar
     */
    public Boolean getPresentar() {
        return presentar;
    }

    /**
     * @param presentar the presentar to set
     */
    public void setPresentar(Boolean presentar) {
        this.presentar = presentar;
    }

     /**
     * @return the esMenu
     */
    public Boolean getEsMenu() {
        return esMenu;
    }

    /**
     * @param esMenu the esMenu to set
     */
    public void setEsMenu(Boolean esMenu) {
        this.esMenu = esMenu;
    }
    
}
