package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourse {

  public static Connection connect() {
    String db_file = "jdbc:sqlite:resources/bank.db";
    Connection connection = null;

    try {
      connection = DriverManager.getConnection(db_file);
      System.out.println("we're connected");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public static Customer getCustomer(String username) {
    String sql = "select * from customer where username = ?";
    Customer customer = null;
    try (Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql)) {

      statement.setString(1, username);
      try (ResultSet resultSet = statement.executeQuery()) {
        customer = new Customer(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("username"),
            resultSet.getString("passwoed"),
            resultSet.getInt("account_id"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return customer;
  }

  public static void main(String[] args) {

    Customer customer = getCustomer("ttoulchi5@ehow.com");
    System.out.println(customer.getName());
  }
}
