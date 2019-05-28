/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 *
 * @author ATA1
 */
@MappedSuperclass
public class TrabajoPiso extends Trabajo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @OneToMany
    private List<PartePiso> listaPartePiso;

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.TrabajoPiso[ id=" + super.getIdTrabajo() + " ]";
    }

    /**
     * @return the listaPartePiso
     */
    public List<PartePiso> getListaPartePiso() {
        return listaPartePiso;
    }

    /**
     * @param listaPartePiso the listaPartePiso to set
     */
    public void setListaPartePiso(List<PartePiso> listaPartePiso) {
        this.listaPartePiso = listaPartePiso;
    }
    
}
