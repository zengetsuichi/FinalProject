package client.clientmodel.addNewManagerAdmin;

import client.networking.Client;
import shared.util.User;

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
