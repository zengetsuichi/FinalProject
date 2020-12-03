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
 * Testing class used for making an integration test on Register Feature
 * @author Dorin, Piotr
 */



class RegisterFeatureTest
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
   * credentials are correct the method will register the user
   */
  @Test
      public void successfullRegister(){
    //arrange
    String username = "User100";
    String password = "user100";
    String email ="user100@gmail.com";
    String dob = "2010-10-10";
    String response = "User registered";
    //act
    //assert
    assertEquals(response, loginRegisterServerModel.validateRegister(username, email, password, dob));
  }

  /**
   * Testing method used for indicating that if the username is already used
   * system will return "User with this username already exist"
   */
  @Test
  public void unsuccessfullRegisterUsernameExists(){
    //arrange
    String username = "Admin";
    String password = "user100";
    String email ="user100@gmail.com";
    String dob = "2010-10-10";
    String response = "User with this username already exist";
    //act
    //assert
    assertEquals(response, loginRegisterServerModel.validateRegister(username, email, password, dob));
  }

  /**
   * Testing method used for indicating that if the username is already used
   * system will return "Email already used"
   */
  @Test
  public void unsuccessfullRegisterEmailExists(){
    //arrange
    String username = "User100";
    String password = "user100";
    String email ="admin@gmail.com";
    String dob = "2010-10-10";
    String response = "Email already used";
    //act
    //assert
    assertEquals(response, loginRegisterServerModel.validateRegister(username, email, password, dob));
  }






}