/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.PEFHorarioDao;
import ec.com.ata.cn.modelo.PEFHorario;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class PEFHorarioBean {
    
    @Inject
    private PEFHorarioDao pEFHorarioDao;
    
    public PEFHorario crear(PEFHorario pEFHorarioEntrada) throws Exception{
        return pEFHorarioDao.crear(pEFHorarioEntrada);
    }
    
    public List<PEFHorario> obtenerLista(){
        return pEFHorarioDao.obtenerTodos();
    }
    
    public PEFHorario obtenerPorCodigo(Long idPEFHorario){
        return pEFHorarioDao.obtenerPorCodigo(idPEFHorario);
    }
    
    public List<PEFHorario> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return pEFHorarioDao.obtenerListaPorParametros(parametros);
    }
}
