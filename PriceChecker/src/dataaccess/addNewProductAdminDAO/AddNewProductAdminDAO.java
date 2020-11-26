package dataaccess.addNewProductAdminDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddNewProductAdminDAO
{
  ArrayList<String> getAllProductCategories() throws SQLException;
  ArrayList<String> getAllTags() throws SQLException;
  String addNewProduct(String productName, String productDescription,
      String category, ArrayList<String> parseTag) throws SQLException;
  String addNewCategory(String newCategory) throws SQLException;
  String addNewTag(String newTag) throws SQLException;
}
