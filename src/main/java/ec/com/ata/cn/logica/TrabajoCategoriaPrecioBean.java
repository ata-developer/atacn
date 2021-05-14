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
import ec.com.ata.cn.modelo.GrupoPrecioParteCategoriaVehiculo;
import ec.com.ata.cn.modelo.Parte;
import ec.com.ata.cn.modelo.Trabajo;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecioId;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoCategoriaTrabajo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
    
    @Inject
    private GrupoPrecioParteCategoriaVehiculoBean grupoPrecioParteCategoriaVehiculoBean;
    
    @Inject
    private VehiculoCategoriaTrabajoBean vehiculoCategoriaTrabajoBean;
    
    public List<TrabajoCategoriaPrecio> generarListaTrabajoCategoriaPrecioTodosMenosElAuto(Vehiculo vehiculoEntrada, GrupoPrecio grupoPrecio) {
        List<VehiculoCategoriaTrabajo> listaVehiculoCategoriaTrabajo = this.vehiculoCategoriaTrabajoBean.obtenerListaPorVehiculo(vehiculoEntrada);
        List<TrabajoCategoriaPrecio> listaCategoriaPrecioDeAuto = new ArrayList<>();
        for (VehiculoCategoriaTrabajo vehiculoCategoriaTrabajo : listaVehiculoCategoriaTrabajo) {
            listaCategoriaPrecioDeAuto.add(vehiculoCategoriaTrabajo.getTrabajoCategoriaPrecio());
        }        
        List<TrabajoCategoriaPrecio> diff = obtenerPorGrupoPrecio(grupoPrecio).stream()
                .filter(i -> !listaCategoriaPrecioDeAuto.contains(i))
                .collect(Collectors.toList());
        return diff;
    }
    
    public void eliminar(TrabajoCategoriaPrecio trabajoCategoriaPrecioEntrada) throws Exception {
        trabajoCategoriaPrecioDao.eliminar(trabajoCategoriaPrecioEntrada);
    }
    
    public List<TrabajoCategoriaPrecio> conseguirListaTrabajoCategoriaPrecio(GrupoPrecio grupoPrecioEntrada, Categoria categoriaEntrada) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("grupoPrecio", grupoPrecioEntrada);
        parametros.put("categoria", categoriaEntrada);
        return trabajoCategoriaPrecioDao.obtenerListaPorParametros(parametros);
    }
    
    public TrabajoCategoriaPrecio obtenerPorCategoriaTrabajoGrupoPrecio(Categoria categoriaEntrada, Trabajo trabajoEntrada, GrupoPrecio grupoPrecioEntrada) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("grupoPrecio", grupoPrecioEntrada);
        parametros.put("categoria", categoriaEntrada);
        parametros.put("trabajo", trabajoEntrada);
        List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecioTemp = trabajoCategoriaPrecioDao.obtenerListaPorParametros(parametros);
        if (listaTrabajoCategoriaPrecioTemp.isEmpty()) {
            return null;
        }
        return listaTrabajoCategoriaPrecioTemp.get(0);
    }
    
    public List<TrabajoCategoriaPrecio> generarListaCompletaTrabajoCategoriaPrecioPorVehiculo(List<GrupoPrecioParteCategoriaVehiculo> listaGrupoPrecioParteCategoriaVehiculo) {
        List<TrabajoCategoriaPrecio> listaTrabajoCompleto = new ArrayList<>();
        for (GrupoPrecioParteCategoriaVehiculo grupoPrecioParteCategoriaVehiculo : listaGrupoPrecioParteCategoriaVehiculo) {
            List<TrabajoCategoriaPrecio> listaTemporal = conseguirListaTrabajoCategoriaPrecio(grupoPrecioParteCategoriaVehiculo.getGrupoPrecio(), grupoPrecioParteCategoriaVehiculo.getCategoria());
            for (TrabajoCategoriaPrecio trabajoCategoriaPrecio : listaTemporal) {
                listaTrabajoCompleto.add(trabajoCategoriaPrecio);
            }
        }
        return listaTrabajoCompleto;
    }
    
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
                trabajoCategoriaPrecioTmp = (trabajoCategoriaPrecioTmp == null ? new TrabajoCategoriaPrecio() : trabajoCategoriaPrecioTmp);
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
                TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp = this.obtenerPorCategoriaTrabajoGrupoPrecio(categoria, trabajo, grupoPrecio);                
                trabajoCategoriaPrecioTmp = (trabajoCategoriaPrecioTmp == null ? new TrabajoCategoriaPrecio() : trabajoCategoriaPrecioTmp);
                mapaTrabajoCategoriaPrecio.put(clave, trabajoCategoriaPrecioTmp);
            }
            listaTrabajoCategoriaPrecio.add(mapaTrabajoCategoriaPrecio);
        }
        return listaTrabajoCategoriaPrecio;
    }
    
    public List<HashMap<String, Object>> obtenerListaMapaTrabajoCategoriaPrecioYParte(GrupoPrecio grupoPrecio, Parte parte) {
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
                TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp = obtenerPorCodigoYParte(trabajoCategoriaPrecioIdTmp, parte);
                if (trabajoCategoriaPrecioTmp != null) {
                    System.out.println("trabajoCategoriaPrecioTmp: " + trabajoCategoriaPrecioTmp.getPrecioVentaPublico());
                }
                trabajoCategoriaPrecioTmp = (trabajoCategoriaPrecioTmp == null ? new TrabajoCategoriaPrecio() : trabajoCategoriaPrecioTmp);
                mapaTrabajoCategoriaPrecio.put(clave, trabajoCategoriaPrecioTmp);
            }
            listaTrabajoCategoriaPrecio.add(mapaTrabajoCategoriaPrecio);
        }
        return listaTrabajoCategoriaPrecio;
    }
    
    public List<HashMap<String, Object>> obtenerListaMapaTrabajoCategoriaPrecioYParte(GrupoPrecio grupoPrecio, Parte parte, Categoria categoriaTmp) {
        List<HashMap<String, Object>> listaTrabajoCategoriaPrecio = new ArrayList<>();
        List<Trabajo> listaTrabajo = trabajoBean.obtenerListaPorGrupoPrecio(grupoPrecio);
        List<Categoria> listaCategoria = categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecio);
        for (Trabajo trabajo : listaTrabajo) {
            HashMap<String, Object> mapaTrabajoCategoriaPrecio = new HashMap<>();
            mapaTrabajoCategoriaPrecio.put(Constante.TRABAJO_CATEGORIA, trabajo.getDescripcion());
            for (Categoria categoria : listaCategoria) {
                if (categoria.getIdCategoria().equals(categoriaTmp.getIdCategoria())) {
                    String clave = categoria.getCategoria();
                    Long idCategoria = categoria.getIdCategoria();
                    Long idTrabajo = trabajo.getIdTrabajo();
                    Long idGrupoPrecio = grupoPrecio.getIdGrupoPrecio();
                    TrabajoCategoriaPrecioId trabajoCategoriaPrecioIdTmp = new TrabajoCategoriaPrecioId();
                    trabajoCategoriaPrecioIdTmp.setIdCategoria(idCategoria);
                    trabajoCategoriaPrecioIdTmp.setIdTrabajo(idTrabajo);
                    trabajoCategoriaPrecioIdTmp.setIdGrupoPrecio(idGrupoPrecio);
                    TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp = obtenerPorCodigoYParte(trabajoCategoriaPrecioIdTmp, parte);
                    trabajoCategoriaPrecioTmp = (trabajoCategoriaPrecioTmp == null ? new TrabajoCategoriaPrecio() : trabajoCategoriaPrecioTmp);
                    mapaTrabajoCategoriaPrecio.put(clave, trabajoCategoriaPrecioTmp);
                }
            }
            listaTrabajoCategoriaPrecio.add(mapaTrabajoCategoriaPrecio);
        }
        return listaTrabajoCategoriaPrecio;
    }
    
    public List<HashMap<String, Object>> obtenerListaMapaTrabajoCategoriaPrecioYVehiculo(GrupoPrecio grupoPrecio, Vehiculo vehiculo) {
        System.out.println(" obtenerListaMapaTrabajoCategoriaPrecioYVehiculo ");
        List<HashMap<String, Object>> listaTrabajoCategoriaPrecio = new ArrayList<>();
        List<GrupoPrecioParteCategoriaVehiculo> listaGPPCV = grupoPrecioParteCategoriaVehiculoBean.obtenerListaPorVehiculoYCategoria(grupoPrecio, vehiculo);
        System.out.println(" listaGPPCV.size: " + listaGPPCV.size());
        List<Trabajo> listaTrabajo = trabajoBean.obtenerListaPorGrupoPrecio(grupoPrecio);
        List<Categoria> listaCategoria = categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecio);
        if (!listaGPPCV.isEmpty()) {
            System.out.println("entro");
            for (Trabajo trabajo : listaTrabajo) {
                HashMap<String, Object> mapaTrabajoCategoriaPrecio = new HashMap<>();
                mapaTrabajoCategoriaPrecio.put(Constante.TRABAJO_CATEGORIA, trabajo.getDescripcion());
                for (Categoria categoria : listaCategoria) {
                    for (GrupoPrecioParteCategoriaVehiculo grupoPrecioParteCategoriaVehiculo : listaGPPCV) {
                        
                        if (grupoPrecioParteCategoriaVehiculo.getCategoria().getIdCategoria().equals(categoria.getIdCategoria())) {
                            String clave = categoria.getCategoria();
                            Long idCategoria = categoria.getIdCategoria();
                            Long idTrabajo = trabajo.getIdTrabajo();
                            Long idGrupoPrecio = grupoPrecio.getIdGrupoPrecio();
                            TrabajoCategoriaPrecioId trabajoCategoriaPrecioIdTmp = new TrabajoCategoriaPrecioId();
                            trabajoCategoriaPrecioIdTmp.setIdCategoria(idCategoria);
                            trabajoCategoriaPrecioIdTmp.setIdTrabajo(idTrabajo);
                            trabajoCategoriaPrecioIdTmp.setIdGrupoPrecio(idGrupoPrecio);
                            TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp = trabajoCategoriaPrecioDao.obtenerPorCodigo(trabajoCategoriaPrecioIdTmp);
                            if (trabajoCategoriaPrecioTmp != null) {
                                System.out.println("***>: " + trabajoCategoriaPrecioTmp.getPrecioVentaPublico());
                            }
                            trabajoCategoriaPrecioTmp = (trabajoCategoriaPrecioTmp == null ? new TrabajoCategoriaPrecio() : trabajoCategoriaPrecioTmp);
                            mapaTrabajoCategoriaPrecio.put(clave, trabajoCategoriaPrecioTmp);
                        }
                    }
                }
                listaTrabajoCategoriaPrecio.add(mapaTrabajoCategoriaPrecio);
            }
        }
        return listaTrabajoCategoriaPrecio;
    }
    
    public TrabajoCategoriaPrecio obtenerPorCodigoYParte(TrabajoCategoriaPrecioId trabajoCategoriaPrecioIdEntrada, Parte parte) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("trabajoCategoriaPrecioId", trabajoCategoriaPrecioIdEntrada);
        parametros.put("parte", parte);
        List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecio = trabajoCategoriaPrecioDao.obtenerListaPorParametros(parametros);
        if (!listaTrabajoCategoriaPrecio.isEmpty()) {
            return trabajoCategoriaPrecioDao.obtenerListaPorParametros(parametros).get(0);
        }
        return null;
    }
    
    public List<TrabajoCategoriaPrecio> obtenerPorCategoria(Categoria categoria) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("categoria", categoria);
        return trabajoCategoriaPrecioDao.obtenerListaPorParametros(parametros);
    }
    
    public List<TrabajoCategoriaPrecio> obtenerPorGrupoPrecio(GrupoPrecio grupoPrecioEntrada) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("grupoPrecio", grupoPrecioEntrada);
        return trabajoCategoriaPrecioDao.obtenerListaPorParametros(parametros);
    }
    
}
