/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador.convertidor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author ATA1
 */
public interface Convertidor<T> {

    T getAsObject(FacesContext context, UIComponent component, String value);

    String getAsString(FacesContext context, UIComponent component, T value);
}
