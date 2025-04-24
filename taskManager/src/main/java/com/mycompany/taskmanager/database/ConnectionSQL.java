/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aluno
 */
public class ConnectionSQL {
     // Carrega as variáveis do arquivo .env
    private static final Dotenv dotenv = Dotenv.load();

    private static Connection connection;

    // Variáveis de conexão
    private static final String URL = dotenv.get("DB_URL");
    private static final String DB_USER = dotenv.get("DB_USER");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    // Conecta ao banco de dados
    public static Connection connect() {
        try {
            // Se ainda não houver conexão, cria uma nova
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
                createTable();
                System.out.println("Conectado ao banco.");
            }
        } catch (SQLException error) {
            throw new RuntimeException("Erro na conexão com o banco de dados", error);
        }

        return connection;
    }

    // Cria a tabela de usuários se ela não existir
    private static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS tarefas ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "titulo VARCHAR(255) NOT NULL UNIQUE, "
                + "descricao TEXT NOT NULL, "
                + "data_vencimento DATE, "
                + "status ENUM('pendente', 'concluido') DEFAULT 'pendente',"
                + "user_id INT, "
                + "FOREIGN KEY (user_id) REFERENCES usuario(id_user));";
        
        String sql2 = "CREATE TABLE IF NOT EXISTS usuario (" 
                + "id_user INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "email VARCHAR(100) UNIQUE NOT NULL, "
                + "password VARCHAR(255) NOT NULL);";
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
//            stmt.execute(sql2);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela", e);
        }
    }
}
