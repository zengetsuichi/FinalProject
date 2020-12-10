package client.views.user;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.IndexedCheckModel;
import shared.util.Product;
import shared.util.ProductList;

import java.util.*;

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
  @FXML private CheckListView<String> categoryTable;

  private ViewHandler viewHandler;
  private UserViewModel userViewModel;
  private String thisUser;
  private ObservableList<Product> shoppinglist = FXCollections.observableArrayList();
  private ObservableList<String> categoryList = FXCollections.observableArrayList();
  private ObservableList<String> tempCategoryList = FXCollections.observableArrayList();
  private FilteredList<Product> filteredData;
  private Map<Integer, List<String>> tagsHashMap;

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

    // loads categories and set categories
    categoryList = userViewModel.getAllProductCategories();
    userViewModel.loadData();
    categoryTable.setItems(categoryList);

    // Initialize the filtered list with an observable list of all products.
    filteredData = new FilteredList<Product>(userViewModel.getListOfAllProducts(), p -> true);

    // Making a haspmap that contains product id and array list of tags for this product
    tagsHashMap = new HashMap<Integer, List<String>>();
    for (int i = 0; i < filteredData.size(); i++)
    {
      tagsHashMap.put(filteredData.get(i).getProductId(), userViewModel.getTagsById(filteredData.get(i).getProductId()));
    }

    // loads product table
    loadProductTable();
    // Checks for changes in the category table and saves to a variable that is used into loadProductTable()
    categorySorting();
  }

  private void loadProductTable()
  {

    productTable.getItems().clear();
    productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
    productDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("description"));
    productCategoryColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));

    // Set Predicate expression whenever filter changes.
    searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
      filteredData.setPredicate(product -> {

        // Sets the list of tags of this product to a List
        List<String> tagsById = tagsHashMap.get(product.getProductId());

        // Display products that have the same category as in the list
        if (tempCategoryList.contains(product.getCategory()))
        {
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
          // Check if tags for each product contains text in search bat
          for (String s : tagsById)
          {
            if (s.contains(newValue.toLowerCase()))
            {
              return true;
            }
          }
        }
        return false; // Does not match.
      });
    });
    System.out.println(userViewModel.getTagsById(1));

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

  // Checks for changes in the category table and save them to a field variable
  public void categorySorting()
  {

    categoryTable.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>()
    {
      String textInSearchBar;
      @Override public void onChanged(ListChangeListener.Change<? extends String> c)
      {
        c.next();
        if (c.getList().isEmpty())
        {
          // If non of categories are selected, select all of them
          tempCategoryList = categoryList;
        }
        else
        {
          // Save checked categories to a list
          tempCategoryList = (ObservableList<String>) c.getList();
        }

        // Trigger the search bar to get the newest changes in category selection
        textInSearchBar =  searchBar.getCharacters().toString();
        searchBar.setText("");
        searchBar.setText(textInSearchBar);
      }
    });

  }

  public void resetCategoriesBtn(ActionEvent actionEvent)
  {

    //   clears category selection
    for (int i = 0; i < categoryTable.getItems().size(); i++)
    {
      categoryTable.getCheckModel().clearCheck(i);
    }

  }
}
