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
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ATA1
 */
@Named
@RequestScoped
public class VehiculoConvertidor implements Convertidor<Vehiculo> {

    @Inject
    private VehiculoBean vehiculoBean;

    @Override
    public Vehiculo getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("getAsObject----------");
        System.out.println("value:---------------------" +value);
        if (value != null && value.trim().length() > 0) {
            return vehiculoBean.obtenerPorCodigo(new Long(value));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("getAsString------------");
        if (value != null) {
            Vehiculo vehiculoTmp = (Vehiculo) value;
            return String.valueOf(vehiculoTmp.getIdVehiculo());
        } else {
            return null;
        }
    }
}
