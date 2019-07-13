/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.MarcaVehiculoBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Vehiculo;
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

    private MarcaVehiculo marcaVehiculo;
    
    private MarcaVehiculo marcaVehiculoSeleccionado;

    private List<MarcaVehiculo> listaMarcaVehiculo;
    
    private List<Vehiculo> listaVehiculo;

    @PostConstruct
    public void init() {
        setMarcaVehiculo(new MarcaVehiculo());
        setListaMarcaVehiculo(getMarcaVehiculoBean().obtenerLista());
    }
    
    public void cargarListaDeVehiculos(){
        setListaVehiculo(vehiculoBean.obtenerLista());
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
            marcaVehiculo.setFechaRegistro(new Date(System.currentTimeMillis()));
            getMarcaVehiculoBean().crear(marcaVehiculo);
            setListaMarcaVehiculo(getMarcaVehiculoBean().obtenerLista());
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
     * @return the marcaVehiculoBean
     */
    public MarcaVehiculoBean getMarcaVehiculoBean() {
        return marcaVehiculoBean;
    }

    /**
     * @param marcaVehiculoBean the marcaVehiculoBean to set
     */
    public void setMarcaVehiculoBean(MarcaVehiculoBean marcaVehiculoBean) {
        this.marcaVehiculoBean = marcaVehiculoBean;
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
