package server.networking.servermodel.administratorServerModel;

import dataaccess.DummyDatabase;
import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.administratorDAO.AdministratorDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shared.util.Product;
import shared.util.ProductList;

import java.sql.SQLException;

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
    Product product2 = new Product(2,"Fanta 1l", "Fanta, bottle, german engineering","Fruits");
    Product product3 = new Product(3,"Big Bread", "Big bread, cuted for toaster","Bread");
    Product product4 = new Product(4,"Nescafe 1kg", "Nescafe, worst coffe for even worser times","Coffee");
    ProductList productList = new ProductList();
  }


}