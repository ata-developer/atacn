/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador.convertidor;

import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.modelo.Vehiculo;
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
public class VehiculoConvertidor implements Converter{
     
    @Inject
    private VehiculoBean vehiculoBean;

    /**
     * Default constructor
     */
    public VehiculoConvertidor () {
    }

    @Override
    public Vehiculo getAsObject (final FacesContext context, final UIComponent component, final String submittedValue) {
        // Is the value null or empty?
        System.out.println("vehiculoBean: "+vehiculoBean);
        System.out.println("submittedValue: "+submittedValue);
        if ((null == submittedValue) || (submittedValue.trim().isEmpty())) {
            // Return null
            return null;
        }

        // Init instance
        Vehiculo vehiculo = null;

        try {
            // Try to parse the value as long
            
            final Long idVehiculo = Long.valueOf(submittedValue);

            // Try to get user instance from it
            vehiculo = vehiculoBean.obtenerPorCodigo(idVehiculo);
        } catch (Exception ex) {
            // Throw again
            ex.printStackTrace();
            throw new ConverterException(ex);
        } 

        // Return it
        return vehiculo;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object vehiculo) {
        System.out.println("vehiculo: "+vehiculo);
        if ((null == vehiculo) || (null == ((Vehiculo)vehiculo).getIdVehiculo())|| (String.valueOf(vehiculo).isEmpty())) {
            // Is null
            return ""; //NOI18N
        }

        // Return id number
        Vehiculo vehiculoTmp =  (Vehiculo) vehiculo;
        return vehiculoTmp.getIdVehiculo().toString();
    }
}
