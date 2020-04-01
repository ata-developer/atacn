/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.PeriodoDao;
import ec.com.ata.cn.modelo.Periodo;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class PeriodoBean {
    
    @Inject
    private PeriodoDao periodoDao;
    
    public Periodo crear(Periodo periodoEntrada) throws Exception{
        return periodoDao.crear(periodoEntrada);
    }
    
    public List<Periodo> obtenerLista(){
        return periodoDao.obtenerTodos();
    }
    
    public Periodo obtenerPorCodigo(Long idPeriodo){
        return periodoDao.obtenerPorCodigo(idPeriodo);
    }
    
    public List<Periodo> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return periodoDao.obtenerListaPorParametros(parametros);
    }
}
