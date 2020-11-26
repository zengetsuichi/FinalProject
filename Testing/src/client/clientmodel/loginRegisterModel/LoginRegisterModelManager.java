package client.clientmodel.loginRegisterModel;

import client.networking.Client;

/**
 * Class used for implementing the methods from Login Register Model interface.
 * @author Gosia, Piotr
 */

public class LoginRegisterModelManager implements LoginRegisterModel
{
  private Client client;

  public LoginRegisterModelManager(Client client)
  {
    this.client = client;
    client.startClient();
  }

  @Override public String validateLogin(String username, String password)
  {
    return client.validateLogin(username, password);
  }

}
