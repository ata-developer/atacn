/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.CiudadBean;
import ec.com.ata.cn.logica.EstablecimientoBean;
import ec.com.ata.cn.logica.GrupoPrecioParteCategoriaVehiculoBean;
import ec.com.ata.cn.logica.HorarioParqueaderoBean;
import ec.com.ata.cn.logica.ImpuestoBean;
import ec.com.ata.cn.logica.MaterialBean;
import ec.com.ata.cn.logica.OrdenFechaBean;
import ec.com.ata.cn.logica.OrdenVehiculoBean;
import ec.com.ata.cn.logica.ParteBean;
import ec.com.ata.cn.logica.ProcesarHorarioBean;
import ec.com.ata.cn.logica.TipoDocumentoBean;
import ec.com.ata.cn.logica.TrabajoCategoriaPrecioBean;
import ec.com.ata.cn.logica.TrabajoParteBean;
import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.logica.VehiculoTrabajoBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Ciudad;
import ec.com.ata.cn.modelo.Equipo;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.GenericoEntidad;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.GrupoPrecioParteCategoriaVehiculo;
import ec.com.ata.cn.modelo.HorarioParqueadero;
import ec.com.ata.cn.modelo.Impuesto;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Material;
import ec.com.ata.cn.modelo.OrdenFecha;
import ec.com.ata.cn.modelo.OrdenVehiculo;
import ec.com.ata.cn.modelo.Parte;
import ec.com.ata.cn.modelo.Periodo;
import ec.com.ata.cn.modelo.PeriodoEstablecimientoFecha;
import ec.com.ata.cn.modelo.TipoDocumento;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import ec.com.ata.cn.modelo.TrabajoParte;
import ec.com.ata.cn.modelo.Usuario;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoTrabajo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.omnifaces.util.selectitems.SelectItemsBuilder;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author ATA1
 */
@ViewScoped
@Named
public class OrdenControlador extends BaseControlador {

    @Inject
    private VehiculoBean vehiculoBean;

    @Inject
    private UsuarioBean usuarioBean;

    @Inject
    private CiudadBean ciudadBean;

    @Inject
    private TipoDocumentoBean tipoDocumentoBean;

    @Inject
    private EstablecimientoBean establecimientoBean;

    @Inject
    private TrabajoCategoriaPrecioBean trabajoCategoriaTrabajoBean;

    @Inject
    private GrupoPrecioParteCategoriaVehiculoBean grupoPrecioParteCategoriaVehiculoBean;

    @Inject
    private ParteBean parteBean;

    @Inject
    private MaterialBean materialBean;

    @Inject
    private ProcesarHorarioBean procesarHorarioBean;

    @Inject
    private OrdenVehiculoBean ordenVehiculoBean;

    @Inject
    private VehiculoTrabajoBean vehiculoTrabajoBean;

    @Inject
    private TrabajoParteBean trabajoParteBean;

    @Inject
    private HorarioParqueaderoBean horarioParqueaderoBean;

    @Inject
    private ImpuestoBean impuestoBean;

    @Inject
    private OrdenFechaBean ordenFechaBean;

    private Establecimiento establecimiento;

    private Usuario clienteOrden;

    private Usuario clienteFactura;

    private Usuario clienteConsulta;

    private List<Vehiculo> listaVehiculos;

    private String numeroDocumento;

    private List<Usuario> listaClientes;

    private List<Establecimiento> listaEstablecimiento;

    private Boolean mismosDatosOrden;

    private Boolean llenarEsteMomento;

    private List<OrdenVehiculo> vehiculosCliente;

    private Vehiculo vehiculoSeleccionado;

    private List<SelectItem> listaSiNo;

    private String numeroDocumentoOrden;

    private String numeroDocumentoFactura;

    private String seleccionConsulta;

    private MarcaVehiculo marcaVehiculoSeleccionado;

    private OrdenVehiculo vehiculoParaTrabajos;

    private List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecioTmp;

    private TrabajoCategoriaPrecio trabajoCategoriaPrecio;

    private List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado;

    private List<GrupoPrecioParteCategoriaVehiculo> listaGrupoPrecioParteCategoriaVehiculo;

    private List<VehiculoTrabajo> listaVehiculoTrabajo;

    private List<SelectItem> listaTrabajosPorParte;

    private HashMap<OrdenVehiculo, List<TrabajoCategoriaPrecio>> mapaOrdenVehiculoListaTrabajos;

    private VehiculoTrabajo vehiculoTrabajoSeleccionado;

    private Parte parteSeleccionada;

    private TrabajoParte trabajoParteSeleccionada;

    private Material materialSeleccionado;

    private Long mesSeleccionado;

    private List<HashMap<String, PeriodoEstablecimientoFecha>> listaPEF;

    private Periodo periodoSeleccionadoParaCalendario;

    private Equipo equipoSeleccionado;

    private HashMap<OrdenVehiculo, List<VehiculoTrabajo>> mapaOrdenVehiculoTrabajo;

    private HashMap<VehiculoTrabajo, List<TrabajoParte>> mapaVehiculoTrabajoParte;

    private VehiculoTrabajo vehiculoTrabajoParaHorario;

    private BigDecimal totalDescuento;

    private BigDecimal totalPVP;

    private Boolean pagoTarjeta = null;

    private VehiculoTrabajo vehiculoTrabajoPago;

    private List<VehiculoTrabajo> listaVehiculoTrabajosFinal;

    private Boolean configurarPrecio;

    private String tipoPago;

    private String observacionPago;

    private String origen;

    private String nombreReferencia;

    private Date fechaYHoraActual;

    private OrdenFecha ordenFecha;

    private List<OrdenFecha> listaOrdenFecha;

    @PostConstruct
    public void init() {
        setEstablecimiento(new Establecimiento());
        setClienteOrden(new Usuario());
        setClienteFactura(new Usuario());
        setClienteConsulta(new Usuario());
        setListaVehiculos(new ArrayList<Vehiculo>());
        setListaClientes(new ArrayList<Usuario>());
        setMismosDatosOrden(null);
        setLlenarEsteMomento(null);
        setVehiculosCliente(new ArrayList<OrdenVehiculo>());
        setListaSiNo(new ArrayList<SelectItem>());
        generarSeleccionSino();
        setNumeroDocumento(new String());
        setNumeroDocumentoOrden(new String());
        setNumeroDocumentoFactura(new String());
        setMarcaVehiculoSeleccionado(new MarcaVehiculo());
        setVehiculoParaTrabajos(new OrdenVehiculo());
        setListaTrabajoCategoriaPrecioTmp(new ArrayList<TrabajoCategoriaPrecio>());
        setTrabajoCategoriaPrecio(new TrabajoCategoriaPrecio());
        setListaVehiculoTrabajo(new ArrayList<VehiculoTrabajo>());
        setMapaOrdenVehiculoListaTrabajos(new HashMap<OrdenVehiculo, List<TrabajoCategoriaPrecio>>());
        setMapaOrdenVehiculoTrabajo(new HashMap<OrdenVehiculo, List<VehiculoTrabajo>>());
        setMapaVehiculoTrabajoParte(new HashMap<VehiculoTrabajo, List<TrabajoParte>>());
        setVehiculoTrabajoPago(new VehiculoTrabajo());
        setListaVehiculoTrabajosFinal(new ArrayList<VehiculoTrabajo>());
        setConfigurarPrecio(Boolean.FALSE);
        getVehiculoParaTrabajos().setDarDescuento(Boolean.FALSE);
        setPagoTarjeta(null);
        getVehiculoParaTrabajos().setSubTotal(new BigDecimal("0"));
        getVehiculoParaTrabajos().setSubTotalDescuento(new BigDecimal("0"));
        getVehiculoParaTrabajos().setSubTotalIva(new BigDecimal("0"));
        getVehiculoParaTrabajos().setTotal(new BigDecimal("0"));
        getVehiculoParaTrabajos().setTotalAbono(new BigDecimal("0"));
        getVehiculoParaTrabajos().setTotalSaldo(new BigDecimal("0"));
        getVehiculoParaTrabajos().setPagoAbono(new BigDecimal("0"));
        getVehiculoParaTrabajos().setImporteAbono(new BigDecimal("0"));
        setFechaYHoraActual(new Date(System.currentTimeMillis()));
        setOrdenFecha(new OrdenFecha());
        setListaOrdenFecha(new ArrayList<OrdenFecha>());
    }

