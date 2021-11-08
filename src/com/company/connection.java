package com.company;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
    Connection conn=null;
    public Connection getdbconnection()
    {
        try {
            this.conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "charishma");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
