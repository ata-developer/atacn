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
@DiscriminatorValue(value = "POMO")
public class Pomo extends Trabajo implements Serializable {
  
    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private List<PartePomo> listaPartePomo;

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Pomo[ id=" + super.getIdTrabajo() + " ]";
    }

    /**
     * @return the listaPartePomo
     */
    public List<PartePomo> getListaPartePomo() {
        return listaPartePomo;
    }

    /**
     * @param listaPartePomo the listaPartePomo to set
     */
    public void setListaPartePomo(List<PartePomo> listaPartePomo) {
        this.listaPartePomo = listaPartePomo;
    }
}
