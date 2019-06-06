/**
 * Clase BaseControlador.java 26 de set. de 2017
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.controlador.util.BundleUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 *
 *
 * @author Rodrigo Vallejo (rxvc120809)
 *
 * Modificado por: Planificacion TI
 *
 */
public class BaseControlador implements Serializable {

    /**
     * Estos Ids deben estar declarados en el template: messages.xhtml
     */
    // ID DE LOS MENSAJES GLOBALES
    final static String ID_MENSAJE_INFORMACION_GLOBAL = "mensajeGeneral";
    final static String ID_MENSAJE_ADVERTENCIA_GLOBAL = "mensajeGeneral";
    final static String ID_MENSAJE_ERROR_GLOBAL = "mensajeGeneral";
    final static String ID_MENSAJE_FATAL_GLOBAL = "mensajeGeneral";

    // ID DE LOS MENSAJES PARA POPUP
    final static String ID_MENSAJE_EXITOSO_POPUP = "mensajeGeneral";
    final static String ID_MENSAJE_INFORMACION_POPUP = "mensajeGeneral";
    final static String ID_MENSAJE_ADVERTENCIA_POPUP = "mensajeGeneral";
    final static String ID_MENSAJE_ERROR_POPUP = "mensajeGeneral";

    // ID DE LOS MENSAJES GLOBALES
    private final static String ID_MENSAJE_EXITOSO_GLOBAL = "mensajeGeneral";

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private final static String STRING_VACIO = "";
    private final static String DOS_PUNTOS = ": ";

    @Inject
    private MensajeControlador mensajeControlador;

   

