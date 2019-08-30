/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.VehiculoImagenDao;
import ec.com.ata.cn.modelo.Imagen;
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
public class VehiculoImagenBean {

    @Inject
    private VehiculoImagenDao vehiculoImagenDao;

    public VehiculoImagen crear(VehiculoImagen filaImagenEntrada) throws Exception {
        return vehiculoImagenDao.crear(filaImagenEntrada);
    }

    public VehiculoImagen guardar(VehiculoImagen filaImagenEntrada) throws Exception {
        return vehiculoImagenDao.crear(filaImagenEntrada);
    }

    public List<VehiculoImagen> obtenerLista() {
        return vehiculoImagenDao.obtenerTodos();
    }

    public VehiculoImagen obtenerPorId(VehiculoImagenId filaImagenId) {
        return vehiculoImagenDao.obtenerPorCodigo(filaImagenId);
    }

    public VehiculoImagen modificar(VehiculoImagen filaImagenEntrada) throws Exception {
        return vehiculoImagenDao.modificar(filaImagenEntrada);
    }

    public List<Imagen> obtenerListaPorVehiculo(Vehiculo vehiculoEntrada) {
        List<Imagen> listaImagen = new ArrayList();;

        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("vehiculo", vehiculoEntrada);
        List<VehiculoImagen> listaVehiculoImagen = vehiculoImagenDao.obtenerListaPorParametros(parametros);
        for (VehiculoImagen vehiculoImagen : listaVehiculoImagen) {
            listaImagen.add(vehiculoImagen.getImagen());
        }
        return listaImagen;
    }

    public List<VehiculoImagen> obtenerListaVehiculoImagenPorVehiculo(Vehiculo vehiculoEntrada) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("vehiculo", vehiculoEntrada);
        return vehiculoImagenDao.obtenerListaPorParametros(parametros);

    }

    public void eliminarPorVehiculo(Vehiculo vehiculoEntrada) {
        List<VehiculoImagen> listaVehiculoImagen = obtenerListaVehiculoImagenPorVehiculo(vehiculoEntrada);
        for (VehiculoImagen vehiculoImagen : listaVehiculoImagen) {
            eliminar(vehiculoImagen);
        }
    }

    public void eliminar(VehiculoImagen vehiculoImagen) {
        vehiculoImagenDao.eliminar(vehiculoImagen.getVehiculoImagenId());
    }

}
