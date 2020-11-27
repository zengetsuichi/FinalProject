package server.networking.servermodel.editProductAdminServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import shared.util.EventType;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditProductAdminServerModelManager implements EditProductAdminServerModel
{
  private AdministratorDAO administratorDAO;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public EditProductAdminServerModelManager(AdministratorDAO administratorDAO)
  {
    this.administratorDAO = administratorDAO;
  }

  @Override public String editProduct(String productName,
      String productDescription, String category, ArrayList<String> parseTag,
      int productId)
  {
    try
    {
      String product = administratorDAO
          .editProduct(productName, productDescription, category, parseTag, productId);
      if(product.equals("Product updated."))
        support.firePropertyChange(EventType.NEW_PRODUCT.name(), null, administratorDAO.giveAllProductData());
      return product;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
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
