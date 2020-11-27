package shared.networking;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A Client Callback interface. //TODO check it Gosia
 * @author Gosia
 */

public interface ClientCallback extends Remote
{
  void update(String eventName, Object newValue) throws RemoteException;
}
