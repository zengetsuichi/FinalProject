package dataaccess.administratorDAO;

import dataaccess.DatabaseConnection;
import shared.util.Product;
import shared.util.ProductList;
import shared.util.ShopPrice;
import shared.util.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Class implementing the data access interface. Used for requesting data from
 * the database.
 *
 * Providing methods for; getting all products from the database, requesting
 * the data about specific product (such as shops providing the product and
 * shop prices), getting all tags assigned to the specific product.
 * Additionally it provides the method for deleting the specific product,
 * editing the product, getting all user data, adding new manager,
 * as well as, editing the user.
 *
 * @author Gosia, Karlo
 */

public class AdministratorDAOManager implements AdministratorDAO
{

  private DatabaseConnection databaseConnection;

  public AdministratorDAOManager() throws SQLException
  {
    databaseConnection = DatabaseConnection.getInstance();
  }

  @Override public ProductList giveAllProductData() throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
      ResultSet resultSet = statement.executeQuery();
      ProductList productList = new ProductList();
      while(resultSet.next()){
        int productId = resultSet.getInt("productid");
        String productName = resultSet.getString("productname");
        String productDescription = resultSet.getString("productdescription");
        String categoryName = resultSet.getString("categoryname");
        Product product = new Product( productId,productName, productDescription, categoryName);
        productList.addProduct(product);
      }
      return productList;
    }
  }

  /**
   * Method retrieving data about the shop offering the specified product, as well as,
   * the price the shop is offering.
   * @param productId - the specific product the method will retrieve data for.
   * @author Gosia, Hadi
   */

  @Override public ArrayList<ShopPrice> getShopPricesTableById(int productId)
      throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement(
          "SELECT users.username, price.price, price.productid FROM users INNER JOIN price ON users.userid = price.userid WHERE price.productid = ?");
      statement.setInt(1, productId);
      ResultSet resultSet = statement.executeQuery();
     ArrayList<ShopPrice> shopPrices = new ArrayList<>();
      while(resultSet.next()){
        String shopManagers = resultSet.getString("username");
        int price = resultSet.getInt("price");
        int id = resultSet.getInt("productid");
        ShopPrice shopPrice = new ShopPrice(shopManagers, price);
        shopPrices.add(shopPrice);
      }
      return shopPrices;
    }
  }

  @Override public ArrayList<String> getAllTagsById(int productId)
      throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("select * from producttag where productid =?");
      statement.setInt(1, productId);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<String> tags = new ArrayList<>();
      while(resultSet.next()){
        int id = resultSet.getInt("productid");
        String tagname = resultSet.getString("tagname");
        tags.add(tagname);
      }
      return tags;
    }
  }

  @Override public String editProduct(String productName, String productDescription, String category, ArrayList<String> parseTag,
      int productId)
      throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM product where productid = ?");
      statement.setInt(1, productId);
      ResultSet resultSet = statement.executeQuery();
      Product product = null;
      while(resultSet.next())
      {
        int productId2 = resultSet.getInt("productid");
        String name = resultSet.getString("productname");
        String description = resultSet.getString("productdescription");
        String categoryName = resultSet.getString("categoryname");
        product = new Product(productId2, name, description, categoryName);
      }

      Product newProduct = new Product(productId, productName, productDescription, category);
      ArrayList<String> allTagsById2 = getAllTagsById(productId);
      Collections.sort(allTagsById2);
      Collections.sort(parseTag);
      if(newProduct.equals(product))
      {
        if(allTagsById2.equals(parseTag))
          return "Specified product already exists.";
        else
        {
          updateTags(parseTag, productId);
          return "Product updated.";
        }
      }
      else
      {
        updateProduct(productId, productName, productDescription, category);
        if(allTagsById2.equals(parseTag))
          return "Product updated.";
        else
        {
          updateTags(parseTag, productId);
          return "Product updated.";
        }
      }
    }
  }

  @Override public String deleteProduct(int productId) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE productid = ?");
      statement.setInt(1, productId);
      statement.execute();
      return "Product deleted.";
    }
  }

  @Override public List<User> getAllUsers() throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
      ResultSet resultSet = statement.executeQuery();
      List<User> usersList = new ArrayList<>();
      while(resultSet.next()){
        int userId = resultSet.getInt("userid");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String dob = resultSet.getString("dob");
        String type = resultSet.getString("type");
        User user = new User(username, email, password, dob, type, userId, false);

        if(!user.getType().contains("Admin"))
          usersList.add(user);
      }

      PreparedStatement statement1 = connection.prepareStatement("SELECT * FROM defaultUser");
      ResultSet resultSet1 = statement1.executeQuery();
      while(resultSet1.next()){
        int userId = resultSet1.getInt("userid");
        String issubscribed = resultSet1.getString("issubscribed");

        for (User user : usersList)
        {
          if(user.getUserId() == userId)
            user.setSubscribed(issubscribed.equals("T"));
        }
      }
      return usersList;
    }
  }

  private void updateProduct(int productId, String productName,
      String productDescription, String category) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE product SET productname = ?, productdescription = ?, categoryname = ? WHERE productid = ?");
      statement.setString(1, productName);
      statement.setString(2, productDescription);
      statement.setString(3, category);
      statement.setInt(4, productId);
      statement.executeUpdate();
    }
  }

  private void updateTags(ArrayList<String> parseTag, int productId) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("DELETE FROM producttag WHERE productid =?");
      statement.setInt(1, productId);
      statement.execute();

      for (String tag : parseTag)
      {
        PreparedStatement statement2 = connection.prepareStatement("INSERT INTO producttag (productid, tagname) VALUES (?,?)");
        statement2.setInt(1, productId);
        statement2.setString(2, tag);
        statement2.executeUpdate();
      }
    }
  }

  @Override public String addNewManager(User newManager) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM users where username = ?");
      statement.setString(1, newManager.getUsername());
      ResultSet resultSet = statement.executeQuery();
      User user = null;
      while (resultSet.next())
      {
        int userId = resultSet.getInt("userid");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String dob = resultSet.getString("dob");
        String type = resultSet.getString("type");
        user = new User(username, email, password, dob, type, userId, false);
      }
      if(user != null)
        return "Specified shop manager already exists.";
      else
      {
        String response = checkTheEmail(newManager);
        return response;
      }
    }
  }

  private String checkTheEmail(User newManager) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM users where email = ?");
      statement.setString(1, newManager.getEmail());
      ResultSet resultSet = statement.executeQuery();
      User user = null;
      while (resultSet.next())
      {
        int userId = resultSet.getInt("userid");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String dob = resultSet.getString("dob");
        String type = resultSet.getString("type");
        user = new User(username, email, password, dob, type, userId, false);
      }
      if(user != null)
        return "Shop manager with this email already exists.";
      else
      {
        addNewManagerNow(newManager);
        return "Shop manager added.";
      }
    }
  }



  private void addNewManagerNow(User newManager) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, email, password, dob, type) VALUES(?, ?, ?, ?, ?)");
      statement.setString(1, newManager.getUsername());
      statement.setString(2, newManager.getEmail());
      statement.setString(3, newManager.getPassword());
      Date date = Date.valueOf(newManager.getDob());
      statement.setDate(4, date);
      statement.setString(5, newManager.getType());
      statement.executeUpdate();
    }
  }

  @Override public void editUser(String oldUsername, String oldEmail, String username, String email, String password, String dob)throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("UPDATE users  SET username = ?, email = ?, password = ?, dob = ? WHERE username = ?");
      statement.setString(1, username);
      statement.setString(2, email);
      statement.setString(3, password);
      Date date = Date.valueOf(dob);
      statement.setDate(4, date);
      statement.setString(5, oldUsername);
      statement.executeUpdate();
    }

  }

}
