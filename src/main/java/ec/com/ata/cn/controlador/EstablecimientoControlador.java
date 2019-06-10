/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.EstablecimientoBean;
import ec.com.ata.cn.modelo.Establecimiento;

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
public class EstablecimientoControlador extends BaseControlador {

    @Inject
    private EstablecimientoBean establecimientoBean;

    private Establecimiento establecimiento;

    private List<Establecimiento> listaEstablecimiento;

    @PostConstruct
    public void init() {
        establecimiento = new Establecimiento();
        listaEstablecimiento = establecimientoBean.obtenerLista();
    }

    public List<Establecimiento> obtenerListaEstablecimiento() {
        return establecimientoBean.obtenerLista();
    }

    public void guardar() {
        try {
            establecimientoBean.crear(getEstablecimiento());
            listaEstablecimiento = establecimientoBean.obtenerLista();            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setEstablecimiento(new Establecimiento());
        }
    }

    /**
     * @return the establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * @return the listaEstablecimiento
     */
    public List<Establecimiento> getListaEstablecimiento() {
        return listaEstablecimiento;
    }

    /**
     * @param listaEstablecimiento the listaEstablecimiento to set
     */
    public void setListaEstablecimiento(List<Establecimiento> listaEstablecimiento) {
        this.listaEstablecimiento = listaEstablecimiento;
    }
}
