package dataaccess.loginRegisterDAO;

import shared.util.User;

import java.sql.SQLException;

public interface LoginRegisterDAO
{
  User findUser(String username) throws SQLException;
}
