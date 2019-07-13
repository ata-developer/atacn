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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table( name = "vehiculo")
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "vehiculo_seq",
            sequenceName = "vehiculo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehiculo_seq")
    @Column(name = "id_vehiculo")
    private Long idVehiculo;
       
    @Column(name = "modelo")
    private String modelo;
    
    private String observacionAnio;
    
    private Integer numeroDeFilas;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @ManyToOne
    private MarcaVehiculo marca;
    
    private Long anioVehiculoDesde;
        
    private Long anioVehiculoHasta;
    
    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idVehiculo fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Vehiculo[ id=" + idVehiculo + " ]";
    }

    /**
     * @return the marca
     */
    public MarcaVehiculo getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(MarcaVehiculo marca) {
        this.marca = marca;
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
     * @return the observacionAnio
     */
    public String getObservacionAnio() {
        return observacionAnio;
    }

    /**
     * @param observacionAnio the observacionAnio to set
     */
    public void setObservacionAnio(String observacionAnio) {
        this.observacionAnio = observacionAnio;
    }

    /**
     * @return the anioVehiculoDesde
     */
    public Long getAnioVehiculoDesde() {
        return anioVehiculoDesde;
    }

    /**
     * @param anioVehiculoDesde the anioVehiculoDesde to set
     */
    public void setAnioVehiculoDesde(Long anioVehiculoDesde) {
        this.anioVehiculoDesde = anioVehiculoDesde;
    }

    /**
     * @return the anioVehiculoHasta
     */
    public Long getAnioVehiculoHasta() {
        return anioVehiculoHasta;
    }

    /**
     * @param anioVehiculoHasta the anioVehiculoHasta to set
     */
    public void setAnioVehiculoHasta(Long anioVehiculoHasta) {
        this.anioVehiculoHasta = anioVehiculoHasta;
    }

    /**
     * @return the numeroDeFilas
     */
    public Integer getNumeroDeFilas() {
        return numeroDeFilas;
    }

    /**
     * @param numeroDeFilas the numeroDeFilas to set
     */
    public void setNumeroDeFilas(Integer numeroDeFilas) {
        this.numeroDeFilas = numeroDeFilas;
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

   
}
