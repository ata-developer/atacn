/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.RolDao;
import ec.com.ata.cn.modelo.Rol;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class RolBean {
    
    @Inject
    private RolDao rolDao;
    
    public Rol crear(Rol rolEntrada) throws Exception{
        return rolDao.crear(rolEntrada);
    }
    
    public void eliminar(Rol rolEntrada) throws Exception{
        rolDao.eliminar(rolEntrada);
    }
    public void eliminar(Long rolEntradaId) throws Exception{
        rolDao.eliminar(rolEntradaId);
    }
    
    public void modificar(Rol parteEntrada) throws Exception {
        rolDao.modificar(parteEntrada);
    }
    
    public List<Rol> obtenerLista(){
        return rolDao.obtenerTodos();
    }
    
    public Rol obtenerPorCodigo(Long idRol){
        return rolDao.obtenerPorCodigo(idRol);
    }
    
    public List<Rol> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return rolDao.obtenerListaPorParametros(parametros);
    }
    
   
    
}
