package client.networking;

import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Class used for establishing the connection from the client to the server. //TODO Gosia check
 * @author Gosia, Piotr
 */
public class RMIClient implements Client, ClientCallback
{
  private RMIServer rmiServer;

  public RMIClient(){

  }

  /**
   * Method used for starting the client, and adding it to the register pool on
   * the server side.
   * @author Gosia, Piotr
   */

  @Override public void startClient()
  {
    try
    {
    UnicastRemoteObject.exportObject(this, 0);
    Registry registry = LocateRegistry.getRegistry("localhost", 1099);
    rmiServer = (RMIServer) registry.lookup("priceCheckerServer");
    //rmiServer.registerClient(this); //TODO
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }

  }

  /**
   * Class used for sending a request to validate the login information,
   * send from the model, to the server.
   * @param username
   * @param password
   * @author Gosia, Piotr
   */
  @Override public String validateLogin(String username, String password)
  {
    try
    {
      return rmiServer.validateLogin(username, password);
    }
    catch (RemoteException e)
    {
      throw new RuntimeException("Could not contact server");
    }
  }

}
