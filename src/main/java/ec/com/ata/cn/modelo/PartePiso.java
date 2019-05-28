/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author ATA1
 */
@Entity
@DiscriminatorValue(value = "PARTEPISO")
public class PartePiso extends Parte implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.ParteAsiento[ id=" + super.getIdParte() + " ]";
    }
    
   
}
