package server.networking.servermodel.addNewProductAdminServerModel;
import dataaccess.addNewProductAdminDAO.AddNewProductAdminDAO;
import dataaccess.administratorDAO.AdministratorDAO;
import shared.util.EventType;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddNewProductAdminServerModelManager implements AddNewProductAdminServerModel
{
  private AddNewProductAdminDAO addNewProductAdminDAO;
  private AdministratorDAO administratorDAO;
  private PropertyChangeSupport support = new PropertyChangeSupport(this);

  public AddNewProductAdminServerModelManager(AddNewProductAdminDAO addNewProductAdminDAO, AdministratorDAO administratorDAO)
  {
    this.addNewProductAdminDAO = addNewProductAdminDAO;
    this.administratorDAO = administratorDAO;
  }

  @Override public ArrayList<String> getAllProductCategories()
  {
    try
    {
      return addNewProductAdminDAO.getAllProductCategories();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public ArrayList<String> getAllTags()
  {
    try
    {
      return addNewProductAdminDAO.getAllTags();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public String addNewProduct(String productName,
      String productDescription, String category, ArrayList<String> parseTag)
  {
    try
    {
      String response =  addNewProductAdminDAO.addNewProduct(productName, productDescription, category, parseTag);
      if(response.equals("Product added.")){
        support.firePropertyChange(EventType.NEW_PRODUCT.name(), null, administratorDAO.giveAllProductData());
        return response;
      }
      else{
        return response;
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public String addNewCategory(String newCategory)
  {
    try
    {
      String response =  addNewProductAdminDAO.addNewCategory(newCategory);
      if(response.equals("Category added.")){
        support.firePropertyChange(EventType.NEW_CATEGORY.name(), null, addNewProductAdminDAO.getAllProductCategories());
        return response;
      }
      else{
        return response;
      }
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override public String addNewTag(String newTag)
  {
    try
    {
      String response = addNewProductAdminDAO.addNewTag(newTag);
      if(response.equals("Tag added.")){
        support.firePropertyChange(EventType.NEW_TAG.name(), null, addNewProductAdminDAO.getAllTags());
        return response;
      }
      else{
        return response;
      }
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
