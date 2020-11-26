package client.core;

import client.views.login.LoginViewModel;

/**
 * Class used for initializing instances of view models.
 * @author Gosia, Piotr
 */

public class ViewModelFactory
{
  /**
   * @author Gosia, Piotr
   */
  private ModelFactory modelFactory;
  private LoginViewModel loginViewModel;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    this.modelFactory = modelFactory;
  }

  public LoginViewModel getLoginViewModel(){
    if(loginViewModel == null){
      loginViewModel = new LoginViewModel(modelFactory.getLoginRegisterModel());
    }
    return loginViewModel;
  }
}
