/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.VehiculoDao;
import ec.com.ata.cn.modelo.Vehiculo;

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
    private VehiculoDao categoriaDao;
    
    public Vehiculo crear(Vehiculo categoriaEntrada) throws Exception{
        return categoriaDao.crear(categoriaEntrada);
    }
    
    public List<Vehiculo> obtenerLista(){
        return categoriaDao.obtenerTodos();
    }
}
