/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.PeriodoHorarioDao;
import ec.com.ata.cn.modelo.PeriodoHorario;
import ec.com.ata.cn.modelo.PeriodoHorarioId;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class PeriodoHorarioBean {
    
    @Inject
    private PeriodoHorarioDao periodoHorarioDao;
    
    public PeriodoHorario crear(PeriodoHorario periodoHorarioEntrada) throws Exception{
        return periodoHorarioDao.crear(periodoHorarioEntrada);
    }
    
    public List<PeriodoHorario> obtenerLista(){
        return periodoHorarioDao.obtenerTodos();
    }
    
    public PeriodoHorario obtenerPorCodigo(PeriodoHorarioId idPeriodoHorario){
        return periodoHorarioDao.obtenerPorCodigo(idPeriodoHorario);
    }
    
    public List<PeriodoHorario> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return periodoHorarioDao.obtenerListaPorParametros(parametros);
    }
    
    public void eliminar (PeriodoHorarioId idPeriodoHorarioEntrada) {
        periodoHorarioDao.eliminar(idPeriodoHorarioEntrada);
    }
    
    public void eliminar (PeriodoHorario periodoHorarioEntrada) {
        periodoHorarioDao.eliminar(periodoHorarioEntrada);
    } 
}
