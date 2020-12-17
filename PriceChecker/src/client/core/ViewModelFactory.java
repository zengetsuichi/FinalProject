package client.core;

import client.views.addNewManagerAdmin.AddNewManagerAdminViewModel;
import client.views.addNewProductAdmin.AddNewProductAdminViewModel;
import client.views.addNewProductShopManager.AddNewProductShopManagerViewModel;
import client.views.administrator.AdministratorViewModel;
import client.views.administratorEditUser.AdministratorEditUserViewModel;
import client.views.administratorUsersPage.AdministratorUsersPageViewModel;
import client.views.editProductAdmin.EditProductAdminViewModel;
import client.views.editProductShopManager.EditProductShopManagerViewModel;
import client.views.login.LoginViewModel;
import client.views.register.RegisterViewModel;
import client.views.shopManager.ShopManagerViewModel;
import client.views.shoppingListView.ShoppingListViewViewModel;
import client.views.user.UserViewModel;

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
  private ShoppingListViewViewModel shoppingListViewViewModel;
  private UserViewModel userViewModel;
  private AddNewProductShopManagerViewModel addNewProductShopManagerViewModel;

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

  /**
   * Lazy instantiation of the Administrator view model.
   * @author Gosia, Karlo
   */
  public AdministratorViewModel getAdministratorViewModel(){
    if(administratorViewModel == null){
      administratorViewModel = new AdministratorViewModel(modelFactory.getAdministratorModel());
    }
    return administratorViewModel;
  }

  /**
   * Lazy instantiation of the Administrator add new product view model.
   * @author Gosia, Karlo
   */
  public AddNewProductAdminViewModel getAddNewProductAdminViewModel()
  {
    if(addNewProductAdminViewModel == null){
      addNewProductAdminViewModel = new AddNewProductAdminViewModel(modelFactory.getAddNewProductAdminModel());
    }
    return addNewProductAdminViewModel;
  }

  /**
   * Lazy instantiation of the Administrator edit product view model.
   * @author Gosia, Karlo
   */
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

  /**
   * Lazy instantiation of the Administrator users view model.
   * @author Karlo
   */
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
  /**
   * Lazy instantiation of the Edit Product ShopManager view model.
   * @author Hadi
   */
  public EditProductShopManagerViewModel getEditProductShopManagerViewModel()
  {
    if (editProductShopManagerViewModel == null){
      editProductShopManagerViewModel = new EditProductShopManagerViewModel(modelFactory.getEditProductShopManagerModel());
    }
    return editProductShopManagerViewModel;
  }

  public UserViewModel getUserViewModel()
  {
    if (userViewModel == null){
      userViewModel = new UserViewModel(modelFactory.getUserModel());
    }
    return userViewModel;
  }

  /**
   * Lazy instantiation of the Shopping list view model.
   * @author Gosia, Karlo
   */
  public ShoppingListViewViewModel getShoppingListViewViewModel()
  {
    if (shoppingListViewViewModel == null){
      shoppingListViewViewModel = new ShoppingListViewViewModel(modelFactory.getShoppingListModel());
    }
    return shoppingListViewViewModel;
  }

  public AddNewProductShopManagerViewModel getAddNewProductShopManagerViewModel() {
    if(addNewProductShopManagerViewModel == null){
      addNewProductShopManagerViewModel = new AddNewProductShopManagerViewModel(modelFactory.getAddNewProductShopManagerModel());
    }
    return addNewProductShopManagerViewModel;
  }
}
