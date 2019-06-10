/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.PaisDao;
import ec.com.ata.cn.modelo.Pais;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class PaisBean {
    
    @Inject
    private PaisDao paisDao;
    
    public Pais crear(Pais paisEntrada) throws Exception{
        return paisDao.crear(paisEntrada);
    }
    
    public List<Pais> obtenerLista(){
        return paisDao.obtenerTodos();
    }
}
