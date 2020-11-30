package server.networking.servermodel.administratorUsersServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import shared.util.EventType;
import shared.util.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.List;

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
}
