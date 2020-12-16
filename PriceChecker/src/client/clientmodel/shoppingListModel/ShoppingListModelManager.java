package client.clientmodel.shoppingListModel;
import client.networking.Client;
import shared.util.EventType;
import shared.util.Product;
import shared.util.ShopPrice;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
/**
 * Class implementing the model interface. Used for requesting data from
 * the client as well as listening for events and passing them forward.
 *
 * Providing methods for; loading the shopping list for current user, clearing
 * the shopping list of that current user, deleting chosen product from the
 * shopping list and changing the quantity of chosen product.
 *
 * @author Karlo, Gosia
 */
public class ShoppingListModelManager implements ShoppingListModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private Client client;

  public ShoppingListModelManager(Client client)
  {
    this.client = client;
    client.addListener(EventType.DELETED_PRODUCT_PRICE.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.EDIT_SHOP_MANAGER_PRODUCT.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.NEW_PRODUCT.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.DELETED_PRODUCT.name(), evt -> support.firePropertyChange(evt));
    client.addListener(EventType.SHOPPING_LIST_CHANGE.name(),evt -> support.firePropertyChange(evt));
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

  @Override public Boolean deleteTheProductFromSL(int productId)
  {
    return client.deleteTheProductFromSL(productId);
  }

  @Override public void changeQuantityForThisProduct(int productId,
      int quantity)
  {
    client.changeQuantityForThisProduct(productId, quantity);
  }

  @Override public ArrayList<ShopPrice> loadPricesList()
  {
    return client.getThisUserPricesList();
  }

  @Override public ArrayList<Product> getAvailableProducts(String shopName,String clientUsername)
  {
    return client.getAvailableProducts(shopName,clientUsername);
  }

  @Override public ArrayList<Product> getUnavailableProducts(String shopName,String clientUsername)
  {
    return client.getUnavailableProducts(shopName,clientUsername);
  }
}
