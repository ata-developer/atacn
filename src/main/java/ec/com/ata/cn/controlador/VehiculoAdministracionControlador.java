/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.MarcaVehiculoBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import java.util.Date;
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
public class VehiculoAdministracionControlador extends BaseControlador {

    @Inject
    private MarcaVehiculoBean marcaVehiculoBean;

    private MarcaVehiculo marcaVehiculo;

    private List<MarcaVehiculo> listaMarcaVehiculo;

    @PostConstruct
    public void init() {
        setMarcaVehiculo(new MarcaVehiculo());
        setListaMarcaVehiculo(marcaVehiculoBean.obtenerLista());
    }

    public void guadarVehiculo() {
        try {
            marcaVehiculo.setFechaRegistro(new Date(System.currentTimeMillis()));
            marcaVehiculoBean.crear(marcaVehiculo);
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

}
