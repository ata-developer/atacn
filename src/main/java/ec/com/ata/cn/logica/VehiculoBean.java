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
import ec.com.ata.cn.modelo.Plantilla;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoImagen;
import ec.com.ata.cn.modelo.VehiculoImagenId;
import ec.com.ata.cn.modelo.VehiculoParte;
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
    
    @Inject
    private PlantillaBean plantillaBean;
    
    @Inject
    private VehiculoParteBean vehiculoParteBean;

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
    
    

    public Vehiculo actualizar(Vehiculo vehiculoEntrada) throws Exception {
        Vehiculo vehiculoCreado = vehiculoDao.modificar(vehiculoEntrada);
        return vehiculoCreado;
    }

    private void eliminarListaVehiculoImagen(List<Imagen> listaImagenesEntrada) {
        for (Imagen imagen : listaImagenesEntrada) {
            imagenBean.eliminar(imagen.getIdImagen());
        }
    }
    
    public Vehiculo crear(
            Vehiculo vehiculoEntrada,
            List<Imagen> listaImagenesVehiculo,
            List<Plantilla> listaPlantillaEntrada,
            List<VehiculoParte> listaVehiculoParteEntrada) throws Exception {
        
        Vehiculo vehiculoCreado = vehiculoDao.crear(vehiculoEntrada);
                
        if (null != listaImagenesVehiculo && !listaImagenesVehiculo.isEmpty()) {
            for (Imagen imagen : listaImagenesVehiculo) {
                VehiculoImagen vehiculoImagen = new VehiculoImagen();
                vehiculoImagen.setVehiculo(vehiculoCreado);
                vehiculoImagen.setImagen(imagen);
                vehiculoImagenBean.crear(vehiculoImagen);
            }
        }
        
        if (null != listaPlantillaEntrada && !listaPlantillaEntrada.isEmpty()) {
            for (Plantilla plantilla : listaPlantillaEntrada) {
                plantilla.setVehiculo(vehiculoCreado);
                plantillaBean.crear(plantilla);
            }
        }
        
        if (null != listaVehiculoParteEntrada && !listaVehiculoParteEntrada.isEmpty()) {
            for (VehiculoParte vehiculoParte : listaVehiculoParteEntrada) {
                vehiculoParte.setVehiculo(vehiculoCreado);
                vehiculoParteBean.crear(vehiculoParte);
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
    
    public List<String> obtenerModeloListaPorModeloLike(String modelo) {
        List<String> listaDescripcion = new ArrayList<>();
        HashMap<String, Object> parametros = new HashMap<>();
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

    public Vehiculo obtenerPorCodigo(Long idVehiculo){
        return vehiculoDao.obtenerPorCodigo(idVehiculo);
    }
}
