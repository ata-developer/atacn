/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table( name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
        
    @Id
    @Column(name = "numero_documento", length = 60)
    private String numeroDocumento;    
    
    @Column(name = "nombre", length = 60)
    private String nombre;
    
    @Column(name = "apellido", length = 60)
    private String apellido;
    
    @Column(name = "calle_principal", length = 60)
    private String callePrincipal;
    
    @Column(name = "numeracion", length = 60)
    private String numeracion;
    
    @Column(name = "calle_secundaria", length = 60)
    private String calleSecundaria;
    
    @Column(name = "celular", length = 60, unique=true)
    private String celular;
    
    @Column(name = "telefono", length = 60, unique=true)
    private String telefono;
    
    @Column(name = "correo", length = 60, unique=true)
    private String correo;
    
    @Column(name = "es_confirmado")
    private Boolean esConfirmado;
    
    @Column(name = "es_sistema")
    private Boolean esSistema;
    
    @Column(name = "es_cliente")
    private Boolean esCliente;
    
    @Column(name = "usuario", length = 60, unique=true)
    private String usuario;
        
    @Column(name = "contrasenia", length = 100)
    private String contrasenia;
    
    @ManyToOne
    @JoinColumn(name="id_tipo_documento", referencedColumnName="id_tipo_documento")
    private TipoDocumento tipoDocumento;
    
    @ManyToOne
    @JoinColumn(name="id_ciudad", referencedColumnName="id_ciudad")
    private Ciudad ciudad;

    public String getIdUsuario() {
        return numeroDocumento;
    }

    public void setIdUsuario(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numeroDocumento != null ? numeroDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the numeroDocumento fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.numeroDocumento == null && other.numeroDocumento != null) || (this.numeroDocumento != null && !this.numeroDocumento.equals(other.numeroDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Usuario[ id=" + numeroDocumento + " ]";
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
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
     * @return the numeroDocumento
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * @param numeroDocumento the numeroDocumento to set
     */
    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
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
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    
    /**
     * @return the tipoDocumento
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the esConfirmado
     */
    public Boolean getEsConfirmado() {
        return esConfirmado;
    }

    /**
     * @param esConfirmado the esConfirmado to set
     */
    public void setEsConfirmado(Boolean esConfirmado) {
        this.esConfirmado = esConfirmado;
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
    
}
