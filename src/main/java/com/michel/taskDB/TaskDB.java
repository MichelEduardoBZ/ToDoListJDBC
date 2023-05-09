package com.michel.taskDB;

import com.michel.connectionFactory.ConnectionFactory;
import com.michel.entities.Task;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskDB {

    public static void save(Task task) {
        String sql = "INSERT INTO `task`.`tb_task`(`name`, `description`) VALUES ('" + task.getName() + "', '" + task.getDesc() + "');";
        Connection conn = ConnectionFactory.getConexao();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            ConnectionFactory.close(conn, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean delete(Long id) {
        if (validationNull(id) != null) {
            String sql = "DELETE FROM tb_task WHERE id='" + id + "'";
            Connection conn = ConnectionFactory.getConexao();
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                ConnectionFactory.close(conn, stmt);
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static void update(Long id, Integer editTaskNum, String element) {
        if (validationNull(id) != null) {
            String sql = null;
            if (editTaskNum == 1) {
                sql = "UPDATE tb_task SET name='" + element + "'" + "WHERE id='" + id + "'";
            } else if (editTaskNum == 2) {
                sql = "UPDATE tb_task SET description='" + element + "'" + "WHERE id='" + id + "'";
            }
            Connection conn = ConnectionFactory.getConexao();
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                ConnectionFactory.close(conn, stmt);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Task> selectAll() {
        String sql = "SELECT id, name, description FROM tb_task";
        Connection conn = ConnectionFactory.getConexao();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Task> listTasks = new ArrayList<>();
            while (rs.next()) {
                listTasks.add(new Task(rs.getString("name"), rs.getString("description"), rs.getLong("id")));
            }
            ConnectionFactory.close(conn, stmt, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Task> selectId(Long id) {
        String sql = "SELECT * FROM tb_task WHERE id='" + id + "'";
        Connection conn = ConnectionFactory.getConexao();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            List<Task> listTask = new ArrayList<>();
            while (rs.next()) {
                listTask.add(new Task(rs.getString("name"), rs.getString("description"), rs.getLong("id")));
                if (!rs.next()) {
                    return listTask;
                }
            }
            String a = rs.toString();
            ConnectionFactory.close(conn, stmt, rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Integer validationNull(Long id) {
        String sql = "SELECT id FROM tb_task WHERE id='" + id + "'";
        Connection conn = ConnectionFactory.getConexao();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt("id");
            }
            ConnectionFactory.close(conn, stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
