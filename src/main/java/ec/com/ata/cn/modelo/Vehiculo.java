/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author ATA1
 */
@Entity
@Table( name = "vehiculo")
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(
            name = "vehiculo_seq",
            sequenceName = "vehiculo_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "vehiculo_seq")
    @Column(name = "id_vehiculo")
    private Long idVehiculo;
       
    @Column(name = "modelo")
    private String modelo;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "numero_filas")   
    private Integer numeroDeFilas;
    
    @Column(name = "tipo_rango")   
    private String tipoRango;
    
    @Embedded
    private GenericoEntidad genericoEntidad;
    
    @ManyToOne
    private MarcaVehiculo marca;
    
    @OneToMany(mappedBy = "vehiculo",fetch = FetchType.EAGER)
    private List<Fila> filasDeAsientos;
    
    @Column(name = "anio_desde")
    private Long anioDesde;
        

    @Column(name = "anio_hasta")
    private Long anioHasta;
    
    public Vehiculo () {
        genericoEntidad = new GenericoEntidad();
    }
    
    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVehiculo != null ? idVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idVehiculo fields are not set
        if (!(object instanceof Vehiculo)) {
            return false;
        }
        Vehiculo other = (Vehiculo) object;
        if ((this.idVehiculo == null && other.idVehiculo != null) || (this.idVehiculo != null && !this.idVehiculo.equals(other.idVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "idVehiculo=" + idVehiculo + ", modelo=" + modelo + ", numeroDeFilas=" + numeroDeFilas + ", tipoRango=" + tipoRango + ", marca=" + marca + ", anioVehiculoDesde=" + anioDesde + ", anioVehiculoHasta=" + anioHasta + '}';
    }

    

    /**
     * @return the marca
     */
    public MarcaVehiculo getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(MarcaVehiculo marca) {
        this.marca = marca;
    }

    

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the anioDesde
     */
    public Long getAnioDesde() {
        return anioDesde;
    }

    /**
     * @param anioDesde the anioDesde to set
     */
    public void setAnioDesde(Long anioDesde) {
        this.anioDesde = anioDesde;
    }

    /**
     * @return the anioHasta
     */
    public Long getAnioHasta() {
        return anioHasta;
    }

    /**
     * @param anioHasta the anioHasta to set
     */
    public void setAnioHasta(Long anioHasta) {
        this.anioHasta = anioHasta;
    }

    /**
     * @return the numeroDeFilas
     */
    public Integer getNumeroDeFilas() {
        return numeroDeFilas;
    }

    /**
     * @param numeroDeFilas the numeroDeFilas to set
     */
    public void setNumeroDeFilas(Integer numeroDeFilas) {
        this.numeroDeFilas = numeroDeFilas;
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

    /**
     * @return the filasDeAsientos
     */
    public List<Fila> getFilasDeAsientos() {
        return filasDeAsientos;
    }

    /**
     * @param filasDeAsientos the filasDeAsientos to set
     */
    public void setFilasDeAsientos(List<Fila> filasDeAsientos) {
        this.filasDeAsientos = filasDeAsientos;
    }

    /**
     * @return the tipoRango
     */
    public String getTipoRango() {
        return tipoRango;
    }

    /**
     * @param tipoRango the tipoRango to set
     */
    public void setTipoRango(String tipoRango) {
        this.tipoRango = tipoRango;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   
}
