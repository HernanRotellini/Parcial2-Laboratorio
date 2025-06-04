package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBD {
    //Modificar Nombre BD,usuario y contraseña
    //Script creacion BD

    /*

    CREATE DATABASE IF NOT EXISTS gestion_cursos;

    USE gestion_cursos;

    CREATE TABLE IF NOT EXISTS curso (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS estudiante (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    curso_id INT NOT NULL,
    FOREIGN KEY (curso_id) REFERENCES curso(id) ON DELETE CASCADE
    );

     */
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_cursos";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final Logger LOGGER = Logger.getLogger(ConexionBD.class.getName());

    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al conectar con la base de datos", e);
            throw new RuntimeException(e);
        }
    }
}