    /**
     * Este metodo retorna HttpServletRequest.
     *
     * @return HttpServletRequest
     */
    protected HttpServletRequest getHttpRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    /**
     * Este metodo retorn HttpServletResponse.
     *
     * @return HttpServletResponse
     */
    protected HttpServletResponse getHttpResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }

    /**
     * Este metodo retorna HttpSession.
     *
     * @return HttpSession
     */
    protected HttpSession getHttpSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     * Este metodo retorna el nombre del contexto.
     *
     * @return String
     */
    public static String getContextName() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public static String getSchema() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestScheme();
    }

    public static String getServerName() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
    }

    /**
     * Este metodo retorna ExternalContext.
     *
     * @return ExternalContext
     */
    protected ExternalContext getExternalContext() {
        return getContext().getExternalContext();
    }

    /**
     * Devuelve el servlet context.
     *
     * @return Servlet context.
     */
    protected ServletContext getServletContext() {
        return getHttpRequest().getSession().getServletContext();
    }

    /**
     * Guarda objetos en el contexto.
     *
     * @param key Clave.
     * @param objeto Objeto a guardar.
     */
    public void guardarCache(final String key, final Object objeto) {
        getServletContext().setAttribute(key, objeto);
    }

    /**
     * Este metodo redirecciona el url.
     *
     * @param url String
     * @throws IOException IOException
     */
    protected void redirect(final String url) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect(url);
    }

    /**
     * @return the context
     */
    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Obtiene un parametro del Url dado el nombre del parametro.
     *
     * @param name Nombre del parametro que se quiere recuperar
     * @return el valor del parametro
     */
    public String obtenerParametroURL(final String name) {
        return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
    }

    // METODOS PARA TRABAJAR CON EL ARCHIVO DE PROPIEDADES
    /**
     * @author naue230512
     * @param clave valor de la clave del mensaje desde messages.properties
     * @return
     */
    public static String obtenerMensaje(String clave) {
        FacesContext context = FacesContext.getCurrentInstance();
        return getBundle(context.getApplication().getMessageBundle(), clave, null, context.getViewRoot().getLocale());
    }

    /**
     * Método usado para recuperar mensajes desde el archivo de propiedades de
     * la aplicacion.
     *
     * @param key Es la clave por la que se recuperara el mensaje en el archivo
     * de propiedades
     * @param params Si el mensaje tiene parametros, este array contiene los
     * parámetros necesarios para completar el mensaje
     * @return El mensaje recuperado
     */
    public static String getBundle(final String key, final Object... params) {
        return BundleUtil.getBundle(key, params);
    }

    /**
     * Método usado para recuperar mensajes desde el archivo de propiedades de
     * la aplicacion.
     *
     * @param bundleName bundle Name
     * @param key Es la clave por la que se recuperara el mensaje en el archivo
     * de propiedades
     * @param params Si el mensaje tiene parametros, este array contiene los
     * parámetros necesarios para completar el mensaje
     * @param locale localidad
     * @return
     */
    public static String getBundle(final String bundleName, final String key, final Object[] params,
            final Locale locale) {
        return BundleUtil.getBundle(bundleName, key, params, locale);
    }

    // MENSAJE EXITOSO
    /**
     * @author naue230512
     *
     * @param idClient id cliente
     * @param summary resumen del mensaje exitoso
     * @param detail detalle del mensaje exitoso
     */
    protected void addSuccessMessage(final String idClient, final String summary, final String detail) {
        FacesMessage successMessage = new FacesMessage();
        successMessage.setSummary(summary);

        StringBuffer detalle = new StringBuffer(STRING_VACIO);
        if (! "".equals(detail)) {
            detalle.append(DOS_PUNTOS);
            detalle.append(detail);
        }
        successMessage.setDetail(detalle.toString());

        successMessage.setSeverity(FacesMessage.SEVERITY_FATAL);
        FacesContext.getCurrentInstance().addMessage(idClient, successMessage);
    }

    /**
     *
     * @author naue230512
     *
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addSuccessMessage(final String summary, final String detail) {

        this.addSuccessMessage(ID_MENSAJE_EXITOSO_GLOBAL, summary, detail);
    }

    /**
     *
     * @author naue230512
     *
     * @param summary resumen del mensaje
     */
    protected void addSuccessMessage(final String summary) {
        this.addSuccessMessage(summary, STRING_VACIO);
    }

    /**
     *
     * @author naue230512
     *
     * @param listSummary lista de resumenes de mensajes
     */
    protected void addSuccessMessage(final List<String> listSummary) {

        for (String summary : listSummary) {

            this.addSuccessMessage(summary);
        }
    }

    /**
     * @author naue230512
     * @param summary resumen del mensaje para popups
     * @param detail detalle del mensaje
     */
    protected void addSuccessPopupMessage(final String summary, final String detail) {
        FacesMessage successMessage = new FacesMessage();
        successMessage.setSummary(summary);

        StringBuffer detalle = new StringBuffer(STRING_VACIO);
        if (! "".equals(detail)) {
            detalle.append(DOS_PUNTOS);
            detalle.append(detail);
        }
        successMessage.setDetail(detalle.toString());

        successMessage.setSeverity(FacesMessage.SEVERITY_FATAL);
        FacesContext.getCurrentInstance().addMessage(this.ID_MENSAJE_EXITOSO_POPUP, successMessage);
    }

    /**
     * @author naue230512
     *
     * @param summary summary
     */
    protected void addSuccessPopupMessage(final String summary) {
        this.addSuccessPopupMessage(summary, STRING_VACIO);
    }

    // MENSAJE INFORMACION
    /**
     * @author naue230512
     *
     * @param idClient id del cleinte
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addInfoMessage(final String idClient, final String summary, final String detail) {
        FacesMessage infoMessage = new FacesMessage();
        infoMessage.setSummary(summary);

        StringBuilder detalle = new StringBuilder(STRING_VACIO);
        if (! "".equals(detail)) {
            detalle.append(DOS_PUNTOS);
            detalle.append(detail);
        }
        infoMessage.setDetail(detalle.toString());

        infoMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(idClient, infoMessage);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addInfoMessage(final String summary, final String detail) {
        this.addInfoMessage(ID_MENSAJE_INFORMACION_GLOBAL, summary, detail);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     *
     */
    protected void addInfoMessage(final String summary) {
        this.addInfoMessage(summary, STRING_VACIO);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addInfoPopupMessage(final String summary, final String detail) {
        FacesMessage infoMessage = new FacesMessage();
        infoMessage.setSummary(summary);

        StringBuffer detalle = new StringBuffer(STRING_VACIO);
        if (! "".equals(detail)) {
            detalle.append(DOS_PUNTOS);
            detalle.append(detail);
        }
        infoMessage.setDetail(detalle.toString());

        infoMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(this.ID_MENSAJE_INFORMACION_POPUP, infoMessage);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     */
    protected void addInfoPopupMessage(final String summary) {
        this.addInfoPopupMessage(summary, STRING_VACIO);
    }

    // MENSAJE ADVERTENCIA
    /**
     * @author naue230512
     *
     * @param idClient id del cliente
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addWarningMessage(final String idClient, final String summary, final String detail) {
        FacesMessage warningMessage = new FacesMessage();
        warningMessage.setSummary(summary);

        StringBuffer detalle = new StringBuffer(STRING_VACIO);
        if (! "".equals(detail)) {
            detalle.append(DOS_PUNTOS);
            detalle.append(detail);

        }
        warningMessage.setDetail(detalle.toString());

        warningMessage.setSeverity(FacesMessage.SEVERITY_WARN);
        FacesContext.getCurrentInstance().addMessage(idClient, warningMessage);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addWarningMessage(final String summary, final String detail) {
        this.addWarningMessage(this.ID_MENSAJE_ADVERTENCIA_GLOBAL, summary, detail);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     */
    protected void addWarningMessage(final String summary) {
        this.addWarningMessage(summary, STRING_VACIO);
    }

    /**
     * @author naue230512
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addWarningPopupMessage(final String summary, final String detail) {
        FacesMessage warningMessage = new FacesMessage();
        warningMessage.setSummary(summary);

        StringBuffer detalle = new StringBuffer(STRING_VACIO);
        if (! "".equals(detail)) {
            detalle.append(DOS_PUNTOS);
            detalle.append(detail);
        }
        warningMessage.setDetail(detalle.toString());

        warningMessage.setSeverity(FacesMessage.SEVERITY_WARN);
        FacesContext.getCurrentInstance().addMessage(this.ID_MENSAJE_ADVERTENCIA_POPUP, warningMessage);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     */
    protected void addWarningPopupMessage(final String summary) {
        this.addWarningPopupMessage(summary, STRING_VACIO);
    }

    // MENSAJE ERROR
    /**
     * @author naue230512
     *
     * @param idClient id del cliente
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addErrorMessage(final String idClient, final String summary, final String detail) {
        FacesMessage errorMessage = new FacesMessage();
        errorMessage.setSummary(summary);

        StringBuffer detalle = new StringBuffer(STRING_VACIO);
        if (! "".equals(detail)) {
            detalle.append(DOS_PUNTOS);
            detalle.append(detail);
        }
        errorMessage.setDetail(detalle.toString());

        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(idClient, errorMessage);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addErrorMessage(final String summary, final String detail) {
        this.addErrorMessage(BaseControlador.ID_MENSAJE_ERROR_GLOBAL, summary, detail);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     */
    protected void addErrorMessage(final String summary) {
        this.addErrorMessage(summary, STRING_VACIO);
    }

    /**
     *
     * @author naue230512
     *
     * @param listSummary lista de resumenes
     */
    protected void addErrorMessage(final List<String> listSummary) {

        for (String summary : listSummary) {
            this.addErrorMessage(summary);
        }
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addErrorPopupMessage(final String summary, final String detail) {
        FacesMessage errorMessage = new FacesMessage();
        errorMessage.setSummary(summary);

        StringBuffer detalle = new StringBuffer(STRING_VACIO);
        if (! "".equals(detail)) {
            detalle.append(DOS_PUNTOS);
            detalle.append(detail);
        }
        errorMessage.setDetail(detalle.toString());

        errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(this.ID_MENSAJE_ERROR_POPUP, errorMessage);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     */
    protected void addErrorPopupMessage(final String summary) {
        this.addErrorPopupMessage(summary, STRING_VACIO);
    }

    // MENSAJE FATAL
    /**
     * @author naue230512
     *
     * @param idClient id del cliente
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addFatalMessage(final String idClient, final String summary, final String detail) {
        FacesMessage fatalMessage = new FacesMessage();
        fatalMessage.setSummary(summary);

        StringBuffer detalle = new StringBuffer(STRING_VACIO);
        if (! "".equals(detail)) {
            detalle.append(DOS_PUNTOS);
            detalle.append(detail);
        }
        fatalMessage.setDetail(detalle.toString());

        fatalMessage.setSeverity(FacesMessage.SEVERITY_FATAL);
        FacesContext.getCurrentInstance().addMessage(idClient, fatalMessage);
    }

    /**
     * @author naue230512
     *
     * @param summary resumen del mensaje
     * @param detail detalle del mensaje
     */
    protected void addFatalMessage(final String summary, final String detail) {
        this.addFatalMessage(this.ID_MENSAJE_FATAL_GLOBAL, summary, detail);
    }

    /**
     * @author naue230512
     *
     * * @param summary resumen del mensaje
     */
    protected void addFatalMessage(final String summary) {
        this.addFatalMessage(summary, STRING_VACIO);
    }

    
    /**
     * *
     * Metodo para validar el rol de usuario conectado
     *
     * @param rol rol del usuario conectado
     * @return
     */
    public boolean validarRol(String rol) {
        return getHttpRequest().isUserInRole(rol);
    }

    /**
     *
     * @param <T> T
     * @param beanName nombre del bean o controlador
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName) {
        FacesContext context = FacesContext.getCurrentInstance();
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

    /**
     * Este metodo busca y retorna un componente segun el id.
     *
     * @param elementoId id del elemento dato tipo String
     * @return UIComponent componente ui
     */
    public UIComponent obtenerElemento(final String elementoId) {
        return FacesContext.getCurrentInstance().getViewRoot().findComponent(elementoId);
    }

    /**
     * Este méstodo limpia una lista y le asigna el valor por defecto en
     * selección.
     *
     * @param lista List
     * @return List lista de items
     */
    public final List<SelectItem> iniciarCombos(final List<SelectItem> lista) {
        if (lista != null) {
            lista.clear();
            lista.add(new SelectItem(null, getBundle("generico.combo.seleccione", null)));
        }
        return lista;
    }

    /**
     * Este méstodo limpia una lista y le asigna el valor por defecto en TODOS.
     *
     * @param lista Lista de items
     * @return List
     */
    public final List<SelectItem> iniciarCombosTodos(final List<SelectItem> lista) {
        if (lista != null) {
            lista.clear();
            lista.add(new SelectItem(null, getBundle("generico.combo.todos", null)));
        }
        return lista;
    }

   

    /**
     * Este metodo obtiene la descripcion de un select ithem.
     *
     * @param lista List
     * @param key Object
     * @return String
     */
    protected final String obtenerDescripcionCombo(final List<SelectItem> lista, final Object key) {
        String descripcion = "Ninguna";
        for (SelectItem s : lista) {
            if (s.getValue() != null && s.getValue().equals(key)) {
                descripcion = s.getLabel();
                break;
            }
        }
        return descripcion;
    }

    /**
     * Permite actualizar los componentes 
     * @param components lista de componentes que se va actualizar
     */
    //protected void actualizarCompomentes(final List<String> components) {
    //    RequestContext.getCurrentInstance().update(components);
    //}
    
    /**
     * Permite actualizar los componentes 
     * @param componente Nombre del componente
     */
    //protected void actualizarCompomentes(final String componente) {
    //    RequestContext.getCurrentInstance().update(componente);
    //}

}
