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
@DiscriminatorValue(value = "CAPUCHON")
public class Capuchon extends Trabajo implements Serializable  {

    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private List<ParteCapuchon> listaParteCapuchon;
    
    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Capuchon[ id=" + super.getIdTrabajo() + " ]";
    }

    /**
     * @return the listaParteCapuchon
     */
    public List<ParteCapuchon> getListaParteCapuchon() {
        return listaParteCapuchon;
    }

    /**
     * @param listaParteCapuchon the listaParteCapuchon to set
     */
    public void setListaParteCapuchon(List<ParteCapuchon> listaParteCapuchon) {
        this.listaParteCapuchon = listaParteCapuchon;
    }
    
}
