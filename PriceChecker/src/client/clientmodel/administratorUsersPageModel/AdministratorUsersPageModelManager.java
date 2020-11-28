package client.clientmodel.administratorUsersPageModel;

import client.networking.Client;
import shared.util.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class AdministratorUsersPageModelManager
    implements AdministratorUsersPageModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Client client;

  public AdministratorUsersPageModelManager(Client client)
  {
    this.client = client;
  }

  @Override public List<User> getAllUsers()
  {
    return client.getAllUsers();
  }

  @Override public void logOut()
  {
    client.logOut();
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
