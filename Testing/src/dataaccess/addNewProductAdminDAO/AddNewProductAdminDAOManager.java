package dataaccess.addNewProductAdminDAO;

import dataaccess.DatabaseConnection;
import shared.util.Product;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class implementing the data access interface. Used for requesting data from
 * the database.
 *
 * Providing methods for; getting all product categories, getting all product
 * tags, adding new products to the database, adding new categories and adding
 * new tags for the products to the database.
 *
 * @author Gosia, Karlo
 */

public class AddNewProductAdminDAOManager implements AddNewProductAdminDAO
{
  private DatabaseConnection databaseConnection;

  public AddNewProductAdminDAOManager() throws SQLException
  {
    databaseConnection = DatabaseConnection.getInstance();
  }

  @Override public ArrayList<String> getAllProductCategories()
      throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    ArrayList<String> category = new ArrayList<>();

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT * FROM category");
      resultSet = statement.executeQuery();
      while(resultSet.next()){
        String categoryname = resultSet.getString("categoryname");
        category.add(categoryname);
      }
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (resultSet != null) try { resultSet.close(); } catch (Exception e) { e.printStackTrace(); }
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return category;
  }

  @Override public ArrayList<String> getAllTags() throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    ArrayList<String> tags = new ArrayList<>();

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT * FROM tag");
      resultSet = statement.executeQuery();
      while(resultSet.next()){
        String tagName = resultSet.getString("tagname");
        tags.add(tagName);
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

  @Override public String addNewProduct(String productName,
      String productDescription, String category, ArrayList<String> parseTag)
      throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String returnStatement = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT * FROM product where productName = ?");
      statement.setString(1, productName);
      resultSet = statement.executeQuery();
      Product product = null;
      while(resultSet.next())
      {
        int productId = resultSet.getInt("productid");
        String name = resultSet.getString("productname");
        String description = resultSet.getString("productdescription");
        String categoryName = resultSet.getString("categoryname");
        product = new Product(productId, name, description, categoryName);
      }

     if(!(product == null)){
       returnStatement = "Specified product already exists";
     }
     else{
       int id = addToTheProductTable(productName, productDescription, category);
       addToTheProductTagTable(id, parseTag);
       returnStatement = "Product added.";
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

  @Override public String addNewCategory(String newCategory) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String returnStatement = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT * FROM category where categoryname = ?");
      statement.setString(1, newCategory);
      resultSet = statement.executeQuery();
      String category = null;
      while(resultSet.next())
      {
        category = resultSet.getString("categoryName");
      }

      if(!(category == null)){
        returnStatement = "Specified category already exists.";
      }
      else{
        addNewCategoryNow(newCategory);
        returnStatement = "Category added.";
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

  @Override public String addNewTag(String newTag) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    String returnStatement = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("SELECT * FROM tag where tagname = ?");
      statement.setString(1, newTag);
      resultSet = statement.executeQuery();
      String tag = null;
      while(resultSet.next())
      {
        tag = resultSet.getString("tagname");
      }

      if(!(tag == null)){
        returnStatement = "Specified tag already exists.";
      }
      else{
        addNewTagNow(newTag);
        returnStatement = "Tag added.";
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

  private void addNewTagNow(String newTag) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("INSERT INTO tag (tagname) VALUES(?)");
      statement.setString(1, newTag);
      statement.executeUpdate();
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
  }

  private void addNewCategoryNow(String newCategory) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement("INSERT INTO category (categoryname) VALUES(?)");
      statement.setString(1, newCategory);
      statement.executeUpdate();
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
  }

  private void addToTheProductTagTable(int id, ArrayList<String> parseTag) throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = databaseConnection.getConnection();
      for (int i = 0; i < parseTag.size(); i++)
      {
        statement = connection.prepareStatement("INSERT INTO producttag(productid, tagname) VALUES(?,?)");
        statement.setInt(1, id);
        statement.setString(2, parseTag.get(i));
        statement.executeUpdate();
      }
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
  }

  private int addToTheProductTable(String productName, String productDescription, String category)
      throws SQLException
  {
    Connection connection = null;
    PreparedStatement statement = null;
    int returnInt = 0;

    try {
      connection = databaseConnection.getConnection();
      statement = connection.prepareStatement
          ("INSERT into product(productname, productdescription, categoryname)  values (?, ?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, productName);
      statement.setString(2, productDescription);
      statement.setString(3, category);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if(keys.next()){
        returnInt = keys.getInt("productid");
      }
    }
    catch (SQLException ex) { ex.printStackTrace(); }
    finally {
      if (statement != null) try { statement.close(); } catch (Exception e) { e.printStackTrace(); }
      if (connection != null) try { connection.close(); } catch (Exception e) { e.printStackTrace(); }
    }
    return returnInt;
  }
}
