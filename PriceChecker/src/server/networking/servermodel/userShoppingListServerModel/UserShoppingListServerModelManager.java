package server.networking.servermodel.userShoppingListServerModel;
import dataaccess.userDAO.UserDAO;
import shared.util.EventType;
import shared.util.Product;
import shared.util.ShopPrice;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Class implementing the model interface. Used for requesting data from
 * the data access object and firing events.
 *
 * Providing methods for; getting the shopping list for a specific user,
 * clearing the shopping list, adding a product to the shopping list of a
 * specific user, deleting one product for a specific user and changing the
 * quantity for a specific product.
 *
 * @author Gosia, Karlo
 */
public class UserShoppingListServerModelManager implements UserShoppingListServerModel
{
  private PropertyChangeSupport support = new PropertyChangeSupport(this);
  private UserDAO userDAO;

  public UserShoppingListServerModelManager(UserDAO userDAO)
  {
    this.userDAO = userDAO;
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

  @Override public ArrayList<Product> getThisUserShoppingList(
      String clientUsername)
  {
    try
    {
      return userDAO.getThisUserShoppingList(clientUsername);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public Boolean clearSL(String clientUsername)
  {
    try
    {
      return userDAO.clearSL(clientUsername);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public boolean addProductToSL(Product item, String clientUsername)
  {
    try
    {
      return userDAO.addProductToSL(item, clientUsername);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return false;
  }

  @Override public Boolean deleteTheProductFromSL(String clientUsername,
      int productId)
  {
    try
    {
      Boolean deleteProduct = userDAO.deleteTheProductFromSL(clientUsername, productId);
      if(deleteProduct)
        support.firePropertyChange(EventType.SHOPPING_LIST_CHANGE.name(), null,userDAO.getThisUserPriceList(clientUsername));
      return deleteProduct;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return false;
  }

  @Override public void changeQuantityForThisProduct(String clientUsername,
      int productId, int quantity)
  {
    try
    {
      userDAO.changeQuantityForThisProduct(clientUsername, productId, quantity);
      support.firePropertyChange(EventType.SHOPPING_LIST_CHANGE.name(), null,userDAO.getThisUserPriceList(clientUsername));
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
  /**
   * Methods used for receiving information from the server, such as users price list,
   * available and unavailable products.
   * @author Hadi, Piotr
   */
  @Override public ArrayList<ShopPrice> getThisUserPriceList(
      String clientUsername)
  {
    try
    {
      return userDAO.getThisUserPriceList(clientUsername);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Product> getAvailableProducts(String shopName,String clientUsername)
  {
    try
    {
      return userDAO.getAvailableProducts(shopName,clientUsername);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<Product> getUnavailableProducts(String shopName,String clientUsername)
  {
    try
    {
      return userDAO.getUnavailableProducts(shopName,clientUsername);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }
}
