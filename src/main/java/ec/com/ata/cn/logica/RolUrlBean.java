/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.RolUrlDao;
import ec.com.ata.cn.modelo.RolUrl;
import ec.com.ata.cn.modelo.RolUrlId;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class RolUrlBean {

    @Inject
    private RolUrlDao rolUrlDao;

    public RolUrl crear(RolUrl rolUrlEntrada) throws Exception {
        return rolUrlDao.crear(rolUrlEntrada);
    }

    public void eliminar(RolUrl rolUrlEntrada) throws Exception {
        rolUrlDao.eliminar(rolUrlEntrada);
    }

    public void eliminar(RolUrlId rolUrlEntradaId) throws Exception {
        rolUrlDao.eliminar(rolUrlEntradaId);
    }

    public void modificar(RolUrl parteEntrada) throws Exception {
        rolUrlDao.modificar(parteEntrada);
    }

    public List<RolUrl> obtenerLista() {
        return rolUrlDao.obtenerTodos();
    }

    public RolUrl obtenerPorCodigo(RolUrlId idRolUrl) {
        return rolUrlDao.obtenerPorCodigo(idRolUrl);
    }

    public List<RolUrl> obtenerListaPorParametros(HashMap<String, Object> parametros) {
        return rolUrlDao.obtenerListaPorParametros(parametros);
    }

}
