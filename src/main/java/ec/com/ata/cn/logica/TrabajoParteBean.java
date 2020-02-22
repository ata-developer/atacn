/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.TrabajoParteDao;
import ec.com.ata.cn.modelo.TrabajoParte;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class TrabajoParteBean {
    
    @Inject
    private TrabajoParteDao trabajoParteDao;
    
    public TrabajoParte crear(TrabajoParte trabajoParteEntrada) throws Exception{
        return trabajoParteDao.crear(trabajoParteEntrada);
    }
    
    public List<TrabajoParte> obtenerLista(){
        return trabajoParteDao.obtenerTodos();
    }
    
    
}
