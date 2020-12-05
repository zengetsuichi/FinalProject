package server.networking.servermodel.administratorUsersServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import shared.util.EventType;
import shared.util.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;

/**
 * Class implementing the model interface. Used for requesting data from
 * the data access object as well as firing the events.
 *
 * Providing methods for; getting all users from the database and adding
 * new manager.
 *
 * @author Karlo
 */

public class AdministratorUsersServerModelManager implements AdministratorUsersServerModel
{
  private AdministratorDAO administratorDAO;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

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

  @Override public String addNewManager(User newManager)
  {
    try
    {
      String response = administratorDAO.addNewManager(newManager);
      if(response.equals("Shop manager added.")){
        support.firePropertyChange(EventType.NEW_SHOP_MANAGER.name(), null, administratorDAO.getAllUsers());
        return response;
      }
      else{
        return response;
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }


  @Override public void addListener(String eventName,
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName,
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }
  @Override public String deleteUser(String username)
  {
    try
    {
      String response = administratorDAO.deleteUser(username);
      if(response.equals("User Deleted.")){
        support.firePropertyChange(EventType.DELETE_USER.name(), null, administratorDAO.getAllUsers());
        return response;
      }
      else{
        return response;
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}
