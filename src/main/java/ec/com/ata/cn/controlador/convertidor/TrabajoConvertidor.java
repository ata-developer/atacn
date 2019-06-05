/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador.convertidor;

import ec.com.ata.cn.logica.TrabajoBean;
import ec.com.ata.cn.modelo.Trabajo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ATA1
 */

@Named(value ="trabajoConvertidor")
public class TrabajoConvertidor implements Convertidor<Trabajo>{
     
    @Inject
    private TrabajoBean trabajoBean;

    /**
     * Default constructor
     */
    public TrabajoConvertidor () {
    }

    @Override
    public Trabajo getAsObject (final FacesContext context, final UIComponent component, final String submittedValue) {
        // Is the value null or empty?
        if ((null == submittedValue) || (submittedValue.trim().isEmpty())) {
            // Return null
            return null;
        }

        // Init instance
        Trabajo trabajo = null;

        try {
            // Try to parse the value as long
            final Long idTrabajo = Long.valueOf(submittedValue);

            // Try to get user instance from it
            trabajo = trabajoBean.obtenerPorId(idTrabajo);
        } catch (Exception ex) {
            // Throw again
            throw new ConverterException(ex);
        } 

        // Return it
        return trabajo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Trabajo trabajo) {
        if ((null == trabajo) || (String.valueOf(trabajo).isEmpty())) {
            // Is null
            return ""; //NOI18N
        }

        // Return id number
        return String.valueOf(trabajo.getIdTrabajo());
    }
}
