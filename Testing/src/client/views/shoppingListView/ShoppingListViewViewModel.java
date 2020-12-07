package client.views.shoppingListView;

import client.clientmodel.shoppingListModel.ShoppingListModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.Product;

public class ShoppingListViewViewModel
{
  private ShoppingListModel shoppingListModel;
  private ObservableList<Product> shoppingList;

  public ShoppingListViewViewModel(ShoppingListModel shoppingListModel)
  {
    this.shoppingListModel = shoppingListModel;
    shoppingList = FXCollections.observableArrayList();
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
}
