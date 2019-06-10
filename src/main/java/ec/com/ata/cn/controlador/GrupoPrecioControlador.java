/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.GrupoPrecioBean;
import ec.com.ata.cn.modelo.GrupoPrecio;

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
public class GrupoPrecioControlador extends BaseControlador {

    @Inject
    private GrupoPrecioBean grupoPrecioBean;

    private GrupoPrecio grupoPrecio;
    
    private String nombre;

    private List<GrupoPrecio> listaGrupoPrecio;

    @PostConstruct
    public void init() {
        grupoPrecio = new GrupoPrecio();
        listaGrupoPrecio = grupoPrecioBean.obtenerLista();
    }

    public List<GrupoPrecio> obtenerListaGrupoPrecio() {
        return grupoPrecioBean.obtenerLista();
    }

    public void guardar() {
        try {
            getGrupoPrecio().setNombre(getGrupoPrecio().getNombre().trim());
            grupoPrecioBean.crear(getGrupoPrecio());
            listaGrupoPrecio = grupoPrecioBean.obtenerLista();            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setGrupoPrecio(new GrupoPrecio());
        }
    }

    /**
     * @return the grupoPrecio
     */
    public GrupoPrecio getGrupoPrecio() {
        return grupoPrecio;
    }

    /**
     * @param grupoPrecio the grupoPrecio to set
     */
    public void setGrupoPrecio(GrupoPrecio grupoPrecio) {
        this.grupoPrecio = grupoPrecio;
    }

    /**
     * @return the listaGrupoPrecio
     */
    public List<GrupoPrecio> getListaGrupoPrecio() {
        return listaGrupoPrecio;
    }

    /**
     * @param listaGrupoPrecio the listaGrupoPrecio to set
     */
    public void setListaGrupoPrecio(List<GrupoPrecio> listaGrupoPrecio) {
        this.listaGrupoPrecio = listaGrupoPrecio;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
