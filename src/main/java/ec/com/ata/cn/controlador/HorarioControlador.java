/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.HorarioBean;
import ec.com.ata.cn.logica.PeriodoBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Horario;
import ec.com.ata.cn.modelo.Periodo;
import java.util.ArrayList;

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
public class HorarioControlador extends BaseControlador {

    
    
    @Inject
    private PeriodoBean periodoBean; 
    
    @Inject
    private HorarioBean horarioBean;
    
    private Periodo periodo;
    
    private Horario horario;
    
    private List<Periodo> listaPeriodo;
    
    private List<Horario> listaHorario;
    

    @PostConstruct
    public void init() {
        setPeriodo(new Periodo());
        setListaPeriodo(periodoBean.obtenerLista());
        setHorario(new Horario());
        setListaHorario(new ArrayList<Horario>());
    }
    
    public void guardar() {
        try {
            periodoBean.crear(getPeriodo());
            setListaPeriodo(periodoBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setPeriodo(new Periodo());
        }
    }
    
    /**
     * @return the listaPeriodo
     */
    public List<Periodo> getListaPeriodo() {
        return listaPeriodo;
    }

    /**
     * @param listaPeriodo the listaPeriodo to set
     */
    public void setListaPeriodo(List<Periodo> listaPeriodo) {
        this.listaPeriodo = listaPeriodo;
    }

    /**
     * @return the periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
    
    /**
     * @return the listaHorario
     */
    public List<Horario> getListaHorario() {
        return listaHorario;
    }

    /**
     * @param listaHorario the listaHorario to set
     */
    public void setListaHorario(List<Horario> listaHorario) {
        this.listaHorario = listaHorario;
    }

    /**
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
}
