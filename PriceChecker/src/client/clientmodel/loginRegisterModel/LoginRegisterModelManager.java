package client.clientmodel.loginRegisterModel;
import client.networking.Client;
/**
 * Class implementing the model interface. Used for requesting data from
 * the client as well as listening for events and passing them forward.
 *
 * Starting the client and providing methods for; validating the login information.
 *
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

  @Override
  public String validateRegister(String username, String email, String password, String dob) {
    return client.validateRegister(username,email,password,dob);
  }

}
