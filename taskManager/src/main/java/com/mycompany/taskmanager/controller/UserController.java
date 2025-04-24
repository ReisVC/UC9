/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.controller;

import com.mycompany.taskmanager.dao.UserDAO;
import com.mycompany.taskmanager.model.User;

/**
 *
 * @author Aluno
 */
public class UserController {
    
    
    
    public static boolean registerUser(String name, String email, String password) {
        
        UserDAO userDAO = new UserDAO();
        User user = new User(name, email, password);
        
        return userDAO.registerUser(user);
    }
    
    public static User validateLogin(User user) {
        
        UserDAO userDAO = new UserDAO();
        
        return userDAO.validateLogin(user);
    }
}
