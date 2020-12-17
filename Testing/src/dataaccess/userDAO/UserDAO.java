package dataaccess.userDAO;

import shared.util.Product;
import shared.util.ShopPrice;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Interface is used for separating the server model manager from the data access object as
 * well as providing methods to the data access manager.
 *
 * @author Gosia
 */
public interface UserDAO
{
  ArrayList<Product> getThisUserShoppingList(String clientUsername) throws
      SQLException;
  Boolean clearSL(String clientUsername) throws SQLException;
  boolean addProductToSL(Product item, String clientUsername) throws SQLException;
  Boolean deleteTheProductFromSL(String clientUsername, int productId) throws SQLException;
  void changeQuantityForThisProduct(String clientUsername, int productId, int quantity) throws SQLException;

  ArrayList<ShopPrice> getThisUserPriceList(String clientUsername)throws SQLException;
  ArrayList<Product> getAvailableProducts(String shopName,String clientUsername)throws SQLException;
  ArrayList<Product> getUnavailableProducts(String shopName,String clientUsername)throws SQLException;
}
