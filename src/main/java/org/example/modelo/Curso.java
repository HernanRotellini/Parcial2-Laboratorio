package org.example.modelo;

/**
 * Clase que representa un Curso con sus atributos básicos.
 * Contiene información del identificador, nombre y descripción del curso.
 * Proporciona métodos getters y setters para manipular los datos,
 * así como un método toString para la representación en texto.
 *

 */
public class Curso {
    private int id;
    private String nombre;
    private String descripcion;

    /**
     * Constructor vacío para crear un objeto Curso sin inicializar atributos.
     */
    public Curso() {}

    /**
     * Constructor con parámetros para inicializar un objeto Curso con valores específicos.
     *
     * @param id identificador único del curso
     * @param nombre nombre del curso
     * @param descripcion descripción del curso
     */
    public Curso(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el ID del curso.
     * @return id del curso
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del curso.
     * @param id nuevo identificador del curso
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del curso.
     * @return nombre del curso
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del curso.
     * @param nombre nuevo nombre del curso
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del curso.
     * @return descripción del curso
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del curso.
     * @param descripcion nueva descripción del curso
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Representa el objeto Curso como una cadena de texto con sus atributos.
     * @return cadena con información del curso
     */
    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", nombre='" + nombre + "', descripcion='" + descripcion + "'}";
    }
}