    public String generarDetalleTrabajo(VehiculoTrabajo vehiculoTrabajo) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("vehiculoTrabajo", vehiculoTrabajo);
        List<TrabajoParte> ListaTrabajoParteTmp = trabajoParteBean.obtenerListaPorParametros(parametros);
        for (TrabajoParte trabajoParte : ListaTrabajoParteTmp) {
            
        }
        return "";
    }

    public void cambiarFechaFin(Date fechaEntrada) {
        System.out.println("cambiarFechaFin");
        this.ordenFecha.setFechaFin(fechaEntrada);
    }

    public void agregarOrdenFecha(OrdenFecha ordenFecha) {
        try {
            ordenFecha.setOrdenVehiculo(vehiculoParaTrabajos);
            if (null == ordenFecha.getParqueadero().getIdParqueadero()) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + Constante.MENSAJE_SELECCION_PARQUEADERO);
                return;
            }

            if (null == ordenFecha.getEquipo().getIdEquipo()) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + Constante.MENSAJE_SELECCION_EQUIPO);
                return;
            }

            if (null == ordenFecha.getFechaInicio()) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + Constante.MENSAJE_SELECCION_FECHA_INICIO);
                return;
            }

            if (null == ordenFecha.getFechaFin()) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + Constante.MENSAJE_SELECCION_FECHA_FIN);
                return;
            }
            ordenFechaBean.crear(ordenFecha);
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("ordenVehiculo", vehiculoParaTrabajos);
            setListaOrdenFecha(ordenFechaBean.obtenerListaPorParametros(parametros));
            setOrdenFecha(new OrdenFecha());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void eliminarOrdenFecha(OrdenFecha ordenFecha) {
        try {
            ordenFechaBean.eliminar(ordenFecha.getIdOrdenFecha());
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("ordenVehiculo", vehiculoParaTrabajos);
            setListaOrdenFecha(ordenFechaBean.obtenerListaPorParametros(parametros));
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void setearACero() {
        try {
            System.out.println("setearACero");
            if (getVehiculoParaTrabajos().getDarDescuento() == false) {
                for (OrdenVehiculo ordenVehiculo : mapaOrdenVehiculoTrabajo.keySet()) {
                    List<VehiculoTrabajo> listaVehiculoTrabajos = mapaOrdenVehiculoTrabajo.get(ordenVehiculo);
                    List<VehiculoTrabajo> listaVehiculoTrabajosTmp = new ArrayList<>();
                    for (VehiculoTrabajo vehiculoTrabajoTmp : listaVehiculoTrabajos) {
                        vehiculoTrabajoTmp = vehiculoTrabajoBean.obtenerPorCodigo(vehiculoTrabajoTmp.getIdVehiculoTrabajo());
                        vehiculoTrabajoTmp.setDescuento(new BigDecimal("0"));
                        vehiculoTrabajoTmp = vehiculoTrabajoBean.modificar(vehiculoTrabajoTmp);
                        listaVehiculoTrabajosTmp.add(vehiculoTrabajoTmp);
                    }
                    mapaOrdenVehiculoTrabajo.put(ordenVehiculo, listaVehiculoTrabajosTmp);
                    setListaVehiculoTrabajosFinal(listaVehiculoTrabajosFinal);
                    generartListaVehiculoTrabajoPagoEfectivoSiNo();
                    return;
                }
            }
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void calcularImporteAbono() {
        System.out.println("getTotalAbono():" + getVehiculoParaTrabajos().getTotalAbono());
        System.out.println("getPagoAbono():" + getVehiculoParaTrabajos().getPagoAbono());
        getVehiculoParaTrabajos().setImporteAbono(getVehiculoParaTrabajos().getPagoAbono().subtract(getVehiculoParaTrabajos().getTotalAbono()));
        System.out.println("getImporteAbono():" + getVehiculoParaTrabajos().getImporteAbono());
    }

    public VehiculoTrabajo procesarValoresDescuento(VehiculoTrabajo vehiculoTrabajoEntrada) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("impuesto", "IVA");
        List<Impuesto> listaImpuesto = impuestoBean.obtenerListaPorParametros(parametros);
        Impuesto impuesto = listaImpuesto.get(0);
        System.out.println("impuesto factor: " + impuesto.getFactor());
        switch (vehiculoParaTrabajos.getTipoPago()) {
            case "EFECTIVO":
                return calcularDescuentoEfectivoTransferencia(vehiculoTrabajoEntrada, impuesto);
            case "TRANSFERENCIA":
                // Statements
                return calcularDescuentoEfectivoTransferencia(vehiculoTrabajoEntrada, impuesto);
            case "TARJETA":
                // Statements
                return calcularDescuentoTarjeta(vehiculoTrabajoEntrada, impuesto);
            // You can have any number of case statements.
            default:
                return new VehiculoTrabajo();
        }
    }

    private void procesarTotalesEfectivoTransferencia(VehiculoTrabajo vehiculoTrabajo) {
        getVehiculoParaTrabajos().setSubTotal(getVehiculoParaTrabajos().getSubTotal().add(vehiculoTrabajo.getPrecioDescuento()));
        getVehiculoParaTrabajos().setSubTotalDescuento(getVehiculoParaTrabajos().getSubTotalDescuento().add(vehiculoTrabajo.getDescuento()));
        getVehiculoParaTrabajos().setTotal(getVehiculoParaTrabajos().getTotal().add(vehiculoTrabajo.getPrecioDescuentoFactura()));
        getVehiculoParaTrabajos().setTotalAbono(getVehiculoParaTrabajos().getTotalAbono().add(vehiculoTrabajo.getPrecioAbonoEfectivo()));
        getVehiculoParaTrabajos().setTotalSaldo(getVehiculoParaTrabajos().getTotalSaldo().add(vehiculoTrabajo.getPrecioSaldoEfectivo()));
    }

    private void procesarTotalesTarjeta(VehiculoTrabajo vehiculoTrabajo) {
        getVehiculoParaTrabajos().setSubTotal(getVehiculoParaTrabajos().getSubTotal().add(vehiculoTrabajo.getPrecioVentaPublico()));
        getVehiculoParaTrabajos().setSubTotalDescuento(getVehiculoParaTrabajos().getSubTotalDescuento().add(vehiculoTrabajo.getDescuento()));
        getVehiculoParaTrabajos().setTotal(getVehiculoParaTrabajos().getTotal().add(vehiculoTrabajo.getPrecioVentaPublicoFactura()));
        getVehiculoParaTrabajos().setTotalAbono(getVehiculoParaTrabajos().getTotalAbono().add(vehiculoTrabajo.getPrecioAbonoTarjeta()));
        getVehiculoParaTrabajos().setTotalSaldo(getVehiculoParaTrabajos().getTotalSaldo().add(vehiculoTrabajo.getPrecioSaldoTarjeta()));
    }

    public void onRowEdit(RowEditEvent event) {
        VehiculoTrabajo vehiculoTrabajoAux = (VehiculoTrabajo) event.getObject();
        System.out.println("vehiculoTrabajo.getDescuento(): " + vehiculoTrabajoAux.getDescuento());
        System.out.println("vehiculoTrabajo.getIdVehiculoTrabajo(): " + vehiculoTrabajoAux.getIdVehiculoTrabajo());
        try {
            vehiculoTrabajoAux.setTipoPago(tipoPago);
            vehiculoTrabajoAux = procesarValoresDescuento(vehiculoTrabajoAux);
            vehiculoTrabajoBean.modificar(vehiculoTrabajoAux);
            List<VehiculoTrabajo> listaVehiculoTrabajoTemporal = new ArrayList<>();
            getVehiculoParaTrabajos().setSubTotal(new BigDecimal("0"));
            getVehiculoParaTrabajos().setSubTotalDescuento(new BigDecimal("0"));
            getVehiculoParaTrabajos().setTotal(new BigDecimal("0"));
            getVehiculoParaTrabajos().setTotalAbono(new BigDecimal("0"));
            getVehiculoParaTrabajos().setTotalSaldo(new BigDecimal("0"));
            for (VehiculoTrabajo vehiculoTrabajo1 : listaVehiculoTrabajosFinal) {
                VehiculoTrabajo vehiculoTrabajo2 = vehiculoTrabajoBean.obtenerPorCodigo(vehiculoTrabajo1.getIdVehiculoTrabajo());
                switch (getVehiculoParaTrabajos().getTipoPago()) {
                    case "EFECTIVO":
                        procesarTotalesEfectivoTransferencia(vehiculoTrabajo2);
                        break;
                    case "TRANSFERENCIA":
                        procesarTotalesEfectivoTransferencia(vehiculoTrabajo2);
                        break;
                    case "TARJETA":
                        procesarTotalesTarjeta(vehiculoTrabajo2);
                        break;
                    default:
                        break;
                }
                listaVehiculoTrabajoTemporal.add(vehiculoTrabajo2);
            }
            setListaVehiculoTrabajosFinal(listaVehiculoTrabajoTemporal);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void onRowEditHorario(RowEditEvent event) {
        VehiculoTrabajo vehiculoTrabajoAux = (VehiculoTrabajo) event.getObject();
        System.out.println("vehiculoTrabajo.getDescuento(): " + vehiculoTrabajoAux.getDescuento());
        System.out.println("vehiculoTrabajo.getIdVehiculoTrabajo(): " + vehiculoTrabajoAux.getIdVehiculoTrabajo());
        System.out.println("vehiculoTrabajo.getFechaInicio(): " + vehiculoTrabajoAux.getFechaInicio());
        System.out.println("vehiculoTrabajo.getFechaFin(): " + vehiculoTrabajoAux.getFechaFin());
        System.out.println("vehiculoTrabajo.getEquipo(): " + vehiculoTrabajoAux.getEquipo().getEquipo());
        try {
            vehiculoTrabajoBean.modificar(vehiculoTrabajoAux);

            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void onRowCancel(RowEditEvent event) {
        System.out.println("Cancelado");

    }

    public void onRowCancelHorario(RowEditEvent event) {
        System.out.println("Cancelado");

    }

    public void calcularDescuento(VehiculoTrabajo vehiculoTrabajoEntrada) {
        if (pagoTarjeta) {
            vehiculoTrabajoEntrada.setPrecioVentaPublico(vehiculoTrabajoEntrada.getPrecioVentaPublico().subtract(vehiculoTrabajoEntrada.getDescuento()));
            vehiculoTrabajoEntrada.setPrecioSaldoTarjeta(vehiculoTrabajoEntrada.getPrecioVentaPublico().subtract(vehiculoTrabajoEntrada.getPrecioAbonoTarjeta()));
        } else {
            vehiculoTrabajoEntrada.setDescuento(vehiculoTrabajoEntrada.getDescuento().subtract(vehiculoTrabajoEntrada.getDescuento()));
            vehiculoTrabajoEntrada.setPrecioSaldoEfectivo(vehiculoTrabajoEntrada.getPrecioDescuento().subtract(vehiculoTrabajoEntrada.getPrecioAbonoEfectivo()));

        }
    }

    public void guardarPago(VehiculoTrabajo vehiculoTrabajoEntrada) {
        try {
            vehiculoTrabajoBean.modificar(vehiculoTrabajoEntrada);
            setConfigurarPrecio(false);
            List<VehiculoTrabajo> listaVehiculoTmp = new ArrayList<>();
            for (VehiculoTrabajo vehiculoTrabajo : listaVehiculoTrabajosFinal) {
                VehiculoTrabajo vehiculoTrabajoTmp = vehiculoTrabajoBean.obtenerPorCodigo(vehiculoTrabajo.getIdVehiculoTrabajo());
                listaVehiculoTmp.add(vehiculoTrabajoTmp);
            }
            setListaVehiculoTrabajosFinal(listaVehiculoTmp);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public void seleccionarVehiculoTrabajoPago(VehiculoTrabajo vehiculoTrabajoEntrada) {

        setConfigurarPrecio(true);
        setVehiculoTrabajoPago(vehiculoTrabajoEntrada);
    }

    public void generartListaVehiculoTrabajoPago() {
        List<VehiculoTrabajo> listaVehiculoTrabajosFinalTmp = new ArrayList<>();
        for (OrdenVehiculo ordenVehiculo : mapaOrdenVehiculoTrabajo.keySet()) {
            List<VehiculoTrabajo> listaVehiculoTrabajos = mapaOrdenVehiculoTrabajo.get(ordenVehiculo);
            for (VehiculoTrabajo listaVehiculoTrabajoTmp : listaVehiculoTrabajos) {
                listaVehiculoTrabajosFinalTmp.add(listaVehiculoTrabajoTmp);
            }
        }
        setListaVehiculoTrabajosFinal(listaVehiculoTrabajosFinalTmp);
    }

    private void calculaPreciosListaFactura() throws Exception {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("impuesto", "IVA");
        List<Impuesto> listaImpuesto = impuestoBean.obtenerListaPorParametros(parametros);
        Impuesto impuesto = listaImpuesto.get(0);
        List<VehiculoTrabajo> listaVehiculoTrabajosFinalTmp = new ArrayList<>();
        for (OrdenVehiculo ordenVehiculo : mapaOrdenVehiculoTrabajo.keySet()) {
            List<VehiculoTrabajo> listaVehiculoTrabajos = mapaOrdenVehiculoTrabajo.get(ordenVehiculo);
            for (VehiculoTrabajo vehiculoTrabajoTmp : listaVehiculoTrabajos) {
                vehiculoTrabajoTmp.setPorcentajeIva(impuesto.getFactor());
                vehiculoTrabajoTmp.setValorIva(vehiculoTrabajoTmp.getPrecioDescuento().multiply(vehiculoTrabajoTmp.getPorcentajeIva()));
                vehiculoTrabajoTmp.setPrecioDescuentoFactura(vehiculoTrabajoTmp.getPrecioDescuentoFactura().add(vehiculoTrabajoTmp.getValorIva()));
                vehiculoTrabajoTmp.setPrecioVentaPublicoFactura(vehiculoTrabajoTmp.getPrecioVentaPublicoFactura().add(vehiculoTrabajoTmp.getValorIva()));
                vehiculoTrabajoTmp.setPrecioSaldoTarjeta(vehiculoTrabajoTmp.getPrecioVentaPublicoFactura().subtract(vehiculoTrabajoTmp.getPrecioAbonoTarjeta()));
                vehiculoTrabajoTmp.setPrecioSaldoEfectivo(vehiculoTrabajoTmp.getPrecioDescuentoFactura().subtract(vehiculoTrabajoTmp.getPrecioAbonoEfectivo()));
                vehiculoTrabajoTmp = vehiculoTrabajoBean.modificar(vehiculoTrabajoTmp);
                listaVehiculoTrabajosFinalTmp.add(vehiculoTrabajoTmp);
            }
        }
        setListaVehiculoTrabajosFinal(listaVehiculoTrabajosFinalTmp);
    }

    public List<VehiculoTrabajo> generarListaVehiculoTrabajo() {
        List<VehiculoTrabajo> listaVehiculoTrabajosFinalTmp = new ArrayList<>();
        for (OrdenVehiculo ordenVehiculo : mapaOrdenVehiculoTrabajo.keySet()) {
            List<VehiculoTrabajo> listaVehiculoTrabajos = mapaOrdenVehiculoTrabajo.get(ordenVehiculo);
            for (VehiculoTrabajo vehiculoTrabajoTmp : listaVehiculoTrabajos) {
                VehiculoTrabajo vehiculoTrabajoTmp2 = vehiculoTrabajoBean.obtenerPorCodigo(vehiculoTrabajoTmp.getIdVehiculoTrabajo());
                listaVehiculoTrabajosFinalTmp.add(vehiculoTrabajoTmp2);
            }
        }
        return listaVehiculoTrabajosFinalTmp;
    }

    private void calcularListaPagoEfectivoTransferencia() throws Exception {
        getVehiculoParaTrabajos().setSubTotal(new BigDecimal("0"));
        getVehiculoParaTrabajos().setSubTotalDescuento(new BigDecimal("0"));
        getVehiculoParaTrabajos().setSubTotalIva(new BigDecimal("0"));
        getVehiculoParaTrabajos().setTotal(new BigDecimal("0"));
        getVehiculoParaTrabajos().setTotalAbono(new BigDecimal("0"));
        getVehiculoParaTrabajos().setTotalSaldo(new BigDecimal("0"));
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("impuesto", "IVA");
        List<Impuesto> listaImpuesto = impuestoBean.obtenerListaPorParametros(parametros);
        Impuesto impuesto = listaImpuesto.get(0);
        List<VehiculoTrabajo> listaVehiculoTrabajosFinalTmp = new ArrayList<>();
        for (OrdenVehiculo ordenVehiculo : mapaOrdenVehiculoTrabajo.keySet()) {
            List<VehiculoTrabajo> listaVehiculoTrabajos = mapaOrdenVehiculoTrabajo.get(ordenVehiculo);
            for (VehiculoTrabajo vehiculoTrabajoTmp : listaVehiculoTrabajos) {
                vehiculoTrabajoTmp = vehiculoTrabajoBean.obtenerPorCodigo(vehiculoTrabajoTmp.getIdVehiculoTrabajo());
                vehiculoTrabajoTmp.setTipoPago(tipoPago);
                vehiculoTrabajoTmp.setPrecioDescuentoFactura(vehiculoTrabajoTmp.getPrecioDescuento().subtract(vehiculoTrabajoTmp.getDescuento()));
                vehiculoTrabajoTmp.setPorcentajeIva(impuesto.getFactor());
                vehiculoTrabajoTmp.setValorIva(vehiculoTrabajoTmp.getPrecioDescuento().multiply(vehiculoTrabajoTmp.getPorcentajeIva()));
                vehiculoTrabajoTmp.setPrecioDescuentoFactura(vehiculoTrabajoTmp.getPrecioDescuentoFactura().add(vehiculoTrabajoTmp.getValorIva()));
                vehiculoTrabajoTmp.setPrecioSaldoEfectivo(vehiculoTrabajoTmp.getPrecioDescuentoFactura().subtract(vehiculoTrabajoTmp.getPrecioAbonoEfectivo()));
                getVehiculoParaTrabajos().setSubTotal(getVehiculoParaTrabajos().getSubTotal().add(vehiculoTrabajoTmp.getPrecioDescuento()));
                getVehiculoParaTrabajos().setSubTotalDescuento(getVehiculoParaTrabajos().getSubTotalDescuento().add(vehiculoTrabajoTmp.getDescuento()));
                getVehiculoParaTrabajos().setTotal(getVehiculoParaTrabajos().getTotal().add(vehiculoTrabajoTmp.getPrecioDescuentoFactura()));
                getVehiculoParaTrabajos().setTotalAbono(getVehiculoParaTrabajos().getTotalAbono().add(vehiculoTrabajoTmp.getPrecioAbonoEfectivo()));
                getVehiculoParaTrabajos().setTotalSaldo(getVehiculoParaTrabajos().getTotalSaldo().add(vehiculoTrabajoTmp.getPrecioSaldoEfectivo()));
                vehiculoTrabajoTmp = vehiculoTrabajoBean.modificar(vehiculoTrabajoTmp);
                listaVehiculoTrabajosFinalTmp.add(vehiculoTrabajoTmp);
            }
        }
        setListaVehiculoTrabajosFinal(listaVehiculoTrabajosFinalTmp);
    }

    private VehiculoTrabajo calcularDescuentoEfectivoTransferencia(VehiculoTrabajo vehiculoTrabajoTmp, Impuesto impuesto) {
        vehiculoTrabajoTmp.setPrecioDescuentoFactura(vehiculoTrabajoTmp.getPrecioDescuento().subtract(vehiculoTrabajoTmp.getDescuento()));
        vehiculoTrabajoTmp.setPorcentajeIva(impuesto.getFactor());
        vehiculoTrabajoTmp.setValorIva(vehiculoTrabajoTmp.getPrecioDescuento().multiply(vehiculoTrabajoTmp.getPorcentajeIva()));
        vehiculoTrabajoTmp.setPrecioDescuentoFactura(vehiculoTrabajoTmp.getPrecioDescuentoFactura().add(vehiculoTrabajoTmp.getValorIva()));
        vehiculoTrabajoTmp.setPrecioSaldoEfectivo(vehiculoTrabajoTmp.getPrecioDescuentoFactura().subtract(vehiculoTrabajoTmp.getPrecioAbonoEfectivo()));
        return vehiculoTrabajoTmp;
    }

    private VehiculoTrabajo calcularDescuentoTarjeta(VehiculoTrabajo vehiculoTrabajoTmp, Impuesto impuesto) {
        vehiculoTrabajoTmp.setPrecioVentaPublicoFactura(vehiculoTrabajoTmp.getPrecioVentaPublico().subtract(vehiculoTrabajoTmp.getDescuento()));
        vehiculoTrabajoTmp.setPorcentajeIva(impuesto.getFactor());
        vehiculoTrabajoTmp.setValorIva(vehiculoTrabajoTmp.getPrecioVentaPublicoFactura().multiply(vehiculoTrabajoTmp.getPorcentajeIva()));
        vehiculoTrabajoTmp.setPrecioVentaPublicoFactura(vehiculoTrabajoTmp.getPrecioVentaPublicoFactura().add(vehiculoTrabajoTmp.getValorIva()));
        vehiculoTrabajoTmp.setPrecioSaldoTarjeta(vehiculoTrabajoTmp.getPrecioVentaPublicoFactura().subtract(vehiculoTrabajoTmp.getPrecioAbonoTarjeta()));
        return vehiculoTrabajoTmp;
    }

    private void calcularListaTarjeta() throws Exception {
        getVehiculoParaTrabajos().setSubTotal(new BigDecimal("0"));
        getVehiculoParaTrabajos().setSubTotalDescuento(new BigDecimal("0"));
        getVehiculoParaTrabajos().setSubTotalIva(new BigDecimal("0"));
        getVehiculoParaTrabajos().setTotal(new BigDecimal("0"));
        getVehiculoParaTrabajos().setTotalAbono(new BigDecimal("0"));
        getVehiculoParaTrabajos().setTotalSaldo(new BigDecimal("0"));
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("impuesto", "IVA");
        List<Impuesto> listaImpuesto = impuestoBean.obtenerListaPorParametros(parametros);
        Impuesto impuesto = listaImpuesto.get(0);
        List<VehiculoTrabajo> listaVehiculoTrabajosFinalTmp = new ArrayList<>();
        for (OrdenVehiculo ordenVehiculo : mapaOrdenVehiculoTrabajo.keySet()) {
            List<VehiculoTrabajo> listaVehiculoTrabajos = mapaOrdenVehiculoTrabajo.get(ordenVehiculo);
            for (VehiculoTrabajo vehiculoTrabajoTmp : listaVehiculoTrabajos) {
                vehiculoTrabajoTmp = vehiculoTrabajoBean.obtenerPorCodigo(vehiculoTrabajoTmp.getIdVehiculoTrabajo());
                vehiculoTrabajoTmp.setTipoPago(tipoPago);
                vehiculoTrabajoTmp.setPrecioVentaPublicoFactura(vehiculoTrabajoTmp.getPrecioVentaPublico().subtract(vehiculoTrabajoTmp.getDescuento()));
                vehiculoTrabajoTmp.setPorcentajeIva(impuesto.getFactor());
                vehiculoTrabajoTmp.setValorIva(vehiculoTrabajoTmp.getPrecioVentaPublicoFactura().multiply(vehiculoTrabajoTmp.getPorcentajeIva()));
                vehiculoTrabajoTmp.setPrecioVentaPublicoFactura(vehiculoTrabajoTmp.getPrecioVentaPublicoFactura().add(vehiculoTrabajoTmp.getValorIva()));
                vehiculoTrabajoTmp.setPrecioSaldoTarjeta(vehiculoTrabajoTmp.getPrecioVentaPublicoFactura().subtract(vehiculoTrabajoTmp.getPrecioAbonoTarjeta()));
                getVehiculoParaTrabajos().setSubTotal(getVehiculoParaTrabajos().getSubTotal().add(vehiculoTrabajoTmp.getPrecioVentaPublico()));
                getVehiculoParaTrabajos().setSubTotalDescuento(getVehiculoParaTrabajos().getSubTotalDescuento().add(vehiculoTrabajoTmp.getDescuento()));
                getVehiculoParaTrabajos().setTotal(getVehiculoParaTrabajos().getTotal().add(vehiculoTrabajoTmp.getPrecioVentaPublicoFactura()));
                getVehiculoParaTrabajos().setTotalAbono(getVehiculoParaTrabajos().getTotalAbono().add(vehiculoTrabajoTmp.getPrecioAbonoTarjeta()));
                getVehiculoParaTrabajos().setTotalSaldo(getVehiculoParaTrabajos().getTotalSaldo().add(vehiculoTrabajoTmp.getPrecioSaldoTarjeta()));
                vehiculoTrabajoTmp = vehiculoTrabajoBean.modificar(vehiculoTrabajoTmp);
                listaVehiculoTrabajosFinalTmp.add(vehiculoTrabajoTmp);
            }
        }
        setListaVehiculoTrabajosFinal(listaVehiculoTrabajosFinalTmp);
    }

    public void generartListaVehiculoTrabajoPagoEfectivoSiNo() {
        try {
            System.out.println("generartListaVehiculoTrabajoPagoEfectivoSiNo: " + getVehiculoParaTrabajos());
            switch (getVehiculoParaTrabajos().getTipoPago()) {
                case "EFECTIVO":
                    calcularListaPagoEfectivoTransferencia();
                    addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
                    break; // optional
                case "TRANSFERENCIA":
                    // Statements
                    calcularListaPagoEfectivoTransferencia();
                    addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
                    break; // optional
                case "TARJETA":
                    // Statements
                    calcularListaTarjeta();
                    addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
                    break;
                // You can have any number of case statements.
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void guardarHorarioVehiculoTrabajo(HorarioParqueadero horarioParqueaderoEntrada, VehiculoTrabajo vehiculoTrabajoEntrada) {
        try {
            if (null != horarioParqueaderoEntrada.getVehiculoTrabajo()
                    && horarioParqueaderoEntrada.getVehiculoTrabajo().getIdVehiculoTrabajo().equals(vehiculoTrabajoEntrada.getIdVehiculoTrabajo())) {
                horarioParqueaderoEntrada.setVehiculoTrabajo(null);
                horarioParqueaderoBean.modificar(horarioParqueaderoEntrada);
                cargarFechasPrivado();
                addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
                return;
            }
            if (null != horarioParqueaderoEntrada.getVehiculoTrabajo()
                    && !horarioParqueaderoEntrada.getVehiculoTrabajo().getIdVehiculoTrabajo().equals(vehiculoTrabajoEntrada.getIdVehiculoTrabajo())) {
                addInfoMessage(Constante.MENSAJE_SELECCIONADO, Constante.MENSAJE_SELECCIONADO);
                return;
            }
            System.out.println("guardarHorarioVehiculoTrabajo");
            vehiculoTrabajoEntrada = vehiculoTrabajoBean.modificar(vehiculoTrabajoEntrada);
            horarioParqueaderoEntrada.setVehiculoTrabajo(vehiculoTrabajoEntrada);
            horarioParqueaderoEntrada.setOrdenVehiculo(vehiculoTrabajoEntrada.getOrdenVehiculo());
            horarioParqueaderoBean.modificar(horarioParqueaderoEntrada);
            cargarFechasPrivado();
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    private void cargarFechasPrivado() {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("periodo", getPeriodoSeleccionadoParaCalendario());
        parametros.put("mes", getMesSeleccionado());
        parametros.put("diaMesOrderByAsc", null);
        setListaPEF(procesarHorarioBean.generarCalendarioHash(parametros));
    }

    public void cargarFechas() {
        try {
            cargarFechasPrivado();
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public List<SelectItem> generarSelectItemMaterial() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Material materialTmp : materialBean.obtenerLista()) {
            selectItemsBuilder.add(materialTmp, materialTmp.getCodigo() + " - " + materialTmp.getDescripcion() + " - " + materialTmp.getColor().getColor());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemPartesAsiento() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp : getListaTrabajoCategoriaPrecioTmp()) {
            selectItemsBuilder.add(trabajoCategoriaPrecioTmp, trabajoCategoriaPrecioTmp.getTrabajo().getDescripcion() + " - " + trabajoCategoriaPrecioTmp.getPrecioVentaPublico() + " - " + trabajoCategoriaPrecioTmp.getPrecioDescuento());
        }
        return selectItemsBuilder.buildList();
    }

    public void agregarTrabajoParte(TrabajoParte trabajoParteEntrada) {
        try {
            System.out.println("agregarTrabajoParte: " + trabajoParteEntrada);
            List<TrabajoParte> ListaTrabajoParteTmp = getMapaVehiculoTrabajoParte().get(getVehiculoTrabajoSeleccionado());
            trabajoParteEntrada.setVehiculoTrabajo(getVehiculoTrabajoSeleccionado());
            trabajoParteEntrada = trabajoParteBean.crear(trabajoParteEntrada);
            ListaTrabajoParteTmp.add(trabajoParteEntrada);
            getMapaVehiculoTrabajoParte().put(getVehiculoTrabajoSeleccionado(), ListaTrabajoParteTmp);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public void actualizarTrabajoParte(TrabajoParte trabajoParteEntrada) {
        try {
            System.out.println("agregarTrabajoParte: " + trabajoParteEntrada);
            TrabajoParte trabajoParteTemp = new TrabajoParte();
            List<TrabajoParte> ListaTrabajoParteTmp = getMapaVehiculoTrabajoParte().get(getVehiculoTrabajoSeleccionado());
            for (TrabajoParte trabajoParte : ListaTrabajoParteTmp) {
                if (trabajoParte.getIdTrabajoParte().longValue() == trabajoParteEntrada.getIdTrabajoParte().longValue()) {
                    trabajoParteTemp = trabajoParte;
                }
            }
            ListaTrabajoParteTmp.remove(trabajoParteTemp);
            trabajoParteEntrada = trabajoParteBean.modificar(trabajoParteEntrada);
            ListaTrabajoParteTmp.add(trabajoParteEntrada);
            getMapaVehiculoTrabajoParte().put(getVehiculoTrabajoSeleccionado(), ListaTrabajoParteTmp);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public void seleccionarParte(VehiculoTrabajo vehiculoTrabajoEntrada) {
        setVehiculoTrabajoSeleccionado(vehiculoTrabajoEntrada);
        HashMap parametros = new HashMap<>();
        parametros.put("padre", vehiculoTrabajoEntrada.getPartePrincipal());
        List<Parte> listaPartesTrabajo = parteBean.obtenerListaPorParametros(parametros);
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Parte parteTmp2 : listaPartesTrabajo) {
            TrabajoParte trabajoParteTmp = new TrabajoParte();
            trabajoParteTmp.setPartePadre(trabajoCategoriaPrecio.getParte());
            trabajoParteTmp.setParte(parteTmp2);
            selectItemsBuilder.add(trabajoParteTmp, parteTmp2.getParte());
        }
        listaTrabajosPorParte = selectItemsBuilder.buildList();

    }

    public void eliminarParte(TrabajoParte trabajoParte, VehiculoTrabajo vehiculoTrabajo) {
        List<TrabajoParte> listaParteTrabajo = getMapaVehiculoTrabajoParte().get(vehiculoTrabajo);
        listaParteTrabajo.remove(trabajoParte);
        getMapaVehiculoTrabajoParte().put(vehiculoTrabajo, listaParteTrabajo);
    }

    public void eliminarParte(TrabajoParte trabajoParteEntrada) {
        try {
            List<TrabajoParte> listaParteTrabajo = getMapaVehiculoTrabajoParte().get(getVehiculoTrabajoSeleccionado());
            listaParteTrabajo.remove(trabajoParteEntrada);
            if (null != trabajoParteEntrada.getIdTrabajoParte()) {
                trabajoParteBean.eliminar(trabajoParteEntrada.getIdTrabajoParte());
            }
            getMapaVehiculoTrabajoParte().put(getVehiculoTrabajoSeleccionado(), listaParteTrabajo);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public void eliminarTrabajo(VehiculoTrabajo vehiculoTrabajoEntrada) {
        try {
            //Lista<TrabajoParte> listaTrabajoParteTmp2 = getMapaVehiculoTrabajoParte()
            VehiculoTrabajo vehiculoTrabajoTmp2 = new VehiculoTrabajo();
            for (VehiculoTrabajo vehiculoTrabajoTmp : getMapaVehiculoTrabajoParte().keySet()) {
                if (Objects.equals(vehiculoTrabajoTmp.getIdVehiculoTrabajo(), vehiculoTrabajoEntrada.getIdVehiculoTrabajo())) {
                    vehiculoTrabajoTmp2 = vehiculoTrabajoTmp;
                }
            }
            getMapaVehiculoTrabajoParte().remove(vehiculoTrabajoTmp2);
            if (null != vehiculoTrabajoEntrada.getIdVehiculoTrabajo()) {
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("vehiculoTrabajo", vehiculoTrabajoEntrada);
                List<TrabajoParte> listaTrabajoParte = trabajoParteBean.obtenerListaPorParametros(parametros);
                for (TrabajoParte trabajoParte : listaTrabajoParte) {
                    trabajoParteBean.eliminar(trabajoParte.getIdTrabajoParte());
                }
                vehiculoTrabajoBean.eliminar(vehiculoTrabajoEntrada.getIdVehiculoTrabajo());
            }
            List<VehiculoTrabajo> listaVehiculoTrabajoTmp2 = getMapaOrdenVehiculoTrabajo().get(getVehiculoParaTrabajos());
            listaVehiculoTrabajoTmp2.remove(vehiculoTrabajoEntrada);
            getMapaOrdenVehiculoTrabajo().put(getVehiculoParaTrabajos(), listaVehiculoTrabajoTmp2);
            System.out.println("eliminar trabajo");
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            e.printStackTrace();
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public void agregarParte() {
        System.out.println("agregar parte");
    }

    public void eliminarVehiculoOrden(OrdenVehiculo ordenVehiculoEntrada) {
        try {
            getMapaOrdenVehiculoListaTrabajos().remove(ordenVehiculoEntrada);
            getMapaOrdenVehiculoTrabajo().remove(ordenVehiculoEntrada);
            getVehiculosCliente().remove(ordenVehiculoEntrada);
            if (null != ordenVehiculoEntrada.getIdOrdenVehiculo()) {
                ordenVehiculoBean.eliminar(ordenVehiculoEntrada.getIdOrdenVehiculo());
            }
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public void agregarTrabajo(OrdenVehiculo ordenVehiculoEntrada) {
        try {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("impuesto", "IVA");
            List<Impuesto> listaImpuesto = impuestoBean.obtenerListaPorParametros(parametros);
            Impuesto impuesto = listaImpuesto.get(0);
            VehiculoTrabajo vehiculoTrabajo = new VehiculoTrabajo();
            vehiculoTrabajo.setOrdenVehiculo(ordenVehiculoEntrada);
            vehiculoTrabajo.setIdGrupoPrecio(trabajoCategoriaPrecio.getGrupoPrecio().getIdGrupoPrecio());
            vehiculoTrabajo.setIdTrabajo(trabajoCategoriaPrecio.getTrabajo().getIdTrabajo());
            vehiculoTrabajo.setIdVehiculo(ordenVehiculoEntrada.getIdOrdenVehiculo());
            vehiculoTrabajo.setPrecioVentaPublico(trabajoCategoriaPrecio.getPrecioVentaPublico());
            vehiculoTrabajo.setPrecioVentaPublicoFactura(trabajoCategoriaPrecio.getPrecioVentaPublico());
            vehiculoTrabajo.setPrecioDescuento(trabajoCategoriaPrecio.getPrecioDescuento());
            vehiculoTrabajo.setPrecioDescuentoFactura(trabajoCategoriaPrecio.getPrecioDescuento());
            vehiculoTrabajo.setPrecioAbonoEfectivo(new BigDecimal("0"));
            vehiculoTrabajo.setPrecioAbonoTarjeta(new BigDecimal("0"));
            vehiculoTrabajo.setPrecioSaldoEfectivo(new BigDecimal("0"));
            vehiculoTrabajo.setPrecioSaldoTarjeta(new BigDecimal("0"));
            vehiculoTrabajo.setPorcentajeIva(impuesto.getFactor());
            vehiculoTrabajo.setValorIva(new BigDecimal("0"));
            vehiculoTrabajo.setDescuento(new BigDecimal("0"));

            vehiculoTrabajo.setVehiculoDescripcion(ordenVehiculoEntrada.getVehiculo().getModelo());
            vehiculoTrabajo.setTrabajoDescripcion(trabajoCategoriaPrecio.getTrabajo().getDescripcion());
            vehiculoTrabajo.setPartePrincipal(trabajoCategoriaPrecio.getParte());
            vehiculoTrabajo.setGenericoEntidad(new GenericoEntidad());
            vehiculoTrabajo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            vehiculoTrabajo = vehiculoTrabajoBean.crear(vehiculoTrabajo);
            System.out.println("trabajoCategoriaPrecio.getTrabajoCategoriaPrecioId:" + trabajoCategoriaPrecio.getTrabajoCategoriaPrecioId());
            System.out.println("trabajoCategoriaPrecio.getParte:" + trabajoCategoriaPrecio.getParte());
            parametros = new HashMap<>();
            parametros.put("padre", trabajoCategoriaPrecio.getParte());
            parametros.put("distintivo", "INICIALIZAR");
            List<Parte> listaPartes = parteBean.obtenerListaPorParametros(parametros);
            List<TrabajoParte> listaTrabajoPartesTmp = new ArrayList<>();
            for (Parte parteTmp : listaPartes) {
                TrabajoParte trabajoParte = new TrabajoParte();
                trabajoParte.setPartePadre(trabajoCategoriaPrecio.getParte());
                trabajoParte.setParte(parteTmp);
                System.out.println("Parte:" + parteTmp.getParte());
                trabajoParte.setVehiculoTrabajo(vehiculoTrabajo);
                trabajoParte = trabajoParteBean.crear(trabajoParte);
                listaTrabajoPartesTmp.add(trabajoParte);
            }

            getMapaVehiculoTrabajoParte().put(vehiculoTrabajo, listaTrabajoPartesTmp);
            List<VehiculoTrabajo> listaVehiculoTrabajoTmp2 = getMapaOrdenVehiculoTrabajo().get(ordenVehiculoEntrada);
            listaVehiculoTrabajoTmp2.add(vehiculoTrabajo);
            getMapaOrdenVehiculoTrabajo().put(ordenVehiculoEntrada, listaVehiculoTrabajoTmp2);
            System.out.println("----> agregarTrabajo fin");
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            e.printStackTrace();
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
        System.out.println("----> agregarTrabajo inicio: " + trabajoCategoriaPrecio.toString());

    }

    public List<GrupoPrecioParteCategoriaVehiculo> generarListaGrupoVehiculo() {
        GrupoPrecio grupoPrecio = establecimiento.getGrupoPrecio();
        List<GrupoPrecioParteCategoriaVehiculo> listaTemp = grupoPrecioParteCategoriaVehiculoBean.obtenerListaPorVehiculoYCategoria(grupoPrecio, vehiculoParaTrabajos.getVehiculo());
        System.out.println("tamaño listaTemp: " + listaTemp.size());
        return listaTemp;
    }

    public void cargarPreciosVehiculo() {
        System.out.println("vehiculoParaTrabajos: " + vehiculoParaTrabajos.toString());
        if (vehiculoParaTrabajos != null) {
            GrupoPrecio grupoPrecio = establecimiento.getGrupoPrecio();
            setListaMapaTrabajoCategoriaPrecioVehiculoSeleccionado(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecioYVehiculo(grupoPrecio, vehiculoParaTrabajos.getVehiculo()));
            setListaGrupoPrecioParteCategoriaVehiculo(generarListaGrupoVehiculo());
            setListaTrabajoCategoriaPrecioTmp(trabajoCategoriaTrabajoBean.generarListaCompletaTrabajoCategoriaPrecioPorVehiculo(getListaGrupoPrecioParteCategoriaVehiculo()));
        }

    }

    public List<TrabajoCategoriaPrecio> obtenerListaTrabajoPorGrupoVehiculo(GrupoPrecio grupoPrecioEntrada, Vehiculo vehiculoEntrada) {
        return trabajoCategoriaTrabajoBean.generarListaCompletaTrabajoCategoriaPrecioPorVehiculo(grupoPrecioParteCategoriaVehiculoBean.obtenerListaPorVehiculoYCategoria(grupoPrecioEntrada, vehiculoEntrada));
    }

    public List<SelectItem> generarSelectItemDeTrabajosParaVehiculoSeleccionado(OrdenVehiculo ordenVehiculoEntrada) {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        if (null != ordenVehiculoEntrada) {
            for (TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp : mapaOrdenVehiculoListaTrabajos.get(ordenVehiculoEntrada)) {
                selectItemsBuilder.add(trabajoCategoriaPrecioTmp, trabajoCategoriaPrecioTmp.getTrabajo().getDescripcion() + " - " + trabajoCategoriaPrecioTmp.getPrecioVentaPublico() + " - " + trabajoCategoriaPrecioTmp.getPrecioDescuento());
            }
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeTrabajosParaVehiculoSeleccionado() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp : getListaTrabajoCategoriaPrecioTmp()) {
            selectItemsBuilder.add(trabajoCategoriaPrecioTmp, trabajoCategoriaPrecioTmp.getTrabajo().getDescripcion() + " - " + trabajoCategoriaPrecioTmp.getPrecioVentaPublico() + " - " + trabajoCategoriaPrecioTmp.getPrecioDescuento());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeVehiculosParaTrabajo() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (OrdenVehiculo vehiculoTmp : vehiculosCliente) {
            selectItemsBuilder.add(vehiculoTmp, vehiculoTmp.getVehiculo().getModelo() + " - " + vehiculoTmp.getVehiculo().getAnioDesde() + " - " + vehiculoTmp.getVehiculo().getAnioHasta());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeVehiculosParaTrabajoMapa() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (OrdenVehiculo vehiculoTmp : mapaOrdenVehiculoListaTrabajos.keySet()) {
            selectItemsBuilder.add(vehiculoTmp, vehiculoTmp.getVehiculo().getModelo() + " - " + vehiculoTmp.getVehiculo().getAnioDesde() + " - " + vehiculoTmp.getVehiculo().getAnioHasta());
        }
        return selectItemsBuilder.buildList();
    }

    public void cargarListaDeVehiculos() {
        setListaVehiculos(vehiculoBean.obtenerListaPorMarca(marcaVehiculoSeleccionado));
    }

    public void generarRuc() {
        if (clienteFactura.getNumeroDocumento().length() == 10) {
            clienteFactura.setNumeroDocumento(clienteFactura.getNumeroDocumento() + "001");
        }
    }

    public void generarSeleccionSino() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        selectItemsBuilder.add(Boolean.TRUE, "Si");
        selectItemsBuilder.add(Boolean.FALSE, "No");
        setListaSiNo(selectItemsBuilder.buildList());
    }

    public void cargarTree() {

    }

    public void seleccionarVehiculo() {
        System.out.println("agregarVehiculo: " + vehiculoSeleccionado.getDescripcionDetallada());
    }

    public List<Vehiculo> autoCompletarVehiculo(String consulta) {
        List<Vehiculo> listaResultado = new ArrayList<>();
        for (Vehiculo vehiculoTmp : listaVehiculos) {
            if (vehiculoTmp.getModelo().toUpperCase().contains(consulta.trim().toUpperCase())) {
                listaResultado.add(vehiculoTmp);
            }
        }
        //return vehiculoBean.obtenerModeloListaPorModeloLike(consulta);
        return listaResultado;
    }

    public void agregarVehiculo() {
        try {
            if (!getMapaOrdenVehiculoListaTrabajos().entrySet().isEmpty()) {
                addInfoMessage(Constante.EXITO, Constante.EXITO_YA_EXISTE);
                return;
            }
            System.out.println("agregarVehiculo");
            OrdenVehiculo ordenVehiculo = new OrdenVehiculo();
            ordenVehiculo.setVehiculo(vehiculoSeleccionado);
            ordenVehiculo.setFechaRegistroVehiculo(new Date(System.currentTimeMillis()));
            ordenVehiculo.setOrigen(origen);
            ordenVehiculo.setNombreReferencia(nombreReferencia);
            ordenVehiculo = ordenVehiculoBean.crearOrden(ordenVehiculo, clienteOrden, clienteFactura, llenarEsteMomento);
            setVehiculoParaTrabajos(ordenVehiculo);
            getVehiculosCliente().add(ordenVehiculo);
            List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecio = obtenerListaTrabajoPorGrupoVehiculo(establecimiento.getGrupoPrecio(), vehiculoSeleccionado);
            getMapaOrdenVehiculoListaTrabajos().put(ordenVehiculo, listaTrabajoCategoriaPrecio);
            getMapaOrdenVehiculoTrabajo().put(ordenVehiculo, new ArrayList<VehiculoTrabajo>());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void llenarEsteMomentoOrden() {
        System.out.println("llenarEsteMomentoOrden: " + llenarEsteMomento);
    }

    public void mismosDatosParaFactura() {
        try {
            if (mismosDatosOrden) {
                clienteFactura = new Usuario();
                BeanUtils.copyProperties(clienteFactura, clienteOrden);

                /*clienteFactura.setApellido(clienteOrden.getApellido());
            clienteFactura.setCallePrincipal(clienteOrden.getCallePrincipal());
            clienteFactura.setCalleSecundaria(clienteOrden.getCalleSecundaria());
            clienteFactura.setCelular(clienteOrden.getCelular());
            clienteFactura.setCiudad(clienteOrden.getCiudad());
            clienteFactura.setContrasenia(clienteOrden.getContrasenia());
            clienteFactura.setCorreo(clienteOrden.getCorreo());
            clienteFactura.setGenericoEntidad(clienteOrden.getGenericoEntidad());
            clienteFactura.setNombre(clienteOrden.getNombre());
            clienteFactura.setNumeracion(clienteOrden.getNumeracion());
            clienteFactura.setNumeroDocumento(clienteOrden.getNumeroDocumento());
            clienteFactura.setTelefono(clienteOrden.getTelefono());
            clienteFactura.setTipoDocumento(clienteOrden.getTipoDocumento());*/
            } else {
                clienteFactura = new Usuario();
            }
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void seleccionarEstablecimiento() {
        addInfoMessage(Constante.EXITO, Constante.EXITO_ESTABLECIMIENTO);
    }

    public List<SelectItem> generarSelectItemDeEstablecimento() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Establecimiento establecimientoTmp : establecimientoBean.obtenerLista()) {
            selectItemsBuilder.add(establecimientoTmp, establecimientoTmp.getNombre());
        }
        return selectItemsBuilder.buildList();
    }

    public void actualizarClienteOrden(AjaxBehaviorEvent event) {
        String numeroDocumento = "";
        System.out.println("actualizarClienteOrdeon: " + this.numeroDocumentoOrden);
        if (numeroDocumentoOrden != null) {
            String[] resultado = numeroDocumentoOrden.split("-");
            if (resultado.length > 0) {
                numeroDocumento = resultado[0].trim();
                this.clienteOrden = usuarioBean.obtenerPorNumeroDocumento(numeroDocumento);
                numeroDocumentoOrden = numeroDocumento;
            } else {
                System.out.println("vacio");

            }
        }

    }

    public void actualizarClienteFactura(AjaxBehaviorEvent event) {
        System.out.println("actualizarClienteFactura: " + this.clienteFactura.getNumeroDocumento());
    }

    public List<Establecimiento> autoCompletarEstablecimiento(String consulta) {
        listaEstablecimiento = establecimientoBean.obtenerModeloListaPorNombreLike(consulta);
        return listaEstablecimiento;
    }

    public List<Usuario> autoCompletar(String consulta) {
        System.out.println("seleccion consulta: " + seleccionConsulta);
        listaClientes = usuarioBean.obtenerModeloListaPorNumeroDocumentoLike(consulta);
        return listaClientes;
    }

    public List<SelectItem> autoCompletarSoloTexto2(String consulta) {
        List<SelectItem> listaSelectItem = new ArrayList<>();
        listaClientes = usuarioBean.obtenerModeloListaPorNumeroDocumentoLike(consulta);
        for (Usuario usuario : listaClientes) {
            listaSelectItem.add(new SelectItem(usuario.getDocumentoYNombres(), usuario.getNumeroDocumento()));
        }
        return listaSelectItem;
    }

    public List<String> autoCompletarSoloTexto(String consulta) {
        List<String> listaSelectItem = new ArrayList<>();
        listaClientes = usuarioBean.obtenerModeloListaPorNumeroDocumentoLike(consulta);
        for (Usuario usuario : listaClientes) {
            listaSelectItem.add(usuario.getDocumentoYNombres());
        }
        return listaSelectItem;
    }

    public void onItemSelect() {
        System.out.println("onItemSelect");
        clienteOrden = usuarioBean.obtenerPorNumeroDocumento(numeroDocumento);
    }

    public List<SelectItem> generarSelectItemDeCiudad() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Ciudad ciudadTmp : ciudadBean.obtenerLista()) {
            selectItemsBuilder.add(ciudadTmp, ciudadTmp.getCiudad() + " - " + ciudadTmp.getProvinciaEstado().getProvinciaEstado() + " - " + ciudadTmp.getProvinciaEstado().getPais().getPais());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeTipoDocumento() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (TipoDocumento tipoDocumentoTmo : tipoDocumentoBean.obtenerListasCliente((Object) true)) {
            selectItemsBuilder.add(tipoDocumentoTmo, tipoDocumentoTmo.getTipoDocumento());
        }
        return selectItemsBuilder.buildList();
    }

    public String enFlujoProceso(FlowEvent event) {
        System.out.println("getNewStep: " + event.getNewStep());
        System.out.println("getOldStep: " + event.getOldStep());

        switch (event.getOldStep()) {

            case "idSeleccionVehiculo":
                if (!getVehiculosCliente().isEmpty()) {
                    return event.getNewStep();
                } else {
                    addErrorMessage(Constante.ERROR_LISTA_VEHICULOS_MENSAJE + ":" + Constante.ERROR_LISTA_VEHICULOS, Constante.ERROR_LISTA_VEHICULOS_MENSAJE + ":" + Constante.ERROR_LISTA_VEHICULOS);
                    return event.getOldStep();
                }

            case "idSeleccionTrabajo":
                Boolean existeTrabajos = true;
                for (VehiculoTrabajo vehiculoTrabajo : getMapaVehiculoTrabajoParte().keySet()) {
                    List<TrabajoParte> listaTrabajoParteTmp = getMapaVehiculoTrabajoParte().get(vehiculoTrabajo);
                    for (TrabajoParte trabajoParte : listaTrabajoParteTmp) {
                        if (null == trabajoParte.getMaterial()) {
                            existeTrabajos = false;
                        }
                    }
                }
                if (existeTrabajos) {
                    return event.getNewStep();
                } else {
                    addErrorMessage(Constante.ERROR_LISTA_TRABAJO_MENSAJE + ":" + Constante.ERROR_LISTA_TRABAJO, Constante.ERROR_LISTA_TRABAJO_MENSAJE + ":" + Constante.ERROR_LISTA_TRABAJO);
                    return event.getOldStep();
                }
            case "idSeleccionHorario":
                if (this.listaOrdenFecha.isEmpty()) {
                    addErrorMessage(Constante.ERROR_LISTA_ORDEN_FECHA_MENSAJE + ":" + Constante.ERROR_LISTA_ORDEN_FECHA, Constante.ERROR_LISTA_ORDEN_FECHA_MENSAJE + ":" + Constante.ERROR_LISTA_ORDEN_FECHA);
                    return event.getOldStep();
                } else {
                    return event.getNewStep();
                }
            default:
                return event.getNewStep();
        }
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
     * @return the listaVehiculos
     */
    public List<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    /**
     * @param listaVehiculos the listaVehiculos to set
     */
    public void setListaVehiculos(List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    /**
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    /**
     * @return the listaClientes
     */
    public List<Usuario> getListaClientes() {
        return listaClientes;
    }

    /**
     * @param listaClientes the listaClientes to set
     */
    public void setListaClientes(List<Usuario> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * @return the establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * @return the listaEstablecimiento
     */
    public List<Establecimiento> getListaEstablecimiento() {
        return listaEstablecimiento;
    }

    /**
     * @param listaEstablecimiento the listaEstablecimiento to set
     */
    public void setListaEstablecimiento(List<Establecimiento> listaEstablecimiento) {
        this.listaEstablecimiento = listaEstablecimiento;
    }

    /**
     * @return the mismosDatosOrden
     */
    public Boolean getMismosDatosOrden() {
        return mismosDatosOrden;
    }

    /**
     * @param mismosDatosOrden the mismosDatosOrden to set
     */
    public void setMismosDatosOrden(Boolean mismosDatosOrden) {
        this.mismosDatosOrden = mismosDatosOrden;
    }

    /**
     * @return the llenarEsteMomento
     */
    public Boolean getLlenarEsteMomento() {
        return llenarEsteMomento;
    }

    /**
     * @param llenarEsteMomento the llenarEsteMomento to set
     */
    public void setLlenarEsteMomento(Boolean llenarEsteMomento) {
        this.llenarEsteMomento = llenarEsteMomento;
    }

    /**
     * @return the vehiculosCliente
     */
    public List<OrdenVehiculo> getVehiculosCliente() {
        return vehiculosCliente;
    }

    /**
     * @param vehiculosCliente the vehiculosCliente to set
     */
    public void setVehiculosCliente(List<OrdenVehiculo> vehiculosCliente) {
        this.vehiculosCliente = vehiculosCliente;
    }

    /**
     *
     *
     * @return the vehiculoSeleccionado
     */
    public Vehiculo getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    /**
     * @param vehiculoSeleccionado the vehiculoSeleccionado to set
     */
    public void setVehiculoSeleccionado(Vehiculo vehiculoSeleccionado) {
        this.vehiculoSeleccionado = vehiculoSeleccionado;
    }

    /**
     * @return the numeroDocumentoOrden
     */
    public String getNumeroDocumentoOrden() {
        return numeroDocumentoOrden;
    }

    /**
     * @param numeroDocumentoOrden the numeroDocumentoOrden to set
     */
    public void setNumeroDocumentoOrden(String numeroDocumentoOrden) {
        this.numeroDocumentoOrden = numeroDocumentoOrden;
    }

    /**
     * @return the numeroDocumentoFactura
     */
    public String getNumeroDocumentoFactura() {
        return numeroDocumentoFactura;
    }

    /**
     * @param numeroDocumentoFactura the numeroDocumentoFactura to set
     */
    public void setNumeroDocumentoFactura(String numeroDocumentoFactura) {
        this.numeroDocumentoFactura = numeroDocumentoFactura;
    }

    /**
     * @return the seleccionConsulta
     */
    public String getSeleccionConsulta() {
        return seleccionConsulta;
    }

    /**
     * @param seleccionConsulta the seleccionConsulta to set
     */
    public void setSeleccionConsulta(String seleccionConsulta) {
        this.seleccionConsulta = seleccionConsulta;
    }

    /**
     * @return the clienteConsulta
     */
    public Usuario getClienteConsulta() {
        return clienteConsulta;
    }

    /**
     * @param clienteConsulta the clienteConsulta to set
     */
    public void setClienteConsulta(Usuario clienteConsulta) {
        this.clienteConsulta = clienteConsulta;
    }

    /**
     * @return the listaSiNo
     */
    public List<SelectItem> getListaSiNo() {
        return listaSiNo;
    }

    /**
     * @param listaSiNo the listaSiNo to set
     */
    public void setListaSiNo(List<SelectItem> listaSiNo) {
        this.listaSiNo = listaSiNo;
    }

    /**
     * @return the marcaVehiculoSeleccionado
     */
    public MarcaVehiculo getMarcaVehiculoSeleccionado() {
        return marcaVehiculoSeleccionado;
    }

    /**
     * @param marcaVehiculoSeleccionado the marcaVehiculoSeleccionado to set
     */
    public void setMarcaVehiculoSeleccionado(MarcaVehiculo marcaVehiculoSeleccionado) {
        this.marcaVehiculoSeleccionado = marcaVehiculoSeleccionado;
    }

    /**
     * @return the vehiculoParaTrabajos
     */
    public OrdenVehiculo getVehiculoParaTrabajos() {
        return vehiculoParaTrabajos;
    }

    /**
     * @param vehiculoParaTrabajos the vehiculoParaTrabajos to set
     */
    public void setVehiculoParaTrabajos(OrdenVehiculo vehiculoParaTrabajos) {
        this.vehiculoParaTrabajos = vehiculoParaTrabajos;
    }

    /**
     * @return the listaTrabajoCategoriaPrecioTmp
     */
    public List<TrabajoCategoriaPrecio> getListaTrabajoCategoriaPrecioTmp() {
        return listaTrabajoCategoriaPrecioTmp;
    }

    /**
     * @param listaTrabajoCategoriaPrecioTmp the listaTrabajoCategoriaPrecioTmp
     * to set
     */
    public void setListaTrabajoCategoriaPrecioTmp(List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecioTmp) {
        this.listaTrabajoCategoriaPrecioTmp = listaTrabajoCategoriaPrecioTmp;
    }

    /**
     * @return the trabajoCategoriaPrecio
     */
    public TrabajoCategoriaPrecio getTrabajoCategoriaPrecio() {
        return trabajoCategoriaPrecio;
    }

    /**
     * @param trabajoCategoriaPrecio the trabajoCategoriaPrecio to set
     */
    public void setTrabajoCategoriaPrecio(TrabajoCategoriaPrecio trabajoCategoriaPrecio) {
        this.trabajoCategoriaPrecio = trabajoCategoriaPrecio;
    }

    /**
     * @return the listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado
     */
    public List<HashMap<String, Object>> getListaMapaTrabajoCategoriaPrecioVehiculoSeleccionado() {
        return listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado;
    }

    /**
     * @param listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado the
     * listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado to set
     */
    public void setListaMapaTrabajoCategoriaPrecioVehiculoSeleccionado(List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado) {
        this.listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado = listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado;
    }

    /**
     * @return the listaGrupoPrecioParteCategoriaVehiculo
     */
    public List<GrupoPrecioParteCategoriaVehiculo> getListaGrupoPrecioParteCategoriaVehiculo() {
        return listaGrupoPrecioParteCategoriaVehiculo;
    }

    /**
     * @param listaGrupoPrecioParteCategoriaVehiculo the
     * listaGrupoPrecioParteCategoriaVehiculo to set
     */
    public void setListaGrupoPrecioParteCategoriaVehiculo(List<GrupoPrecioParteCategoriaVehiculo> listaGrupoPrecioParteCategoriaVehiculo) {
        this.listaGrupoPrecioParteCategoriaVehiculo = listaGrupoPrecioParteCategoriaVehiculo;
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
     * @return the listaTrabajosPorParte
     */
    public List<SelectItem> getListaTrabajosPorParte() {
        return listaTrabajosPorParte;
    }

    /**
     * @param listaTrabajosPorParte the listaTrabajosPorParte to set
     */
    public void setListaTrabajosPorParte(List<SelectItem> listaTrabajosPorParte) {
        this.listaTrabajosPorParte = listaTrabajosPorParte;
    }

    /**
     * @return the mapaOrdenVehiculoListaTrabajos
     */
    public HashMap<OrdenVehiculo, List<TrabajoCategoriaPrecio>> getMapaOrdenVehiculoListaTrabajos() {
        return mapaOrdenVehiculoListaTrabajos;
    }

    /**
     * @param mapaOrdenVehiculoListaTrabajos the mapaOrdenVehiculoListaTrabajos
     * to set
     */
    public void setMapaOrdenVehiculoListaTrabajos(HashMap<OrdenVehiculo, List<TrabajoCategoriaPrecio>> mapaOrdenVehiculoListaTrabajos) {
        this.mapaOrdenVehiculoListaTrabajos = mapaOrdenVehiculoListaTrabajos;
    }

    /**
     * @return the parteSeleccionada
     */
    public Parte getParteSeleccionada() {
        return parteSeleccionada;
    }

    /**
     * @param parteSeleccionada the parteSeleccionada to set
     */
    public void setParteSeleccionada(Parte parteSeleccionada) {
        this.parteSeleccionada = parteSeleccionada;
    }

    /**
     * @return the vehiculoTrabajoSeleccionado
     */
    public VehiculoTrabajo getVehiculoTrabajoSeleccionado() {
        return vehiculoTrabajoSeleccionado;
    }

    /**
     * @param vehiculoTrabajoSeleccionado the vehiculoTrabajoSeleccionado to set
     */
    public void setVehiculoTrabajoSeleccionado(VehiculoTrabajo vehiculoTrabajoSeleccionado) {
        this.vehiculoTrabajoSeleccionado = vehiculoTrabajoSeleccionado;
    }

    /**
     * @return the trabajoParteSeleccionada
     */
    public TrabajoParte getTrabajoParteSeleccionada() {
        return trabajoParteSeleccionada;
    }

    /**
     * @param trabajoParteSeleccionada the trabajoParteSeleccionada to set
     */
    public void setTrabajoParteSeleccionada(TrabajoParte trabajoParteSeleccionada) {
        this.trabajoParteSeleccionada = trabajoParteSeleccionada;
    }

    /**
     * @return the materialSeleccionado
     */
    public Material getMaterialSeleccionado() {
        return materialSeleccionado;
    }

    /**
     * @param materialSeleccionado the materialSeleccionado to set
     */
    public void setMaterialSeleccionado(Material materialSeleccionado) {
        this.materialSeleccionado = materialSeleccionado;
    }

    /**
     * @return the mesSeleccionado
     */
    public Long getMesSeleccionado() {
        return mesSeleccionado;
    }

    /**
     * @param mesSeleccionado the mesSeleccionado to set
     */
    public void setMesSeleccionado(Long mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    /**
     * @return the listaPEF
     */
    public List<HashMap<String, PeriodoEstablecimientoFecha>> getListaPEF() {
        return listaPEF;
    }

    /**
     * @param listaPEF the listaPEF to set
     */
    public void setListaPEF(List<HashMap<String, PeriodoEstablecimientoFecha>> listaPEF) {
        this.listaPEF = listaPEF;
    }

    /**
     * @return the periodoSeleccionadoParaCalendario
     */
    public Periodo getPeriodoSeleccionadoParaCalendario() {
        return periodoSeleccionadoParaCalendario;
    }

    /**
     * @param periodoSeleccionadoParaCalendario the
     * periodoSeleccionadoParaCalendario to set
     */
    public void setPeriodoSeleccionadoParaCalendario(Periodo periodoSeleccionadoParaCalendario) {
        this.periodoSeleccionadoParaCalendario = periodoSeleccionadoParaCalendario;
    }

    /**
     * @return the equipoSeleccionado
     */
    public Equipo getEquipoSeleccionado() {
        return equipoSeleccionado;
    }

    /**
     * @param equipoSeleccionado the equipoSeleccionado to set
     */
    public void setEquipoSeleccionado(Equipo equipoSeleccionado) {
        this.equipoSeleccionado = equipoSeleccionado;
    }

    /**
     * @return the mapaOrdenVehiculoTrabajo
     */
    public HashMap<OrdenVehiculo, List<VehiculoTrabajo>> getMapaOrdenVehiculoTrabajo() {
        return mapaOrdenVehiculoTrabajo;
    }

    /**
     * @param mapaOrdenVehiculoTrabajo the mapaOrdenVehiculoTrabajo to set
     */
    public void setMapaOrdenVehiculoTrabajo(HashMap<OrdenVehiculo, List<VehiculoTrabajo>> mapaOrdenVehiculoTrabajo) {
        this.mapaOrdenVehiculoTrabajo = mapaOrdenVehiculoTrabajo;
    }

    /**
     * @return the mapaVehiculoTrabajoParte
     */
    public HashMap<VehiculoTrabajo, List<TrabajoParte>> getMapaVehiculoTrabajoParte() {
        return mapaVehiculoTrabajoParte;
    }

    /**
     * @param mapaVehiculoTrabajoParte the mapaVehiculoTrabajoParte to set
     */
    public void setMapaVehiculoTrabajoParte(HashMap<VehiculoTrabajo, List<TrabajoParte>> mapaVehiculoTrabajoParte) {
        this.mapaVehiculoTrabajoParte = mapaVehiculoTrabajoParte;
    }

    /**
     * @return the vehiculoTrabajoParaHorario
     */
    public VehiculoTrabajo getVehiculoTrabajoParaHorario() {
        return vehiculoTrabajoParaHorario;
    }

    /**
     * @param vehiculoTrabajoParaHorario the vehiculoTrabajoParaHorario to set
     */
    public void setVehiculoTrabajoParaHorario(VehiculoTrabajo vehiculoTrabajoParaHorario) {
        this.vehiculoTrabajoParaHorario = vehiculoTrabajoParaHorario;
    }

    /**
     * @return the totalDescuento
     */
    public BigDecimal getTotalDescuento() {
        return totalDescuento;
    }

    /**
     * @param totalDescuento the totalDescuento to set
     */
    public void setTotalDescuento(BigDecimal totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    /**
     * @return the totalPVP
     */
    public BigDecimal getTotalPVP() {
        return totalPVP;
    }

    /**
     * @param totalPVP the totalPVP to set
     */
    public void setTotalPVP(BigDecimal totalPVP) {
        this.totalPVP = totalPVP;
    }

    /**
     * @return the pagoTarjeta
     */
    public Boolean getPagoTarjeta() {
        return pagoTarjeta;
    }

    /**
     * @param pagoTarjeta the pagoTarjeta to set
     */
    public void setPagoTarjeta(Boolean pagoTarjeta) {
        this.pagoTarjeta = pagoTarjeta;
    }

    /**
     * @return the vehiculoTrabajoPago
     */
    public VehiculoTrabajo getVehiculoTrabajoPago() {
        return vehiculoTrabajoPago;
    }

    /**
     * @param vehiculoTrabajoPago the vehiculoTrabajoPago to set
     */
    public void setVehiculoTrabajoPago(VehiculoTrabajo vehiculoTrabajoPago) {
        this.vehiculoTrabajoPago = vehiculoTrabajoPago;
    }

    /**
     * @return the listaVehiculoTrabajosFinal
     */
    public List<VehiculoTrabajo> getListaVehiculoTrabajosFinal() {
        return listaVehiculoTrabajosFinal;
    }

    /**
     * @param listaVehiculoTrabajosFinal the listaVehiculoTrabajosFinal to set
     */
    public void setListaVehiculoTrabajosFinal(List<VehiculoTrabajo> listaVehiculoTrabajosFinal) {
        this.listaVehiculoTrabajosFinal = listaVehiculoTrabajosFinal;
    }

    /**
     * @return the configurarPrecio
     */
    public Boolean getConfigurarPrecio() {
        return configurarPrecio;
    }

    /**
     * @param configurarPrecio the configurarPrecio to set
     */
    public void setConfigurarPrecio(Boolean configurarPrecio) {
        this.configurarPrecio = configurarPrecio;
    }

    /**
     * @return the observacionPago
     */
    public String getObservacionPago() {
        return observacionPago;
    }

    /**
     * @param observacionPago the observacionPago to set
     */
    public void setObservacionPago(String observacionPago) {
        this.observacionPago = observacionPago;
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
     * @return the fechaYHoraActual
     */
    public Date getFechaYHoraActual() {
        return fechaYHoraActual;
    }

    /**
     * @param fechaYHoraActual the fechaYHoraActual to set
     */
    public void setFechaYHoraActual(Date fechaYHoraActual) {
        this.fechaYHoraActual = fechaYHoraActual;
    }

    /**
     * @return the listaOrdenFecha
     */
    public List<OrdenFecha> getListaOrdenFecha() {
        return listaOrdenFecha;
    }

    /**
     * @param listaOrdenFecha the listaOrdenFecha to set
     */
    public void setListaOrdenFecha(List<OrdenFecha> listaOrdenFecha) {
        this.listaOrdenFecha = listaOrdenFecha;
    }

    /**
     * @return the ordenFecha
     */
    public OrdenFecha getOrdenFecha() {
        return ordenFecha;
    }

    /**
     * @param ordenFecha the ordenFecha to set
     */
    public void setOrdenFecha(OrdenFecha ordenFecha) {
        this.ordenFecha = ordenFecha;
    }

}
