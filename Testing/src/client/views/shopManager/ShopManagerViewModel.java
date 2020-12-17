package client.views.shopManager;

import client.clientmodel.shopManagerModel.ShopManagerModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventType;
import shared.util.Product;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class ShopManagerViewModel
{
  private ShopManagerModel shopManagerModel;
  private ObservableList<Product> listOfAllProductsForSpecificManager;
  private ObservableList<String> tagsForSpecificProduct;

  public ShopManagerViewModel(ShopManagerModel shopManagerModel)
  {
    this.shopManagerModel = shopManagerModel;
    listOfAllProductsForSpecificManager = FXCollections.observableArrayList();
    tagsForSpecificProduct = FXCollections.observableArrayList();

    shopManagerModel.addListener(EventType.DELETED_PRODUCT_PRICE.name(), this::newProduct);
    shopManagerModel.addListener(EventType.EDIT_SHOP_MANAGER_PRODUCT.name(), this::newProduct);
    shopManagerModel.addListener(EventType.NEW_PRODUCT.name(), this::newProduct);
    shopManagerModel.addListener(EventType.DELETED_PRODUCT.name(), this::newProduct);
  }

  private void reloadData(PropertyChangeEvent propertyChangeEvent)
  {
    Platform.runLater(()-> {
      ArrayList<Product> productList = (ArrayList<Product>) propertyChangeEvent.getNewValue();
      listOfAllProductsForSpecificManager.setAll(productList);
    });
  }

  public String getLoggedInUser()
  {
    return shopManagerModel.getLoggedInUser();
  }

  public void loadProductData(String username)
  {
    ArrayList<Product> products = shopManagerModel.getAllProductsForSpecificManager(username);
    listOfAllProductsForSpecificManager.setAll(products);
  }

  public ObservableList<Product> getListOfAllProductsForSpecificManager()
  {
    return listOfAllProductsForSpecificManager;
  }

  public ObservableList<String> getAllTagsById(int productId)
  {
    ArrayList<String> tags = shopManagerModel.getAllTagsById(productId);
    tagsForSpecificProduct.setAll(tags);
    return tagsForSpecificProduct;
  }

  public void logOut()
  {
    shopManagerModel.logOut();
  }

  public String deleteProductPrice(int productId, String username)
  {
    return shopManagerModel.deleteProductPrice(productId, username);
  }
  private void newProduct(PropertyChangeEvent propertyChangeEvent)
  {
    Platform.runLater(()-> loadProductData(getLoggedInUser()));
  }
}
