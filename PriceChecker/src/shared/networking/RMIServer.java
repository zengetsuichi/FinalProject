package shared.networking;

import client.networking.RMIClient;
import shared.util.Product;
import shared.util.ProductList;
import shared.util.ShopPrice;
import shared.util.User;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * An RMIServer interface is used for separating the Client
 * from the server networking layer, providing methods that client calls
 * on the server.
 *
 * @author Gosia, Piotr, Karlo, Dorin, Hadi
 */

public interface RMIServer extends Remote
{
  String validateLogin(String username, String password) throws RemoteException;
  void registerClient(ClientCallback client) throws RemoteException;
  void startServer() throws RemoteException, AlreadyBoundException;
  ProductList loadProductData() throws RemoteException;
  ArrayList<ShopPrice> getShopPricesById(int productId) throws RemoteException;
  ArrayList<String> getAllTagsById(int productId) throws RemoteException;
  ArrayList<String> getAllProductCategories() throws RemoteException;
  ArrayList<String> getAllTags() throws RemoteException;
  String addNewProduct(String productName, String productDescription, String category, ArrayList<String> parseTag) throws RemoteException;
  String addNewCategory(String newCategory) throws RemoteException;
  String addNewTag(String newTag) throws RemoteException;
  String editProduct(String productName, String productDescription, String category, ArrayList<String> parseTag,
      int productId) throws RemoteException;
  String deleteProduct(int productId) throws RemoteException;
  void logOut(ClientCallback client) throws RemoteException;
  String validateRegister(String username, String email, String password, String dob) throws RemoteException;
  ArrayList<Product> getAllProductsForSpecificManager(String username) throws RemoteException;
  String deleteProductPrice(int productId, String username) throws RemoteException;
  List<User> getAllUsers() throws RemoteException;
  String addNewManager(User newManager) throws RemoteException;
  String validateUserEdit(String oldUsername, String oldEmail, String username, String email, String password, String dob) throws RemoteException;
//  void editUser(RMIClient rmiClient) throws RemoteException;
String editShopProduct(String productName, String productDescription, String category, ArrayList<String> parseTag,
    int productId, int price,String username)
    throws RemoteException;
}
