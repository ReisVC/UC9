/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.taskmanager;

import com.mycompany.taskmanager.dao.TaskDAO;
import com.mycompany.taskmanager.dao.UserDAO;
import com.mycompany.taskmanager.database.ConnectionSQL;
import com.mycompany.taskmanager.model.Task;
import com.mycompany.taskmanager.model.User;
import com.mycompany.taskmanager.view.LoginScreen;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class TaskManager {

    public static void main(String[] args) {
        new LoginScreen().setVisible(true);
    }
}
