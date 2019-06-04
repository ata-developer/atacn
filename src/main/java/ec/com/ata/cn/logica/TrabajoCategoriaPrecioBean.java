/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.TrabajoCategoriaPrecioDao;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecioId;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class TrabajoCategoriaPrecioBean {
    
    @Inject
    private TrabajoCategoriaPrecioDao trabajoCategoriaPrecioDao;
    
    public TrabajoCategoriaPrecio crear(TrabajoCategoriaPrecio trabajoCategoriaPrecioEntrada) throws Exception{
        return trabajoCategoriaPrecioDao.crear(trabajoCategoriaPrecioEntrada);
    }
    
    public List<TrabajoCategoriaPrecio> obtenerLista(){
        return trabajoCategoriaPrecioDao.obtenerTodos();
    }
    
    public TrabajoCategoriaPrecio obtenerPorId(TrabajoCategoriaPrecioId trabajoCategoriaPrecioId){
        return trabajoCategoriaPrecioDao.obtenerPorCodigo(trabajoCategoriaPrecioId);
    }
    
    public TrabajoCategoriaPrecio modificar(TrabajoCategoriaPrecio trabajoCategoriaPrecioEntrada) throws Exception{
        return trabajoCategoriaPrecioDao.modificar(trabajoCategoriaPrecioEntrada);
    }
}
