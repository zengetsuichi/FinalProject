package server.networking.servermodel.shopManagerServerModel;

import dataaccess.shopManagerDAO.ShopManagerDAO;
import dataaccess.shopManagerDAO.ShopManagerDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;
import shared.util.Product;
import shared.util.ProductList;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopManagerServerModelTest
{
  private ShopManagerServerModel shopManagerServerModel;
  private ShopManagerDAO shopManagerDAO;
  private DummyDatabase dummyDatabase;

  @BeforeEach void setUp() throws SQLException
  {
    shopManagerDAO = new ShopManagerDAOManager();
    shopManagerServerModel = new ShopManagerServerModelManager(shopManagerDAO);
    dummyDatabase = new DummyDatabase();
    dummyDatabase.createDummyDatabase();
  }

  @AfterEach void tearDown()
  {
    dummyDatabase.dropDatabase();
  }

  @Test void getAllProductsForSpecificManager()
  {
    Product product1 = new Product(1,"Cola 330", "Cola 330 ml, can, best for parties","Beverages", 105);
    Product product2 = new Product(2,"Fanta 1l", "Fanta, bottle, german engineering","Beverages", 155);
    Product product4 = new Product(3,"Nescafe 1kg", "Nescafe, worst coffe for even worser times","Coffee", 55);
    ProductList productList = new ProductList();
    productList.addProduct(product1);
    productList.addProduct(product2);
    productList.addProduct(product4);
    ArrayList<Product> arrayList = shopManagerServerModel.getAllProductsForSpecificManager("Netto");

    assertEquals(productList.getProducts(), arrayList);
  }



  //Cannot finish the test because we cannot add the product to the shop
  /**
   * Testing method used for deleting the product from a shop
   * system will return "Product deleted."
   * @author Dorin
   */
  @Test void deleteProductForSpecificShop()
  {

    //arrange
    Product product1 = new Product(1,"Cola 330", "Cola 330 ml, can, best for parties","Beverages", 105);

    String response = "Product deleted.";

      assertEquals(response, shopManagerServerModel.deleteProductPrice(1,"Netto"));

  }


}