package client.core;
import client.networking.Client;
import client.networking.RMIClient;
/**
 * Class responsible for creating an instance of the client.
 * @author Gosia, Piotr
 */

public class ClientFactory
{
  private Client client;

  public Client getClient(){
    if(client == null){
      client = new RMIClient();
    }
    return client;
  }
}
