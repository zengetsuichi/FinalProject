package server.networking.servermodel.administratorServerModel;

import shared.util.ProductList;
import shared.util.PropertyChangeSubject;
import shared.util.ShopPrice;

import java.util.ArrayList;

public interface AdministratorServerModel extends PropertyChangeSubject
{
  ProductList loadProductData();
  ArrayList<ShopPrice> getShopPricesById(int productId);
  ArrayList<String> getAllTagsById(int productId);
  String deleteProduct(int productId);
}
