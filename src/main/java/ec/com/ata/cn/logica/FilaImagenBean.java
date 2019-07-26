/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.FilaImagenDao;
import ec.com.ata.cn.modelo.FilaImagen;
import ec.com.ata.cn.modelo.FilaImagenId;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class FilaImagenBean {

    @Inject
    private FilaImagenDao filaImagenDao;

    /*@Inject
    private TrabajoBean trabajoBean;

    @Inject
    private CategoriaBean categoriaBean;*/

    public FilaImagen crear(FilaImagen filaImagenEntrada) throws Exception {
        return filaImagenDao.crear(filaImagenEntrada);
    }

    public FilaImagen guardar(FilaImagen filaImagenEntrada) throws Exception {
        //if (null != filaImagenEntrada.getFilaImagenId()) {
          //  return filaImagenDao.modificar(filaImagenEntrada);
        //} else {
            return filaImagenDao.crear(filaImagenEntrada);
        //}
    }

    public List<FilaImagen> obtenerLista() {
        return filaImagenDao.obtenerTodos();
    }

    public FilaImagen obtenerPorId(FilaImagenId filaImagenId) {
        return filaImagenDao.obtenerPorCodigo(filaImagenId);
    }

    public FilaImagen modificar(FilaImagen filaImagenEntrada) throws Exception {
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
                FilaImagenId filaImagenIdTmp = new FilaImagenId();
                filaImagenIdTmp.setIdCategoria(idCategoria);
                filaImagenIdTmp.setIdTrabajo(idTrabajo);
                FilaImagen filaImagenTmp = filaImagenDao.obtenerPorCodigo(filaImagenIdTmp);
                filaImagenTmp = (filaImagenTmp == null ? new FilaImagen() :filaImagenTmp);
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
                FilaImagenId filaImagenIdTmp = new FilaImagenId();
                filaImagenIdTmp.setIdCategoria(idCategoria);
                filaImagenIdTmp.setIdTrabajo(idTrabajo);
                filaImagenIdTmp.setIdGrupoPrecio(idGrupoPrecio);
                FilaImagen filaImagenTmp = filaImagenDao.obtenerPorCodigo(filaImagenIdTmp);
                filaImagenTmp = (filaImagenTmp == null ? new FilaImagen() :filaImagenTmp);
                mapaFilaImagen.put(clave, filaImagenTmp);
            }
            listaFilaImagen.add(mapaFilaImagen);
        }
        return listaFilaImagen;
    }*/
}
