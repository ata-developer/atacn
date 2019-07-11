/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;


import ec.com.ata.cn.logica.CategoriaBean;
import ec.com.ata.cn.logica.GrupoPrecioBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Categoria;
import ec.com.ata.cn.modelo.GrupoPrecio;
import java.util.ArrayList;

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
public class CategoriaControlador extends BaseControlador {

    @Inject
    private CategoriaBean categoriaBean;
    
    @Inject
    private GrupoPrecioBean grupoPrecioBean;

    private Categoria categoria;
    
    private GrupoPrecio grupoPrecio;

    private List<Categoria> listaCategoria;
    
    private List<GrupoPrecio> listaGrupoPrecio;

    @PostConstruct
    public void init() {
        categoria = new Categoria();
        listaCategoria = new ArrayList<>();
        setListaGrupoPrecio(grupoPrecioBean.obtenerLista());
    }

    public List<Categoria> obtenerListaCategoria() {
        return categoriaBean.obtenerLista();
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
            categoriaBean.crear(getCategoria());
            listaCategoria = categoriaBean.obtenerLista();            
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setCategoria(new Categoria());
        }
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
}
