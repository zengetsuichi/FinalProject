package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A Client Callback interface responsible for
 * providing the client with an update method.
 *
 * @author Gosia
 */

public interface ClientCallback extends Remote
{
  void update(String eventName, Object newValue) throws RemoteException;
}
