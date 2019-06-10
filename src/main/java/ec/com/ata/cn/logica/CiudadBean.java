/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.CiudadDao;
import ec.com.ata.cn.modelo.Ciudad;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class CiudadBean {
    
    @Inject
    private CiudadDao ciudadDao;
    
    public Ciudad crear(Ciudad ciudadEntrada) throws Exception{
        return ciudadDao.crear(ciudadEntrada);
    }
    
    public List<Ciudad> obtenerLista(){
        return ciudadDao.obtenerTodos();
    }
}
