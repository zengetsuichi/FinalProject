package server.networking.servermodel.addNewProductAdminServerModel;

import dataaccess.addNewProductAdminDAO.AddNewProductAdminDAO;
import dataaccess.addNewProductAdminDAO.AddNewProductAdminDAOManager;
import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.administratorDAO.AdministratorDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;
import server.networking.servermodel.administratorServerModel.AdministratorServerModelManager;
import shared.util.Product;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddNewProductAdminServerModelTest
{
  private DummyDatabase dummyDatabase;
  private AddNewProductAdminServerModel addNewProductAdminServerModel;

  @BeforeEach
  public void setup() throws SQLException
  {
    AdministratorDAO administratorDAO = new AdministratorDAOManager();
    AddNewProductAdminDAO addNewProductAdminDAO = new AddNewProductAdminDAOManager();
    addNewProductAdminServerModel = new AddNewProductAdminServerModelManager(addNewProductAdminDAO, administratorDAO);
    dummyDatabase = new DummyDatabase();
    dummyDatabase.createDummyDatabase();
  }

  @AfterEach
  public void drop(){
    dummyDatabase.dropDatabase();
  }

   /*
  The method is testing whether the server model will return all
  the categories that are saved in the database.
   */

  @Test
  public void getAllProductCategoriesFromDatabase(){
    //arrange
    ArrayList<String> categories = new ArrayList<>();
    categories.add("Beverages");
    categories.add("Meat");
    categories.add("Sweets");
    categories.add("Coffee");
    assertEquals(categories, addNewProductAdminServerModel.getAllProductCategories());
  }

  /*
  The method is testing whether the server model will return all
  the tags that are saved in the database.
   */

  @Test
  public void getAllTagsFromDatabase(){
    ArrayList<String> tags = new ArrayList<>();

    tags.add("drink");
    tags.add("beer");
    tags.add("coffee");
    tags.add("cola");

    assertEquals(tags, addNewProductAdminServerModel.getAllTags());
  }

   /*
  The test method is testing whether the server model will return a
  positive response for adding the product to the database.
   */

  @Test
  public void addNewNonExistingProduct(){
    ArrayList<String> tags = new ArrayList<>();
    tags.add("drink");
    tags.add("beer");

    assertEquals("Product added.", addNewProductAdminServerModel.addNewProduct("Bananas",
        "Very good bananans", "Sweets", tags));
  }

   /*
  The test method is testing whether the server model will return
  a negative response when adding the product that already exists in the
  database.
   */

  @Test
  public void addAnExistingProduct(){
    ArrayList<String> tags = new ArrayList<>();
    tags.add("cola");
    tags.add("drink");

    assertEquals("Specified product already exists", addNewProductAdminServerModel.addNewProduct("Cola 330",
        "Cola 330 ml, can, best for parties", "Beverages", tags));
  }

}