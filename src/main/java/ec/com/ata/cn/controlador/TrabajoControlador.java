/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.TrabajoBean;
import ec.com.ata.cn.modelo.Trabajo;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.postgresql.util.PSQLException;

/**
 *
 * @author ATA1
 */
@SessionScoped
@Named
public class TrabajoControlador extends BaseControlador {

    @Inject
    private TrabajoBean trabajoBean;

    private Trabajo trabajo;

    private List<Trabajo> listaTrabajo;

    @PostConstruct
    public void init() {
        trabajo = new Trabajo();
        listaTrabajo = trabajoBean.obtenerLista();
    }

    public List<Trabajo> obtenerListaTrabajo() {
        return trabajoBean.obtenerLista();
    }

    public void guardar() {
        try {
            trabajoBean.crear(getTrabajo());
            listaTrabajo = trabajoBean.obtenerLista();            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_GUARDAR + ":" + root.getMessage());
        } finally {
            setTrabajo(new Trabajo());
        }
    }

    /**
     * @return the trabajo
     */
    public Trabajo getTrabajo() {
        return trabajo;
    }

    /**
     * @param trabajo the trabajo to set
     */
    public void setTrabajo(Trabajo trabajo) {
        this.trabajo = trabajo;
    }

    /**
     * @return the listaTrabajo
     */
    public List<Trabajo> getListaTrabajo() {
        return listaTrabajo;
    }

    /**
     * @param listaTrabajo the listaTrabajo to set
     */
    public void setListaTrabajo(List<Trabajo> listaTrabajo) {
        this.listaTrabajo = listaTrabajo;
    }
}
