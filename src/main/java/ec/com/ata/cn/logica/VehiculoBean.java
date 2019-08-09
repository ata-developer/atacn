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

    public Vehiculo crear(Vehiculo vehiculoEntrada) throws Exception {
        Vehiculo vehiculoCreado = vehiculoDao.crear(vehiculoEntrada);
        if (null != vehiculoEntrada.getFilasDeAsientos()){
            List<Fila> filasDeAsientos = vehiculoEntrada.getFilasDeAsientos();
            for (Fila filaDeAsiento : filasDeAsientos) {
                filaDeAsiento.setVehiculo(vehiculoCreado);
                filaBean.crear(filaDeAsiento);
            }
        }
        return vehiculoCreado;
    }
    
    public Vehiculo crear (Vehiculo vehiculoEntrada, List<Imagen> listaImagenesVehiculo) throws Exception {
        Vehiculo vehiculoCreado = vehiculoDao.crear(vehiculoEntrada);
        if (null != vehiculoEntrada.getFilasDeAsientos()){
            List<Fila> filasDeAsientos = vehiculoEntrada.getFilasDeAsientos();
            for (Fila filaDeAsiento : filasDeAsientos) {
                filaDeAsiento.setVehiculo(vehiculoCreado);
                filaBean.crear(filaDeAsiento);
            }
        }
        if (null != listaImagenesVehiculo && !listaImagenesVehiculo.isEmpty()){
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
    
    public List<Vehiculo> obtenerModeloListaPorMarcaYPorModeloLike(MarcaVehiculo marcaVehiculo, String modelo) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("marca", marcaVehiculo);
        parametros.put("modeloLike", modelo);
        List<Vehiculo> listaVehiculos = vehiculoDao.obtenerListaPorParametros(parametros);
        for (Vehiculo vehiculo : listaVehiculos) {
            String modeloResultado = vehiculo.getModelo();
            String anioDesde = " - " + vehiculo.getAnioVehiculoDesde().toString();
            String anioHasta = vehiculo.getAnioVehiculoDesde() == null ? "" : " - " + vehiculo.getAnioVehiculoDesde().toString();
            List<Fila> filas = vehiculo.getFilasDeAsientos();
            for (Fila fila : filas) {
                
            }
            
        }
        return null;
    }
    
    
}
