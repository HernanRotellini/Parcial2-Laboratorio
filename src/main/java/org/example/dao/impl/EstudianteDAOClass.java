package org.example.dao.impl;

import org.example.modelo.Estudiante;
import org.example.util.ConexionBD;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOClass implements GenericInterfaceDAO<Estudiante, Integer> {

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
            e.printStackTrace();
        }
    }



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
            e.printStackTrace();
        }
        return null;
    }

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
            //logger.error("Error al eliminar un estudiante: ", e);
            e.printStackTrace();
        }
        return estudiantes;
    }



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
            e.printStackTrace();
        }
    }



    @Override
    public void eliminar(Integer id) {
        String sql = "DELETE FROM estudiante WHERE id = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
               // logger.warn("No se encontró ningún estudiante con ese ID.");
            } else {
                //logger.info("Estudiante eliminado exitosamente.");
            }
        } catch (SQLException e) {
           // logger.error("Error al eliminar un estudiante: ", e.getMessage());
            e.printStackTrace();
        }
    }
}

