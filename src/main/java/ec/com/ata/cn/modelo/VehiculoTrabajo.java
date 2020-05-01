/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
    
    @Column(name = "precio_abono_efectivo")
    private BigDecimal precioAbonoEfectivo;
    
    @Column(name = "precio_abono_tarjeta")
    private BigDecimal precioAbonoTarjeta;
    
    @Column(name = "precio_saldo_efectivo")
    private BigDecimal precioSaldoEfectivo;
    
    @Column(name = "precio_saldo_tarjeta")
    private BigDecimal precioSaldoTarjeta;
    
    @ManyToOne
    @JoinColumn(name="id_parte", referencedColumnName="id_parte")
    private Parte partePrincipal;
    
    @ManyToOne
    @JoinColumn(name="id_orden_vehiculo", referencedColumnName="id_orden_vehiculo")
    private OrdenVehiculo ordenVehiculo;
    
    @ManyToOne
    @JoinColumn(name="id_equipo", referencedColumnName="id_equipo")
    private Equipo equipo;
     
    @OneToMany(mappedBy = "vehiculoTrabajo", fetch = FetchType.EAGER)
    private List<TrabajoParte> listaTrabajoParte;
   
    
    
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
    
    /**
     * @return the partePrincipal
     */
    public Parte getPartePrincipal() {
        return partePrincipal;
    }

    /**
     * @param partePrincipal the partePrincipal to set
     */
    public void setPartePrincipal(Parte partePrincipal) {
        this.partePrincipal = partePrincipal;
    }
    
    /**
     * @return the listaTrabajoParte
     */
    public List<TrabajoParte> getListaTrabajoParte() {
        return listaTrabajoParte;
    }

    /**
     * @param listaTrabajoParte the listaTrabajoParte to set
     */
    public void setListaTrabajoParte(List<TrabajoParte> listaTrabajoParte) {
        this.listaTrabajoParte = listaTrabajoParte;
    }
    
    /**
     * @return the precioAbonoEfectivo
     */
    public BigDecimal getPrecioAbonoEfectivo() {
        return precioAbonoEfectivo;
    }

    /**
     * @param precioAbonoEfectivo the precioAbonoEfectivo to set
     */
    public void setPrecioAbonoEfectivo(BigDecimal precioAbonoEfectivo) {
        this.precioAbonoEfectivo = precioAbonoEfectivo;
    }

    /**
     * @return the precioAbonoTarjeta
     */
    public BigDecimal getPrecioAbonoTarjeta() {
        return precioAbonoTarjeta;
    }

    /**
     * @param precioAbonoTarjeta the precioAbonoTarjeta to set
     */
    public void setPrecioAbonoTarjeta(BigDecimal precioAbonoTarjeta) {
        this.precioAbonoTarjeta = precioAbonoTarjeta;
    }

    /**
     * @return the precioSaldoEfectivo
     */
    public BigDecimal getPrecioSaldoEfectivo() {
        return precioSaldoEfectivo;
    }

    /**
     * @param precioSaldoEfectivo the precioSaldoEfectivo to set
     */
    public void setPrecioSaldoEfectivo(BigDecimal precioSaldoEfectivo) {
        this.precioSaldoEfectivo = precioSaldoEfectivo;
    }

    /**
     * @return the precioSaldoTarjeta
     */
    public BigDecimal getPrecioSaldoTarjeta() {
        return precioSaldoTarjeta;
    }

    /**
     * @param precioSaldoTarjeta the precioSaldoTarjeta to set
     */
    public void setPrecioSaldoTarjeta(BigDecimal precioSaldoTarjeta) {
        this.precioSaldoTarjeta = precioSaldoTarjeta;
    }
    
    /**
     * @return the equipo
     */
    public Equipo getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
    
    /**
     * @return the ordenVehiculo
     */
    public OrdenVehiculo getOrdenVehiculo() {
        return ordenVehiculo;
    }

    /**
     * @param ordenVehiculo the ordenVehiculo to set
     */
    public void setOrdenVehiculo(OrdenVehiculo ordenVehiculo) {
        this.ordenVehiculo = ordenVehiculo;
    }
}
