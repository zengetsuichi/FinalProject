package server.networking;


import client.clientmodel.editProductShopManagerModel.EditProductShopManagerModel;
import server.networking.servermodel.addNewProductAdminServerModel.AddNewProductAdminServerModel;
import server.networking.servermodel.administratorEditUserServerModel.AdministratorEditUserServerModel;
import server.networking.servermodel.administratorServerModel.AdministratorServerModel;
import server.networking.servermodel.administratorUsersServerModel.AdministratorUsersServerModel;
import server.networking.servermodel.editProductAdminServerModel.EditProductAdminServerModel;
import server.networking.servermodel.editProductShopManagerModel.EditProductShopManagerServerModel;
import server.networking.servermodel.loginRegisterServerModel.LoginRegisterServerModel;
import server.networking.servermodel.shopManagerServerModel.ShopManagerServerModel;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.util.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class implementing RMI Server interface responsible for receiving the requests
 * from client, as well as, processing them and responding back.
 *
 * Moreover, the class is in charge of updating all the clients that are active in case of events
 * fired from server models.
 *
 * @author Gosia, Piotr, Karlo, Dorin, Hadi
 */

public class RMIServerManager implements RMIServer
{
  private LoginRegisterServerModel loginRegisterServerModel;
  private AdministratorServerModel administratorServerModel;
  private AddNewProductAdminServerModel addNewProductAdminServerModel;
  private EditProductAdminServerModel editProductAdminServerModel;
  private ShopManagerServerModel shopManagerServerModel;
  private AdministratorUsersServerModel administratorUsersServerModel;
  private AdministratorEditUserServerModel administratorEditUserServerModel;
  private Map<ClientCallback, PropertyChangeListener> listeners = new HashMap<>();
  private EditProductShopManagerServerModel editProductShopManagerServerModel;

