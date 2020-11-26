package client.clientmodel.loginRegisterModel;

/**
 * Interface used for separating the model part of the implementation from View models.
 * @author Gosia, Piotr
 */
public interface LoginRegisterModel
{
  String validateLogin(String username, String password);
}
