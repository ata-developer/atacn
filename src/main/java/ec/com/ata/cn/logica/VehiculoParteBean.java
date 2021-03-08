/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.VehiculoParteDao;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoParte;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class VehiculoParteBean {
    
    @Inject
    private VehiculoParteDao vehiculoParteDao;
    
    public VehiculoParte crear(VehiculoParte categoriaEntrada) throws Exception{
        return vehiculoParteDao.crear(categoriaEntrada);
    }
    
    public void eliminar(VehiculoParte vehiculoParteEntrada) throws Exception{
        vehiculoParteDao.eliminar(vehiculoParteEntrada);
    }
    public void eliminar(Long categoriaEntradaId) throws Exception{
        vehiculoParteDao.eliminar(categoriaEntradaId);
    }
    
    public void modificar(VehiculoParte vehiculoParteEntrada) throws Exception {
        vehiculoParteDao.modificar(vehiculoParteEntrada);
    }
    
    public List<VehiculoParte> obtenerLista(){
        return vehiculoParteDao.obtenerTodos();
    }
    
    public VehiculoParte obtenerPorCodigo(Long idVehiculoParte){
        return vehiculoParteDao.obtenerPorCodigo(idVehiculoParte);
    }
    
    public List<VehiculoParte> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return vehiculoParteDao.obtenerListaPorParametros(parametros);
    }
    
    public List<VehiculoParte> obtenerListaPorVehiculo(Vehiculo vehiculoEntrada){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("vehiculo", vehiculoEntrada);
        parametros.put("disposicionOrderByAsc", null);
        return vehiculoParteDao.obtenerListaPorParametros(parametros);
    }
   
    
}
