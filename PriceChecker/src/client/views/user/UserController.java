package client.views.user;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.CheckListView;
import shared.util.Product;
import shared.util.ProductList;

public class UserController implements ViewController
{

  @FXML private TableView<Product> productTable;
  @FXML private TableColumn<Product, String> productNameColumn;
  @FXML private TableColumn<Product, String> productDescriptionColumn;
  @FXML private TableColumn<Product, String> productCategoryColumn;
  @FXML private Button logOut;
  @FXML private Label errorLabel;
  @FXML private TextField searchBar;
  @FXML private Button addProduct;
  @FXML private Button openShopingList;
  @FXML private Label productCountLabel;
  @FXML private Button MainPage;
  @FXML private Button shoppingHistory;
  @FXML private Button subscribeButton;
  @FXML private Label usernameLabel;
  @FXML private CheckListView<?> categoryTable;

  private ViewHandler viewHandler;
  private UserViewModel userViewModel;
  private String thisUser;
  private ObservableList<Product> shoppinglist = FXCollections.observableArrayList();

  @Override public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    userViewModel = viewModelFactory.getUserViewModel();

    thisUser = userViewModel.getLoggedInUser();
    usernameLabel.setText(thisUser);

    userViewModel.loadProductData();
    userViewModel.loadShoppingList();

    shoppinglist = userViewModel.getThisUserShoppingList();
    productCountLabel.setText("Products: " + shoppinglist.size());
    loadProductTable();
  }

  private void loadProductTable()
  {
    productTable.getItems().clear();
    productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
    productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
    productCategoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));

    // Initialize the filtered list with an observable list of all products.
    FilteredList<Product> filteredData = new FilteredList<Product>(userViewModel.getListOfAllProducts(), p -> true);

    // Set Predicate expression whenever filter changes.
    searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredData.setPredicate(product -> {
        // If search bar text field is empty, display all products.
        if (newValue == null || newValue.isEmpty())
        {
          return true;
        }

        // Compare product name or category or description with the string from the search bar.
        String lowerCaseFilter = newValue.toLowerCase();

        if (product.getProductName().toLowerCase().contains(lowerCaseFilter))
        {
          return true; // String matches with product name.
        }
        else if (product.getCategory().toLowerCase().contains(lowerCaseFilter))
          return true; // String matches with product category.
        else if (product.getDescription().toLowerCase().contains(lowerCaseFilter))
          return true; // String matches with product description.
        return false; // Does not match.
      });
    });

    // Initialize the filtered list with a sorted list.
    SortedList<Product> sortedData = new SortedList<>(filteredData);

    // Order of values in the sorted list is bound to the order of values from the product table
    sortedData.comparatorProperty().bind(productTable.comparatorProperty());

    // Add filtered data to the table.
    productTable.setItems(sortedData);
  }

  @Override public void handleClickMe(ActionEvent actionEvent)
  {
  }

  @FXML void MainPageBtn(ActionEvent event)
  {

  }

  @FXML void addProductBtn(ActionEvent event)
  {
    errorLabel.setText("");
    if (productTable.getSelectionModel().getSelectedCells().isEmpty())
    {
      errorLabel.setText("Please first select a product in a table.");
    }
    else
    {
      /*
          Taking the selected row from the table, creating a product object,
          getting the product and putting it into a ProductList so it can be sent
          to the Shopping List view.
       */
      TablePosition pos = productTable.getSelectionModel().getSelectedCells().get(0);
      int row = pos.getRow();
      Product item = productTable.getItems().get(row);

      if (shoppinglist.contains(item))
        errorLabel.setText("Product already in the shopping list!");
      else
      {
        userViewModel.addProductToSL(item);
      }

      productCountLabel.setText("Products: " + shoppinglist.size());

      /*
      for (int i = 0; i < productList.getProducts().size(); i++)
      {
        System.out.println(productList.getProducts().get(i).getProductName());
      }
      System.out.println();
      */

    }
  }

  @FXML void logOutBtn(ActionEvent event)
  {
    userViewModel.logOut();
    viewHandler.openLoginView();
  }

  @FXML void shoppingHistoryBtn(ActionEvent event)
  {

  }

  @FXML void subscribeButton(ActionEvent event)
  {

  }

  @FXML void openShopingListBtn(ActionEvent event)
  {
    viewHandler.openShoppingList(thisUser);
  }

}
