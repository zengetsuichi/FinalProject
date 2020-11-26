package server.networking.servermodel.addNewProductAdminServerModel;

import shared.util.PropertyChangeSubject;

import java.util.ArrayList;

public interface AddNewProductAdminServerModel extends PropertyChangeSubject
{
  ArrayList<String> getAllProductCategories();
  ArrayList<String> getAllTags();
  String addNewProduct(String productName, String productDescription, String category, ArrayList<String> parseTag);
  String addNewCategory(String newCategory);
  String addNewTag(String newTag);
}
