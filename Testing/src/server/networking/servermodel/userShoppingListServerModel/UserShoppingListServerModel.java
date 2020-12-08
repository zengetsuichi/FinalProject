package server.networking.servermodel.userShoppingListServerModel;

import shared.util.Product;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface UserShoppingListServerModel extends PropertyChangeSubject
{
  ArrayList<Product> getThisUserShoppingList(String clientUsername);
  Boolean clearSL(String clientUsername);
  boolean addProductToSL(Product item, String clientUsername);
  Boolean deleteTheProductFromSL(String clientUsername, int productId);
  void changeQuantityForThisProduct(String clientUsername, int productId, int quantity);
}
