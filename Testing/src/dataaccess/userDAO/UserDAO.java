package dataaccess.userDAO;

import shared.util.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO
{
  ArrayList<Product> getThisUserShoppingList(String clientUsername) throws
      SQLException;
  Boolean clearSL(String clientUsername) throws SQLException;
  boolean addProductToSL(Product item, String clientUsername) throws SQLException;
  Boolean deleteTheProductFromSL(String clientUsername, int productId) throws SQLException;
  void changeQuantityForThisProduct(String clientUsername, int productId, int quantity) throws SQLException;
}
