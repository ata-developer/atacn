/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador.convertidor;

import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.modelo.Usuario;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ATA1
 */
@Named
@RequestScoped
public class UsuarioConvertidor implements Converter{
     
    @Inject
    private UsuarioBean usuarioBean;

    /**
     * Default constructor
     */
    public UsuarioConvertidor () {
    }

    @Override
    public Usuario getAsObject (final FacesContext context, final UIComponent component, final String submittedValue) {
        // Is the value null or empty?
        System.out.println("usuarioBean: "+usuarioBean);
        System.out.println("submittedValue: "+submittedValue);
        if ((null == submittedValue) || (submittedValue.trim().isEmpty())) {
            // Return null
            return null;
        }

        // Init instance
        Usuario usuario = null;

        try {
            // Try to parse the value as long
            
            final Long idUsuario = Long.valueOf(submittedValue);

            // Try to get user instance from it
            usuario = usuarioBean.obtenerPorCodigo(idUsuario);
        } catch (Exception ex) {
            // Throw again
            ex.printStackTrace();
            throw new ConverterException(ex);
        } 

        // Return it
        return usuario;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object usuario) {
        System.out.println("usuario: "+usuario);
        if ((null == usuario) || (null == ((Usuario)usuario).getIdUsuario())|| (String.valueOf(usuario).isEmpty())) {
            // Is null
            return ""; //NOI18N
        }

        // Return id number
        Usuario usuarioTmp =  (Usuario) usuario;
        return usuarioTmp.getIdUsuario().toString();
    }
}
