package client.clientmodel.shoppingListModel;

import shared.util.Product;
import shared.util.PropertyChangeSubject;
import shared.util.ShopPrice;

import java.util.ArrayList;

public interface ShoppingListModel extends PropertyChangeSubject
{
  ArrayList<Product> loadShoppingList();
  Boolean clearSL();
  ArrayList<ShopPrice> loadPricesList();
  Boolean deleteTheProductFromSL(int productId);
  void changeQuantityForThisProduct(int productId, int quantity);
  ArrayList<Product> getAvailableProducts(String shopName,String clientUsername);
  ArrayList<Product> getUnavailableProducts(String shopName,String clientUsername);
}
