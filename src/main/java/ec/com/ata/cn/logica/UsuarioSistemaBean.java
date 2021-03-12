/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.UsuarioSistemaDao;
import ec.com.ata.cn.modelo.UsuarioSistema;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class UsuarioSistemaBean {
    
    @Inject
    private UsuarioSistemaDao usuarioDao;
    
    public UsuarioSistema crear(UsuarioSistema usuarioEntrada) throws Exception {
        return usuarioDao.crear(usuarioEntrada);
    }
    
    
    public List<UsuarioSistema> obtenerLista(){
        return usuarioDao.obtenerTodos();
    }
    
    public UsuarioSistema obtenerPorCodigo(String correo){
        return usuarioDao.obtenerPorCodigo(correo);
    }
    
    public UsuarioSistema obtenerPorNumeroDocumento(String numeroDocumento){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("numeroDocumento", numeroDocumento);
        List<UsuarioSistema> listaUsuarioSistema = usuarioDao.obtenerListaPorParametros(parametros);
        if ( listaUsuarioSistema.isEmpty() ){
            return new UsuarioSistema();
        } else { 
            return listaUsuarioSistema.get(0);
        }
    }
    
    public UsuarioSistema modificar(UsuarioSistema usuarioEntrada) throws Exception {
        return usuarioDao.modificar(usuarioEntrada);
    }
    
    public List<UsuarioSistema> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return usuarioDao.obtenerListaPorParametros(parametros);
    }
}
