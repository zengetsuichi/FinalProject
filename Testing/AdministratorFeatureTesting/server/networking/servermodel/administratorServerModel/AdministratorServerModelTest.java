package server.networking.servermodel.administratorServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.administratorDAO.AdministratorDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;
import shared.util.Product;
import shared.util.ProductList;
import shared.util.ShopPrice;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorServerModelTest
{
  private AdministratorServerModel administratorServerModel;
  private DummyDatabase dummyDatabase;

  @BeforeEach
  public void setup() throws SQLException
  {
    AdministratorDAO administratorDAO = new AdministratorDAOManager();
    administratorServerModel = new AdministratorServerModelManager(administratorDAO);
    dummyDatabase = new DummyDatabase();
    dummyDatabase.createDummyDatabase();
  }

  @AfterEach
  public void drop(){
    dummyDatabase.dropDatabase();
  }

  /*
  The method is testing whether the server model will return
  the same data about the product list as it is in database.
   */

  @Test
  public void returnTheSameProductListAsInDatabase(){
    //arrange
    //creating product list product as it is in database
    Product product1 = new Product(1,"Cola 330", "Cola 330 ml, can, best for parties","Beverages");
    Product product2 = new Product(2,"Fanta 1l", "Fanta, bottle, german engineering","Beverages");
    Product product4 = new Product(3,"Nescafe 1kg", "Nescafe, worst coffe for even worser times","Coffee");
    ProductList productList = new ProductList();
    productList.addProduct(product1);
    productList.addProduct(product2);
    productList.addProduct(product4);
    ArrayList<Product> array = administratorServerModel.loadProductData().getProducts();

    assertTrue(productList.equals(array));
  }

  /*
  The method is testing whether the server model will return the shops that are offering the
  specific product along with the prices corresponding to each shop.
   */

  @Test
  public void getShopAndPricesForTheSpecificProduct(){
    //arrange
    //creating shop and prices
    ShopPrice shopPrices = new ShopPrice("Netto", 105);
    ShopPrice shopPrices2 = new ShopPrice("Lidl", 100);

    ArrayList<ShopPrice> shopsPrices = new ArrayList<>();
    shopsPrices.add(shopPrices);
    shopsPrices.add(shopPrices2);

    assertEquals(shopsPrices, administratorServerModel.getShopPricesById(1));
  }

   /*
   The test method checks whether the server model will return an empty list
   when asked for shop and prices for a product that does not exist.
   */

  @Test
  public void getShopAndPricesForTheNonExistingProduct(){
    ArrayList<ShopPrice> shopPrices = new ArrayList<>();
    assertEquals(shopPrices, administratorServerModel.getShopPricesById(1000));
  }

  /*
  The method is testing whether the server model will return all the tags
  assigned to a specific product.
   */

  @Test
  public void returnAllTagsAssignedToASpecificProduct(){
    //arrange, all tags assigned to the product
    ArrayList<String> tags = new ArrayList<>();
    tags.add("cola");
    tags.add("drink");

    assertEquals(tags, administratorServerModel.getAllTagsById(1));
  }

  /*
  The method is testing whether the server model will return an empty ArrayList
  when asking for tags for a non existing product.
   */

  @Test
  public void returnEmptyArrayOfTagsForNonExistingProduct(){
    //arrange, all tags assigned to the product
    ArrayList<String> tags = new ArrayList<>();
    assertEquals(tags, administratorServerModel.getAllTagsById(10000));
  }

  /*
  The method used for testing whether the server model will
  delete the product from the database by specific product id
   */

  @Test
  public void deleteTheSpecificProduct(){
    assertEquals("Product deleted.", administratorServerModel.deleteProduct(1));
  }

}