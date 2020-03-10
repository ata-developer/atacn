/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.EquipoDao;
import ec.com.ata.cn.modelo.Equipo;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class EquipoBean {
    
    @Inject
    private EquipoDao parteDao;
    
    public Equipo crear(Equipo equipoEntrada) throws Exception{
        return parteDao.crear(equipoEntrada);
    }
    
    public List<Equipo> obtenerLista(){
        return parteDao.obtenerTodos();
    }
    
    public Equipo obtenerPorCodigo(Long idEquipo){
        return parteDao.obtenerPorCodigo(idEquipo);
    }
    
    public List<Equipo> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return parteDao.obtenerListaPorParametros(parametros);
    }
}
