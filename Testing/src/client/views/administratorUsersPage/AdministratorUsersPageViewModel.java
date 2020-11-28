package client.views.administratorUsersPage;

import client.clientmodel.administratorUsersPageModel.AdministratorUsersPageModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.User;

public class AdministratorUsersPageViewModel
{
  private AdministratorUsersPageModel administratorUsersPageModel;
  private ObservableList<User> allUsers;

  public AdministratorUsersPageViewModel(AdministratorUsersPageModel administratorUsersPageModel)
  {
    this.administratorUsersPageModel = administratorUsersPageModel;
    allUsers = FXCollections.observableArrayList();
  }

  public ObservableList<User> getAllUsers()
  {
    return allUsers;
  }

  public void loadUsers()
  {
    allUsers.setAll(administratorUsersPageModel.getAllUsers());
  }

  public void logOut()
  {
    administratorUsersPageModel.logOut();
  }
}
