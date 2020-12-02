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
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM category");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<String> category = new ArrayList<>();
      while(resultSet.next()){
        String categoryname = resultSet.getString("categoryname");
        category.add(categoryname);
      }
      return category;
    }
  }

  @Override public ArrayList<String> getAllTags() throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM tag");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<String> tags = new ArrayList<>();
      while(resultSet.next()){
        String tagName = resultSet.getString("tagname");
        tags.add(tagName);
      }
      return tags;
    }
  }

  @Override public String addNewProduct(String productName,
      String productDescription, String category, ArrayList<String> parseTag)
      throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM product where productName = ?");
      statement.setString(1, productName);
      ResultSet resultSet = statement.executeQuery();
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
       return "Specified product already exists";
     }
     else{
       int id = addToTheProductTable(productName, productDescription, category);
       addToTheProductTagTable(id, parseTag);
       return "Product added.";
     }
    }
  }

  @Override public String addNewCategory(String newCategory) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM category where categoryname = ?");
      statement.setString(1, newCategory);
      ResultSet resultSet = statement.executeQuery();
      String category = null;
      while(resultSet.next())
      {
        category = resultSet.getString("categoryName");
      }

      if(!(category == null)){
        return "Specified category already exists.";
      }
      else{
        addNewCategoryNow(newCategory);
        return "Category added.";
      }
    }
  }

  @Override public String addNewTag(String newTag) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM tag where tagname = ?");
      statement.setString(1, newTag);
      ResultSet resultSet = statement.executeQuery();
      String tag = null;
      while(resultSet.next())
      {
        tag = resultSet.getString("tagname");
      }

      if(!(tag == null)){
        return "Specified tag already exists.";
      }
      else{
        addNewTagNow(newTag);
        return "Tag added.";
      }
    }
  }

  private void addNewTagNow(String newTag) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO tag (tagname) VALUES(?)");
      statement.setString(1, newTag);
      statement.executeUpdate();
    }
  }

  private void addNewCategoryNow(String newCategory) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT INTO category (categoryname) VALUES(?)");
      statement.setString(1, newCategory);
      statement.executeUpdate();
    }
  }

  private void addToTheProductTagTable(int id, ArrayList<String> parseTag) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      for (int i = 0; i < parseTag.size(); i++)
      {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO producttag(productid, tagname) VALUES(?,?)");
        statement.setInt(1, id);
        statement.setString(2, parseTag.get(i));
        statement.executeUpdate();
      }
    }
  }

  private int addToTheProductTable(String productName, String productDescription, String category)
      throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection()){
      PreparedStatement statement = connection.prepareStatement("INSERT into product(productname, productdescription, categoryname)  values (?, ?, ?)",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, productName);
      statement.setString(2, productDescription);
      statement.setString(3, category);
      statement.executeUpdate();
      ResultSet keys = statement.getGeneratedKeys();
      if(keys.next()){
        return keys.getInt("productid");
      }
    }
    return 0;
  }
}
