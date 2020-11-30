package server.networking.servermodel.administratorUsersServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.administratorDAO.AdministratorDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;
import shared.util.User;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

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
    //TODO wait for update
  }


  /*
  Test method prepared for checking if the method will add the user to the
  database providing that the user does not exists yet.
   */

  @Test
  public void addNewManager(){
    //arrange
    dummyDatabase.createDummyDatabase();
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
    dummyDatabase.createDummyDatabase();
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
    dummyDatabase.createDummyDatabase();
    User newManager = new User("Neto", "admin@gmail.com", "netto1",
        "2003-02-01", "ShopManager");
    //assert
    assertEquals("Shop manager with this email already exists.", administratorUsersServerModel.addNewManager(newManager));
  }

}