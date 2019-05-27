/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author ATA1
 */
@Entity
@DiscriminatorValue(value = "PARTEFRENOMANO")
public class ParteFrenoMano extends Parte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.ParteFrenoMano[ id=" + super.getIdParte() + " ]";
    }
}
