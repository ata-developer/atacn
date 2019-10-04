/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;


import ec.com.ata.cn.logica.dao.TrabajoCategoriaPrecioDao;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.modelo.Categoria;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.Trabajo;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecioId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class TrabajoCategoriaPrecioBean {

    @Inject
    private TrabajoCategoriaPrecioDao trabajoCategoriaPrecioDao;

    @Inject
    private TrabajoBean trabajoBean;

    @Inject
    private CategoriaBean categoriaBean;

    public TrabajoCategoriaPrecio crear(TrabajoCategoriaPrecio trabajoCategoriaPrecioEntrada) throws Exception {
        return trabajoCategoriaPrecioDao.crear(trabajoCategoriaPrecioEntrada);
    }

    public TrabajoCategoriaPrecio guardar(TrabajoCategoriaPrecio trabajoCategoriaPrecioEntrada) throws Exception {
        //if (null != trabajoCategoriaPrecioEntrada.getTrabajoCategoriaPrecioId()) {
          //  return trabajoCategoriaPrecioDao.modificar(trabajoCategoriaPrecioEntrada);
        //} else {
            return trabajoCategoriaPrecioDao.crear(trabajoCategoriaPrecioEntrada);
        //}
    }

    public List<TrabajoCategoriaPrecio> obtenerLista() {
        return trabajoCategoriaPrecioDao.obtenerTodos();
    }

    public TrabajoCategoriaPrecio obtenerPorId(TrabajoCategoriaPrecioId trabajoCategoriaPrecioId) {
        return trabajoCategoriaPrecioDao.obtenerPorCodigo(trabajoCategoriaPrecioId);
    }

    public TrabajoCategoriaPrecio modificar(TrabajoCategoriaPrecio trabajoCategoriaPrecioEntrada) throws Exception {
        return trabajoCategoriaPrecioDao.modificar(trabajoCategoriaPrecioEntrada);
    }

    public List<HashMap<String, Object>> obtenerListaMapaTrabajoCategoriaPrecio() {
        List<HashMap<String, Object>> listaTrabajoCategoriaPrecio = new ArrayList<>();       
        List<Trabajo> listaTrabajo = trabajoBean.obtenerLista();
        List<Categoria> listaCategoria = categoriaBean.obtenerLista();
        for (Trabajo trabajo : listaTrabajo) {
             HashMap<String, Object> mapaTrabajoCategoriaPrecio = new HashMap<>();
             mapaTrabajoCategoriaPrecio.put(Constante.TRABAJO_CATEGORIA, trabajo.getDescripcion());
            for (Categoria categoria : listaCategoria) {
                String clave = categoria.getCategoria();
                Long idCategoria = categoria.getIdCategoria();
                Long idTrabajo = trabajo.getIdTrabajo();
                TrabajoCategoriaPrecioId trabajoCategoriaPrecioIdTmp = new TrabajoCategoriaPrecioId();
                trabajoCategoriaPrecioIdTmp.setIdCategoria(idCategoria);
                trabajoCategoriaPrecioIdTmp.setIdTrabajo(idTrabajo);
                TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp = trabajoCategoriaPrecioDao.obtenerPorCodigo(trabajoCategoriaPrecioIdTmp);
                trabajoCategoriaPrecioTmp = (trabajoCategoriaPrecioTmp == null ? new TrabajoCategoriaPrecio() :trabajoCategoriaPrecioTmp);
                mapaTrabajoCategoriaPrecio.put(clave, trabajoCategoriaPrecioTmp);
            }
            listaTrabajoCategoriaPrecio.add(mapaTrabajoCategoriaPrecio);
        }

        return listaTrabajoCategoriaPrecio;
    }
    
    public List<HashMap<String, Object>> obtenerListaMapaTrabajoCategoriaPrecio(GrupoPrecio grupoPrecio) {
        List<HashMap<String, Object>> listaTrabajoCategoriaPrecio = new ArrayList<>();       
        List<Trabajo> listaTrabajo = trabajoBean.obtenerListaPorGrupoPrecio(grupoPrecio);
        List<Categoria> listaCategoria = categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecio);
        for (Trabajo trabajo : listaTrabajo) {
             HashMap<String, Object> mapaTrabajoCategoriaPrecio = new HashMap<>();
             mapaTrabajoCategoriaPrecio.put(Constante.TRABAJO_CATEGORIA, trabajo.getDescripcion());
            for (Categoria categoria : listaCategoria) {
                String clave = categoria.getCategoria();
                Long idCategoria = categoria.getIdCategoria();
                Long idTrabajo = trabajo.getIdTrabajo();
                Long idGrupoPrecio = grupoPrecio.getIdGrupoPrecio();
                TrabajoCategoriaPrecioId trabajoCategoriaPrecioIdTmp = new TrabajoCategoriaPrecioId();
                trabajoCategoriaPrecioIdTmp.setIdCategoria(idCategoria);
                trabajoCategoriaPrecioIdTmp.setIdTrabajo(idTrabajo);
                trabajoCategoriaPrecioIdTmp.setIdGrupoPrecio(idGrupoPrecio);
                TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp = trabajoCategoriaPrecioDao.obtenerPorCodigo(trabajoCategoriaPrecioIdTmp);
                trabajoCategoriaPrecioTmp = (trabajoCategoriaPrecioTmp == null ? new TrabajoCategoriaPrecio() :trabajoCategoriaPrecioTmp);
                mapaTrabajoCategoriaPrecio.put(clave, trabajoCategoriaPrecioTmp);
            }
            listaTrabajoCategoriaPrecio.add(mapaTrabajoCategoriaPrecio);
        }
        return listaTrabajoCategoriaPrecio;
    }
    
    
}
