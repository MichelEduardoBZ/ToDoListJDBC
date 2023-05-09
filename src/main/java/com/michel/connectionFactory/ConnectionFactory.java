package com.michel.connectionFactory;

import java.sql.*;

public class ConnectionFactory {

    public static Connection getConexao(){
        String url = "jdbc:mysql://localhost:3306/task?useSSL=false";
        String user = "root";
        String password = "bimbo1804";
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Abaixo irá ter as sobrecargas para fechar o Connection, Statement e o ResultSet
    public static void close(Connection connection){
        try {
            if(connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection connection, Statement stmt){
        close(connection);
        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection connection, Statement stmt, ResultSet rs){
        close(connection, stmt);
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
