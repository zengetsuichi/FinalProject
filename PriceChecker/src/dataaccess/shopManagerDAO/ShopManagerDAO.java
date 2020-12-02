package dataaccess.shopManagerDAO;

import shared.util.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ShopManagerDAO
{
  ArrayList<Product> getAllProductsForSpecificManager(String username) throws
      SQLException;
  String deleteProductPrice(int productId, String username)  throws SQLException;
  ArrayList<String> getAllTagsById(int productId) throws SQLException;
  String editShopProduct(String productName, String productDescription, String category, ArrayList<String> parseTag,
      int productId,int price,String username) throws SQLException;
}
