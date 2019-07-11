/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.ParametroDao;
import ec.com.ata.cn.modelo.Parametro;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class ParametroBean {

    @Inject
    private ParametroDao parametroDao;

    public Parametro obtenerPorCodigo(String codigo) {
        return parametroDao.obtenerPorCodigo(codigo);
    }
}
