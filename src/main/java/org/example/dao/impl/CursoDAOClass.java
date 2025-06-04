package org.example.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Main;
import org.example.modelo.Curso;
import org.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de DAO para la entidad Curso.
 * Proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * utilizando JDBC para interactuar con la base de datos MySQL.
 *

 */
public class CursoDAOClass implements GenericInterfaceDAO<Curso, Integer> {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    /**
     * Inserta un nuevo curso en la base de datos.
     * @param curso objeto Curso a insertar
     */
    @Override
    public void crear(Curso curso) {
        String sql = "INSERT INTO curso (nombre, descripcion) VALUES (?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error al crear un curso: ", e);
            e.printStackTrace();
        }
    }

    /**
     * Busca un curso en la base de datos por su ID.
     * @param id identificador del curso a buscar
     * @return objeto Curso si se encuentra, o null si no existe
     */
    @Override
    public Curso buscarPorId(Integer id) {
        String sql = "SELECT * FROM curso WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curso(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error al buscar cursos por id: ", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lista todos los cursos almacenados en la base de datos.
     * @return lista de objetos Curso
     */
    @Override
    public List<Curso> listarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cursos.add(new Curso(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion")));
            }
        } catch (SQLException e) {
            LOGGER.error("Error al listar cursos: ", e);
            e.printStackTrace();
        }
        return cursos;
    }

    /**
     * Actualiza los datos de un curso existente en la base de datos.
     * @param curso objeto Curso con los datos actualizados (debe incluir el id)
     */
    @Override
    public void actualizar(Curso curso) {
        String sql = "UPDATE curso SET nombre = ?, descripcion = ? WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, curso.getNombre());
            stmt.setString(2, curso.getDescripcion());
            stmt.setInt(3, curso.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error al actualizar un curso: ", e);
            e.printStackTrace();
        }
    }

    /**
     * Elimina un curso de la base de datos según su ID.
     * @param id identificador del curso a eliminar
     */
    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró ningún curso con ese ID.");
            } else {
                System.out.println("Curso eliminado exitosamente.");
            }
        } catch (SQLException e) {
            LOGGER.error("Error al eliminar un curso: ", e);
            e.printStackTrace();
        }
    }
}
