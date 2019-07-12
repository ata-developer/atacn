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
public class Parqueadero implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "parqueadero_seq",
            sequenceName = "parqueadero_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "parqueadero_seq")
    @Column(name = "id_parqueadero")
    private Long idEstablecimiento;

    @Column(name = "parqueadero")
    private String parqueadero;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @ManyToOne
    @JoinColumn(name="id_establecimiento", referencedColumnName="id_establecimiento")
    private Establecimiento establecimiento;

    public Long getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Long idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstablecimiento != null ? idEstablecimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idEstablecimiento fields are not set
        if (!(object instanceof Parqueadero)) {
            return false;
        }
        Parqueadero other = (Parqueadero) object;
        return !((this.idEstablecimiento == null && other.idEstablecimiento != null) || (this.idEstablecimiento != null && !this.idEstablecimiento.equals(other.idEstablecimiento)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Establecimiento[ id=" + idEstablecimiento + " ]";
    }

    /**
     * @return the parqueadero
     */
    public String getParqueadero() {
        return parqueadero;
    }

    /**
     * @param parqueadero the parqueadero to set
     */
    public void setParqueadero(String parqueadero) {
        this.parqueadero = parqueadero;
    }

    /**
     * @return the establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

   

    
}
