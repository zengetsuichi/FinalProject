package client.core;
import client.clientmodel.addNewManagerAdmin.AddNewManagerAdminModel;
import client.clientmodel.addNewManagerAdmin.AddNewManagerAdminModelManager;
import client.clientmodel.addNewProductAdministratorModel.AddNewProductAdminModel;
import client.clientmodel.addNewProductAdministratorModel.AddNewProductAdminModelManager;
import client.clientmodel.addNewProductShopManagerModel.AddNewProductShopManagerModel;
import client.clientmodel.addNewProductShopManagerModel.AddNewProductShopManagerModelManager;
import client.clientmodel.administratorEditUser.AdministratorEditUserModel;
import client.clientmodel.administratorEditUser.AdministratorEditUserModelManager;
import client.clientmodel.administratorModel.AdministratorModel;
import client.clientmodel.administratorModel.AdministratorModelManager;
import client.clientmodel.administratorUsersPageModel.AdministratorUsersPageModel;
import client.clientmodel.administratorUsersPageModel.AdministratorUsersPageModelManager;
import client.clientmodel.editProductAdministratorModel.EditProductAdministratorModel;
import client.clientmodel.editProductAdministratorModel.EditProductAdministratorModelManager;
import client.clientmodel.editProductShopManagerModel.EditProductShopManagerModel;
import client.clientmodel.editProductShopManagerModel.EditProductShopManagerModelManager;
import client.clientmodel.loginRegisterModel.LoginRegisterModel;
import client.clientmodel.loginRegisterModel.LoginRegisterModelManager;
import client.clientmodel.shopManagerModel.ShopManagerModel;
import client.clientmodel.shopManagerModel.ShopManagerModelManager;
import client.clientmodel.shoppingListModel.ShoppingListModel;
import client.clientmodel.shoppingListModel.ShoppingListModelManager;
import client.clientmodel.userModel.UserModel;
import client.clientmodel.userModel.UserModelManager;

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
  private AdministratorEditUserModel administratorEditUserModel;
  private EditProductShopManagerModel editProductShopManagerModel;
  private ShoppingListModel shoppingListModel;
  private UserModel userModel;
  private AddNewProductShopManagerModel addNewProductShopManagerModel;

  public ModelFactory(ClientFactory clientFactory){
    this.clientFactory = clientFactory;
  }

  public LoginRegisterModel getLoginRegisterModel(){
    if(loginRegisterModel == null){
      loginRegisterModel = new LoginRegisterModelManager(clientFactory.getClient());
    }
    return loginRegisterModel;
  }

  /**
   * Lazy instantiation of the Administrator model.
   * @author Gosia, Karlo
   */
  public AdministratorModel getAdministratorModel(){
    if(administratorModel == null){
      administratorModel = new AdministratorModelManager(clientFactory.getClient());
    }
    return administratorModel;
  }

  /**
   * Lazy instantiation of the Administrator add new product model.
   * @author Gosia, Karlo
   */
  public AddNewProductAdminModel getAddNewProductAdminModel()
  {
    if(addNewProductAdminModel == null){
      addNewProductAdminModel = new AddNewProductAdminModelManager(clientFactory.getClient());
    }
    return addNewProductAdminModel;
  }

  /**
   * Lazy instantiation of the Administrator edit product model.
   * @author Gosia, Karlo
   */
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

  /**
   * Lazy instantiation of the Administrator user model.
   * @author Karlo
   */
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

  public AdministratorEditUserModel getAdministratorEditUserModel()
  {
    if(administratorEditUserModel == null){
      administratorEditUserModel = new AdministratorEditUserModelManager(clientFactory.getClient());
    }
    return administratorEditUserModel;
  }

  public EditProductShopManagerModel getEditProductShopManagerModel()
  {
    if (editProductShopManagerModel == null){
      editProductShopManagerModel = new EditProductShopManagerModelManager(clientFactory.getClient());
    }
    return editProductShopManagerModel;
  }

  public UserModel getUserModel()
  {
    if (userModel == null){
      userModel = new UserModelManager(clientFactory.getClient());
    }
    return userModel;
  }

  /**
   * Lazy instantiation of the Shopping list model.
   * @author Gosia, Karlo
   */
  public ShoppingListModel getShoppingListModel()
  {
    if (shoppingListModel == null){
      shoppingListModel = new ShoppingListModelManager(clientFactory.getClient());
    }
    return shoppingListModel;
  }

  public AddNewProductShopManagerModel getAddNewProductShopManagerModel() {
    if (addNewProductShopManagerModel == null) {
      addNewProductShopManagerModel = new AddNewProductShopManagerModelManager(clientFactory.getClient());
    }
    return addNewProductShopManagerModel;
  }
}
