/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;


import ec.com.ata.cn.logica.BarrioBean;
import ec.com.ata.cn.logica.CiudadBean;
import ec.com.ata.cn.logica.PaisBean;
import ec.com.ata.cn.logica.ProvinciaBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Barrio;
import ec.com.ata.cn.modelo.Ciudad;
import ec.com.ata.cn.modelo.Pais;
import ec.com.ata.cn.modelo.ProvinciaEstado;
import java.util.ArrayList;

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
public class PaisCiudadControlador extends BaseControlador {

    @Inject
    private BarrioBean barrioBean;
    
    @Inject
    private CiudadBean ciudadBean;
    
    @Inject
    private ProvinciaBean provinciaBean;
    
    @Inject
    private PaisBean paisBean;
    
    private Pais pais;
    
    private Pais paisSeleccionado;
    
    private ProvinciaEstado provinciaEstado;
    
    private ProvinciaEstado provinciaEstadoSeleccionado;
    
    private Ciudad ciudad;
    
    private Ciudad ciudadSeleccionado;

    private Barrio barrio;
    
    private Barrio barrioSeleccionado;
    
    
    private List<Barrio> listaBarrio;
    
    private List<Ciudad> listaCiudad;
        
    private List<ProvinciaEstado> listaProvinciaEstado;
    
    private List<Pais> listaPais;

    @PostConstruct
    public void init() {
        
        barrio = new Barrio();
        ciudad = new Ciudad();        
        provinciaEstado = new ProvinciaEstado();
        pais = new Pais();
        
        barrioSeleccionado = new Barrio();
        ciudadSeleccionado = new Ciudad();        
        provinciaEstadoSeleccionado = new ProvinciaEstado();
        paisSeleccionado = new Pais();
        
        listaPais = paisBean.obtenerLista();
        
        setListaProvinciaEstado(new ArrayList<ProvinciaEstado>());
        setListaCiudad(new ArrayList<Ciudad>());
        setListaBarrio(new ArrayList<Barrio>());
    }
    
    public void cargarBarriosPorCiudad(){
        setListaBarrio(barrioBean.obtenerListaPorCiudad(ciudadSeleccionado));
    }
    
    public void cargarCiudadesPorProvincia(){
        setListaCiudad(ciudadBean.obtenerListaPorProvincia(provinciaEstadoSeleccionado));
    }
    
    public void cargarProvinciasPorPais(){
        setListaProvinciaEstado(provinciaBean.obtenerListaPorPais(paisSeleccionado));
    }
    
