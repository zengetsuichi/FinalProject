package client.views.user;

import client.clientmodel.userModel.UserModel;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import shared.util.EventType;
import shared.util.Product;
import shared.util.ProductList;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserViewModel
{
  private UserModel userModel;
  private ObservableList<Product> listOfAllProducts;
  private ObservableList<Product> shoppingList;
  private ObservableList<String> allProductCategories;

  public UserViewModel(UserModel userModel)
  {
    this.userModel = userModel;
    listOfAllProducts = FXCollections.observableArrayList();
    shoppingList = FXCollections.observableArrayList();
    allProductCategories = FXCollections.observableArrayList();

    userModel.addListener(EventType.NEW_PRODUCT.name(), this::newProduct);
    userModel.addListener(EventType.DELETED_PRODUCT.name(), this::newProduct);
    userModel.addListener(EventType.NEW_CATEGORY.name(), this::newCategory);
    userModel.addListener(EventType.EDIT_SHOP_MANAGER_PRODUCT.name(), this::newProduct);
  }


  public void loadData()
  {
    allProductCategories.setAll(userModel.getAllProductCategories());
  }

  private void newCategory(PropertyChangeEvent event)
  {
    Platform.runLater(() -> {
      ArrayList<String> newCategories = (ArrayList<String>) event.getNewValue();
      allProductCategories.setAll(newCategories);
    });
  }

  private void newProduct(PropertyChangeEvent propertyChangeEvent)
  {
    Platform.runLater(()-> {
      ProductList productList = (ProductList) propertyChangeEvent.getNewValue();
      listOfAllProducts.setAll(productList.getProducts());
    });
  }


  public void loadProductData()
  {
    listOfAllProducts.setAll(userModel.loadProductData().getProducts());
  }

  public ObservableList<Product> getListOfAllProducts(){
    return listOfAllProducts;
  }

  public void logOut()
  {
    userModel.logOut();
  }

  public String getLoggedInUser()
  {
    return userModel.getLoggedInUser();
  }

  public void loadShoppingList()
  {
    shoppingList.setAll(userModel.getThisUserShoppingList());
  }

  public ObservableList<Product> getThisUserShoppingList()
  {
    return shoppingList;
  }

  public void addProductToSL(Product item)
  {
    boolean added = userModel.addProductToSL(item);
    if(added)
      loadShoppingList();
  }

  public ObservableList<String> getAllProductCategories()
  {
    return allProductCategories;
  }

  public ArrayList<String> getTagsById(int productId)
  {
    return userModel.getTagsById(productId);
  }
}
