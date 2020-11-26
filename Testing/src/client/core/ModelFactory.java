package client.core;

import client.clientmodel.loginRegisterModel.LoginRegisterModel;
import client.clientmodel.loginRegisterModel.LoginRegisterModelManager;

/**
 * Class used for initializing instances of models. //TODO Gosia check
 * @author Gosia, Piotr
 */
public class ModelFactory
{
  /**
   * @author Gosia, Piotr
   */
  private LoginRegisterModel loginRegisterModel;
  private ClientFactory clientFactory;


  public ModelFactory(ClientFactory clientFactory){
    this.clientFactory = clientFactory;
  }

  public LoginRegisterModel getLoginRegisterModel(){
    if(loginRegisterModel == null){
      loginRegisterModel = new LoginRegisterModelManager(clientFactory.getClient());
    }
    return loginRegisterModel;
  }

}
