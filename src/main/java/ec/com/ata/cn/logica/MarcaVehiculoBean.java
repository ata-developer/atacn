/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.MarcaVehiculoDao;
import ec.com.ata.cn.modelo.MarcaVehiculo;

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
    private MarcaVehiculoDao categoriaDao;
    
    public MarcaVehiculo crear(MarcaVehiculo categoriaEntrada) throws Exception{
        return categoriaDao.crear(categoriaEntrada);
    }
    
    public List<MarcaVehiculo> obtenerLista(){
        return categoriaDao.obtenerTodos();
    }
}
