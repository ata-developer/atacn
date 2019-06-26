/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.CategoriaBean;
import ec.com.ata.cn.logica.GrupoPrecioBean;
import ec.com.ata.cn.logica.TrabajoBean;
import ec.com.ata.cn.logica.TrabajoCategoriaPrecioBean;
import ec.com.ata.cn.modelo.Categoria;
import ec.com.ata.cn.modelo.Trabajo;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.omnifaces.util.selectitems.SelectItemsBuilder;

/**
 *
 * @author ATA1
 */
@SessionScoped
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

    private Trabajo trabajo;

    private List<Trabajo> listaTrabajo;

    private Categoria categoria;

    private GrupoPrecio grupoPrecio;

    private List<Categoria> listaCategoria;

    private List<GrupoPrecio> listaGrupoPrecio;

    private TrabajoCategoriaPrecio trabajoCategoriaPrecio;

    private List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecio;

    private List<Categoria> listaCategoriaTmp = null;

    private BigDecimal precioVentaPublico;

    private BigDecimal precioDescuento;

    @PostConstruct
    public void init() {
        grupoPrecio = new GrupoPrecio();
        categoria = new Categoria();
        trabajo = new Trabajo();
        listaGrupoPrecio = grupoPrecioBean.obtenerLista();
        listaCategoria = new ArrayList<>();
        listaTrabajo = new ArrayList<>();
        setTrabajoCategoriaPrecio(new TrabajoCategoriaPrecio());
        setListaMapaTrabajoCategoriaPrecio(new ArrayList<HashMap<String, Object>>());
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

    public void guardar() {
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
     * @param listaMapaTrabajoCategoriaPrecio the listaMapaTrabajoCategoriaPrecio to set
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

}
