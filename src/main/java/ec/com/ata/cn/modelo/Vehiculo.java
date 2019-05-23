/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author ATA1
 */
@Entity
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
    
    @ManyToOne
    private Marca marca;
    
    @ManyToOne
    private AnioVehiculo anioVehiculo;
    
    @ManyToOne
    private AnioVehiculo observacionAnio;
    
    @OneToMany
    private List<Trabajo> listaTrabajo;
    
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
    public Marca getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    /**
     * @return the anioVehiculo
     */
    public AnioVehiculo getAnioVehiculo() {
        return anioVehiculo;
    }

    /**
     * @param anioVehiculo the anioVehiculo to set
     */
    public void setAnioVehiculo(AnioVehiculo anioVehiculo) {
        this.anioVehiculo = anioVehiculo;
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
    public AnioVehiculo getObservacionAnio() {
        return observacionAnio;
    }

    /**
     * @param observacionAnio the observacionAnio to set
     */
    public void setObservacionAnio(AnioVehiculo observacionAnio) {
        this.observacionAnio = observacionAnio;
    }

    /**
     * @return the listaTrabajo
     */
    public List<Trabajo> getListaTrabajo() {
        return listaTrabajo;
    }

    /**
     * @param listaTrabajo the listaTrabajo to set
     */
    public void setListaTrabajo(List<Trabajo> listaTrabajo) {
        this.listaTrabajo = listaTrabajo;
    }

    
    
}
