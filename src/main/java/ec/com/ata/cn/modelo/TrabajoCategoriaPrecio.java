/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
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
@Table(name = "trabajo_categoria_precio")
public class TrabajoCategoriaPrecio implements Serializable { 
    
    private static final long serialVersionUID = 1L;  
    
    @Id
    @SequenceGenerator(
            name = "trabajo_categoria_precio_seq",
            sequenceName = "trabajo_categoria_precio_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trabajo_categoria_precio_seq")
    @Column(name = "id_trabajo_categoria_precio")
    private Long idTrabajoCategoriaPrecio;
    
    @JoinColumn(name = "id_trabajo", referencedColumnName = "id_trabajo")
    @ManyToOne
    private Trabajo trabajo;   
    
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne
    private Categoria categoria;
    
    @JoinColumn(name = "id_grupo_precio", referencedColumnName = "id_grupo_precio")
    @ManyToOne
    private GrupoPrecio grupoPrecio;
    
    @JoinColumn(name = "id_parte", referencedColumnName = "id_parte")
    @ManyToOne
    private Parte parte;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @Column(name = "codigo",unique=true)
    private String codigo;
    
    @Column(name = "descripcion",unique=true)
    private String descripcion;
    
    @Column(name = "detalle")
    private String detalle;

    @Column(name = "precio_venta_publico")
    private BigDecimal precioVentaPublico;
    
    @Column(name = "precio_efectivo")
    private BigDecimal precioEfectivo;

    @Column(name = "precio_descuento")
    private BigDecimal precioDescuento;
    
    public TrabajoCategoriaPrecio () {
        genericoEntidad = new GenericoEntidad();
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (!Objects.equals(this.idTrabajoCategoriaPrecio, other.idTrabajoCategoriaPrecio)) {
            return false;
        }
        
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.grupoPrecio, other.grupoPrecio)) {
            return false;
        }
        if (!Objects.equals(this.parte, other.parte)) {
            return false;
        }
        return true;
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
     * @return the parte
     */
    public Parte getParte() {
        return parte;
    }

    /**
     * @param parte the parte to set
     */
    public void setParte(Parte parte) {
        this.parte = parte;
    }
    
    /**
     * @return the idTrabajoCategoriaPrecio
     */
    public Long getIdTrabajoCategoriaPrecio() {
        return idTrabajoCategoriaPrecio;
    }

    /**
     * @param idTrabajoCategoriaPrecio the idTrabajoCategoriaPrecio to set
     */
    public void setIdTrabajoCategoriaPrecio(Long idTrabajoCategoriaPrecio) {
        this.idTrabajoCategoriaPrecio = idTrabajoCategoriaPrecio;
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
     * @return the precioEfectivo
     */
    public BigDecimal getPrecioEfectivo() {
        return precioEfectivo;
    }

    /**
     * @param precioEfectivo the precioEfectivo to set
     */
    public void setPrecioEfectivo(BigDecimal precioEfectivo) {
        this.precioEfectivo = precioEfectivo;
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

}
