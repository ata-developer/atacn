/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.CiudadBean;
import ec.com.ata.cn.logica.TipoDocumentoBean;
import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.modelo.Ciudad;
import ec.com.ata.cn.modelo.TipoDocumento;
import ec.com.ata.cn.modelo.Usuario;
import ec.com.ata.cn.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    private Usuario clienteOrden;

    private Usuario clienteFactura;

    private List<Vehiculo> listaVehiculos;

    private boolean skip;

    @PostConstruct
    public void init() {
        setClienteOrden(new Usuario());
        setClienteFactura(new Usuario());
        setListaVehiculos(new ArrayList<Vehiculo>());
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
}
