/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.CategoriaBean;
import ec.com.ata.cn.logica.FilaBean;
import ec.com.ata.cn.logica.GrupoPrecioBean;
import ec.com.ata.cn.logica.GrupoPrecioParteCategoriaVehiculoBean;
import ec.com.ata.cn.logica.ImagenBean;
import ec.com.ata.cn.logica.MarcaVehiculoBean;
import ec.com.ata.cn.logica.ParametroBean;
import ec.com.ata.cn.logica.ParteBean;
import ec.com.ata.cn.logica.TipoFilaBean;
import ec.com.ata.cn.logica.TrabajoCategoriaPrecioBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.logica.VehiculoCategoriaTrabajoBean;
import ec.com.ata.cn.logica.VehiculoImagenBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.logica.util.gestor.UtilGeneral;
import ec.com.ata.cn.modelo.Categoria;
import ec.com.ata.cn.modelo.Fila;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.GrupoPrecioParteCategoriaVehiculo;
import ec.com.ata.cn.modelo.Imagen;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Parte;
import ec.com.ata.cn.modelo.TipoFila;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoCategoriaTrabajo;
import ec.com.ata.cn.modelo.VehiculoImagen;
import ec.com.ata.cn.modelo.VehiculoImagenId;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.omnifaces.util.selectitems.SelectItemsBuilder;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ATA1
 */
@ViewScoped
@Named
public class VehiculoCategoriaAdministracionControlador extends BaseControlador {

    @Inject
    private CategoriaBean categoriaBean;

    @Inject
    private MarcaVehiculoBean marcaVehiculoBean;

    @Inject
    private VehiculoBean vehiculoBean;

    @Inject
    private VehiculoImagenBean vehiculoImagenBean;

    @Inject
    private TipoFilaBean tipoFilaBean;

    @Inject
    private ParametroBean parametroBean;

    @Inject
    private ImagenBean imagenBean;

    @Inject
    private FilaBean filaBean;

    @Inject
    private GrupoPrecioBean grupoPrecioBean;

    @Inject
    private ParteBean parteBean;

    @Inject
    private TrabajoCategoriaPrecioBean trabajoCategoriaTrabajoBean;

    @Inject
    private GrupoPrecioParteCategoriaVehiculoBean grupoPrecioParteCategoriaVehiculoBean;

    @Inject
    private VehiculoCategoriaTrabajoBean vehiculoCategoriaTrabajoBean;

    private MarcaVehiculo marcaVehiculo;

    private MarcaVehiculo marcaVehiculoSeleccionado;

    private Vehiculo vehiculo;

    private Vehiculo vehiculoSeleccionado;

    private Vehiculo vehiculoSeleccionadoParaCopiar;

    private List<MarcaVehiculo> listaMarcaVehiculo;

    private List<Vehiculo> listaVehiculo;

    private List<Vehiculo> listaVehiculoSeleccionado;

    private List<Fila> filasDelVehiculo;

    private List<TipoFila> tipoFilasAsiento;

    private Integer anioDesdeRango;

    private Integer anioHastaRango;

    private List<Integer> rangoAnioInicial;

    private List<Integer> rangoAnioFinal;

    private List<UploadedFile> imagenes;

    private List<Imagen> imagenesVehiculo;

    private UploadedFile imagen;

    private Integer contadorImagenes;

    private String modelo;

    private Boolean modoEdicion;

    private GrupoPrecio grupoPrecioSeccionado;

    private Parte partePrincipalSeleccionada;

    private List<Categoria> listaCategoriaTmp;

    private List<Categoria> listaCategoria;

    private Categoria categoriaSeleccionado;

    private List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecio;

    private List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado;

    private List<GrupoPrecioParteCategoriaVehiculo> listaGrupoPrecioParteCategoriaVehiculo;

    private List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecio;

    private List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecioParaConFigurar;

    private List<VehiculoCategoriaTrabajo> listaVehiculoCategoriaTrabajo;

    private List<VehiculoCategoriaTrabajo> listaVehiculoCategoriaTrabajoDeAutoCopiado;

    @PostConstruct
    public void init() {

        this.listaCategoriaTmp = new ArrayList<>();

        setMarcaVehiculo(new MarcaVehiculo());
        setVehiculo(new Vehiculo());
        setListaMarcaVehiculo(marcaVehiculoBean.obtenerLista());
        setTipoFilasAsiento(tipoFilaBean.obtenerLista());
        setAnioDesdeRango(new Integer(parametroBean.obtenerPorCodigo(Constante.ANIO_INICIO_RANGO) == null ? "0" : parametroBean.obtenerPorCodigo(Constante.ANIO_INICIO_RANGO).getValor()));
        setAnioHastaRango(new Integer(parametroBean.obtenerPorCodigo(Constante.ANIO_FIN_RANGO) == null ? "0" : parametroBean.obtenerPorCodigo(Constante.ANIO_FIN_RANGO).getValor()));
        setRangoAnioInicial(new ArrayList<>());
        setRangoAnioFinal(new ArrayList<>());
        setImagenes(new ArrayList<>());
        setImagenesVehiculo(new ArrayList<>());
        setListaCategoria(new ArrayList<>());
        setVehiculoSeleccionado(new Vehiculo());
        setListaVehiculoCategoriaTrabajo(new ArrayList<>());
        setListaVehiculoCategoriaTrabajoDeAutoCopiado(new ArrayList<>());

        inicializarRangoAnioInicial();
        setContadorImagenes(0);
        setModoEdicion(false);
        setGrupoPrecioSeccionado(new GrupoPrecio());

        setListaVehiculo(vehiculoBean.obtenerLista());
    }
    
