/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.CiudadBean;
import ec.com.ata.cn.logica.PaisBean;
import ec.com.ata.cn.modelo.Ciudad;
import ec.com.ata.cn.modelo.Pais;

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
public class PaisCiudadControlador extends BaseControlador {

    @Inject
    private CiudadBean ciudadBean;
    
    @Inject
    private PaisBean paisBean;

    private Ciudad ciudad;
    
    private Pais pais;

    private List<Ciudad> listaCiudad;
    
    private List<Pais> listaPais;

    @PostConstruct
    public void init() {
        ciudad = new Ciudad();
        pais = new Pais();
        
        listaPais = paisBean.obtenerLista();
        listaCiudad = ciudadBean.obtenerLista();
        setPais(new Pais());
        setListaPais(paisBean.obtenerLista());
        setCiudad(new Ciudad());
        setListaCiudad(ciudadBean.obtenerLista());
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
    
    public void guardarCiudad() {
        try {
            ciudadBean.crear(getCiudad());
            listaCiudad = ciudadBean.obtenerLista();            
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
}
