/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador.convertidor;

import ec.com.ata.cn.logica.TipoFilaBean;
import ec.com.ata.cn.modelo.TipoFila;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@FacesConverter(value = "tipoFilaConvertidor")
public class TipoFilaConvertidor implements Convertidor<TipoFila>{
     
    @Inject
    private TipoFilaBean tipoFilaBean;

    /**
     * Default constructor
     */
    public TipoFilaConvertidor () {
    }

    @Override
    public TipoFila getAsObject (final FacesContext context, final UIComponent component, final String submittedValue) {
        // Is the value null or empty?
        if ((null == submittedValue) || (submittedValue.trim().isEmpty())) {
            // Return null
            return null;
        }

        // Init instance
        TipoFila tipoFila = null;

        try {
            // Try to parse the value as long
            final Long idTipoFila = Long.valueOf(submittedValue);

            // Try to get user instance from it
            tipoFila = tipoFilaBean.obtenerPorCodigo(idTipoFila);
        } catch (Exception ex) {
            // Throw again
            throw new ConverterException(ex);
        } 

        // Return it
        return tipoFila;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object tipoFila) {
        if ((null == tipoFila) || (String.valueOf(tipoFila).isEmpty())) {
            // Is null
            return ""; //NOI18N
        }

        // Return id number
        return String.valueOf( ((TipoFila) tipoFila).getIdTipoFila());
    }
}
