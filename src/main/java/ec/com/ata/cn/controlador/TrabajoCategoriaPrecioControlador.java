/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.CategoriaBean;
import ec.com.ata.cn.logica.TrabajoBean;
import ec.com.ata.cn.logica.TrabajoCategoriaPrecioBean;
import ec.com.ata.cn.modelo.Categoria;
import ec.com.ata.cn.modelo.Trabajo;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecioId;
import java.math.BigDecimal;
import java.util.HashMap;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.selectitems.SelectItemsBuilder;

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 *
 * @author ATA1
 */
@SessionScoped
@Named
public class TrabajoCategoriaPrecioControlador extends BaseControlador {

    @Inject
    private TrabajoCategoriaPrecioBean trabajoCategoriaTrabajoBean;

    @Inject
    private TrabajoBean trabajoBean;

    @Inject
    private CategoriaBean categoriaBean;

    private Trabajo trabajo;

    private Categoria categoria;

    private TrabajoCategoriaPrecio trabajoCategoriaPrecio;

    private List<HashMap<String,Object>> listaMapaTrabajoCategoriaPrecio;

    private List<Trabajo> listaTrabajo;

    private List<Categoria> listaCategoria;

    private BigDecimal precioVentaPublico;

    private BigDecimal precioDescuento;

    @PostConstruct
    public void init() {
        setTrabajoCategoriaPrecio(new TrabajoCategoriaPrecio());        
        setListaTrabajo(trabajoBean.obtenerLista());
        setListaCategoria(categoriaBean.obtenerLista());
        setTrabajoCategoriaPrecio(new TrabajoCategoriaPrecio());
        setListaMapaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecio());
    }

    public void cargarPrecio() {
        try {
            if (null != categoria && null != trabajo) {
                TrabajoCategoriaPrecioId trabajoCategoriaPrecioId = new TrabajoCategoriaPrecioId();
                trabajoCategoriaPrecioId.setIdCategoria(categoria.getIdCategoria());
                trabajoCategoriaPrecioId.setIdTrabajo(trabajo.getIdTrabajo());
                trabajoCategoriaPrecio = trabajoCategoriaTrabajoBean.obtenerPorId(trabajoCategoriaPrecioId);
                if (null != trabajoCategoriaPrecio) {
                    setPrecioVentaPublico(trabajoCategoriaPrecio.getPrecioVentaPublico());
                    setPrecioDescuento(trabajoCategoriaPrecio.getPrecioDescuento());
                    addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
                    return;
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

    public List<TrabajoCategoriaPrecio> obtenerListaTrabajo() {
        return trabajoCategoriaTrabajoBean.obtenerLista();
    }

    public void guardar() {
        try {
            trabajoCategoriaTrabajoBean.guardar(trabajoCategoriaPrecio);
            setListaMapaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecio());
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_GUARDAR + ":" + root.getMessage());
        } finally {

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
     * @return the listaMapaTrabajoCategoriaPrecio
     */
    public List<HashMap<String,Object>> getListaMapaTrabajoCategoriaPrecio() {
        return listaMapaTrabajoCategoriaPrecio;
    }

    /**
     * @param listaMapaTrabajoCategoriaPrecio the listaMapaTrabajoCategoriaPrecio to set
     */
    public void setListaMapaTrabajoCategoriaPrecio(List<HashMap<String,Object>> listaMapaTrabajoCategoriaPrecio) {
        this.listaMapaTrabajoCategoriaPrecio = listaMapaTrabajoCategoriaPrecio;
    }
}
