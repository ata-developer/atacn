/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.AnioVehiculoDao;
import ec.com.ata.cn.modelo.AnioVehiculo;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class AnioVehiculoBean {
    
    @Inject
    private AnioVehiculoDao anioVehiculoDao;
    
    public AnioVehiculo crear(AnioVehiculo anioVehiculoEntrada) throws Exception {
        return anioVehiculoDao.crear(anioVehiculoEntrada);
    }
    
    public List<AnioVehiculo> obtenerLista(){
        return anioVehiculoDao.obtenerTodos();
    }
}
