package client.views.administratorUsersPage;

import client.clientmodel.administratorUsersPageModel.AdministratorUsersPageModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventType;
import shared.util.User;
import java.beans.PropertyChangeEvent;
import java.util.List;

/**
 * Class responsible for managing and storing controller data.
 *
 * @author Karlo
 */

public class AdministratorUsersPageViewModel
{
  private AdministratorUsersPageModel administratorUsersPageModel;
  private ObservableList<User> allUsers;

  public AdministratorUsersPageViewModel(AdministratorUsersPageModel administratorUsersPageModel)
  {
    this.administratorUsersPageModel = administratorUsersPageModel;
    allUsers = FXCollections.observableArrayList();
    administratorUsersPageModel.addListener(EventType.NEW_SHOP_MANAGER.name(), this::newManager);
    administratorUsersPageModel.addListener(EventType.EDIT_USER.name(), this::newManager);
  }

  private void newManager(PropertyChangeEvent propertyChangeEvent)
  {
    Platform.runLater(() -> {
      List<User> newUsers = (List<User>) propertyChangeEvent.getNewValue();
      allUsers.setAll(newUsers);
    });
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
