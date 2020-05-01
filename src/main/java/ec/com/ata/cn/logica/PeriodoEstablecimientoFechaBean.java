/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.PeriodoEstablecimientoFechaDao;
import ec.com.ata.cn.modelo.PeriodoEstablecimientoFecha;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class PeriodoEstablecimientoFechaBean {
    
    @Inject
    private PeriodoEstablecimientoFechaDao periodoEstablecimientoFechaDao;
    
    public PeriodoEstablecimientoFecha crear(PeriodoEstablecimientoFecha periodoEstablecimientoFechaEntrada) throws Exception{
        return periodoEstablecimientoFechaDao.crear(periodoEstablecimientoFechaEntrada);
    }
    
    public List<PeriodoEstablecimientoFecha> obtenerLista(){
        return periodoEstablecimientoFechaDao.obtenerTodos();
    }
    
    public PeriodoEstablecimientoFecha obtenerPorCodigo(Long idPeriodoEstablecimientoFecha){
        return periodoEstablecimientoFechaDao.obtenerPorCodigo(idPeriodoEstablecimientoFecha);
    }
    
    public List<PeriodoEstablecimientoFecha> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return periodoEstablecimientoFechaDao.obtenerListaPorParametros(parametros);
    }
}
