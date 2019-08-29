/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.ImagenDao;
import ec.com.ata.cn.modelo.Imagen;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class ImagenBean {

    @Inject
    private ImagenDao imagenDao;
    
    public Imagen guardar(Imagen imagenEntrada) throws Exception {
            return imagenDao.crear(imagenEntrada);
    }
    
    public List<Imagen> obtenerLista() {
        return imagenDao.obtenerTodos();
    }
    
    public Imagen obtenerPorCodigo(Long idImagen){
        return imagenDao.obtenerPorCodigo(idImagen);
    }
    
    public void eliminar(Long imagenId){
        imagenDao.eliminar(imagenId);
    }
}
