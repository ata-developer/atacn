/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author ATA1
 */
@MappedSuperclass
public class TrabajoPiso extends Trabajo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.TrabajoPiso[ id=" + super.getIdTrabajo() + " ]";
    }
    
}
