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
@DiscriminatorValue( value="OTRO" )
public class Otro extends Trabajo implements Serializable {
  
    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private List<ParteOtro> listaParteOtro;
    
    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Otro[ id=" + super.getIdTrabajo() + " ]";
    }

    /**
     * @return the listaParteOtro
     */
    public List<ParteOtro> getListaParteOtro() {
        return listaParteOtro;
    }

    /**
     * @param listaParteOtro the listaParteOtro to set
     */
    public void setListaParteOtro(List<ParteOtro> listaParteOtro) {
        this.listaParteOtro = listaParteOtro;
    }
    
}
