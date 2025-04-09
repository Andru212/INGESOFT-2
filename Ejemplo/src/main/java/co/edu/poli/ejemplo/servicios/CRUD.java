package co.edu.poli.ejemplo.servicios;

import java.util.List;

/**
 * Interfaz genérica CRUD para cualquier clase de modelo.
 * 
 * @param <T>  Tipo del objeto que se manipula en la base de datos.
 * @param <ID> Tipo del identificador del objeto.
 * @param <R>  Tipo del resultado que devuelven las operaciones (puede ser String, Boolean, etc.).
 */
public interface CRUD<T, ID, R> {

    /**
     * Crea una nueva instancia de tipo T en la base de datos.
     * @param t El objeto de tipo T a crear.
     * @return Resultado de la operación (puede ser un mensaje, un booleano, etc.).
     */
    R create(T t);

    /**
     * Obtiene todos los objetos de tipo T desde la base de datos.
     * @return Una lista con todos los objetos de tipo T.
     */
    List<T> readAll();

    /**
     * Obtiene un objeto de tipo T de la base de datos usando su ID.
     * @param id El ID del objeto a obtener.
     * @return El objeto de tipo T, o null si no se encuentra.
     */
    T read(ID id) throws Exception;

    /**
     * Actualiza un objeto de tipo T en la base de datos.
     * @param id El ID del objeto a actualizar.
     * @param t El objeto con los nuevos datos.
     * @return Resultado de la operación.
     */
    R update(ID id, T t) throws Exception;

    /**
     * Elimina un objeto de tipo T de la base de datos usando su ID.
     * @param id El ID del objeto a eliminar.
     */
    R delete(ID id) throws Exception;
}
