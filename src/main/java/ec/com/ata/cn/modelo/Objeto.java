/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ATA1
 */
@Entity
@Table
public class Objeto implements Serializable {

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
            name = "objeto_seq",
            sequenceName = "objeto_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "objeto_seq")
    @Column(name = "id_objeto")
    private Long idObjeto;

    @Column(name = "objeto")
    private String objeto;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "detalle")
    private String detalle;
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    @Transient
    private Long idObjectoPadre;
    
    public Objeto () {
        genericoEntidad = new GenericoEntidad();
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_objeto_padre")
    private Objeto padre;

    @OneToMany(mappedBy = "padre", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Objeto> hijos = new ArrayList<Objeto>();

    public Long getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Long idObjeto) {
        this.idObjeto = idObjeto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjeto != null ? idObjeto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idObjeto fields are not set
        if (!(object instanceof Objeto)) {
            return false;
        }
        Objeto other = (Objeto) object;
        return !((this.idObjeto == null && other.idObjeto != null) || (this.idObjeto != null && !this.idObjeto.equals(other.idObjeto)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Objeto[ id=" + idObjeto + " ]";
    }

    /**
     * @return the objeto
     */
    public String getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(String objeto) {
        this.objeto = objeto;
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
     * @return the padre
     */
    public Objeto getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(Objeto padre) {
        this.padre = padre;
    }

    /**
     * @return the hijos
     */
    public List<Objeto> getHijos() {
        return hijos;
    }

    /**
     * @param hijos the hijos to set
     */
    public void setHijos(List<Objeto> hijos) {
        this.hijos = hijos;
    }

    /**
     * @return the idObjectoPadre
     */
    public Long getIdObjectoPadre() {
        return idObjectoPadre;
    }

    /**
     * @param idObjectoPadre the idObjectoPadre to set
     */
    public void setIdObjectoPadre(Long idObjectoPadre) {
        this.idObjectoPadre = idObjectoPadre;
    }

    

}
