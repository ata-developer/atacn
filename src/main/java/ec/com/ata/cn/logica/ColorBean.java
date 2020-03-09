/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.ColorDao;
import ec.com.ata.cn.modelo.Color;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class ColorBean {
    
    @Inject
    private ColorDao colorDao;
    
    public Color crear(Color colorEntrada) throws Exception{
        return colorDao.crear(colorEntrada);
    }
    
    public List<Color> obtenerLista(){
        return colorDao.obtenerTodos();
    }
    
    public Color obtenerPorCodigo(Long idColor){
        return colorDao.obtenerPorCodigo(idColor);
    }
    
    public List<Color> obtenerListaPorParametros(HashMap<String, Object> parametros){
        return colorDao.obtenerListaPorParametros(parametros);
    }
}
