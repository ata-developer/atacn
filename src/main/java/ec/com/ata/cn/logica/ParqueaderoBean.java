/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.ParqueaderoDao;
import ec.com.ata.cn.modelo.Parqueadero;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class ParqueaderoBean {
    
    @Inject
    private ParqueaderoDao parqueaderoDao;
    
    public Parqueadero crear(Parqueadero parqueaderoEntrada) throws Exception{
        return parqueaderoDao.crear(parqueaderoEntrada);
    }
    
    public List<Parqueadero> obtenerLista(){
        return parqueaderoDao.obtenerTodos();
    }
    
    public Parqueadero obtenerPorCodigo(Long idParqueadero){
        return parqueaderoDao.obtenerPorCodigo(idParqueadero);
    }
    
    public List<Parqueadero> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return parqueaderoDao.obtenerListaPorParametros(parametros);
    }
}
