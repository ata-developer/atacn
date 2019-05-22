/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author ATA1
 */
@Entity
public class TipoParteAsiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "tipoparteasiento_seq",
            sequenceName = "tipoparteasiento_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tipoparteasiento_seq")
    @Column(name = "id_tipoparteasiento")
    private Long idTipoParteAsiento;
    
    private String tipo;

    public Long getIdTipoParteAsiento() {
        return idTipoParteAsiento;
    }

    public void setIdTipoParteAsiento(Long idTipoParteAsiento) {
        this.idTipoParteAsiento = idTipoParteAsiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoParteAsiento != null ? idTipoParteAsiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idTipoParteAsiento fields are not set
        if (!(object instanceof TipoParteAsiento)) {
            return false;
        }
        TipoParteAsiento other = (TipoParteAsiento) object;
        if ((this.idTipoParteAsiento == null && other.idTipoParteAsiento != null) || (this.idTipoParteAsiento != null && !this.idTipoParteAsiento.equals(other.idTipoParteAsiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.TipoParteAsiento[ id=" + idTipoParteAsiento + " ]";
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
