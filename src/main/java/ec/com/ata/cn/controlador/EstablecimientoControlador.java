/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.CiudadBean;
import ec.com.ata.cn.logica.EstablecimientoBean;
import ec.com.ata.cn.logica.ParqueaderoBean;
import ec.com.ata.cn.logica.PeriodoEstablecimientoBean;
import ec.com.ata.cn.logica.ProcesarHorarioBean;
import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Ciudad;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.Parqueadero;
import ec.com.ata.cn.modelo.Periodo;
import ec.com.ata.cn.modelo.PeriodoEstablecimiento;
import ec.com.ata.cn.modelo.PeriodoEstablecimientoFecha;
import ec.com.ata.cn.modelo.Usuario;
import java.util.ArrayList;
import java.util.HashMap;

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
public class EstablecimientoControlador extends BaseControlador {
    
    @Inject
    private EstablecimientoBean establecimientoBean;

    @Inject
    private CiudadBean ciudadBean;

    @Inject
    private UsuarioBean usuarioBean;

    @Inject
    private ParqueaderoBean parqueaderoBean;

    @Inject
    private ProcesarHorarioBean procesarHorarioBean;

    @Inject
    private PeriodoEstablecimientoBean periodoEstablecimientoBean;

    private Establecimiento establecimiento;

    private List<Establecimiento> listaEstablecimiento;

    private List<Ciudad> listaCiudad;

    private List<Usuario> listaUsuario;

    private Parqueadero parqueadero;

    private List<Parqueadero> listaParqueadero;

    private Establecimiento establecimientoSeleccionado;

    private Long mesSeleccionado;

    private List<PeriodoEstablecimientoFecha> listaFecha;

    private List<PeriodoEstablecimientoFecha> listaSemanas;

    //private Long numeroColumna = 0l;
    private List<Long> numeroColumna;

    private List<List<PeriodoEstablecimientoFecha>> semana;

    private Periodo periodoSeleccionado;

    private List<PeriodoEstablecimiento> listaPeriodoEstablecimiento;

    private Periodo periodoSeleccionadoParaProceso;

    private Periodo periodoSeleccionadoParaCalendario;
    
    private List<HashMap<String, PeriodoEstablecimientoFecha>> listaPEF;

    @PostConstruct
    public void init() {
        setNumeroColumna(cargarColumnas());
        establecimiento = new Establecimiento();
        listaEstablecimiento = establecimientoBean.obtenerLista();
        listaCiudad = ciudadBean.obtenerLista();
        setListaUsuario(usuarioBean.obtenerLista());
        setParqueadero(new Parqueadero());
        setListaParqueadero(new ArrayList<Parqueadero>());
        setEstablecimientoSeleccionado(new Establecimiento());
        setListaPeriodoEstablecimiento(new ArrayList<PeriodoEstablecimiento>());
        setPeriodoSeleccionadoParaCalendario(new Periodo());

    }

    public void guardarPeriodoEstablecimiento() {
        try {
            PeriodoEstablecimiento periodoEstablecimiento = new PeriodoEstablecimiento();
            periodoEstablecimiento.setEstablecimiento(getEstablecimientoSeleccionado());
            periodoEstablecimiento.setPeriodo(getPeriodoSeleccionado());
            periodoEstablecimientoBean.crear(periodoEstablecimiento);
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("establecimiento", establecimiento);
            setListaPeriodoEstablecimiento(periodoEstablecimientoBean.obtenerListaPorParametros(parametros));
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setEstablecimiento(new Establecimiento());
        }
    }

    private List<Long> cargarColumnas() {
        List<Long> columnas = new ArrayList<>();
        columnas.add(1l);
        columnas.add(2l);
        columnas.add(3l);
        columnas.add(4l);
        columnas.add(5l);
        return columnas;
    }

    public void cargarFechas() {
        try {
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("periodo", getPeriodoSeleccionadoParaCalendario());
            parametros.put("mes", getMesSeleccionado());
            parametros.put("diaMesOrderByAsc", null);
            //setSemana(procesarHorarioBean.generarCalendario(parametros));   
            setListaPEF(procesarHorarioBean.generarCalendarioHash(parametros));
            
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            e.printStackTrace();
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public void procesarHorarios() {
        try {
            procesarHorarioBean.procesarPeriodoEstablecimientoHorario(getEstablecimientoSeleccionado(), getPeriodoSeleccionado());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setEstablecimiento(new Establecimiento());
        }
    }

    public void cargarListaParquederos() {

        System.out.println("tama;o lista: " + getEstablecimientoSeleccionado().getListaParqueadero().size());
    }

    public void seleccionarEstablecimiento(Establecimiento establecimientEntrada) {
        setEstablecimientoSeleccionado(establecimientEntrada);
        HashMap parametros = new HashMap();
        parametros.put("establecimiento", getEstablecimientoSeleccionado());
        List<Parqueadero> listaParqueaderoTmp = parqueaderoBean.obtenerListaPorParametros(parametros);
        setListaParqueadero(listaParqueaderoTmp);
        parametros = new HashMap();
        parametros.put("establecimiento", getEstablecimientoSeleccionado());
        setListaPeriodoEstablecimiento(periodoEstablecimientoBean.obtenerListaPorParametros(parametros));
        addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
    }

    public List<Establecimiento> obtenerListaEstablecimiento() {
        return establecimientoBean.obtenerLista();
    }

    public List<SelectItem> generarSelectItemDeCiudad() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Ciudad ciudadTmp : getListaCiudad()) {
            selectItemsBuilder.add(ciudadTmp, ciudadTmp.getCiudad() + " - " + ciudadTmp.getProvinciaEstado().getProvinciaEstado() + " - " + ciudadTmp.getProvinciaEstado().getPais().getPais());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeUsuario() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Usuario usuarioTmp : getListaUsuario()) {
            selectItemsBuilder.add(usuarioTmp, usuarioTmp.getUsuario());
        }
        return selectItemsBuilder.buildList();
    }

