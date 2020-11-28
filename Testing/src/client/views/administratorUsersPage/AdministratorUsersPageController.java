package client.views.administratorUsersPage;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shared.util.User;

public class AdministratorUsersPageController implements ViewController
{
  @FXML
  private TableView<User> usersTable;
  @FXML
  private TableColumn<User, Integer> userIdColumn;
  @FXML
  private TableColumn<User, String> userNameColumn;
  @FXML
  private TableColumn<User, String> emailColumn;
  @FXML
  private TableColumn<User, String> dobColumn;
  @FXML
  private TableColumn<User, String> userTypeColumn;
  @FXML
  private TableColumn<User, Boolean> isSubscribedColumn;
  @FXML
  private Button logOutBtn;
  @FXML
  private Label errorLabel;
  @FXML
  private TextField searchBar;
  @FXML
  private Button addUserBtn;
  @FXML
  private Button editUserBtn;
  @FXML
  private Button deleteUserBtn;
  @FXML
  private Button productsPageBtn;
  @FXML
  private Button usersPageBtn;
  @FXML
  private Button newsletterPageBtn;

  private ViewHandler viewHandler;
  private AdministratorUsersPageViewModel administratorUsersPageViewModel;

  @Override public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    administratorUsersPageViewModel = viewModelFactory.getAdministratorUsersPageViewModel();
    administratorUsersPageViewModel.loadUsers();
    loadUsers();
  }

  private void loadUsers()
  {
    usersTable.getItems().clear();
    userIdColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("userId"));
    userNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<User, String >("email"));
    dobColumn.setCellValueFactory(new PropertyValueFactory<User, String>("dob"));
    userTypeColumn.setCellValueFactory(new PropertyValueFactory<User, String>("type"));
    isSubscribedColumn.setCellValueFactory(new PropertyValueFactory<User, Boolean>("isSubscribed"));
    usersTable.setItems(administratorUsersPageViewModel.getAllUsers());
  }

  @Override public void handleClickMe(ActionEvent actionEvent)
  {
    if(actionEvent.getSource() == logOutBtn){
      administratorUsersPageViewModel.logOut();
      viewHandler.openLoginView();
    }
  }
}
