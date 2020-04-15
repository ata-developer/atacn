/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.controlador;

import ec.com.ata.cn.logica.EstablecimientoBean;
import ec.com.ata.cn.logica.HorarioBean;
import ec.com.ata.cn.logica.PeriodoBean;
import ec.com.ata.cn.modelo.Establecimiento;
import ec.com.ata.cn.modelo.Horario;
import ec.com.ata.cn.modelo.Periodo;
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
    
    @PostConstruct
    public void init() {
        
    }
    
    public List<SelectItem> generarSelectItemDePeriodos() {
        SelectItemsBuilder selectItemsBuilder = new SelectItemsBuilder();
        for (Periodo periodoTmp : periodoBean.obtenerLista()) {
            selectItemsBuilder.add(periodoTmp, periodoTmp.getPeriodo());
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
   
}
