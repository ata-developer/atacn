/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "trabajo_categoria_precio")
public class TrabajoCategoriaPrecio implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TrabajoCategoriaPrecioId trabajoCategoriaPrecioId;

    @MapsId("idTrabajo")
    @JoinColumn(name = "id_trabajo", referencedColumnName = "id_trabajo")
    @ManyToOne
    private Trabajo trabajo;

    @MapsId("idCategoria")
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne
    private Categoria categoria;
    
    @MapsId("idGrupoPrecio")
    @JoinColumn(name = "id_grupo_precio", referencedColumnName = "id_grupo_precio")
    @ManyToOne
    private GrupoPrecio grupoPrecio;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    

    private BigDecimal precioVentaPublico;

    private BigDecimal precioDescuento;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.trabajoCategoriaPrecioId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TrabajoCategoriaPrecio other = (TrabajoCategoriaPrecio) obj;
        if (!Objects.equals(this.trabajoCategoriaPrecioId, other.trabajoCategoriaPrecioId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TrabajoCategoriaPrecio{" + "trabajoCategoriaPrecioId=" + (trabajoCategoriaPrecioId == null ? null : trabajoCategoriaPrecioId.toString()) + ", precioVentaPublico=" + precioVentaPublico + ", precioDescuento=" + precioDescuento + '}';
    }
    
    

    /**
     * @return the precioVentaPublico
     */
    public BigDecimal getPrecioVentaPublico() {
        return precioVentaPublico;
    }

    /**
     * @param precioVentaPublico the precioVentaPublico to set
     */
    public void setPrecioVentaPublico(BigDecimal precioVentaPublico) {
        this.precioVentaPublico = precioVentaPublico;
    }

    /**
     * @return the precioDescuento
     */
    public BigDecimal getPrecioDescuento() {
        return precioDescuento;
    }

    /**
     * @param precioDescuento the precioDescuento to set
     */
    public void setPrecioDescuento(BigDecimal precioDescuento) {
        this.precioDescuento = precioDescuento;
    }

    /**
     * @return the trabajo
     */
    public Trabajo getTrabajo() {
        return trabajo;
    }

    /**
     * @param trabajo the trabajo to set
     */
    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the trabajoCategoriaPrecioId
     */
    public TrabajoCategoriaPrecioId getTrabajoCategoriaPrecioId() {
        return trabajoCategoriaPrecioId;
    }

    /**
     * @param trabajoCategoriaPrecioId the trabajoCategoriaPrecioId to set
     */
    public void setTrabajoCategoriaPrecioId(TrabajoCategoriaPrecioId trabajoCategoriaPrecioId) {
        this.trabajoCategoriaPrecioId = trabajoCategoriaPrecioId;
    }

    /**
     * @return the grupoPrecio
     */
    public GrupoPrecio getGrupoPrecio() {
        return grupoPrecio;
    }

    /**
     * @param grupoPrecio the grupoPrecio to set
     */
    public void setGrupoPrecio(GrupoPrecio grupoPrecio) {
        this.grupoPrecio = grupoPrecio;
    }

}
