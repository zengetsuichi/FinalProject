package client.clientmodel.administratorEditUser;

import shared.util.PropertyChangeSubject;

/**
 * Interface is used for separating the view model from the model as
 * well as providing methods to the model manager.
 *
 * @author Dorin
 */
public interface AdministratorEditUserModel extends PropertyChangeSubject
{
  String validateEditUser(String oldUsername, String oldEmail, String username, String email, String password, String dob);
}
