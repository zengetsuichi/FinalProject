package client.views.administratorEditUser;

import client.clientmodel.administratorEditUser.AdministratorEditUserModel;

public class AdministratorEditUserViewModel
{
  private AdministratorEditUserModel administratorEditUserModel;

  public AdministratorEditUserViewModel(AdministratorEditUserModel administratorEditUserModel)
  {
    this.administratorEditUserModel = administratorEditUserModel;
  }

  public String validateEditUser(String oldUsername, String oldEmail, String username, String email, String password, String dob)
  {

    return administratorEditUserModel.validateEditUser(oldUsername, oldEmail, username, email, password, dob);
  }
}
