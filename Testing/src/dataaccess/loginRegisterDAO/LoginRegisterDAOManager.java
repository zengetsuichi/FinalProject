package dataaccess.loginRegisterDAO;

import dataaccess.DatabaseConnection;
import shared.util.User;

import java.sql.*;
import java.util.ArrayList;

/**
 * Class implementing the data access interface. Used for requesting data from
 * the database.
 *
 * Providing methods for; retrieving the data about the users, registering the user and
 * searching for a specific email in the database.
 *
 * @author Gosia, Piotr, Dorin
 */

public class LoginRegisterDAOManager implements LoginRegisterDAO
{

  private DatabaseConnection databaseConnection;

  public LoginRegisterDAOManager() throws SQLException
  {
    databaseConnection = DatabaseConnection.getInstance();
  }

  /**
   * Method used for retrieving the data from database about the specific user.
   * @param username - the username of the person whose data we want to retrieve
   * @author Gosia, Piotr
   */
  @Override public User findUser(String username) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    User user = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
      statement.setString(1, username);
      resultSet = statement.executeQuery();
      if(resultSet.next()){
        int userId = resultSet.getInt("userId");
        username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String dob = resultSet.getString("dob");
        String type = resultSet.getString("type");
        user = new User(username, email, password, dob, type, userId, false);
      }
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return user;
  }

  /**
   * Method used for inserting the data to database about the user.
   *
   * @param username - the username of the person system registers
   * @param email - the email of the person system registers
   * @param password - the password of the person system registers
   * @param dob - date of birth of the person system registers
   * @author Dorin, Piotr
   */

  @Override
  public User register(String username, String email, String password, String dob) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    User user = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("INSERT INTO users(username, email, password, dob, type) VALUES(? , ? , ? , ? , ?)");
      statement.setString(1, username);
      statement.setString(2, email);
      statement.setString(3, password);
      Date date = Date.valueOf(dob);
      statement.setDate(4,date);
      statement.setString(5,"User");
      statement.executeUpdate();
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return user;
  }

  /**
   * Method used for retrieving the data from database about the specific user to check if the email already exist
   *
   * @param email - the email of the person whose data we want to retrieve
   * @author Dorin, Piotr
   */

  @Override
  public String findEmail(String email) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String returnStatement = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT email FROM users WHERE email = ?");
      statement.setString(1, email);
      resultSet = statement.executeQuery();
      if(resultSet.next())
        returnStatement = resultSet.getString("email");
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return returnStatement;
  }
}
