package client.clientmodel.addNewManagerAdmin;

import shared.util.User;
/**
 * Interface is used for separating the view model from the model as
 * well as providing methods to the model manager.
 *
 * @author Gosia
 */
public interface AddNewManagerAdminModel
{
  String addNewManager(User newManager);
}
