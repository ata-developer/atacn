/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ATA1
 */
@Entity
@Table
public class Fila implements Serializable {

    

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

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "fila_seq",
            sequenceName = "fila_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fila_seq")
    @Column(name = "id_fila")
    private Long idFila;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @OneToMany
    private List<Asiento> asientos;
    
    private Integer numeroAsientos;
    
    @Transient
    private TipoFila tipoFilaTmp;
    
    @ManyToOne
    @JoinColumn(name="id_tipo_fila", referencedColumnName="id_tipo_fila")
    private TipoFila tipoFila;
    
    @ManyToOne
    @JoinColumn(name="id_vehiculo", referencedColumnName="id_vehiculo")
    private Vehiculo vehiculo;
    
    public Fila () {
        genericoEntidad = new GenericoEntidad();
    }
    
    public Long getIdFila() {
        return idFila;
    }

    public void setIdFila(Long idFila) {
        this.idFila = idFila;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFila != null ? idFila.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idFila fields are not set
        if (!(object instanceof Fila)) {
            return false;
        }
        Fila other = (Fila) object;
        if ((this.idFila == null && other.idFila != null) || (this.idFila != null && !this.idFila.equals(other.idFila))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Fila[ id=" + idFila + " ]";
    }

    /**
     * @return the asientos
     */
    public List<Asiento> getAsientos() {
        return asientos;
    }

    /**
     * @param asientos the asientos to set
     */
    public void setAsientos(List<Asiento> asientos) {
        this.asientos = asientos;
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
     * @return the numeroAsientos
     */
    public Integer getNumeroAsientos() {
        return numeroAsientos;
    }

    /**
     * @param numeroAsientos the numeroAsientos to set
     */
    public void setNumeroAsientos(Integer numeroAsientos) {
        this.numeroAsientos = numeroAsientos;
    }

    /**
     * @return the tipoFila
     */
    public TipoFila getTipoFila() {
        return tipoFila;
    }

    /**
     * @param tipoFila the tipoFila to set
     */
    public void setTipoFila(TipoFila tipoFila) {
        this.tipoFila = tipoFila;
    }

    /**
     * @return the tipoFilaTmp
     */
    public TipoFila getTipoFilaTmp() {
        return tipoFilaTmp;
    }

    /**
     * @param tipoFilaTmp the tipoFilaTmp to set
     */
    public void setTipoFilaTmp(TipoFila tipoFilaTmp) {
        this.tipoFilaTmp = tipoFilaTmp;
        this.tipoFila = tipoFilaTmp;
        this.numeroAsientos = tipoFilaTmp.getNumeroAsiento();
    }
    
}
