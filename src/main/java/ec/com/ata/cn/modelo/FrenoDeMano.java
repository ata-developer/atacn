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
@DiscriminatorValue( value="FRENODEMANO" )
public class FrenoDeMano extends Trabajo implements Serializable {
  
    private static final long serialVersionUID = 1L;

    @OneToMany
    private List<ParteFrenoMano> listaParteFrenoMano;
    
    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.FrenoDeMano[ id=" + super.getIdTrabajo() + " ]";
    }

    /**
     * @return the listaParteFrenoMano
     */
    public List<ParteFrenoMano> getListaParteFrenoMano() {
        return listaParteFrenoMano;
    }

    /**
     * @param listaParteFrenoMano the listaParteFrenoMano to set
     */
    public void setListaParteFrenoMano(List<ParteFrenoMano> listaParteFrenoMano) {
        this.listaParteFrenoMano = listaParteFrenoMano;
    }
    
}
