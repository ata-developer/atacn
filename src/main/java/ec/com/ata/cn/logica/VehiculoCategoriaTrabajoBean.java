/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.VehiculoCategoriaTrabajoDao;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoCategoriaTrabajo;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class VehiculoCategoriaTrabajoBean {

    @Inject
    private VehiculoCategoriaTrabajoDao vehiculoCategoriaTrabajoDao;

    public void crearListaVehiculoCategoriaTrabajo(List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecioEntrada, Vehiculo vehiculoEntrada) throws Exception {
        for (TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp : listaTrabajoCategoriaPrecioEntrada) {
            if (trabajoCategoriaPrecioTmp.isSeleccionar()) {
                VehiculoCategoriaTrabajo vehiculoTrabajoCategoriaTrabajoTmp = new VehiculoCategoriaTrabajo();
                vehiculoTrabajoCategoriaTrabajoTmp.setVehiculo(vehiculoEntrada);
                vehiculoTrabajoCategoriaTrabajoTmp.setTrabajoCategoriaPrecio(trabajoCategoriaPrecioTmp);
                crear(vehiculoTrabajoCategoriaTrabajoTmp);
            }
        }
    }
    
    public List<VehiculoCategoriaTrabajo> obtenerListaPorVehiculo(Vehiculo vehiculo){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("vehiculo", vehiculo);
        return vehiculoCategoriaTrabajoDao.obtenerListaPorParametros(parametros);
    }

    public VehiculoCategoriaTrabajo crear(VehiculoCategoriaTrabajo vehiculoCategoriaTrabajoEntrada) throws Exception {
        return vehiculoCategoriaTrabajoDao.crear(vehiculoCategoriaTrabajoEntrada);
    }

    public List<VehiculoCategoriaTrabajo> obtenerLista() {
        return vehiculoCategoriaTrabajoDao.obtenerTodos();
    }

    public void eliminarVehiculoCategoriaTrabajo(VehiculoCategoriaTrabajo vehiculoCategoriaTrabajo) throws Exception {
        vehiculoCategoriaTrabajoDao.eliminar(vehiculoCategoriaTrabajo.getVehiculoCategoriaTrabajoId());
    }

    public VehiculoCategoriaTrabajo modificar(VehiculoCategoriaTrabajo vehiculoCategoriaTrabajoEntrada) throws Exception {
        return vehiculoCategoriaTrabajoDao.modificar(vehiculoCategoriaTrabajoEntrada);
    }
}
