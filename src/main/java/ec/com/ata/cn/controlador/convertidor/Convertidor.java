/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador.convertidor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author ATA1
 */
public interface Convertidor<T> extends Converter {

    T getAsObject(FacesContext context, UIComponent component, String value);

    String getAsString(FacesContext context, UIComponent component, Object value);
}
