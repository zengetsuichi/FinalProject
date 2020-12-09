package dataaccess.addNewProductShopManagerDAO;

import shared.util.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddNewProductShopManagerDAO {



    ArrayList<String> getAllTags() throws SQLException;

    ArrayList<String> getAllProductCategories() throws SQLException;

    ArrayList<Product> getAllProducts() throws SQLException;

    String addNewProduct(String clientUsername, String productName, String productDescription, String category, ArrayList<String> parseTag, int price) throws SQLException;

    String addNewCategory(String newCategory) throws SQLException;

    String addNewTag(String newTag) throws SQLException;

    ArrayList<Product> getAllProductsFor(String username) throws SQLException;

    String editNewProduct(int userId, int price, int productid) throws SQLException;

    ArrayList<String> getAllTagsById(int productId) throws SQLException;

    int getUserId(String username) throws SQLException;
}
