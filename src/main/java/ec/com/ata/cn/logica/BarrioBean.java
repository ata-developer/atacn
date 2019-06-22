/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.BarrioDao;
import ec.com.ata.cn.modelo.Barrio;
import ec.com.ata.cn.modelo.Ciudad;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class BarrioBean {
    
    @Inject
    private BarrioDao barrioDao;
    
    public Barrio crear(Barrio ciudadEntrada) throws Exception{
        return barrioDao.crear(ciudadEntrada);
    }
    
    public List<Barrio> obtenerLista(){
        return barrioDao.obtenerTodos();
    }
    
    public List<Barrio> obtenerListaPorCiudad(Ciudad ciudadEntrada){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("ciudad", ciudadEntrada);
        return barrioDao.obtenerListaPorParametros(parametros);
    }
}
