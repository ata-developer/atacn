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
@Table
public class Plantilla implements Serializable {
    
    @Id
    @SequenceGenerator(
            name = "plantilla_seq",
            sequenceName = "plantilla_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "plantilla_seq")
    @Column(name = "id_plantilla")
    private Long idPlantilla;
    
    @Column(name = "existe")
    private boolean existe;
    
    @Column(name = "numero_piezas")
    private Integer numeroPiezas;
    
    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "observacion")
    private String observacion;
    
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    private Vehiculo vehiculo;

    @Embedded
    private GenericoEntidad genericoEntidad;

    public Plantilla() {
        genericoEntidad = new GenericoEntidad();
    }
    
    public void setIdPlantilla(Long idPlantilla) {
        this.idPlantilla = idPlantilla;
    }
    
    public Long getIdPlantilla() {
        return idPlantilla;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdPlantilla() != null ? getIdPlantilla().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPlantilla fields are not set
        if (!(object instanceof Plantilla)) {
            return false;
        }
        Plantilla other = (Plantilla) object;
        if ((this.getIdPlantilla() == null && other.getIdPlantilla() != null) || (this.getIdPlantilla() != null && !this.idPlantilla.equals(other.idPlantilla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Plantilla[ id=" + getIdPlantilla() + " ]";
    }
    
    /**
     * @return the existe
     */
    public Boolean getExiste() {
        return existe;
    }

    /**
     * @param existe the existe to set
     */
    public void setExiste(Boolean existe) {
        this.existe = existe;
    }

    /**
     * @return the numeroPiezas
     */
    public Integer getNumeroPiezas() {
        return numeroPiezas;
    }

    /**
     * @param numeroPiezas the numeroPiezas to set
     */
    public void setNumeroPiezas(Integer numeroPiezas) {
        this.numeroPiezas = numeroPiezas;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
}
