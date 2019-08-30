/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.VehiculoDao;
import ec.com.ata.cn.modelo.Fila;
import ec.com.ata.cn.modelo.Imagen;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoImagen;
import ec.com.ata.cn.modelo.VehiculoImagenId;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class VehiculoBean {

    @Inject
    private VehiculoDao vehiculoDao;

    @Inject
    private FilaBean filaBean;

    @Inject
    private VehiculoImagenBean vehiculoImagenBean;

    @Inject
    private ImagenBean imagenBean;

    public Vehiculo crear(Vehiculo vehiculoEntrada) throws Exception {
        Vehiculo vehiculoCreado = vehiculoDao.crear(vehiculoEntrada);
        if (null != vehiculoEntrada.getFilasDeAsientos()) {
            List<Fila> filasDeAsientos = vehiculoEntrada.getFilasDeAsientos();
            for (Fila filaDeAsiento : filasDeAsientos) {
                filaDeAsiento.setVehiculo(vehiculoCreado);
                filaBean.crear(filaDeAsiento);
            }
        }
        return vehiculoCreado;
    }

    public Vehiculo actualizar(Vehiculo vehiculoEntrada, List<Imagen> listaImagenesVehiculo) throws Exception {
        Vehiculo vehiculoCreado = vehiculoDao.modificar(vehiculoEntrada);
        /*filaBean.eliminarPorVehiculo(vehiculoEntrada);
        System.out.println("Elimnado filas");
        if (null != vehiculoEntrada.getFilasDeAsientos()) {
            List<Fila> filasDeAsientos = vehiculoEntrada.getFilasDeAsientos();
            for (Fila filaDeAsiento : filasDeAsientos) {
                filaDeAsiento.setVehiculo(vehiculoCreado);
                filaDeAsiento.setIdFila(null);
                filaBean.crear(filaDeAsiento);
                System.out.println("crear vehiculo imagen");
            }
        }*/
        //List<Imagen> listaImagen = vehiculoImagenBean.obtenerListaPorVehiculo(vehiculoEntrada);
        vehiculoImagenBean.eliminarPorVehiculo(vehiculoEntrada);
        System.out.println("Elimnado vehiculo imagen");
        //eliminarListaVehiculoImagen(listaImagen);
        if (null != listaImagenesVehiculo && !listaImagenesVehiculo.isEmpty()) {
            for (Imagen imagen : listaImagenesVehiculo) {
                VehiculoImagenId vehiculoImagenId = new VehiculoImagenId();
                vehiculoImagenId.setIdImagen(imagen.getIdImagen());
                vehiculoImagenId.setIdVehiculo(vehiculoEntrada.getIdVehiculo());                
                VehiculoImagen vehiculoImagenTmp = vehiculoImagenBean.obtenerPorId(vehiculoImagenId);
                System.out.println("getIdImagen: " + imagen.getIdImagen());
                System.out.println("getIdVehiculo: " + vehiculoEntrada.getIdVehiculo());
                System.out.println("vehiculoImagenTmp: " + vehiculoImagenTmp);
                if (null == vehiculoImagenTmp) {
                    VehiculoImagen vehiculoImagen = new VehiculoImagen();
                    vehiculoImagen.setVehiculo(vehiculoCreado);
                    vehiculoImagen.setImagen(imagen);

                    System.out.println("getIdImagen2: " + vehiculoImagen.getImagen().getIdImagen());
                    System.out.println("getIdVehiculo2: " + vehiculoImagen.getVehiculo().getIdVehiculo());
                    System.out.println("vehiculoImagenTmp2: " + vehiculoImagenTmp);

                    vehiculoImagenBean.crear(vehiculoImagen);
                }
                System.out.println("crear vehiculo imagen");
            }
        }
        return vehiculoCreado;
    }

    private void eliminarListaVehiculoImagen(List<Imagen> listaImagenesEntrada) {
        for (Imagen imagen : listaImagenesEntrada) {
            imagenBean.eliminar(imagen.getIdImagen());
        }
    }

    public Vehiculo crear(Vehiculo vehiculoEntrada, List<Imagen> listaImagenesVehiculo) throws Exception {
        Vehiculo vehiculoCreado = vehiculoDao.crear(vehiculoEntrada);
        if (null != vehiculoEntrada.getFilasDeAsientos()) {
            List<Fila> filasDeAsientos = vehiculoEntrada.getFilasDeAsientos();
            for (Fila filaDeAsiento : filasDeAsientos) {
                filaDeAsiento.setVehiculo(vehiculoCreado);
                filaBean.crear(filaDeAsiento);
            }
        }
        if (null != listaImagenesVehiculo && !listaImagenesVehiculo.isEmpty()) {
            for (Imagen imagen : listaImagenesVehiculo) {
                VehiculoImagen vehiculoImagen = new VehiculoImagen();
                vehiculoImagen.setVehiculo(vehiculoCreado);
                vehiculoImagen.setImagen(imagen);
                vehiculoImagenBean.crear(vehiculoImagen);
            }
        }
        return vehiculoCreado;
    }

    public List<Vehiculo> obtenerLista() {
        return vehiculoDao.obtenerTodos();
    }

    public List<Vehiculo> obtenerListaPorMarca(MarcaVehiculo marcaVehiculo) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("marca", marcaVehiculo);
        return vehiculoDao.obtenerListaPorParametros(parametros);
    }

    public List<String> obtenerModeloListaPorMarcaYPorModeloLike(MarcaVehiculo marcaVehiculo, String modelo) {
        List<String> listaDescripcion = new ArrayList<>();
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("marca", marcaVehiculo);
        parametros.put("modeloLike", modelo);
        List<Vehiculo> listaVehiculos = vehiculoDao.obtenerListaPorParametros(parametros);
        for (Vehiculo vehiculo : listaVehiculos) {
            String modeloResultado = vehiculo.getModelo();
            String anioDesde = vehiculo.getAnioDesde() == null ? "" : " - " + vehiculo.getAnioDesde().toString();
            String anioHasta = vehiculo.getAnioHasta() == null ? "" : " - " + vehiculo.getAnioHasta().toString();
            List<Fila> filas = vehiculo.getFilasDeAsientos();
            String descripcionFilas = "";
            for (Fila fila : filas) {
                descripcionFilas = descripcionFilas + " - " + fila.getTipoFila().getTipoFila() + "\n";
            }
            String resultado = modeloResultado + anioDesde + anioHasta + descripcionFilas;
            listaDescripcion.add(resultado);
        }
        return listaDescripcion;
    }

}
