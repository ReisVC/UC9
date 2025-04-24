/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.taskmanager.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author Aluno
 */
public class Task {
    
    private int id;
    private String titulo;
    private String descricao;
    private Date data_vencimento;
    private String status;
    private int user_id;

    public Task(String titulo, String descricao, Date data_vencimento, String status, int user_id) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data_vencimento = data_vencimento;
        this.status = status;
        this.user_id = user_id;
    }
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
