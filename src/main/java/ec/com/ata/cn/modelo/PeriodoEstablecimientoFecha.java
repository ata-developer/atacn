/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ATA1
 */
@Entity
@Table(name = "periodo_establecimiento_fecha")
public class PeriodoEstablecimientoFecha implements Serializable {   

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "pef_seq",
            sequenceName = "pef_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pef_seq")
    @Column(name = "id_pef")
    private Long idPEF;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "dia_anio")
    private Long diaAnio;
    
    @Column(name = "dia_mes")
    private Long diaMes;
    
    @Column(name = "mes")
    private Long mes;
    
    @Column(name = "dia")
    private Long dia;
    
    @Column(name = "orden")
    private Long orden;
    
    @Column(name = "dia_cadena")
    private String diaCadena;
    
    @Column(name = "anio")
    private Long anio;
    
    @ManyToOne
    @JoinColumn(name="id_periodo", referencedColumnName="id_periodo")
    private Periodo periodo;
    
    @ManyToOne
    @JoinColumn(name="id_establecimiento", referencedColumnName="id_establecimiento")
    private Establecimiento establecimiento;
    
    @OneToMany(mappedBy = "periodoEstablecimientoFecha", fetch = FetchType.EAGER)
    @OrderBy("orden ASC")
    private List<PEFHorario> listaPEFHorario;
    
    @Embedded
    private GenericoEntidad genericoEntidad;   
    
    public PeriodoEstablecimientoFecha () {
        
    }

    public Long getIdPEF() {
        return idPEF;
    }

    public void setIdPEF(Long idPEF) {
        this.idPEF = idPEF;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPEF != null ? idPEF.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idPEF fields are not set
        if (!(object instanceof PeriodoEstablecimientoFecha)) {
            return false;
        }
        PeriodoEstablecimientoFecha other = (PeriodoEstablecimientoFecha) object;
        return !((this.idPEF == null && other.idPEF != null) || (this.idPEF != null && !this.idPEF.equals(other.idPEF)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.PEF[ id=" + idPEF + " ]";
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
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * @param periodo the periodo to set
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /**
     * @return the establecimiento
     */
    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }
    
    /**
     * @return the diaAnio
     */
    public Long getDiaAnio() {
        return diaAnio;
    }

    /**
     * @param diaAnio the diaAnio to set
     */
    public void setDiaAnio(Long diaAnio) {
        this.diaAnio = diaAnio;
    }

    /**
     * @return the diaMes
     */
    public Long getDiaMes() {
        return diaMes;
    }

    /**
     * @param diaMes the diaMes to set
     */
    public void setDiaMes(Long diaMes) {
        this.diaMes = diaMes;
    }

    /**
     * @return the mes
     */
    public Long getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(Long mes) {
        this.mes = mes;
    }
    
    /**
     * @return the dia
     */
    public Long getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(Long dia) {
        this.dia = dia;
    }

    /**
     * @return the orden
     */
    public Long getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(Long orden) {
        this.orden = orden;
    }
    
    /**
     * @return the diaCadena
     */
    public String getDiaCadena() {
        return diaCadena;
    }

    /**
     * @param diaCadena the diaCadena to set
     */
    public void setDiaCadena(String diaCadena) {
        this.diaCadena = diaCadena;
    }
    
    /**
     * @return the anio
     */
    public Long getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Long anio) {
        this.anio = anio;
    }
    
    /**
     * @return the listaPEFHorario
     */
    public List<PEFHorario> getListaPEFHorario() {
        return listaPEFHorario;
    }

    /**
     * @param listaPEFHorario the listaPEFHorario to set
     */
    public void setListaPEFHorario(List<PEFHorario> listaPEFHorario) {
        this.listaPEFHorario = listaPEFHorario;
    }
}
