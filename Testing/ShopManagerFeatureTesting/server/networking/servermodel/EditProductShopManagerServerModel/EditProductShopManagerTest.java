package server.networking.servermodel.EditProductShopManagerServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.administratorDAO.AdministratorDAOManager;
import dataaccess.shopManagerDAO.ShopManagerDAO;
import dataaccess.shopManagerDAO.ShopManagerDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;
import server.networking.servermodel.editProductShopManagerModel.EditProductShopManagerServerModel;
import server.networking.servermodel.editProductShopManagerModel.EditProductShopManagerServerModelManager;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class used for making an integration test on Shop Manager Edit Product Feature
 * @author Hadi
 */

class EditProductShopManagerTest
{
  private EditProductShopManagerServerModel editProductShopManagerServerModel;
  private DummyDatabase dummyDatabase;

  @BeforeEach
  public void setup() throws SQLException
  {
    AdministratorDAO administratorDAO = new AdministratorDAOManager();
    ShopManagerDAO shopManagerDAO = new ShopManagerDAOManager();
    editProductShopManagerServerModel = new EditProductShopManagerServerModelManager(administratorDAO,shopManagerDAO );
    dummyDatabase = new DummyDatabase();
    dummyDatabase.createDummyDatabase();
  }

  @AfterEach
  public void after()
  {
    dummyDatabase.dropDatabase();
  }

  /**
   * Testing method used for editing shop manager products
   *
   */
  @Test void editProductAlreadyExists()
  {
    int productId = 1;
    String productName = "Cola 330";
    String productDescription = "Cola 330 ml, can, best for parties";
    String category = "Beverages";
    ArrayList<String> parseTag = new ArrayList<>();
    parseTag.add("cola");
    parseTag.add("drink");
    int price = 7;
    String username = "Netto";

    assertEquals("Specified product already exists.", editProductShopManagerServerModel.editShopProduct(
        productName, productDescription, category, parseTag, productId,price,username));
  }

  /*
    The method is testing whether the server model will return positive response
    after editing a product that does not exist in the database.
   */

  @Test void editProductDoesNotExist()
  {
    int productId = 1;
    String productName = "Cola 330";
    String productDescription = "Cola 330 ml, can, best for parties";
    String category = "Beverages";
    ArrayList<String> parseTag = new ArrayList<>();
    parseTag.add("cola");
    parseTag.add("drink");
    int price = 7;
    String username = "Netto";

    assertEquals("Specified product already exists.", editProductShopManagerServerModel.editShopProduct(
        productName, productDescription, category, parseTag, productId,price,username));
  }

}