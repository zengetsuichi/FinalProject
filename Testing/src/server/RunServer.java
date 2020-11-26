package server;

import dataaccess.loginRegisterDAO.LoginRegisterDAO;
import dataaccess.loginRegisterDAO.LoginRegisterDAOManager;
import server.networking.RMIServerManager;
import server.networking.servermodel.loginRegisterServerModel.LoginRegisterServerModelManager;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

/**
 * Class used for starting the RMI server.
 * @author Gosia, Piotr
 */

public class RunServer
{
  public static void main(String[] args)
      throws AlreadyBoundException, RemoteException, SQLException
  {
    LoginRegisterDAO loginRegisterDAO = new LoginRegisterDAOManager();
    RMIServer rmiServer = new RMIServerManager(new LoginRegisterServerModelManager(loginRegisterDAO));
    rmiServer.startServer();
  }
}
