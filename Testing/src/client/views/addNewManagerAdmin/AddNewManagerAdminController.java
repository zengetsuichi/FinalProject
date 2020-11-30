package client.views.addNewManagerAdmin;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import shared.util.User;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewManagerAdminController implements ViewController
{
  @FXML
  private Button addManagerBtn;

  @FXML
  private Button goBackBtn;

  @FXML
  private Label errorLabel;

  @FXML
  private TextField usernameTextField;

  @FXML
  private TextField emailTextField;

  @FXML
  private TextField passwordTextField;

  @FXML
  private DatePicker dateOfBirthPicker;

  private ViewHandler viewHandler;
  private AddNewManagerAdminViewModel addNewManagerAdminViewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    addNewManagerAdminViewModel = viewModelFactory.getAddNewManagerAdminViewModel();
    setMinMaxDate();
  }

  @Override public void handleClickMe(ActionEvent actionEvent)
  {
    if(actionEvent.getSource() == goBackBtn){
      viewHandler.openAdministratorUsersPage();
    }
    else if(actionEvent.getSource() == addManagerBtn){
      addNewManager();
    }
  }

  private void addNewManager()
  {
    String username = usernameTextField.getText();
    String email = emailTextField.getText();
    String password = passwordTextField.getText();

    Pattern pattern = Pattern.compile("^.+@.+\\..+$");
    Matcher emailValidation = pattern.matcher(email);

    if (username.isEmpty() || email.isEmpty() || (dateOfBirthPicker.getValue()
        == null) || passwordTextField.getText().isEmpty())
    {
      errorLabel.setText("Fill out all the fields.");
    }
    else
    {
      if (username.length() <= 20)
      {
        if (email.length() <= 50 && emailValidation.matches())
        {
          //TODO Gosia add length password
          String dateOfBirth = dateOfBirthPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          User newManager = new User(username, email, password, dateOfBirth,
              "ShopManager");
          String response = addNewManagerAdminViewModel.addNewManager(newManager);
          if (response.equals("Shop manager added."))
          {
            viewHandler.openAdministratorUsersPage();
          }
          else
          {
            errorLabel.setText(response);
          }
        }
        else
          errorLabel.setText("Email field is invalid or too long.");
      }
      else
        errorLabel.setText("Username is too long.");
    }
  }


  private void setMinMaxDate()
  {
    DatePicker minDate = new DatePicker(); // DatePicker, used to define min date available
    DatePicker maxDate = new DatePicker(); // DatePicker, used to define max date available
    minDate.setValue(LocalDate.of(1920, Month.JANUARY, 1)); // Min date available will be 1920-01-01
    maxDate.setValue(LocalDate.now()); // Max date available will be today
    final Callback<DatePicker, DateCell> dayCellFactory;

    dayCellFactory = (final DatePicker datePicker) -> new DateCell()
    {
      @Override public void updateItem(LocalDate item, boolean empty)
      {
        super.updateItem(item, empty);
        if (item.isAfter(maxDate.getValue()))
        { //Disable all dates after required date
          setDisable(true);
          setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
        }
        if (item.isBefore(minDate.getValue()))
        { //Disable all dates before required date
          setDisable(true);
          setStyle("-fx-background-color: #ffc0cb;"); //To set background on different color
        }
      }
    };
    //Updates our DatePicker cell factory as follow:
    dateOfBirthPicker.setDayCellFactory(dayCellFactory);
  }
}
