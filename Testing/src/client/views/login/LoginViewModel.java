package client.views.login;

import client.clientmodel.loginRegisterModel.LoginRegisterModel;

/**
 * Class used for pushing the request from controller class.
 * @author Gosia, Piotr
 */

public class LoginViewModel
{
  private LoginRegisterModel loginRegisterModel;

  public LoginViewModel(LoginRegisterModel loginRegisterModel)
  {
    this.loginRegisterModel = loginRegisterModel;
  }

  public String validateLogin(String username, String password)
  {
    return loginRegisterModel.validateLogin(username, password);
  }
}
