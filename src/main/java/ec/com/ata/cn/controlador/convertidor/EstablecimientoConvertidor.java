/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador.convertidor;

import ec.com.ata.cn.logica.EstablecimientoBean;
import ec.com.ata.cn.modelo.Establecimiento;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@FacesConverter(value = "establecimientoConvertidor")
public class EstablecimientoConvertidor implements Convertidor<Establecimiento>{
     
    @Inject
    private EstablecimientoBean establecimientoBean;

    /**
     * Default constructor
     */
    public EstablecimientoConvertidor () {
    }

    @Override
    public Establecimiento getAsObject (final FacesContext context, final UIComponent component, final String submittedValue) {
        // Is the value null or empty?
        if ((null == submittedValue) || (submittedValue.trim().isEmpty())) {
            // Return null
            return null;
        }

        // Init instance
        Establecimiento establecimiento = null;

        try {
            // Try to parse the value as long
            final Long idEstablecimiento = Long.valueOf(submittedValue);

            // Try to get user instance from it
            establecimiento = establecimientoBean.obtenerPorCodigo(idEstablecimiento);
        } catch (Exception ex) {
            // Throw again
            throw new ConverterException(ex);
        } 

        // Return it
        return establecimiento;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object establecimiento) {
        if ((null == establecimiento) || (String.valueOf(establecimiento).isEmpty())) {
            // Is null
            return ""; //NOI18N
        }

        // Return id number
        return String.valueOf( ((Establecimiento) establecimiento).getIdEstablecimiento());
    }
}
