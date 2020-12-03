package server.networking.servermodel.addNewProductShopManagerServerModel;

import dataaccess.addNewProductShopManagerDAO.AddNewProductShopManagerDAO;
import shared.util.EventType;
import shared.util.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.SQLException;
import java.util.ArrayList;

public class addNewProductShopManagerServerModelManager implements AddNewProductShopManagerServerModel {

    private AddNewProductShopManagerDAO addNewProductShopManagerDAO;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public addNewProductShopManagerServerModelManager(AddNewProductShopManagerDAO addNewProductShopManagerDAO) {
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
                support.firePropertyChange(EventType.NEW_PRODUCT.name(), null, getAllProducts());
                support.firePropertyChange(EventType.NEW_PRODUCT_FOR_MANAGER.name(), null, getAllProductsFor(clientUsername));
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
