package client.clientmodel.addNewManagerAdmin;

import client.networking.Client;
import shared.util.User;
/**
 * Class implementing the model interface. Used for pushing the requests
 * to the client.
 *
 * Providing methods for; adding a new shop manager into the database.
 *
 * @author Gosia
 */
public class AddNewManagerAdminModelManager implements AddNewManagerAdminModel
{
  private Client client;

  public AddNewManagerAdminModelManager(Client client)
  {
    this.client = client;
  }

  @Override public String addNewManager(User newManager)
  {
    return client.addNewManager(newManager);
  }
}
