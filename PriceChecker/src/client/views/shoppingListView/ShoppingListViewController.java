package client.views.shoppingListView;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.util.Callback;
import shared.util.Product;
import shared.util.ProductList;
import shared.util.ShopPrice;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
/**
 * Class implementing the view controller interface. Used for initializing
 * view components, retrieving data from them and providing functionality
 * for components.
 *
 * @author Gosia, Karlo
 */
public class ShoppingListViewController implements ViewController
{
  @FXML
  private Button backButton;
  @FXML
  private Button clearShoppingListButton;
  @FXML
  private TableView<Product> shoppingListTable;
  @FXML
  private TableColumn<Product, String> productNameColumn;
  @FXML
  private TableColumn<Product, Integer> quantityColumn;
  @FXML
  private TableColumn<ShopPrice,String> shopNameColumn;
  @FXML
  private TableColumn<Product,Integer> totalPriceColumn;
  @FXML
  private TableView<ShopPrice> totalPriceTable;
  @FXML
  private TableView<Product> productsPriceTable;
  @FXML
  private TableColumn<Product,String> productsAvailableColumn;
  @FXML
  private TableColumn<Product,Integer> shopPricesColumn;
  @FXML
  private TableView<Product> unavailableProductsTable;
  @FXML
  private TableColumn<Product,String> unavailableProductsColumn;

  private ViewHandler viewHandler;
  private ShoppingListViewViewModel shoppingListViewViewModel;
  private String thisUser;

  @Override public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    shoppingListViewViewModel = viewModelFactory.getShoppingListViewViewModel();

    shoppingListViewViewModel.loadShoppingList();
    loadTable();
    shoppingListTable.setPlaceholder(new Label("No products in the table."));

    addButtonToTable();
    addComboBox();
  }

  private void addComboBox()
  {
    shoppingListTable.setEditable(true);
    ObservableList<Integer> quantityList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,
        11,12,13,14,15,16,17,18,19,20);

    quantityColumn.setCellValueFactory(param -> {
      Product product = param.getValue();
      return new SimpleObjectProperty<>(product.getQuantity());
    });

    quantityColumn.setCellFactory(ComboBoxTableCell.forTableColumn(quantityList));

    quantityColumn.setOnEditCommit((TableColumn.CellEditEvent<Product, Integer> event) -> {
      TablePosition<Product, Integer> pos = event.getTablePosition();
      int row = pos.getRow();
      Product product = event.getTableView().getItems().get(row);

      int newQuantity = event.getNewValue();
      product.setQuantity(newQuantity);
      shoppingListViewViewModel.changeQuantityForThisProduct(product.getProductId(), product.getQuantity());
    });
  }

  private void addButtonToTable()
  {
    TableColumn<Product, Void> colBtn = new TableColumn();

    Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<>() {
      @Override
      public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
        return new TableCell<>() {

          private final Button btn = new Button();
          {
            btn.setOnAction((ActionEvent event) -> {
              Product data = getTableView().getItems().get(getIndex());
              shoppingListViewViewModel.deleteTheProductFromSL(data.getProductId());
            });
          }

          @Override
          public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setGraphic(null);
            } else {
              setGraphic(btn);
              ImageView view = new ImageView(new Image("file:PriceChecker/src/client/views/shoppingListView/Trashcan.png"));
              btn.setGraphic(view);
              btn.setBackground(Background.EMPTY);
            }
          }
        };
      }
    };
    ImageView view = new ImageView(new Image("file:PriceChecker/src/client/views/shoppingListView/Trashcan.png"));

    colBtn.setGraphic(view);
    colBtn.setStyle("-fx-alignment: CENTER;");
    colBtn.setCellFactory(cellFactory);
    colBtn.setMinWidth(50); colBtn.setMaxWidth(50);

    shoppingListTable.getColumns().add(colBtn);

    shoppingListViewViewModel.loadPriceList();
    loadPricesTable();
    totalPriceTable.setPlaceholder(new Label("No Products selected."));
  }

  private void loadTable()
  {
    shoppingListTable.getItems().clear();
    productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
    quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    shoppingListTable.setItems(shoppingListViewViewModel.getShoppingList());
  }

  @Override public void handleClickMe(ActionEvent actionEvent)
  {
    if(actionEvent.getSource() == backButton)
      viewHandler.openUserView();
    else if(actionEvent.getSource() == clearShoppingListButton)
    {
      if(!shoppingListTable.getItems().isEmpty())
      {
        shoppingListViewViewModel.clearSL();
        productsPriceTable.getItems().clear();
        totalPriceTable.getItems().clear();
        unavailableProductsTable.getItems().clear();
      }
    }
  }

  public void setUser(String thisUser)
  {
    this.thisUser = thisUser;
  }
  private void loadPricesTable()
  {
    totalPriceTable.getItems().clear();
    totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    shopNameColumn.setCellValueFactory(new PropertyValueFactory<>("shopName"));
    totalPriceTable.setItems(shoppingListViewViewModel.getTotalPricesList());
  }
  public void loadProductShopInfo(MouseEvent mouseEvent)
  {
    productsPriceTable.getItems().clear();
    Platform.runLater(()-> {

      if (!totalPriceTable.getSelectionModel().isEmpty())
      {
        TablePosition pos = totalPriceTable.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        ShopPrice item = totalPriceTable.getItems().get(row);
        String shopName = item.getShopName();

        //get available products for each shop by shop name
        ObservableList<Product> availableProductList = shoppingListViewViewModel
            .getAvailableProducts(shopName, thisUser);
        productsPriceTable.getItems().clear();
        productsAvailableColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        shopPricesColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));

        for (int i = 0; i < availableProductList.size(); i++)
          productsPriceTable.getItems().add(availableProductList.get(i));

        //get unavailable products for each shop by shop name
        ObservableList<Product> unavailableProducts = shoppingListViewViewModel
            .getUnavailableProducts(shopName, thisUser);
        unavailableProductsTable.getItems().clear();
        unavailableProductsColumn.setCellValueFactory(
            new PropertyValueFactory<Product, String>("productName"));

        for (int i = 0; i < unavailableProducts.size(); i++)
          unavailableProductsTable.getItems().add(unavailableProducts.get(i));
      }
    });
  }
}
