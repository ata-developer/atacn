/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.OrdenFechaDao;
import ec.com.ata.cn.modelo.OrdenFecha;
import ec.com.ata.cn.modelo.GrupoPrecio;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class OrdenFechaBean {
    
    @Inject
    private OrdenFechaDao ordenFechaDao;
    
    public OrdenFecha obtenerPorCodigo(Long codigo) {
        return ordenFechaDao.obtenerPorCodigo(codigo);
    }
    
    public OrdenFecha crear(OrdenFecha ordenFechaEntrada) throws Exception {    
        return ordenFechaDao.crear(ordenFechaEntrada);
    }
    
    public OrdenFecha modificar(OrdenFecha ordenFechaEntrada) throws Exception {
        return ordenFechaDao.modificar(ordenFechaEntrada);
    }
    
    public List<OrdenFecha> obtenerLista(){
        return ordenFechaDao.obtenerTodos();
    }
    
    public void eliminar(Long ordenFechaEntradaId) throws Exception {    
        ordenFechaDao.eliminar(ordenFechaEntradaId);
    }
}
