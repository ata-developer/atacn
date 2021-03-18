/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.EquipoBean;
import ec.com.ata.cn.logica.EstablecimientoBean;
import ec.com.ata.cn.logica.HorarioBean;
import ec.com.ata.cn.logica.ParqueaderoBean;
import ec.com.ata.cn.logica.ParteBean;
import ec.com.ata.cn.logica.PeriodoBean;
import ec.com.ata.cn.logica.PeriodoEstablecimientoBean;
import ec.com.ata.cn.logica.RolBean;
import ec.com.ata.cn.logica.RolUrlBean;
import ec.com.ata.cn.logica.UrlBean;
import ec.com.ata.cn.logica.UsuarioBean;
import ec.com.ata.cn.modelo.Equipo;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.Horario;
import ec.com.ata.cn.modelo.HorarioParqueadero;
import ec.com.ata.cn.modelo.OrdenVehiculo;
import ec.com.ata.cn.modelo.Parqueadero;
import ec.com.ata.cn.modelo.Parte;
import ec.com.ata.cn.modelo.Periodo;
import ec.com.ata.cn.modelo.PeriodoEstablecimiento;
import ec.com.ata.cn.modelo.Rol;
import ec.com.ata.cn.modelo.TrabajoCategoriaPrecio;
import ec.com.ata.cn.modelo.TrabajoParte;
import ec.com.ata.cn.modelo.Url;
import ec.com.ata.cn.modelo.Usuario;
import ec.com.ata.cn.modelo.VehiculoTrabajo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.omnifaces.util.selectitems.SelectItemsBuilder;

/**
 *
 * @author ATA1
 */
@ViewScoped
@Named
public class ConsultaControlador extends BaseControlador {
    
    @Inject
    private PeriodoBean periodoBean;

    @Inject
    private HorarioBean horarioBean;

    @Inject
    private EstablecimientoBean establecimientoBean;

    @Inject
    private PeriodoEstablecimientoBean periodoEstablecimientoBean;

    @Inject
    private EquipoBean equipoBean;
    
    @Inject
    private ParqueaderoBean parqueaderoBean;
    
    @Inject
    private ParteBean parteBean;
    
    @Inject
    private RolBean rolBean;
    
    @Inject
    private UsuarioBean usuarioBean;
    
    @Inject
    private UrlBean urlBean;
    
    

    private List<SelectItem> selectItemDias;

    private List<SelectItem> selectItemMeses;
    
    private String seleccionConsulta;
    
    private List<Usuario> listaClientes;

    @PostConstruct
    public void init() {
        selectItemDias = generarDias();
        selectItemMeses = generarMeses();
    }
    
