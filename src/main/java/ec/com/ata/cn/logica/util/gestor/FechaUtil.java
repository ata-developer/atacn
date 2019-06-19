/**
 * Clase FechaUtil.java 15 de mar. de 2019
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.com.ata.cn.logica.util.gestor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author roth210418
 */
public final class FechaUtil {
    
    private FechaUtil() {
    }
    
    public static String darFormatoFecha(String formato, Date fecha) {
        SimpleDateFormat format = new SimpleDateFormat(formato);
        return (fecha == null ? null : format.format(fecha));
    }
}
