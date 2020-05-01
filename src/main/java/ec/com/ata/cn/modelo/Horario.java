/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.ata.cn.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    public Boolean presentarPorDia(String dia) {
        switch (dia) {
            case "LUNES":
                return this.lunes;
            case "MARTES":
                return this.martes;
            case "MIERCOLES":
                return this.miercoles;
            case "JUEVES":
                return this.jueves;
            case "VIERNES":
                return this.viernes;
            case "SABADO":
                return this.sabado;
            case "DOMINGO":
                return this.domingo;
            default:
                return null;
        }
    }

    @Column(name = "horario")
    private String horario;

    @Column(name = "posicion")
    private Long posicion;

    @Column(name = "inicio")
    @Temporal(TemporalType.TIME)
    private Date inicio;

    @Column(name = "fin")
    @Temporal(TemporalType.TIME)
    private Date fin;

    @Column(name = "lunes")
    private Boolean lunes;

    @Column(name = "martes")
    private Boolean martes;

    @Column(name = "miercoles")
    private Boolean miercoles;

    @Column(name = "jueves")
    private Boolean jueves;

    @Column(name = "viernes")
    private Boolean viernes;

    @Column(name = "sabado")
    private Boolean sabado;

    @Column(name = "doming")
    private Boolean domingo;

    @Embedded
    private GenericoEntidad genericoEntidad;

    public Horario() {
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
    public Date getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(Date fin) {
        this.fin = fin;
    }

    /**
     * @return the inicio
     */
    public Date getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Date inicio) {
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
