/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */

@Entity
@Table ( name = "vehiculo_parte")
public class VehiculoParte implements Serializable {
        
    @Id
    @SequenceGenerator(
            name = "vehiculo_parte_seq",
            sequenceName = "vehiculo_parte_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehiculo_parte_seq")
    @Column(name = "id_vehiculo_parte")
    private Long idVehiculoParte;
    
    @ManyToOne
    @JoinColumn(name="id_parte", referencedColumnName="id_parte")
    private Parte parte;
    
    @ManyToOne
    @JoinColumn(name="id_vehiculo", referencedColumnName="id_vehiculo")
    private Vehiculo vehiculo;    
    
    @Column(name = "disposicion")
    private Long disposicion;
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    
    /**
     * @return the idVehiculoParte
     */
    public Long getIdVehiculoParte() {
        return idVehiculoParte;
    }

    /**
     * @param idVehiculoParte the idVehiculoParte to set
     */
    public void setIdVehiculoParte(Long idVehiculoParte) {
        this.idVehiculoParte = idVehiculoParte;
    }

    /**
     * @return the parte
     */
    public Parte getParte() {
        return parte;
    }

    /**
     * @param parte the parte to set
     */
    public void setParte(Parte parte) {
        this.parte = parte;
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
     * @return the genericoEntidad
     */
    public GenericoEntidad getGenericoEntidad() {
        return genericoEntidad;
    }

    /**
     * @param genericoEntidad the genericoEntidad to set
     */
    public void setGenericoEntidad(GenericoEntidad genericoEntidad) {
        this.genericoEntidad = genericoEntidad;
    }

    /**
     * @return the disposicion
     */
    public Long getDisposicion() {
        return disposicion;
    }

    /**
     * @param disposicion the disposicion to set
     */
    public void setDisposicion(Long disposicion) {
        this.disposicion = disposicion;
    }

    
    

}
