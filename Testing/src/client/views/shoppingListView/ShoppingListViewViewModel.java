package client.views.shoppingListView;
import client.clientmodel.shoppingListModel.ShoppingListModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventType;
import shared.util.Product;
import shared.util.ShopPrice;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
/**
 * Class responsible for managing and storing controller data.
 *
 * @author Gosia, Karlo
 */
public class ShoppingListViewViewModel
{
  private ObservableList<ShopPrice> totalPricesList;
  private ShoppingListModel shoppingListModel;
  private ObservableList<Product> shoppingList;
  private ObservableList<Product> unavailableProductsList;
  private ObservableList<Product> availableProductsList;

  public ShoppingListViewViewModel(ShoppingListModel shoppingListModel)
  {
    this.shoppingListModel = shoppingListModel;
    shoppingList = FXCollections.observableArrayList();
    totalPricesList = FXCollections.observableArrayList();
    unavailableProductsList = FXCollections.observableArrayList();
    availableProductsList = FXCollections.observableArrayList();

    shoppingListModel.addListener(EventType.DELETED_PRODUCT_PRICE.name(), this::newProduct);
    shoppingListModel.addListener(EventType.EDIT_SHOP_MANAGER_PRODUCT.name(), this::newProduct);
    shoppingListModel.addListener(EventType.NEW_PRODUCT.name(), this::newProduct);
    shoppingListModel.addListener(EventType.DELETED_PRODUCT.name(), this::newProduct);
    shoppingListModel.addListener(EventType.SHOPPING_LIST_CHANGE.name(),this::newProduct);
  }

  private void newProduct(PropertyChangeEvent event)
  {
    Platform.runLater(()->
        newProducts()
        );
  }

  public void loadShoppingList()
  {
    shoppingList.setAll(shoppingListModel.loadShoppingList());
  }

  public ObservableList<Product> getShoppingList()
  {
    return shoppingList;
  }


  public void clearSL()
  {
    Boolean clear = shoppingListModel.clearSL();
    if(clear)
      shoppingList.clear();
  }

  public ObservableList<ShopPrice> getTotalPricesList()
  {
    return totalPricesList;
  }
  public void loadPriceList()
  {
    totalPricesList.setAll(shoppingListModel.loadPricesList());
  }

  public void deleteTheProductFromSL(int productId)
  {
    Boolean response = shoppingListModel.deleteTheProductFromSL(productId);
    if(response){
      loadShoppingList();
    }
    else{
      System.out.println("Doesnt work");
    }
  }

  public void changeQuantityForThisProduct(int productId, int quantity)
  {
    shoppingListModel.changeQuantityForThisProduct(productId, quantity);
  }

  public ObservableList<Product> getAvailableProducts(String shopName,String clientUsername)
  {
    ArrayList<Product> availableProducts = shoppingListModel.getAvailableProducts(shopName,clientUsername);
    availableProductsList.setAll(availableProducts);
    return availableProductsList;
  }

  public ObservableList<Product> getUnavailableProducts(String shopName,String clientUsername)
  {
    ArrayList<Product> unavailableProducts = shoppingListModel.getUnavailableProducts(shopName, clientUsername);
    unavailableProductsList.setAll(unavailableProducts);
    return unavailableProductsList;
  }
  private void newProducts()
  {
    loadShoppingList();
    loadPriceList();
  }
}
