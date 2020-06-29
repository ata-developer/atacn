/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.OrdenVehiculoDao;
import ec.com.ata.cn.modelo.OrdenVehiculo;
import ec.com.ata.cn.modelo.Usuario;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class OrdenVehiculoBean {

    @Inject
    private OrdenVehiculoDao ordenVehiculoDao;
    
    @Inject
    private UsuarioBean usuarioBean;

    public OrdenVehiculo crear(OrdenVehiculo ordenVehiculoEntrada) throws Exception {
        return ordenVehiculoDao.crear(ordenVehiculoEntrada);
    }

    public OrdenVehiculo crearOrden(OrdenVehiculo ordenVehiculoEntrada, Usuario clienteOrden, Usuario clienteFactura, Boolean llenarEsteMomentoFactura) throws Exception {
        OrdenVehiculo ordenVehiculo = new OrdenVehiculo();
        if (llenarEsteMomentoFactura == true) {
            if (clienteFactura.getIdUsuario() == null) {
                clienteFactura = usuarioBean.crearUsuarioOrdenFactura(clienteFactura);
            } else {
                clienteFactura = usuarioBean.modificar(clienteFactura);
            }
            ordenVehiculo.setClienteFactura(clienteFactura);
        }

        if (clienteOrden.getIdUsuario() == null) {
            clienteOrden = usuarioBean.crearUsuarioOrdenFactura(clienteOrden);
        } else {
            clienteOrden = usuarioBean.modificar(clienteOrden);
        }
        ordenVehiculoEntrada.setClienteOrden(clienteOrden);
        return ordenVehiculoDao.crear(ordenVehiculoEntrada);
    }

    public List<OrdenVehiculo> obtenerLista() {
        return ordenVehiculoDao.obtenerTodos();
    }

    public OrdenVehiculo obtenerPorCodigo(Long idOrdenVehiculo) {
        return ordenVehiculoDao.obtenerPorCodigo(idOrdenVehiculo);
    }

    public List<OrdenVehiculo> obtenerListaPorParametros(HashMap<String, Object> parametros) {
        return ordenVehiculoDao.obtenerListaPorParametros(parametros);
    }

    public void eliminar(OrdenVehiculo ordenVehiculoEntrada) {
        ordenVehiculoDao.eliminar(ordenVehiculoEntrada);
    }

    public void eliminar(Long idOrdenVehiculoEntrada) {
        ordenVehiculoDao.eliminar(idOrdenVehiculoEntrada);
    }
}
