package lk.ijse.myap.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {



private final String DB_URL="jdbc:mysql://localhost:3306/myap";
private final String DB_USERNAME="root";
private final String DB_PASSWORD="mysql";

    private static DBConnection dbc;
    private Connection connection;

  private DBConnection() throws SQLException {
      connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
      System.out.println("Connected to database successfully");
  }

    public static DBConnection getInstance() throws SQLException {
      return (dbc== null? dbc=new DBConnection():dbc);
    }

    public Connection getConnection() {
      return connection;
    }
}
