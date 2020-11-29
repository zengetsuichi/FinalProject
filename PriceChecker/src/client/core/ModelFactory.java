package client.core;
import client.clientmodel.addNewManagerAdmin.AddNewManagerAdminModel;
import client.clientmodel.addNewManagerAdmin.AddNewManagerAdminModelManager;
import client.clientmodel.addNewProductAdministratorModel.AddNewProductAdminModel;
import client.clientmodel.addNewProductAdministratorModel.AddNewProductAdminModelManager;
import client.clientmodel.administratorModel.AdministratorModel;
import client.clientmodel.administratorModel.AdministratorModelManager;
import client.clientmodel.administratorUsersPageModel.AdministratorUsersPageModel;
import client.clientmodel.administratorUsersPageModel.AdministratorUsersPageModelManager;
import client.clientmodel.editProductAdministratorModel.EditProductAdministratorModel;
import client.clientmodel.editProductAdministratorModel.EditProductAdministratorModelManager;
import client.clientmodel.loginRegisterModel.LoginRegisterModel;
import client.clientmodel.loginRegisterModel.LoginRegisterModelManager;
import client.clientmodel.shopManagerModel.ShopManagerModel;
import client.clientmodel.shopManagerModel.ShopManagerModelManager;

/**
 * Class used for lazy instantiation of model instances and passing
 * the instance of a client to the models.
 *
 * @author Gosia, Piotr, Karlo
 */

public class ModelFactory
{
  private LoginRegisterModel loginRegisterModel;
  private AdministratorModel administratorModel;
  private AddNewProductAdminModel addNewProductAdminModel;
  private EditProductAdministratorModel editProductAdministratorModel;
  private ShopManagerModel shopManagerModel;
  private AdministratorUsersPageModel administratorUsersPageModel;
  private ClientFactory clientFactory;
  private AddNewManagerAdminModel addNewManagerAdminModel;

  public ModelFactory(ClientFactory clientFactory){
    this.clientFactory = clientFactory;
  }

  public LoginRegisterModel getLoginRegisterModel(){
    if(loginRegisterModel == null){
      loginRegisterModel = new LoginRegisterModelManager(clientFactory.getClient());
    }
    return loginRegisterModel;
  }

  public AdministratorModel getAdministratorModel(){
    if(administratorModel == null){
      administratorModel = new AdministratorModelManager(clientFactory.getClient());
    }
    return administratorModel;
  }

  public AddNewProductAdminModel getAddNewProductAdminModel()
  {
    if(addNewProductAdminModel == null){
      addNewProductAdminModel = new AddNewProductAdminModelManager(clientFactory.getClient());
    }
    return addNewProductAdminModel;
  }

  public EditProductAdministratorModel getEditProductAdministratorModel()
  {
    if(editProductAdministratorModel == null){
      editProductAdministratorModel = new EditProductAdministratorModelManager(clientFactory.getClient());
    }
    return editProductAdministratorModel;
  }

  public ShopManagerModel getShopManagerModel()
  {
    if(shopManagerModel == null){
      shopManagerModel = new ShopManagerModelManager(clientFactory.getClient());
    }
    return shopManagerModel;
  }

  public AdministratorUsersPageModel getAdministratorUsersPageModel()
  {
    if(administratorUsersPageModel == null){
      administratorUsersPageModel = new AdministratorUsersPageModelManager(clientFactory.getClient());
    }
    return administratorUsersPageModel;
  }

  public AddNewManagerAdminModel getAddNewManagerAdminModel(){
    if(addNewManagerAdminModel == null){
      addNewManagerAdminModel = new AddNewManagerAdminModelManager(clientFactory.getClient());
    }
    return addNewManagerAdminModel;
  }
}
