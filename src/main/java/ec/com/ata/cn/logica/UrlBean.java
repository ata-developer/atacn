/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.UrlDao;
import ec.com.ata.cn.modelo.Parte;
import ec.com.ata.cn.modelo.Url;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class UrlBean {
    
    @Inject
    private UrlDao urlDao;
    
    public Url crear(Url urlEntrada) throws Exception{
        return urlDao.crear(urlEntrada);
    }
    
    public void eliminar(Url urlEntrada) throws Exception{
        urlDao.eliminar(urlEntrada);
    }
    public void eliminar(Long urlEntradaId) throws Exception{
        urlDao.eliminar(urlEntradaId);
    }
    
    public void modificar(Url urlEntrada) throws Exception {
        urlDao.modificar(urlEntrada);
    }
    
    public List<Url> obtenerLista(){
        return urlDao.obtenerTodos();
    }
    
    public Url obtenerPorCodigo(Long idUrl){
        return urlDao.obtenerPorCodigo(idUrl);
    }
    
    public List<Url> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return urlDao.obtenerListaPorParametros(parametros);
    }
    
    public List<Url> obtenerListaPorPadreItNull(){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("padreIsNull", null);
        return urlDao.obtenerListaPorParametros(parametros);
    }
    
}
