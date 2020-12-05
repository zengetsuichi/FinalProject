package server.networking.servermodel.administratorEditUserServerModel;

import shared.util.PropertyChangeSubject;

public interface AdministratorEditUserServerModel extends PropertyChangeSubject
{
  String validateUserEdit(String oldUsername, String oldEmail, String username, String email, String password, String dob);
}
