/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
@Table(name = "orden_vehiculo")
public class OrdenVehiculo implements Serializable {

    @Id
    @SequenceGenerator(
            name = "orden_vehiculo_seq",
            sequenceName = "orden_vehiculo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orden_vehiculo_seq")
    @Column(name = "id_orden_vehiculo")
    private Long idOrdenVehiculo;

    @Column(name = "descripcion", unique = true)
    private String descripcion;

    @Column(name = "precio_total_abono_efectivo")
    private BigDecimal precioTotalAbonoEfectivo;

    @Column(name = "precio_total_abono_tarjeta")
    private BigDecimal precioTotalAbonoTarjeta;

    @Column(name = "precio_total_saldo_efectivo")
    private BigDecimal precioTotalSaldoEfectivo;

    @Column(name = "precio_total_saldo_tarjeta")
    private BigDecimal precioTotalSaldoTarjeta;

    @Column(name = "precio_total_efectivo")
    private BigDecimal precioTotalEfectivo;

    @Column(name = "precio_total_tarjeta")
    private BigDecimal precioTotalTarjeta;

    @Column(name = "pago_abono")
    private BigDecimal pagoAbono;

    @Column(name = "importe_abono")
    private BigDecimal importeAbono;

    @Column(name = "tipo_tarjeta")
    private String tipoTarjeta;

    @Column(name = "banco")
    private String banco;

    @Column(name = "lote")
    private String lote;

    @Column(name = "pago_abono_transferencia")
    private BigDecimal pagoAbonoTransferencia;

    @Column(name = "banco_transferencia")
    private String bancoTransferencia;

    @Column(name = "numero_transferencia")
    private String numeroTransferencia;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @Column(name = "sub_total_descuento")
    private BigDecimal subTotalDescuento;

