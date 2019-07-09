/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.TipoDocumentoBean;
import ec.com.ata.cn.modelo.TipoDocumento;

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
public class TipoDocumentoControlador extends BaseControlador {

    @Inject
    private TipoDocumentoBean tipoNumeracionDocumentoBean;

    private TipoDocumento tipoNumeracionDocumento;

    private List<TipoDocumento> listaTipoNumeracionDocumento;

    @PostConstruct
    public void init() {
        tipoNumeracionDocumento = new TipoDocumento();
        listaTipoNumeracionDocumento = tipoNumeracionDocumentoBean.obtenerLista();
    }

    public List<TipoDocumento> obtenerListaTipoNumeracionDocumento() {
        return tipoNumeracionDocumentoBean.obtenerLista();
    }

    public void guardar() {
        try {
            getTipoNumeracionDocumento().setTipoDocumento(getTipoNumeracionDocumento().getTipoDocumento().trim());
            tipoNumeracionDocumentoBean.crear(getTipoNumeracionDocumento());
            listaTipoNumeracionDocumento = tipoNumeracionDocumentoBean.obtenerLista();            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
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
     * @param listaTipoNumeracionDocumento the listaTipoNumeracionDocumento to set
     */
    public void setListaTipoNumeracionDocumento(List<TipoDocumento> listaTipoNumeracionDocumento) {
        this.listaTipoNumeracionDocumento = listaTipoNumeracionDocumento;
    }
}