    public List<SelectItem> generarSelectItemDeCiudad() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Ciudad ciudadTmp : ciudadBean.obtenerListaPorProvincia(provinciaEstadoSeleccionado)) {
            selectItemsBuilder.add(ciudadTmp, ciudadTmp.getCiudad());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDeProvincia() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (ProvinciaEstado provinciaEstadoTmp : provinciaBean.obtenerListaPorPais(paisSeleccionado)) {
            selectItemsBuilder.add(provinciaEstadoTmp, provinciaEstadoTmp.getProvinciaEstado());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDePais() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Pais paisTmp : getListaPais()) {
            selectItemsBuilder.add(paisTmp, paisTmp.getPais());
        }
        return selectItemsBuilder.buildList();
    }

    public List<Ciudad> obtenerListaCiudad() {
        return ciudadBean.obtenerLista();
    }

    public void guardar() {
        try {
            paisBean.crear(getPais());
            listaPais = paisBean.obtenerLista();
            paisSeleccionado = new Pais();
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setPais(new Pais());
        }
    }
    
    public void guardarProvincia() {
        try {
            getProvinciaEstado().setPais(paisSeleccionado);
            provinciaBean.crear(getProvinciaEstado());
            listaProvinciaEstado = provinciaBean.obtenerListaPorPais(paisSeleccionado);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setProvinciaEstado(new ProvinciaEstado());
        }
    }
    
    public void guardarCiudad() {
        try {
            getCiudad().setProvinciaEstado(provinciaEstadoSeleccionado);
            ciudadBean.crear(getCiudad());
            listaCiudad = ciudadBean.obtenerListaPorProvincia(provinciaEstadoSeleccionado);            
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setCiudad(new Ciudad());
        }
    }
    
    public void guardarBarrio() {
        try {
            getBarrio().setCiudad(ciudadSeleccionado);
            barrioBean.crear(getBarrio());
            listaBarrio = barrioBean.obtenerListaPorCiudad(ciudadSeleccionado);            
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setBarrio(new Barrio());
        }
    }

    /**
     * @return the ciudad
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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
     * @return the pais
     */
    public Pais getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(Pais pais) {
        this.pais = pais;
    }

    /**
     * @return the listaPais
     */
    public List<Pais> getListaPais() {
        return listaPais;
    }

    /**
     * @param listaPais the listaPais to set
     */
    public void setListaPais(List<Pais> listaPais) {
        this.listaPais = listaPais;
    }

    /**
     * @return the provinciaEstado
     */
    public ProvinciaEstado getProvinciaEstado() {
        return provinciaEstado;
    }

    /**
     * @param provinciaEstado the provinciaEstado to set
     */
    public void setProvinciaEstado(ProvinciaEstado provinciaEstado) {
        this.provinciaEstado = provinciaEstado;
    }

    /**
     * @return the listaProvinciaEstado
     */
    public List<ProvinciaEstado> getListaProvinciaEstado() {
        return listaProvinciaEstado;
    }

    /**
     * @param listaProvinciaEstado the listaProvinciaEstado to set
     */
    public void setListaProvinciaEstado(List<ProvinciaEstado> listaProvinciaEstado) {
        this.listaProvinciaEstado = listaProvinciaEstado;
    }

    /**
     * @return the listaBarrio
     */
    public List<Barrio> getListaBarrio() {
        return listaBarrio;
    }

    /**
     * @param listaBarrio the listaBarrio to set
     */
    public void setListaBarrio(List<Barrio> listaBarrio) {
        this.listaBarrio = listaBarrio;
    }

    /**
     * @return the barrio
     */
    public Barrio getBarrio() {
        return barrio;
    }

    /**
     * @param barrio the barrio to set
     */
    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    /**
     * @return the paisSeleccionado
     */
    public Pais getPaisSeleccionado() {
        return paisSeleccionado;
    }

    /**
     * @param paisSeleccionado the paisSeleccionado to set
     */
    public void setPaisSeleccionado(Pais paisSeleccionado) {
        this.paisSeleccionado = paisSeleccionado;
    }

    /**
     * @return the provinciaEstadoSeleccionado
     */
    public ProvinciaEstado getProvinciaEstadoSeleccionado() {
        return provinciaEstadoSeleccionado;
    }

    /**
     * @param provinciaEstadoSeleccionado the provinciaEstadoSeleccionado to set
     */
    public void setProvinciaEstadoSeleccionado(ProvinciaEstado provinciaEstadoSeleccionado) {
        this.provinciaEstadoSeleccionado = provinciaEstadoSeleccionado;
    }

    /**
     * @return the ciudadSeleccionado
     */
    public Ciudad getCiudadSeleccionado() {
        return ciudadSeleccionado;
    }

    /**
     * @param ciudadSeleccionado the ciudadSeleccionado to set
     */
    public void setCiudadSeleccionado(Ciudad ciudadSeleccionado) {
        this.ciudadSeleccionado = ciudadSeleccionado;
    }

    /**
     * @return the barrioSeleccionado
     */
    public Barrio getBarrioSeleccionado() {
        return barrioSeleccionado;
    }

    /**
     * @param barrioSeleccionado the barrioSeleccionado to set
     */
    public void setBarrioSeleccionado(Barrio barrioSeleccionado) {
        this.barrioSeleccionado = barrioSeleccionado;
    }
}
