package org.example.modelo;

public class Estudiante {
    private int id;
    private String nombre;
    private String email;
    private int cursoId;

    public Estudiante() {}

    public Estudiante(int id, String nombre, String email, int cursoId) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cursoId = cursoId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }

    @Override
    public String toString() {
        return "Estudiante{" + "id=" + id + ", nombre='" + nombre + "', email='" + email + "', cursoId=" + cursoId + '}';
    }
}