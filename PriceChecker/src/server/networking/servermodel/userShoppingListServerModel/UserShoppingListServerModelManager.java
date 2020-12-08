package server.networking.servermodel.userShoppingListServerModel;

import dataaccess.userDAO.UserDAO;
import shared.util.Product;
import shared.util.ShopPrice;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

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


  @Override public Boolean deleteTheProductFromSL(String clientUsername,
      int productId)
  {
    try
    {
      return userDAO.deleteTheProductFromSL(clientUsername, productId);
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
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }
}
