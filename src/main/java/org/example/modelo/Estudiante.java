package org.example.modelo;

/**
 * Clase que representa un Estudiante con sus atributos básicos.
 * Contiene información del identificador, nombre, email y el ID del curso al que está asociado.
 * Proporciona métodos getters y setters para manipular los datos,
 * así como un método toString para la representación en texto.
 *

 */
public class Estudiante {
    private int id;
    private String nombre;
    private String email;
    private int cursoId;

    /**
     * Constructor vacío para crear un objeto Estudiante sin inicializar atributos.
     */
    public Estudiante() {}

    /**
     * Constructor con parámetros para inicializar un objeto Estudiante con valores específicos.
     *
     * @param id identificador único del estudiante
     * @param nombre nombre del estudiante
     * @param email correo electrónico del estudiante
     * @param cursoId identificador del curso asociado al estudiante
     */
    public Estudiante(int id, String nombre, String email, int cursoId) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cursoId = cursoId;
    }

    /**
     * Obtiene el ID del estudiante.
     * @return id del estudiante
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del estudiante.
     * @param id nuevo identificador del estudiante
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del estudiante.
     * @return nombre del estudiante
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del estudiante.
     * @param nombre nuevo nombre del estudiante
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el email del estudiante.
     * @return email del estudiante
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del estudiante.
     * @param email nuevo correo electrónico del estudiante
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el ID del curso asociado al estudiante.
     * @return id del curso
     */
    public int getCursoId() {
        return cursoId;
    }

    /**
     * Establece el ID del curso asociado al estudiante.
     * @param cursoId nuevo id del curso
     */
    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    /**
     * Representa el objeto Estudiante como una cadena de texto con sus atributos.
     * @return cadena con información del estudiante
     */
    @Override
    public String toString() {
        return "Estudiante{" + "id=" + id + ", nombre='" + nombre + "', email='" + email + "', cursoId=" + cursoId + '}';
    }
}
