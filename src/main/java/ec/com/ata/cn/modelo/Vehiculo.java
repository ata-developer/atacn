/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Transient;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "vehiculo")
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

    @Column(name = "tiene_plantilla")
    private boolean tienePlantilla;

    @Column(name = "estado_plantilla")
    private String estadoPlantilla;

    @Column(name = "observacion_plantilla")
    private String observacionPlantilla;

    @Column(name = "tiene_plantilla_volante")
    private boolean tienePlantillaVolante;

    @Column(name = "estado_plantilla_volante")
    private String estadoPlantillaVolante;

    @Column(name = "observacion_plantilla_volante")
    private String observacionPlantillaVolante;

    @Column(name = "observacion")
    private String observacion;

    @Transient
    private String descripcionDetallada;

    @Transient
    private String descripcionConMarca;

    @Transient
    private boolean seleccionar;

    @Embedded
    private GenericoEntidad genericoEntidad;

    @ManyToOne(fetch = FetchType.LAZY)
    private MarcaVehiculo marca;

    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.EAGER)
    private List<Fila> filasDeAsientos;

    @Column(name = "anio_desde")
    private Long anioDesde;

    @Column(name = "anio_hasta")
    private Long anioHasta;

    public Vehiculo() {
        genericoEntidad = new GenericoEntidad();
        filasDeAsientos = new ArrayList<>();
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

    /**
     * @return the descripcionDetallada
     */
    public String getDescripcionDetallada() {
        String modeloResultado = getModelo();
        String anioDesdeTmp = getAnioDesde() == null ? "" : " - " + getAnioDesde().toString();
        String anioHastaTmp = getAnioHasta() == null ? "" : " - " + getAnioHasta().toString();
        List<Fila> filas = getFilasDeAsientos();
        String descripcionFilas = "";
        for (Fila fila : filas) {
            descripcionFilas = descripcionFilas + " - " + fila.getTipoFila().getTipoFila() + "\n";
        }
        descripcionDetallada = modeloResultado + anioDesdeTmp + anioHastaTmp + descripcionFilas;
        return descripcionDetallada;
    }

    /**
     * @param descripcionDetallada the descripcionDetallada to set
     */
    public void setDescripcionDetallada(String descripcionDetallada) {
        this.descripcionDetallada = descripcionDetallada;
    }

    /**
     * @return the tiene_plantillas
     */
    public boolean isTienePlantilla() {
        return tienePlantilla;
    }

    /**
     * @param tiene_plantillas the tiene_plantillas to set
     */
    public void setTienePlantilla(boolean tiene_plantillas) {
        this.tienePlantilla = tiene_plantillas;
    }

    /**
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * @return the estadoPlantilla
     */
    public String getEstadoPlantilla() {
        return estadoPlantilla;
    }

    /**
     * @param estadoPlantilla the estadoPlantilla to set
     */
    public void setEstadoPlantilla(String estadoPlantilla) {
        this.estadoPlantilla = estadoPlantilla;
    }

    /**
     * @return the observacionPlantilla
     */
    public String getObservacionPlantilla() {
        return observacionPlantilla;
    }

    /**
     * @param observacionPlantilla the observacionPlantilla to set
     */
    public void setObservacionPlantilla(String observacionPlantilla) {
        this.observacionPlantilla = observacionPlantilla;
    }

    /**
     * @return the tienePlantillaVolante
     */
    public boolean isTienePlantillaVolante() {
        return tienePlantillaVolante;
    }

    /**
     * @param tienePlantillaVolante the tienePlantillaVolante to set
     */
    public void setTienePlantillaVolante(boolean tienePlantillaVolante) {
        this.tienePlantillaVolante = tienePlantillaVolante;
    }

    /**
     * @return the estadoPlantillaVolante
     */
    public String getEstadoPlantillaVolante() {
        return estadoPlantillaVolante;
    }

    /**
     * @param estadoPlantillaVolante the estadoPlantillaVolante to set
     */
    public void setEstadoPlantillaVolante(String estadoPlantillaVolante) {
        this.estadoPlantillaVolante = estadoPlantillaVolante;
    }

    /**
     * @return the observacionPlantillaVolante
     */
    public String getObservacionPlantillaVolante() {
        return observacionPlantillaVolante;
    }

    /**
     * @param observacionPlantillaVolante the observacionPlantillaVolante to set
     */
    public void setObservacionPlantillaVolante(String observacionPlantillaVolante) {
        this.observacionPlantillaVolante = observacionPlantillaVolante;
    }

    /**
     * @return the seleccionar
     */
    public boolean isSeleccionar() {
        return seleccionar;
    }

    /**
     * @param seleccionar the seleccionar to set
     */
    public void setSeleccionar(boolean seleccionar) {
        this.seleccionar = seleccionar;
    }

    /**
     * @return the descripcionConMarca
     */
    public String getDescripcionConMarca() {
        try {
            String marcatmp = getMarca() == null ? "" : getMarca().getMarca();
            String modelotmp = getModelo();
            String anioDesdeTmp = getAnioDesde() == null ? "" : getAnioDesde().toString();
            String anioHastaTmp = getAnioHasta() == null ? "" : getAnioHasta().toString();
            descripcionConMarca = marcatmp.trim().concat(" ").concat(modelotmp.trim()).concat(" ").concat(anioDesdeTmp.trim()).concat(" ").concat(anioHastaTmp.trim());
            return descripcionConMarca;
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * @param descripcionConMarca the descripcionConMarca to set
     */
    public void setDescripcionConMarca(String descripcionConMarca) {
        this.descripcionConMarca = descripcionConMarca;
    }

}
