package client.clientmodel.addNewProductShopManagerModel;

import client.networking.Client;
import shared.util.EventType;
import shared.util.Product;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * Class implementing the model interface. Used for requesting data from
 * the client as well as listening for events and passing them forward.
 *
 * Providing methods for; getting all product categories, getting all product
 * tags, adding new products to the database, adding new categories and adding
 * new tags for the products.
 *
 * @author Piotr
 */

public class AddNewProductShopManagerModelManager implements AddNewProductShopManagerModel{

    private Client client;
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    public AddNewProductShopManagerModelManager(Client client) {
        this.client = client;

        client.addListener(EventType.NEW_PRODUCT.name(), evt -> support.firePropertyChange(evt));
    }


    @Override
    public ArrayList<String> getAllProductCategories() {
        return  client.getAllProductCategories();
    }

    @Override
    public ArrayList<String> getAllTags() {
        return  client.getAllTags();
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return client.getAllProducts();
    }

    @Override
    public String addNewProduct(String productName, String productDescription, String category, ArrayList<String> parseTag, int price) {
       return client.addNewProductShopManager(productName,productDescription,category,parseTag,price);
    }

    @Override
    public String addNewCategory(String newCategory) {
      return   client.addNewCategory(newCategory);
    }

    @Override
    public String addNewTag(String newTag) {
        return client.addNewTag(newTag);
    }



    @Override
    public String getLoggedInUser() {
        return  client.getLoggedInUser();
    }



    @Override
    public ArrayList<Product> getAllProductsFor() {
        return client.getAllProductsFor();
    }

    @Override
    public ArrayList<String> getAllTagsById(int productId) {
        return client.getAllTagsById(productId);
    }

    @Override
    public String editNewProduct(int userId, int price, int productid) {
        return client.editNewProduct(userId,price,productid);
    }

    @Override
    public int getUserId(String username) {


        return client.getUserId(username);
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
