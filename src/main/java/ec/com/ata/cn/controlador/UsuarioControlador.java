/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.modelo.Usuario;

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
public class UsuarioControlador extends BaseControlador {

    @Inject
    private UsuarioBean usuarioBean;

    private Usuario usuario;

    private List<Usuario> listaUsuario;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listaUsuario = usuarioBean.obtenerLista();
    }

    public List<Usuario> obtenerListaUsuario() {
        return usuarioBean.obtenerLista();
    }

    public void guardar() {
        try {
            usuarioBean.crear(getUsuario());
            listaUsuario = usuarioBean.obtenerLista();            
            addInfoMessage(ConstantesUtil.EXITO, ConstantesUtil.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(ConstantesUtil.ERROR, ConstantesUtil.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setUsuario(new Usuario());
        }
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
}
