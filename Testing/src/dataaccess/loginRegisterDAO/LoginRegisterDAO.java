package dataaccess.loginRegisterDAO;

import shared.util.User;

import java.sql.SQLException;

/**
 * Interface is used for separating the server model manager from the data access object as
 * well as providing methods to the data access manager.
 *
 * @author Gosia, Dorin, Piotr
 */

public interface LoginRegisterDAO
{
  User findUser(String username) throws SQLException;
  User register(String username, String email, String password, String dob) throws SQLException;
  String findEmail(String email) throws SQLException;
}
