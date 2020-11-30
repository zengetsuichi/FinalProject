package dataaccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;


import org.apache.ibatis.jdbc.ScriptRunner;

public class DummyDatabase
{
  private String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=pricechecker";
  private String username = "postgres";
  private String password = "database123";
  private Connection con;

  public DummyDatabase()
  {
    try
    {
      //Registering the Driver
      DriverManager.registerDriver(new org.postgresql.Driver());
      //Getting the connection
      con = DriverManager.getConnection(url, username, password);
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }

  }
  public void createDummyDatabase()
  {
    try
    {
      System.out.println("Connection established......");
      //Initialize the script runner
      ScriptRunner sr = new ScriptRunner(con);
      //Creating a reader object
      Reader reader = null;
      reader = new BufferedReader(new FileReader("Testing/dummydatabase.sql"));
      //Running the script
      sr.runScript(reader);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }

  }

  public void dropDatabase()
  {
    try
    {
      PreparedStatement statement = con.prepareStatement("DROP schema pricecheckerdummy cascade");
      statement.executeUpdate();
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
  }


}