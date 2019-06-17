/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.ProvinciaEstadoDao;
import ec.com.ata.cn.modelo.ProvinciaEstado;
import ec.com.ata.cn.modelo.Pais;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class ProvinciaBean {
    
    @Inject
    private ProvinciaEstadoDao provinciaEstadoDao;
    
    public ProvinciaEstado crear(ProvinciaEstado provinciaEstadoEntrada) throws Exception{
        return provinciaEstadoDao.crear(provinciaEstadoEntrada);
    }
    
    public List<ProvinciaEstado> obtenerLista(){
        return provinciaEstadoDao.obtenerTodos();
    }
    
    public List<ProvinciaEstado> obtenerListaPorPais(Pais paisEntrada){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("pais", paisEntrada);
        return provinciaEstadoDao.obtenerListaPorParametros(parametros);
    }
}
