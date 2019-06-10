/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica.dao;

import ec.com.ata.cn.logica.util.gestor.GenericoDaoUtil;
import ec.com.ata.cn.modelo.Ciudad;
import ec.com.ata.cn.modelo.Pais;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author ATA1
 */
@Stateless
public class CiudadDao extends GenericoDaoUtil<Ciudad, Long>{
    
    public CiudadDao() {
        super(Ciudad.class);
    }
    
    public List<Ciudad> obtenerListaPorPais(Pais paisEntrada){
        return null;
    }
}
