/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica.dao;

import ec.com.ata.cn.logica.util.gestor.GenericoDaoUtil;
import ec.com.ata.cn.modelo.Categoria;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.Vehiculo;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author ATA1
 */
@Stateless
public class CategoriaDao extends GenericoDaoUtil<Categoria, Long>{
    
    public CategoriaDao() {
        super(Categoria.class);
    }
    
    public List<Categoria> obtenerListaPorGrupoPrecioYVehiculo(GrupoPrecio grupoPrecio, Vehiculo vehiculo){
        return this.em.createQuery("SELECT g.categoria FROM GrupoPrecioParteCategoriaVehiculo g WHERE g.grupoPrecio.idGrupoPrecio = :idGrupoPrecio AND g.vehiculo.idVehiculo = :idVehiculo")
                .setParameter("idGrupoPrecio", grupoPrecio.getIdGrupoPrecio())
                .setParameter("idVehiculo", vehiculo.getIdVehiculo())
                .getResultList();
    }
    
}
