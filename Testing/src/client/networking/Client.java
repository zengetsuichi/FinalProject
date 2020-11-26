package client.networking;

/**
 * Class used for separating the client implementation from the models.
 * @author Gosia, Piotr
 */
public interface Client
{
  void startClient();
  String validateLogin(String username, String password);
}
