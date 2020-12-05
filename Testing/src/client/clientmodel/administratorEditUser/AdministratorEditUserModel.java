package client.clientmodel.administratorEditUser;

import shared.util.PropertyChangeSubject;

public interface AdministratorEditUserModel extends PropertyChangeSubject
{
  String validateEditUser(String oldUsername, String oldEmail, String username, String email, String password, String dob);
}
