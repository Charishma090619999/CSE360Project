package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignUp {
    public String signup(String Firstname, String Lastname, String Username, String Password) {
        // write your code here
        try {
            connection conn = new connection();
            Connection connection = conn.getdbconnection();
            Statement s=connection.createStatement();
            ResultSet r=s.executeQuery("select * from users where username='" + Username + "'");
            if(r.next())
            {
               return "Username already exists cannot be created";
            }
            else{
            Statement s1=connection.createStatement();
            ResultSet resultset=s1.executeQuery("SELECT count(*) from users");
            resultset.next();
            int id=resultset.getInt(1)+1;
            String query = "INSERT INTO users values ('" + id + "','" + Firstname + "','" + Lastname
                    + "','" + Username + "','" + Password + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return "Details have been inserted";}
        } catch (Exception e) {
            return "Connection has not been established";
        }
    }
}
