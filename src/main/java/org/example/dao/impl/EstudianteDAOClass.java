package org.example.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Main;
import org.example.modelo.Estudiante;
import org.example.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de DAO para la entidad Estudiante.
 * Proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * mediante JDBC para interactuar con la base de datos MySQL.
 *

 */
public class EstudianteDAOClass implements GenericInterfaceDAO<Estudiante, Integer> {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());

    /**
     * Inserta un nuevo estudiante en la base de datos.
     * @param estudiante objeto Estudiante a insertar
     */
    @Override
    public void crear(Estudiante estudiante) {
        String sql = "INSERT INTO estudiante (nombre, email, curso_id) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getEmail());
            stmt.setInt(3, estudiante.getCursoId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error al crear un estudiante: ", e);
            e.printStackTrace();
        }
    }

    /**
     * Busca un estudiante en la base de datos por su ID.
     * @param id identificador del estudiante a buscar
     * @return objeto Estudiante si se encuentra, o null si no existe
     */
    @Override
    public Estudiante buscarPorId(Integer id) {
        String sql = "SELECT * FROM estudiante WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("curso_id"));
            }
        } catch (SQLException e) {
            LOGGER.error("Error al buscar estudiante por ID: ", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lista todos los estudiantes almacenados en la base de datos.
     * @return lista de objetos Estudiante
     */
    @Override
    public List<Estudiante> listarTodos() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";
        try (Connection conn = ConexionBD.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                estudiantes.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getInt("curso_id")));
            }
        } catch (SQLException e) {
            LOGGER.error("Error al listar estudiantes: ", e);
            e.printStackTrace();
        }
        return estudiantes;
    }

    /**
     * Actualiza los datos de un estudiante existente en la base de datos.
     * @param estudiante objeto Estudiante con los datos actualizados (debe incluir el id)
     */
    @Override
    public void actualizar(Estudiante estudiante) {
        String sql = "UPDATE estudiante SET nombre = ?, email = ?, curso_id = ? WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estudiante.getNombre());
            stmt.setString(2, estudiante.getEmail());
            stmt.setInt(3, estudiante.getCursoId());
            stmt.setInt(4, estudiante.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Error al actualizar un estudiante: ", e);
            e.printStackTrace();
        }
    }

    /**
     * Elimina un estudiante de la base de datos según su ID.
     * @param id identificador del estudiante a eliminar
     */
    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                LOGGER.warn("No se encontró ningún estudiante con ese ID.");
            } else {
                LOGGER.info("Estudiante eliminado exitosamente.");
            }
        } catch (SQLException e) {
            LOGGER.error("Error al eliminar un estudiante: ", e.getMessage());
            e.printStackTrace();
        }
    }
}
