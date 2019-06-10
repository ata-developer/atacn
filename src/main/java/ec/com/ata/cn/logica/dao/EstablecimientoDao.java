/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica.dao;

import ec.com.ata.cn.logica.util.gestor.GenericoDaoUtil;
import ec.com.ata.cn.modelo.Establecimiento;
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
    
    
}
