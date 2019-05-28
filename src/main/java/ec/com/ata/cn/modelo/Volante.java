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
@DiscriminatorValue(value = "VOLANTE")
public class Volante extends Trabajo implements Serializable {

    private static final long serialVersionUID = 1L;

    @OneToMany
    private List<ParteVolante> listaParteVolante;

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Volante[ id=" + super.getIdTrabajo() + " ]";
    }

    /**
     * @return the listaParteVolante
     */
    public List<ParteVolante> getListaParteVolante() {
        return listaParteVolante;
    }

    /**
     * @param listaParteVolante the listaParteVolante to set
     */
    public void setListaParteVolante(List<ParteVolante> listaParteVolante) {
        this.listaParteVolante = listaParteVolante;
    }

}
