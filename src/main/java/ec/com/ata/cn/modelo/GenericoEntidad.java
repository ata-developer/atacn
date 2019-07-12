/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ATA1
 */
@Embeddable
public class GenericoEntidad implements Serializable, Comparable<GenericoEntidad> {

    private static final long serialVersionUID = 600033366699901111L;
    
    @Column(name = "documento_usuario")
    private String documentoUsuario;
    
    @Column(name = "fecha_registro")
    private String fechaRegistro;
    
    @Column(name = "estado")
    private String estado;

    @Override
    public int compareTo(GenericoEntidad o) {
        return this.getEstado().compareTo(o.getEstado());
    }

    /**
     * @return the documentoUsuario
     */
    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    /**
     * @param documentoUsuario the documentoUsuario to set
     */
    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    /**
     * @return the fechaRegistro
     */
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
