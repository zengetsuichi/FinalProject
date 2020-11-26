package client.views.login;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * Class used for initializing the view components based on
 * the data send from view model.
 * @author Gosia, Piotr
 */

public class LoginController implements ViewController
{
  @FXML private TextField usernameTextField;
  @FXML private PasswordField passwordTextField;
  @FXML private Button loginBtn;
  @FXML private Button registerBtn;
  @FXML private Label errorLabel;

  private ViewHandler viewHandler;
  private LoginViewModel loginViewModel;

  @Override public void init(ViewHandler viewHandler,
      ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    loginViewModel = viewModelFactory.getLoginViewModel();
  }

  public void handleClickMe(ActionEvent actionEvent)
  {
    if (actionEvent.getSource() == loginBtn)
    {
      if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty())
      {
        errorLabel.setText("Fill out all fields");
      }
      else
      {
        String username = null;
        String password = null;

        username = usernameTextField.getText();
        password = passwordTextField.getText();

        String loginResponse = loginViewModel.validateLogin(username, password);

        if (loginResponse.equals("User with this username does not exist"))
        {
          errorLabel.setText("User with this username does not exist");
        }
        else if (loginResponse.equals("Wrong credentials"))
        {
          errorLabel.setText("Wrong credentials.");
        }
        else if (loginResponse.equals("User"))
        {
          loginViewModel.setClientUsername(username);
          viewHandler.openNormalUserView();
        }
        else if (loginResponse.contains("Admin"))
        {
          loginViewModel.setClientUsername(username);
          viewHandler.openAdministratorView();
        }
        else if (loginResponse.contains("ShopManager"))
        {
          loginViewModel.setClientUsername(username);
          viewHandler.openShopManagerView();
        }
        else
        {
          System.out.println(loginResponse + "here");
        }
      }
    }
    else if (actionEvent.getSource() == registerBtn)
    {
      viewHandler.openRegisterView();
    }
  }
}