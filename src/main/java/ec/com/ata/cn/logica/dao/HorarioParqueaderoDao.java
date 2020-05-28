/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.logica.dao;

import ec.com.ata.cn.logica.util.gestor.GenericoDaoUtil;
import ec.com.ata.cn.modelo.CitaDTO;
import ec.com.ata.cn.modelo.HorarioParqueadero;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author ATA1
 */
@Stateless
public class HorarioParqueaderoDao extends GenericoDaoUtil<HorarioParqueadero, Long> {

    public HorarioParqueaderoDao() {
        super(HorarioParqueadero.class);
    }

    public List<CitaDTO> obtenerCitaPorIdVehiculoTrabajo(Long idVehiculoTrabaho) {
        String consulta = "select\n"
                + "(select h.inicio from horario h where posicion = min(hp.orden_parqueadero)) inicio,\n"
                + "(select h.fin from horario h where posicion = max(hp.orden_parqueadero)) fin,\n"
                + "fecha\n"
                + "from\n"
                + "	horario_parqueadero hp \n"
                + "where \n"
                + "	hp.id_vehiculo_trabajo = ? "
                + " group by fecha\n"
                + "order by fecha asc;";
        Query query = em.createNativeQuery(consulta);
        query.setParameter(1, idVehiculoTrabaho);
        List<Object[]> list = query.getResultList();
        List<CitaDTO> listaCitaDTO = new ArrayList<>();
        for (Object[] q1 : list) {
            CitaDTO citaDTO  = new CitaDTO();
            citaDTO.setInicio((Date) q1[0]);
            citaDTO.setFin((Date) q1[1]);
            citaDTO.setFecha((Date) q1[2]);
            listaCitaDTO.add(citaDTO);
        }
        return listaCitaDTO;
    }

}
