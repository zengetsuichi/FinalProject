package client.views.editProductAdmin;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.SearchableComboBox;
import shared.util.Product;
import java.util.ArrayList;

/**
 * Class implementing the view controller interface. Used for initializing
 * view components, retrieving data from them and providing functionality
 * for components.
 *
 * @author Gosia, Karlo
 */

public class EditProductAdminController implements ViewController
{
  @FXML
  private TextField productNameTextField;
  @FXML
  private TextArea productDescriptionTextField;
  @FXML
  private TextField newTagTextField;
  @FXML
  private Button addNewTagBtn;
  @FXML
  private Button editProductBtn;
  @FXML
  private SearchableComboBox<String> categoryBox;
  @FXML
  private TextField newCategoryTextField;
  @FXML
  private Button addNewCategoryBtn;
  @FXML
  private Button goBackBtn;
  @FXML
  private CheckListView<String> checkListViewTags;
  @FXML
  private Label errorLabel;

  private ViewHandler viewHandler;
  private EditProductAdminViewModel editProductAdminViewModel;
  private Product product;
  private ObservableList<String> tags;

  @Override public void init(ViewHandler viewHandler, ViewModelFactory viewModelFactory)
  {
    this.viewHandler = viewHandler;
    editProductAdminViewModel = viewModelFactory.getEditProductAdminViewModel();

    productNameTextField.setText(product.getProductName());
    productDescriptionTextField.setText(product.getDescription());

    editProductAdminViewModel.loadData();

    categoryBox.setItems(editProductAdminViewModel.getAllProductCategories());
    checkListViewTags.setItems(editProductAdminViewModel.getAllTags());

    categoryBox.getSelectionModel().select(product.getCategory());
    selectAllTagsForThisProduct();
  }

  @Override public void handleClickMe(ActionEvent actionEvent)
  {
    if(actionEvent.getSource() == goBackBtn)
      viewHandler.openAdministratorView();
    else if(actionEvent.getSource() == addNewCategoryBtn)
    {
      addNewCategory();
    }
    else if(actionEvent.getSource() == addNewTagBtn)
    {
      addNewTag();
    }
    else if(actionEvent.getSource() == editProductBtn)
    {
      editProduct();
    }
  }

  private void editProduct()
  {
    String productName = productNameTextField.getText();
    String productDescription = productDescriptionTextField.getText();
    String category = categoryBox.selectionModelProperty().get().getSelectedItem();
    ObservableList<String> tags = checkListViewTags.getCheckModel().getCheckedItems();

    if(!productName.isEmpty() && productName.length() <= 40){
      if(!productDescription.isEmpty() && productDescription.length() <= 200){
        if(category != null){
          if(!tags.isEmpty()){
            ArrayList<String> parseTag = new ArrayList<>(tags);
            String response = editProductAdminViewModel.editProduct(productName, productDescription, category, parseTag, product.getProductId());

            if(response.equals("Product updated."))
              viewHandler.openAdministratorView();
            else
              errorLabel.setText("Product already exists.");
          }
          else{
            errorLabel.setText("Choose at least one product tag.");
          }
        }
        else{
          errorLabel.setText("Choose product category.");
        }
      }
      else{
        errorLabel.setText("Product description is empty or longer than 200 letters.");
      }
    }
    else{
      errorLabel.setText("Product name is empty or longer than 40 letters.");
    }
  }

  private void selectAllTagsForThisProduct()
  {
    for (int i = 0; i < editProductAdminViewModel.getAllTags().size(); i++)
    {
      for (int j = 0; j < tags.size(); j++)
      {
        if(tags.get(j).equals(editProductAdminViewModel.getAllTags().get(i)))
        {
          checkListViewTags.getCheckModel().check(i);
        }
      }
    }
  }

  public void setProductData(Product productId, ObservableList<String> tags)
  {
    this.product = productId;
    this.tags = tags;
  }

  private void addNewCategory()
  {
    String newCategory = newCategoryTextField.getText();
    if(!newCategory.isEmpty() && newCategory.length() <= 40)
    {
      String response = editProductAdminViewModel.addNewCategory(newCategory);
      errorLabel.setText(response);
      Platform.runLater(() -> categoryBox.getSelectionModel().select(product.getCategory()));
      newCategoryTextField.clear();
    } else
      errorLabel.setText("Category name is too long or category field is empty.");
  }

  private void addNewTag()
  {
    String newTag = newTagTextField.getText();
    if(!newTag.isEmpty() && newTag.length() <= 20)
    {
      String response = editProductAdminViewModel.addNewTag(newTag);
      errorLabel.setText(response);
      newTagTextField.clear();
      Platform.runLater(this::selectAllTagsForThisProduct);
    } else
      errorLabel.setText("Tag name is too long or tag field is empty.");
  }
}
