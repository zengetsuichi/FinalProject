package client.views.administrator;

import client.clientmodel.administratorModel.AdministratorModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventType;
import shared.util.Product;
import shared.util.ProductList;
import shared.util.ShopPrice;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * Class responsible for managing and storing controller data.
 *
 * @author Gosia, Karlo
 */

public class AdministratorViewModel
{
  private AdministratorModel administratorModel;
  private ObservableList<Product> listOfAllProducts;
  private ObservableList<ShopPrice> shopPricesForSpecificProduct;
  private ObservableList<String> tagsForSpecificProduct;

  public AdministratorViewModel(AdministratorModel administratorModel)
  {
    this.administratorModel = administratorModel;
    listOfAllProducts = FXCollections.observableArrayList();
    shopPricesForSpecificProduct = FXCollections.observableArrayList();
    tagsForSpecificProduct = FXCollections.observableArrayList();
    administratorModel.addListener(EventType.NEW_PRODUCT.name(), this::newProduct);
    administratorModel.addListener(EventType.DELETED_PRODUCT.name(), this::newProduct);
  }

  private void newProduct(PropertyChangeEvent propertyChangeEvent)
  {
    Platform.runLater(()-> {
      //listOfAllProducts.clear();
      ProductList productList = (ProductList) propertyChangeEvent.getNewValue();
      listOfAllProducts.setAll(productList.getProducts());
    });
  }

  public void loadProductData()
  {
    listOfAllProducts.setAll(administratorModel.loadProductData().getProducts());
  }

  public ObservableList<Product> getListOfAllProducts(){
    return listOfAllProducts;
  }

  public ObservableList<ShopPrice> getShopPricesById(int productId)
  {
    ArrayList<ShopPrice> shopPrices = administratorModel.getShopPricesById(productId);
    //shopPricesForSpecificProduct.clear();
    shopPricesForSpecificProduct.setAll(shopPrices);
    return shopPricesForSpecificProduct;
  }

  public ObservableList<String> getAllTagsById(int productId)
  {
    ArrayList<String> tags = administratorModel.getAllTagsById(productId);
    tagsForSpecificProduct.setAll(tags);
    return tagsForSpecificProduct;
  }

  public String deleteProduct(int productId)
  {
    return administratorModel.deleteProduct(productId);
  }

  public void logOut()
  {
    administratorModel.logOut();
  }
}
