package client.clientmodel.administratorModel;
import client.networking.Client;
import shared.util.EventType;
import shared.util.ProductList;
import shared.util.ShopPrice;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
/**
 * Class implementing the model interface. Used for requesting data from
 * the client as well as listening for events and passing them forward.
 *
 * Providing methods for; getting all products from the database, requesting
 * the data about specific product (such as shops proving the product and
 * shop prices), getting all tags assigned to the specific product.
 * Additionally it provides the method for deleting the specific product and
 * logging out of the session.
 *
 * @author Gosia, Karlo
 */
public class AdministratorModelManager implements AdministratorModel
{
  private Client client;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public AdministratorModelManager(Client client)
  {
    this.client = client;
    client.addListener(EventType.NEW_PRODUCT.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.DELETED_PRODUCT.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.NEW_PRODUCT_MANAGER.name(), evt -> support.firePropertyChange(evt));
  }

  /**
   * Method used to request and return back all product data.
   * @author Gosia, Karlo
   */
  @Override public ProductList loadProductData()
  {
    return client.loadProductData();
  }

  @Override public ArrayList<ShopPrice> getShopPricesById(int productId)
  {
    return client.getShopPricesById(productId);
  }

  @Override public ArrayList<String> getAllTagsById(int productId)
  {
    return client.getAllTagsById(productId);
  }

  @Override public String deleteProduct(int productId)
  {
    return client.deleteProduct(productId);
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
