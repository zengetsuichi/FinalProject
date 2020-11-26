package server.networking;

import client.networking.Client;
import server.networking.servermodel.loginRegisterServerModel.LoginRegisterServerModel;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class used for receiving, analyzing the request send from cient, as well as, sending the
 * back to the client.
 * @author Gosia, Piotr
 */

public class RMIServerManager implements RMIServer
{
  private LoginRegisterServerModel loginRegisterServerModel;


  public RMIServerManager(LoginRegisterServerModel loginRegisterServerModel) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.loginRegisterServerModel = loginRegisterServerModel;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("priceCheckerServer", this);

  }

  @Override public String validateLogin(String username, String password)
      throws RemoteException
  {
    //TODO dont add to the registry if the login info was wrong
    return loginRegisterServerModel.validateLogin(username, password);
  }

  @Override public void registerClient(Client client) throws RemoteException
  {
    //TODO remember to implement with Observer pattern
  }

}
