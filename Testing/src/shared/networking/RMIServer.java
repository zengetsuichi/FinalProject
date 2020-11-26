package shared.networking;

import client.networking.Client;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * An RMIServer interface is used for separating the Client
 * from the server networking layer.
 * @author Gosia, Piotr
 */

public interface RMIServer extends Remote
{
  String validateLogin(String username, String password) throws RemoteException;
  void registerClient(Client client) throws RemoteException;
  void startServer() throws RemoteException, AlreadyBoundException;
}
