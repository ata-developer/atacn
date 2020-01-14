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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */

@Entity
@Table ( name = "vehiculo_trabajo")
public class VehiculoTrabajo implements Serializable {
    
    @Id
    @SequenceGenerator(
            name = "vehiculo_trabajo_seq",
            sequenceName = "vehiculo_trabajo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehiculo_trabajo_seq")
    @Column(name = "id_vehiculo_trabajo")
    private Long idVehiculoTrabajo;
    
    @Column(name = "vehiculo_descripcion")
    private String vehiculoDescripcion;
    
    @Column(name = "trabajo_descripcion")
    private String trabajoDescripcion;
    
    @Column(name = "id_vehiculo")
    private Long idVehiculo;
    
    @Column(name = "id_trabajo")
    private Long idTrabajo;
    
    @Column(name = "id_grupo_precio")
    private Long idGrupoPrecio;
    
    @Column(name = "precio_venta_publico")
    private BigDecimal precioVentaPublico;

    @Column(name = "precio_descuento")
    private BigDecimal precioDescuento;
    
    
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    

    
    
    /**
     * @return the idVehiculoTrabajo
     */
    public Long getIdVehiculoTrabajo() {
        return idVehiculoTrabajo;
    }

    /**
     * @param idVehiculoTrabajo the idVehiculoTrabajo to set
     */
    public void setIdVehiculoTrabajo(Long idVehiculoTrabajo) {
        this.idVehiculoTrabajo = idVehiculoTrabajo;
    }

    /**
     * @return the vehiculoDescripcion
     */
    public String getVehiculoDescripcion() {
        return vehiculoDescripcion;
    }

    /**
     * @param vehiculoDescripcion the vehiculoDescripcion to set
     */
    public void setVehiculoDescripcion(String vehiculoDescripcion) {
        this.vehiculoDescripcion = vehiculoDescripcion;
    }

    /**
     * @return the trabajoDescripcion
     */
    public String getTrabajoDescripcion() {
        return trabajoDescripcion;
    }

    /**
     * @param trabajoDescripcion the trabajoDescripcion to set
     */
    public void setTrabajoDescripcion(String trabajoDescripcion) {
        this.trabajoDescripcion = trabajoDescripcion;
    }

    /**
     * @return the idVehiculo
     */
    public Long getIdVehiculo() {
        return idVehiculo;
    }

    /**
     * @param idVehiculo the idVehiculo to set
     */
    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
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
