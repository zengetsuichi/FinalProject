package client.clientmodel.loginRegisterModel;
/**
 * Interface is used for separating the view model from the model as
 * well as providing methods to the model manager.
 *
 * @author Gosia, Piotr
 */
public interface LoginRegisterModel
{
  String validateLogin(String username, String password);
  String validateRegister(String username, String email, String password, String dob);

  void setClientUsername(String username);
}
