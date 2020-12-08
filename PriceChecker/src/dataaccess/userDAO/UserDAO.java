package dataaccess.userDAO;

import shared.util.Product;
import shared.util.ShopPrice;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO
{
  ArrayList<Product> getThisUserShoppingList(String clientUsername) throws
      SQLException;
  Boolean clearSL(String clientUsername) throws SQLException;
  boolean addProductToSL(Product item, String clientUsername) throws SQLException;
  ArrayList<ShopPrice> getThisUserPriceList(String clientUsername)throws SQLException;
}
