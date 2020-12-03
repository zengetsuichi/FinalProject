package client.clientmodel.shopManagerModel;

import client.networking.Client;
import shared.util.EventType;
import shared.util.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
/**
 * Class implementing the model interface. Used for requesting data from
 * the client as well as listening for events and passing them forward.
 *
 * Providing methods for; getting the user name of the currently logged in shop manager,
 * requesting all the products assigned to the specific shop manager,
 * getting all tags assigned to a specific product, deleting the
 * price from a product and logging out of the session.
 *
 * @author Gosia
 */

public class ShopManagerModelManager implements ShopManagerModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Client client;

  public ShopManagerModelManager(Client client)
  {
    this.client = client;
    client.addListener(EventType.NEW_PRODUCT_FOR_MANAGER.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.DELETED_PRODUCT_PRICE.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.EDIT_SHOP_MANAGER_PRODUCT.name(), evt -> support.firePropertyChange(evt));
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

  @Override public ArrayList<Product> getAllProductsForSpecificManager(
      String username)
  {
    return client.getAllProductsForSpecificManager(username);
  }

  @Override public ArrayList<String> getAllTagsById(int productId)
  {
    return client.getAllTagsById(productId);
  }

  @Override public void logOut()
  {
    client.logOut();
  }

  @Override public String deleteProductPrice(int productId, String username)
  {
    return client.deleteProductPrice(productId, username);
  }
}
