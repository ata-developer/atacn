/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.FilaBean;
import ec.com.ata.cn.logica.GrupoPrecioBean;
import ec.com.ata.cn.logica.ImagenBean;
import ec.com.ata.cn.logica.MarcaVehiculoBean;
import ec.com.ata.cn.logica.ParametroBean;
import ec.com.ata.cn.logica.ParteBean;
import ec.com.ata.cn.logica.TipoFilaBean;
import ec.com.ata.cn.logica.TrabajoCategoriaPrecioBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.logica.VehiculoImagenBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.logica.util.gestor.UtilGeneral;
import ec.com.ata.cn.modelo.Fila;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.Imagen;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Parte;
import ec.com.ata.cn.modelo.TipoFila;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoImagen;
import ec.com.ata.cn.modelo.VehiculoImagenId;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    private MarcaVehiculo marcaVehiculo;

    private MarcaVehiculo marcaVehiculoSeleccionado;

    private Vehiculo vehiculo;

    private Vehiculo vehiculoSeleccionado;

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

    private List<HashMap<String, Object>> listaMapaTrabajoCategoriaPrecio;

    @PostConstruct
    public void init() {
        setMarcaVehiculo(new MarcaVehiculo());
        setVehiculo(new Vehiculo());
        setListaMarcaVehiculo(marcaVehiculoBean.obtenerLista());
        setTipoFilasAsiento(tipoFilaBean.obtenerLista());
        setAnioDesdeRango(new Integer(parametroBean.obtenerPorCodigo(Constante.ANIO_INICIO_RANGO) == null ? "0" : parametroBean.obtenerPorCodigo(Constante.ANIO_INICIO_RANGO).getValor()));
        setAnioHastaRango(new Integer(parametroBean.obtenerPorCodigo(Constante.ANIO_FIN_RANGO) == null ? "0" : parametroBean.obtenerPorCodigo(Constante.ANIO_FIN_RANGO).getValor()));
        setRangoAnioInicial(new ArrayList<Integer>());
        setRangoAnioFinal(new ArrayList<Integer>());
        setImagenes(new ArrayList<UploadedFile>());
        setImagenesVehiculo(new ArrayList<Imagen>());
        inicializarRangoAnioInicial();
        setContadorImagenes(0);
        setModoEdicion(false);
        setGrupoPrecioSeccionado(new GrupoPrecio());
    }

    public void cargarListaPrecio() {
        try {
            setListaMapaTrabajoCategoriaPrecio(trabajoCategoriaTrabajoBean.obtenerListaMapaTrabajoCategoriaPrecioYParte(grupoPrecioSeccionado,partePrincipalSeleccionada));
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
        this.vehiculoSeleccionado = vehiculoEntrada;
        getListaVehiculoSeleccionado().add(vehiculoEntrada);
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
            vehiculoBean.crear(vehiculo, imagenesVehiculo);
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
            vehiculoBean.actualizar(vehiculo, imagenesVehiculo);
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
}
