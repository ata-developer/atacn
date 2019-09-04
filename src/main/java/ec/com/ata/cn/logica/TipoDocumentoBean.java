/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.TipoDocumentoDao;
import ec.com.ata.cn.modelo.TipoDocumento;
import java.util.HashMap;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class TipoDocumentoBean {
    
    @Inject
    private TipoDocumentoDao tipoNumeracionDocumentoDao;
    
    public TipoDocumento crear(TipoDocumento tipoNumeracionDocumentoEntrada) throws Exception{
        return tipoNumeracionDocumentoDao.crear(tipoNumeracionDocumentoEntrada);
    }
        
    public List<TipoDocumento> obtenerLista(){
        return tipoNumeracionDocumentoDao.obtenerTodos();
    }
    
    public List<TipoDocumento> obtenerListasSistema(Object parametroEntrada){
        HashMap<String,Object> parametros = new HashMap<>();
        parametros.put("esSistema", parametroEntrada);
        return tipoNumeracionDocumentoDao.obtenerListaPorParametros(parametros);
    }
    
    public List<TipoDocumento> obtenerListasCliente(Object parametroEntrada){
        HashMap<String,Object> parametros = new HashMap<>();
        parametros.put("esCliente", parametroEntrada);
        return tipoNumeracionDocumentoDao.obtenerListaPorParametros(parametros);
    }
}
