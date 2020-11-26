package client.core;

import client.views.login.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
 /**
 * Class used for opening specific views.
 * @author Gosia, Piotr
 */
public class ViewHandler
{
  /**
   * @author Gosia, Piotr
   */
  private ViewModelFactory viewModelFactory;
  private Stage stage;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.viewModelFactory = viewModelFactory;
    stage = new Stage();
  }

  public void start(){
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
    System.out.println("Register view opened.");
  }

  public void openShopManagerView(){
    //TODO change this method
    System.out.println("Shop manager view opened.");
  }

  public void openAdministratorView(){
    //TODO change this method
    System.out.println("Administrator view opened.");
  }

  public void openNormalUserView(){
    //TODO change this method
    System.out.println("User view opened.");
  }
}
