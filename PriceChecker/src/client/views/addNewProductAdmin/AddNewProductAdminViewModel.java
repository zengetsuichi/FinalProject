package client.views.addNewProductAdmin;
import client.clientmodel.addNewProductAdministratorModel.AddNewProductAdminModel;
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

public class AddNewProductAdminViewModel
{
  private AddNewProductAdminModel addNewProductAdminModel;
  private ObservableList<String> allProductCategories;
  private ObservableList<String> allTags;

  public AddNewProductAdminViewModel(AddNewProductAdminModel addNewProductAdminModel)
  {
    this.addNewProductAdminModel = addNewProductAdminModel;
    allProductCategories = FXCollections.observableArrayList();
    allTags = FXCollections.observableArrayList();
    addNewProductAdminModel.addListener(EventType.NEW_CATEGORY.name(), this::newCategory);
    addNewProductAdminModel.addListener(EventType.NEW_TAG.name(), this::newTag);
  }

  private void newTag(PropertyChangeEvent event)
  {
    Platform.runLater(() -> {
      ArrayList<String> newTags = (ArrayList<String>) event.getNewValue();
      allTags.setAll(newTags);
    });
  }

  private void newCategory(PropertyChangeEvent event)
  {
    Platform.runLater(() -> {
      ArrayList<String> newCategories = (ArrayList<String>) event.getNewValue();
      allProductCategories.setAll(newCategories);
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
    allProductCategories.setAll(addNewProductAdminModel.getAllProductCategories());
    allTags.setAll(addNewProductAdminModel.getAllTags());
  }

  public String addNewProduct(String productName, String productDescription, String category, ArrayList<String> parseTag)
  {
    return addNewProductAdminModel.addNewProduct(productName, productDescription, category, parseTag);
  }

  public String addNewCategory(String newCategory)
  {
    return addNewProductAdminModel.addNewCategory(newCategory);
  }

  public String addNewTag(String newTag)
  {
    return addNewProductAdminModel.addNewTag(newTag);
  }
}
