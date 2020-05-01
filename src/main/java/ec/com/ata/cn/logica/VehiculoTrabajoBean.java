/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.VehiculoTrabajoDao;
import ec.com.ata.cn.modelo.VehiculoTrabajo;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class VehiculoTrabajoBean {

    @Inject
    private VehiculoTrabajoDao vehiculoTrabajoDao;

    public VehiculoTrabajo crear(VehiculoTrabajo horarioEntrada) throws Exception {
        return vehiculoTrabajoDao.crear(horarioEntrada);
    }
    
    public VehiculoTrabajo modificar(VehiculoTrabajo horarioEntrada) throws Exception {
        return vehiculoTrabajoDao.modificar(horarioEntrada);
    }
    
    public List<VehiculoTrabajo> obtenerLista() {
        return vehiculoTrabajoDao.obtenerTodos();
    }

    public VehiculoTrabajo obtenerPorCodigo(Long idVehiculoTrabajo) {
        return vehiculoTrabajoDao.obtenerPorCodigo(idVehiculoTrabajo);
    }

    public List<VehiculoTrabajo> obtenerListaPorParametros(HashMap<String, Object> parametros) {
        return vehiculoTrabajoDao.obtenerListaPorParametros(parametros);
    }

    public void eliminar(VehiculoTrabajo horarioEntrada) {
        vehiculoTrabajoDao.eliminar(horarioEntrada);
    }

    public void eliminar(Long idVehiculoTrabajoEntrada) {
        vehiculoTrabajoDao.eliminar(idVehiculoTrabajoEntrada);
    }

}
