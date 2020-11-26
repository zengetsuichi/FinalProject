package dataaccess.loginRegisterDAO;

import shared.util.User;

import java.sql.SQLException;

public interface LoginRegisterDAO
{
  User findUser(String username) throws SQLException;
  User register(String username, String email, String password, String dob) throws SQLException;

  String findEmail(String email) throws SQLException;
}
