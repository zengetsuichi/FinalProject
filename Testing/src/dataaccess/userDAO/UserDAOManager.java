package dataaccess.userDAO;

import dataaccess.DatabaseConnection;
import shared.util.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOManager implements UserDAO
{
  private DatabaseConnection databaseConnection;

  public UserDAOManager() throws SQLException
  {
    databaseConnection = DatabaseConnection.getInstance();
  }

  @Override public ArrayList<Product> getThisUserShoppingList(
      String clientUsername) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    PreparedStatement statement1 = null;
    ResultSet resultSet1 = null;
    ArrayList<Product> shoppingList = new ArrayList<>();
    int shopListId = 0;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement(
          "SELECT shoplistid FROM shoppinglist INNER JOIN users ON shoppinglist.userid = users.userid WHERE username = ?");
      statement.setString(1, clientUsername);
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        shopListId = resultSet.getInt("shoplistid");
      }

      statement1 = connection.prepareStatement(
          "select product.productid, product.productname, product.productdescription, product.categoryname, "
              + "quantity from product inner join shoppingListItem "
              + "on shoppingListItem.productid =  product.productid where shoplistid =?");
      statement1.setInt(1, shopListId);
      resultSet1 = statement1.executeQuery();
      while (resultSet1.next())
      {
        int productId = resultSet1.getInt("productid");
        String productName = resultSet1.getString("productname");
        String productDescription = resultSet1.getString("productdescription");
        String categoryName = resultSet1.getString("categoryname");
        int quantity = resultSet1.getInt("quantity");
        Product product = new Product(quantity, productId, productName, productDescription, categoryName);
        shoppingList.add(product);
      }
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (resultSet1 != null) try { resultSet1.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement1 != null) try { statement1.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return shoppingList;
  }

  @Override public Boolean clearSL(String clientUsername)
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    PreparedStatement statement1 = null;
    boolean response = false;
    int shopListId = 0;

    try
    {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT shoplistid FROM shoppinglist INNER JOIN users "
          + "ON shoppinglist.userid = users.userid WHERE username = ?");
      statement.setString(1, clientUsername);
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        shopListId = resultSet.getInt("shoplistid");
      }

      statement1 = connection.prepareStatement("DELETE FROM shoppingListItem WHERE shoplistid = ?");
      statement1.setInt(1, shopListId);
      statement1.executeUpdate();
      response = true;
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement1 != null) try { statement1.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return response;
  }

  @Override public boolean addProductToSL(Product item, String clientUsername)
  {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement statement = null;
    PreparedStatement statement1 = null;
    int shopListId = 0;
    boolean response = false;

    try
    {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT shoplistid FROM shoppinglist INNER JOIN users "
          + "ON shoppinglist.userid = users.userid WHERE username = ?");
      statement.setString(1, clientUsername);
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        shopListId = resultSet.getInt("shoplistid");
      }

      statement1 = connection.prepareStatement("INSERT INTO shoppingListItem (shoplistid, productid, quantity) VALUES (?, ?, ?)");
      statement1.setInt(1, shopListId);
      statement1.setInt(2, item.getProductId());
      statement1.setInt(3, 1);
      statement1.executeUpdate();
      response = true;
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement1 != null) try { statement1.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return response;
  }

  @Override public Boolean deleteTheProductFromSL(String clientUsername,
      int productId) throws SQLException
  {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement statement = null;
    PreparedStatement statement1 = null;
    int shopListId = 0;
    boolean response = false;

    try
    {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT shoplistid FROM shoppinglist INNER JOIN users "
          + "ON shoppinglist.userid = users.userid WHERE username = ?");
      statement.setString(1, clientUsername);
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        shopListId = resultSet.getInt("shoplistid");
      }

      statement1 = connection.prepareStatement("DELETE FROM shoppinglistitem WHERE shoplistid = ? AND productid = ?");
      statement1.setInt(1, shopListId);
      statement1.setInt(2, productId);
      statement1.executeUpdate();
      response = true;
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement1 != null) try { statement1.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return response;
  }

  @Override public void changeQuantityForThisProduct(String clientUsername,
      int productId, int quantity) throws SQLException
  {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement statement = null;
    PreparedStatement statement1 = null;
    int shopListId = 0;
    try
    {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT shoplistid FROM shoppinglist INNER JOIN users "
          + "ON shoppinglist.userid = users.userid WHERE username = ?");
      statement.setString(1, clientUsername);
      resultSet = statement.executeQuery();
      while (resultSet.next())
      {
        shopListId = resultSet.getInt("shoplistid");
      }
      statement1 = connection.prepareStatement("UPDATE shoppinglistitem SET quantity = ? WHERE shoplistid = ? AND productid = ?");
      statement1.setInt(1, quantity);
      statement1.setInt(2, shopListId);
      statement1.setInt(3, productId);
      statement1.executeUpdate();
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement1 != null) try { statement1.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
  }
}
