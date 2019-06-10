/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.EstablecimientoDao;
import ec.com.ata.cn.modelo.Establecimiento;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class EstablecimientoBean {
    
    @Inject
    private EstablecimientoDao establecimientoDao;
    
    public Establecimiento crear(Establecimiento establecimientoEntrada) throws Exception{
        return establecimientoDao.crear(establecimientoEntrada);
    }
    
    public List<Establecimiento> obtenerLista(){
        return establecimientoDao.obtenerTodos();
    }
}
