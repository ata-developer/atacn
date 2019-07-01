/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica.dao;

import ec.com.ata.cn.logica.util.gestor.GenericoDaoUtil;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.GrupoPrecio;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author ATA1
 */
@Stateless
public class EstablecimientoDao extends GenericoDaoUtil<Establecimiento, Long>{
    
    public EstablecimientoDao() {
        super(Establecimiento.class);
    }
    
    
    public List<Establecimiento> obtenerListaSinGrupoPrecio(){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("grupoPrecioIsNull","grupoPrecio");        
        return obtenerListaPorParametros(parametros);
    }
    
    public List<Establecimiento> obtenerListaPorGrupoPrecio(GrupoPrecio grupoPrecio){
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("grupoPrecio",grupoPrecio);        
        return obtenerListaPorParametros(parametros);
    }
    
}
