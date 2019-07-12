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
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "categoria_seq",
            sequenceName = "categoria_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "categoria_seq")
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "categoria")
    private String categoria;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @ManyToOne
    @JoinColumn(name="id_grupo_precio", referencedColumnName="id_grupo_precio")
    private GrupoPrecio grupoPrecio;

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idCategoria fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        return !((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Categoria[ id=" + idCategoria + " ]";
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

}
