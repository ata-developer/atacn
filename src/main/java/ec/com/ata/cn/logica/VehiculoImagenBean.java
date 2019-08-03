/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.VehiculoImagenDao;
import ec.com.ata.cn.modelo.VehiculoImagen;
import ec.com.ata.cn.modelo.VehiculoImagenId;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class VehiculoImagenBean {

    @Inject
    private VehiculoImagenDao filaImagenDao;

    /*@Inject
    private TrabajoBean trabajoBean;

    @Inject
    private CategoriaBean categoriaBean;*/

    public VehiculoImagen crear(VehiculoImagen filaImagenEntrada) throws Exception {
        return filaImagenDao.crear(filaImagenEntrada);
    }

    public VehiculoImagen guardar(VehiculoImagen filaImagenEntrada) throws Exception {
        //if (null != filaImagenEntrada.getFilaImagenId()) {
          //  return filaImagenDao.modificar(filaImagenEntrada);
        //} else {
            return filaImagenDao.crear(filaImagenEntrada);
        //}
    }

    public List<VehiculoImagen> obtenerLista() {
        return filaImagenDao.obtenerTodos();
    }

    public VehiculoImagen obtenerPorId(VehiculoImagenId filaImagenId) {
        return filaImagenDao.obtenerPorCodigo(filaImagenId);
    }

    public VehiculoImagen modificar(VehiculoImagen filaImagenEntrada) throws Exception {
        return filaImagenDao.modificar(filaImagenEntrada);
    }

    /*public List<HashMap<String, Object>> obtenerListaMapaFilaImagen() {
        List<HashMap<String, Object>> listaFilaImagen = new ArrayList<>();       
        List<Trabajo> listaTrabajo = trabajoBean.obtenerLista();
        List<Categoria> listaCategoria = categoriaBean.obtenerLista();
        for (Trabajo trabajo : listaTrabajo) {
             HashMap<String, Object> mapaFilaImagen = new HashMap<>();
             mapaFilaImagen.put(Constante.TRABAJO_CATEGORIA, trabajo.getDescripcion());
            for (Categoria categoria : listaCategoria) {
                String clave = categoria.getCategoria();
                Long idCategoria = categoria.getIdCategoria();
                Long idTrabajo = trabajo.getIdTrabajo();
                VehiculoImagenId filaImagenIdTmp = new VehiculoImagenId();
                filaImagenIdTmp.setIdCategoria(idCategoria);
                filaImagenIdTmp.setIdTrabajo(idTrabajo);
                VehiculoImagen filaImagenTmp = filaImagenDao.obtenerPorCodigo(filaImagenIdTmp);
                filaImagenTmp = (filaImagenTmp == null ? new VehiculoImagen() :filaImagenTmp);
                mapaFilaImagen.put(clave, filaImagenTmp);
            }
            listaFilaImagen.add(mapaFilaImagen);
        }

        return listaFilaImagen;
    }
    
    public List<HashMap<String, Object>> obtenerListaMapaFilaImagen(GrupoPrecio grupoPrecio) {
        List<HashMap<String, Object>> listaFilaImagen = new ArrayList<>();       
        List<Trabajo> listaTrabajo = trabajoBean.obtenerListaPorGrupoPrecio(grupoPrecio);
        List<Categoria> listaCategoria = categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecio);
        for (Trabajo trabajo : listaTrabajo) {
             HashMap<String, Object> mapaFilaImagen = new HashMap<>();
             mapaFilaImagen.put(Constante.TRABAJO_CATEGORIA, trabajo.getDescripcion());
            for (Categoria categoria : listaCategoria) {
                String clave = categoria.getCategoria();
                Long idCategoria = categoria.getIdCategoria();
                Long idTrabajo = trabajo.getIdTrabajo();
                Long idGrupoPrecio = grupoPrecio.getIdGrupoPrecio();
                VehiculoImagenId filaImagenIdTmp = new VehiculoImagenId();
                filaImagenIdTmp.setIdCategoria(idCategoria);
                filaImagenIdTmp.setIdTrabajo(idTrabajo);
                filaImagenIdTmp.setIdGrupoPrecio(idGrupoPrecio);
                VehiculoImagen filaImagenTmp = filaImagenDao.obtenerPorCodigo(filaImagenIdTmp);
                filaImagenTmp = (filaImagenTmp == null ? new VehiculoImagen() :filaImagenTmp);
                mapaFilaImagen.put(clave, filaImagenTmp);
            }
            listaFilaImagen.add(mapaFilaImagen);
        }
        return listaFilaImagen;
    }*/
}
