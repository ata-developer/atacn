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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table
public class Establecimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "establecimiento_seq",
            sequenceName = "establecimiento_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "establecimiento_seq")
    @Column(name = "id_establecimiento")
    private Long idEstablecimiento;

    @Column(name = "establecimiento")
    private String establecimiento;
    
    @Column(name = "calle_principal")
    private String callePrincipal;
    
    @Column(name = "numeracion")
    private String numeracion;
    
    @Column(name = "calle_secundaria")
    private String calleSecundaria;
    
    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "celular")
    private String celular;
    
    @Column(name = "correo")
    private String correo;
    
    @ManyToOne
    private Usuario responsable;
    
    @ManyToOne
    private Ciudad ciudad;
    
    public Long getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Long idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getIdEstablecimiento() != null ? getIdEstablecimiento().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idEstablecimiento fields are not set
        if (!(object instanceof Establecimiento)) {
            return false;
        }
        Establecimiento other = (Establecimiento) object;
        return !((this.idEstablecimiento == null && other.idEstablecimiento != null) || (this.idEstablecimiento != null && !this.idEstablecimiento.equals(other.idEstablecimiento)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Establecimiento[ id=" + getIdEstablecimiento() + " ]";
    }

    /**
     * @return the establecimiento
     */
    public String getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * @return the callePrincipal
     */
    public String getCallePrincipal() {
        return callePrincipal;
    }

    /**
     * @param callePrincipal the callePrincipal to set
     */
    public void setCallePrincipal(String callePrincipal) {
        this.callePrincipal = callePrincipal;
    }

    /**
     * @return the numeracion
     */
    public String getNumeracion() {
        return numeracion;
    }

    /**
     * @param numeracion the numeracion to set
     */
    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    /**
     * @return the calleSecundaria
     */
    public String getCalleSecundaria() {
        return calleSecundaria;
    }

    /**
     * @param calleSecundaria the calleSecundaria to set
     */
    public void setCalleSecundaria(String calleSecundaria) {
        this.calleSecundaria = calleSecundaria;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the responsable
     */
    public Usuario getResponsable() {
        return responsable;
    }

    /**
     * @param responsable the responsable to set
     */
    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    /**
     * @return the ciudad
     */
    public Ciudad getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

}
