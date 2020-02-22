/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.ParteDao;
import ec.com.ata.cn.modelo.Barrio;
import ec.com.ata.cn.modelo.Ciudad;
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
    
    public List<Parte> obtenerLista(){
        return parteDao.obtenerTodos();
    }
    
    public Parte obtenerPorCodigo(Long idParte){
        return parteDao.obtenerPorCodigo(idParte);
    }
    
    public List<Parte> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return parteDao.obtenerListaPorParametros(parametros);
    }
    
    public List<Parte> obtenerListaPorPadre(Parte padre){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("padre", padre);
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
