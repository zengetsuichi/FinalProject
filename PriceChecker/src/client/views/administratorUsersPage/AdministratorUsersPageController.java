package client.views.administratorUsersPage;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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


    // Initialize the filtered list with an observable list of all users.
    FilteredList<User> filteredData = new FilteredList<>(
        administratorUsersPageViewModel.getAllUsers(), p -> true);

    // Set Predicate expression whenever filter changes.
    searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredData.setPredicate(user -> {
        // If search bar text field is empty, display all users.
        if (newValue == null || newValue.isEmpty()) {
          return true;
        }

        // Compare username or email or dob or type or isSubscribed with the string from the search bar.
        String lowerCaseFilter = newValue.toLowerCase();

        if (user.getUsername().toLowerCase().contains(lowerCaseFilter))
          return true; // String matches with username.
        else if (user.getEmail().toLowerCase().contains(lowerCaseFilter))
          return true; // String matches with user email.
        else if (user.getDob().toLowerCase().contains(lowerCaseFilter))
          return true; // String matches with user dob.
        else if (user.getType().toLowerCase().contains(lowerCaseFilter))
          return true; // String matches with user type.
        else if (String.valueOf(user.getIsSubscribed()).toLowerCase().contains(lowerCaseFilter))
          return true; // String matches with user subscription.
        return false; // Does not match.
      });
    });

    // Initialize the filtered list with a sorted list.
    SortedList<User> sortedData = new SortedList<>(filteredData);

    // Order of values in the sorted list is bound to the order of values from the user table
    sortedData.comparatorProperty().bind(usersTable.comparatorProperty());

    // Add filtered data to the table.
    usersTable.setItems(sortedData);
  }

  @Override public void handleClickMe(ActionEvent actionEvent)
  {
    if(actionEvent.getSource() == logOutBtn){
      administratorUsersPageViewModel.logOut();
      viewHandler.openLoginView();
    }
    else if (actionEvent.getSource() == addUserBtn){
      viewHandler.openAddNewManagerView();
    }else if (actionEvent.getSource() == editUserBtn){
      if(usersTable.getSelectionModel().getSelectedCells().isEmpty())
      {
        errorLabel.setText("Please first select a user from the table.");
      } else {
      /*
          Taking the selected row from the table, creating a user object,
          passing it to the next view.
       */
        TablePosition pos = usersTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        User user = usersTable.getItems().get(row);

        viewHandler.openAdministratorEditUserView(user);
      }
  }
  }
}
