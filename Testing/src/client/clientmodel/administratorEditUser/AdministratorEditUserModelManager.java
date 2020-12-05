package client.clientmodel.administratorEditUser;

import client.networking.Client;
import shared.util.EventType;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AdministratorEditUserModelManager implements AdministratorEditUserModel
{
  private Client client;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public AdministratorEditUserModelManager(Client client)
  {
    this.client = client;

  }

  @Override public String validateEditUser(String oldUsername, String oldEmail, String username, String email, String password, String dob)
  {
    return client.validateEditUser(oldUsername, oldEmail, username,email,password,dob);
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
