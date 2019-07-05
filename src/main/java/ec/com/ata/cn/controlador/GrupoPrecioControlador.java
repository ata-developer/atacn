/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.CategoriaBean;
import ec.com.ata.cn.logica.EstablecimientoBean;
import ec.com.ata.cn.logica.GrupoPrecioBean;
import ec.com.ata.cn.logica.TrabajoBean;
import ec.com.ata.cn.logica.TrabajoCategoriaPrecioBean;
import ec.com.ata.cn.modelo.Categoria;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.Trabajo;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecioId;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.omnifaces.util.selectitems.SelectItemsBuilder;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author ATA1
 */
@FacesConfig
@ViewScoped
@Named
public class GrupoPrecioControlador extends BaseControlador {

    @Inject
    private GrupoPrecioBean grupoPrecioBean;

    @Inject
    private CategoriaBean categoriaBean;

    @Inject
    private TrabajoBean trabajoBean;

    @Inject
    private TrabajoCategoriaPrecioBean trabajoCategoriaTrabajoBean;
    
    @Inject
    private EstablecimientoBean establecimientoBean;

    private GrupoPrecio grupoPrecio;

    private GrupoPrecio grupoPrecioSeccionado;

    private Categoria categoria;

    private Categoria categoriaSeleccionado;

    private Trabajo trabajo;

    private Trabajo trabajoSeleccionado;

    private List<Trabajo> listaTrabajo;

    private List<Categoria> listaCategoria;

    private List<GrupoPrecio> listaGrupoPrecio;

    private TrabajoCategoriaPrecio trabajoCategoriaPrecio;

    private List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecio;

    private List<Categoria> listaCategoriaTmp = null;

    private BigDecimal precioVentaPublico;

    private BigDecimal precioDescuento;

    private List<Establecimiento> establecimientosOrigen;
    
    private List<Establecimiento> establecimientosDestino;
    
    private DualListModel<Establecimiento> listaEstablecimientos;