    public void guardar() {
        try {
            establecimientoBean.crear(getEstablecimiento());
            listaEstablecimiento = establecimientoBean.obtenerLista();
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setEstablecimiento(new Establecimiento());
        }
    }

    public void guardarParqueadero() {
        try {
            parqueadero.setEstablecimiento(getEstablecimientoSeleccionado());
            parqueaderoBean.crear(parqueadero);
            HashMap parametros = new HashMap();
            parametros.put("establecimiento", getEstablecimientoSeleccionado());
            List<Parqueadero> listaParqueaderoTmp = parqueaderoBean.obtenerListaPorParametros(parametros);
            setListaParqueadero(listaParqueaderoTmp);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setParqueadero(new Parqueadero());
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
     * @return the listaUsuario
     */
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * @param listaUsuario the listaUsuario to set
     */
    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    /**
     * @return the listaParqueadero
     */
    public List<Parqueadero> getListaParqueadero() {
        return listaParqueadero;
    }

    /**
     * @param listaParqueadero the listaParqueadero to set
     */
    public void setListaParqueadero(List<Parqueadero> listaParqueadero) {
        this.listaParqueadero = listaParqueadero;
    }

    /**
     * @return the parqueadero
     */
    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    /**
     * @param parqueadero the parqueadero to set
     */
    public void setParqueadero(Parqueadero parqueadero) {
        this.parqueadero = parqueadero;
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
     * @return the mesSeleccionado
     */
    public Long getMesSeleccionado() {
        return mesSeleccionado;
    }

    /**
     * @param mesSeleccionado the mesSeleccionado to set
     */
    public void setMesSeleccionado(Long mesSeleccionado) {
        this.mesSeleccionado = mesSeleccionado;
    }

    /**
     * @return the listaFecha
     */
    public List<PeriodoEstablecimientoFecha> getListaFecha() {
        return listaFecha;
    }

    /**
     * @param listaFecha the listaFecha to set
     */
    public void setListaFecha(List<PeriodoEstablecimientoFecha> listaFecha) {
        this.listaFecha = listaFecha;
    }

    /**
     * @return the numeroColumna
     */
    public List<Long> getNumeroColumna() {
        return numeroColumna;
    }

    /**
     * @param numeroColumna the numeroColumna to set
     */
    public void setNumeroColumna(List<Long> numeroColumna) {
        this.numeroColumna = numeroColumna;
    }

    /**
     * @return the listaSemanas
     */
    public List<PeriodoEstablecimientoFecha> getListaSemanas() {
        return listaSemanas;
    }

    /**
     * @param listaSemanas the listaSemanas to set
     */
    public void setListaSemanas(List<PeriodoEstablecimientoFecha> listaSemanas) {
        this.listaSemanas = listaSemanas;
    }

    /**
     * @return the semana
     */
    public List<List<PeriodoEstablecimientoFecha>> getSemana() {
        return semana;
    }

    /**
     * @param semana the semana to set
     */
    public void setSemana(List<List<PeriodoEstablecimientoFecha>> semana) {
        this.semana = semana;
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
     * @return the listaPeriodoEstablecimiento
     */
    public List<PeriodoEstablecimiento> getListaPeriodoEstablecimiento() {
        return listaPeriodoEstablecimiento;
    }

    /**
     * @param listaPeriodoEstablecimiento the listaPeriodoEstablecimiento to set
     */
    public void setListaPeriodoEstablecimiento(List<PeriodoEstablecimiento> listaPeriodoEstablecimiento) {
        this.listaPeriodoEstablecimiento = listaPeriodoEstablecimiento;
    }

    /**
     * @return the periodoSeleccionadoParaProceso
     */
    public Periodo getPeriodoSeleccionadoParaProceso() {
        return periodoSeleccionadoParaProceso;
    }

    /**
     * @param periodoSeleccionadoParaProceso the periodoSeleccionadoParaProceso
     * to set
     */
    public void setPeriodoSeleccionadoParaProceso(Periodo periodoSeleccionadoParaProceso) {
        this.periodoSeleccionadoParaProceso = periodoSeleccionadoParaProceso;
    }
    
    /**
     * @return the periodoSeleccionadoParaCalendario
     */
    public Periodo getPeriodoSeleccionadoParaCalendario() {
        return periodoSeleccionadoParaCalendario;
    }

    /**
     * @param periodoSeleccionadoParaCalendario the
     * periodoSeleccionadoParaCalendario to set
     */
    public void setPeriodoSeleccionadoParaCalendario(Periodo periodoSeleccionadoParaCalendario) {
        this.periodoSeleccionadoParaCalendario = periodoSeleccionadoParaCalendario;
    }
    
    /**
     * @return the listaPEF
     */
    public List<HashMap<String, PeriodoEstablecimientoFecha>> getListaPEF() {
        return listaPEF;
    }

    /**
     * @param listaPEF the listaPEF to set
     */
    public void setListaPEF(List<HashMap<String, PeriodoEstablecimientoFecha>> listaPEF) {
        this.listaPEF = listaPEF;
    }
}
