package client.core;

import client.views.addNewManagerAdmin.AddNewManagerAdminViewModel;
import client.views.addNewProductAdmin.AddNewProductAdminViewModel;
import client.views.administrator.AdministratorViewModel;
import client.views.administratorEditUser.AdministratorEditUserViewModel;
import client.views.administratorUsersPage.AdministratorUsersPageViewModel;
import client.views.editProductAdmin.EditProductAdminViewModel;
import client.views.editProductShopManager.EditProductShopManagerViewModel;
import client.views.login.LoginViewModel;
import client.views.register.RegisterViewModel;

import client.views.shopManager.ShopManagerViewModel;

/**
 * Class used for lazy instantiation of view model instances and passing
 * the instance of models to the view models.
 *
 * @author Gosia, Piotr, Karlo
 */

public class ViewModelFactory
{
  private ModelFactory modelFactory;
  private LoginViewModel loginViewModel;
  private RegisterViewModel registerViewModel;
  private AdministratorViewModel administratorViewModel;
  private AddNewProductAdminViewModel addNewProductAdminViewModel;
  private EditProductAdminViewModel editProductAdminViewModel;
  private ShopManagerViewModel shopManagerViewModel;
  private AdministratorUsersPageViewModel administratorUsersPageViewModel;
  private AddNewManagerAdminViewModel addNewManagerAdminViewModel;
  private AdministratorEditUserViewModel administratorEditUserViewModel;
  private EditProductShopManagerViewModel editProductShopManagerViewModel;

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

  public AdministratorViewModel getAdministratorViewModel(){
    if(administratorViewModel == null){
      administratorViewModel = new AdministratorViewModel(modelFactory.getAdministratorModel());
    }
    return administratorViewModel;
  }

  public AddNewProductAdminViewModel getAddNewProductAdminViewModel()
  {
    if(addNewProductAdminViewModel == null){
      addNewProductAdminViewModel = new AddNewProductAdminViewModel(modelFactory.getAddNewProductAdminModel());
    }
    return addNewProductAdminViewModel;
  }

  public EditProductAdminViewModel getEditProductAdminViewModel()
  {
    if(editProductAdminViewModel == null){
      editProductAdminViewModel = new EditProductAdminViewModel(modelFactory.getEditProductAdministratorModel());
    }
    return editProductAdminViewModel;
  }

  public ShopManagerViewModel getShopManagerViewModel()
  {
    if(shopManagerViewModel == null){
      shopManagerViewModel = new ShopManagerViewModel(modelFactory.getShopManagerModel());
    }
    return shopManagerViewModel;
  }

  public RegisterViewModel getRegisterViewModel() {
    if(registerViewModel == null){
      registerViewModel = new RegisterViewModel(modelFactory.getLoginRegisterModel());
    }
    return registerViewModel;
  }

  public AdministratorUsersPageViewModel getAdministratorUsersPageViewModel() {
    if(administratorUsersPageViewModel == null){
      administratorUsersPageViewModel = new AdministratorUsersPageViewModel(modelFactory.getAdministratorUsersPageModel());
    }
    return administratorUsersPageViewModel;
  }

  public AddNewManagerAdminViewModel getAddNewManagerAdminViewModel()
  {
    if(addNewManagerAdminViewModel == null){
      addNewManagerAdminViewModel = new AddNewManagerAdminViewModel(modelFactory.getAddNewManagerAdminModel());
    }
    return addNewManagerAdminViewModel;
  }

  public AdministratorEditUserViewModel getAdministratorEditUserViewModel()
  {
    if(administratorEditUserViewModel == null){
      administratorEditUserViewModel = new AdministratorEditUserViewModel(modelFactory.getAdministratorEditUserModel());
    }
    return administratorEditUserViewModel;
  }
  public EditProductShopManagerViewModel getEditProductShopManagerViewModel()
  {
    if (editProductShopManagerViewModel == null){
      editProductShopManagerViewModel = new EditProductShopManagerViewModel(modelFactory.getEditProductShopManagerModel());
    }
    return editProductShopManagerViewModel;
  }
}
