/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.controlador.util.AuthenticationUtils;
import ec.com.ata.cn.logica.dao.CorreoRolDao;
import ec.com.ata.cn.logica.dao.UsuarioDao;
import ec.com.ata.cn.logica.dao.UsuarioSistemaDao;
import ec.com.ata.cn.modelo.CorreoRol;
import ec.com.ata.cn.modelo.Usuario;
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
public class UsuarioBean {
    
    @Inject
    private UsuarioDao usuarioDao;
    
    @Inject
    private UsuarioSistemaDao usuarioSistemaDao;
    
    @Inject
    private CorreoRolDao correoRolDao;
    
    public Usuario crearUsuarioSistema(Usuario usuarioEntrada) throws Exception {
        usuarioEntrada.setContrasenia(AuthenticationUtils.encodeSHA256(usuarioEntrada.getContrasenia()));
        Usuario usuarioTmp = usuarioDao.crear(usuarioEntrada);
        UsuarioSistema usuarioSistemaTmp = new UsuarioSistema();
        usuarioSistemaTmp.setCorreo(usuarioTmp.getCorreo());
        usuarioSistemaTmp.setContrasenia(usuarioTmp.getContrasenia());
        usuarioSistemaTmp.setUsuario(usuarioTmp);
        usuarioSistemaTmp = usuarioSistemaDao.crear(usuarioSistemaTmp);
        CorreoRol correoRolTmp = new  CorreoRol();
        correoRolTmp.setCorreo(usuarioSistemaTmp.getCorreo());
        correoRolTmp.setRol("USUARIOS");
        correoRolDao.crear(correoRolTmp);
        return usuarioTmp;
    }
    
    public Usuario crear(Usuario usuarioEntrada) throws Exception {
        usuarioEntrada.setContrasenia(AuthenticationUtils.encodeSHA256(usuarioEntrada.getContrasenia()));
        return usuarioDao.crear(usuarioEntrada);
    }
    
    public Usuario crearUsuarioOrdenFactura(Usuario usuarioEntrada) throws Exception{       
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
        usuarioEntrada.setContrasenia(AuthenticationUtils.encodeSHA256(usuarioEntrada.getContrasenia()));
        return usuarioDao.modificar(usuarioEntrada);
    }
    
    public List<Usuario> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return usuarioDao.obtenerListaPorParametros(parametros);
    }
}
