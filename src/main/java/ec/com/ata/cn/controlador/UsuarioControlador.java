/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;


import ec.com.ata.cn.logica.EquipoBean;
import ec.com.ata.cn.logica.TipoDocumentoBean;
import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Equipo;
import ec.com.ata.cn.modelo.TipoDocumento;
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
public class UsuarioControlador extends BaseControlador {   

    @Inject
    private UsuarioBean usuarioBean;
    
    @Inject
    private EquipoBean equipoBean;
    
    @Inject
    private TipoDocumentoBean tipoDocumentoBean;

    private Usuario usuario;    

    private List<Usuario> listaUsuario;
    
    private List<TipoDocumento> listaTipoDocumento;
    
    private Equipo equipo;
    
    private Equipo equipoSeleccionado;
    
    private List<Equipo> listaEquipo;
    
    private Usuario usuarioSeleccionado;
    
    private List<Usuario> listaUsuarioEquipo;
    
    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listaUsuario = usuarioBean.obtenerLista();
        listaTipoDocumento = tipoDocumentoBean.obtenerListasSistema((Object)true);
        setEquipo(new Equipo());
        setEquipoSeleccionado(new Equipo());
        setListaEquipo(equipoBean.obtenerLista());
        setListaUsuarioEquipo(new ArrayList<Usuario>());
    }
    
    public void cargarListaUsuarioSelecionados(){
        //Equipo equipoTmp = equipoBean.obtenerPorCodigo(getEquipoSeleccionado().getIdEquipo());
        //System.out.println("equipoTmp:+"+equipoTmp.getEquipo());
        HashMap parametros = new HashMap();
        parametros.put("equipo", getEquipoSeleccionado());
        listaUsuarioEquipo = usuarioBean.obtenerListaPorParametros(parametros);
        for (Usuario usuarioTemp : listaUsuarioEquipo) {
            System.out.println("usuarioTemp:"+usuarioTemp.getDocumentoYNombres());
        }
    }
    
    public List<SelectItem> generarSelectItemDeTipoDocumento() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (TipoDocumento tipoDocumentoTmo : getListaTipoDocumento()) {
            selectItemsBuilder.add(tipoDocumentoTmo, tipoDocumentoTmo.getTipoDocumento());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDeEquipo() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Equipo equipoTemp : equipoBean.obtenerLista()) {
            selectItemsBuilder.add(equipoTemp, equipoTemp.getEquipo());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDeUsuarios() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Usuario usuarioTemp : usuarioBean.obtenerLista()) {
            selectItemsBuilder.add(usuarioTemp, usuarioTemp.getDocumentoYNombres());
        }
        return selectItemsBuilder.buildList();
    }
    
    

    public List<Usuario> obtenerListaUsuario() {
        return usuarioBean.obtenerLista();
    }

    public void guardar() {
        try {
            
            usuarioBean.crear(getUsuario());
            listaUsuario = usuarioBean.obtenerLista();            
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
            setUsuario(new Usuario());
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }
    
    public void guardarEquipo() {
        try {
            
            equipoBean.crear(getEquipo());
            setListaEquipo(equipoBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setEquipo(new Equipo());
        }
    }
    
    public void actualizarEquipoUsuario(){
        try {
            System.out.println("ec.com.ata.cn.controlador.UsuarioControlador.actualizarEquipoUsuario()");
            getUsuarioSeleccionado().setEquipo(getEquipoSeleccionado());
            System.out.println("getEquipoSeleccionado:"+getEquipoSeleccionado().getEquipo());
            System.out.println("getUsuarioSeleccionado:"+getUsuarioSeleccionado().getDocumentoYNombres());
            usuarioBean.modificar(getUsuarioSeleccionado());
            cargarListaUsuarioSelecionados();
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

    /**
     * @return the listaTipoDocumento
     */
    public List<TipoDocumento> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    /**
     * @param listaTipoDocumento the listaTipoDocumento to set
     */
    public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    /**
     * @return the equipo
     */
    public Equipo getEquipo() {
        return equipo;
    }

    /**
     * @param equipo the equipo to set
     */
    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    /**
     * @return the listaEquipo
     */
    public List<Equipo> getListaEquipo() {
        return listaEquipo;
    }

    /**
     * @param listaEquipo the listaEquipo to set
     */
    public void setListaEquipo(List<Equipo> listaEquipo) {
        this.listaEquipo = listaEquipo;
    }

    /**
     * @return the equipoSeleccionado
     */
    public Equipo getEquipoSeleccionado() {
        return equipoSeleccionado;
    }

    /**
     * @param equipoSeleccionado the equipoSeleccionado to set
     */
    public void setEquipoSeleccionado(Equipo equipoSeleccionado) {
        this.equipoSeleccionado = equipoSeleccionado;
    }
    
     /**
     * @return the usuarioSeleccionado
     */
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    /**
     * @param usuarioSeleccionado the usuarioSeleccionado to set
     */
    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    /**
     * @return the listaUsuarioEquipo
     */
    public List<Usuario> getListaUsuarioEquipo() {
        return listaUsuarioEquipo;
    }

    /**
     * @param listaUsuarioEquipo the listaUsuarioEquipo to set
     */
    public void setListaUsuarioEquipo(List<Usuario> listaUsuarioEquipo) {
        this.listaUsuarioEquipo = listaUsuarioEquipo;
    }
}
