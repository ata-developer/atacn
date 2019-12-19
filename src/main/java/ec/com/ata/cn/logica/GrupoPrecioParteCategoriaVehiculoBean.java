/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.GrupoPrecioParteCategoriaVehiculoDao;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.GrupoPrecioParteCategoriaVehiculo;
import ec.com.ata.cn.modelo.Vehiculo;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1 
 */
@Stateless
public class GrupoPrecioParteCategoriaVehiculoBean {

    @Inject
    private GrupoPrecioParteCategoriaVehiculoDao grupoPrecioParteCategoriaVehiculoDao;
    
     public GrupoPrecioParteCategoriaVehiculo crear(GrupoPrecioParteCategoriaVehiculo grupoPrecioParteCategoriaVehiculo) throws Exception {
        return grupoPrecioParteCategoriaVehiculoDao.crear(grupoPrecioParteCategoriaVehiculo);
    }
    
    public List<GrupoPrecioParteCategoriaVehiculo> obtenerLista(){
        return grupoPrecioParteCategoriaVehiculoDao.obtenerTodos();
    }
    
    public List<GrupoPrecioParteCategoriaVehiculo> obtenerListaPorVehiculoYCategoria(GrupoPrecio grupoPrecio, Vehiculo vehiculo) {
        System.out.println("vehiculo: "+vehiculo);
        System.out.println("grupoPrecio: "+grupoPrecio);
        System.out.println("grupoPrecio.getIdGrupoPrecio: "+grupoPrecio.getIdGrupoPrecio());
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("vehiculo", vehiculo);
        parametros.put("grupoPrecio", grupoPrecio);
        return grupoPrecioParteCategoriaVehiculoDao.obtenerListaPorParametros(parametros);
    }
    
    public void eliminar(GrupoPrecioParteCategoriaVehiculo grupoPrecioParteCategoriaVehiculoEntrada){
        GrupoPrecioParteCategoriaVehiculo grupoPrecioParteCategoriaVehiculoEntradaTMP = grupoPrecioParteCategoriaVehiculoDao.obtenerPorCodigo(grupoPrecioParteCategoriaVehiculoEntrada.getGrupoPrecioParteCategoriaVehiculoId());
        grupoPrecioParteCategoriaVehiculoDao.eliminar(grupoPrecioParteCategoriaVehiculoEntradaTMP);
    }

}
