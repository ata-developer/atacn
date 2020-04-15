/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.CiudadBean;
import ec.com.ata.cn.logica.EstablecimientoBean;
import ec.com.ata.cn.logica.ParqueaderoBean;
import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Ciudad;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.Parqueadero;
import ec.com.ata.cn.modelo.Usuario;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.omnifaces.util.selectitems.SelectItemsBuilder;

/**
 *
 * @author ATA1
 */
@ViewScoped
@Named
public class EstablecimientoControlador extends BaseControlador {

    @Inject
    private EstablecimientoBean establecimientoBean;

    @Inject
    private CiudadBean ciudadBean;

    @Inject
    private UsuarioBean usuarioBean;

    @Inject
    private ParqueaderoBean parqueaderoBean;

    private Establecimiento establecimiento;

    private List<Establecimiento> listaEstablecimiento;

    private List<Ciudad> listaCiudad;

    private List<Usuario> listaUsuario;

    private Parqueadero parqueadero;

    private List<Parqueadero> listaParqueaderos;

    private Establecimiento establecimientoSeleccionado;
    

    @PostConstruct
    public void init() {
        establecimiento = new Establecimiento();
        listaEstablecimiento = establecimientoBean.obtenerLista();
        listaCiudad = ciudadBean.obtenerLista();
        setListaUsuario(usuarioBean.obtenerLista());
        setParqueadero(new Parqueadero());
        setListaParqueaderos(new ArrayList<Parqueadero>());
        setEstablecimientoSeleccionado(new Establecimiento());
    }

    public void seleccionarEstablecimiento(Establecimiento establecimientEntrada) {
        setEstablecimientoSeleccionado(establecimientEntrada);
        HashMap parametros = new HashMap();
        parametros.put("establecimiento", getEstablecimientoSeleccionado());
        List<Parqueadero> listaParqueadero = parqueaderoBean.obtenerListaPorParametros(parametros);
        setListaParqueaderos(listaParqueadero);
        addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
    }

    public List<Establecimiento> obtenerListaEstablecimiento() {
        return establecimientoBean.obtenerLista();
    }

    public List<SelectItem> generarSelectItemDeCiudad() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Ciudad ciudadTmp : getListaCiudad()) {
            selectItemsBuilder.add(ciudadTmp, ciudadTmp.getCiudad() + " - " + ciudadTmp.getProvinciaEstado().getProvinciaEstado() + " - " + ciudadTmp.getProvinciaEstado().getPais().getPais());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeUsuario() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Usuario usuarioTmp : getListaUsuario()) {
            selectItemsBuilder.add(usuarioTmp, usuarioTmp.getUsuario());
        }
        return selectItemsBuilder.buildList();
    }

    public void guardar() {
        try {
            establecimientoBean.crear(getEstablecimiento());
            listaEstablecimiento = establecimientoBean.obtenerLista();
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setEstablecimiento(new Establecimiento());
        }
    }

    public void guardarParqueadero() {
        try {
            parqueadero.setEstablecimiento(getEstablecimientoSeleccionado());
            parqueaderoBean.crear(parqueadero);
            listaEstablecimiento = establecimientoBean.obtenerLista();
            HashMap parametros = new HashMap();
            parametros.put("establecimiento", getEstablecimientoSeleccionado());
            List<Parqueadero> listaParqueadero = parqueaderoBean.obtenerListaPorParametros(parametros);
            setListaParqueaderos(listaParqueadero);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setParqueadero(new Parqueadero());
        }
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
     * @return the listaCiudad
     */
    public List<Ciudad> getListaCiudad() {
        return listaCiudad;
    }

    /**
     * @param listaCiudad the listaCiudad to set
     */
    public void setListaCiudad(List<Ciudad> listaCiudad) {
        this.listaCiudad = listaCiudad;
    }

    /**
     * @return the listaUsuario
     */
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    /**
     * @return the listaParqueaderos
     */
    public List<Parqueadero> getListaParqueaderos() {
        return listaParqueaderos;
    }

    /**
     * @param listaParqueaderos the listaParqueaderos to set
     */
    public void setListaParqueaderos(List<Parqueadero> listaParqueaderos) {
        this.listaParqueaderos = listaParqueaderos;
    }

    /**
     * @return the parqueadero
     */
    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    /**
     * @param parqueadero the parqueadero to set
     */
    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
    }

    /**
     * @return the establecimientoSeleccionado
     */
    public Establecimiento getEstablecimientoSeleccionado() {
        return establecimientoSeleccionado;
    }

    /**
     * @param establecimientoSeleccionado the establecimientoSeleccionado to set
     */
    public void setEstablecimientoSeleccionado(Establecimiento establecimientoSeleccionado) {
        this.establecimientoSeleccionado = establecimientoSeleccionado;
    }
}
