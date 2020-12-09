package server.networking.servermodel.AddNewProductShopManagerServerModel;

import dataaccess.addNewProductShopManagerDAO.AddNewProductShopManagerDAO;
import dataaccess.addNewProductShopManagerDAO.AddNewProductShopManagerDAOManager;
import dataaccess.shopManagerDAO.ShopManagerDAO;
import dataaccess.shopManagerDAO.ShopManagerDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;
import server.networking.servermodel.addNewProductShopManagerServerModel.AddNewProductShopManagerServerModel;
import server.networking.servermodel.addNewProductShopManagerServerModel.AddNewProductShopManagerServerModelManager;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddNewProductShopManagerTest {

    /**
     * Testing class used for making an integration test on Shop Manager Edit Product Feature
     * @author Hadi
     */

    private DummyDatabase dummyDatabase;
    private AddNewProductShopManagerServerModel addNewProductShopManagerServerModel;

    @BeforeEach
    public void setup() throws SQLException
    {
        ShopManagerDAO shopManagerDAO = new ShopManagerDAOManager();
        AddNewProductShopManagerDAO addNewProductShopManagerDAO = new AddNewProductShopManagerDAOManager();
        addNewProductShopManagerServerModel = new AddNewProductShopManagerServerModelManager(addNewProductShopManagerDAO);
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
        assertEquals(categories, addNewProductShopManagerServerModel.getAllProductCategories());
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

        assertEquals(tags, addNewProductShopManagerServerModel.getAllTags());
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

        assertEquals("Product added.", addNewProductShopManagerServerModel.addNewProduct("Netto","Bananas",
                "Very good bananans", "Sweets", tags,100));
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

        assertEquals("Product added.", addNewProductShopManagerServerModel.addNewProduct("Netto","Cola 330",
                "Cola 330 ml, can, best for parties", "Beverages", tags,200));
    }

  /*
    The test method is testing whether the server model will return
    a positive response when adding the category that does not exists in the
    database.
   */

    @Test
    public void addNewNonExistingCategory(){
        String newCategory = "Fruits";

        assertEquals("Category added.", addNewProductShopManagerServerModel.addNewCategory(newCategory));
    }

    /*
      The test method is testing whether the server model will return
      a negative response when adding the category that already exists in the
      database.
    */
    @Test
    public void addAnExistingCategory(){
        String newCategory = "Meat";

        assertEquals("Specified category already exists.", addNewProductShopManagerServerModel.addNewCategory(newCategory));
    }

  /*
    The test method is testing whether the server model will return
    a positive response when adding the tag that does not exists in the
    database.
   */

    @Test
    public void addNewNonExistingTag(){
        String newTag = "candy";

        assertEquals("Tag added.", addNewProductShopManagerServerModel.addNewTag(newTag));
    }

  /*
    The test method is testing whether the server model will return
    a negative response when adding the tag that already exists in the
    database.
  */

    @Test
    public void addAnExistingTag(){
        String newTag = "beer";

        assertEquals("Specified tag already exists.", addNewProductShopManagerServerModel.addNewTag(newTag));
    }

}
