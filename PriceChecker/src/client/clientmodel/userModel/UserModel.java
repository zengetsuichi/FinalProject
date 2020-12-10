package client.clientmodel.userModel;

import shared.util.Product;
import shared.util.ProductList;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface UserModel extends PropertyChangeSubject
{
  ProductList loadProductData();
  String getLoggedInUser();
  void logOut();
  ArrayList<Product> getThisUserShoppingList();
  /**
   *  Adding the product to the shopping list
   *  @author Karlo, Gosia
  */
  boolean addProductToSL(Product item);
  ArrayList<String> getAllProductCategories();
  ArrayList<String> getTagsById(int productId);
}
