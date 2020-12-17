package server.networking.servermodel.administratorEditUserServerModel;

import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.administratorDAO.AdministratorDAOManager;
import dataaccess.loginRegisterDAO.LoginRegisterDAO;
import dataaccess.loginRegisterDAO.LoginRegisterDAOManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.DummyDatabase;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class used for making an integration test on Admin Edit User Feature
 * @author Dorin
 */

class AdministratorEditUserTest
{
  private AdministratorEditUserServerModel administratorEditUserServerModel;
  private DummyDatabase dummyDatabase;

  @BeforeEach
  public void setup() throws SQLException
  {
    AdministratorDAO administratorDAO = new AdministratorDAOManager();
    LoginRegisterDAO loginRegisterDAO = new LoginRegisterDAOManager();
    administratorEditUserServerModel = new AdministratorEditUserServerModelManager(administratorDAO, loginRegisterDAO);
    dummyDatabase = new DummyDatabase();
    dummyDatabase.createDummyDatabase();
  }

  @AfterEach
  public void after()
  {
    dummyDatabase.dropDatabase();
  }

  /**
   * Testing method used for editing user data
   *
   */
  @Test
  public void successfullEditUser(){
    //arrange
    String oldUsername = "NettoTest";
    String oldEmail = "nettoTest@gmail.com";

    String username = "Netto";
    String password = "admin1";
    String email ="netto@gmail.com";
    String dob = "2003-01-02";
    String response = "User edited";
    //act
    //assert
    assertEquals(response, administratorEditUserServerModel.validateUserEdit(oldUsername,oldEmail,username,email,password,dob));
  }

  /**
   * Testing method to announce that username is already used
   *
   */
  @Test
  public void unsuccessfullUsernameExists(){
    //arrange
    String oldUsername = "Netto";
    String oldEmail = "nettoTest@gmail.com";

    String username = "Netto";
    String password = "admin1";
    String email ="netto@gmail.com";
    String dob = "2003-01-02";
    String response = "User with this username already exist";
    //act
    //assert
    assertEquals(response, administratorEditUserServerModel.validateUserEdit(oldUsername,oldEmail,username,email,password,dob));
  }


  /**
   * Testing method to announce that email is already used
   *
   */
  @Test
  public void unsuccessfullEmailExists(){
    //arrange
    String oldUsername = "NettoTest";
    String oldEmail = "netto@gmail.com";

    String username = "Netto";
    String password = "admin1";
    String email ="netto@gmail.com";
    String dob = "2003-01-02";
    String response = "Email already used";
    //act
    //assert
    assertEquals(response, administratorEditUserServerModel.validateUserEdit(oldUsername,oldEmail,username,email,password,dob));
  }

}