/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.PlantillaDao;
import ec.com.ata.cn.modelo.Plantilla;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoParte;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class PlantillaBean {

    @Inject
    private PlantillaDao plantillaDao;

    public Plantilla crear(Plantilla categoriaEntrada) throws Exception {
        return plantillaDao.crear(categoriaEntrada);
    }

    public void eliminar(Plantilla categoriaEntrada) throws Exception {
        plantillaDao.eliminar(categoriaEntrada);
    }

    public void eliminar(Long categoriaEntradaId) throws Exception {
        plantillaDao.eliminar(categoriaEntradaId);
    }

    public void modificar(Plantilla plantillaEntrada) throws Exception {
        plantillaDao.modificar(plantillaEntrada);
    }

    public List<Plantilla> obtenerLista() {
        return plantillaDao.obtenerTodos();
    }

    public Plantilla obtenerPorCodigo(Long idPlantilla) {
        return plantillaDao.obtenerPorCodigo(idPlantilla);
    }

    public List<Plantilla> obtenerListaPorParametros(HashMap<String, Object> parametros) {
        return plantillaDao.obtenerListaPorParametros(parametros);
    }

    public List<Plantilla> obtenerListaPorVehiculo(Vehiculo vehiculoEntrada) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("vehiculo", vehiculoEntrada);
        return plantillaDao.obtenerListaPorParametros(parametros);
    }

}
