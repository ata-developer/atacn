/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "grupo_precio")
public class GrupoPrecio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(
            name = "grupo_precio_seq",
            sequenceName = "grupo_precio_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "grupo_precio_seq")
    @Column(name = "id_grupo_precio")
    private Long idGrupoPrecio;
    
    @Column(name = "nombre")
    private String nombre;
    
    @OneToMany
    private List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecio;

    public Long getIdGrupoPrecio() {
        return idGrupoPrecio;
    }

    public void setIdGrupoPrecio(Long idGrupoPrecio) {
        this.idGrupoPrecio = idGrupoPrecio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGrupoPrecio != null ? idGrupoPrecio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idGrupoPrecio fields are not set
        if (!(object instanceof GrupoPrecio)) {
            return false;
        }
        GrupoPrecio other = (GrupoPrecio) object;
        if ((this.idGrupoPrecio == null && other.idGrupoPrecio != null) || (this.idGrupoPrecio != null && !this.idGrupoPrecio.equals(other.idGrupoPrecio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.GrupoPrecio[ id=" + idGrupoPrecio + " ]";
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
     * @return the listaTrabajoCategoriaPrecio
     */
    public List<TrabajoCategoriaPrecio> getListaTrabajoCategoriaPrecio() {
        return listaTrabajoCategoriaPrecio;
    }

    /**
     * @param listaTrabajoCategoriaPrecio the listaTrabajoCategoriaPrecio to set
     */
    public void setListaTrabajoCategoriaPrecio(List<TrabajoCategoriaPrecio> listaTrabajoCategoriaPrecio) {
        this.listaTrabajoCategoriaPrecio = listaTrabajoCategoriaPrecio;
    }
    
}
