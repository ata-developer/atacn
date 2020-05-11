/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.ImpuestoDao;
import ec.com.ata.cn.modelo.Impuesto;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class ImpuestoBean {

    @Inject
    private ImpuestoDao vehiculoTrabajoDao;

    public Impuesto crear(Impuesto impuestoEntrada) throws Exception {
        return vehiculoTrabajoDao.crear(impuestoEntrada);
    }
    
    public Impuesto modificar(Impuesto impuestoEntrada) throws Exception {
        return vehiculoTrabajoDao.modificar(impuestoEntrada);
    }
    
    public List<Impuesto> obtenerLista() {
        return vehiculoTrabajoDao.obtenerTodos();
    }

    public Impuesto obtenerPorCodigo(Long idImpuesto) {
        return vehiculoTrabajoDao.obtenerPorCodigo(idImpuesto);
    }

    public List<Impuesto> obtenerListaPorParametros(HashMap<String, Object> parametros) {
        return vehiculoTrabajoDao.obtenerListaPorParametros(parametros);
    }

    public void eliminar(Impuesto impuestoEntrada) {
        vehiculoTrabajoDao.eliminar(impuestoEntrada);
    }

    public void eliminar(Long idImpuestoEntrada) {
        vehiculoTrabajoDao.eliminar(idImpuestoEntrada);
    }

}
