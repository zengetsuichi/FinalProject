package dataaccess.shopManagerDAO;

import dataaccess.DatabaseConnection;
import shared.util.Product;
import shared.util.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class implementing the data access interface. Used for requesting data from
 * the database.
 *
 * Providing methods for; getting all products for a specific manager from the database
 * and deleting the product price.
 *
 * @author Gosia, Karlo
 */

public class ShopManagerDAOManager implements ShopManagerDAO
{
  private DatabaseConnection databaseConnection;

  public ShopManagerDAOManager() throws SQLException
  {
    databaseConnection = DatabaseConnection.getInstance();
  }

  @Override public ArrayList<Product> getAllProductsForSpecificManager(
      String username) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    ArrayList<Product> products = new ArrayList<>();

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement(
          "SELECT product.productid, product.productname, product.productdescription, "
              + "product.categoryname, price.price " + "from price inner join product on product.productid = "
              + "price.productid inner join users on price.userid = users.userid where users.username = ?");
      statement.setString(1, username);
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        int productId2 = resultSet.getInt("productid");
        String name = resultSet.getString("productname");
        String description = resultSet.getString("productdescription");
        String categoryName = resultSet.getString("categoryname");
        int price = resultSet.getInt("price");
        Product product = new Product(productId2, name, description,
            categoryName, price);
        products.add(product);
      }
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return products;
  }

  @Override public String deleteProductPrice(int productId, String username) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    String returnStatement = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement(
          "DELETE FROM price WHERE productId = ? AND userid = (SELECT userid FROM users WHERE username = ?);");
      statement.setInt(1, productId);
      statement.setString(2, username);
      statement.executeUpdate();
      returnStatement = "Product deleted.";
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return returnStatement;
  }

  @Override public ArrayList<String> getAllTagsById(int productId)
      throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    ArrayList<String> tags = new ArrayList<>();

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("select * from producttag where productid =?");
      statement.setInt(1, productId);
      resultSet = statement.executeQuery();
      while(resultSet.next()){
        int id = resultSet.getInt("productid");
        String tagname = resultSet.getString("tagname");
        tags.add(tagname);
      }
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return tags;
  }

  @Override public String editShopProduct(String productName,
      String productDescription, String category, ArrayList<String> parseTag,
      int productId, int price,String username) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Product product = null;
    String returnStatement = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT u.userName as shopmanagers, p.productName,"
          + " p.productId, p.categoryname,p.productdescription,"
          + " pr.price, pt.tagname FROM price pr\n"
          + "JOIN product p ON pr.productId = p.productId\n"
          + "JOIN users u ON u.userId = pr.UserId\n"
          + "JOIN productTag pt ON pt.productId = p.productId where u.username = ? and pr.price = ?");
      statement.setString(1, username);
      statement.setInt(2,productId);
      resultSet = statement.executeQuery();
      while(resultSet.next())
      {
        int productId2 = resultSet.getInt("productid");
        String name = resultSet.getString("productname");
        String description = resultSet.getString("productdescription");
        String categoryName = resultSet.getString("categoryname");
        int price1 = resultSet.getInt("price");
        product = new Product(productId2, name, description, categoryName,price1);
      }

      Product newProduct = new Product(productId, productName, productDescription, category,price);
      ArrayList<String> allTagsById2 = getAllTagsById(productId);
      Collections.sort(allTagsById2);
      Collections.sort(parseTag);
      if(newProduct.equals(product))
      {
        if(allTagsById2.equals(parseTag))
          returnStatement = "Specified product already exists";
        else
        {
          updateTags(parseTag, productId);
          returnStatement = "Product updated.";
        }
      }
      else
      {
        updateProduct(productId, productName, productDescription, category,price,username);
        if(allTagsById2.equals(parseTag))
          returnStatement = "Product updated.";
        else
        {
          updateTags(parseTag, productId);
          returnStatement = "Product updated.";
        }
      }
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return returnStatement;
  }

  private void updateProduct(int productId, String productName,
      String productDescription, String category,int price,String username) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement(
          "UPDATE product SET productname = ?, productdescription = ?, categoryname = ? WHERE productid = ?; "
              + "Update price set price = ? where userid = (select userid from users where username = ?) and productid = ?");
      statement.setString(1, productName);
      statement.setString(2, productDescription);
      statement.setString(3, category);
      statement.setInt(4,productId);
      statement.setInt(5, price);
      statement.setString(6,username);
      statement.setInt(7,productId);
      statement.executeUpdate();
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
  }

  private void updateTags(ArrayList<String> parseTag, int productId) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    PreparedStatement statement1 = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("DELETE FROM producttag WHERE productid =?");
      statement.setInt(1, productId);
      statement.execute();

      for (String tag : parseTag)
      {
        statement1 = connection.prepareStatement("INSERT INTO producttag (productid, tagname) VALUES (?,?)");
        statement1.setInt(1, productId);
        statement1.setString(2, tag);
        statement1.executeUpdate();
      }
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement1 != null) try { statement1.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
  }
}
