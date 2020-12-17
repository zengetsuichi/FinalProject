package server.networking.servermodel.administratorUsersServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.administratorDAO.AdministratorDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;
import shared.util.User;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing class used for making an integration test on AdministratorUsers Model
 *
 * @author Gosia, Karlo
 */

class AdministratorUsersServerModelTest
{

  private AdministratorUsersServerModel administratorUsersServerModel;
  private DummyDatabase dummyDatabase;

  @BeforeEach
  public void setup() throws SQLException
  {
    AdministratorDAO administratorDAO = new AdministratorDAOManager();
    administratorUsersServerModel = new AdministratorUsersServerModelManager(administratorDAO);
    dummyDatabase = new DummyDatabase();
    dummyDatabase.createDummyDatabase();
  }

  @AfterEach
  public void drop(){
    dummyDatabase.dropDatabase();
  }

  /*
  Test method prepared for checking if the method will return the list of all
  the users stored in database.
   */

  @Test
  public void getAllUsersInDatabase(){
    //arrange
    User user1 = new User("Admin", "admin@gmail.com","admin1", "2003-01-02", "Admin", 1);
    User user2 = new User("Netto", "netto@gmail.com","admin1", "2003-01-02", "ShopManager", 2);
    User user3 = new User("Lidl", "lidl@gmail.com","lidl1", "2003-01-02", "ShopManager", 3);
    User user4 = new User("User1", "user1@gmail.com","user1", "2003-01-02", "User", 4);
    User user5 = new User("User2", "user2@gmail.com","user2", "2003-01-02", "User", 5);
    ArrayList arrayList = new ArrayList<User>();
    //arrayList.add(user1);
    arrayList.add(user2);
    arrayList.add(user3);
    arrayList.add(user4);
    arrayList.add(user5);

    assertEquals(arrayList, administratorUsersServerModel.getAllUsers());
  }


  /*
  Test method prepared for checking if the method will add the user to the
  database providing that the user does not exists yet.
   */

  @Test
  public void addNewManager(){
    //arrange
    User newManager = new User("Aldi", "aldi@gmail.com", "aldi123",
        "2003-01-12", "ShopManager");
    //assert
    assertEquals("Shop manager added.", administratorUsersServerModel.addNewManager(newManager));
  }

  /*
  Test method prepared for checking if the method will add the user to the
  database providing that the user already exists.
   */

  @Test
  public void addAlreadyExistingManager(){
    //arrange
    User newManager = new User("Netto", "admin@gmail.com", "netto1",
        "2003-02-01", "ShopManager");
    //assert
    assertEquals("Specified shop manager already exists.", administratorUsersServerModel.addNewManager(newManager));
  }

  /*
  Test method prepared for checking if the method will add the user to the
  database providing that the input email already exists.
   */

  @Test
  public void addManagerWithAlreadyExistingEmail(){
    //arrange
    User newManager = new User("Neto", "admin@gmail.com", "netto1",
        "2003-02-01", "ShopManager");
    //assert
    assertEquals("Shop manager with this email already exists.", administratorUsersServerModel.addNewManager(newManager));
  }

}