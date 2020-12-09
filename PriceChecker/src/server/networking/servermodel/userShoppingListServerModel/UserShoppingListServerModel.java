package server.networking.servermodel.userShoppingListServerModel;
import shared.util.Product;
import shared.util.PropertyChangeSubject;
import shared.util.ShopPrice;
import java.util.ArrayList;
/**
 * Interface is used for separating the server from the model as
 * well as providing methods to the model manager.
 *
 * The interface extends property change subject to listen and fire events
 * to the listeners of this model.
 *
 * @author Gosia, Karlo
 */
public interface UserShoppingListServerModel extends PropertyChangeSubject
{
  ArrayList<Product> getThisUserShoppingList(String clientUsername);
  Boolean clearSL(String clientUsername);
  boolean addProductToSL(Product item, String clientUsername);
  Boolean deleteTheProductFromSL(String clientUsername, int productId);
  void changeQuantityForThisProduct(String clientUsername, int productId, int quantity);

  ArrayList<ShopPrice> getThisUserPriceList(String clientUsername);
  ArrayList<Product> getAvailableProducts(String shopName,String clientUsername);
  ArrayList<Product> getUnavailableProducts(String shopName,String clientUsername);
}
