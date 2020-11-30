package dataaccess.administratorDAO;

import shared.util.ProductList;
import shared.util.ShopPrice;
import shared.util.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * An interface separating the data access objects from server models.
 * @author Gosia, Hadi
 */
public interface AdministratorDAO
{
  ProductList giveAllProductData() throws SQLException;
  ArrayList<ShopPrice> getShopPricesTableById(int productId) throws SQLException;
  ArrayList<String> getAllTagsById(int productId) throws SQLException;
  String editProduct(String productName, String productDescription, String category, ArrayList<String> parseTag,
      int productId) throws SQLException;
  String deleteProduct(int productId) throws SQLException;
  List<User> getAllUsers() throws SQLException;
  String addNewManager(User newManager) throws SQLException;
}
