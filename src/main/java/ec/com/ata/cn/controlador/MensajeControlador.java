/**
 * Clase MensajeControlador.java 26 de set. de 2017
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.com.ata.cn.controlador;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;


import ec.com.ata.cn.controlador.util.BundleUtil;

/**
 *
 * @author roth210418
 */
@ViewScoped
@Named(value = "mensajeBean")
public class MensajeControlador implements Serializable {

   private static final long serialVersionUID = 1L;

   private String mensajeGlobalExito = BundleUtil.getBundle("generico.proceso.exito");

   public String getMensajeGlobalExito() {
      return mensajeGlobalExito;
   }

   public void setMensajeGlobalExito(String mensajeGlobalExito) {
      this.mensajeGlobalExito = mensajeGlobalExito;
   }

}
