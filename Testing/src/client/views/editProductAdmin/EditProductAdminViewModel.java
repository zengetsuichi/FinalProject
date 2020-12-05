package client.views.editProductAdmin;

import client.clientmodel.editProductAdministratorModel.EditProductAdministratorModel;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.util.EventType;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * Class responsible for managing and storing controller data.
 *
 * @author Gosia, Karlo
 */

public class EditProductAdminViewModel
{
  private EditProductAdministratorModel editProductAdministratorModel;
  private ObservableList<String> allProductCategories;
  private ObservableList<String> allTags;

  public EditProductAdminViewModel(EditProductAdministratorModel editProductAdministratorModel)
  {
    this.editProductAdministratorModel = editProductAdministratorModel;
    allProductCategories = FXCollections.observableArrayList();
    allTags = FXCollections.observableArrayList();
    editProductAdministratorModel.addListener(EventType.NEW_CATEGORY.name(), this::newCategory);
    editProductAdministratorModel.addListener(EventType.NEW_TAG.name(), this::newTag);
  }

  private void newTag(PropertyChangeEvent event)
  {
    Platform.runLater(() -> {
      //allTags.clear();
      ArrayList<String> newTags = (ArrayList<String>) event.getNewValue();
      allTags.setAll(newTags);
      //for (String newTag : newTags)
        //allTags.add(newTag);
    });
  }

  private void newCategory(PropertyChangeEvent event)
  {
    Platform.runLater(() -> {
      //allProductCategories.clear();
      ArrayList<String> newCategories = (ArrayList<String>) event.getNewValue();
      allProductCategories.setAll(newCategories);
      //for (String newCategory : newCategories)
        //allProductCategories.add(newCategory);
    });
  }

  public ObservableList<String> getAllProductCategories()
  {
    return allProductCategories;
  }

  public ObservableList<String> getAllTags()
  {
    return allTags;
  }

  public void loadData()
  {
    allProductCategories.setAll(editProductAdministratorModel.getAllProductCategories());
    allTags.setAll(editProductAdministratorModel.getAllTags());
  }

  public String addNewCategory(String newCategory)
  {
    return editProductAdministratorModel.addNewCategory(newCategory);
  }

  public String addNewTag(String newTag)
  {
    return editProductAdministratorModel.addNewTag(newTag);
  }

  public String editProduct(String productName, String productDescription,
      String category, ArrayList<String> parseTag, int productId)
  {
    return editProductAdministratorModel.editProduct(productName, productDescription, category, parseTag, productId);
  }
}
