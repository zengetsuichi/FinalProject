package server.networking.servermodel.shopManagerServerModel;

import dataaccess.shopManagerDAO.ShopManagerDAO;
import shared.util.EventType;
import shared.util.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopManagerServerModelManager implements ShopManagerServerModel
{
  private ShopManagerDAO shopManagerDAO;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public ShopManagerServerModelManager(ShopManagerDAO shopManagerDAO)
  {
    this.shopManagerDAO = shopManagerDAO;
  }

  @Override public ArrayList<Product> getAllProductsForSpecificManager(
      String username)
  {
    try
    {
      return shopManagerDAO.getAllProductsForSpecificManager(username);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public String deleteProductPrice(int productId, String username)
  {
    try
    {
      String deleteProduct = shopManagerDAO.deleteProductPrice(productId, username);
      if(deleteProduct.equals("Product deleted."))
        support.firePropertyChange(EventType.DELETED_PRODUCT_PRICE.name(), null, shopManagerDAO.getAllProductsForSpecificManager(username));
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
