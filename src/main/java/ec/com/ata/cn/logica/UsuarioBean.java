/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.UsuarioDao;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.logica.util.gestor.HashCreadorUtil;
import ec.com.ata.cn.modelo.Detalle;
import ec.com.ata.cn.modelo.Usuario;
import java.util.HashMap;

import java.util.List;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class UsuarioBean {
    
    @Inject
    private UsuarioDao usuarioDao;
    
    public Usuario crear(Usuario usuarioEntrada) throws Exception{
        Optional<String> hashContraseniaOptional = HashCreadorUtil.hashContrasenia(usuarioEntrada.getContrasenia(), Constante.SALT);
        String hashContrasenia = hashContraseniaOptional.get();
        usuarioEntrada.setContrasenia(hashContrasenia);
        return usuarioDao.crear(usuarioEntrada);
    }
    
    public List<Usuario> obtenerLista(){
        return usuarioDao.obtenerTodos();
    }
    
    public List<Usuario> obtenerModeloListaPorNumeroDocumentoLike(String numeroDocumento) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("numeroDocumentoLike", numeroDocumento);
        return usuarioDao.obtenerListaPorParametros(parametros);
    }
    
    public Usuario obtenerPorCodigo(Long idUsuario){
        return usuarioDao.obtenerPorCodigo(idUsuario);
    }
    
    public Usuario obtenerPorNumeroDocumento(String numeroDocumento){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("numeroDocumento", numeroDocumento);
        List<Usuario> listaUsuario = usuarioDao.obtenerListaPorParametros(parametros);
        if ( listaUsuario.isEmpty() ){
            return new Usuario();
        } else { 
            return listaUsuario.get(0);
        }
    }
    
    public Usuario modificar(Usuario usuarioEntrada) throws Exception {
        return usuarioDao.modificar(usuarioEntrada);
    }
    
    public List<Usuario> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return usuarioDao.obtenerListaPorParametros(parametros);
    }
}
