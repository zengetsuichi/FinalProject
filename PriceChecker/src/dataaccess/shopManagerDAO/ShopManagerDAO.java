package dataaccess.shopManagerDAO;

import shared.util.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShopManagerDAO
{
  ArrayList<Product> getAllProductsForSpecificManager(String username) throws
      SQLException;
  String deleteProductPrice(int productId, String username)  throws SQLException;
}
