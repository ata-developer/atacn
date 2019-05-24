/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author ATA1
 */
@Entity
@DiscriminatorValue( value="FORRO" )
public class Forro extends Trabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private List<Fila> filas;

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.ForroTapiceria[ id=" + super.getIdTrabajo() + " ]";
    }

    /**
     * @return the filas
     */
    public List<Fila> getFilas() {
        return filas;
    }

    /**
     * @param filas the filas to set
     */
    public void setFilas(List<Fila> filas) {
        this.filas = filas;
    }
    
}
