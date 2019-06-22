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
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "tipo_documento")
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "tipo_documento_seq",
            sequenceName = "tipo_documento_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tipo_documento_seq")
    @Column(name = "id_tipo_documento")
    private Long idTipoDocumento;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "es_sistema")
    private Boolean esSistema;

    @Column(name = "es_cliente")
    private Boolean esCliente;

    public TipoDocumento() {
        setEsSistema(false);
        setEsCliente(false);
    }

    public Long getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Long idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idTipoDocumento fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        return !((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.TipoDocumento[ id=" + idTipoDocumento + " ]";
    }

    /**
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the esSistema
     */
    public Boolean getEsSistema() {
        return esSistema;
    }

    /**
     * @param esSistema the esSistema to set
     */
    public void setEsSistema(Boolean esSistema) {
        this.esSistema = esSistema;
    }

    /**
     * @return the esCliente
     */
    public Boolean getEsCliente() {
        return esCliente;
    }

    /**
     * @param esCliente the esCliente to set
     */
    public void setEsCliente(Boolean esCliente) {
        this.esCliente = esCliente;
    }

}
