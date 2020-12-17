package client.clientmodel.shoppingListModel;
import shared.util.Product;
import shared.util.PropertyChangeSubject;
import shared.util.ShopPrice;
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
public interface ShoppingListModel extends PropertyChangeSubject
{
  ArrayList<Product> loadShoppingList();
  Boolean clearSL();
  Boolean deleteTheProductFromSL(int productId);
  void changeQuantityForThisProduct(int productId, int quantity);
  ArrayList<Product> getAvailableProducts(String shopName,String clientUsername);
  ArrayList<Product> getUnavailableProducts(String shopName,String clientUsername);
  ArrayList<ShopPrice> loadPricesList();
}
