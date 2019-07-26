/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.FilaDao;
import ec.com.ata.cn.modelo.Fila;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class FilaBean {
    
    @Inject
    private FilaDao filaDao;
    
    public Fila crear(Fila filaEntrada) throws Exception{
        return filaDao.crear(filaEntrada);
    }
    
    public List<Fila> obtenerLista(){
        return filaDao.obtenerTodos();
    }
}
