/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.HorarioParqueaderoDao;
import ec.com.ata.cn.modelo.CitaDTO;
import ec.com.ata.cn.modelo.HorarioParqueadero;
import ec.com.ata.cn.modelo.HorarioParqueadero;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class HorarioParqueaderoBean {
    
    @Inject
    private HorarioParqueaderoDao horarioParqueaderoDao;
    
    public HorarioParqueadero crear(HorarioParqueadero horarioEntrada) throws Exception {
        return horarioParqueaderoDao.crear(horarioEntrada);
    }
    
    public HorarioParqueadero modificar(HorarioParqueadero horarioEntrada) throws Exception {
        return horarioParqueaderoDao.modificar(horarioEntrada);
    }
    
    public List<HorarioParqueadero> obtenerLista() {
        return horarioParqueaderoDao.obtenerTodos();
    }

    public HorarioParqueadero obtenerPorCodigo(Long idHorarioParqueadero) {
        return horarioParqueaderoDao.obtenerPorCodigo(idHorarioParqueadero);
    }

    public List<HorarioParqueadero> obtenerListaPorParametros(HashMap<String, Object> parametros) {
        return horarioParqueaderoDao.obtenerListaPorParametros(parametros);
    }

    public void eliminar(HorarioParqueadero horarioEntrada) {
        horarioParqueaderoDao.eliminar(horarioEntrada);
    }

    public void eliminar(Long idHorarioParqueaderoEntrada) {
        horarioParqueaderoDao.eliminar(idHorarioParqueaderoEntrada);
    }
    
    public List<CitaDTO> obtenerCitaPorIdVehiculoTrabajo(Long idVehiculoTrabaho) {
        return horarioParqueaderoDao.obtenerCitaPorIdVehiculoTrabajo(idVehiculoTrabaho);
    }
}
