/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.ParteDetalleDao;
import ec.com.ata.cn.modelo.ParteDetalle;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class ParteDetalleBean {
    
    @Inject
    private ParteDetalleDao parteDetalleDao;
    
    public ParteDetalle crear(ParteDetalle detalleEntrada) throws Exception{
        return parteDetalleDao.crear(detalleEntrada);
    }
    
    public List<ParteDetalle> obtenerLista(){
        return parteDetalleDao.obtenerTodos();
    }
    
    public ParteDetalle obtenerPorCodigo(Long idParteDetalle){
        return parteDetalleDao.obtenerPorCodigo(idParteDetalle);
    }
    
    public List<ParteDetalle> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return parteDetalleDao.obtenerListaPorParametros(parametros);
    }
}
