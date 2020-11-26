package dataaccess.loginRegisterDAO;

import dataaccess.DatabaseConnection;
import shared.util.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Class used for retrieving necessary information from the database.
 * @author Gosia, Piotr
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
       return new User(username, email, password, dob, type, userId);
     }
     else{
       return null;
     }
   }
  }
}
