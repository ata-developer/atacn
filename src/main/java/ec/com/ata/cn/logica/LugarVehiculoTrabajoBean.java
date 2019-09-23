/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.LugarVehiculoTrabajoDao;
import ec.com.ata.cn.modelo.LugarVehiculoTrabajo;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class LugarVehiculoTrabajoBean {
    
    @Inject
    private LugarVehiculoTrabajoDao lugarVehiculoTrabajoDao;
    
    public LugarVehiculoTrabajo crear(LugarVehiculoTrabajo categoriaEntrada) throws Exception{
        return lugarVehiculoTrabajoDao.crear(categoriaEntrada);
    }
    
    public List<LugarVehiculoTrabajo> obtenerLista(){
        return lugarVehiculoTrabajoDao.obtenerTodos();
    }
    
    public LugarVehiculoTrabajo obtenerPorCodigo(Long idLugarVehiculoTrabajo){
        return lugarVehiculoTrabajoDao.obtenerPorCodigo(idLugarVehiculoTrabajo);
    }
}
