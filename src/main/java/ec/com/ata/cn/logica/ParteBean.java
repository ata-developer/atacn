/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.ParteDao;
import ec.com.ata.cn.modelo.Parte;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author ATA1
 */
@Stateless
public class ParteBean {
    
    @Inject
    private ParteDao parteDao;
    
    public Parte crear(Parte categoriaEntrada) throws Exception{
        return parteDao.crear(categoriaEntrada);
    }
    
    public void eliminar(Parte categoriaEntrada) throws Exception{
        parteDao.eliminar(categoriaEntrada);
    }
    public void eliminar(Long categoriaEntradaId) throws Exception{
        parteDao.eliminar(categoriaEntradaId);
    }
    
    public void modificar(Parte parteEntrada) throws Exception {
        parteDao.modificar(parteEntrada);
    }
    
    public List<Parte> obtenerLista(){
        return parteDao.obtenerTodos();
    }
    
    public Parte obtenerPorCodigo(Long idParte){
        return parteDao.obtenerPorCodigo(idParte);
    }
    
    public List<Parte> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return parteDao.obtenerListaPorParametros(parametros);
    }
    
    public Parte obtenerPorCodigoPersonalizado(String codigoPersonalizado){
        HashMap<String, Object> parametros = new HashMap<>();
        System.out.println("codigoPersonalizado:"+codigoPersonalizado);
        parametros.put("codigo", codigoPersonalizado);
        return parteDao.obtenerListaPorParametros(parametros).get(0);
    }
    
    public List<Parte> obtenerListaPorPadre(Parte padre){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("padre", padre);
        return parteDao.obtenerListaPorParametros(parametros);
    }
    
    public List<Parte> obtenerListaPorPadreIsNotNull(){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("padreIsNotNull", null);
        return parteDao.obtenerListaPorParametros(parametros);
    }
    
    public List<Parte> obtenerListaPorPadreItNull(){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("padreIsNull", null);
        return parteDao.obtenerListaPorParametros(parametros);
    }
    
     public List<Parte> obtenerListaPorPadres(){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("esPadre", true);
        return parteDao.obtenerListaPorParametros(parametros);
    }
    
    public TreeNode cargarNodoPrincipal (){
        TreeNode nodoPrincipal = new DefaultTreeNode();
        List<Parte> listaParte = obtenerListaPorPadre(null);
        for (Parte parte : listaParte) {
            nodoPrincipal.getChildren().add(crearNuevoYCargarHijos(parte));
        }
        return nodoPrincipal;
    }
    
    public TreeNode crearNuevoYCargarHijos(Parte parte){
        TreeNode hijo = new DefaultTreeNode(parte);
        List<Parte> listaParteHijos = this.obtenerListaPorPadre(parte);
        for (Parte parteTmp : listaParteHijos) {
            hijo.getChildren().add(crearNuevoYCargarHijos(parteTmp));
        }
        return hijo;
    }
    
}