    @PostConstruct
    public void init() {
        grupoPrecio = new GrupoPrecio();
        categoria = new Categoria();
        trabajo = new Trabajo();
        grupoPrecioSeccionado = new GrupoPrecio();
        categoriaSeleccionado = new Categoria();
        trabajoSeleccionado = new Trabajo();
        listaGrupoPrecio = grupoPrecioBean.obtenerLista();
        listaCategoria = new ArrayList<>();
        listaTrabajo = new ArrayList<>();
        
        setEstablecimientosOrigen(new ArrayList<Establecimiento>());
        setEstablecimientosDestino(new ArrayList<Establecimiento>());
        setListaEstablecimientos(new DualListModel<Establecimiento>());

        setTrabajoCategoriaPrecio(new TrabajoCategoriaPrecio());
        setListaMapaTrabajoCategoriaPrecio(new ArrayList<HashMap<String, Object>>());
    }

    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Establecimiento) item).getNombre()).append("<br />");
        }
         
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
     
    public void onSelect(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().toString()));
    }
     
    public void onUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().toString()));
    }
     
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }
    
    public List<Categoria> listaCategoriasTemporal() {
        if (null == listaCategoriaTmp && null != grupoPrecioSeccionado.getIdGrupoPrecio()) {
            listaCategoriaTmp = new ArrayList<>();
            Categoria categoriaTmp = new Categoria();
            categoriaTmp.setCategoria(ConstantesUtil.TRABAJO_CATEGORIA);
            listaCategoriaTmp.add(categoriaTmp);
            for (Categoria categoriaX : categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecioSeccionado)) {
                listaCategoriaTmp.add(categoriaX);
            }
        }
        return listaCategoriaTmp;
    }

    public void guardarPrecioConfiguracion() {
        try {
            trabajoCategoriaPrecio = new TrabajoCategoriaPrecio();
            trabajoCategoriaPrecio.setCategoria(getCategoriaSeleccionado());
            trabajoCategoriaPrecio.setTrabajo(getTrabajoSeleccionado());
            trabajoCategoriaPrecio.setGrupoPrecio(getGrupoPrecioSeccionado());
            trabajoCategoriaPrecio.setPrecioDescuento(getPrecioDescuento());
            trabajoCategoriaPrecio.setPrecioVentaPublico(getPrecioVentaPublico());
            trabajoCategoriaTrabajoBean.guardar(trabajoCategoriaPrecio);
            setListaMapaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecio(grupoPrecioSeccionado));
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_GUARDAR + ":" + root.getMessage());
        } finally {

        }
    }

    public void cargarPrecio() {
        try {
            if (null != categoriaSeleccionado && null != trabajoSeleccionado) {
                TrabajoCategoriaPrecioId trabajoCategoriaPrecioId = new TrabajoCategoriaPrecioId();
                trabajoCategoriaPrecioId.setIdCategoria(categoriaSeleccionado.getIdCategoria());
                trabajoCategoriaPrecioId.setIdTrabajo(trabajoSeleccionado.getIdTrabajo());
                trabajoCategoriaPrecioId.setIdGrupoPrecio(grupoPrecioSeccionado.getIdGrupoPrecio());
                TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp = trabajoCategoriaTrabajoBean.obtenerPorId(trabajoCategoriaPrecioId);
                setListaMapaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecio(grupoPrecioSeccionado));
                if (null != trabajoCategoriaPrecioTmp) {
                    setPrecioVentaPublico(trabajoCategoriaPrecioTmp.getPrecioVentaPublico());
                    setPrecioDescuento(trabajoCategoriaPrecioTmp.getPrecioDescuento());
                    addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
                    return;
                } else {
                    setPrecioDescuento(null);
                    setPrecioVentaPublico(null);
                }

                addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.NO_EXISTE_REGISTRO);
            }
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_PRECIOS_CONTROLADOR_GUARDAR_ROOT + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_PRECIOS_CONTROLADOR_GUARDAR_EX + ":" + e.getMessage());
        }
    }

    public List<SelectItem> generarSelectItemDeTrabajos() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Trabajo trabajoTmp : getListaTrabajo()) {
            selectItemsBuilder.add(trabajoTmp, trabajoTmp.getDescripcion());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeCategorias() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Categoria categoriaTmp : getListaCategoria()) {
            selectItemsBuilder.add(categoriaTmp, categoriaTmp.getCategoria());
        }
        return selectItemsBuilder.buildList();
    }

    public void limpiarTrabajo() {
        trabajoSeleccionado = new Trabajo();
        precioVentaPublico = null;
        precioDescuento = null;
    }

    public void cargarListaCategoriaYTrabajos() {
        establecimientosOrigen = establecimientoBean.obtenerListaSinGrupoPrecio();
        establecimientosDestino = establecimientoBean.obtenerListaPorGrupoPrecio(grupoPrecioSeccionado);
        listaCategoria = categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecioSeccionado);
        listaTrabajo = trabajoBean.obtenerListaPorGrupoPrecio(grupoPrecioSeccionado);
        setListaMapaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecio(grupoPrecioSeccionado));
    }

    public List<GrupoPrecio> obtenerListaGrupoPrecio() {
        return grupoPrecioBean.obtenerLista();
    }

    public List<SelectItem> generarSelectItemDeGrupoPrecio() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (GrupoPrecio grupoPrecioTmp : getListaGrupoPrecio()) {
            selectItemsBuilder.add(grupoPrecioTmp, grupoPrecioTmp.getNombre());
        }
        return selectItemsBuilder.buildList();
    }

    public void guardarGrupoPrecio() {
        try {
            grupoPrecioBean.crear(getGrupoPrecio());
            listaGrupoPrecio = grupoPrecioBean.obtenerLista();
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setGrupoPrecio(new GrupoPrecio());
        }
    }

    public void guardarCategoria() {
        try {
            getCategoria().setGrupoPrecio(grupoPrecioSeccionado);
            categoriaBean.crear(getCategoria());
            listaCategoria = categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecioSeccionado);
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setCategoria(new Categoria());
        }
    }

    public void guardarTrabajo() {
        try {
            getTrabajo().setGrupoPrecio(grupoPrecioSeccionado);
            trabajoBean.crear(trabajo);
            listaTrabajo = trabajoBean.obtenerListaPorGrupoPrecio(grupoPrecioSeccionado);
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setTrabajo(new Trabajo());
        }
    }

    /**
     * @return the grupoPrecio
     */
    public GrupoPrecio getGrupoPrecio() {
        return grupoPrecio;
    }

    /**
     * @param grupoPrecio the grupoPrecio to set
     */
    public void setGrupoPrecio(GrupoPrecio grupoPrecio) {
        this.grupoPrecio = grupoPrecio;
    }

    /**
     * @return the listaGrupoPrecio
     */
    public List<GrupoPrecio> getListaGrupoPrecio() {
        return listaGrupoPrecio;
    }

    /**
     * @param listaGrupoPrecio the listaGrupoPrecio to set
     */
    public void setListaGrupoPrecio(List<GrupoPrecio> listaGrupoPrecio) {
        this.listaGrupoPrecio = listaGrupoPrecio;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the listaCategoria
     */
    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    /**
     * @param listaCategoria the listaCategoria to set
     */
    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
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

    /**
     * @return the trabajoCategoriaPrecio
     */
    public TrabajoCategoriaPrecio getTrabajoCategoriaPrecio() {
        return trabajoCategoriaPrecio;
    }

    /**
     * @param trabajoCategoriaPrecio the trabajoCategoriaPrecio to set
     */
    public void setTrabajoCategoriaPrecio(TrabajoCategoriaPrecio trabajoCategoriaPrecio) {
        this.trabajoCategoriaPrecio = trabajoCategoriaPrecio;
    }

    /**
     * @return the listaMapaTrabajoCategoriaPrecio
     */
    public List<HashMap<String, Object>> getListaMapaTrabajoCategoriaPrecio() {
        return listaMapaTrabajoCategoriaPrecio;
    }

    /**
     * @param listaMapaTrabajoCategoriaPrecio the
     * listaMapaTrabajoCategoriaPrecio to set
     */
    public void setListaMapaTrabajoCategoriaPrecio(List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecio) {
        this.listaMapaTrabajoCategoriaPrecio = listaMapaTrabajoCategoriaPrecio;
    }

    /**
     * @return the listaCategoriaTmp
     */
    public List<Categoria> getListaCategoriaTmp() {
        return listaCategoriaTmp;
    }

    /**
     * @param listaCategoriaTmp the listaCategoriaTmp to set
     */
    public void setListaCategoriaTmp(List<Categoria> listaCategoriaTmp) {
        this.listaCategoriaTmp = listaCategoriaTmp;
    }

    /**
     * @return the precioVentaPublico
     */
    public BigDecimal getPrecioVentaPublico() {
        return precioVentaPublico;
    }

    /**
     * @param precioVentaPublico the precioVentaPublico to set
     */
    public void setPrecioVentaPublico(BigDecimal precioVentaPublico) {
        this.precioVentaPublico = precioVentaPublico;
    }

    /**
     * @return the precioDescuento
     */
    public BigDecimal getPrecioDescuento() {
        return precioDescuento;
    }

    /**
     * @param precioDescuento the precioDescuento to set
     */
    public void setPrecioDescuento(BigDecimal precioDescuento) {
        this.precioDescuento = precioDescuento;
    }

    /**
     * @return the grupoPrecioSeccionado
     */
    public GrupoPrecio getGrupoPrecioSeccionado() {
        return grupoPrecioSeccionado;
    }

    /**
     * @param grupoPrecioSeccionado the grupoPrecioSeccionado to set
     */
    public void setGrupoPrecioSeccionado(GrupoPrecio grupoPrecioSeccionado) {
        this.grupoPrecioSeccionado = grupoPrecioSeccionado;
    }

    /**
     * @return the categoriaSeleccionado
     */
    public Categoria getCategoriaSeleccionado() {
        return categoriaSeleccionado;
    }

    /**
     * @param categoriaSeleccionado the categoriaSeleccionado to set
     */
    public void setCategoriaSeleccionado(Categoria categoriaSeleccionado) {
        this.categoriaSeleccionado = categoriaSeleccionado;
    }

    /**
     * @return the trabajoSeleccionado
     */
    public Trabajo getTrabajoSeleccionado() {
        return trabajoSeleccionado;
    }

    /**
     * @param trabajoSeleccionado the trabajoSeleccionado to set
     */
    public void setTrabajoSeleccionado(Trabajo trabajoSeleccionado) {
        this.trabajoSeleccionado = trabajoSeleccionado;
    }

    /**
     * @return the establecimientosOrigen
     */
    public List<Establecimiento> getEstablecimientosOrigen() {
        return establecimientosOrigen;
    }

    /**
     * @param establecimientosOrigen the establecimientosOrigen to set
     */
    public void setEstablecimientosOrigen(List<Establecimiento> establecimientosOrigen) {
        this.establecimientosOrigen = establecimientosOrigen;
    }

    /**
     * @return the establecimientosDestino
     */
    public List<Establecimiento> getEstablecimientosDestino() {
        return establecimientosDestino;
    }

    /**
     * @param establecimientosDestino the establecimientosDestino to set
     */
    public void setEstablecimientosDestino(List<Establecimiento> establecimientosDestino) {
        this.establecimientosDestino = establecimientosDestino;
    }

    /**
     * @return the listaEstablecimientos
     */
    public DualListModel<Establecimiento> getListaEstablecimientos() {
        return listaEstablecimientos;
    }

    /**
     * @param listaEstablecimientos the listaEstablecimientos to set
     */
    public void setListaEstablecimientos(DualListModel<Establecimiento> listaEstablecimientos) {
        this.listaEstablecimientos = listaEstablecimientos;
    }

   

}
