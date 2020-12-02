package client.views.login;

import client.clientmodel.loginRegisterModel.LoginRegisterModel;

/**
 * Class used for pushing the request from controller class and sending back the responses.
 *
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

  public void setClientUsername(String username)
  {
    loginRegisterModel.setClientUsername(username);
  }
}
