package client.clientmodel.userModel;

import client.networking.Client;
import shared.util.EventType;
import shared.util.ProductList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class UserModelManager implements UserModel
{

  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Client client;

  public UserModelManager(Client client)
  {
    this.client = client;
  }

  @Override public void logOut()
  {
    client.logOut();
  }

  @Override public ProductList loadProductData()
  {
    return client.loadProductData();
  }

  @Override public String getLoggedInUser()
  {
    return client.getLoggedInUser();
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
