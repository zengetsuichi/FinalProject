package server.networking.servermodel.addNewProductShopManagerServerModel;

import shared.util.Product;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface AddNewProductShopManagerServerModel  extends PropertyChangeSubject {


    ArrayList<String> getAllProductCategories();
    ArrayList<String> getAllTags();
    String addNewProduct(String clientUsername,String productName, String productDescription, String category, ArrayList<String> parseTag, int price );
    String addNewCategory(String newCategory);
    String addNewTag(String newTag);
    ArrayList<Product> getAllProducts();
    ArrayList<Product> getAllProductsFor(String username);

}
