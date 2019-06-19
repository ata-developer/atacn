/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.controlador.util.ConstantesUtil;
import ec.com.ata.cn.logica.dao.UsuarioDao;
import ec.com.ata.cn.logica.util.gestor.HashCreadorUtil;
import ec.com.ata.cn.modelo.Usuario;

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
        Optional<String> hashContraseniaOptional = HashCreadorUtil.hashContrasenia(usuarioEntrada.getContrasenia(), ConstantesUtil.SALT);
        String hashContrasenia = hashContraseniaOptional.get();
        usuarioEntrada.setContrasenia(hashContrasenia);
        return usuarioDao.crear(usuarioEntrada);
    }
    
    public List<Usuario> obtenerLista(){
        return usuarioDao.obtenerTodos();
    }
}
