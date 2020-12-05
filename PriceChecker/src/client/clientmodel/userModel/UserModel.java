package client.clientmodel.userModel;

import shared.util.Product;
import shared.util.ProductList;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface UserModel extends PropertyChangeSubject
{
  ProductList loadProductData();
  String getLoggedInUser();
  void logOut();
  ArrayList<Product> getThisUserShoppingList();
  boolean addProductToSL(Product item);
}
