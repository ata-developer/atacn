/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.MarcaVehiculoDao;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Parte;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class MarcaVehiculoBean {
    
    @Inject
    private MarcaVehiculoDao marcaVehiculoDao;
    
    public MarcaVehiculo crear(MarcaVehiculo marcaVehiculoEntrada) throws Exception{
        return marcaVehiculoDao.crear(marcaVehiculoEntrada);
    }
    
    public List<MarcaVehiculo> obtenerLista(){
        return marcaVehiculoDao.obtenerTodos();
    }
    
    public void modificar(MarcaVehiculo marcaVehiculoEntrada) throws Exception {
        marcaVehiculoDao.modificar(marcaVehiculoEntrada);
    }
    
    public MarcaVehiculo obtenerPorCodigo(Long idVehiculoParte){
        return marcaVehiculoDao.obtenerPorCodigo(idVehiculoParte);
    }
    
}
