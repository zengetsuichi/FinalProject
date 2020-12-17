package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;

import dataaccess.DatabaseConnection;
import org.apache.ibatis.jdbc.ScriptRunner;

/**
 * Class used for running the sql script.
 * @author Gosia
 */

public class DummyDatabase
{
  private DatabaseConnection databaseConnection;

  public DummyDatabase() throws SQLException
  {
    databaseConnection = DatabaseConnection.getInstance();
  }

  public void createDummyDatabase()
  {
    try(Connection connection = databaseConnection.getConnection())
    {
      System.out.println("Connection established......");
      //Initialize the script runner
      ScriptRunner sr = new ScriptRunner(connection);
      //Creating a reader object
      Reader reader = null;
      reader = new BufferedReader(new FileReader("Testing/dummydatabase.sql"));
      //Running the script
      sr.runScript(reader);
    }
    catch (FileNotFoundException | SQLException e)
    {
      e.printStackTrace();
    }

  }

  public void dropDatabase()
  {
    try(Connection connection = databaseConnection.getConnection())
    {
      PreparedStatement statement = connection.prepareStatement("DROP schema pricecheckerdummy cascade");
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }




}