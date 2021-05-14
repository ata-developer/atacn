/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.FilaBean;
import ec.com.ata.cn.logica.ImagenBean;
import ec.com.ata.cn.logica.MarcaVehiculoBean;
import ec.com.ata.cn.logica.ParametroBean;
import ec.com.ata.cn.logica.ParteBean;
import ec.com.ata.cn.logica.PlantillaBean;
import ec.com.ata.cn.logica.TipoFilaBean;
import ec.com.ata.cn.logica.VehiculoBean;
import ec.com.ata.cn.logica.VehiculoCategoriaTrabajoBean;
import ec.com.ata.cn.logica.VehiculoImagenBean;
import ec.com.ata.cn.logica.VehiculoParteBean;
import ec.com.ata.cn.logica.util.gestor.Constante;
import ec.com.ata.cn.logica.util.gestor.UtilGeneral;
import ec.com.ata.cn.modelo.Fila;
import ec.com.ata.cn.modelo.GrupoPrecio;
import ec.com.ata.cn.modelo.Imagen;
import ec.com.ata.cn.modelo.MarcaVehiculo;
import ec.com.ata.cn.modelo.Parte;
import ec.com.ata.cn.modelo.Plantilla;
import ec.com.ata.cn.modelo.TipoFila;
import ec.com.ata.cn.modelo.Usuario;
import ec.com.ata.cn.modelo.Vehiculo;
import ec.com.ata.cn.modelo.VehiculoCategoriaTrabajo;
import ec.com.ata.cn.modelo.VehiculoImagen;
import ec.com.ata.cn.modelo.VehiculoImagenId;
import ec.com.ata.cn.modelo.VehiculoParte;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.omnifaces.util.selectitems.SelectItemsBuilder;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ATA1
 */
@Named
@SessionScoped
public class VehiculoAdministracionControlador extends BaseControlador {

   

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
    private ParteBean parteBean;

    @Inject
    private PlantillaBean plantillaBean;

    @Inject
    private VehiculoParteBean vehiculoParteBean;

    @Inject
    private VehiculoCategoriaTrabajoBean vehiculoCategoriaTrabajoBean;

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

    private Parte partePadre;

    private List<VehiculoParte> listaVehiculoParte;

    private List<Plantilla> listaPlantilla;

    private Parte parteHijo;

    private List<Parte> listaPartePadre;

    private List<Vehiculo> listaVehiculosFiltrados;

    private GrupoPrecio grupoPrecioSeccionado;

    private List<VehiculoCategoriaTrabajo> listaVehiculoCategoriaTrabajo;

