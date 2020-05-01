/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.PeriodoEstablecimientoDao;
import ec.com.ata.cn.modelo.PeriodoEstablecimiento;
import ec.com.ata.cn.modelo.PeriodoEstablecimientoId;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class PeriodoEstablecimientoBean {
    
    @Inject
    private PeriodoEstablecimientoDao periodoEstablecimientoDao;
    
    public PeriodoEstablecimiento crear(PeriodoEstablecimiento horarioEntrada) throws Exception{
        return periodoEstablecimientoDao.crear(horarioEntrada);
    }
    
    public List<PeriodoEstablecimiento> obtenerLista(){
        return periodoEstablecimientoDao.obtenerTodos();
    }
    
    public PeriodoEstablecimiento obtenerPorCodigo(PeriodoEstablecimientoId idPeriodoEstablecimiento){
        return periodoEstablecimientoDao.obtenerPorCodigo(idPeriodoEstablecimiento);
    }
    
    public List<PeriodoEstablecimiento> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return periodoEstablecimientoDao.obtenerListaPorParametros(parametros);
    }
    
    public void eliminar (PeriodoEstablecimiento horarioEntrada) {
        periodoEstablecimientoDao.eliminar(horarioEntrada);
    }
    
    public void eliminar (PeriodoEstablecimientoId idPeriodoEstablecimientoEntrada) {
        periodoEstablecimientoDao.eliminar(idPeriodoEstablecimientoEntrada);
    } 
}
