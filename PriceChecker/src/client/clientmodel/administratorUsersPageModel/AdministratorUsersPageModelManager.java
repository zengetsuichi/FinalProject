package client.clientmodel.administratorUsersPageModel;

import client.networking.Client;
import shared.util.EventType;
import shared.util.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
/**
 * Class implementing the model interface. Used for requesting data from
 * the client as well as listening for events and passing them forward.
 *
 * Providing methods for; getting all users from the database and
 * logging out of the session.
 *
 * @author Karlo
 */
public class AdministratorUsersPageModelManager
    implements AdministratorUsersPageModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Client client;

  public AdministratorUsersPageModelManager(Client client)
  {
    this.client = client;
    client.addListener(EventType.NEW_SHOP_MANAGER.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.EDIT_USER.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.DELETE_USER.name(), evt -> support.firePropertyChange(evt));

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
  @Override public String deleteUser(String username)
  {
    return client.deleteUser(username);
  }
}
