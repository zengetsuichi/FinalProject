package server.networking.servermodel.loginRegisterServerModel;

/**
 * Class used for separating the Login Register Model from the server.
 * @author Gosia, Piotr
 */
public interface LoginRegisterServerModel
{
  String validateLogin(String username, String password);
  String validateRegister(String username, String email, String password, String dob);

}
