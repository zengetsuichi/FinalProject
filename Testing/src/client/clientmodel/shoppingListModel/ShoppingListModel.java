package client.clientmodel.shoppingListModel;

import shared.util.Product;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface ShoppingListModel extends PropertyChangeSubject
{
  ArrayList<Product> loadShoppingList();
  Boolean clearSL();
}