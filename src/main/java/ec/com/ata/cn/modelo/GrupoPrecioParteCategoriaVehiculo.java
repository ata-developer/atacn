/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "grupo_precio_parte_categoria_vehiculo")
public class GrupoPrecioParteCategoriaVehiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private GrupoPrecioParteCategoriaVehiculoId grupoPrecioParteCategoriaVehiculoId;

    @MapsId("idGrupoPrecio")
    @JoinColumn(name = "id_grupo_precio", referencedColumnName = "id_grupo_precio")
    @ManyToOne
    private GrupoPrecio grupoPrecio;

    @MapsId("idCategoria")
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria")
    @ManyToOne
    private Categoria categoria;
    
    @MapsId("idParte")
    @JoinColumn(name = "id_parte", referencedColumnName = "id_parte")
    @ManyToOne
    private Parte parte;
    
    @MapsId("idVehiculo")
    @JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
    @ManyToOne
    private Vehiculo vehiculo;
    
    @Embedded
    private GenericoEntidad genericoEntidad;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.grupoPrecioParteCategoriaVehiculoId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GrupoPrecioParteCategoriaVehiculo other = (GrupoPrecioParteCategoriaVehiculo) obj;
        if (!Objects.equals(this.grupoPrecioParteCategoriaVehiculoId, other.grupoPrecioParteCategoriaVehiculoId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "GrupoPrecioParteCategoriaVehiculo{" + "grupoPrecio=" + grupoPrecio + ", categoria=" + categoria + ", parte=" + parte + ", vehiculo=" + vehiculo + '}';
    }
    
    

    /**
     * @return the grupoPrecioParteCategoriaVehiculoId
     */
    public GrupoPrecioParteCategoriaVehiculoId getGrupoPrecioParteCategoriaVehiculoId() {
        return grupoPrecioParteCategoriaVehiculoId;
    }

    /**
     * @param grupoPrecioParteCategoriaVehiculoId the grupoPrecioParteCategoriaVehiculoId to set
     */
    public void setGrupoPrecioParteCategoriaVehiculoId(GrupoPrecioParteCategoriaVehiculoId grupoPrecioParteCategoriaVehiculoId) {
        this.grupoPrecioParteCategoriaVehiculoId = grupoPrecioParteCategoriaVehiculoId;
    }

    /**
     * @return the grupoPrecio
     */
    public GrupoPrecio getGrupoPrecio() {
        return grupoPrecio;
    }

    /**
     * @param grupoPrecio the grupoPrecio to set
     */
    public void setGrupoPrecio(GrupoPrecio grupoPrecio) {
        this.grupoPrecio = grupoPrecio;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the parte
     */
    public Parte getParte() {
        return parte;
    }

    /**
     * @param parte the parte to set
     */
    public void setParte(Parte parte) {
        this.parte = parte;
    }

    /**
     * @return the vehiculo
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the genericoEntidad
     */
    public GenericoEntidad getGenericoEntidad() {
        return genericoEntidad;
    }

    /**
     * @param genericoEntidad the genericoEntidad to set
     */
    public void setGenericoEntidad(GenericoEntidad genericoEntidad) {
        this.genericoEntidad = genericoEntidad;
    }
    

    
}
