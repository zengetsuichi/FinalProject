package server.networking.servermodel.userShoppingListServerModel;


import dataaccess.userDAO.UserDAO;
import dataaccess.userDAO.UserDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;
import shared.util.Product;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserShoppingListServerModelTest
{
  private UserShoppingListServerModel userShoppingListServerModel;
  private DummyDatabase dummyDatabase;

  @BeforeEach void setUp() throws SQLException
  {
    UserDAO userDAO = new UserDAOManager();
    userShoppingListServerModel = new UserShoppingListServerModelManager(userDAO);
    dummyDatabase = new DummyDatabase();
    dummyDatabase.createDummyDatabase();
  }

  @AfterEach void tearDown()
  {
    dummyDatabase.dropDatabase();
  }

  /*
  Test method prepared for checking if the method will return the list of all
  the products from the shopping assigned to the specific user stored in database.
   */

  @Test
  void getAllProductsFromShoppingListForSpecificUser()
  {
    //arrange
    Product product = new Product(1, 3, "Nescafe 1kg",
        "Nescafe, worst coffe for even worser times", "Coffee");
    Product product1 = new Product(1, 1, "Cola 330",
        "Cola 330 ml, can, best for parties", "Beverages");
    ArrayList<Product> products = new ArrayList<>();
    products.add(product1);
    products.add(product);

    //act
    assertEquals(products, userShoppingListServerModel.getThisUserShoppingList("User1"));
  }

  /*
  Test method prepared for checking if the method will erase all the items
  from a shopping list.
  */

  @Test
  void clearTheWholeShoppingList()
  {
    //arrange
    ArrayList<Product> products = new ArrayList<>();
    //act
    assertTrue(userShoppingListServerModel.clearSL("User1"));
    assertEquals(products, userShoppingListServerModel.getThisUserShoppingList("User1"));
  }

  /*
  Test method prepared for checking if the method will add the product to the shopping list
  of that user to the database.
  */

  @Test
  void addTheProductToTheShoppingListOfASpecificUser()
  {
    //arrange
    Product product = new Product(1, 3, "Nescafe 1kg",
        "Nescafe, worst coffe for even worser times", "Coffee");
    ArrayList<Product> products = new ArrayList<>();
    //act
    userShoppingListServerModel.clearSL("User1");
    products.add(product);
    //assert
    assertTrue(userShoppingListServerModel.addProductToSL(product, "User1"));
    assertEquals(products, userShoppingListServerModel.getThisUserShoppingList("User1"));
  }
}