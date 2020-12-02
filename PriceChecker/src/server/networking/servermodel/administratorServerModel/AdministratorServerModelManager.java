package server.networking.servermodel.administratorServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import shared.util.EventType;
import shared.util.ProductList;
import shared.util.ShopPrice;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class implementing the model interface. Used for requesting data from
 * the data access object as well as firing the events.
 *
 * Providing methods for; getting all products from the database, requesting
 * the data about specific product (such as shops proving the product and
 * shop prices), getting all tags assigned to the specific product.
 * Additionally it provides the method for deleting the specific product.
 *
 * @author Gosia, Karlo
 */

public class AdministratorServerModelManager implements AdministratorServerModel
{
  private AdministratorDAO administratorDAO;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public AdministratorServerModelManager(AdministratorDAO administratorDAO)
  {
    this.administratorDAO = administratorDAO;
  }

  @Override public ProductList loadProductData()
  {
    try
    {
      return administratorDAO.giveAllProductData();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<ShopPrice> getShopPricesById(int productId)
  {
    try
    {
      return administratorDAO.getShopPricesTableById(productId);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<String> getAllTagsById(int productId)
  {
    try
    {
      return administratorDAO.getAllTagsById(productId);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public String deleteProduct(int productId)
  {
    try
    {
      String deleteProduct = administratorDAO.deleteProduct(productId);
      if(deleteProduct.equals("Product deleted."))
        support.firePropertyChange(EventType.DELETED_PRODUCT.name(), null, administratorDAO.giveAllProductData());
      return deleteProduct;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return "Problem with deleting the product.";
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
