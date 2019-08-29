/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.TipoFilaDao;
import ec.com.ata.cn.modelo.TipoFila;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class TipoFilaBean {
    
    @Inject
    private TipoFilaDao tipoFilaDao;
    
    public TipoFila crear(TipoFila categoriaEntrada) throws Exception{
        return tipoFilaDao.crear(categoriaEntrada);
    }
    
    public List<TipoFila> obtenerLista(){
        return tipoFilaDao.obtenerTodos();
    }
    
    public TipoFila obtenerPorCodigo(Long idTipoFila){
        return tipoFilaDao.obtenerPorCodigo(idTipoFila);
    }
}
