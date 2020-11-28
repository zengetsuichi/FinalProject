package client.clientmodel.administratorUsersPageModel;

import shared.util.PropertyChangeSubject;
import shared.util.User;

import java.util.List;

public interface AdministratorUsersPageModel extends PropertyChangeSubject
{
  List<User> getAllUsers();
  void logOut();
}
