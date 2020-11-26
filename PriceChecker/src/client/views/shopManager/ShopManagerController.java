package client.views.shopManager;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ShopManagerController implements ViewController
{
  @FXML
  private TableView<?> productTable;

  @FXML
  private TableColumn<?, ?> productIdColumn;

  @FXML
  private TableColumn<?, ?> productNameColumn;

  @FXML
  private TableColumn<?, ?> productDescriptionColumn;

  @FXML
  private TableColumn<?, ?> productCategoryColumn;

  @FXML
  private TableColumn<?, ?> priceColumn;

  @FXML
  private Button logOutBtn;

  @FXML
  private TableView<?> tagListTable;

  @FXML
  private TableColumn<?, ?> tagsColumn;

  @FXML
  private Label errorLabel;

  @FXML
  private TextField searchBar;

  @FXML
  private Button addProductBtn;

  @FXML
  private Button editProductBtn;

  @FXML
  private Button deleteProductBtn;

  @FXML
  private Label loggedInAs;
 private ViewHandler viewHandler;
 private ShopManagerViewModel shopManagerViewModel;
 private String username;

  @Override public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    shopManagerViewModel = viewModelFactory.getShopManagerViewModel();
    username = shopManagerViewModel.getLoggedInUser();
    loggedInAs.setText(username);
  }

  @Override public void handleClickMe(ActionEvent actionEvent)
  {

  }


  @FXML
  void loadTags(MouseEvent event) {

  }
}
