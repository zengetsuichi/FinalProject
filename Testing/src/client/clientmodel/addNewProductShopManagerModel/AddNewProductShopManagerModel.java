package client.clientmodel.addNewProductShopManagerModel;

import shared.util.Product;
import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface AddNewProductShopManagerModel extends PropertyChangeSubject {
        /**
         * Interface is used for separating the view model from the model as
         * well as providing abstract classes to the model manager.
         *
         * The interface extends property change subject to listen and fire events
         * to the listeners of this model.
         *
         * @author Piotr
         */

        ArrayList<String> getAllProductCategories();
        ArrayList<String> getAllTags();
        ArrayList<Product> getAllProducts();
        String addNewProduct(String productName, String productDescription, String category, ArrayList<String> parseTag, int price);
        String addNewCategory(String newCategory);
        String addNewTag(String newTag);
        String getLoggedInUser();
        ArrayList<Product> getAllProductsFor();
        ArrayList<String> getAllTagsById(int productId);

    String editNewProduct(int userId, int price, int productid);


        int getUserId(String username);
}
