package server.networking.servermodel.administratorUsersServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import shared.util.EventType;
import shared.util.User;

import java.sql.SQLException;
import java.util.List;

public class AdministratorUsersServerModelManager implements AdministratorUsersServerModel
{
  private AdministratorDAO administratorDAO;
  public AdministratorUsersServerModelManager(AdministratorDAO administratorDAO)
  {
    this.administratorDAO = administratorDAO;

  }

  @Override public List<User> getAllUsers()
  {
    try
    {
      return administratorDAO.getAllUsers();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}
