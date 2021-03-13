/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.modelo.Usuario;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xnio
 */
@Named
@SessionScoped
public class LoginBean extends BaseControlador {
    
    @Inject
    private UsuarioBean usuarioBean;

    //private static Logger log = Logger.getLogger(LoginBean.class.getName());
    private String correo;
    private String contrasena;

    private Usuario usuario;

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            System.out.println("correo: " + this.correo);
            System.out.println("contrasena: " + this.contrasena);
            request.login(this.correo, this.contrasena);
            //Principal principal = request.getUserPrincipal();
            //HashMap<String, Object> parametros = new HashMap<>();
            //parametros.put("correo", this.correo);
            //List<Usuario> listaUsuario = usuarioBean.obtenerListaPorParametros(parametros);
            //this.usuario = listaUsuario.get(0);
            
            //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            //Map<String, Object> sessionMap = externalContext.getSessionMap();
            //sessionMap.put("usuario", usuario);

        } catch (Exception e) {
            e.printStackTrace();
            context.addMessage(null, new FacesMessage("Login failed."));
            return "error";
        }
        return "/actividades/index.xhtml";
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            this.usuario = null;
            request.logout();
            // clear the session
            ((HttpSession) context.getExternalContext().getSession(false)).invalidate();
        } catch (ServletException e) {
            //log.log(Level.SEVERE, "Failed to logout user!", e);
        }
        return "/login.xhtml?faces-redirect=true";
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
