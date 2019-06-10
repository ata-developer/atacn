/**
 * Clase BaseExcepcion.java 13 de abr. de 2017
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.com.ata.cn.logica.excepcion;

import javax.ejb.ApplicationException;



/**
 * BaseExcepcion es una subclase de RuntimeExcepticon y puede ser usada como
 * excepción base
 * 
 * @author djlu030215
 *
 */
@ApplicationException(rollback = true, inherited = true)
public class BaseExcepcion extends RuntimeException {

	private static final long serialVersionUID = -8170481043533937658L;

	/*
	 * Varios Consturctores
	 */

	public BaseExcepcion() {
		super();
	}

	public BaseExcepcion(String msg, Object... params) {
		super(String.format(msg, params));
	}

	public BaseExcepcion(Exception e, String msg, Object... params) {
		super(String.format(msg, params), e);
	}

	public BaseExcepcion(Exception e) {
		super(e.getMessage(), e);
	}

	public BaseExcepcion(String msg, String argument) {
		super(String.format(msg, argument));
	}
}