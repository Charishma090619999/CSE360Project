package com.company;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login {

    public String login(String Username, String Password) {
        // write your code here
        try {
            connection conn=new connection();
            Connection connection=conn.getdbconnection();
            String query = "SELECT * FROM users where username = '" + Username + "' and password = '" + Password + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery(query);
            if (!resultSet.next() ) {
                return "Invalid credentials";
            }
            else{
                return "logged in successfully";
            }


        }
        catch(Exception e)
        {
            return "Connection has not established";
        }

    }
}
