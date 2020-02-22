/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.DetalleDao;
import ec.com.ata.cn.modelo.Detalle;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class DetalleBean {
    
    @Inject
    private DetalleDao parteDao;
    
    public Detalle crear(Detalle detalleEntrada) throws Exception{
        return parteDao.crear(detalleEntrada);
    }
    
    public List<Detalle> obtenerLista(){
        return parteDao.obtenerTodos();
    }
    
    public Detalle obtenerPorCodigo(Long idDetalle){
        return parteDao.obtenerPorCodigo(idDetalle);
    }
    
    public List<Detalle> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return parteDao.obtenerListaPorParametros(parametros);
    }
}
