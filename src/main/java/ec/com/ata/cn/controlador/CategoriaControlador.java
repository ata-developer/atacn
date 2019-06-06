/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.CategoriaBean;
import ec.com.ata.cn.modelo.Categoria;

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
public class CategoriaControlador extends BaseControlador {

    @Inject
    private CategoriaBean trabajoBean;

    private Categoria trabajo;

    private List<Categoria> listaCategoria;

    @PostConstruct
    public void init() {
        trabajo = new Categoria();
        listaCategoria = trabajoBean.obtenerLista();
    }

    public List<Categoria> obtenerListaCategoria() {
        return trabajoBean.obtenerLista();
    }

    public void guardar() {
        try {
            getCategoria().setCategoria(getCategoria().getCategoria().trim());
            trabajoBean.crear(getCategoria());
            listaCategoria = trabajoBean.obtenerLista();            
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

    /**
     * @return the trabajo
     */
    public Categoria getCategoria() {
        return trabajo;
    }

    /**
     * @param trabajo the trabajo to set
     */
    public void setCategoria(Categoria trabajo) {
        this.trabajo = trabajo;
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
}
