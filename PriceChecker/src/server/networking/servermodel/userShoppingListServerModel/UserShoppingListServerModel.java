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
}
