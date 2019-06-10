/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.GrupoPrecioDao;
import ec.com.ata.cn.modelo.GrupoPrecio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class GrupoPrecioBean {
    
    @Inject
    private GrupoPrecioDao grupoPrecioDao;
    
    public GrupoPrecio crear(GrupoPrecio grupoPrecioEntrada) throws Exception{
        return grupoPrecioDao.crear(grupoPrecioEntrada);
    }
    
    public List<GrupoPrecio> obtenerLista(){
        return grupoPrecioDao.obtenerTodos();
    }
}
