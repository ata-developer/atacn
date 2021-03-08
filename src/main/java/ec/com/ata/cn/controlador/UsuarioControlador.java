/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;


import ec.com.ata.cn.logica.EquipoBean;
import ec.com.ata.cn.logica.RolBean;
import ec.com.ata.cn.logica.RolUrlBean;
import ec.com.ata.cn.logica.TipoDocumentoBean;
import ec.com.ata.cn.logica.UrlBean;
import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.logica.UsuarioRolBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Equipo;
import ec.com.ata.cn.modelo.Rol;
import ec.com.ata.cn.modelo.RolUrl;
import ec.com.ata.cn.modelo.TipoDocumento;
import ec.com.ata.cn.modelo.Url;
import ec.com.ata.cn.modelo.Usuario;
import ec.com.ata.cn.modelo.UsuarioRol;
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
    
    @Inject
    private RolBean rolBean;
    
    @Inject
    private UsuarioRolBean usuarioRolBean;
    
    @Inject
    private UrlBean urlBean;
    
    @Inject
    private RolUrlBean rolUrlBean;

    private Usuario usuario;
    
    private Rol rol;
    
    private UsuarioRol usuarioRol;
    
    private Url url;

    private List<Usuario> listaUsuario;
    
    private List<TipoDocumento> listaTipoDocumento;
    
    private Equipo equipo;
    
    private Equipo equipoSeleccionado;
    
    private List<Equipo> listaEquipo;
    
    private Usuario usuarioSeleccionado;
    
    private List<Usuario> listaUsuarioEquipo;
    
    private List<Rol> listaRol;
    
    private List<UsuarioRol> listaUsuarioRol;
    
    private List<Url> listaUrl;
    
    private List<RolUrl> listaRolUrl;
    
    private Boolean esUrlPadre;
    
    private Url urlPrincipal;
    
    private Boolean sePresenta;
    
    @PostConstruct
    public void init() {
        usuario = new Usuario();
        listaUsuario = usuarioBean.obtenerLista();
        listaTipoDocumento = tipoDocumentoBean.obtenerListasSistema((Object)true);
        setEquipo(new Equipo());
        setEquipoSeleccionado(new Equipo());
        setListaEquipo(equipoBean.obtenerLista());
        setListaUsuarioEquipo(new ArrayList<Usuario>());
        setRol(new Rol());
        setListaRol(rolBean.obtenerLista());
        setListaUsuarioRol(usuarioRolBean.obtenerLista());
        setUsuarioRol(new UsuarioRol());
        setListaUrl(urlBean.obtenerLista());
        setEsUrlPadre(false);
        setUrlPrincipal(new Url());
        setUrl(new Url());
        getUrl().setEsMenu(true);
        setSePresenta(null);
        setListaRolUrl(new ArrayList<RolUrl>());
        
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
    
    public void guardarRol() {
        try {
            
            rolBean.crear(getRol());
            setListaRol(rolBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setRol(new Rol());
        }
    }
    
    public void guardarUrl() {
        try {
            
            urlBean.crear(getUrl());
            setListaUrl(urlBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setUrl(new Url());
        }
    }
    
    public void guardarUsuarioRol() {
        try {
            
            UsuarioRol usuarioRolTmp = new UsuarioRol();
            usuarioRolTmp.setRol(rol);
            usuarioRolTmp.setUsuario(usuario);
            usuarioRolBean.crear(usuarioRolTmp);
            setListaUsuarioRol(usuarioRolBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setRol(new Rol());
        }
    }
    
    
    
    public void guardarRolUrl() {
        try {
            RolUrl rolUrlTmp = new RolUrl();
            rolUrlTmp.setRol(rol);
            rolUrlTmp.setUrl(url);
            rolUrlBean.crear(rolUrlTmp);
            setListaRolUrl(rolUrlBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            setRol(new Rol());
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
    
    /**
     * @return the rol
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    
    
    /**
     * @return the usuarioRol
     */
    public UsuarioRol getUsuarioRol() {
        return usuarioRol;
    }

    /**
     * @param usuarioRol the usuarioRol to set
     */
    public void setUsuarioRol(UsuarioRol usuarioRol) {
        this.usuarioRol = usuarioRol;
    }

    /**
     * @return the listaRol
     */
    public List<Rol> getListaRol() {
        return listaRol;
    }

    /**
     * @param listaRol the listaRol to set
     */
    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }
    
    /**
     * @return the listaUsuarioRol
     */
    public List<UsuarioRol> getListaUsuarioRol() {
        return listaUsuarioRol;
    }

    /**
     * @param listaUsuarioRol the listaUsuarioRol to set
     */
    public void setListaUsuarioRol(List<UsuarioRol> listaUsuarioRol) {
        this.listaUsuarioRol = listaUsuarioRol;
    }

    
    /**
     * @return the listaUrl
     */
    public List<Url> getListaUrl() {
        return listaUrl;
    }

    /**
     * @param listaUrl the listaUrl to set
     */
    public void setListaUrl(List<Url> listaUrl) {
        this.listaUrl = listaUrl;
    }

    
    /**
     * @return the url
     */
    public Url getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(Url url) {
        this.url = url;
    }

    /**
     * @return the urlPrincipal
     */
    public Url getUrlPrincipal() {
        return urlPrincipal;
    }

    /**
     * @param urlPrincipal the urlPrincipal to set
     */
    public void setUrlPrincipal(Url urlPrincipal) {
        this.urlPrincipal = urlPrincipal;
    }

    /**
     * @return the esUrlPadre
     */
    public Boolean getEsUrlPadre() {
        return esUrlPadre;
    }

    /**
     * @param esUrlPadre the esUrlPadre to set
     */
    public void setEsUrlPadre(Boolean esUrlPadre) {
        this.esUrlPadre = esUrlPadre;
    }

    /**
     * @return the listaRolUrl
     */
    public List<RolUrl> getListaRolUrl() {
        return listaRolUrl;
    }

    /**
     * @param listaRolUrl the listaRolUrl to set
     */
    public void setListaRolUrl(List<RolUrl> listaRolUrl) {
        this.listaRolUrl = listaRolUrl;
    }

    /**
     * @return the sePresenta
     */
    public Boolean getSePresenta() {
        return sePresenta;
    }

    /**
     * @param sePresenta the sePresenta to set
     */
    public void setSePresenta(Boolean sePresenta) {
        this.sePresenta = sePresenta;
    }

    

}
