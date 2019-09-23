/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.CiudadBean;
import ec.com.ata.cn.logica.EstablecimientoBean;
import ec.com.ata.cn.logica.TipoDocumentoBean;
import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Ciudad;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.TipoDocumento;
import ec.com.ata.cn.modelo.Usuario;
import ec.com.ata.cn.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.selectitems.SelectItemsBuilder;
import org.primefaces.event.FlowEvent;

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

    private Establecimiento establecimiento;

    private Usuario clienteOrden;

    private Usuario clienteFactura;

    private List<Vehiculo> listaVehiculos;

    private boolean skip;

    private String numeroDocumento;

    private List<Usuario> listaClientes;

    private List<Establecimiento> listaEstablecimiento;
    
    private Boolean mismosDatosOrden;
    
    private Boolean llenarEsteMomento;
    
    private List<Vehiculo> vehiculosCliente;
    
    private Vehiculo vehiculoSeleccionado;

    @PostConstruct
    public void init() {
        setEstablecimiento(new Establecimiento());
        setClienteOrden(new Usuario());
        setClienteFactura(new Usuario());
        setListaVehiculos(new ArrayList<Vehiculo>());
        setListaClientes(new ArrayList<Usuario>());
        setMismosDatosOrden(false);
        setLlenarEsteMomento(false);
        setVehiculosCliente(new ArrayList<Vehiculo>());
    }
    
    public void seleccionarVehiculo() {
        System.out.println("agregarVehiculo: "+vehiculoSeleccionado.getDescripcionDetallada());
    }
    
    public List<Vehiculo> autoCompletarVehiculo(String consulta) {
        return vehiculoBean.obtenerModeloListaPorModeloLike(consulta);
    }
    
    public void agregarVehiculo () {
        System.out.println("agregarVehiculo");
        getVehiculosCliente().add(vehiculoSeleccionado);
    } 
    
    public void llenarEsteMomentoOrden() {
        System.out.println("llenarEsteMomentoOrden: "+llenarEsteMomento);
    }
    
    public void mismosDatosParaFactura(){
        System.out.println("mismosDatosParaFactura: "+mismosDatosOrden);
        if (mismosDatosOrden){
            clienteFactura.setApellido(clienteOrden.getApellido());
            clienteFactura.setCallePrincipal(clienteOrden.getCallePrincipal());
            clienteFactura.setCalleSecundaria(clienteOrden.getCalleSecundaria());
            clienteFactura.setCelular(clienteOrden.getCelular());
            clienteFactura.setContrasenia(clienteOrden.getContrasenia());
            clienteFactura.setCorreo(clienteOrden.getCorreo());            
            clienteFactura.setGenericoEntidad(clienteOrden.getGenericoEntidad());
            clienteFactura.setNombre(clienteOrden.getNombre());
            clienteFactura.setNumeracion(clienteOrden.getNumeracion());
            clienteFactura.setNumeroDocumento(clienteOrden.getNumeroDocumento());
            clienteFactura.setTelefono(clienteOrden.getTelefono());
        } else {
            clienteFactura = new Usuario();
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
        System.out.println("actualizarClienteOrdeon: " + this.clienteOrden.getNumeroDocumento());
    }
    
    public void actualizarClienteFactura(AjaxBehaviorEvent event) {
        System.out.println("actualizarClienteFactura: " + this.clienteFactura.getNumeroDocumento());
    }

    public List<Establecimiento> autoCompletarEstablecimiento(String consulta) {
        listaEstablecimiento = establecimientoBean.obtenerModeloListaPorNombreLike(consulta);
        return listaEstablecimiento;
    }

    public List<Usuario> autoCompletar(String consulta) {
        listaClientes = usuarioBean.obtenerModeloListaPorNumeroDocumentoLike(consulta);
        return listaClientes;
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
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
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
    public List<Vehiculo> getVehiculosCliente() {
        return vehiculosCliente;
    }

    /**
     * @param vehiculosCliente the vehiculosCliente to set
     */
    public void setVehiculosCliente(List<Vehiculo> vehiculosCliente) {
        this.vehiculosCliente = vehiculosCliente;
    }

    /**
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
}
