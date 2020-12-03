package client.views.addNewProductShopManager;

import client.clientmodel.addNewProductShopManagerModel.AddNewProductShopManagerModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.Product;

import java.util.ArrayList;

/**
 * Class responsible for managing and storing controller data.
 *
 * @author Piotr
 */


public class AddNewProductShopManagerViewModel {
    private AddNewProductShopManagerModel addNewProductShopManagerModel;
    private ObservableList<String> allProductCategories;
    private ObservableList<String> allTags;
    private ObservableList<Product> allProducts;
    private ObservableList<Product> listOfAllProductsForSpecificManager;
    private ObservableList<String> tagsForSpecificProduct;



    public AddNewProductShopManagerViewModel(AddNewProductShopManagerModel addNewProductShopManagerModel){
        this.addNewProductShopManagerModel = addNewProductShopManagerModel;
        allProductCategories = FXCollections.observableArrayList();
        allTags = FXCollections.observableArrayList();
        allProducts = FXCollections.observableArrayList();
        listOfAllProductsForSpecificManager = FXCollections.observableArrayList();
        tagsForSpecificProduct = FXCollections.observableArrayList();

    }

    public ObservableList<String> getAllProductCategories()
    {
        return allProductCategories;
    }

    public ObservableList<String> getAllTags()
    {
        return allTags;
    }

    public ObservableList<String> getAllTagsById(int productId)
    {
        ArrayList<String> tags = addNewProductShopManagerModel.getAllTagsById(productId);
        tagsForSpecificProduct.setAll(tags);
        return tagsForSpecificProduct;
    }

    public ObservableList<Product> getAllProducts() {return allProducts;}

    public void loadDataForProductsNames() {
        allProductCategories.setAll(addNewProductShopManagerModel.getAllProductCategories());
        allTags.setAll(addNewProductShopManagerModel.getAllTags());



        ArrayList<Product> products2 = addNewProductShopManagerModel.getAllProductsFor();
        listOfAllProductsForSpecificManager.setAll(products2);

        ArrayList<Product> products = addNewProductShopManagerModel.getAllProducts();


        allProducts.setAll(products);


    }




    public String addNewProduct(String productName, String productDescription, String category, ArrayList<String> parseTag, int price) {
        return addNewProductShopManagerModel.addNewProduct(productName,productDescription,category,parseTag,price);
    }

    public String addNewCategory(String newCategory)
    {
        return addNewProductShopManagerModel.addNewCategory(newCategory);
    }

    public String addNewTag(String newTag)
    {
        return addNewProductShopManagerModel.addNewTag(newTag);
    }



    public String getLoggedInUser() {
        return addNewProductShopManagerModel.getLoggedInUser();
    }


    public ObservableList<Product> getAllProductsInfoForSpecificManager() {
        return  listOfAllProductsForSpecificManager;
    }


}
