package server.networking.servermodel.userShoppingListServerModel;

import shared.util.Product;
import shared.util.PropertyChangeSubject;
import shared.util.ShopPrice;

import java.util.ArrayList;

public interface UserShoppingListServerModel extends PropertyChangeSubject
{
  ArrayList<Product> getThisUserShoppingList(String clientUsername);
  Boolean clearSL(String clientUsername);
  boolean addProductToSL(Product item, String clientUsername);
  ArrayList<ShopPrice> getThisUserPriceList(String clientUsername);
  Boolean deleteTheProductFromSL(String clientUsername, int productId);
  void changeQuantityForThisProduct(String clientUsername, int productId, int quantity);
  ArrayList<Product> getAvailableProducts(String shopName,String clientUsername);
  ArrayList<Product> getUnavailableProducts(String shopName,String clientUsername);
}
