/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.BarrioBean;
import ec.com.ata.cn.logica.CiudadBean;
import ec.com.ata.cn.logica.PaisBean;
import ec.com.ata.cn.logica.ProvinciaBean;
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

    private Barrio barrio;
    
    private Ciudad ciudad;
    
    private ProvinciaEstado provinciaEstado;
    
    private Pais pais;

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
        
        listaPais = paisBean.obtenerLista();
        
        setListaProvinciaEstado(new ArrayList<ProvinciaEstado>());
        setListaCiudad(new ArrayList<Ciudad>());
        setListaBarrio(new ArrayList<Barrio>());
    }
    
    public void cargarBarriosPorCiudad(){
        setListaBarrio(barrioBean.obtenerListaPorCiudad(ciudad));
    }
    
    public void cargarCiudadesPorProvincia(){
        setListaCiudad(ciudadBean.obtenerListaPorProvincia(provinciaEstado));
    }
    
    public void cargarProvinciasPorPais(){
        setListaProvinciaEstado(provinciaBean.obtenerListaPorPais(pais));
    }
    
    public List<SelectItem> generarSelectItemDeCiudad() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Ciudad ciudadTmp : getListaCiudad()) {
            selectItemsBuilder.add(ciudadTmp, ciudadTmp.getCiudad());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDeProvincia() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (ProvinciaEstado provinciaEstadoTmp : getListaProvinciaEstado()) {
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
            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setPais(new Pais());
        }
    }
    
    public void guardarProvincia() {
        try {
            getProvinciaEstado().setPais(pais);
            provinciaBean.crear(getProvinciaEstado());
            listaProvinciaEstado = provinciaBean.obtenerListaPorPais(pais);            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            e.printStackTrace();
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setProvinciaEstado(new ProvinciaEstado());
        }
    }
    
    public void guardarCiudad() {
        try {
            getCiudad().setProvinciaEstado(provinciaEstado);
            ciudadBean.crear(getCiudad());
            listaCiudad = ciudadBean.obtenerListaPorProvincia(provinciaEstado);            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setCiudad(new Ciudad());
        }
    }
    
    public void guardarBarrio() {
        try {
            getBarrio().setCiudad(ciudad);
            barrioBean.crear(getBarrio());
            listaBarrio = barrioBean.obtenerListaPorCiudad(ciudad);            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setCiudad(new Ciudad());
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
}
