package server.networking.servermodel.addNewProductShopManagerServerModel;

import dataaccess.addNewProductShopManagerDAO.AddNewProductShopManagerDAO;
import shared.util.EventType;
import shared.util.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddNewProductShopManagerServerModelManager implements AddNewProductShopManagerServerModel {

    private AddNewProductShopManagerDAO addNewProductShopManagerDAO;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public AddNewProductShopManagerServerModelManager(AddNewProductShopManagerDAO addNewProductShopManagerDAO) {
        this.addNewProductShopManagerDAO = addNewProductShopManagerDAO;
    }


    @Override
    public ArrayList<Product> getAllProducts() {
        try
        {
            return addNewProductShopManagerDAO.getAllProducts();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product> getAllProductsFor(String username) {
        try
        {
            return addNewProductShopManagerDAO.getAllProductsFor(username);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String editNewProduct(int userId,int price, int productid) {
        try
        {
            support.firePropertyChange(EventType.NEW_PRODUCT_MANAGER.name(), null, getAllProducts());
            return addNewProductShopManagerDAO.editNewProduct(userId,price,productid);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int getUserId(String username) {
        int index = 0;
        try
        {
             index = addNewProductShopManagerDAO.getUserId(username);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return index;
    }


    @Override
    public ArrayList<String> getAllProductCategories() {
        try
        {
            return addNewProductShopManagerDAO.getAllProductCategories();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }


    @Override
    public ArrayList<String> getAllTags() {
        try
        {
            return addNewProductShopManagerDAO.getAllTags();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }



    @Override
    public String addNewProduct(String clientUsername, String productName, String productDescription, String category, ArrayList<String> parseTag, int price) {
        try {
            String response = addNewProductShopManagerDAO.addNewProduct(clientUsername,productName, productDescription, category, parseTag,price);
            if(response.equals("Product added.")){
                support.firePropertyChange(EventType.NEW_PRODUCT_MANAGER.name(), null, getAllProducts());
                return response;
            }else
                return response;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }




    @Override
    public String addNewCategory(String newCategory) {
        try {
            String response = addNewProductShopManagerDAO.addNewCategory(newCategory);
            if (response.equals("Category added.")) {
                return response;
            } else {
                return response;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public String addNewTag(String newTag) {
        try
        {
            return addNewProductShopManagerDAO.addNewTag(newTag);
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
