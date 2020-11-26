package server;

import client.clientmodel.administratorModel.AdministratorModel;
import client.clientmodel.administratorModel.AdministratorModelManager;
import dataaccess.addNewProductAdminDAO.AddNewProductAdminDAO;
import dataaccess.addNewProductAdminDAO.AddNewProductAdminDAOManager;
import dataaccess.administratorDAO.AdministratorDAO;
import dataaccess.administratorDAO.AdministratorDAOManager;
import dataaccess.loginRegisterDAO.LoginRegisterDAO;
import dataaccess.loginRegisterDAO.LoginRegisterDAOManager;
import server.networking.RMIServerManager;
import server.networking.servermodel.addNewProductAdminServerModel.AddNewProductAdminServerModel;
import server.networking.servermodel.addNewProductAdminServerModel.AddNewProductAdminServerModelManager;
import server.networking.servermodel.administratorServerModel.AdministratorServerModel;
import server.networking.servermodel.administratorServerModel.AdministratorServerModelManager;
import server.networking.servermodel.editProductAdminServerModel.EditProductAdminServerModel;
import server.networking.servermodel.editProductAdminServerModel.EditProductAdminServerModelManager;
import server.networking.servermodel.loginRegisterServerModel.LoginRegisterServerModel;
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
    AdministratorDAO administratorDAO = new AdministratorDAOManager();
    AddNewProductAdminDAO addNewProductAdminDAO = new AddNewProductAdminDAOManager();
    AdministratorServerModel administratorServerModel = new AdministratorServerModelManager(administratorDAO);
    LoginRegisterServerModel loginRegisterServerModel = new LoginRegisterServerModelManager(loginRegisterDAO);
    EditProductAdminServerModel editProductAdminServerModel = new EditProductAdminServerModelManager(administratorDAO);
    AddNewProductAdminServerModel addNewProductAdminServerModel = new AddNewProductAdminServerModelManager(addNewProductAdminDAO, administratorDAO);
    RMIServer rmiServer = new RMIServerManager(loginRegisterServerModel, administratorServerModel, addNewProductAdminServerModel, editProductAdminServerModel);
    rmiServer.startServer();
  }
}
