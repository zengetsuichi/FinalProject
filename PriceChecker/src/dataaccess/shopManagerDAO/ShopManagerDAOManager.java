package dataaccess.shopManagerDAO;

import dataaccess.DatabaseConnection;
import shared.util.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    try (Connection connection = databaseConnection.getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT product.productid, product.productname, product.productdescription, "
              + "product.categoryname, price.price " + "from price inner join product on product.productid = "
              + "price.productid inner join users on price.userid = users.userid where users.username = ?");
      statement.setString(1, username);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Product> products = new ArrayList<>();
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

      return products;
    }
  }

  @Override public String deleteProductPrice(int productId, String username) throws SQLException
  {
    try (Connection connection = databaseConnection.getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "DELETE FROM price WHERE productId = ? AND userid = (SELECT userid FROM users WHERE username = ?);");
      statement.setInt(1, productId);
      statement.setString(2, username);
      statement.executeUpdate();

      return "Product deleted.";
    }
  }
}
