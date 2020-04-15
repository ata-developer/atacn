/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.HorarioBean;
import ec.com.ata.cn.logica.PeriodoBean;
import ec.com.ata.cn.logica.PeriodoHorarioBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.Horario;
import ec.com.ata.cn.modelo.Parqueadero;
import ec.com.ata.cn.modelo.Periodo;
import ec.com.ata.cn.modelo.PeriodoHorario;
import java.util.ArrayList;
import java.util.HashMap;

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
    
    @Inject
    private PeriodoHorarioBean periodoHorarioBean;
    
    private Periodo periodo;
    
    private Horario horario;
    
    private List<Periodo> listaPeriodo;
    
    private List<Horario> listaHorario;
    
    private Periodo periodoSeleccionado;
    
    private Horario horarioSeleccionado;
    
    private Establecimiento establecimientoSeleccionado;
    
    private List<PeriodoHorario> listaPeriodoHorario;
    
    private Periodo periodoSeleccionadoParaEstablecimiento;
    
    private List<Parqueadero> listaParqueaderoSeleccionado;
    
    @PostConstruct
    public void init() {
        setPeriodo(new Periodo());
        setListaPeriodo(periodoBean.obtenerLista());
        setHorario(new Horario());
        setListaHorario(horarioBean.obtenerLista());
        setPeriodoSeleccionado(new Periodo());
        setHorarioSeleccionado(new Horario());
        setEstablecimientoSeleccionado(new Establecimiento());
        setListaPeriodoHorario(new ArrayList<PeriodoHorario>());
        setPeriodoSeleccionadoParaEstablecimiento(new Periodo());
        setListaParqueaderoSeleccionado(new ArrayList<Parqueadero>());        
    }
    
    public void cargarListaParquederos() {
        setListaParqueaderoSeleccionado(getEstablecimientoSeleccionado().getListaParqueadero());
        System.out.println("tama;o lista: "+getListaParqueaderoSeleccionado().size());
    }
    
    public void guardarPeriodoHorario() {
        try {
            PeriodoHorario periodoHorario = new PeriodoHorario();
            periodoHorario.setPeriodo(getPeriodoSeleccionado());
            periodoHorario.setHorario(getHorarioSeleccionado());
            periodoHorarioBean.crear(periodoHorario);
            HashMap<String,Object> parametros = new HashMap();
            parametros.put("periodo", getPeriodoSeleccionado());
            setListaPeriodoHorario(periodoHorarioBean.obtenerListaPorParametros(parametros));
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setHorarioSeleccionado(new Horario());
        }
    }
    
    public void eliminarHorario(Horario horarioEntrada) {
        try {
            horarioBean.eliminar(horarioEntrada.getIdHorario());
            setListaHorario(horarioBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }
    
    public void guardarPeriodo() {
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
    
    public void guardarHorario() {
        try {
            horarioBean.crear(getHorario());
            setListaHorario(horarioBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setHorario(new Horario());
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
    
    /**
     * @return the periodoSeleccionado
     */
    public Periodo getPeriodoSeleccionado() {
        return periodoSeleccionado;
    }

    /**
     * @param periodoSeleccionado the periodoSeleccionado to set
     */
    public void setPeriodoSeleccionado(Periodo periodoSeleccionado) {
        this.periodoSeleccionado = periodoSeleccionado;
    }

    /**
     * @return the horarioSeleccionado
     */
    public Horario getHorarioSeleccionado() {
        return horarioSeleccionado;
    }

    /**
     * @param horarioSeleccionado the horarioSeleccionado to set
     */
    public void setHorarioSeleccionado(Horario horarioSeleccionado) {
        this.horarioSeleccionado = horarioSeleccionado;
    }

    /**
     * @return the establecimientoSeleccionado
     */
    public Establecimiento getEstablecimientoSeleccionado() {
        return establecimientoSeleccionado;
    }

    /**
     * @param establecimientoSeleccionado the establecimientoSeleccionado to set
     */
    public void setEstablecimientoSeleccionado(Establecimiento establecimientoSeleccionado) {
        this.establecimientoSeleccionado = establecimientoSeleccionado;
    }
    
    /**
     * @return the listaPeriodoHorario
     */
    public List<PeriodoHorario> getListaPeriodoHorario() {
        return listaPeriodoHorario;
    }

    /**
     * @param listaPeriodoHorario the listaPeriodoHorario to set
     */
    public void setListaPeriodoHorario(List<PeriodoHorario> listaPeriodoHorario) {
        this.listaPeriodoHorario = listaPeriodoHorario;
    }
    
    /**
     * @return the periodoSeleccionadoParaEstablecimiento
     */
    public Periodo getPeriodoSeleccionadoParaEstablecimiento() {
        return periodoSeleccionadoParaEstablecimiento;
    }

    /**
     * @param periodoSeleccionadoParaEstablecimiento the periodoSeleccionadoParaEstablecimiento to set
     */
    public void setPeriodoSeleccionadoParaEstablecimiento(Periodo periodoSeleccionadoParaEstablecimiento) {
        this.periodoSeleccionadoParaEstablecimiento = periodoSeleccionadoParaEstablecimiento;
    }
    
    /**
     * @return the listaParqueaderoSeleccionado
     */
    public List<Parqueadero> getListaParqueaderoSeleccionado() {
        return listaParqueaderoSeleccionado;
    }

    /**
     * @param listaParqueaderoSeleccionado the listaParqueaderoSeleccionado to set
     */
    public void setListaParqueaderoSeleccionado(List<Parqueadero> listaParqueaderoSeleccionado) {
        this.listaParqueaderoSeleccionado = listaParqueaderoSeleccionado;
    }
    
}
