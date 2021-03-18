/**
 * Clase Constante.java 17 mayo. 2018
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.com.ata.cn.logica.util.gestor;

/**
 * Clase donde se ubican las constantes a usarse en el proyecto ADM
 *
 * @author aepb191017
 *
 */
public class Constante {

    public static final String ACTIVOTEXTO = "ACTIVO";
    public static final String INACTIVOTEXTO = "INACTIVO";
    public static final String ACTIVO = "A";
    public static final String INACTIVO = "I";
    public static final String ELIMINADO = "E";
    public static final String ELIMINADOBASE = "N";
    public static final String SI = "SI";
    public static final String NO = "No";
    public static final String ESTADO = "estado";
    public static final String GENERICO_ENTIDAD = "genericoEntidad";
    public static final String ELIMINADOGENERICOENTIDAD = "eliminado";
    public static final String NOMBRE = "nombre";
    public static final Long NIVELGEOGRAFICOUNO = 1L;
    public static final Long NIVELESTRUCTURAORGANIZACIONALUNO = 1L;
    public static final Long NIVELACTIVIDADECONOMICAUNO = 1L;
    public static final Long NIVELGRUPOIMPUESTOUNO = 1L;
    public static final Long NIVELTIPOSUJETOUNO = 1L;
    public static final Long NIVELGRUPOIMPUESTOSUBCLASE = 3L;
    public static final String UBICACION_GEOGRAFICA = "ubicacionGeografica";
    public static final String ACTIVIDAD_ECONOMICA_PADRE = "actividadEconomicaPadre";
    public static final String GRUPO_IMPUESTO_PADRE = "grupoImpuestoPadre";
    public static final String TIPO_SUJETO_PADRE = "tipoSujetoPadre";
    public static final String ARBOLTIPOSUJETO = "ARBOLTIPOSUJETO";
    public static final String FECHA_MINIMA_NO = "no";
    public static final String FECHA_MINIMA_DIAFERIADO = "DiaFeriado";

    public static final int ITERATIONS = 65536;
    public static final int KEY_LENGTH = 512;
    public static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    public static final String FECHA_INICIAL = "FECHA_INICIAL";
    public static final String FECHA_FIN = "FECHA_FIN";

    public static final String CODIGO_APLICACION = "ADC";
    public static final String URL_PAGINA_INICIO_INTRANET = "URL_HOME_INTRANET";
    public static final int NUM_CARACTERES_FILTRAR = 3;
    public static final String URL_HOME = "/pages/inicio.jsf";
    public static final String URL_HOME_APLICACIONES = "/lista-aplicaciones.jsf";
    public static final String MENU_SELECCIONADO = "MEN-SELEC";
    public static final int NUM_DECIMALES = 3;
    public static final String ESTILOS_ERROR = " ui-state-error";
    public static final String VALIDACION_FORMULARIO_GETID = "document.getElementById('";
    public static final String VALIDACION_FORMULARIO_CLASS = "').className +=' ";
    public static final String ATRIBUTO_VALOR = "value";
    public static final String VALIDACION_FORMULARIO_NMB_FECHA_FIN = "nombreCampoFechaFin";
    public static final String VALIDACION_FORMULARIO_NMB_FECHA_INI = "nombreCampoFechaInicial";
    public static final String VALIDACION_FORMULARIO_MSG_FECHA_VALOR = "El valor del campo '";
    public static final String VALIDACION_FORMULARIO_MSG_VAL_FECHAINI = "valorFechaInicial";
    public static final String VALIDACION_FORMULARIO_MSG_VAL_FECHAFIN = "valorFechaFinal";

    public static final String EXITO = "Exitoso";
    public static final String EXITO_YA_EXISTE = "Ya existe auto seleccionado";
    public static final String EXITO_DETALLE = "Se ha procesado su requerimiento exitosamente";
    public static final String EXITO_ESTABLECIMIENTO = "Se ha seleccionado el establecimiento";
    public static final String NO_EXISTE_REGISTRO = "No existe registro";
    public static final String ERROR = "Error";
    public static final String ERROR_TRABAJO_CONTROLADOR_GUARDAR = " 001 ";
    public static final String ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO = " 002 ";
    public static final String ERROR_CATEGORIA_CONTROLADOR_GUARDAR = " 003 ";
    public static final String ERROR_PRECIOS_CONTROLADOR_GUARDAR_EX = " 004 ";
    public static final String ERROR_PRECIOS_CONTROLADOR_GUARDAR_ROOT = " 005 ";
    public static final String ERROR_MATERIAL = " 006 ";
    public static final String ERROR_COLOR = " 007 ";
    public static final String ERROR_LISTA_TRABAJO = " 008 ";
    public static final String ERROR_LISTA_TRABAJO_MENSAJE = "Uno de los vehiculos no tiene trabajos asignados";
    public static final String ERROR_LISTA_VEHICULOS = " 008 ";
    public static final String ERROR_LISTA_VEHICULOS_MENSAJE = "Seleccione uno o mas vehiculos";
    public static final String ERROR_LISTA_ORDEN_FECHA = " 009 ";
    public static final String ERROR_LISTA_ORDEN_FECHA_MENSAJE = "Agregue una fecha";

