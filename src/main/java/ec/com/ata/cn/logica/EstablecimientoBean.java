/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.EstablecimientoDao;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.GrupoPrecio;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class EstablecimientoBean {
    
    @Inject
    private EstablecimientoDao establecimientoDao;
    
    public Establecimiento obtenerPorCodigo(Long codigo) {
        return establecimientoDao.obtenerPorCodigo(codigo);
    }
    
    public Establecimiento crear(Establecimiento establecimientoEntrada) throws Exception {    
        return establecimientoDao.crear(establecimientoEntrada);
    }
    
    public Establecimiento modificar(Establecimiento establecimientoEntrada) throws Exception {
        return establecimientoDao.modificar(establecimientoEntrada);
    }
    
    public List<Establecimiento> obtenerLista(){
        return establecimientoDao.obtenerTodos();
    }
    
    public List<Establecimiento> obtenerListaSinGrupoPrecio(){
        return establecimientoDao.obtenerListaSinGrupoPrecio();
    }
    
    public List<Establecimiento> obtenerListaPorGrupoPrecio(GrupoPrecio grupoPrecio){
        return establecimientoDao.obtenerListaPorGrupoPrecio(grupoPrecio);
    }
    
    public List<Establecimiento> obtenerModeloListaPorNombreLike(String nombre) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("nombreLike", nombre);
        return establecimientoDao.obtenerListaPorParametros(parametros);
    }
    
}
