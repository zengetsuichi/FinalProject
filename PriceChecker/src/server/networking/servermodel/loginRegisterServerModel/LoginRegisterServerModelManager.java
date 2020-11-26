package server.networking.servermodel.loginRegisterServerModel;

import dataaccess.loginRegisterDAO.LoginRegisterDAO;
import shared.util.User;
import java.sql.SQLException;

/**
 * Class used for implementing the methods from LoginRegisterServerModel
 * @author Gosia, Piotr
 */

public class LoginRegisterServerModelManager implements LoginRegisterServerModel
{
  private LoginRegisterDAO loginRegisterDAO;

  public LoginRegisterServerModelManager(LoginRegisterDAO loginRegisterDAO){
    this.loginRegisterDAO = loginRegisterDAO;
  }

  /**
   * Method used for validating the login information send from the server
   * @param username
   * @param password
   * @author Gosia, Piotr
   */

  @Override public String validateLogin(String username, String password)
  {
    try
    {
      User user = loginRegisterDAO.findUser(username);
      if (user == null)
      {
        return "User with this username does not exist";
      }
      else
      {
        if (user.getPassword().equals(password))
        {
          return user.getType();
        }
        else
        {
          return "Wrong credentials";
        }
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return "Houston we have a problem someone fucked up the code.";
  }



}
