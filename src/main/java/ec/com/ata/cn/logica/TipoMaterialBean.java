/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.logica.dao.TipoMaterialDao;
import ec.com.ata.cn.modelo.TipoMaterial;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class TipoMaterialBean {
    
    @Inject
    private TipoMaterialDao tipoMaterialDao;
    
    public TipoMaterial crear(TipoMaterial tipoMaterialEntrada) throws Exception{
        return tipoMaterialDao.crear(tipoMaterialEntrada);
    }
    
    public List<TipoMaterial> obtenerLista(){
        return tipoMaterialDao.obtenerTodos();
    }
    
    public TipoMaterial obtenerPorId(Long idTipoMaterial){
        return tipoMaterialDao.obtenerPorCodigo(idTipoMaterial);
    }
    
    
}
