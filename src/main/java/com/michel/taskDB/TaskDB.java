package com.michel.taskDB;

import com.michel.connectionFactory.ConnectionFactory;
import com.michel.entities.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDB {

    //Salva a task no banco
    public static void save(Task task) {
        String sql = "INSERT INTO `task`.`tb_task`(`name`, `description`) VALUES (?, ?);";
        Connection conn = ConnectionFactory.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getDesc());
            stmt.executeUpdate();
            ConnectionFactory.close(conn, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Caso houver o nome da task no banco, o usuário pode deletar
    public static void delete(String name) {
        String sql = "DELETE FROM tb_task WHERE name = ?";
        Connection conn = ConnectionFactory.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
            ConnectionFactory.close(conn, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Faz a edição da Task, é possível editar o nome e a descrição
    public static void update(Integer editTaskNum, String nameEdit, String element) {
        String sql = null;
        if (editTaskNum == 1) {
            sql = "UPDATE tb_task SET name = ? WHERE name = ? ";
        } else if (editTaskNum == 2) {
            sql = "UPDATE tb_task SET description = ? WHERE name = ?";
        }
        Connection conn = ConnectionFactory.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, element);
            stmt.setString(2, nameEdit);
            stmt.executeUpdate();
            ConnectionFactory.close(conn, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Irá retornar uma lista com todas as Tasks inseridas por ordem
    public static List<Task> selectAll() {
        String sql = "SELECT id, name, description FROM tb_task";
        Connection conn = ConnectionFactory.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            List<Task> listTasks = new ArrayList<>();
            while (rs.next()) {
                listTasks.add(new Task(rs.getString("name"), rs.getString("description"), rs.getLong("id")));
            }
            ConnectionFactory.close(conn, stmt, rs);
            return listTasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Método para selecionar e retornar Task selecionada por nome
    public static List<Task> selectName(String name) {
        String sql = "SELECT * FROM tb_task WHERE name = ?";
        Connection conn = ConnectionFactory.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            List<Task> listTask = new ArrayList<>();
            while (rs.next()) {
                listTask.add(new Task(rs.getString("name"), rs.getString("description"), rs.getLong("id")));
            }
            ConnectionFactory.close(conn, stmt, rs);
            return listTask;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Método para validar o nome, caso houver ele retorna o nome, caso for nulo ele retorna null
    public static Boolean validationName(String name) {
        String sql = "SELECT name FROM tb_task WHERE name = ?";
        Connection conn = ConnectionFactory.getConexao();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            ConnectionFactory.close(conn, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