    public void seleccionarTodos () {
        List<VehiculoCategoriaTrabajo> listaVehiculoCategoriaTrabajoTmp1 = this.listaVehiculoCategoriaTrabajo;
        List<VehiculoCategoriaTrabajo> listaVehiculoCategoriaTrabajoTmp2 = new ArrayList<>();
        listaVehiculoCategoriaTrabajoTmp1.stream().map(vehiculoCategoriaTrabajoTmp -> {
            vehiculoCategoriaTrabajoTmp.setSeleccionar(true);
            return vehiculoCategoriaTrabajoTmp;
        }).forEachOrdered(vehiculoCategoriaTrabajoTmp -> {
            listaVehiculoCategoriaTrabajoTmp2.add(vehiculoCategoriaTrabajoTmp);
        });
        this.listaVehiculoCategoriaTrabajo = listaVehiculoCategoriaTrabajoTmp2;
    }

    public void eliminarVehiculoCategoriaTrabajo(VehiculoCategoriaTrabajo vehiculoCategoriaTrabajo) {
        try {
            this.vehiculoCategoriaTrabajoBean.eliminarVehiculoCategoriaTrabajo(vehiculoCategoriaTrabajo);
            this.listaVehiculoCategoriaTrabajo = this.vehiculoCategoriaTrabajoBean.obtenerListaPorVehiculo(vehiculo);
            setListaTrabajoCategoriaPrecioParaConFigurar(this.trabajoCategoriaTrabajoBean.generarListaTrabajoCategoriaPrecioTodosMenosElAuto(vehiculo, grupoPrecioSeccionado));
        } catch (Exception ex) {
            final Throwable root = ExceptionUtils.getRootCause(ex);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + ex.getMessage());
            Logger.getLogger(VehiculoCategoriaAdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Vehiculo> completarAuto(String consulta) {

        List<Vehiculo> listaVehiculos = this.vehiculoBean.obtenerLista();
        return listaVehiculos.stream().filter(t -> t.getDescripcionConMarca().toLowerCase().contains(consulta.toLowerCase())).collect(Collectors.toList());

    }

    public void cargarVehiculoSeleccionado() {
        cargarVehiculoSeleccionadoParaCopiar();
        setListaTrabajoCategoriaPrecioParaConFigurar(this.trabajoCategoriaTrabajoBean.generarListaTrabajoCategoriaPrecioTodosMenosElAuto(vehiculo, grupoPrecioSeccionado));
    }

    public void cargarVehiculoSeleccionadoParaCopiar() {
        this.listaVehiculoSeleccionado = new ArrayList<>();
        this.listaVehiculoSeleccionado.add(vehiculo);
        this.listaVehiculoCategoriaTrabajo = this.vehiculoCategoriaTrabajoBean.obtenerListaPorVehiculo(vehiculo);
    }

    public void cargarPreciosAVehiculo() {
        for (TrabajoCategoriaPrecio trabajoCategoriaPrecioTmp : getListaTrabajoCategoriaPrecioParaConFigurar()) {
            if (trabajoCategoriaPrecioTmp.isSeleccionar()) {

            }
        }
    }

    public List<TrabajoCategoriaPrecio> generarTrabajoCategoriaPrecioParaAuto(GrupoPrecioParteCategoriaVehiculo grupoPrecioParteCategoriaVehiculo) {

        System.out.println("***grupoPrecioParteCategoriaVehiculo:" + grupoPrecioParteCategoriaVehiculo);
        //return trabajoCategoriaTrabajoBean.conseguirListaTrabajoCategoriaPrecio(grupoPrecioParteCategoriaVehiculo.getGrupoPrecio(), grupoPrecioParteCategoriaVehiculo.getCategoria());
        return new ArrayList<>();
    }

    public void eliminarGrupoParteTrabajo(GrupoPrecioParteCategoriaVehiculo grupoPrecioParteCategoriaVehiculo) {
        grupoPrecioParteCategoriaVehiculoBean.eliminar(grupoPrecioParteCategoriaVehiculo);
        setListaMapaTrabajoCategoriaPrecioVehiculoSeleccionado(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecioYVehiculo(grupoPrecioSeccionado, vehiculoSeleccionado));
        setListaGrupoPrecioParteCategoriaVehiculo(generarListaGrupoVehiculo());
        setListaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.generarListaCompletaTrabajoCategoriaPrecioPorVehiculo(getListaGrupoPrecioParteCategoriaVehiculo()));
        System.out.println("eliminarAutoParte");
    }

    public void agregarGrupoTrabajoAVehiculo() {
        System.out.println("agregarGrupoTrabajoAVehiculo");
        try {
            GrupoPrecioParteCategoriaVehiculo grupoPrecioParteCategoriaVehiculo = new GrupoPrecioParteCategoriaVehiculo();
            System.out.println("grupoPrecioSeccionado: " + grupoPrecioSeccionado.getNombre());
            System.out.println("partePrincipalSeleccionada: " + partePrincipalSeleccionada.getParte());
            System.out.println("categoriaSeleccionado: " + categoriaSeleccionado.getCategoria());
            System.out.println("agregarGrupoTrabajoAVehiculo: " + vehiculoSeleccionado.getDescripcionDetallada());

            grupoPrecioParteCategoriaVehiculo.setGrupoPrecio(grupoPrecioSeccionado);
            grupoPrecioParteCategoriaVehiculo.setParte(partePrincipalSeleccionada);
            grupoPrecioParteCategoriaVehiculo.setCategoria(categoriaSeleccionado);
            grupoPrecioParteCategoriaVehiculo.setVehiculo(vehiculoSeleccionado);
            this.grupoPrecioParteCategoriaVehiculoBean.crear(grupoPrecioParteCategoriaVehiculo);
            setListaMapaTrabajoCategoriaPrecioVehiculoSeleccionado(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecioYVehiculo(grupoPrecioSeccionado, vehiculoSeleccionado));
            setListaGrupoPrecioParteCategoriaVehiculo(generarListaGrupoVehiculo());
            setListaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.generarListaCompletaTrabajoCategoriaPrecioPorVehiculo(getListaGrupoPrecioParteCategoriaVehiculo()));
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
            grupoPrecioSeccionado = null;
            partePrincipalSeleccionada = null;
            categoriaSeleccionado = null;

        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_PRECIOS_CONTROLADOR_GUARDAR_ROOT + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_PRECIOS_CONTROLADOR_GUARDAR_EX + ":" + e.getMessage());
        } finally {

        }
    }

    public List<SelectItem> generarSelectItemDeCategorias() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Categoria categoriaTmp : listaCategoria) {
            selectItemsBuilder.add(categoriaTmp, categoriaTmp.getCategoria());
        }
        return selectItemsBuilder.buildList();
    }

    public void cargarListaCategoria() {

        System.out.println("cargarListaCategoria: " + grupoPrecioSeccionado.getNombre());

        setListaCategoria(categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecioSeccionado));
        System.out.println("listaCategoria.size: " + listaCategoria.size());
        System.out.println("listaCategoria: " + listaCategoria);
        System.out.println("grupoPrecioSeccionado: " + grupoPrecioSeccionado);
        System.out.println("vehiculoSeleccionado: " + vehiculoSeleccionado);
        setListaMapaTrabajoCategoriaPrecioVehiculoSeleccionado(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecioYVehiculo(grupoPrecioSeccionado, vehiculoSeleccionado));
        setListaGrupoPrecioParteCategoriaVehiculo(generarListaGrupoVehiculo());
        setListaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.generarListaCompletaTrabajoCategoriaPrecioPorVehiculo(getListaGrupoPrecioParteCategoriaVehiculo()));

    }

    public void consultarListaTrabajo() {
    }

    public List<Categoria> listaCategoriasTemporal() {

        listaCategoriaTmp = new ArrayList<>();
        Categoria categoriaTmp = new Categoria();
        categoriaTmp.setCategoria(Constante.TRABAJO_CATEGORIA);
        listaCategoriaTmp.add(categoriaTmp);
        listaCategoriaTmp.add(categoriaSeleccionado);
        return listaCategoriaTmp;
    }

    public List<Categoria> listaCategoriasTodas() {

        listaCategoriaTmp = new ArrayList<>();
        Categoria categoriaTmp = new Categoria();
        categoriaTmp.setCategoria(Constante.TRABAJO_CATEGORIA);
        listaCategoriaTmp.add(categoriaTmp);
        listaCategoriaTmp.addAll(categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecioSeccionado));
        return listaCategoriaTmp;
    }

    public List<Categoria> listaCategoriasPorGrupoPrecioYVehiculo() {

        listaCategoriaTmp = new ArrayList<>();
        Categoria categoriaTmp = new Categoria();
        categoriaTmp.setCategoria(Constante.TRABAJO_CATEGORIA);
        listaCategoriaTmp.add(categoriaTmp);
        listaCategoriaTmp.addAll(categoriaBean.obtenerListaPorGrupoPrecioYVehiculo(grupoPrecioSeccionado, vehiculoSeleccionado));
        return listaCategoriaTmp;
    }

    public List<GrupoPrecioParteCategoriaVehiculo> generarListaGrupoVehiculo() {
        List<GrupoPrecioParteCategoriaVehiculo> listaTemp = grupoPrecioParteCategoriaVehiculoBean.obtenerListaPorVehiculoYCategoria(grupoPrecioSeccionado, vehiculoSeleccionado);
        System.out.println("tamaño listaTemp: " + listaTemp.size());
        return listaTemp;
    }

    public List<SelectItem> generarSelectItemDeCategoriasTodas() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Categoria categoriaTmp : categoriaBean.obtenerListaPorGrupoPrecio(grupoPrecioSeccionado)) {
            selectItemsBuilder.add(categoriaTmp, categoriaTmp.getCategoria());
        }
        return selectItemsBuilder.buildList();
    }

    public void cargarListaPrecio() {
        try {
            System.out.println("grupoPrecioSeccionado: " + grupoPrecioSeccionado.getNombre());
            System.out.println("partePrincipalSeleccionada: " + partePrincipalSeleccionada.getParte());
            setListaMapaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecioYParte(grupoPrecioSeccionado, partePrincipalSeleccionada, categoriaSeleccionado));
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_PRECIOS_CONTROLADOR_GUARDAR_ROOT + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_PRECIOS_CONTROLADOR_GUARDAR_EX + ":" + e.getMessage());
        } finally {

        }
    }

    public List<SelectItem> generarSelectItemDePartesPrincipales() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Parte parteTmp : parteBean.obtenerListaPorPadre(null)) {
            selectItemsBuilder.add(parteTmp, parteTmp.getParte());
        }
        return selectItemsBuilder.buildList();
    }

    public void eliminarImagen(Long imagenId) {
        try {
            VehiculoImagenId vehiculoImagenId = new VehiculoImagenId();
            vehiculoImagenId.setIdImagen(imagenId);
            vehiculoImagenId.setIdVehiculo(vehiculo.getIdVehiculo());
            VehiculoImagen vehiculoImagenTmp = new VehiculoImagen();
            vehiculoImagenTmp.setVehiculoImagenId(vehiculoImagenId);
            if (null != vehiculoImagenBean.obtenerPorId(vehiculoImagenId)) {
                vehiculoImagenBean.eliminar(vehiculoImagenTmp);
            }
            imagenBean.eliminar(imagenId);
            this.imagenesVehiculo = vehiculoImagenBean.obtenerListaPorVehiculo(vehiculo);
            setListaVehiculo(vehiculoBean.obtenerListaPorMarca(marcaVehiculoSeleccionado));
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            e.printStackTrace();
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public List<SelectItem> generarSelectItemDeGrupoPrecio() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (GrupoPrecio grupoPrecioTmp : grupoPrecioBean.obtenerLista()) {
            selectItemsBuilder.add(grupoPrecioTmp, grupoPrecioTmp.getNombre());
        }
        return selectItemsBuilder.buildList();
    }

    public void seleccionarVehiculo(Vehiculo vehiculoEntrada) {
        setListaVehiculoSeleccionado(new ArrayList<Vehiculo>());
        setListaMapaTrabajoCategoriaPrecioVehiculoSeleccionado(new ArrayList<HashMap<String, Object>>());
        this.vehiculoSeleccionado = vehiculoEntrada;
        setGrupoPrecioSeccionado(new GrupoPrecio());
        setPartePrincipalSeleccionada(new Parte());
        setCategoriaSeleccionado(new Categoria());
        getListaVehiculoSeleccionado().add(vehiculoEntrada);
        setListaTrabajoCategoriaPrecio(new ArrayList<TrabajoCategoriaPrecio>());

    }

    public List<Fila> listaFila(Vehiculo vehiculo) {
        return filaBean.obtenerListaPorVehiculo(vehiculo);
    }

    public List<Imagen> listaImagenes(Vehiculo vehiculoEntrada) {
        return vehiculoImagenBean.obtenerListaPorVehiculo(vehiculoEntrada);
    }

    public List<String> autoCompletar(String consulta) {
        System.out.println("modelo: " + consulta);
        return vehiculoBean.obtenerModeloListaPorMarcaYPorModeloLike(marcaVehiculoSeleccionado, consulta);
    }

    public void cargarImagen() {
        try {

            //System.out.println("-->"+new String(UtilGeneral.ImagenAByte(event.getFile())));
            Imagen imagenTmp = new Imagen();
            imagenTmp.setNombre(imagen.getFileName());
            imagenTmp.setDatosImagen(UtilGeneral.ImagenAByte(imagen));
            imagenBean.guardar(imagenTmp);
            getImagenesVehiculo().add(imagenTmp);
            addInfoMessage(Constante.EXITO, "probando");
        } catch (Exception e) {
            e.printStackTrace();
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public StreamedContent procesarImagen(Imagen imagenEntrada) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
                return new DefaultStreamedContent();
            } else {
                return new DefaultStreamedContent(new ByteArrayInputStream(imagenEntrada.getDatosImagen()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new DefaultStreamedContent();
        }

    }

    public void manejadorCargarArchivo(FileUploadEvent event) {
        try {
            System.out.println("nombre: " + event.getFile().getFileName());
            //System.out.println("-->"+new String(UtilGeneral.ImagenAByte(event.getFile())));

            Imagen imagenTmp = new Imagen();
            imagenTmp.setNombre(event.getFile().getFileName());
            imagenTmp.setDatosImagen(UtilGeneral.ImagenAByte(event.getFile()));
            imagenTmp.setTienePadre(false);
            Imagen imagenTmp2 = imagenBean.guardar(imagenTmp);
            imagenesVehiculo.add(imagenTmp2);
            addInfoMessage(Constante.EXITO, "probando");
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }
    }

    public void configurarRangoAnioFinal() {
        setRangoAnioFinal(new ArrayList<Integer>());
        for (int i = vehiculo.getAnioDesde().intValue(); i <= getAnioHastaRango(); i++) {
            getRangoAnioFinal().add(i);
        }
        vehiculo.setTipoRango(Constante.SUPERIOR);
    }

    public void configurarRango() {
        vehiculo.setTipoRango(Constante.RANGO);
    }

    private void inicializarRangoAnioInicial() {
        for (int i = getAnioDesdeRango(); i <= getAnioHastaRango(); i++) {
            getRangoAnioInicial().add(i);
        }
    }

    public List<SelectItem> generarSelectItemDeTipoFilaDeAsiento() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (TipoFila tipoFilaAsientoTmp : getTipoFilasAsiento()) {
            selectItemsBuilder.add(tipoFilaAsientoTmp, tipoFilaAsientoTmp.getTipoFila());
        }
        return selectItemsBuilder.buildList();
    }

    public List<TipoFila> obtenerTodosTipoFila() {
        return tipoFilaBean.obtenerLista();
    }

    public List<SelectItem> generarSelectAnioInicial() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();

        for (TipoFila tipoFilaAsientoTmp : getTipoFilasAsiento()) {
            selectItemsBuilder.add(tipoFilaAsientoTmp, tipoFilaAsientoTmp.getTipoFila());
        }
        return selectItemsBuilder.buildList();
    }

    public void generarFilas() {
        System.err.println("--- generarFilas --");
        List<Fila> listaFilasTemp = new ArrayList<>();
        for (int i = 0; i < vehiculo.getNumeroDeFilas(); i++) {
            listaFilasTemp.add(new Fila());
        }
        setFilasDelVehiculo(listaFilasTemp);
    }

    public void cargarListaDeVehiculos() {
        setListaVehiculo(vehiculoBean.obtenerListaPorMarca(marcaVehiculoSeleccionado));
    }

    public List<SelectItem> generarSelectItemDeMarcaVehiculo() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (MarcaVehiculo marcaVehiculoTmp : getListaMarcaVehiculo()) {
            selectItemsBuilder.add(marcaVehiculoTmp, marcaVehiculoTmp.getMarca());
        }
        return selectItemsBuilder.buildList();
    }

    public void guadarMarcaVehiculo() {
        try {
            marcaVehiculo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            marcaVehiculoBean.crear(marcaVehiculo);
            setListaMarcaVehiculo(marcaVehiculoBean.obtenerLista());
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            marcaVehiculo = new MarcaVehiculo();
        }
    }

    public void guadarVehiculo() {
        if (modoEdicion) {
            modificar();
        } else {
            guardar();
        }
    }

    public void guardarProductosSeleccionados() {
        try {
            this.vehiculoCategoriaTrabajoBean.crearListaVehiculoCategoriaTrabajo(listaTrabajoCategoriaPrecioParaConFigurar, vehiculo);
            this.listaVehiculoCategoriaTrabajo = this.vehiculoCategoriaTrabajoBean.obtenerListaPorVehiculo(vehiculo);
            setListaTrabajoCategoriaPrecioParaConFigurar(this.trabajoCategoriaTrabajoBean.generarListaTrabajoCategoriaPrecioTodosMenosElAuto(vehiculo, grupoPrecioSeccionado));
        } catch (Exception ex) {
            final Throwable root = ExceptionUtils.getRootCause(ex);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + ex.getMessage());
            Logger.getLogger(VehiculoCategoriaAdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void copiarCategoriaProductosAOtro() {
        try {
            for (VehiculoCategoriaTrabajo vehiculoCategoriaTrabajoTmp : this.listaVehiculoCategoriaTrabajo) {
                if (vehiculoCategoriaTrabajoTmp.isSeleccionar()) {
                    VehiculoCategoriaTrabajo vehiculoCategoriaTrabajoTmp2 = new VehiculoCategoriaTrabajo();
                    vehiculoCategoriaTrabajoTmp2.setVehiculo(vehiculoSeleccionadoParaCopiar);
                    vehiculoCategoriaTrabajoTmp2.setTrabajoCategoriaPrecio(vehiculoCategoriaTrabajoTmp.getTrabajoCategoriaPrecio());
                    vehiculoCategoriaTrabajoBean.crear(vehiculoCategoriaTrabajoTmp2);
                }
            }
            this.listaVehiculoCategoriaTrabajoDeAutoCopiado = vehiculoCategoriaTrabajoBean.obtenerListaPorVehiculo(vehiculoSeleccionadoParaCopiar);
            
        } catch (Exception ex) {
            final Throwable root = ExceptionUtils.getRootCause(ex);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + ex.getMessage());
            Logger.getLogger(VehiculoCategoriaAdministracionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardar() {
        if (imagenesVehiculo.isEmpty()) {
            addErrorMessage(Constante.ERROR, Constante.SIN_IMAGENES);
            return;
        }
        try {
            vehiculo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            vehiculo.setMarca(marcaVehiculoSeleccionado);
            vehiculo.setFilasDeAsientos(filasDelVehiculo);
            System.out.println("vehiculo: " + vehiculo.toString());
            //vehiculoBean.crear(vehiculo, imagenesVehiculo);
            setListaVehiculo(vehiculoBean.obtenerListaPorMarca(marcaVehiculoSeleccionado));
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            limpiarValoresDespuesDeGuardar();
        }
    }

    public void modificar() {
        if (imagenesVehiculo.isEmpty()) {
            addErrorMessage(Constante.ERROR, Constante.SIN_IMAGENES);
            return;
        }
        try {
            vehiculo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            vehiculo.setMarca(marcaVehiculoSeleccionado);
            vehiculo.setFilasDeAsientos(filasDelVehiculo);
            System.out.println("vehiculo modificar: " + vehiculo.toString());
            vehiculoBean.actualizar(vehiculo);
            setListaVehiculo(vehiculoBean.obtenerListaPorMarca(marcaVehiculoSeleccionado));
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            e.printStackTrace();
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        } finally {
            limpiarValoresDespuesDeGuardar();
        }
    }

    public void limpiarValoresDespuesDeGuardar() {
        this.vehiculo = new Vehiculo();
        this.modoEdicion = false;
        this.imagenesVehiculo = new ArrayList<>();
        this.filasDelVehiculo = new ArrayList<>();
    }

    /**
     * @return the marcaVehiculo
     */
    public MarcaVehiculo getMarcaVehiculo() {
        return marcaVehiculo;
    }

    /**
     * @param marcaVehiculo the marcaVehiculo to set
     */
    public void setMarcaVehiculo(MarcaVehiculo marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

    /**
     * @return the listaMarcaVehiculo
     */
    public List<MarcaVehiculo> getListaMarcaVehiculo() {
        return listaMarcaVehiculo;
    }

    /**
     * @param listaMarcaVehiculo the listaMarcaVehiculo to set
     */
    public void setListaMarcaVehiculo(List<MarcaVehiculo> listaMarcaVehiculo) {
        this.listaMarcaVehiculo = listaMarcaVehiculo;
    }

    /**
     * @return the marcaVehiculoSeleccionado
     */
    public MarcaVehiculo getMarcaVehiculoSeleccionado() {
        return marcaVehiculoSeleccionado;
    }

    /**
     * @param marcaVehiculoSeleccionado the marcaVehiculoSeleccionado to set
     */
    public void setMarcaVehiculoSeleccionado(MarcaVehiculo marcaVehiculoSeleccionado) {
        this.marcaVehiculoSeleccionado = marcaVehiculoSeleccionado;
    }

    /**
     * @return the listaVehiculo
     */
    public List<Vehiculo> getListaVehiculo() {
        return listaVehiculo;
    }

    /**
     * @param listaVehiculo the listaVehiculo to set
     */
    public void setListaVehiculo(List<Vehiculo> listaVehiculo) {
        this.listaVehiculo = listaVehiculo;
    }

    /**
     * @return the vehiculo
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the vehiculoSeleccionado
     */
    public Vehiculo getVehiculoSeleccionado() {
        return vehiculoSeleccionado;
    }

    /**
     * @param vehiculoSeleccionado the vehiculoSeleccionado to set
     */
    public void setVehiculoSeleccionado(Vehiculo vehiculoSeleccionado) {
        this.vehiculoSeleccionado = vehiculoSeleccionado;
    }

    /**
     * @return the filasDelVehiculo
     */
    public List<Fila> getFilasDelVehiculo() {
        return filasDelVehiculo;
    }

    /**
     * @param filasDelVehiculo the filasDelVehiculo to set
     */
    public void setFilasDelVehiculo(List<Fila> filasDelVehiculo) {
        this.filasDelVehiculo = filasDelVehiculo;
    }

    /**
     * @return the tipoFilasAsiento
     */
    public List<TipoFila> getTipoFilasAsiento() {
        return tipoFilasAsiento;
    }

    /**
     * @param tipoFilasAsiento the tipoFilasAsiento to set
     */
    public void setTipoFilasAsiento(List<TipoFila> tipoFilasAsiento) {
        this.tipoFilasAsiento = tipoFilasAsiento;
    }

    /**
     * @return the anioDesdeRango
     */
    public Integer getAnioDesdeRango() {
        return anioDesdeRango;
    }

    /**
     * @param anioDesdeRango the anioDesdeRango to set
     */
    public void setAnioDesdeRango(Integer anioDesdeRango) {
        this.anioDesdeRango = anioDesdeRango;
    }

    /**
     * @return the anioHastaRango
     */
    public Integer getAnioHastaRango() {
        return anioHastaRango;
    }

    /**
     * @param anioHastaRango the anioHastaRango to set
     */
    public void setAnioHastaRango(Integer anioHastaRango) {
        this.anioHastaRango = anioHastaRango;
    }

    /**
     * @return the rangoAnioInicial
     */
    public List<Integer> getRangoAnioInicial() {
        return rangoAnioInicial;
    }

    /**
     * @param rangoAnioInicial the rangoAnioInicial to set
     */
    public void setRangoAnioInicial(List<Integer> rangoAnioInicial) {
        this.rangoAnioInicial = rangoAnioInicial;
    }

    /**
     * @return the rangoAnioFinal
     */
    public List<Integer> getRangoAnioFinal() {
        return rangoAnioFinal;
    }

    /**
     * @param rangoAnioFinal the rangoAnioFinal to set
     */
    public void setRangoAnioFinal(List<Integer> rangoAnioFinal) {
        this.rangoAnioFinal = rangoAnioFinal;
    }

    /**
     * @return the imagenes
     */
    public List<UploadedFile> getImagenes() {
        return imagenes;
    }

    /**
     * @param imagenes the imagenes to set
     */
    public void setImagenes(List<UploadedFile> imagenes) {
        this.imagenes = imagenes;
    }

    /**
     * @return the imagenesVehiculo
     */
    public List<Imagen> getImagenesVehiculo() {
        return imagenesVehiculo;
    }

    /**
     * @param imagenesVehiculo the imagenesVehiculo to set
     */
    public void setImagenesVehiculo(List<Imagen> imagenesVehiculo) {
        this.imagenesVehiculo = imagenesVehiculo;
    }

    /**
     * @return the imagen
     */
    public UploadedFile getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the contadorImagenes
     */
    public Integer getContadorImagenes() {
        return contadorImagenes;
    }

    /**
     * @param contadorImagenes the contadorImagenes to set
     */
    public void setContadorImagenes(Integer contadorImagenes) {
        this.contadorImagenes = contadorImagenes;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the modoEdicion
     */
    public Boolean getModoEdicion() {
        return modoEdicion;
    }

    /**
     * @param modoEdicion the modoEdicion to set
     */
    public void setModoEdicion(Boolean modoEdicion) {
        this.modoEdicion = modoEdicion;
    }

    /**
     * @return the listaVehiculoSeleccionado
     */
    public List<Vehiculo> getListaVehiculoSeleccionado() {
        return listaVehiculoSeleccionado;
    }

    /**
     * @param listaVehiculoSeleccionado the listaVehiculoSeleccionado to set
     */
    public void setListaVehiculoSeleccionado(List<Vehiculo> listaVehiculoSeleccionado) {
        this.listaVehiculoSeleccionado = listaVehiculoSeleccionado;
    }

    /**
     * @return the grupoPrecioSeccionado
     */
    public GrupoPrecio getGrupoPrecioSeccionado() {
        return grupoPrecioSeccionado;
    }

    /**
     * @param grupoPrecioSeccionado the grupoPrecioSeccionado to set
     */
    public void setGrupoPrecioSeccionado(GrupoPrecio grupoPrecioSeccionado) {
        this.grupoPrecioSeccionado = grupoPrecioSeccionado;
    }

    /**
     * @return the partePrincipalSeleccionada
     */
    public Parte getPartePrincipalSeleccionada() {
        return partePrincipalSeleccionada;
    }

    /**
     * @param partePrincipalSeleccionada the partePrincipalSeleccionada to set
     */
    public void setPartePrincipalSeleccionada(Parte partePrincipalSeleccionada) {
        this.partePrincipalSeleccionada = partePrincipalSeleccionada;
    }

    /**
     * @return the listaMapaTrabajoCategoriaPrecio
     */
    public List<HashMap<String, Object>> getListaMapaTrabajoCategoriaPrecio() {
        return listaMapaTrabajoCategoriaPrecio;
    }

    /**
     * @param listaMapaTrabajoCategoriaPrecio the
     * listaMapaTrabajoCategoriaPrecio to set
     */
    public void setListaMapaTrabajoCategoriaPrecio(List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecio) {
        this.listaMapaTrabajoCategoriaPrecio = listaMapaTrabajoCategoriaPrecio;
    }

    /**
     * @return the listaCategoria
     */
    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    /**
     * @param listaCategoria the listaCategoria to set
     */
    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    /**
     * @return the listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado
     */
    public List<HashMap<String, Object>> getListaMapaTrabajoCategoriaPrecioVehiculoSeleccionado() {
        return listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado;
    }

    /**
     * @param listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado the
     * listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado to set
     */
    public void setListaMapaTrabajoCategoriaPrecioVehiculoSeleccionado(List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado) {
        this.listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado = listaMapaTrabajoCategoriaPrecioVehiculoSeleccionado;
    }

    /**
     * @return the listaGrupoPrecioParteCategoriaVehiculo
     */
    public List<GrupoPrecioParteCategoriaVehiculo> getListaGrupoPrecioParteCategoriaVehiculo() {
        return listaGrupoPrecioParteCategoriaVehiculo;
    }

    /**
     * @param listaGrupoPrecioParteCategoriaVehiculo the
     * listaGrupoPrecioParteCategoriaVehiculo to set
     */
    public void setListaGrupoPrecioParteCategoriaVehiculo(List<GrupoPrecioParteCategoriaVehiculo> listaGrupoPrecioParteCategoriaVehiculo) {
        this.listaGrupoPrecioParteCategoriaVehiculo = listaGrupoPrecioParteCategoriaVehiculo;
    }

    /**
     * @return the listaTrabajoCategoriaPrecio
     */
    public List<TrabajoCategoriaPrecio> getListaTrabajoCategoriaPrecio() {
        return listaTrabajoCategoriaPrecio;
    }

    /**
     * @param listaTrabajoCategoriaPrecio the listaTrabajoCategoriaPrecio to set
     */
    public void setListaTrabajoCategoriaPrecio(List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecio) {
        this.listaTrabajoCategoriaPrecio = listaTrabajoCategoriaPrecio;
    }

    /**
     * @return the categoriaSeleccionado
     */
    public Categoria getCategoriaSeleccionado() {
        return categoriaSeleccionado;
    }

    /**
     * @param categoriaSeleccionado the categoriaSeleccionado to set
     */
    public void setCategoriaSeleccionado(Categoria categoriaSeleccionado) {
        this.categoriaSeleccionado = categoriaSeleccionado;
    }

    /**
     * @return the listaTrabajoCategoriaPrecioParaConFigurar
     */
    public List<TrabajoCategoriaPrecio> getListaTrabajoCategoriaPrecioParaConFigurar() {
        return listaTrabajoCategoriaPrecioParaConFigurar;
    }

    /**
     * @param listaTrabajoCategoriaPrecioParaConFigurar the
     * listaTrabajoCategoriaPrecioParaConFigurar to set
     */
    public void setListaTrabajoCategoriaPrecioParaConFigurar(List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecioParaConFigurar) {
        this.listaTrabajoCategoriaPrecioParaConFigurar = listaTrabajoCategoriaPrecioParaConFigurar;
    }

    /**
     * @return the listaVehiculoCategoriaTrabajo
     */
    public List<VehiculoCategoriaTrabajo> getListaVehiculoCategoriaTrabajo() {
        return listaVehiculoCategoriaTrabajo;
    }

    /**
     * @param listaVehiculoCategoriaTrabajo the listaVehiculoCategoriaTrabajo to
     * set
     */
    public void setListaVehiculoCategoriaTrabajo(List<VehiculoCategoriaTrabajo> listaVehiculoCategoriaTrabajo) {
        this.listaVehiculoCategoriaTrabajo = listaVehiculoCategoriaTrabajo;
    }

    /**
     * @return the vehiculoSeleccionadoParaCopiar
     */
    public Vehiculo getVehiculoSeleccionadoParaCopiar() {
        return vehiculoSeleccionadoParaCopiar;
    }

    /**
     * @param vehiculoSeleccionadoParaCopiar the vehiculoSeleccionadoParaCopiar
     * to set
     */
    public void setVehiculoSeleccionadoParaCopiar(Vehiculo vehiculoSeleccionadoParaCopiar) {
        this.vehiculoSeleccionadoParaCopiar = vehiculoSeleccionadoParaCopiar;
    }

    /**
     * @return the listaVehiculoCategoriaTrabajoDeAutoCopiado
     */
    public List<VehiculoCategoriaTrabajo> getListaVehiculoCategoriaTrabajoDeAutoCopiado() {
        return listaVehiculoCategoriaTrabajoDeAutoCopiado;
    }

    /**
     * @param listaVehiculoCategoriaTrabajoDeAutoCopiado the
     * listaVehiculoCategoriaTrabajoDeAutoCopiado to set
     */
    public void setListaVehiculoCategoriaTrabajoDeAutoCopiado(List<VehiculoCategoriaTrabajo> listaVehiculoCategoriaTrabajoDeAutoCopiado) {
        this.listaVehiculoCategoriaTrabajoDeAutoCopiado = listaVehiculoCategoriaTrabajoDeAutoCopiado;
    }

}
