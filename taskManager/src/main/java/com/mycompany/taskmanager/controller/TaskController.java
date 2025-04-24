/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.controller;

import com.mycompany.taskmanager.dao.TaskDAO;
import com.mycompany.taskmanager.model.Task;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class TaskController {
    
    public static boolean registerTask(String titulo, String descricao, Date data_vencimento, String status, int user_id) {
        
        TaskDAO taskDAO = new TaskDAO();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data_vencimento);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        
        Date newDate = new Date(calendar.getTimeInMillis());
      
        
        Task task = new Task(titulo, descricao, newDate, status, user_id);
        
        return taskDAO.registerTask(task); 
    }
    
    public static List<Task> listTasks(int id_user) {
        
        TaskDAO taskDAO = new TaskDAO();
        
        return taskDAO.listTasks(id_user);
    }
    
    public static boolean deleteTask(int id) {
        TaskDAO taskDAO = new TaskDAO();
        
        return(taskDAO.deleteTask(id));
        
    }
    
    public static boolean updateTask(int id, String title, String description, String dataTexto, String status) {
        
        TaskDAO taskDAO = new TaskDAO();
        
        try {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        java.util.Date utilDate = formato.parse(dataTexto);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        return taskDAO.updateTask(id, title, description, sqlDate, status);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Data Inválida");
        }
        
        return false;        
    }
}
