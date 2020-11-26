package client.clientmodel.shopManagerModel;

import client.networking.Client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShopManagerModelManager implements ShopManagerModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Client client;

  public ShopManagerModelManager(Client client)
  {
    this.client = client;
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

  @Override public String getLoggedInUser()
  {
    return client.getLoggedInUser();
  }
}
