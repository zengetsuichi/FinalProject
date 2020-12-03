package client.core;

import client.views.addNewManagerAdmin.AddNewManagerAdminController;
import client.views.addNewProductAdmin.AddNewProductAdminController;
import client.views.addNewProductShopManager.AddNewProductShopManagerController;
import client.views.administrator.AdministratorController;
import client.views.administratorEditUser.AdministratorEditUserController;
import client.views.administratorUsersPage.AdministratorUsersPageController;
import client.views.editProductAdmin.EditProductAdminController;
import client.views.editProductShopManager.EditProductShopManagerController;
import client.views.login.LoginController;
import client.views.register.RegisterController;
import client.views.shopManager.ShopManagerController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import shared.util.Product;
import shared.util.User;

import java.io.IOException;

 /**
 * Class that provides methods for opening specific views.
 * @author Gosia, Piotr, Karlo
 */

public class ViewHandler
{
  private ViewModelFactory viewModelFactory;
  private Stage stage;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    stage = new Stage();
  }

  public void start(){

    // Terminating and closing the program on X button in window
    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent t) {
        Platform.exit();
        System.exit(0);
      }
    });

    openLoginView();
  }

  public void openLoginView(){
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/login/Login.fxml"));
      Parent root = loader.load();
      LoginController view = loader.getController();

      view.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Price Checker Login");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }



  public void openRegisterView(){
    //TODO change this method
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/register/Register.fxml"));
      Parent root = loader.load();
      RegisterController view = loader.getController();

      view.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Price Checker Register");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openShopManagerView(){
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/shopManager/ShopManager.fxml"));
      Parent root = loader.load();
      ShopManagerController view = loader.getController();

      view.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Shop Manager");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openAdministratorView(){
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/administrator/Administrator.fxml"));
      Parent root = loader.load();
      AdministratorController view = loader.getController();

      view.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Price Checker Administrator");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openNormalUserView(){
    //TODO change this method
    System.out.println("User view opened.");
  }

  public void openAddNewProductView()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/addNewProductAdmin/AddNewProductAdmin.fxml"));
      Parent root = loader.load();
      AddNewProductAdminController view = loader.getController();

      view.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Add Product Administrator");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openEditProductView(Product product, ObservableList<String> tags)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/editProductAdmin/EditProductAdmin.fxml"));
      Parent root = loader.load();
      EditProductAdminController view = loader.getController();

      view.setProductData
          (product, tags);
      view.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Edit Product Administrator");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

    public void openShopManagerAddNewProductView() {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/addNewProductShopManager/addNewProductShopManager.fxml"));
            Parent root = loader.load();
            AddNewProductShopManagerController view = loader.getController();


            view.init(this, viewModelFactory);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Add New Product Shop Manager");
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

  public void openAdministratorUsersPage()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/administratorUsersPage/AdministratorUsersPage.fxml"));
      Parent root = loader.load();
      AdministratorUsersPageController view = loader.getController();

      view.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Edit Users Administrator");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openAddNewManagerView(){
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/addNewManagerAdmin/AddNewManagerAdmin.fxml"));
      Parent root = loader.load();
      AddNewManagerAdminController view = loader.getController();

      view.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Add shop manager");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openAdministratorEditUserView(User user)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("../views/administratorEditUser/AdministratorEditUser.fxml"));
      Parent root = loader.load();
      AdministratorEditUserController ctlr = loader.getController();
      ctlr.setUser(user);

      ctlr.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Edit User");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
  public void openEditShopManagerProductView(Product product, ObservableList<String> tags)
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(
          "../views/editProductShopManager/EditProductShopManager.fxml"));
      Parent root = loader.load();
      EditProductShopManagerController view = loader.getController();

      view.setProductData(product, tags);
      view.init(this, viewModelFactory);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Edit Product Shop Manager");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
