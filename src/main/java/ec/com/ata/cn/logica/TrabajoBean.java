/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.TrabajoDao;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.Trabajo;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class TrabajoBean {
    
    @Inject
    private TrabajoDao trabajoDao;
    
    public Trabajo crear(Trabajo trabajoEntrada) throws Exception {
        return trabajoDao.crear(trabajoEntrada);
    }
    
    public List<Trabajo> obtenerLista(){
        return trabajoDao.obtenerTodos();
    }
    
    public Trabajo obtenerPorId(Long idTrabajo){
        return trabajoDao.obtenerPorCodigo(idTrabajo);
    }
    
    public List<Trabajo> obtenerListaPorGrupoPrecio(GrupoPrecio grupoPrecio){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("grupoPrecio", grupoPrecio);
        return trabajoDao.obtenerListaPorParametros(parametros);
    }
    
    public void eliminar (Trabajo trabajoEntrada) {
        trabajoDao.eliminar(trabajoEntrada);
    }
    
    public void eliminar (Long idTrabajoEntrada) {
        trabajoDao.eliminar(idTrabajoEntrada);
    } 
    
    public void modificar(Trabajo trabajoEntrada) throws Exception {
        trabajoDao.modificar(trabajoEntrada);
    }
}
