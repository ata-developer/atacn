/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.TrabajoParteDao;
import ec.com.ata.cn.modelo.TrabajoParte;
import ec.com.ata.cn.modelo.TrabajoParte;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class TrabajoParteBean {
    
    @Inject
    private TrabajoParteDao trabajoParteDao;
    
    public TrabajoParte crear(TrabajoParte trabajoParteEntrada) throws Exception{
        return trabajoParteDao.crear(trabajoParteEntrada);
    }
    
    public TrabajoParte modificar(TrabajoParte trabajoParteEntrada) throws Exception{
        return trabajoParteDao.modificar(trabajoParteEntrada);
    }
    
    public List<TrabajoParte> obtenerLista(){
        return trabajoParteDao.obtenerTodos();
    }
    
    public TrabajoParte obtenerPorCodigo(Long idTrabajoParte){
        return trabajoParteDao.obtenerPorCodigo(idTrabajoParte);
    }
    
    public List<TrabajoParte> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return trabajoParteDao.obtenerListaPorParametros(parametros);
    }
    
    public void eliminar (TrabajoParte trabajoParteEntrada) {
        trabajoParteDao.eliminar(trabajoParteEntrada);
    }
    
    public void eliminar (Long idTrabajoParteEntrada) {
        trabajoParteDao.eliminar(idTrabajoParteEntrada);
    }
    
    
}
