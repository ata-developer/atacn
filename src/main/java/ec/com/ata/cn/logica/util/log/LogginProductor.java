/**
 * Clase ProductorRecursoLog.java 13 de abr. de 2017
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.gob.sri.adm.logica.util.log;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

/**
 * Productor de recursos para su obtención por CDI del Log
 * 
 * @author djlu030215
 *
 */
public class LogginProductor {

	/**
	 * Produce objeto de tipo log
	 * 
	 * @param puntoInyeccion
	 *            punto de inyección
	 * @return objeto de tipo log
	 */
	@Produces
	public Logger getLogger(InjectionPoint puntoInyeccion) {
		String categoria = puntoInyeccion.getMember().getDeclaringClass().getName();
		return Logger.getLogger(categoria);
	}
}