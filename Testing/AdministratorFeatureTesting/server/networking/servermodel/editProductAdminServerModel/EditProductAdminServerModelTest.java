package server.networking.servermodel.editProductAdminServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.administratorDAO.AdministratorDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing class used for making an integration test on Admin Edit Product Feature
 *
 * @author Gosia, Karlo
 */

class EditProductAdminServerModelTest
{

  private EditProductAdminServerModel editProductAdminServerModel;
  private DummyDatabase dummyDatabase;

  @BeforeEach void setUp() throws SQLException
  {
    AdministratorDAO administratorDAO = new AdministratorDAOManager();
    editProductAdminServerModel = new EditProductAdminServerModelManager(administratorDAO);
    dummyDatabase = new DummyDatabase();
    dummyDatabase.createDummyDatabase();
  }

  @AfterEach void tearDown()
  {
    dummyDatabase.dropDatabase();
  }

  /*
    The method is testing whether the server model will return negative response
    after editing a product that already exists in the database.
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

    assertEquals("Specified product already exists.", editProductAdminServerModel.editProduct(
        productName, productDescription, category, parseTag, productId));
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

    assertEquals("Specified product already exists.", editProductAdminServerModel.editProduct(
        productName, productDescription, category, parseTag, productId));
  }
}