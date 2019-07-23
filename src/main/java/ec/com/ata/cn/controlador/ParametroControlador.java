/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.ParametroBean;
import ec.com.ata.cn.logica.TipoDocumentoBean;
import ec.com.ata.cn.logica.TipoFilaBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Parametro;
import ec.com.ata.cn.modelo.TipoDocumento;
import ec.com.ata.cn.modelo.TipoFila;

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
public class ParametroControlador extends BaseControlador {

    @Inject
    private TipoDocumentoBean tipoNumeracionDocumentoBean;

    @Inject
    private TipoFilaBean tipoFilaBean;

    @Inject
    private ParametroBean parametroBean;
    
    
    private TipoDocumento tipoNumeracionDocumento;

    private List<TipoDocumento> listaTipoNumeracionDocumento;

    private TipoFila tipoFila;

    private List<TipoFila> listaTipoFila;
    
    private Integer anioDesdeRango;
    
    private Integer anioHastaRango;
    

    @PostConstruct
    public void init() {
        setTipoFila(new TipoFila());
        setTipoNumeracionDocumento(new TipoDocumento());
        setListaTipoNumeracionDocumento(tipoNumeracionDocumentoBean.obtenerLista());
        setListaTipoFila(tipoFilaBean.obtenerLista());
        setAnioDesdeRango(new Integer(parametroBean.obtenerPorCodigo(Constante.ANIO_INICIO_RANGO) == null ? "0" : parametroBean.obtenerPorCodigo(Constante.ANIO_INICIO_RANGO).getValor()));
        setAnioHastaRango(new Integer(parametroBean.obtenerPorCodigo(Constante.ANIO_FIN_RANGO) == null ? "0" : parametroBean.obtenerPorCodigo(Constante.ANIO_FIN_RANGO).getValor()));
        
    }

    public List<TipoDocumento> obtenerListaTipoNumeracionDocumento() {
        return tipoNumeracionDocumentoBean.obtenerLista();
    }
    
    public void guardarRangoAnios() {
        try {
            Parametro parametroAnioInicioRango = new Parametro();
            parametroAnioInicioRango.setParametro(Constante.ANIO_INICIO_RANGO);
            parametroAnioInicioRango.setValor(anioDesdeRango.toString());            
            parametroBean.modificar(parametroAnioInicioRango);
            
            Parametro parametroAnioFinRango = new Parametro();
            parametroAnioFinRango.setParametro(Constante.ANIO_FIN_RANGO);
            parametroAnioFinRango.setValor(anioHastaRango.toString());
            parametroBean.modificar(parametroAnioFinRango);
            
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setTipoFila(new TipoFila());
        }
    }

    public void guardarTipoFila() {
        try {
            System.out.println("ec.com.ata.cn.controlador.ParametroControlador.guardar()");
            tipoFilaBean.crear(getTipoFila());
            setListaTipoFila(tipoFilaBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setTipoFila(new TipoFila());
        }
    }
    
    public void guardarTipoDocumento() {
        try {
            tipoNumeracionDocumentoBean.crear(getTipoNumeracionDocumento());
            setListaTipoNumeracionDocumento(tipoNumeracionDocumentoBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setTipoNumeracionDocumento(new TipoDocumento());
        }
    }

    /**
     * @return the tipoNumeracionDocumento
     */
    public TipoDocumento getTipoNumeracionDocumento() {
        return tipoNumeracionDocumento;
    }

    /**
     * @param tipoNumeracionDocumento the tipoNumeracionDocumento to set
     */
    public void setTipoNumeracionDocumento(TipoDocumento tipoNumeracionDocumento) {
        this.tipoNumeracionDocumento = tipoNumeracionDocumento;
    }

    /**
     * @return the listaTipoNumeracionDocumento
     */
    public List<TipoDocumento> getListaTipoNumeracionDocumento() {
        return listaTipoNumeracionDocumento;
    }

    /**
     * @param listaTipoNumeracionDocumento the listaTipoNumeracionDocumento to
     * set
     */
    public void setListaTipoNumeracionDocumento(List<TipoDocumento> listaTipoNumeracionDocumento) {
        this.listaTipoNumeracionDocumento = listaTipoNumeracionDocumento;
    }

    /**
     * @return the tipoFila
     */
    public TipoFila getTipoFila() {
        return tipoFila;
    }

    /**
     * @param tipoFila the tipoFila to set
     */
    public void setTipoFila(TipoFila tipoFila) {
        this.tipoFila = tipoFila;
    }

    /**
     * @return the listaTipoFila
     */
    public List<TipoFila> getListaTipoFila() {
        return listaTipoFila;
    }

    /**
     * @param listaTipoFila the listaTipoFila to set
     */
    public void setListaTipoFila(List<TipoFila> listaTipoFila) {
        this.listaTipoFila = listaTipoFila;
    }

    /**
     * @return the anioDesdeRango
     */
    public Integer getAnioDesdeRango() {
        return anioDesdeRango;
    }

    /**
     * @param anioDesdeRango the anioDesdeRango to set
     */
    public void setAnioDesdeRango(Integer anioDesdeRango) {
        this.anioDesdeRango = anioDesdeRango;
    }

    /**
     * @return the anioHastaRango
     */
    public Integer getAnioHastaRango() {
        return anioHastaRango;
    }

    /**
     * @param anioHastaRango the anioHastaRango to set
     */
    public void setAnioHastaRango(Integer anioHastaRango) {
        this.anioHastaRango = anioHastaRango;
    }
}
