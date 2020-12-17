package client.clientmodel.userModel;

import shared.util.Product;
import shared.util.ProductList;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * Interface is used for separating the view model from the model as
 * well as providing methods to the model manager.
 *
 * The interface extends property change subject to listen and fire events
 * to the listeners of this model.
 *
 * @author Hadi, Dorin
 */
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
