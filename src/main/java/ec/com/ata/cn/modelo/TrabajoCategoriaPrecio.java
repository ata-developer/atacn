/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "trabajo_categoria_precio")
@IdClass(TrabajoCategoriaPrecioId.class)
public class TrabajoCategoriaPrecio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_trabajo")
    private Long idTrabajo;

    @Id
    @Column(name = "id_categoria")
    private Long idCategoria;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "id_trabajo", referencedColumnName="id_trabajo" )
    private Trabajo trabajo;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "id_categoria", referencedColumnName="id_categoria")
    private Categoria categoria;

    private BigDecimal precioVentaPublico;

    private BigDecimal precioDescuento;

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
     * @return the idTrabajo
     */
    public Long getIdTrabajo() {
        return idTrabajo;
    }

    /**
     * @param idTrabajo the idTrabajo to set
     */
    public void setIdTrabajo(Long idTrabajo) {
        this.idTrabajo = idTrabajo;
    }

    /**
     * @return the idCategoria
     */
    public Long getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

}
