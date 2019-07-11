/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;


import ec.com.ata.cn.logica.AnioVehiculoBean;
import ec.com.ata.cn.logica.MarcaVehiculoBean;
import ec.com.ata.cn.logica.ParametroBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.AnioVehiculo;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Parametro;
import ec.com.ata.cn.modelo.Vehiculo;
import java.util.ArrayList;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author ATA1
 */
@ViewScoped
@Named
public class VehiculoControlador extends BaseControlador {

    @Inject
    private VehiculoBean vehiculoBean;
    
    @Inject
    private MarcaVehiculoBean marcaVehiculoBean;
    
    @Inject
    private AnioVehiculoBean anioVehiculoBean;
    
    @Inject
    private ParametroBean parametroBean;

    private Vehiculo vehiculo;
    
    private MarcaVehiculo marcaVehiculo;

    private List<Integer> listaAnioInicial = new ArrayList<>();
    
    private List<Integer> listaAnioFinal = new ArrayList<>();;
    
    private List<Vehiculo> listaVehiculo;
    
    private Integer anioInicial;
    
    private Integer anioFinal;
    
    @PostConstruct
    public void init() {
        vehiculo = new Vehiculo();
        listaVehiculo = vehiculoBean.obtenerLista();
        generarAniosSeleccion();
    }
    
    public void generarAniosSeleccion() {
        Parametro anioInicialParametro = parametroBean.obtenerPorCodigo(Constante.FECHA_INICIAL);
        Parametro anioFinParametro = parametroBean.obtenerPorCodigo(Constante.FECHA_FIN);
        anioInicial = new Integer (anioInicialParametro.getParametro());
        anioFinal = new Integer (anioFinParametro.getParametro());
        for (Integer anioInicialTmp = anioInicial; anioInicial <= anioFinal; anioInicial++) {
            getListaAnioInicial().add(anioInicialTmp);
        }
    }

    public List<Vehiculo> obtenerListaVehiculo() {
        return vehiculoBean.obtenerLista();
    }

    public void guardar() {
        try {
            
            vehiculoBean.crear(getVehiculo());
            listaVehiculo = vehiculoBean.obtenerLista();            
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setVehiculo(new Vehiculo());
        }
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
     * @return the listaVehiculo
     */
    public List<Vehiculo> getListaVehiculo() {
        return listaVehiculo;
    }

    /**
     * @param listaVehiculo the listaVehiculo to set
     */
    public void setListaVehiculo(List<Vehiculo> listaVehiculo) {
        this.listaVehiculo = listaVehiculo;
    }

    /**
     * @return the listaAnioInicial
     */
    public List<Integer> getListaAnioInicial() {
        return listaAnioInicial;
    }

    /**
     * @param listaAnioInicial the listaAnioInicial to set
     */
    public void setListaAnioInicial(List<Integer> listaAnioInicial) {
        this.listaAnioInicial = listaAnioInicial;
    }

    /**
     * @return the listaAnioFinal
     */
    public List<Integer> getListaAnioFinal() {
        return listaAnioFinal;
    }

    /**
     * @param listaAnioFinal the listaAnioFinal to set
     */
    public void setListaAnioFinal(List<Integer> listaAnioFinal) {
        this.listaAnioFinal = listaAnioFinal;
    }
}
