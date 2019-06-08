/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.AnioVehiculoBean;
import ec.com.ata.cn.logica.MarcaVehiculoBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.modelo.AnioVehiculo;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Vehiculo;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author ATA1
 */
@SessionScoped
@Named
public class VehiculoControlador extends BaseControlador {

    @Inject
    private VehiculoBean vehiculoBean;
    
    @Inject
    private MarcaVehiculoBean marcaVehiculoBean;
    
    @Inject
    private AnioVehiculoBean anioVehiculoBean;

    private Vehiculo vehiculo;
    
    private AnioVehiculo anioVehiculoDesde;
    
    private AnioVehiculo anioVehiculoHasta;
    
    private MarcaVehiculo marcaVehiculo;

    private List<Vehiculo> listaVehiculo;

    @PostConstruct
    public void init() {
        vehiculo = new Vehiculo();
        listaVehiculo = vehiculoBean.obtenerLista();
    }

    public List<Vehiculo> obtenerListaVehiculo() {
        return vehiculoBean.obtenerLista();
    }

    public void guardar() {
        try {
            
            vehiculoBean.crear(getVehiculo());
            listaVehiculo = vehiculoBean.obtenerLista();            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
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
}
