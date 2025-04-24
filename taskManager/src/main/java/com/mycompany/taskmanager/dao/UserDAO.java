/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.dao;

import com.mycompany.taskmanager.database.ConnectionSQL;
import com.mycompany.taskmanager.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Aluno
 */
public class UserDAO {
    
    ConnectionSQL connectionSQL = new ConnectionSQL();
    
    public boolean registerUser(User user) {
    String sql = "INSERT INTO usuario (name, email, password) VALUES (?, ?, ?)";
    
    String senhaHash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

    try (Connection conn = connectionSQL.connect(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        stmt.setString(3, senhaHash);
        stmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public User validateLogin(User user) {
    String sql = "SELECT * FROM usuario WHERE name = ? AND email = ?";

    try (Connection conn = connectionSQL.connect(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getEmail());
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String senhaHash = rs.getString("password");
            if(BCrypt.checkpw(user.getPassword(), senhaHash)) {
                
                User userSelected = new User(
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("password"));
                
                userSelected.setId(rs.getInt("id_user"));
                
                return userSelected;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
    public User searchUserByEmail(String email) {
    String sql = "SELECT * FROM usuario WHERE email = ?";

    try (Connection conn = connectionSQL.connect(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User usuario = new User(rs.getString("name") ,rs.getString("email"), rs.getString("password"));
            return usuario;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    public boolean updatePassword(String email, String newPassword) {
    String sql = "UPDATE usuario SET password = ? WHERE email = ?";
    String hashPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

    try (Connection conn = connectionSQL.connect(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, hashPassword);
        stmt.setString(2, email);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean deleteUser(String email) {
    String sql = "DELETE FROM usuario WHERE email = ?";

    try (Connection conn = connectionSQL.connect(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, email);
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    public List<User> listUsers() {
    List<User> usuarios = new ArrayList<>();
    String sql = "SELECT * FROM usuario";

    try (Connection conn = connectionSQL.connect(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getString("name"),rs.getString("email"), ""); // senha não exibida
            usuarios.add(user);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return usuarios;
}

    
    
}
