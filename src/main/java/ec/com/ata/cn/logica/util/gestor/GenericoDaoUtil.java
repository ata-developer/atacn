/**
 * Clase GenericoDao.java 13 de abr. de 2017
 * Copyright 2017 Servicio de Rentas Internas.
 * Todos los derechos reservados.
 */
package ec.com.ata.cn.logica.util.gestor;

import ec.com.ata.cn.logica.excepcion.ParametrosNoExisteExcepcion;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
     * Método genérico para insertar un registro
     *
     * @param tablaEntidad tabla de tipo entidad
     */
    public T crear(final T tablaEntidad) throws Exception {
        em.persist(tablaEntidad);
        return tablaEntidad;
    }

    /**
     * Método genérico para actualizar un registro
     *
     * @param tablaEntidad tabla de tipo entidad
     * @return tabla de tipo entidad con registro modificado
     */
    public T modificar(final T tablaEntidad) throws Exception{
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
    
     public void eliminar(final T tablaEntidadEliminar) {
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
    
    /**
     * MÃ©todo generico para consultar, utilizando criteria query
     *
     * @param parametros lista de parametros a consultar
     * @param tablaEntidad tabla a consultar.
     * @return
     */
    public List<T> obtenerListaPorParametros(Map<String, Object> parametros) {
        if (parametros == null || parametros.isEmpty()) {
            throw new ParametrosNoExisteExcepcion("NO EXISTE PARAMETROS PARA LA BUSQUEDA");
        } else {
            Object parametro;
            List<Predicate> predicates = new ArrayList<>();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(tablaEntidad);
            Root<T> root = query.from(tablaEntidad);

            Field[] fields = tablaEntidad.getDeclaredFields();
            for (Field field : fields) {
                parametro = parametros.get(field.getName());
                if (null != parametro) {
                    System.out.println("parametro:" + parametro);
                    System.out.println("field.getName():" + field.getName());
                    predicates.add(cb.and(cb.equal(root.get(field.getName()), parametro)));
                }
                if (parametros.containsKey(field.getName().concat("IsNull"))) {
                    String parametroCadena = (String) parametros.get(field.getName().concat("IsNull"));
                    predicates.add(cb.and(cb.isNull(root.get(parametroCadena))));
                }                
                if (parametros.containsKey(field.getName().concat("IsNotNull"))) {
                    String parametroCadena = (String) parametros.get(field.getName().concat("IsNotNull"));
                    predicates.add(cb.and(cb.isNotNull(root.get(parametroCadena))));
                }
                if (parametros.containsKey(field.getName().concat("Like"))) {
                    System.out.println("like");
                    String parametroCadena = (String) parametros.get(field.getName().concat("Like"));
                    System.out.println("parametroCadena: "+ parametroCadena);
                    System.out.println("field.getName: "+ field.getName());
                    predicates.add(cb.and(cb.like(
                            cb.upper(root.<String>get(field.getName())),
                            "%" + parametroCadena.toUpperCase() + "%")));
                }
            }
            query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            return em.createQuery(query).getResultList();
        }
    }
}
