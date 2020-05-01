/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.controlador.util.Utilitario;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.HorarioParqueadero;
import ec.com.ata.cn.modelo.PEFHorario;
import ec.com.ata.cn.modelo.Parqueadero;
import ec.com.ata.cn.modelo.Periodo;
import ec.com.ata.cn.modelo.PeriodoEstablecimientoFecha;
import ec.com.ata.cn.modelo.PeriodoHorario;
import ec.com.ata.cn.modelo.VehiculoTrabajo;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ATA1
 */
@Stateless
public class ProcesarHorarioBean {

    @Inject
    private PeriodoHorarioBean periodoHorarioBean;

    @Inject
    private PeriodoEstablecimientoFechaBean periodoEstablecimientoFechaBean;

    @Inject
    private PEFHorarioBean pEFHorarioBean;

    @Inject
    private HorarioParqueaderoBean horarioParqueaderoBean;

    public void procesarPeriodoEstablecimientoHorario(Establecimiento establecimientoEntrada, Periodo periodoEntrada) throws Exception {
        Date fechaInicio = periodoEntrada.getInicio();
        Date fechaFin = periodoEntrada.getFin();
        Date fechaActual = new Date();
        VehiculoTrabajo VehiculoTrabajo = null;
        LocalDate localFechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localFechaFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Parqueadero> listaParqueaderos = establecimientoEntrada.getListaParqueadero();

        HashMap<String, Object> parametros = new HashMap();
        parametros.put("periodo", periodoEntrada);
        parametros.put("ordenOrderByAsc", null);
        List<PeriodoHorario> listaPeriodoHorario = periodoHorarioBean.obtenerListaPorParametros(parametros);
        Integer contadorDias = 1;
        for (LocalDate date = localFechaInicio; date.isBefore(localFechaFin); date = date.plusDays(1)) {
            fechaActual = java.util.Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Calendar calendar = Utilitario.dateToCalendar(fechaActual);
            Integer diaDelMes = calendar.get(Calendar.DAY_OF_MONTH);
            Integer mes = calendar.get(Calendar.MONTH);
            Integer diaDelAnio = calendar.get(Calendar.DAY_OF_YEAR);
            Integer dia = calendar.get(Calendar.DAY_OF_WEEK);
            String diaCadena = getDia(calendar.get(Calendar.DAY_OF_WEEK));
            PeriodoEstablecimientoFecha periodoEstablecimientoFecha = new PeriodoEstablecimientoFecha();
            periodoEstablecimientoFecha.setEstablecimiento(establecimientoEntrada);
            periodoEstablecimientoFecha.setPeriodo(periodoEntrada);
            periodoEstablecimientoFecha.setFecha(fechaActual);
            periodoEstablecimientoFecha.setDiaMes(diaDelMes.longValue());
            periodoEstablecimientoFecha.setDiaAnio(diaDelAnio.longValue());
            periodoEstablecimientoFecha.setMes(mes.longValue());
            periodoEstablecimientoFecha.setDia(dia.longValue());
            periodoEstablecimientoFecha.setOrden(contadorDias.longValue());
            periodoEstablecimientoFecha.setDiaCadena(diaCadena);
            periodoEstablecimientoFechaBean.crear(periodoEstablecimientoFecha);
            contadorDias++;
            for (PeriodoHorario periodoHorario : listaPeriodoHorario) {
                PEFHorario pEFHorario = new PEFHorario();
                pEFHorario.setOrden(periodoHorario.getOrden());
                pEFHorario.setHorario(periodoHorario.getHorario());
                pEFHorario.setPeriodoEstablecimientoFecha(periodoEstablecimientoFecha);
                pEFHorarioBean.crear(pEFHorario);
                for (Parqueadero parqueaderoTmp : listaParqueaderos) {
                    HorarioParqueadero horarioParqueadero = new HorarioParqueadero();
                    horarioParqueadero.setHorario(pEFHorario.getHorario());
                    horarioParqueadero.setParqueadero(parqueaderoTmp);
                    horarioParqueadero.setOrden(parqueaderoTmp.getNumero());
                    horarioParqueadero.setVehiculoTrabajo(VehiculoTrabajo);
                    horarioParqueadero.setpEFHorario(pEFHorario);
                    horarioParqueaderoBean.crear(horarioParqueadero);
                }

            }

        }

    }

    public List<List<PeriodoEstablecimientoFecha>> generarCalendario(HashMap parametros) {
        List<PeriodoEstablecimientoFecha> listaSemana = new ArrayList<>();
        List<List<PeriodoEstablecimientoFecha>> semana = new ArrayList<>();
        List<PeriodoEstablecimientoFecha> listaFecha = periodoEstablecimientoFechaBean.obtenerListaPorParametros(parametros);
        Long dia = 1L;
        for (int i = 0; i < listaFecha.size();) {
            PeriodoEstablecimientoFecha fecha = listaFecha.get(i);
            if (dia.intValue() == fecha.getDia().intValue()) {
                listaSemana.add(listaFecha.get(i));
                if (7L == fecha.getDia().intValue()) {
                    dia = 0l;
                    semana.add(listaSemana);
                    listaSemana = new ArrayList<>();

                }
                dia++;
                i++;
            } else {
                listaSemana.add(null);
                dia++;
                i = 0;
            }

        }
        for (int i = listaSemana.size(); i < 7; i++) {
            listaSemana.add(null);
        }
        semana.add(listaSemana);
        return semana;
    }

    public List<HashMap<String, PeriodoEstablecimientoFecha>> generarCalendarioHash(HashMap parametros) {
        List<HashMap<String, PeriodoEstablecimientoFecha>> listaPEF = new ArrayList<>();
        List<PeriodoEstablecimientoFecha> listaSemana;
        List<List<PeriodoEstablecimientoFecha>> semana = generarCalendario(parametros);
        for (int i = 0; i < semana.size(); i++) {
            System.out.println("i:" + i);
            listaSemana = semana.get(i);
            HashMap<String, PeriodoEstablecimientoFecha> hashPEF = new HashMap<>();

            for (int j = 0; j < listaSemana.size(); j++) {
                PeriodoEstablecimientoFecha periodoEstablecimientoFecha = listaSemana.get(j);
                switch (j) {
                    case 0:
                        hashPEF.put("DOMINGO", periodoEstablecimientoFecha);
                        break;
                    case 1:
                        hashPEF.put("LUNES", periodoEstablecimientoFecha);
                        break;
                    case 2:
                        hashPEF.put("MARTES", periodoEstablecimientoFecha);
                        break;
                    case 3:
                        hashPEF.put("MIERCOLES", periodoEstablecimientoFecha);
                        break;
                    case 4:
                        hashPEF.put("JUEVES", periodoEstablecimientoFecha);
                        break;
                    case 5:
                        hashPEF.put("VIERNES", periodoEstablecimientoFecha);
                        break;
                    case 6:
                        hashPEF.put("SABADO", periodoEstablecimientoFecha);
                        break;
                    default:
                        break;
                }
                
            }
            listaPEF.add(hashPEF);

        }
        return listaPEF;
    }

    private String getDia(int dia) {
        switch (dia) {
            case 1:
                return "DOMINGO";
            case 2:
                return "LUNE";
            case 3:
                return "MARTES";
            case 4:
                return "MIERCOLES";
            case 5:
                return "JUEVES";
            case 6:
                return "VIERNES";
            case 7:
                return "SABADO";
        }
        return "";
    }
}
