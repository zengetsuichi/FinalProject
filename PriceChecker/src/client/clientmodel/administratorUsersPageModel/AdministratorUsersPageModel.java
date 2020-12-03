package client.clientmodel.administratorUsersPageModel;

import shared.util.PropertyChangeSubject;
import shared.util.User;

import java.util.List;
/**
 * Interface is used for separating the view model from the model as
 * well as providing methods to the model manager.
 *
 * The interface extends property change subject to listen and fire events
 * to the listeners of this model.
 *
 * @author Karlo
 */
public interface AdministratorUsersPageModel extends PropertyChangeSubject
{
  List<User> getAllUsers();
  void logOut();
  String deleteUser(String username);
}
