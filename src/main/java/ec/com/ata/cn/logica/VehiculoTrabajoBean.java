/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.VehiculoTrabajoDao;
import ec.com.ata.cn.modelo.VehiculoTrabajo;
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
    
    public VehiculoTrabajo crear(VehiculoTrabajo trabajoEntrada) throws Exception{
        return vehiculoTrabajoDao.crear(trabajoEntrada);
    }
    
    public List<VehiculoTrabajo> obtenerLista(){
        return vehiculoTrabajoDao.obtenerTodos();
    }
    
    public VehiculoTrabajo obtenerPorId(Long idTrabajo){
        return vehiculoTrabajoDao.obtenerPorCodigo(idTrabajo);
    }
    
    
}
