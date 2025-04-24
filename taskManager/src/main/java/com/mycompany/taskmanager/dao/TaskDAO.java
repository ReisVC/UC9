/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.dao;

import com.mycompany.taskmanager.database.ConnectionSQL;
import com.mycompany.taskmanager.model.Task;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class TaskDAO {
    
    ConnectionSQL connectionSQL = new ConnectionSQL();
    
    public boolean registerTask(Task task) {
        String sql = "INSERT INTO tarefas (titulo, descricao, data_vencimento, status, user_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connectionSQL.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitulo());
            stmt.setString(2, task.getDescricao());
            stmt.setDate(3, task.getData_vencimento());
            stmt.setString(4, task.getStatus());
            stmt.setInt(5, task.getUser_id());
            stmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
 
    }
    
    public Task searchTaskById(int id) {
        String sql = "SELECT * FROM tarefas WHERE id = ?";

        try (Connection conn = connectionSQL.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Task(rs.getString("titulo"), rs.getString("descricao"), rs.getDate("data_vencimento"), rs.getString("status"), rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean updateTask(int id, String title, String description, Date date_expirated, String status) {
        
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, data_vencimento = ?, status = ? WHERE id = ?";
        
        try (Connection conn = ConnectionSQL.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setDate(3, date_expirated);
            stmt.setString(4, status);
            stmt.setInt(5, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteTask(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = connectionSQL.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Task> listTasks(int id_user) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE user_id = ?";

        try (Connection conn = connectionSQL.connect(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id_user);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(rs.getString("titulo"), rs.getString("descricao"), rs.getDate("data_vencimento"), rs.getString("status"), rs.getInt("user_id"));
                task.setId(rs.getInt("id"));
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }




    
}
