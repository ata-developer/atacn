/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.MaterialDao;
import ec.com.ata.cn.modelo.Material;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class MaterialBean {
    
    @Inject
    private MaterialDao materialDao;
    
    public Material crear(Material materialEntrada) throws Exception{
        return materialDao.crear(materialEntrada);
    }
    
    public List<Material> obtenerLista(){
        return materialDao.obtenerTodos();
    }
    
    public Material obtenerPorCodigo(Long idMaterial){
        return materialDao.obtenerPorCodigo(idMaterial);
    }
    
    public List<Material> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return materialDao.obtenerListaPorParametros(parametros);
    }
}
