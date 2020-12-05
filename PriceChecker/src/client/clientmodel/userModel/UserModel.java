package client.clientmodel.userModel;

import shared.util.ProductList;
import shared.util.PropertyChangeSubject;
import shared.util.ShopPrice;

import java.util.ArrayList;

public interface UserModel extends PropertyChangeSubject
{
  ProductList loadProductData();
  String getLoggedInUser();
  void logOut();
}
