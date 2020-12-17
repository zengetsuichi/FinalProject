package server.networking.servermodel.loginRegisterServerModel;

import dataaccess.loginRegisterDAO.LoginRegisterDAO;
import shared.util.User;
import java.sql.SQLException;

/**
 * Class implementing the model interface. Used for requesting data from
 * the data access object and firing events.
 *
 * Providing methods for; validating the login process and
 * validating register.
 *
 * @author Gosia, Karlo
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
    return "Houston we have a problem.";
  }

  /**
   * Method used for validating the login information send from the server
   *
   * @param username
   * @param password
   * @param email
   * @param dob
   * @author Gosia, Piotr
   */

  @Override
  public String validateRegister(String username, String email, String password, String dob) {

    String result = null;
    try {
      User user = loginRegisterDAO.findUser(username);
      String userEmail = loginRegisterDAO.findEmail(email);
      if (user != null) {
        //  if (user.getUsername().equals(username))
        return "User with this username already exist";

      } else if (userEmail != null) {
        //if (userEmail.equals(email))
        return "Email already used";
      } else {
        loginRegisterDAO.register(username, email, password, dob);
        return "User registered";
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return "Houston we have a problem.";

  }



}
