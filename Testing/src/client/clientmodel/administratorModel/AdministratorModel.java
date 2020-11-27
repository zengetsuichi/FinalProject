package client.clientmodel.administratorModel;
import shared.util.ProductList;
import shared.util.PropertyChangeSubject;
import shared.util.ShopPrice;

import java.util.ArrayList;
/**
 * Interface is used for separating the view model from the model as
 * well as providing abstract classes to the model manager.
 *
 * The interface extends property change subject to listen and fire events
 * to the listeners of this model.
 *
 * @author Gosia, Karlo
 */
public interface AdministratorModel extends PropertyChangeSubject
{
  ProductList loadProductData();
  ArrayList<ShopPrice> getShopPricesById(int productId);
  ArrayList<String> getAllTagsById(int productId);
  String deleteProduct(int productId);
  void logOut();
}
