package client.clientmodel.userModel;

import client.networking.Client;
import shared.util.EventType;
import shared.util.Product;
import shared.util.ProductList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class UserModelManager implements UserModel
{

  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Client client;

  public UserModelManager(Client client)
  {
    this.client = client;
    client.addListener(EventType.NEW_CATEGORY.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.DELETED_PRODUCT.name(), evt -> support.firePropertyChange(evt));
  }

  @Override public void logOut()
  {
    client.logOut();
  }

  @Override public ArrayList<Product> getThisUserShoppingList()
  {
    return client.getThisUserShoppingList();
  }

  @Override public boolean addProductToSL(Product item)
  {
    return client.addProductToSL(item);
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