    public static final String TRABAJO_CATEGORIA = "TRABAJO/CATEGORIA";

    public static final String SALT = "Br6Jew==";

    public static final String ANIO_INICIO_RANGO = "ANIO_INICIO_RANGO";
    public static final String ANIO_FIN_RANGO = "ANIO_FIN_RANGO";
    public static final String SIN_IMAGENES = "INGRESAR IMAGENES DEL VEHICULO";
    public static final String SIN_PARTES = "INGRESAR IMAGENES PARTE DEL VEHICULO";
    

    public static final String SUPERIOR = "SUPERIOR";
    public static final String RANGO = "RANGO";

    public static final String MENSAJE_SELECCIONADO = "NO PUEDE SELECCIONAR ESTE HORARIO, YA ESTA OCUPADO";
    public static final String MENSAJE_SELECCION_PARQUEADERO = "TIENE QUE SELECCIONAR UN PARQUEADERO";
    public static final String MENSAJE_SELECCION_EQUIPO = "TIENE QUE SELECCIONAR UN EQUIPO";
    public static final String MENSAJE_SELECCION_FECHA_INICIO = "TIENE QUE SELECCIONAR UNA FECHA INICIO";
    public static final String MENSAJE_SELECCION_FECHA_FIN = "TIENE QUE SELECCIONAR UNA FECHA FIN";

    public static final String CODIGO_ASIENTO = "ASI1000";
    public static final String CODIGO_PISO = "PIS1000";
    public static final String CODIGO_VOLANTE = "VOL1000";
    public static final String CODIGO_TECHO = "TEC1000";
    public static final String CODIGO_PUERTA = "PUE1000";
    public static final String CODIGO_POMO = "POM1000";
    public static final String CODIGO_CAPUCHON = "CAP1000";
    public static final String CODIGO_FRENO = "FRE1000";
    public static final String CODIGO_CAJUELA = "CAJ1000";    
    public static final String CODIGO_MOQUETA = "MOQ1000";
    
    public static final String CODIGO_CENTRO = "ASI1001";
    public static final String CODIGO_COSTADOS = "ASI1002";
    public static final String CODIGO_CENTRO_INFERIOR = "ASI1003";
    public static final String CODIGO_CENTRO_SUPERIOR = "ASI1004";
    public static final String CODIGO_PESTANAS = "ASI1005";
    public static final String CODIGO_LATERAL = "ASI1006";
    public static final String CODIGO_SOBRE = "ASI1007";
    public static final String CODIGO_DOBLE = "ASI1008";
    public static final String CODIGO_BOLSILLOS = "ASI1009";
    public static final String CODIGO_AIRBAG = "ASI10010";
    public static final String CODIGO_BRAZO_POSTERIOR = "ASI1011";
    
    public static final String CODIGO_FILA_1 = "ASI1100";
    public static final String CODIGO_MODELO_VOLANTE = "VOL1001";
    public static final String CODIGO_HILO_SUPERIOR = "VOL1002";
    public static final String CODIGO_HILO_CIERRA = "VOL1003";
    public static final String CODIGO_HILO_INFERIOR = "VOL1004";
    public static final String CODIGO_CUERPO_VOLANTE = "VOL1005";
    public static final String CODIGO_CUERPO_PISO = "PIS1001";
    public static final String CODIGO_CUERPO_TECHO = "TEC1001";
    public static final String CODIGO_CUERPO_PUERTA = "PUE1001";
    public static final String CODIGO_CUERPO_CAJUELA = "CAJ1001";
    public static final String CODIGO_CUERPO_POMO = "POM1001";
    public static final String CODIGO_CUERPO_CAPUCHON = "CAP1001";
    public static final String CODIGO_CUERPO_MOQUETA = "MOQ1001";
    public static final String CODIGO_CUERPO_FRENO = "FRE1001";
    public static final String CODIGO_CUERPO_OTRO = "OTR1001";
    
    
    
    
    
    
    
                    
    
}
