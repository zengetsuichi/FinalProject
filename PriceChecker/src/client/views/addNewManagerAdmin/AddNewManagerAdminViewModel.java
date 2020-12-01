package client.views.addNewManagerAdmin;

import client.clientmodel.addNewManagerAdmin.AddNewManagerAdminModel;
import shared.util.User;
/**
 * Class responsible for managing and storing controller data.
 *
 * @author Gosia
 */

public class AddNewManagerAdminViewModel
{
  private AddNewManagerAdminModel addNewManagerAdminModel;

  public AddNewManagerAdminViewModel(AddNewManagerAdminModel addNewManagerAdminModel)
  {
    this.addNewManagerAdminModel = addNewManagerAdminModel;
  }

  public String addNewManager(User newManager)
  {
    return addNewManagerAdminModel.addNewManager(newManager);
  }
}