    private String consultaVehiculo;

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
        setListaVehiculoParte(new ArrayList<VehiculoParte>());
        setListaPlantilla(new ArrayList<Plantilla>());
        setListaPartePadre(parteBean.obtenerListaPorPadreItNull());

    }

    public void consultarAuto() {
        this.listaVehiculo = new ArrayList<>();
        
        if (this.consultaVehiculo.length() < 2){
            this.listaVehiculo = null;
            return;
        }

        this.listaVehiculo = this.vehiculoBean.obtenerLista();
        this.listaVehiculo = this.listaVehiculo.stream().filter(t -> t.getDescripcionConMarca().toLowerCase().contains(this.consultaVehiculo.trim().toLowerCase())).collect(Collectors.toList());
        if (null != this.listaVehiculoSeleccionado && !this.listaVehiculoSeleccionado.isEmpty() ){
            this.listaVehiculoSeleccionado = new ArrayList<>();
        }
        if (null != this.listaVehiculoCategoriaTrabajo && !this.listaVehiculoCategoriaTrabajo.isEmpty() ){
            this.listaVehiculoCategoriaTrabajo = new ArrayList<>();
        }

    }

    public List<Vehiculo> completarAuto(String consulta) {

        List<Vehiculo> listaVehiculos = this.vehiculoBean.obtenerLista();
        return listaVehiculos.stream().filter(t -> t.getDescripcionConMarca().toLowerCase().contains(consulta.toLowerCase())).collect(Collectors.toList());

    }

    public void consultarListaVehiculoCategoriaTrabajo() {
        this.setListaVehiculoCategoriaTrabajo(this.vehiculoCategoriaTrabajoBean.obtenerListaPorVehiculo(this.vehiculoSeleccionado));

    }

    public List<Vehiculo> obtenerTodaLalistaDeAutos() {
        return vehiculoBean.obtenerLista();
    }

    public String generarDatosPlantilla(Vehiculo vehiculoEntrada) {
        String resultado = "";
        List<Plantilla> listaPlantillaTmp = plantillaBean.obtenerListaPorVehiculo(vehiculoEntrada);
        for (Plantilla plantilla : listaPlantillaTmp) {
            resultado = resultado.concat(plantilla.getTipo()).concat("-->").concat(plantilla.getExiste() ? "Si" : "No").concat(", ");
        }
        return resultado;
    }

    public boolean presentarBotonBorra(Parte partePadre) {
        int cantidad = 0;
        for (VehiculoParte vehiculoParte : this.listaVehiculoParte) {
            if (vehiculoParte.getParte().getPadre().equals(partePadre)) {
                cantidad++;
            }
        }

        return cantidad > 1;
    }

    public String nombreLista(Parte parte) {
        return "listaPartes" + parte.getCodigo();
    }

    public void imprimirLista() {
        for (VehiculoParte vehiculoParte : listaVehiculoParte) {
            System.out.println("lista parte:" + vehiculoParte.getParte().getParte());
        }
    }

    public void onRowReorder(ReorderEvent event) {

        List<VehiculoParte> list = new ArrayList<>(listaVehiculoParte);

        int fromIndex = event.getFromIndex();
        System.out.println("fromIndex: ----> " + fromIndex);
        int toIndex = event.getToIndex();
        System.out.println("toIndex: ----> " + toIndex);
        if (toIndex >= fromIndex) {
            Collections.rotate(list.subList(fromIndex, toIndex + 1), -1);
        } else {
            Collections.rotate(list.subList(toIndex, fromIndex + 1), 1);
        }
        listaVehiculoParte.clear();
        listaVehiculoParte.addAll(list);

        for (VehiculoParte vehiculoParte : list) {
            System.out.println("parte: " + vehiculoParte.getParte().getParte());
        }
        System.out.println("--------------------------");
        for (VehiculoParte vehiculoParte : listaVehiculoParte) {
            System.out.println("parte: " + vehiculoParte.getParte().getParte());
        }

    }

    public Boolean evaluarIndexBajar(VehiculoParte vehiculoParteEntrada) {
        int index = this.listaVehiculoParte.indexOf(vehiculoParteEntrada);
        if ((index + 1) != this.listaVehiculoParte.size()) {
            String padreA = vehiculoParteEntrada.getParte().getPadre().getParte();
            Parte pabreB = this.listaVehiculoParte.get(index + 1).getParte().getPadre();
            String padreBString = pabreB.getParte();
            return !padreA.equals(padreBString);
        }
        return true;
    }

    public Boolean evaluarIndexSubir(VehiculoParte vehiculoParteEntrada) {
        int index = this.listaVehiculoParte.indexOf(vehiculoParteEntrada);
        if (index != 0) {
            String padreA = vehiculoParteEntrada.getParte().getPadre().getParte();
            Parte pabreB = this.listaVehiculoParte.get(index - 1).getParte().getPadre();
            String padreBString = pabreB.getParte();
            return !padreA.equals(padreBString);
        }
        return true;
    }

    public void bajarVehiculoParteHijo(VehiculoParte vehiculoParteDeArriba) {
        if (modoEdicion) {
            try {
                int index = this.listaVehiculoParte.indexOf(vehiculoParteDeArriba);
                VehiculoParte vehiculoParteDeAbajo = this.listaVehiculoParte.get(index + 1);

                VehiculoParte vehiculoParteDeArribaTmp = new VehiculoParte();
                vehiculoParteDeArribaTmp.setDisposicion(vehiculoParteDeArriba.getDisposicion());
                vehiculoParteDeArribaTmp.setParte(vehiculoParteDeArriba.getParte());
                vehiculoParteDeArribaTmp.setVehiculo(vehiculoParteDeArriba.getVehiculo());

                vehiculoParteDeArriba.setDisposicion(vehiculoParteDeAbajo.getDisposicion());
                vehiculoParteDeArriba.setParte(vehiculoParteDeAbajo.getParte());
                vehiculoParteDeArriba.setVehiculo(vehiculoParteDeAbajo.getVehiculo());
                HttpSession session = (HttpSession) this.getHttpSession();
                Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
                String documento = usuariotmp.getNumeroDocumento();
                vehiculoParteDeArriba.getGenericoEntidad().setDocumentoUsuario(documento);
                vehiculoParteDeArriba.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));

                vehiculoParteBean.modificar(vehiculoParteDeArriba);

                vehiculoParteDeAbajo.setDisposicion(vehiculoParteDeArribaTmp.getDisposicion());
                vehiculoParteDeAbajo.setParte(vehiculoParteDeArribaTmp.getParte());
                vehiculoParteDeAbajo.setVehiculo(vehiculoParteDeArribaTmp.getVehiculo());

                vehiculoParteDeArriba.getGenericoEntidad().setDocumentoUsuario(documento);
                vehiculoParteDeArriba.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));

                vehiculoParteBean.modificar(vehiculoParteDeAbajo);

                this.listaVehiculoParte = vehiculoParteBean.obtenerListaPorVehiculo(vehiculo);
                addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
            } catch (Exception e) {
                final Throwable root = ExceptionUtils.getRootCause(e);
                if (null != root) {
                    addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                    return;
                }
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
            }

        } else {
            int index = this.listaVehiculoParte.indexOf(vehiculoParteDeArriba);
            VehiculoParte vehiculoParteDeAbajo = this.listaVehiculoParte.get(index + 1);

            VehiculoParte vehiculoParteDeArribaTmp = new VehiculoParte();
            vehiculoParteDeArribaTmp.setDisposicion(vehiculoParteDeArriba.getDisposicion());
            vehiculoParteDeArribaTmp.setParte(vehiculoParteDeArriba.getParte());
            vehiculoParteDeArribaTmp.setVehiculo(vehiculoParteDeArriba.getVehiculo());
            HttpSession session = (HttpSession) this.getHttpSession();
            Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
            String documento = usuariotmp.getNumeroDocumento();
            vehiculoParteDeArriba.getGenericoEntidad().setDocumentoUsuario(documento);
            vehiculoParteDeArriba.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));

            vehiculoParteDeArriba.setDisposicion(vehiculoParteDeAbajo.getDisposicion());
            vehiculoParteDeArriba.setParte(vehiculoParteDeAbajo.getParte());
            vehiculoParteDeArriba.setVehiculo(vehiculoParteDeAbajo.getVehiculo());

            vehiculoParteDeAbajo.setDisposicion(vehiculoParteDeArribaTmp.getDisposicion());
            vehiculoParteDeAbajo.setParte(vehiculoParteDeArribaTmp.getParte());
            vehiculoParteDeAbajo.setVehiculo(vehiculoParteDeArribaTmp.getVehiculo());
            vehiculoParteDeAbajo.getGenericoEntidad().setDocumentoUsuario(documento);
            vehiculoParteDeAbajo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));

            this.listaVehiculoParte.set(index + 1, vehiculoParteDeAbajo);
            this.listaVehiculoParte.set(index, vehiculoParteDeArriba);

        }
    }

    public void subirVehiculoParteHijo(VehiculoParte vehiculoParteDeArriba) {
        if (modoEdicion) {

            try {
                int index = this.listaVehiculoParte.indexOf(vehiculoParteDeArriba);
                VehiculoParte vehiculoParteDeAbajo = this.listaVehiculoParte.get(index - 1);

                VehiculoParte vehiculoParteDeArribaTmp = new VehiculoParte();
                vehiculoParteDeArribaTmp.setDisposicion(vehiculoParteDeArriba.getDisposicion());
                vehiculoParteDeArribaTmp.setParte(vehiculoParteDeArriba.getParte());
                vehiculoParteDeArribaTmp.setVehiculo(vehiculoParteDeArriba.getVehiculo());

                vehiculoParteDeArriba.setDisposicion(vehiculoParteDeAbajo.getDisposicion());
                vehiculoParteDeArriba.setParte(vehiculoParteDeAbajo.getParte());
                vehiculoParteDeArriba.setVehiculo(vehiculoParteDeAbajo.getVehiculo());
                HttpSession session = (HttpSession) this.getHttpSession();
                Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
                String documento = usuariotmp.getNumeroDocumento();
                vehiculoParteDeArriba.getGenericoEntidad().setDocumentoUsuario(documento);
                vehiculoParteDeArriba.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));

                vehiculoParteBean.modificar(vehiculoParteDeArriba);

                vehiculoParteDeAbajo.setDisposicion(vehiculoParteDeArribaTmp.getDisposicion());
                vehiculoParteDeAbajo.setParte(vehiculoParteDeArribaTmp.getParte());
                vehiculoParteDeAbajo.setVehiculo(vehiculoParteDeArribaTmp.getVehiculo());

                vehiculoParteDeAbajo.getGenericoEntidad().setDocumentoUsuario(documento);
                vehiculoParteDeAbajo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));

                vehiculoParteBean.modificar(vehiculoParteDeAbajo);

                this.listaVehiculoParte = vehiculoParteBean.obtenerListaPorVehiculo(vehiculo);
                addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
            } catch (Exception e) {
                final Throwable root = ExceptionUtils.getRootCause(e);
                if (null != root) {
                    addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                    return;
                }
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
            }

        } else {
            int index = this.listaVehiculoParte.indexOf(vehiculoParteDeArriba);
            VehiculoParte vehiculoParteDeAbajo = this.listaVehiculoParte.get(index - 1);

            VehiculoParte vehiculoParteDeArribaTmp = new VehiculoParte();
            vehiculoParteDeArribaTmp.setDisposicion(vehiculoParteDeArriba.getDisposicion());
            vehiculoParteDeArribaTmp.setParte(vehiculoParteDeArriba.getParte());
            vehiculoParteDeArribaTmp.setVehiculo(vehiculoParteDeArriba.getVehiculo());

            vehiculoParteDeArriba.setDisposicion(vehiculoParteDeAbajo.getDisposicion());
            vehiculoParteDeArriba.setParte(vehiculoParteDeAbajo.getParte());
            vehiculoParteDeArriba.setVehiculo(vehiculoParteDeAbajo.getVehiculo());

            vehiculoParteDeAbajo.setDisposicion(vehiculoParteDeArribaTmp.getDisposicion());
            vehiculoParteDeAbajo.setParte(vehiculoParteDeArribaTmp.getParte());
            vehiculoParteDeAbajo.setVehiculo(vehiculoParteDeArribaTmp.getVehiculo());

            this.listaVehiculoParte.set(index - 1, vehiculoParteDeAbajo);
            this.listaVehiculoParte.set(index, vehiculoParteDeArriba);

        }
    }

    public void eliminarVehiculoParteHijo(VehiculoParte vehiculoParteEntrada) {
        if (null == vehiculoParteEntrada.getIdVehiculoParte()) {
            int indice = this.listaVehiculoParte.indexOf(vehiculoParteEntrada);
            this.listaVehiculoParte.remove(indice);
        } else {
            try {
                vehiculoParteBean.eliminar(vehiculoParteEntrada.getIdVehiculoParte());
                this.listaVehiculoParte = vehiculoParteBean.obtenerListaPorVehiculo(vehiculoParteEntrada.getVehiculo());
                addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
            } catch (Exception e) {
                final Throwable root = ExceptionUtils.getRootCause(e);
                if (null != root) {
                    addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                    return;
                }
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
            }
        }

    }

    public void agregarVehiculoParteHijo(Parte parteHijo) {
        if (modoEdicion) {
            try {
                VehiculoParte vehiculoParteTmp = new VehiculoParte();
                vehiculoParteTmp.setParte(parteHijo);
                vehiculoParteTmp.setVehiculo(vehiculo);
                vehiculoParteBean.crear(vehiculoParteTmp);
                this.listaVehiculoParte = vehiculoParteBean.obtenerListaPorVehiculo(vehiculo);
                addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
            } catch (Exception e) {
                final Throwable root = ExceptionUtils.getRootCause(e);
                if (null != root) {
                    addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                    return;
                }
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
            }
        } else {
            VehiculoParte vehiculoParteTmp = new VehiculoParte();
            vehiculoParteTmp.setParte(parteHijo);
            this.listaVehiculoParte.add(vehiculoParteTmp);
        }
    }

    public void enEditarPlantilla(RowEditEvent event) {
        try {
            System.out.println("Plantilla");
            Plantilla plantillaTmp = (Plantilla) event.getObject();
            HttpSession session = (HttpSession) this.getHttpSession();
            Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
            String documento = usuariotmp.getNumeroDocumento();
            plantillaTmp.getGenericoEntidad().setDocumentoUsuario(documento);
            plantillaTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            if (modoEdicion) {
                plantillaBean.modificar(plantillaTmp);
                //this.listaPlantilla = plantillaBean.obtenerListaPorVehiculo(plantillaTmp.getVehiculo());
            }
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void enCancelarPlantilla(RowEditEvent event) {

    }

    public void enEditarMarcaVehiculo(RowEditEvent event) {
        try {
            MarcaVehiculo marcaVehiculoTmp = (MarcaVehiculo) event.getObject();
            HttpSession session = (HttpSession) this.getHttpSession();
            Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
            String documento = usuariotmp.getNumeroDocumento();
            marcaVehiculoTmp.getGenericoEntidad().setDocumentoUsuario(documento);
            marcaVehiculoTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            marcaVehiculoBean.modificar(marcaVehiculoTmp);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void enCancelarMarcaVehiculo(RowEditEvent event) {

    }

    public void enEditarVehiculoParte(RowEditEvent event) {
        try {
            VehiculoParte vehiculoParteTmp = (VehiculoParte) event.getObject();
            if (modoEdicion) {
                vehiculoParteBean.modificar(vehiculoParteTmp);
            }
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
        }

    }

    public void enCancelarVehiculoParte(RowEditEvent event) {

    }

    private List<Plantilla> generarListaInicialPlantillas() {
        List<Plantilla> listaPlantillaTmp = new ArrayList<>();
        Plantilla plantillaForro = new Plantilla();
        plantillaForro.setExiste(false);
        plantillaForro.setTipo("FORRO");
        plantillaForro.setNumeroPiezas(0);
        plantillaForro.setObservacion("");
        HttpSession session = (HttpSession) this.getHttpSession();
        Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
        String documento = usuariotmp.getNumeroDocumento();
        plantillaForro.getGenericoEntidad().setDocumentoUsuario(documento);
        plantillaForro.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        listaPlantillaTmp.add(plantillaForro);

        Plantilla plantillaVolanteEstandar = new Plantilla();
        plantillaVolanteEstandar.setExiste(false);
        plantillaVolanteEstandar.setTipo("VOLANTE ESTANDAR");
        plantillaVolanteEstandar.setNumeroPiezas(0);
        plantillaVolanteEstandar.setObservacion("");
        plantillaVolanteEstandar.getGenericoEntidad().setDocumentoUsuario(documento);
        plantillaVolanteEstandar.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        listaPlantillaTmp.add(plantillaVolanteEstandar);

        Plantilla plantillaVolanteDeportivo = new Plantilla();
        plantillaVolanteDeportivo.setExiste(false);
        plantillaVolanteDeportivo.setTipo("VOLANTE DEPORTIVO");
        plantillaVolanteDeportivo.setNumeroPiezas(0);
        plantillaVolanteDeportivo.setObservacion("");
        plantillaVolanteDeportivo.getGenericoEntidad().setDocumentoUsuario(documento);
        plantillaVolanteDeportivo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        listaPlantillaTmp.add(plantillaVolanteDeportivo);

        Plantilla plantillaVolantePiso = new Plantilla();
        plantillaVolantePiso.setExiste(false);
        plantillaVolantePiso.setTipo("PISO");
        plantillaVolantePiso.setNumeroPiezas(0);
        plantillaVolantePiso.setObservacion("");
        plantillaVolantePiso.getGenericoEntidad().setDocumentoUsuario(documento);
        plantillaVolantePiso.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        listaPlantillaTmp.add(plantillaVolantePiso);

        return listaPlantillaTmp;
    }

    private List<VehiculoParte> generarListaInicialPartes() {

        HttpSession session = (HttpSession) this.getHttpSession();
        Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
        String documento = usuariotmp.getNumeroDocumento();

        List<VehiculoParte> listaVehiculoParteTmp = new ArrayList<>();
        Parte parteFilaPadre = parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_FILA_1);
        List<Parte> listaPartesFilaTmp = parteBean.obtenerListaPorPadre(parteFilaPadre);
        for (Parte parteTmp : listaPartesFilaTmp) {
            VehiculoParte vehiculoParteTmp = new VehiculoParte();
            vehiculoParteTmp.setParte(parteTmp);
            vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
            vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            listaVehiculoParteTmp.add(vehiculoParteTmp);
        }

        //VOLANTE
        VehiculoParte vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_MODELO_VOLANTE));
        vehiculoParteTmp.setDisposicion(1l);
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_HILO_SUPERIOR));
        vehiculoParteTmp.setDisposicion(2l);
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_HILO_CIERRA));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(3l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_HILO_INFERIOR));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(4l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_VOLANTE));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(5l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        //PISO
        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_PISO));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(1l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        //TECHO
        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_TECHO));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(1l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        //PUERTA
        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_PUERTA));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(1l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        //CAJUELA
        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_CAJUELA));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(1l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        //POMO
        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_POMO));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(1l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        //CAPUCHON
        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_CAPUCHON));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(1l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        //MOQUETA
        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_MOQUETA));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(1l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        //FRENO
        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_FRENO));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(1l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        //OTRO
        vehiculoParteTmp = new VehiculoParte();
        vehiculoParteTmp.setVehiculo(new Vehiculo());
        vehiculoParteTmp.setParte(parteBean.obtenerPorCodigoPersonalizado(Constante.CODIGO_CUERPO_OTRO));
        vehiculoParteTmp.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculoParteTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoParteTmp.setDisposicion(1l);
        listaVehiculoParteTmp.add(vehiculoParteTmp);

        return listaVehiculoParteTmp;
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
            //setListaVehiculo(vehiculoBean.obtenerListaPorMarca(marcaVehiculoSeleccionado));
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

    public String agregarVehiculo() {
        setModoEdicion(false);
        this.vehiculo = new Vehiculo();
        this.vehiculo.setEstadoPlantilla("SIN REVISAR");
        this.vehiculo.setEstadoPlantillaVolante("SIN REVISAR");
        this.imagenesVehiculo = new ArrayList<>();
        this.filasDelVehiculo = new ArrayList<>();
        this.listaVehiculoParte = this.generarListaInicialPartes();
        this.listaPlantilla = this.generarListaInicialPlantillas();
        return "actualizar-editar-vehiculo";

    }

    /*public String agregarVehiculo() {
        setModoEdicion(true);
        this.vehiculo = new Vehiculo();
        this.vehiculo.setEstadoPlantilla("SIN REVISAR");
        this.vehiculo.setEstadoPlantillaVolante("SIN REVISAR");
        this.imagenesVehiculo = new ArrayList<>();
        this.filasDelVehiculo = new ArrayList<>();
        this.listaVehiculoParte = this.generarListaInicialPartes();
        this.listaPlantilla = this.generarListaInicialPlantillas();
        try {
            vehiculo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            vehiculo.setMarca(marcaVehiculoSeleccionado);
            vehiculoBean.crear(
                    vehiculo,
                    imagenesVehiculo,
                    listaPlantilla,
                    listaVehiculoParte);
            addInfoMessage(Constante.EXITO, Constante.EXITO_DETALLE);
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            e.printStackTrace();
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return "administrar-vehiculo";
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
            return "administrar-vehiculo";
        }
        return "actualizar-editar-vehiculo";
    }*/
    public void seleccionarVehiculo(Vehiculo vehiculoEntrada) {
        System.out.println("entro al seleccionarVehiculo" + vehiculoEntrada);
        setModoEdicion(true);
        this.vehiculo = vehiculoEntrada;
        configurarRangoAnioFinal();
        this.imagenesVehiculo = vehiculoImagenBean.obtenerListaPorVehiculo(vehiculo);
        this.filasDelVehiculo = filaBean.obtenerListaPorVehiculo(vehiculo);
        this.listaVehiculoParte = this.vehiculoParteBean.obtenerListaPorVehiculo(vehiculoEntrada);
        this.listaPlantilla = this.plantillaBean.obtenerListaPorVehiculo(vehiculoEntrada);
    }

    public void seleccionarVehiculoParaConsulta(Vehiculo vehiculoEntrada) {
        this.listaVehiculoSeleccionado = new ArrayList<>();
        this.vehiculoSeleccionado = vehiculoEntrada;
        this.listaVehiculoSeleccionado.add(vehiculoSeleccionado);
        this.setListaVehiculoCategoriaTrabajo(this.vehiculoCategoriaTrabajoBean.obtenerListaPorVehiculo(this.vehiculoSeleccionado));

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
        HttpSession session = (HttpSession) this.getHttpSession();
        Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
        String documento = usuariotmp.getNumeroDocumento();
        if (modoEdicion) {
            try {
                System.out.println("nombre: " + event.getFile().getFileName());

                Imagen imagenTmp = new Imagen();
                imagenTmp.setNombre(event.getFile().getFileName());
                imagenTmp.setDatosImagen(UtilGeneral.ImagenAByte(event.getFile()));
                imagenTmp.setTienePadre(true);
                imagenTmp.getGenericoEntidad().setDocumentoUsuario(documento);
                imagenTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
                Imagen imagenTmp2 = imagenBean.guardar(imagenTmp);
                VehiculoImagen vehiculoImagen = new VehiculoImagen();
                vehiculoImagen.setImagen(imagenTmp2);
                vehiculoImagen.setVehiculo(vehiculo);
                vehiculoImagen.getGenericoEntidad().setDocumentoUsuario(documento);
                vehiculoImagen.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
                vehiculoImagenBean.crear(vehiculoImagen);
                this.imagenesVehiculo = vehiculoImagenBean.obtenerListaPorVehiculo(vehiculo);
                addInfoMessage(Constante.EXITO, "probando");
            } catch (Exception e) {
                final Throwable root = ExceptionUtils.getRootCause(e);
                e.printStackTrace();
                if (null != root) {
                    addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                    return;
                }
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
            }
        } else {
            try {
                System.out.println("nombre: " + event.getFile().getFileName());
                Imagen imagenTmp = new Imagen();
                imagenTmp.setNombre(event.getFile().getFileName());
                imagenTmp.setDatosImagen(UtilGeneral.ImagenAByte(event.getFile()));
                imagenTmp.setTienePadre(false);
                imagenTmp.getGenericoEntidad().setDocumentoUsuario(documento);
                imagenTmp.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
                Imagen imagenTmp2 = imagenBean.guardar(imagenTmp);
                imagenesVehiculo.add(imagenTmp2);
                addInfoMessage(Constante.EXITO, "probando");
            } catch (Exception e) {
                final Throwable root = ExceptionUtils.getRootCause(e);
                e.printStackTrace();
                if (null != root) {
                    addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                    return;
                }
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
            }
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
            HttpSession session = (HttpSession) this.getHttpSession();
            Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
            String documento = usuariotmp.getNumeroDocumento();
            marcaVehiculo.getGenericoEntidad().setDocumentoUsuario(documento);
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

    public String guadarVehiculo() {
        try {
            if (modoEdicion) {
                modificar();
                modoEdicion = false;
            } else {
                guardar();
                modoEdicion = false;
            }
            limpiarValoresDespuesDeGuardar();
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return null;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
            return null;
        }
        return "administrar-vehiculo";
    }

    public String regresar() {
        if (imagenesVehiculo.isEmpty()) {
            addErrorMessage(Constante.ERROR, Constante.SIN_IMAGENES);
            return null;
        }
        if (listaVehiculoParte.isEmpty()) {
            addErrorMessage(Constante.ERROR, Constante.SIN_PARTES);
            return null;
        }
        try {
            if (modoEdicion) {
                modificar();
                modoEdicion = false;
            } else {
                guardar();
                modoEdicion = false;
            }
        } catch (Exception e) {
            final Throwable root = ExceptionUtils.getRootCause(e);
            if (null != root) {
                addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + root.getMessage());
                return null;
            }
            addErrorMessage(Constante.ERROR, Constante.ERROR_TRABAJO_CONTROLADOR_CARGAR_PRECIO + ":" + e.getMessage());
            return null;
        }
        setListaVehiculo(vehiculoBean.obtenerListaPorMarca(marcaVehiculoSeleccionado));
        return "administrar-vehiculo";
    }

    public void guardar() throws Exception {
        if (imagenesVehiculo.isEmpty()) {
            addErrorMessage(Constante.ERROR, Constante.SIN_IMAGENES);
            throw new Exception(Constante.ERROR + ' ' + Constante.SIN_IMAGENES);
        }

        if (listaVehiculoParte.isEmpty()) {
            addErrorMessage(Constante.ERROR, Constante.SIN_PARTES);
            throw new Exception(Constante.ERROR + ' ' + Constante.SIN_IMAGENES);
        }
        try {
            HttpSession session = (HttpSession) this.getHttpSession();
            Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
            String documento = usuariotmp.getNumeroDocumento();
            vehiculo.getGenericoEntidad().setDocumentoUsuario(documento);
            vehiculo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
            vehiculo.setMarca(marcaVehiculoSeleccionado);
            vehiculoBean.crear(
                    vehiculo,
                    imagenesVehiculo,
                    listaPlantilla,
                    listaVehiculoParte);
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

        }
    }

    public void modificar() throws Exception {
        if (imagenesVehiculo.isEmpty()) {
            addErrorMessage(Constante.ERROR, Constante.SIN_IMAGENES);
            throw new Exception(Constante.ERROR + ' ' + Constante.SIN_IMAGENES);
        }

        if (listaVehiculoParte.isEmpty()) {
            addErrorMessage(Constante.ERROR, Constante.SIN_PARTES);
            throw new Exception(Constante.ERROR + ' ' + Constante.SIN_IMAGENES);
        }

        HttpSession session = (HttpSession) this.getHttpSession();
        Usuario usuariotmp = (Usuario) session.getAttribute("usuario");
        String documento = usuariotmp.getNumeroDocumento();
        vehiculo.getGenericoEntidad().setDocumentoUsuario(documento);
        vehiculo.getGenericoEntidad().setFechaRegistro(new Date(System.currentTimeMillis()));
        vehiculoBean.actualizar(vehiculo);
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
     * @return the partePadre
     */
    public Parte getPartePadre() {
        return partePadre;
    }

    /**
     * @param partePadre the partePadre to set
     */
    public void setPartePadre(Parte partePadre) {
        this.partePadre = partePadre;
    }

    /**
     * @return the listaPlantilla
     */
    public List<Plantilla> getListaPlantilla() {
        return listaPlantilla;
    }

    /**
     * @param listaPlantilla the listaPlantilla to set
     */
    public void setListaPlantilla(List<Plantilla> listaPlantilla) {
        this.listaPlantilla = listaPlantilla;
    }

    /**
     * @return the listaVehiculoParte
     */
    public List<VehiculoParte> getListaVehiculoParte() {
        return listaVehiculoParte;
    }

    /**
     * @param listaVehiculoParte the listaVehiculoParte to set
     */
    public void setListaVehiculoParte(List<VehiculoParte> listaVehiculoParte) {
        this.listaVehiculoParte = listaVehiculoParte;
    }

    /**
     * @return the parteHijo
     */
    public Parte getParteHijo() {
        return parteHijo;
    }

    /**
     * @param parteHijo the parteHijo to set
     */
    public void setParteHijo(Parte parteHijo) {
        this.parteHijo = parteHijo;
    }

    /**
     * @return the listaPartePadre
     */
    public List<Parte> getListaPartePadre() {
        return listaPartePadre;
    }

    /**
     * @param listaPartePadre the listaPartePadre to set
     */
    public void setListaPartePadre(List<Parte> listaPartePadre) {
        this.listaPartePadre = listaPartePadre;
    }

    /**
     * @return the listaVehiculosFiltrados
     */
    public List<Vehiculo> getListaVehiculosFiltrados() {
        return listaVehiculosFiltrados;
    }

    /**
     * @param listaVehiculosFiltrados the listaVehiculosFiltrados to set
     */
    public void setListaVehiculosFiltrados(List<Vehiculo> listaVehiculosFiltrados) {
        this.listaVehiculosFiltrados = listaVehiculosFiltrados;
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
     * @return the consultaVehiculo
     */
    public String getConsultaVehiculo() {
        return consultaVehiculo;
    }

    /**
     * @param consultaVehiculo the consultaVehiculo to set
     */
    public void setConsultaVehiculo(String consultaVehiculo) {
        this.consultaVehiculo = consultaVehiculo;
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
}
