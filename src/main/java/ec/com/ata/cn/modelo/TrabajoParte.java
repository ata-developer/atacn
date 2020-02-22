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
@Table ( name = "trabajo_parte")
public class TrabajoParte implements Serializable {   
    
    @Id
    @SequenceGenerator(
            name = "trabajo_parte_seq",
            sequenceName = "trabajo_parte_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "trabajo_parte_seq")
    @Column(name = "id_trabajo_parte")
    private Long idTrabajoParte;
    
    @Column(name = "detalle")
    private String detalle;
    
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    
    @Column(name = "unidad")
    private String unidad;
    
    @ManyToOne
    @JoinColumn(name="id_vehiculo_trabajo", referencedColumnName="id_vehiculo_trabajo")
    private VehiculoTrabajo vehiculoTrabajo;
    
    @ManyToOne
    @JoinColumn(name="id_parte", referencedColumnName="id_parte")
    private Parte parte;
    
    
    @ManyToOne
    @JoinColumn(name="id_parte_padre", referencedColumnName="id_parte")
    private Parte partePadre;
    
     /**
     * @return the partePadre
     */
    public Parte getPartePadre() {
        return partePadre;
    }

    /**
     * @param partePadre the partePadre to set
     */
    public void setPartePadre(Parte partePadre) {
        this.partePadre = partePadre;
    }
    
    /**
     * @return the idTrabajoParte
     */
    public Long getIdTrabajoParte() {
        return idTrabajoParte;
    }

    /**
     * @param idTrabajoParte the idTrabajoParte to set
     */
    public void setIdTrabajoParte(Long idTrabajoParte) {
        this.idTrabajoParte = idTrabajoParte;
    }
    
    /**
     * @return the vehiculoTrabajo
     */
    public VehiculoTrabajo getVehiculoTrabajo() {
        return vehiculoTrabajo;
    }

    /**
     * @param vehiculoTrabajo the vehiculoTrabajo to set
     */
    public void setVehiculoTrabajo(VehiculoTrabajo vehiculoTrabajo) {
        this.vehiculoTrabajo = vehiculoTrabajo;
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
     * @return the cantidad
     */
    public BigDecimal getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the unidad
     */
    public String getUnidad() {
        return unidad;
    }

    /**
     * @param unidad the unidad to set
     */
    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
}
