/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ATA1
 */
@Entity
public class TrabajoAsiento extends Trabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private List<Fila> filas;
    
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
