package client.core;
import client.clientmodel.shopManagerModel.ShopManagerModel;
import client.views.addNewProductAdmin.AddNewProductAdminViewModel;
import client.views.administrator.AdministratorViewModel;
import client.views.editProductAdmin.EditProductAdminViewModel;
import client.views.login.LoginViewModel;
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
  private AdministratorViewModel administratorViewModel;
  private AddNewProductAdminViewModel addNewProductAdminViewModel;
  private EditProductAdminViewModel editProductAdminViewModel;
  private ShopManagerViewModel shopManagerViewModel;

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
}