  public RMIServerManager(LoginRegisterServerModel loginRegisterServerModel, AdministratorServerModel administratorServerModel,
      AddNewProductAdminServerModel addNewProductAdminServerModel, EditProductAdminServerModel editProductAdminServerModel,
      ShopManagerServerModel shopManagerServerModel, AdministratorUsersServerModel administratorUsersServerModel,
      AdministratorEditUserServerModel administratorEditUserServerModel,
      EditProductShopManagerServerModel editProductShopManagerServerModel) throws RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    this.loginRegisterServerModel = loginRegisterServerModel;
    this.administratorServerModel = administratorServerModel;
    this.addNewProductAdminServerModel = addNewProductAdminServerModel;
    this.editProductAdminServerModel = editProductAdminServerModel;
    this.shopManagerServerModel = shopManagerServerModel;
    this.administratorUsersServerModel = administratorUsersServerModel;
    this.administratorEditUserServerModel= administratorEditUserServerModel;
    this.editProductShopManagerServerModel = editProductShopManagerServerModel;
  }

  public void startServer() throws RemoteException, AlreadyBoundException
  {
    Registry registry = LocateRegistry.createRegistry(1099);
    registry.bind("priceCheckerServer", this);
    System.out.println("Server started.");
  }

  @Override public ProductList loadProductData() throws RemoteException
  {
    return administratorServerModel.loadProductData();
  }

  @Override public ArrayList<ShopPrice> getShopPricesById(int productId)
      throws RemoteException
  {
    return administratorServerModel.getShopPricesById(productId);
  }

  @Override public ArrayList<String> getAllTagsById(int productId)
      throws RemoteException
  {
    return administratorServerModel.getAllTagsById(productId);
  }

  @Override public ArrayList<String> getAllProductCategories()
      throws RemoteException
  {
    return addNewProductAdminServerModel.getAllProductCategories();
  }

  @Override public ArrayList<String> getAllTags() throws RemoteException
  {
    return addNewProductAdminServerModel.getAllTags();
  }

  @Override public String addNewProduct(String productName,
      String productDescription, String category, ArrayList<String> parseTag)
      throws RemoteException
  {
    return addNewProductAdminServerModel.addNewProduct(productName, productDescription, category, parseTag);
  }

  @Override public String addNewCategory(String newCategory)
      throws RemoteException
  {
    return addNewProductAdminServerModel.addNewCategory(newCategory);
  }

  @Override public String addNewTag(String newTag) throws RemoteException
  {
    return addNewProductAdminServerModel.addNewTag(newTag);
  }

  @Override public String editProduct(String productName,
      String productDescription, String category, ArrayList<String> parseTag,
      int productId)
      throws RemoteException
  {
    return editProductAdminServerModel.editProduct(productName, productDescription, category, parseTag, productId);
  }

  @Override public String deleteProduct(int productId) throws RemoteException
  {
    return administratorServerModel.deleteProduct(productId);
  }

  @Override public void logOut(ClientCallback client) throws RemoteException
  {
    listeners.remove(client);
  }

  @Override public String validateLogin(String username, String password)
      throws RemoteException
  {
    return loginRegisterServerModel.validateLogin(username, password);
  }

  @Override
  public String validateRegister(String username, String email, String password, String dob) throws RemoteException {
    return loginRegisterServerModel.validateRegister(username,email,password,dob);
  }

  @Override public ArrayList<Product> getAllProductsForSpecificManager(
      String username) throws RemoteException
  {
    return shopManagerServerModel.getAllProductsForSpecificManager(username);
  }

  @Override public String deleteProductPrice(int productId, String username) throws RemoteException
  {
    return shopManagerServerModel.deleteProductPrice(productId, username);
  }

  @Override public List<User> getAllUsers() throws RemoteException
  {
    return administratorUsersServerModel.getAllUsers();
  }

  @Override public String addNewManager(User newManager) throws RemoteException
  {
    return administratorUsersServerModel.addNewManager(newManager);
  }

  @Override public String validateUserEdit(String oldUsername, String oldEmail, String username, String email, String password, String dob)
  {
    return administratorEditUserServerModel.validateUserEdit(oldUsername, oldEmail, username,email,password,dob);
  }

  /**
   * A method used for saving the client references into the pool of listeners.
   * @author Gosia
   */

  @Override public void registerClient(ClientCallback client) throws RemoteException
  {
    PropertyChangeListener listener = new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent evt) {
        try {
          client.update(evt.getPropertyName(), evt.getNewValue());
        } catch (RemoteException e) {
          e.printStackTrace();
        }
      }
    };
    listeners.put(client, listener);
    addNewProductAdminServerModel.addListener(EventType.NEW_PRODUCT.name(), listener);
    addNewProductAdminServerModel.addListener(EventType.NEW_CATEGORY.name(), listener);
    addNewProductAdminServerModel.addListener(EventType.NEW_TAG.name(), listener);
    editProductAdminServerModel.addListener(EventType.NEW_PRODUCT.name(), listener);
    administratorServerModel.addListener(EventType.DELETED_PRODUCT.name(), listener);
    shopManagerServerModel.addListener(EventType.DELETED_PRODUCT_PRICE.name(), listener);
    administratorUsersServerModel.addListener(EventType.NEW_SHOP_MANAGER.name(), listener);
    administratorEditUserServerModel.addListener(EventType.EDIT_USER.name(), listener);
    editProductShopManagerServerModel.addListener(EventType.EDIT_SHOP_MANAGER_PRODUCT.name(),listener);
    editProductShopManagerServerModel.addListener(EventType.NEW_PRODUCT.name(), listener);
  }
  @Override public String editShopProduct(String productName,
      String productDescription, String category, ArrayList<String> parseTag,
      int productId, int price,String username)
      throws RemoteException
  {
    return editProductShopManagerServerModel.editShopProduct(productName, productDescription,
        category, parseTag, productId,price,username);
  }
}
