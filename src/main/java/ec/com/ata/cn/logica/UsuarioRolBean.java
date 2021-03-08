/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.UsuarioRolDao;
import ec.com.ata.cn.modelo.UsuarioRol;
import ec.com.ata.cn.modelo.UsuarioRolId;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class UsuarioRolBean {

    @Inject
    private UsuarioRolDao usuarioRolDao;

    public UsuarioRol crear(UsuarioRol usuarioRolEntrada) throws Exception {
        return usuarioRolDao.crear(usuarioRolEntrada);
    }

    public void eliminar(UsuarioRol usuarioRolEntrada) throws Exception {
        usuarioRolDao.eliminar(usuarioRolEntrada);
    }

    public void eliminar(UsuarioRolId usuarioRolEntradaId) throws Exception {
        usuarioRolDao.eliminar(usuarioRolEntradaId);
    }

    public void modificar(UsuarioRol parteEntrada) throws Exception {
        usuarioRolDao.modificar(parteEntrada);
    }

    public List<UsuarioRol> obtenerLista() {
        return usuarioRolDao.obtenerTodos();
    }

    public UsuarioRol obtenerPorCodigo(UsuarioRolId idUsuarioRol) {
        return usuarioRolDao.obtenerPorCodigo(idUsuarioRol);
    }

    public List<UsuarioRol> obtenerListaPorParametros(HashMap<String, Object> parametros) {
        return usuarioRolDao.obtenerListaPorParametros(parametros);
    }

}
