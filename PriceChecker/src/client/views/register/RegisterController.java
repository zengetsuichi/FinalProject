package client.views.register;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import client.views.login.LoginViewModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class used for initializing the view components based on
 * the data send from view model.
 *
 * @author Dorin, Piotr
 */

public class RegisterController implements ViewController
{

  @FXML private TextField usernameTextField;

  @FXML private TextField emailTextField;

  @FXML private TextField passwordTextField;

  @FXML private TextField repeatPasswordTextField;

  @FXML private DatePicker dobDateField;

  @FXML private Label errorLabel;

  private ViewHandler viewHandler;
  private RegisterViewModel registerViewModel;

  @Override public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    registerViewModel = viewModelFactory.getRegisterViewModel();
    setMinMaxDate();
  }

  @Override public void handleClickMe(ActionEvent actionEvent)
  {

  }

  @FXML void onBackButton(ActionEvent event)
  {
    registerViewModel.clear();
    viewHandler.openLoginView();
  }

  @FXML void onRegisterButton(ActionEvent event)
  {

    String regex3 = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,10}$";
    Pattern pattern3 = Pattern.compile(regex3, Pattern.CASE_INSENSITIVE);
    Matcher matcher3 = pattern3.matcher(emailTextField.getText());

    if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || emailTextField.getText().isEmpty()
        || repeatPasswordTextField.getText().isEmpty() || dobDateField.getValue() == null)
    {
      errorLabel.setText("Fill out all fields.");

    }
    else if (!passwordTextField.getText().equals(repeatPasswordTextField.getText()))
    {
      errorLabel.setText("Passwords dose not match.");
    }
    else if (usernameTextField.getLength() > 20)
    {
      errorLabel.setText("Username too long.");
    }
    else if (passwordTextField.getLength() > 20)
    {
      errorLabel.setText("Password too long.");
    }
    else if (emailTextField.getLength() > 50)
    {
      errorLabel.setText("Email too long.");
    }
    else if (!usernameTextField.getText().matches(("[a-zA-Z0-9.\\-_]{1,20}")))
    {
      errorLabel.setText("Invalid entry into username field.");
    }
    else if (!matcher3.matches())
    {
      errorLabel.setText("Invalid entry into email field.");
    }
    else
    {
      String username = usernameTextField.getText();
      String email = emailTextField.getText();
      String password = passwordTextField.getText();
      String dob = dobDateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

      String registerResponse = registerViewModel.validateRegister(username, email, password, dob);
      if (registerResponse.equals("User with this username already exist"))
      {
        errorLabel.setText("User with this username already exist");
      }
      else if (registerResponse.equals("Email already used"))
      {
        errorLabel.setText("Email already used");
      }
      else if (registerResponse.equals("User registered"))
      {
        errorLabel.setTextFill(Color.GREEN);
        errorLabel.setText("User registered! Welcome to community!");
        Platform.runLater(() -> {
          try
          {
            Thread.sleep(2500);
            errorLabel.setTextFill(Color.RED);
            viewHandler.openLoginView();
          }
          catch (InterruptedException e)
          {
            e.printStackTrace();
          }
        });
      }
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
    dobDateField.setDayCellFactory(dayCellFactory);
  }
}

