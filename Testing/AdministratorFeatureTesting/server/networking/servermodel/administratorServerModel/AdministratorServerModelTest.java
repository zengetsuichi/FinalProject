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

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
  The method is testing whether the server will return the shops that are offering the
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
}