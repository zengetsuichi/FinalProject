package dataaccess.administratorDAO;

import shared.util.ProductList;
import shared.util.ShopPrice;
import shared.util.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface is used for separating the server model manager from the data access object as
 * well as providing methods to the data access manager.
 *
 * @author Gosia, Karlo
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
  void editUser(String oldUsername, String oldEmail, String username, String email, String password, String dob) throws SQLException;
  String deleteUser(String username) throws SQLException;
}
