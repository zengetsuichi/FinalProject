package server.networking.servermodel.loginRegisterServerModel;


import dataaccess.loginRegisterDAO.LoginRegisterDAO;
import dataaccess.loginRegisterDAO.LoginRegisterDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class used for making an integration test on Login Feature
 * @author Gosia, Piotr
 */

class LoginFeatureTest
{
  private LoginRegisterServerModel loginRegisterServerModel;
  private DummyDatabase dummyDatabase;

  @BeforeEach
  public void setup() throws SQLException
  {
    LoginRegisterDAO loginRegisterDAO = new LoginRegisterDAOManager();
    loginRegisterServerModel = new LoginRegisterServerModelManager(loginRegisterDAO);
    dummyDatabase = new DummyDatabase();
    dummyDatabase.createDummyDatabase();
  }

  @AfterEach
  public void after()
  {
    dummyDatabase.dropDatabase();
  }

  /**
   * Testing method used for indicating that if the normal user
   * credentials are correct the method will return type of the user (User)
   * @author Gosia, Piotr
   */

  @Test
  public void normalUserRightCredentials(){
    //arrange
    String username = "User1";
    String password = "user1";
    String type = "User";
    //assert
    assertEquals(type, loginRegisterServerModel.validateLogin(username, password));
  }

  /**
   * Testing method used for indicating that if the admin user
   * credentials are correct the method will return type of the user (Admin)
   * @author Gosia, Piotr
   */
  @Test
  public void AdminUserRightCredentials(){
    //arrange
    String username = "Admin";
    String password = "admin1";
    String type = "Admin";
    //assert
    assertEquals(type, loginRegisterServerModel.validateLogin(username, password));
  }

  /**
   * Testing method used for indicating that if the SHOP MANAGER user
   * credentials are correct the method will return type of the user (User)
   * @author Gosia, Piotr
   */
  @Test
  public void ShopManagerRightCredentials(){
    //arrange
    String username = "Netto";
    String password = "admin1";
    String type = "ShopManager";
    //assert
    assertEquals(type, loginRegisterServerModel.validateLogin(username, password));
  }


  /**
   * Testing method used for indicating that if the username
   * is wrong the method will return "User with this username does not exist"
   * @author Gosia, Piotr
   */

  @Test
  public void WrongUsername(){
    //arrange
    String username = "Nett";
    String password = "admin1";
    String error = "User with this username does not exist";
    //assert
    assertEquals(error, loginRegisterServerModel.validateLogin(username, password));
  }


  /**
   * Testing method used for indicating that if the password
   * is wrong the method will return "Wrong credentials"
   * @author Gosia, Piotr
   */

  @Test
  public void WrongPassword(){
    //arrange
    String username = "Netto";
    String password = "admin";
    String error = "Wrong credentials";
    //assert
    assertEquals(error, loginRegisterServerModel.validateLogin(username, password));
  }

}