/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
@Table
public class Horario implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(
            name = "horario_seq",
            sequenceName = "horario_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "horario_seq")
    @Column(name = "id_horario")
    private Long idHorario;

    @Column(name = "horario")
    private String horario;
    
    private Long posicion;
    
    private LocalTime inicio;
    
    private LocalTime fin;
    
    private Boolean lunes;
    
    private Boolean martes;
    
    private Boolean miercoles;
    
    private Boolean jueves;
    
    private Boolean viernes;
    
    private Boolean sabado;
    
    private Boolean domingo;
    
    @Embedded
    private GenericoEntidad genericoEntidad;   
    
    public Horario () {
        genericoEntidad = new GenericoEntidad();
    }

    public Long getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Long idHorario) {
        this.idHorario = idHorario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHorario != null ? idHorario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idHorario fields are not set
        if (!(object instanceof Horario)) {
            return false;
        }
        Horario other = (Horario) object;
        return !((this.idHorario == null && other.idHorario != null) || (this.idHorario != null && !this.idHorario.equals(other.idHorario)));
    }

    @Override
    public String toString() {
        return "ec.com.ata.cn.modelo.Horario[ id=" + idHorario + " ]";
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
     * @return the horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    
    /**
     * @return the fin
     */
    public LocalTime getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    /**
     * @return the inicio
     */
    public LocalTime getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(LocalTime inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the posicion
     */
    public Long getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(Long posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the lunes
     */
    public Boolean getLunes() {
        return lunes;
    }

    /**
     * @param lunes the lunes to set
     */
    public void setLunes(Boolean lunes) {
        this.lunes = lunes;
    }

    /**
     * @return the martes
     */
    public Boolean getMartes() {
        return martes;
    }

    /**
     * @param martes the martes to set
     */
    public void setMartes(Boolean martes) {
        this.martes = martes;
    }

    /**
     * @return the miercoles
     */
    public Boolean getMiercoles() {
        return miercoles;
    }

    /**
     * @param miercoles the miercoles to set
     */
    public void setMiercoles(Boolean miercoles) {
        this.miercoles = miercoles;
    }

    /**
     * @return the jueves
     */
    public Boolean getJueves() {
        return jueves;
    }

    /**
     * @param jueves the jueves to set
     */
    public void setJueves(Boolean jueves) {
        this.jueves = jueves;
    }

    /**
     * @return the viernes
     */
    public Boolean getViernes() {
        return viernes;
    }

    /**
     * @param viernes the viernes to set
     */
    public void setViernes(Boolean viernes) {
        this.viernes = viernes;
    }

    /**
     * @return the sabado
     */
    public Boolean getSabado() {
        return sabado;
    }

    /**
     * @param sabado the sabado to set
     */
    public void setSabado(Boolean sabado) {
        this.sabado = sabado;
    }

    /**
     * @return the domingo
     */
    public Boolean getDomingo() {
        return domingo;
    }

    /**
     * @param domingo the domingo to set
     */
    public void setDomingo(Boolean domingo) {
        this.domingo = domingo;
    }


}
