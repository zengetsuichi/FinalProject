package client.clientmodel.shopManagerModel;


import shared.util.Product;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;
/**
 * Interface is used for separating the view model from the model as
 * well as providing methods to the model manager.
 *
 * The interface extends property change subject to listen and fire events
 * to the listeners of this model.
 *
 * @author Gosia
 */
public interface ShopManagerModel extends PropertyChangeSubject
{
  String getLoggedInUser();
  ArrayList<Product> getAllProductsForSpecificManager(String username);
  ArrayList<String> getAllTagsById(int productId);
  void logOut();
  String deleteProductPrice(int productId, String username);
}
