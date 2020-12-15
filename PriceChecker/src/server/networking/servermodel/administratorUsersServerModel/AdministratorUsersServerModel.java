package server.networking.servermodel.administratorUsersServerModel;

import shared.util.PropertyChangeSubject;
import shared.util.User;
import java.util.List;

/**
 * Interface is used for separating the server from the model as
 * well as providing methods to the model manager.
 *
 * The interface extends property change subject to listen and fire events
 * to the listeners of this model.
 *
 * @author Karlo
 */

public interface AdministratorUsersServerModel extends PropertyChangeSubject
{
  List<User> getAllUsers();
  String addNewManager(User newManager);
  String deleteUser(String username);
  String getUserType(String clientUsername);
}