    public List<SelectItem> generarSelectItemDeUrlPadre() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Url urlTmp : urlBean.obtenerListaPorPadreItNull()) {
            selectItemsBuilder.add(urlTmp, urlTmp.getCabecera());
        }
        return selectItemsBuilder.buildList();
    }

    public String obtenerEstilo(HorarioParqueadero horarioParqueadero) {
        if (null == horarioParqueadero.getVehiculoTrabajo()) {
            return "#435A5F";
        } else {            
            return "#"+horarioParqueadero.getVehiculoTrabajo().getEquipo().getColor();
        }
    }
    
    public List<Usuario> autoCompletar(String consulta) {
        System.out.println("seleccion consulta: " + getSeleccionConsulta());
        listaClientes = usuarioBean.obtenerModeloListaPorNumeroDocumentoLike(consulta);
        return listaClientes;
    }

    private List<SelectItem> generarMeses() {

        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();

        selectItemsBuilder.add(0L, getBundle("enero"));
        selectItemsBuilder.add(1L, getBundle("febrero"));
        selectItemsBuilder.add(2L, getBundle("marzo"));
        selectItemsBuilder.add(3L, getBundle("abril"));
        selectItemsBuilder.add(4L, getBundle("mayo"));
        selectItemsBuilder.add(5L, getBundle("junio"));
        selectItemsBuilder.add(6L, getBundle("julio"));
        selectItemsBuilder.add(7L, getBundle("agosto"));
        selectItemsBuilder.add(8L, getBundle("septiembre"));
        selectItemsBuilder.add(9L, getBundle("octubre"));
        selectItemsBuilder.add(10L, getBundle("noviembre"));
        selectItemsBuilder.add(11L, getBundle("diciembre"));

        return selectItemsBuilder.buildList();
    }

    private List<SelectItem> generarDias() {

        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();

        selectItemsBuilder.add(1L, getBundle("domingo"));
        selectItemsBuilder.add(2L, getBundle("lunes"));
        selectItemsBuilder.add(3L, getBundle("martes"));
        selectItemsBuilder.add(4L, getBundle("miercoles"));
        selectItemsBuilder.add(5L, getBundle("jueves"));
        selectItemsBuilder.add(6L, getBundle("viernes"));
        selectItemsBuilder.add(7L, getBundle("sabado"));

        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarOrigen() {

        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();

        selectItemsBuilder.add(getBundle("referencia"), getBundle("referencia"));
        selectItemsBuilder.add(getBundle("google"), getBundle("google"));
        selectItemsBuilder.add(getBundle("twitter"), getBundle("twitter"));
        selectItemsBuilder.add(getBundle("instagram"), getBundle("instagram"));
        selectItemsBuilder.add(getBundle("facebook"), getBundle("facebook"));
        
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarTipoPago() {

        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();

        selectItemsBuilder.add("EFECTIVO", getBundle("pagoEfectivo"));
        selectItemsBuilder.add("TRANSFERENCIA", getBundle("pagoTransferencia"));
        selectItemsBuilder.add("TARJETA", getBundle("pagoTarjeta"));

        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSiNo() {

        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();

        selectItemsBuilder.add(Boolean.TRUE, getBundle("si"));
        selectItemsBuilder.add(Boolean.FALSE, getBundle("no"));

        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarEstadoPlantilla() {

        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();

        selectItemsBuilder.add("SIN REVISAR", getBundle("sinRevisar"));
        selectItemsBuilder.add("REVISADO", getBundle("revisado"));

        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeTrabajoParte(TrabajoCategoriaPrecio trabajoCategoriaPrecioEntrada) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("padre", trabajoCategoriaPrecioEntrada.getParte());
        List<Parte> listaPartesTrabajo = parteBean.obtenerListaPorParametros(parametros);
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Parte parteTmp2 : listaPartesTrabajo) {
            TrabajoParte trabajoParteTmp = new TrabajoParte();
            trabajoParteTmp.setPartePadre(trabajoCategoriaPrecioEntrada.getParte());
            trabajoParteTmp.setParte(parteTmp2);
            selectItemsBuilder.add(trabajoParteTmp, parteTmp2.getParte());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeEquipos() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Equipo equipoTmp : equipoBean.obtenerLista()) {
            selectItemsBuilder.add(equipoTmp, equipoTmp.getEquipo());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDeUrl() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Url urlTmp : urlBean.obtenerLista()) {
            selectItemsBuilder.add(urlTmp, (urlTmp.getCabecera() == null ? "" : urlTmp.getCabecera()).concat(" - ").concat(urlTmp.getDescripcion()));
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDeRol() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Rol rolTmp : rolBean.obtenerLista()) {
            selectItemsBuilder.add(rolTmp, rolTmp.getRol());
        }
        return selectItemsBuilder.buildList();
    }
    
   
    public List<SelectItem> generarSelectItemDePartesPricinpales() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Equipo equipoTmp : equipoBean.obtenerLista()) {
            selectItemsBuilder.add(equipoTmp, equipoTmp.getEquipo());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDePartesPadre() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Parte parteTmp : parteBean.obtenerListaPorPadres()) {
            selectItemsBuilder.add(parteTmp, parteTmp.getParte());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDePartesPorPadre(Parte padre) {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Parte parteTmp : parteBean.obtenerListaPorPadre(padre)) {
            selectItemsBuilder.add(parteTmp, parteTmp.getParte());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDeParqueaderos() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Parqueadero parqueaderoTmp : parqueaderoBean.obtenerLista()) {
            selectItemsBuilder.add(parqueaderoTmp, parqueaderoTmp.getParqueadero());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDePeriodosPorEstablecimiento(Establecimiento establecimientoEntrada) {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("establecimiento", establecimientoEntrada);
        for (PeriodoEstablecimiento periodoEstablecimientoTmp : periodoEstablecimientoBean.obtenerListaPorParametros(parametros)) {
            selectItemsBuilder.add(periodoEstablecimientoTmp.getPeriodo(), periodoEstablecimientoTmp.getPeriodo().getPeriodo());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeVehiculoTrabajo(List<VehiculoTrabajo> listaVehiculoTrabajoEntrada) {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        if (listaVehiculoTrabajoEntrada == null) {
            listaVehiculoTrabajoEntrada = new ArrayList<>();
        }
        for (VehiculoTrabajo vehiculoTrabajoTmp : listaVehiculoTrabajoEntrada) {
            selectItemsBuilder.add(vehiculoTrabajoTmp, vehiculoTrabajoTmp.getVehiculoDescripcion() + " " + vehiculoTrabajoTmp.getTrabajoDescripcion());
        }
        return selectItemsBuilder.buildList();
    }
    
    public List<SelectItem> generarSelectItemDeVehiculoTrabajoMapa(HashMap< OrdenVehiculo, List<VehiculoTrabajo>> mapaOrdenVehiculoTrabajo) {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (OrdenVehiculo ordenVehiculoTmo : mapaOrdenVehiculoTrabajo.keySet()) {
            List<VehiculoTrabajo> listaVehiculoTrabajo = mapaOrdenVehiculoTrabajo.get(ordenVehiculoTmo);
            for (VehiculoTrabajo vehiculoTrabajoTmp : listaVehiculoTrabajo) {
                selectItemsBuilder.add(vehiculoTrabajoTmp, vehiculoTrabajoTmp.getVehiculoDescripcion() + " " + vehiculoTrabajoTmp.getTrabajoDescripcion());
            }
            
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDePeriodos() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Periodo periodoTmp : periodoBean.obtenerLista()) {
            selectItemsBuilder.add(periodoTmp, periodoTmp.getPeriodo());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDePeriodos(List<PeriodoEstablecimiento> listaPeriodoEstablecimientoEntrada) {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        if (listaPeriodoEstablecimientoEntrada == null) {
            listaPeriodoEstablecimientoEntrada = new ArrayList<>();
        }
        for (PeriodoEstablecimiento periodoEstablecimientoTmp : listaPeriodoEstablecimientoEntrada) {
            selectItemsBuilder.add(periodoEstablecimientoTmp.getPeriodo(), periodoEstablecimientoTmp.getPeriodo().getPeriodo());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeHorarios() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Horario horarioTmp : horarioBean.obtenerLista()) {
            selectItemsBuilder.add(horarioTmp, horarioTmp.getHorario());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeEstablecimientos() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Establecimiento establecimientoTmp : establecimientoBean.obtenerLista()) {
            selectItemsBuilder.add(establecimientoTmp, establecimientoTmp.getNombre());
        }
        return selectItemsBuilder.buildList();
    }

    public List<SelectItem> generarSelectItemDeMeses() {
        return selectItemMeses;
    }

    public List<SelectItem> generarSelectItemDeDias() {
        return selectItemDias;
    }
    
    /**
     * @return the seleccionConsulta
     */
    public String getSeleccionConsulta() {
        return seleccionConsulta;
    }

    /**
     * @param seleccionConsulta the seleccionConsulta to set
     */
    public void setSeleccionConsulta(String seleccionConsulta) {
        this.seleccionConsulta = seleccionConsulta;
    }

}
