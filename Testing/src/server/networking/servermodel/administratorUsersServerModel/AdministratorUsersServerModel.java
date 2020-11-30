package server.networking.servermodel.administratorUsersServerModel;

import shared.util.PropertyChangeSubject;
import shared.util.User;

import java.util.List;

public interface AdministratorUsersServerModel extends PropertyChangeSubject
{
  List<User> getAllUsers();
  String addNewManager(User newManager);
}
