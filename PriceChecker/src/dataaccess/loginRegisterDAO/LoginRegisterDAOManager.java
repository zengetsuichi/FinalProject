package dataaccess.loginRegisterDAO;

import dataaccess.DatabaseConnection;
import shared.util.User;

import java.sql.*;


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
   try(Connection connection = databaseConnection.getConnection()){
     PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
     statement.setString(1, username);
     ResultSet resultSet = statement.executeQuery();
     if(resultSet.next()){
       int userId = resultSet.getInt("userId");
       username = resultSet.getString("username");
       String email = resultSet.getString("email");
       String password = resultSet.getString("password");
       String dob = resultSet.getString("dob");
       String type = resultSet.getString("type");
       return new User(username, email, password, dob, type, userId, false);
     }
     else{
       return null;
     }
   }
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
  public User register(String username, String email, String password, String dob) throws SQLException {
    try (Connection connection = databaseConnection.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("INSERT INTO users(username, email, password, dob, type) VALUES(? , ? , ? , ? , ?)");
      statement.setString(1, username);
      statement.setString(2, email);
      statement.setString(3, password);
      Date date=Date.valueOf(dob);
      // statement.setDate(4,new Date(622790105000L));
      statement.setDate(4,date);
      //statement.setString(4,date);
      statement.setString(5,"User");
      statement.executeUpdate();

      return null;

    }}

  /**
   * Method used for retrieving the data from database about the specific user to check if the email already exist
   *
   * @param email - the email of the person whose data we want to retrieve
   * @author Dorin, Piotr
   */

  @Override
  public String findEmail(String email) throws SQLException {
    try (Connection connection = databaseConnection.getConnection()) {
      PreparedStatement statement = connection.prepareStatement("SELECT email FROM users WHERE email = ?");
      statement.setString(1, email);
      ResultSet resultSet = statement.executeQuery();
      if(resultSet.next()) {
        return resultSet.getString("email");
      } else {
        return null;
      }
    }
  }
}
