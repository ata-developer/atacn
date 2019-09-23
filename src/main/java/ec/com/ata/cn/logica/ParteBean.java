/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.ParteDao;
import ec.com.ata.cn.modelo.Parte;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class ParteBean {
    
    @Inject
    private ParteDao parteDao;
    
    public Parte crear(Parte categoriaEntrada) throws Exception{
        return parteDao.crear(categoriaEntrada);
    }
    
    public List<Parte> obtenerLista(){
        return parteDao.obtenerTodos();
    }
    
    public Parte obtenerPorCodigo(Long idParte){
        return parteDao.obtenerPorCodigo(idParte);
    }
}
