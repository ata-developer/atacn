/**
 * Clase BundleUtil.java 13 de junio. de 2017
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.com.ata.cn.controlador.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.jboss.logging.Logger;

/**
 *
 * @author Planificación TI
 * @version $Revision 1.0 $
 */
public final class BundleUtil {

	/**
	 * Nombre del archivo de propiedades de la aplicacion.
	 */
	public static final String BUNDLE_NAME = "messages";
	/**
	 * Logger de la clase.
	 */
	private static final Logger LOG = Logger.getLogger(BundleUtil.class);

	/**
	 * Constructor sin argumentos.
	 */
	private BundleUtil() {
		super();
	}

	/**
	 * Método usado para recuperar mensajes desde el archivo de propiedades de
	 * la aplicacion.
	 *
	 * @param key
	 *            Es la clave por la que se recuperara el mensaje en el archivo
	 *            de propiedades
	 * @param params
	 *            Si el mensaje tiene parametros, este array contiene los
	 *            parámetros necesarios para completar el mensaje
	 * @return El mensaje recuperado
	 */
	public static String getBundle(final String key, final Object... params) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

		ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale, getCurrentClassLoader(params));

		String mensaje = recuperarRecurso(bundle, key);

		if (mensaje == null) {
			mensaje = key;
		} else {
			if (params != null && params.length > 0) {
				MessageFormat mf = new MessageFormat(mensaje, locale);
				mensaje = mf.format(params, new StringBuffer(), null).toString();
			}

		}

		return mensaje;
	}

	/**
	 * Método usado para recuperar mensajes desde el archivo de propiedades de
	 * la aplicacion.
	 *
	 * @param bundleName
	 *            nombre de la propiedad bundle
	 * @param key
	 *            Es la clave por la que se recuperara el mensaje en el archivo
	 *            de propiedades
	 * @param params
	 *            Si el mensaje tiene parametros, este array contiene los
	 *            parámetros necesarios para completar el mensaje
	 * @return El mensaje recuperado
	 */
	public static String getBundle(String bundleName, String key, Object params[], Locale locale) {
		String text = null;
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));
		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = "?? key " + key + " not found ??";
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}

	/**
	 * Se encarga de recuperar una instancia del classloader del thread actual.
	 *
	 * @param defaultObject
	 *            objeto usado para recuperar el class loader
	 * @return Instancia del class loader
	 */
	public static ClassLoader getCurrentClassLoader(final Object defaultObject) {
		ClassLoader loader = null;
		try {
			loader = Thread.currentThread().getContextClassLoader();
			if (loader == null) {
				Method method = defaultObject.getClass().getMethod("getClassLoader", null);
				loader = (ClassLoader) method.invoke(defaultObject, null);
			}
		} catch (SecurityException e) {
			LOG.error(e);
		} catch (NoSuchMethodException e) {
			LOG.error(e);
		} catch (IllegalArgumentException e) {
			LOG.error(e);
		} catch (IllegalAccessException e) {
			LOG.error(e);
		} catch (InvocationTargetException e) {
			LOG.error(e);
		}
		return loader;

	}

	/**
	 * Se encarga de recuperar un recuros desde el repositorio de recursos
	 * pasado como parametro.
	 *
	 * @param bundle
	 *            repositorio de recursos
	 * @param key
	 *            key del recurso que se quiere recuperar
	 * @return el recurso recuperado, si no existe retorna null
	 */
	private static String recuperarRecurso(final ResourceBundle bundle, final String key) {
		String mensaje;
		try {
			mensaje = bundle.getString(key);
		} catch (MissingResourceException e) {
			LOG.warn(new StringBuffer("No existe el recurso ").append(key).append(" en el archivo ").append(BUNDLE_NAME)
					.append(".  Asegúrese de ingresarlo").toString());
			mensaje = generarValorRecursoDesconocido(key);
		}
		return mensaje;
	}

	/**
	 * Se encarga de generar una cadena que representa a un recurso no
	 * encontrado.
	 *
	 * @param keyRecursoBuscado
	 *            el key del mensaje buscado en el archivo de propiedades.
	 * @return una cadena con signos ??? al inicio y al final
	 */
	public static String generarValorRecursoDesconocido(final String keyRecursoBuscado) {
		return new StringBuffer("???").append(keyRecursoBuscado).append("??").toString();
	}
}
