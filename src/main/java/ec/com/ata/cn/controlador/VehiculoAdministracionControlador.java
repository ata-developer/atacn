/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.MarcaVehiculoBean;
import ec.com.ata.cn.logica.TipoFilaBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Fila;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.TipoFila;
import ec.com.ata.cn.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.Date;
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
public class VehiculoAdministracionControlador extends BaseControlador {

    @Inject
    private MarcaVehiculoBean marcaVehiculoBean;
    
    @Inject
    private VehiculoBean vehiculoBean;
    
    @Inject
    private TipoFilaBean tipoFilaBean;

    private MarcaVehiculo marcaVehiculo;
    
    private MarcaVehiculo marcaVehiculoSeleccionado;
    
    private Vehiculo vehiculo;
    
    private Vehiculo vehiculoSeleccionado;

    private List<MarcaVehiculo> listaMarcaVehiculo;
    
    private List<Vehiculo> listaVehiculo;
    
    private List<Fila> filasDelVehiculo;
    
    private List<TipoFila> tipoFilasAsiento;

    @PostConstruct
    public void init() {
        setMarcaVehiculo(new MarcaVehiculo());
        setVehiculo(new Vehiculo());
        setListaMarcaVehiculo(marcaVehiculoBean.obtenerLista());
        setTipoFilasAsiento(tipoFilaBean.obtenerLista());
    }
    
    public List<SelectItem> generarSelectItemDeTipoFilaDeAsiento() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (TipoFila tipoFilaAsientoTmp : getTipoFilasAsiento()) {
            selectItemsBuilder.add(tipoFilaAsientoTmp, tipoFilaAsientoTmp.getTipoFila());
        }
        return selectItemsBuilder.buildList();
    }
    
    public void generarFilas() {
        System.err.println("--- generarFilas --");
        List<Fila> listaFilasTemp = new ArrayList<>();
        for (int i = 0; i < vehiculo.getNumeroDeFilas(); i++) {
            listaFilasTemp.add(new Fila());
        }
        setFilasDelVehiculo(listaFilasTemp);
    }
    
    public void cargarListaDeVehiculos(){
        setListaVehiculo(vehiculoBean.obtenerListaPorMarca(marcaVehiculoSeleccionado));
    }    
    
    public List<SelectItem> generarSelectItemDeMarcaVehiculo() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (MarcaVehiculo marcaVehiculoTmp : getListaMarcaVehiculo()) {
            selectItemsBuilder.add(marcaVehiculoTmp, marcaVehiculoTmp.getMarca());
        }
        return selectItemsBuilder.buildList();
    }

    public void guadarVehiculo() {
        try {
            vehiculo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            vehiculoBean.crear(vehiculo);
            setListaMarcaVehiculo(marcaVehiculoBean.obtenerLista());
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            marcaVehiculo = new MarcaVehiculo();
        }
    }

    /**
     * @return the marcaVehiculo
     */
    public MarcaVehiculo getMarcaVehiculo() {
        return marcaVehiculo;
    }

    /**
     * @param marcaVehiculo the marcaVehiculo to set
     */
    public void setMarcaVehiculo(MarcaVehiculo marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    /**
     * @return the listaMarcaVehiculo
     */
    public List<MarcaVehiculo> getListaMarcaVehiculo() {
        return listaMarcaVehiculo;
    }

    /**
     * @param listaMarcaVehiculo the listaMarcaVehiculo to set
     */
    public void setListaMarcaVehiculo(List<MarcaVehiculo> listaMarcaVehiculo) {
        this.listaMarcaVehiculo = listaMarcaVehiculo;
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
     * @return the filasDelVehiculo
     */
    public List<Fila> getFilasDelVehiculo() {
        return filasDelVehiculo;
    }

    /**
     * @param filasDelVehiculo the filasDelVehiculo to set
     */
    public void setFilasDelVehiculo(List<Fila> filasDelVehiculo) {
        this.filasDelVehiculo = filasDelVehiculo;
    }

    /**
     * @return the tipoFilasAsiento
     */
    public List<TipoFila> getTipoFilasAsiento() {
        return tipoFilasAsiento;
    }

    /**
     * @param tipoFilasAsiento the tipoFilasAsiento to set
     */
    public void setTipoFilasAsiento(List<TipoFila> tipoFilasAsiento) {
        this.tipoFilasAsiento = tipoFilasAsiento;
    }

}
