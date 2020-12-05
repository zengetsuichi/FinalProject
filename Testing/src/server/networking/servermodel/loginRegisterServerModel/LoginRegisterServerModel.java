package server.networking.servermodel.loginRegisterServerModel;

/**
 * Interface is used for separating the server from the model as
 * well as providing methods to the model manager.
 *
 * The interface extends property change subject to listen and fire events
 * to the listeners of this model.
 *
 * @author Gosia, Karlo
 */

public interface LoginRegisterServerModel
{
  String validateLogin(String username, String password);
  String validateRegister(String username, String email, String password, String dob);

}
