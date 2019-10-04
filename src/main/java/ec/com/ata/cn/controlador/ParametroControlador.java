/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.LugarVehiculoTrabajoBean;
import ec.com.ata.cn.logica.ParametroBean;
import ec.com.ata.cn.logica.ParteBean;
import ec.com.ata.cn.logica.TipoDocumentoBean;
import ec.com.ata.cn.logica.TipoFilaBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.LugarVehiculoTrabajo;
import ec.com.ata.cn.modelo.Parametro;
import ec.com.ata.cn.modelo.Parte;
import ec.com.ata.cn.modelo.TipoDocumento;
import ec.com.ata.cn.modelo.TipoFila;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

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
    
    @Inject
    private LugarVehiculoTrabajoBean lugarVehiculoTrabajoBean;
    
    @Inject
    private ParteBean parteBean;
    
    private TipoDocumento tipoNumeracionDocumento;
    
    private List<TipoDocumento> listaTipoNumeracionDocumento;
    
    private TipoFila tipoFila;
    
    private List<TipoFila> listaTipoFila;
    
    private Integer anioDesdeRango;
    
    private Integer anioHastaRango;
    
    private LugarVehiculoTrabajo lugarVehiculoTrabajo;
    
    private List<LugarVehiculoTrabajo> listaLugarVehiculoTrabajo;
    
    private Parte parte;
    
    private List<Parte> listaParte;
    
    private Boolean partePrincipal;
    
    private TreeNode nodoPrincipal;
    
    private TreeNode nodoSeleccionado;
    
    private Parte parteSeleccionada;
    
    @PostConstruct
    public void init() {
        setTipoFila(new TipoFila());
        setTipoNumeracionDocumento(new TipoDocumento());
        setListaTipoNumeracionDocumento(tipoNumeracionDocumentoBean.obtenerLista());
        setListaTipoFila(tipoFilaBean.obtenerLista());
        setAnioDesdeRango(new Integer(parametroBean.obtenerPorCodigo(Constante.ANIO_INICIO_RANGO) == null ? "0" : parametroBean.obtenerPorCodigo(Constante.ANIO_INICIO_RANGO).getValor()));
        setAnioHastaRango(new Integer(parametroBean.obtenerPorCodigo(Constante.ANIO_FIN_RANGO) == null ? "0" : parametroBean.obtenerPorCodigo(Constante.ANIO_FIN_RANGO).getValor()));
        setLugarVehiculoTrabajo(new LugarVehiculoTrabajo());
        setListaLugarVehiculoTrabajo(lugarVehiculoTrabajoBean.obtenerLista());
        setParte(new Parte());
        setListaParte(parteBean.obtenerLista());
        setNodoPrincipal(parteBean.cargarNodoPrincipal());
        setNodoSeleccionado(new DefaultTreeNode());
        //cargarArbolPrincipal();
        setPartePrincipal(false);
    }
    
    public void cargarArbolPrincipal() {
        List<Parte> partesPrincipales = parteBean.obtenerListaPorPadre(null);
        for (Parte parteTmp : partesPrincipales) {
            getNodoPrincipal().getChildren().add(new DefaultTreeNode(parteTmp));
        }
    }
    
    public void seleccionarPartePrincipal() {
        System.out.println("seleccionarPartePrincipal()");
        if (partePrincipal) {
            parte.setPadre(null);
        }        
    }
    
    public void nodoSeleccionadoPartePadre(NodeSelectEvent event) {
        System.out.println("nodoSeleccionadoPartePadre()");        
        System.out.println("parte(): " + ((Parte) nodoSeleccionado.getData()).getParte());
        nodoSeleccionado.setExpanded(true);
        parte.setPadre(((Parte) nodoSeleccionado.getData()));
        /*
        List<Parte> partesPrincipales = parteBean.obtenerListaPorPadre((Parte) nodoSeleccionado.getData());
        event.getTreeNode().getChildren().clear();
        for (Parte parteTmp : partesPrincipales) {
            event.getTreeNode().getChildren().add(new DefaultTreeNode(parteTmp));
        }*/
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
    
    public void guardarLugarVehiculoTrabajo() {
        try {
            System.out.println("ec.com.ata.cn.controlador.ParametroControlador.guardarLugarVehiculoTrabajo()");
            lugarVehiculoTrabajoBean.crear(getLugarVehiculoTrabajo());
            setListaLugarVehiculoTrabajo(lugarVehiculoTrabajoBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setLugarVehiculoTrabajo(new LugarVehiculoTrabajo());
        }
    }
    
    public void guardarParte() {
        try {
            System.out.println("ec.com.ata.cn.controlador.ParametroControlador.guardarParte()");
            //getParte().setPadre(getParteSeleccionada());
            parteBean.crear(getParte());
            setListaParte(parteBean.obtenerLista());
            setNodoPrincipal(parteBean.cargarNodoPrincipal());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setParte(new Parte());
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

    /**
     * @return the lugarVehiculoTrabajo
     */
    public LugarVehiculoTrabajo getLugarVehiculoTrabajo() {
        return lugarVehiculoTrabajo;
    }

    /**
     * @param lugarVehiculoTrabajo the lugarVehiculoTrabajo to set
     */
    public void setLugarVehiculoTrabajo(LugarVehiculoTrabajo lugarVehiculoTrabajo) {
        this.lugarVehiculoTrabajo = lugarVehiculoTrabajo;
    }

    /**
     * @return the listaLugarVehiculoTrabajo
     */
    public List<LugarVehiculoTrabajo> getListaLugarVehiculoTrabajo() {
        return listaLugarVehiculoTrabajo;
    }

    /**
     * @param listaLugarVehiculoTrabajo the listaLugarVehiculoTrabajo to set
     */
    public void setListaLugarVehiculoTrabajo(List<LugarVehiculoTrabajo> listaLugarVehiculoTrabajo) {
        this.listaLugarVehiculoTrabajo = listaLugarVehiculoTrabajo;
    }

    /**
     * @return the parte
     */
    public Parte getParte() {
        return parte;
    }

    /**
     * @param parte the parte to set
     */
    public void setParte(Parte parte) {
        this.parte = parte;
    }

    /**
     * @return the listaParte
     */
    public List<Parte> getListaParte() {
        return listaParte;
    }

    /**
     * @param listaParte the listaParte to set
     */
    public void setListaParte(List<Parte> listaParte) {
        this.listaParte = listaParte;
    }

    /**
     * @return the partePrincipal
     */
    public Boolean getPartePrincipal() {
        return partePrincipal;
    }

    /**
     * @param partePrincipal the partePrincipal to set
     */
    public void setPartePrincipal(Boolean partePrincipal) {
        this.partePrincipal = partePrincipal;
    }

    /**
     * @return the nodoPrincipal
     */
    public TreeNode getNodoPrincipal() {
        return nodoPrincipal;
    }

    /**
     * @param nodoPrincipal the nodoPrincipal to set
     */
    public void setNodoPrincipal(TreeNode nodoPrincipal) {
        this.nodoPrincipal = nodoPrincipal;
    }

    /**
     * @return the nodoSeleccionado
     */
    public TreeNode getNodoSeleccionado() {
        return nodoSeleccionado;
    }

    /**
     * @param nodoSeleccionado the nodoSeleccionado to set
     */
    public void setNodoSeleccionado(TreeNode nodoSeleccionado) {
        this.nodoSeleccionado = nodoSeleccionado;
    }

    /**
     * @return the parteSeleccionada
     */
    public Parte getParteSeleccionada() {
        parteSeleccionada = nodoSeleccionado == null ? new Parte() : (nodoSeleccionado.getData() == null ? new Parte() : (Parte) nodoSeleccionado.getData());
        return parteSeleccionada;
    }

    /**
     * @param parteSeleccionada the parteSeleccionada to set
     */
    public void setParteSeleccionada(Parte parteSeleccionada) {
        this.parteSeleccionada = parteSeleccionada;
    }
}
