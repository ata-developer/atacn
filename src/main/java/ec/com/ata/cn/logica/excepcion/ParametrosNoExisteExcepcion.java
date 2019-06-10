/**
 * Clase EntidadNoExisteExcepcion.java Nov 17, 2017
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.com.ata.cn.logica.excepcion;

/**
 * @author edja220817
 *
 */
public class ParametrosNoExisteExcepcion extends BaseExcepcion {

	private static final long serialVersionUID = -654381666070708700L;

	public ParametrosNoExisteExcepcion() {
		super();
	}

	public ParametrosNoExisteExcepcion(String msg, Object... params) {
		super((null == msg) ? "" : String.format(msg, params));
	}

	public ParametrosNoExisteExcepcion(Exception e, String msg, Object... params) {
		super((null == msg) ? "" : String.format(msg, params), e);
	}

	public ParametrosNoExisteExcepcion(Exception e) {
		super(e.getMessage(), e);
	}

	public ParametrosNoExisteExcepcion(String msg, String argument) {
		super(String.format(msg, argument));
	}

}
