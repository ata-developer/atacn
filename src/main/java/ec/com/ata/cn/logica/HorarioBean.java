/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.HorarioDao;
import ec.com.ata.cn.modelo.Horario;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class HorarioBean {
    
    @Inject
    private HorarioDao horarioDao;
    
    public Horario crear(Horario horarioEntrada) throws Exception{
        return horarioDao.crear(horarioEntrada);
    }
    
    public List<Horario> obtenerLista(){
        return horarioDao.obtenerTodos();
    }
    
    public Horario obtenerPorCodigo(Long idHorario){
        return horarioDao.obtenerPorCodigo(idHorario);
    }
    
    public List<Horario> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return horarioDao.obtenerListaPorParametros(parametros);
    }
    
    public void eliminar (Horario horarioEntrada) {
        horarioDao.eliminar(horarioEntrada);
    }
    
    public void eliminar (Long idHorarioEntrada) {
        horarioDao.eliminar(idHorarioEntrada);
    } 
}