    @Column(name = "sub_total_iva")
    private BigDecimal subTotalIva;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "total_abono")
    private BigDecimal totalAbono;

    @Column(name = "total_saldo")
    private BigDecimal totalSaldo;

    @Column(name = "origen", length = 12)
    private String origen;

    @Column(name = "nombre_referencia", length = 64)
    private String nombreReferencia;

    @Column(name = "dar_descuento")
    private Boolean darDescuento;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @Embedded
    private GenericoEntidad genericoEntidad;

    @ManyToOne
    @JoinColumn(name = "id_orden", referencedColumnName = "id_orden")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "id_usuario_factura", referencedColumnName = "id_usuario")
    private Usuario clienteFactura;

    @ManyToOne
    @JoinColumn(name = "id_usuario_orden", referencedColumnName = "id_usuario")
    private Usuario clienteOrden;

    @Column(name = "fecha_registro_vehiculo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistroVehiculo;

    @OneToMany(mappedBy = "ordenVehiculo")
    private List<VehiculoTrabajo> listaVehiculoTrabajo;
    
    @OneToMany(mappedBy = "ordenVehiculo")
    private List<HorarioParqueadero> listaHorarioParqueadero;

    public OrdenVehiculo() {
        genericoEntidad = new GenericoEntidad();
        setListaVehiculoTrabajo(new ArrayList<VehiculoTrabajo>());
        setSubTotal(new BigDecimal("0"));
        setSubTotalDescuento(new BigDecimal("0"));
        setSubTotalIva(new BigDecimal("0"));
        setTotal(new BigDecimal("0"));
        setTotalAbono(new BigDecimal("0"));
        setTotalSaldo(new BigDecimal("0"));
        setPagoAbono(new BigDecimal("0"));
        setImporteAbono(new BigDecimal("0"));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idOrdenVehiculo);
        hash = 53 * hash + Objects.hashCode(this.descripcion);
        hash = 53 * hash + Objects.hashCode(this.genericoEntidad);
        hash = 53 * hash + Objects.hashCode(this.orden);
        hash = 53 * hash + Objects.hashCode(this.vehiculo);
        hash = 53 * hash + Objects.hashCode(this.fechaRegistroVehiculo);
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
        final OrdenVehiculo other = (OrdenVehiculo) obj;
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.idOrdenVehiculo, other.idOrdenVehiculo)) {
            return false;
        }
        if (!Objects.equals(this.genericoEntidad, other.genericoEntidad)) {
            return false;
        }
        if (!Objects.equals(this.orden, other.orden)) {
            return false;
        }
        if (!Objects.equals(this.vehiculo, other.vehiculo)) {
            return false;
        }
        if (!Objects.equals(this.fechaRegistroVehiculo, other.fechaRegistroVehiculo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrdenVehiculo{" + "idOrdenVehiculo=" + idOrdenVehiculo + ", descripcion=" + descripcion + ", genericoEntidad=" + genericoEntidad + ", orden=" + orden + ", vehiculo=" + vehiculo + ", fechaRegistroVehiculo=" + fechaRegistroVehiculo + '}';
    }

    public Long getIdOrdenVehiculo() {
        return idOrdenVehiculo;
    }

    public void setIdOrdenVehiculo(Long idOrdenVehiculo) {
        this.idOrdenVehiculo = idOrdenVehiculo;
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
     * @return the orden
     */
    public Orden getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    /**
     * @return the vehiculo
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the fechaRegistroVehiculo
     */
    public Date getFechaRegistroVehiculo() {
        return fechaRegistroVehiculo;
    }

    /**
     * @param fechaRegistroVehiculo the fechaRegistroVehiculo to set
     */
    public void setFechaRegistroVehiculo(Date fechaRegistroVehiculo) {
        this.fechaRegistroVehiculo = fechaRegistroVehiculo;
    }

    /**
     * @return the precioTotalTarjeta
     */
    public BigDecimal getPrecioTotalTarjeta() {
        return precioTotalTarjeta;
    }

    /**
     * @param precioTotalTarjeta the precioTotalTarjeta to set
     */
    public void setPrecioTotalTarjeta(BigDecimal precioTotalTarjeta) {
        this.precioTotalTarjeta = precioTotalTarjeta;
    }

    /**
     * @return the precioTotalEfectivo
     */
    public BigDecimal getPrecioTotalEfectivo() {
        return precioTotalEfectivo;
    }

    /**
     * @param precioTotalEfectivo the precioTotalEfectivo to set
     */
    public void setPrecioTotalEfectivo(BigDecimal precioTotalEfectivo) {
        this.precioTotalEfectivo = precioTotalEfectivo;
    }

    /**
     * @return the precioTotalSaldoTarjeta
     */
    public BigDecimal getPrecioTotalSaldoTarjeta() {
        return precioTotalSaldoTarjeta;
    }

    /**
     * @param precioTotalSaldoTarjeta the precioTotalSaldoTarjeta to set
     */
    public void setPrecioTotalSaldoTarjeta(BigDecimal precioTotalSaldoTarjeta) {
        this.precioTotalSaldoTarjeta = precioTotalSaldoTarjeta;
    }

    /**
     * @return the precioTotalSaldoEfectivo
     */
    public BigDecimal getPrecioTotalSaldoEfectivo() {
        return precioTotalSaldoEfectivo;
    }

    /**
     * @param precioTotalSaldoEfectivo the precioTotalSaldoEfectivo to set
     */
    public void setPrecioTotalSaldoEfectivo(BigDecimal precioTotalSaldoEfectivo) {
        this.precioTotalSaldoEfectivo = precioTotalSaldoEfectivo;
    }

    /**
     * @return the precioTotalAbonoTarjeta
     */
    public BigDecimal getPrecioTotalAbonoTarjeta() {
        return precioTotalAbonoTarjeta;
    }

    /**
     * @param precioTotalAbonoTarjeta the precioTotalAbonoTarjeta to set
     */
    public void setPrecioTotalAbonoTarjeta(BigDecimal precioTotalAbonoTarjeta) {
        this.precioTotalAbonoTarjeta = precioTotalAbonoTarjeta;
    }

    /**
     * @return the precioTotalAbonoEfectivo
     */
    public BigDecimal getPrecioTotalAbonoEfectivo() {
        return precioTotalAbonoEfectivo;
    }

    /**
     * @param precioTotalAbonoEfectivo the precioTotalAbonoEfectivo to set
     */
    public void setPrecioTotalAbonoEfectivo(BigDecimal precioTotalAbonoEfectivo) {
        this.precioTotalAbonoEfectivo = precioTotalAbonoEfectivo;
    }

    /**
     * @return the listaVehiculoTrabajo
     */
    public List<VehiculoTrabajo> getListaVehiculoTrabajo() {
        return listaVehiculoTrabajo;
    }

    /**
     * @param listaVehiculoTrabajo the listaVehiculoTrabajo to set
     */
    public void setListaVehiculoTrabajo(List<VehiculoTrabajo> listaVehiculoTrabajo) {
        this.listaVehiculoTrabajo = listaVehiculoTrabajo;
    }

    /**
     * @return the tipoTarjeta
     */
    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    /**
     * @param tipoTarjeta the tipoTarjeta to set
     */
    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    /**
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }

    /**
     * @return the lote
     */
    public String getLote() {
        return lote;
    }

    /**
     * @param lote the lote to set
     */
    public void setLote(String lote) {
        this.lote = lote;
    }

    /**
     * @return the pago_abono
     */
    public BigDecimal getPagaAbono() {
        return getPagoAbono();
    }

    /**
     * @param pago_abono the pago_abono to set
     */
    public void setPagoAbono(BigDecimal pago_abono) {
        this.pagoAbono = pago_abono;
    }

    /**
     * @return the importe_abono
     */
    public BigDecimal getImporteAbono() {
        return importeAbono;
    }

    /**
     * @param importe_abono the importe_abono to set
     */
    public void setImporteAbono(BigDecimal importe_abono) {
        this.importeAbono = importe_abono;
    }

    /**
     * @return the pagoAbonoTransferencia
     */
    public BigDecimal getPagoAbonoTransferencia() {
        return pagoAbonoTransferencia;
    }

    /**
     * @param pagoAbonoTransferencia the pagoAbonoTransferencia to set
     */
    public void setPagoAbonoTransferencia(BigDecimal pagoAbonoTransferencia) {
        this.pagoAbonoTransferencia = pagoAbonoTransferencia;
    }

    /**
     * @return the bancoTransferencia
     */
    public String getBancoTransferencia() {
        return bancoTransferencia;
    }

    /**
     * @param bancoTransferencia the bancoTransferencia to set
     */
    public void setBancoTransferencia(String bancoTransferencia) {
        this.bancoTransferencia = bancoTransferencia;
    }

    /**
     * @return the numeroTransferencia
     */
    public String getNumeroTransferencia() {
        return numeroTransferencia;
    }

    /**
     * @return the pagoAbono
     */
    public BigDecimal getPagoAbono() {
        return pagoAbono;
    }

    /**
     * @return the subTotal
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return the subTotalDescuento
     */
    public BigDecimal getSubTotalDescuento() {
        return subTotalDescuento;
    }

    /**
     * @param subTotalDescuento the subTotalDescuento to set
     */
    public void setSubTotalDescuento(BigDecimal subTotalDescuento) {
        this.subTotalDescuento = subTotalDescuento;
    }

    /**
     * @return the subTotalIva
     */
    public BigDecimal getSubTotalIva() {
        return subTotalIva;
    }

    /**
     * @param subTotalIva the subTotalIva to set
     */
    public void setSubTotalIva(BigDecimal subTotalIva) {
        this.subTotalIva = subTotalIva;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the totalAbono
     */
    public BigDecimal getTotalAbono() {
        return totalAbono;
    }

    /**
     * @param totalAbono the totalAbono to set
     */
    public void setTotalAbono(BigDecimal totalAbono) {
        this.totalAbono = totalAbono;
    }

    /**
     * @return the totalSaldo
     */
    public BigDecimal getTotalSaldo() {
        return totalSaldo;
    }

    /**
     * @param totalSaldo the totalSaldo to set
     */
    public void setTotalSaldo(BigDecimal totalSaldo) {
        this.totalSaldo = totalSaldo;
    }

    /**
     * @return the origen
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * @param origen the origen to set
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * @return the nombreReferencia
     */
    public String getNombreReferencia() {
        return nombreReferencia;
    }

    /**
     * @param nombreReferencia the nombreReferencia to set
     */
    public void setNombreReferencia(String nombreReferencia) {
        this.nombreReferencia = nombreReferencia;
    }

    /**
     * @return the clienteFactura
     */
    public Usuario getClienteFactura() {
        return clienteFactura;
    }

    /**
     * @param clienteFactura the clienteFactura to set
     */
    public void setClienteFactura(Usuario clienteFactura) {
        this.clienteFactura = clienteFactura;
    }

    /**
     * @return the clienteOrden
     */
    public Usuario getClienteOrden() {
        return clienteOrden;
    }

    /**
     * @param clienteOrden the clienteOrden to set
     */
    public void setClienteOrden(Usuario clienteOrden) {
        this.clienteOrden = clienteOrden;
    }

    /**
     * @return the darDescuento
     */
    public Boolean getDarDescuento() {
        return darDescuento;
    }

    /**
     * @param darDescuento the darDescuento to set
     */
    public void setDarDescuento(Boolean darDescuento) {
        this.darDescuento = darDescuento;
    }

    /**
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    
    /**
     * @return the listaHorarioParqueadero
     */
    public List<HorarioParqueadero> getListaHorarioParqueadero() {
        return listaHorarioParqueadero;
    }

    /**
     * @param listaHorarioParqueadero the listaHorarioParqueadero to set
     */
    public void setListaHorarioParqueadero(List<HorarioParqueadero> listaHorarioParqueadero) {
        this.listaHorarioParqueadero = listaHorarioParqueadero;
    }

}
