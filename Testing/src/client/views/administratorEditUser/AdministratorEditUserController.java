package client.views.administratorEditUser;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
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

public class AdministratorEditUserController implements ViewController
{

  @FXML private Button editButton;
  @FXML private Button goBackButton;
  @FXML private DatePicker dateOfBirthPicker;
  @FXML private Label userTypeLabel;
  @FXML private Label isSubscribedLabel;
  @FXML private Label errorLabel;
  @FXML private TextField usernameTextField;
  @FXML private Label userCharLabel;
  @FXML private TextField emailTextField;
  @FXML private Label emailCharLabel;
  @FXML private TextField passwordTextField;
  @FXML private Label passwordCharLabel;
  String oldUsername, oldEmail;

  private ViewHandler viewHandler;
  private AdministratorEditUserViewModel administratorEditUserViewModel;

  @Override public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    this.administratorEditUserViewModel = viewModelFactory.getAdministratorEditUserViewModel();

    setMinMaxDate();
    textFieldsMaxLenght();
    labelCharCount();
  }


  @Override public void handleClickMe(ActionEvent actionEvent)
  {
    if(actionEvent.getSource() == editButton){
      editButton();
    }else if(actionEvent.getSource() == goBackButton){
      goBackButton();
    }
  }

  private void labelCharCount()
  {
    userCharLabel.textProperty().bind(Bindings.length(usernameTextField.textProperty()).asString("%d" + "/20"));
    emailCharLabel.textProperty().bind(Bindings.length(emailTextField.textProperty()).asString("%d" + "/50"));
    passwordCharLabel.textProperty().bind(Bindings.length(passwordTextField.textProperty()).asString("%d" + "/20"));
  }

  private void textFieldsMaxLenght()
  {
    usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.length() > 20)
      {
        usernameTextField.setText(oldValue);
      }
    });

    emailTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.length() > 50)
      {
        emailTextField.setText(oldValue);
      }
    });

    passwordTextField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.length() > 20)
      {
        passwordTextField.setText(oldValue);
      }
    });
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


  public void setUser(User user)
  {
    userTypeLabel.setText(user.getType());
    String subscribed = "Not subscribed";
    if (user.getIsSubscribed())
    {
      subscribed = "Subscribed";
    }
    oldUsername = user.getUsername();
    oldEmail = user.getEmail();

    isSubscribedLabel.setText(subscribed);
    usernameTextField.setText(user.getUsername());
    emailTextField.setText(user.getEmail());
    passwordTextField.setText(user.getPassword());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String date = user.getDob();
    LocalDate localDate = LocalDate.parse(date, formatter);

    dateOfBirthPicker.setValue(localDate);

  }

  private void goBackButton()
  {
    viewHandler.openAdministratorUsersPage();
  }

  private void editButton()
  {
    String regex3 = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,10}$";
    Pattern pattern3 = Pattern.compile(regex3, Pattern.CASE_INSENSITIVE);
    Matcher matcher3 = pattern3.matcher(emailTextField.getText());

    if (!matcher3.matches())
    {
      errorLabel.setText("Invalid email field.");
    }
    else if (!usernameTextField.getText().matches(("[a-zA-Z0-9.\\-_]{1,20}")))
    {
      errorLabel.setText("Invalid entry into username field.");
    }
    else if (usernameTextField.getLength() > 20 || passwordTextField.getLength() > 20 || emailTextField.getLength() > 50
        || usernameTextField.getText().equals("") || passwordTextField.getText().equals("") || emailTextField.getText().equals("")
        || dateOfBirthPicker.getValue() == null)
    {
      errorLabel.setText("Invalid data or empty fields");
    }
    else
    {
      String username = usernameTextField.getText();
      String email = emailTextField.getText();
      String password = passwordTextField.getText();
      String dob = dateOfBirthPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

      String editResponse = administratorEditUserViewModel.validateEditUser(oldUsername, oldEmail, username, email, password, dob);

      if (editResponse.equals("User with this username already exist"))
      {
        errorLabel.setText("User with this username already exist");
      }

      else if (editResponse.equals("Email already used"))
      {
        errorLabel.setText("Email already used");
      }
      else if (editResponse.equals("User edited"))
      {
        viewHandler.openAdministratorUsersPage();
      }
    }

  }

}
