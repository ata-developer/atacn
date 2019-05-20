/**
 * Clase GenericoDao.java 13 de abr. de 2017
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.com.ata.cn.logica.util.gestor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Implementación del patrón de acceso a datos con métodos genéricos CRUD
 *
 * @author djlu030215
 *
 */
public class GenericoDaoUtil<T, I extends Serializable> {

    @PersistenceContext(unitName = "ATACN-PU")
    protected EntityManager em;
    protected TypedQuery<T> tipoConsulta;
    protected Query consulta;
    private final Class<T> tablaEntidad;

    /**
     * Constructor
     *
     * @param tablaEntidad
     */
    public GenericoDaoUtil(final Class<T> tablaEntidad) {
        this.tablaEntidad = tablaEntidad;
    }

    /**
     * @param em
     * @param tablaEntidad
     */
    public GenericoDaoUtil(final Class<T> tablaEntidad, final EntityManager em) {
        this.tablaEntidad = tablaEntidad;
        this.em = em;
    }

    /**
     * Método genérico para insertar un registro
     *
     * @param tablaEntidad tabla de tipo entidad
     */
    public T crear(final T tablaEntidad) {
        em.persist(tablaEntidad);
        em.flush();
        return tablaEntidad;
    }

    /**
     * Método genérico para actualizar un registro
     *
     * @param tablaEntidad tabla de tipo entidad
     * @return tabla de tipo entidad con registro modificado
     */
    public T modificar(final T tablaEntidad) {
        return em.merge(tablaEntidad);
    }

    /**
     * Método genérico para eliminar un registro
     *
     * @param identificador identificador
     */
    public void eliminar(final I identificador) {
        T tablaEntidadEliminar = obtenerPorCodigo(identificador);
        em.remove(tablaEntidadEliminar);
        em.flush();

    }

    /**
     * Método genérico para buscar un registro correspondiente a su
     * identificador principal
     *
     * @param identificador código o identificador principal
     * @return tabla de tipo entidad
     */
    public T obtenerPorCodigo(final I identificador) {
        return em.find(tablaEntidad, identificador);
    }

    /**
     * Método genérico para obtener todos los registros
     *
     * @return lista de registros correspondiente a la tabla de tipo entidad
     */
    public List<T> obtenerTodos() {
        TypedQuery<T> consulta = em.createQuery("Select t from " + tablaEntidad.getSimpleName() + " t", tablaEntidad);
        return consulta.getResultList();
    }
    
}
