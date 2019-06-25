/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.CategoriaDao;
import ec.com.ata.cn.modelo.Categoria;
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
public class CategoriaBean {
    
    @Inject
    private CategoriaDao categoriaDao;
    
    public Categoria crear(Categoria categoriaEntrada) throws Exception{
        return categoriaDao.crear(categoriaEntrada);
    }
    
    public List<Categoria> obtenerLista(){
        return categoriaDao.obtenerTodos();
    }
    
    public List<Categoria> obtenerListaPorGrupoImpuesto(GrupoPrecio grupoPrecio){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("grupoPrecio", grupoPrecio);
        return categoriaDao.obtenerListaPorParametros(parametros);
    }
}
