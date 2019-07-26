/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.VehiculoDao;
import ec.com.ata.cn.modelo.Fila;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Vehiculo;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class VehiculoBean {

    @Inject
    private VehiculoDao vehiculoDao;
    
    @Inject
    private FilaBean filaBean;

    public Vehiculo crear(Vehiculo vehiculoEntrada) throws Exception {
        Vehiculo vehiculoCreado = vehiculoDao.crear(vehiculoEntrada);
        if (null != vehiculoEntrada.getFilasDeAsientos()){
            List<Fila> filasDeAsientos = vehiculoEntrada.getFilasDeAsientos();
            for (Fila filaDeAsiento : filasDeAsientos) {
                filaDeAsiento.setVehiculo(vehiculoCreado);
                filaBean.crear(filaDeAsiento);
            }
        }
        return vehiculoCreado;
    }
    
    

    public List<Vehiculo> obtenerLista() {
        return vehiculoDao.obtenerTodos();
    }

    public List<Vehiculo> obtenerListaPorMarca(MarcaVehiculo marcaVehiculo) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("marca", marcaVehiculo);
        return vehiculoDao.obtenerListaPorParametros(parametros);
    }
}
