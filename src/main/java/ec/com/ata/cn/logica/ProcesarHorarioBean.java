/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica;

import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.Parqueadero;
import ec.com.ata.cn.modelo.Periodo;
import ec.com.ata.cn.modelo.PeriodoHorario;
import ec.com.ata.cn.modelo.VehiculoTrabajo;
import java.time.LocalDate;
import java.time.ZoneId;
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

    public void procesarPeriodoEstablecimientoHorario(Periodo periodoEntrada, Establecimiento establecimientoEntrada) {
        Date fechaInicio = periodoEntrada.getInicio();
        Date fechaFin = periodoEntrada.getFin();
        Date fechaActual = new Date();
        VehiculoTrabajo VehiculoTrabajo = null;
        LocalDate localFechaInicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localFechaFin = fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Parqueadero> listaParqueaderos = establecimientoEntrada.getListaParqueadero();

        HashMap<String, Object> parametros = new HashMap();
        parametros.put("periodo", periodoEntrada);
        List<PeriodoHorario> listaPeriodoHorario = periodoHorarioBean.obtenerListaPorParametros(parametros);

        for (LocalDate date = localFechaInicio; date.isBefore(localFechaFin); date = date.plusDays(1)) {
            fechaActual = java.util.Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
            for (Parqueadero listaParqueadero : listaParqueaderos) {
                for (PeriodoHorario periodoHorario : listaPeriodoHorario) {

                }
            }
        }

    }
}
