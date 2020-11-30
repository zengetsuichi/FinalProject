package client.views.addNewManagerAdmin;

import client.clientmodel.addNewManagerAdmin.AddNewManagerAdminModel;
import shared.util.User;

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
