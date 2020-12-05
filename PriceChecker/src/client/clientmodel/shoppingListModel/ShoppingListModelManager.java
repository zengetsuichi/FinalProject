package client.clientmodel.shoppingListModel;

import client.networking.Client;
import shared.util.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ShoppingListModelManager implements ShoppingListModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Client client;

  public ShoppingListModelManager(Client client)
  {
    this.client = client;
  }

  @Override public void addListener(String eventName, PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(eventName, listener);
  }

  @Override public void removeListener(String eventName, PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(eventName, listener);
  }

  @Override public ArrayList<Product> loadShoppingList()
  {
    return client.getThisUserShoppingList();
  }

  @Override public Boolean clearSL()
  {
    return client.clearSL();
  }
}
