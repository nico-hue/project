package main.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

    public Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project",
                    "root",
                    "131098");
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }
}
